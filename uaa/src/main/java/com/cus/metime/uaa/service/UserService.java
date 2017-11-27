package com.cus.metime.uaa.service;

import com.cloudinary.Cloudinary;
import com.cus.metime.shared.messaging.MessageEvent;
import com.cus.metime.shared.services.CloudinaryFileHandling;
import com.cus.metime.shared.util.RandomString;
import com.cus.metime.uaa.assembler.UserToUserDTOAssembler;
import com.cus.metime.uaa.config.Constants;
import com.cus.metime.uaa.domain.Authority;
import com.cus.metime.uaa.domain.CloudinaryImage;
import com.cus.metime.uaa.domain.User;
import com.cus.metime.uaa.domain.builder.UserBuilder;
import com.cus.metime.uaa.dto.CloudinaryImageDTO;
import com.cus.metime.uaa.dto.builder.CloudinaryImageDTOBuilder;
import com.cus.metime.uaa.repository.AuthorityRepository;
import com.cus.metime.uaa.repository.UserRepository;
import com.cus.metime.shared.security.uaa.AuthoritiesConstants;
import com.cus.metime.uaa.rest.param.UpdateProfileParam;
import com.cus.metime.uaa.security.SecurityUtils;
import com.cus.metime.uaa.service.dto.UserDTO;
import com.cus.metime.uaa.service.util.RandomUtil;
import org.slf4j.Logger;
import com.cloudinary.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthorityRepository authorityRepository;

    private final AssyncMessagingService assyncMessagingService;

    private final UserToUserDTOAssembler userToUserDTOAssembler;

    @Value("${cloudinary.url}")
    private String cloudinaryConnectionString;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository, UserToUserDTOAssembler userToUserDTOAssembler, AssyncMessagingService assyncMessagingService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
        this.assyncMessagingService = assyncMessagingService;
        this.userToUserDTOAssembler = userToUserDTOAssembler;
    }

    public Optional<User> activateRegistration(String key) {
        log.debug("Activating user for activation key {}", key);
        return userRepository.findOneByActivationKey(key)
            .map(user -> {
                // activate given user for the registration key.
                user.setActivated(true);
                user.setActivationKey(null);
                log.debug("Activated user: {}", user);
                return user;
            });
    }

    public Optional<User> completePasswordReset(String newPassword, String key) {
       log.debug("Reset user password for reset key {}", key);

       return userRepository.findOneByResetKey(key)
           .filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400)))
           .map(user -> {
                user.setPassword(passwordEncoder.encode(newPassword));
                user.setResetKey(null);
                user.setResetDate(null);
                return user;
           });
    }

    public Optional<User> requestPasswordReset(String mail) {
        return userRepository.findOneByEmail(mail)
            .filter(User::getActivated)
            .map(user -> {
                user.setResetKey(RandomUtil.generateResetKey());
                user.setResetDate(Instant.now());
                return user;
            });
    }

    public User createUser(String login, String password, String firstName, String lastName, String email,
        String imageUrl, String langKey) {

        User newUser = new UserBuilder().createUser();
        Authority authority = authorityRepository.findOne(AuthoritiesConstants.USER);
        Set<Authority> authorities = new HashSet<>();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(login);
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setCloudinaryImage(null);
        newUser.setLangKey(langKey);
        // new user is not active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        authorities.add(authority);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public User createUser(UserDTO userDTO) {
        User user = new UserBuilder().createUser();
        user.setLogin(userDTO.getLogin());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setCloudinaryImage(userDTO.getCloudinaryImage());
        user.setUuid(UUID.randomUUID().toString());
        if (userDTO.getLangKey() == null) {
            user.setLangKey("en"); // default language
        } else {
            user.setLangKey(userDTO.getLangKey());
        }
        if (userDTO.getAuthorities() != null) {
            Set<Authority> authorities = new HashSet<>();
            userDTO.getAuthorities().forEach(
                authority -> authorities.add(authorityRepository.findOne(authority))
            );
            user.setAuthorities(authorities);
        }
        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
        user.setPassword(encryptedPassword);
        user.setResetKey(RandomUtil.generateResetKey());
        user.setResetDate(Instant.now());
        user.setActivated(true);
        userRepository.save(user);
        log.debug("Created Information for User: {}", user);
        return user;
    }

    /**
     * Update basic information (first name, last name, email, language) for the current user.
     *
     * @param firstName first name of user
     * @param lastName last name of user
     * @param email email id of user
     * @param langKey language key
     * @param imageUrl image URL of user
     */
    public void updateUser(String firstName, String lastName, String email, String langKey, CloudinaryImage imageUrl) {
        userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).ifPresent(user -> {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setLangKey(langKey);
            user.setCloudinaryImage(imageUrl);
            log.debug("Changed Information for User: {}", user);
        });
    }

    /**
     * Update all information for a specific user, and return the modified user.
     *
     * @param userDTO user to update
     * @return updated user
     */
    public Optional<UserDTO> updateUser(UserDTO userDTO) {
        return Optional.of(userRepository
            .findOne(userDTO.getId()))
            .map(user -> {
                user.setLogin(userDTO.getLogin());
                user.setFirstName(userDTO.getFirstName());
                user.setLastName(userDTO.getLastName());
                user.setEmail(userDTO.getEmail());
                user.setCloudinaryImage(userDTO.getCloudinaryImage());
                user.setActivated(userDTO.isActivated());
                user.setLangKey(userDTO.getLangKey());
                Set<Authority> managedAuthorities = user.getAuthorities();
                managedAuthorities.clear();
                userDTO.getAuthorities().stream()
                    .map(authorityRepository::findOne)
                    .forEach(managedAuthorities::add);
                log.debug("Changed Information for User: {}", user);
                return user;
            })
            .map(UserDTO::new);
    }

    public Optional<UserDTO> updateUser(UpdateProfileParam updateProfileParam) {
        return Optional.of(userRepository
            .findOne(updateProfileParam.getId()))
            .map(user -> {
                user.setLogin(updateProfileParam.getLogin());
                user.setFirstName(updateProfileParam.getFirstName());
                user.setLastName(updateProfileParam.getLastName());
                user.setEmail(updateProfileParam.getEmail());
                user.setWebProfile(updateProfileParam.getWebProfile());
                user.setLastName(updateProfileParam.getLastName());
                user.setFirstName(updateProfileParam.getFirstName());
                user.setEmail(updateProfileParam.getEmail());
                user.setUuid(updateProfileParam.getUuid());
                Set<Authority> managedAuthorities = user.getAuthorities();
                managedAuthorities.clear();
                updateProfileParam.getRoles().stream()
                    .map(authorityRepository::findOne)
                    .forEach(managedAuthorities::add);
                log.debug("Changed Information for User: {}", user);
                return user;
            })
            .map(UserDTO::new);
    }

    public void deleteUser(String login) {
        userRepository.findOneByLogin(login).ifPresent(user -> {
            userRepository.delete(user);
            log.debug("Deleted User: {}", user);
        });
    }

    public void changePassword(String password) {
        userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).ifPresent(user -> {
            String encryptedPassword = passwordEncoder.encode(password);
            user.setPassword(encryptedPassword);
            log.debug("Changed password for User: {}", user);
        });
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> getAllManagedUsers(Pageable pageable) {
        return userRepository.findAllByLoginNot(pageable, Constants.ANONYMOUS_USER).map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneWithAuthoritiesByLogin(login);
    }

    @Transactional(readOnly = true)
    public User getUserWithAuthorities(Long id) {
        return userRepository.findOneWithAuthoritiesById(id);
    }

    @Transactional(readOnly = true)
    public User getUserWithAuthorities() {
        return userRepository.findOneWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin()).orElse(null);
    }

    /**
     * Not activated users should be automatically deleted after 3 days.
     * <p>
     * This is scheduled to get fired everyday, at 01:00 (am).
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeNotActivatedUsers() {
        List<User> users = userRepository.findAllByActivatedIsFalseAndCreatedDateBefore(Instant.now().minus(3, ChronoUnit.DAYS));
        for (User user : users) {
            log.debug("Deleting not activated user {}", user.getLogin());
            userRepository.delete(user);
        }
    }

    /**
     * @return a list of all the authorities
     */
    public List<String> getAuthorities() {
        return authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());
    }


    @Transactional
    public User updateUserPhoto(User user, MultipartFile multipartFile) {

        CloudinaryFileHandling cloudinaryFileHandling = new CloudinaryFileHandling(cloudinaryConnectionString);
        Boolean successUpload = false;
        CloudinaryImageDTO cloudinaryImageDTO = null;
        try {

            if(user.getCloudinaryImage() != null){
                cloudinaryFileHandling.deleteImage(user.getCloudinaryImage().getPublicId());
            }

            Map resultMap = cloudinaryFileHandling.uploadIMage(multipartFile);
            Integer version = (Integer) resultMap.get("version");
            cloudinaryImageDTO = new CloudinaryImageDTOBuilder()
                .setPublicId((String) resultMap.get("public_id"))
                .setVersion(version.toString())
                .setSignature((String) resultMap.get("signature"))
                .setWidth(Float.parseFloat(  String.valueOf((Integer) resultMap.get("width"))))
                .setHeight(Float.parseFloat(   String.valueOf((Integer) resultMap.get("height"))))
                .setFormat((String) resultMap.get("format"))
                .setResourceType((String) resultMap.get("resource_type"))
                .setBytes(Long.parseLong(String.valueOf((Integer) resultMap.get("bytes"))))
                .setType((String) resultMap.get("type"))
                .setUrl((String) resultMap.get("url"))
                .setSecureUrl((String) resultMap.get("secure_url"))
                .setEtag((String)resultMap.get("etag"))
                .createCloudinaryImageDTO();
            successUpload = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(successUpload){
                user.setCloudinaryImage(cloudinaryImageDTO);
                userRepository.save(user);
            }
        }
        return user;
    }

    public void saveNewSalonManager(User user, String salonId) {
        userRepository.save(user);
    }
}
