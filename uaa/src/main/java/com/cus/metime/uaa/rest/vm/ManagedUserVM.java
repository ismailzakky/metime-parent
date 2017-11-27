package com.cus.metime.uaa.rest.vm;

import com.cus.metime.uaa.domain.CloudinaryImage;
import com.cus.metime.uaa.domain.User;
import com.cus.metime.uaa.service.dto.UserDTO;

import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Set;

/**
 * View Model extending the UserDTO, which is meant to be used in the user management UI.
 */
public class ManagedUserVM extends UserDTO {

    public static final int PASSWORD_MIN_LENGTH = 4;

    public static final int PASSWORD_MAX_LENGTH = 100;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    public ManagedUserVM() {
        // Empty constructor needed for Jackson.
    }

    public ManagedUserVM(Long id, String login, String firstName, String lastName, String email, CloudinaryImage cloudinaryImage, String webProfile, boolean activated, String langKey, String createdBy, Instant createdDate, String lastModifiedBy, Instant lastModifiedDate, Set<String> authorities, String uuid, String password) {
        super(id, login, firstName, lastName, email, cloudinaryImage, webProfile, activated, langKey, createdBy, createdDate, lastModifiedBy, lastModifiedDate, authorities, uuid);
        this.password = password;
    }

    public ManagedUserVM(User user, String password) {
        super(user);
        this.password = password;
    }

    public ManagedUserVM(Long id, String login, String firstName, String lastName, String email, boolean activated, CloudinaryImage imageUrl, String langKey, String createdBy, Instant createdDate, String lastModifiedBy, Instant lastModifiedDate, Set<String> authorities, String password) {
        super(id, login, firstName, lastName, email, activated, imageUrl, langKey, createdBy, createdDate, lastModifiedBy, lastModifiedDate, authorities);
        this.password = password;
    }



    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "ManagedUserVM{" +
            "} " + super.toString();
    }
}
