package com.cus.metime.uaa.service.dto.builder;

import com.cus.metime.uaa.domain.Authority;
import com.cus.metime.uaa.domain.CloudinaryImage;
import com.cus.metime.uaa.domain.User;
import com.cus.metime.uaa.service.dto.UserDTO;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDTOBuilder {

    private String uuid;
    private User user;
    private CloudinaryImage imageUrl = user.getCloudinaryImage();

    private Long id = user.getId();
    private String login = user.getLogin();
    private String firstName = user.getFirstName();
    private String lastName = user.getLastName();
    private String email = user.getEmail();
    private CloudinaryImage cloudinaryImage;
    private String webProfile;
    private boolean activated = user.getActivated();
    private String langKey = user.getLangKey();
    private String createdBy = user.getCreatedBy();
    private Instant createdDate = user.getCreatedDate();
    private String lastModifiedBy = user.getLastModifiedBy();
    private Instant lastModifiedDate = user.getLastModifiedDate();
    private Set<String> authorities = user.getAuthorities().stream().map(Authority::getName)
        .collect(Collectors.toSet());

    public UserDTOBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public UserDTOBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public UserDTOBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserDTOBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserDTOBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDTOBuilder setCloudinaryImage(CloudinaryImage cloudinaryImage) {
        this.cloudinaryImage = cloudinaryImage;
        return this;
    }

    public UserDTOBuilder setWebProfile(String webProfile) {
        this.webProfile = webProfile;
        return this;
    }

    public UserDTOBuilder setActivated(boolean activated) {
        this.activated = activated;
        return this;
    }

    public UserDTOBuilder setLangKey(String langKey) {
        this.langKey = langKey;
        return this;
    }

    public UserDTOBuilder setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public UserDTOBuilder setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public UserDTOBuilder setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public UserDTOBuilder setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public UserDTOBuilder setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
        return this;
    }

    public UserDTOBuilder setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public UserDTOBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public UserDTOBuilder setImageUrl(CloudinaryImage imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public UserDTO createUserDTO() {
        return new UserDTO(id, login, firstName, lastName, email, cloudinaryImage, webProfile, activated, langKey, createdBy, createdDate, lastModifiedBy, lastModifiedDate, authorities, uuid);
    }
}
