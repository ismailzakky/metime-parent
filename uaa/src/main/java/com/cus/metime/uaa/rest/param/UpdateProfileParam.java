package com.cus.metime.uaa.rest.param;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Data
@Builder
public class UpdateProfileParam implements Serializable {

    @NotNull
    private String login;

    @NotNull
    private String webProfile;


    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Set<String> roles;

    @NotNull
    private String email;

    @NotNull
    private Long id;

    @NotNull
    private String uuid;

    public UpdateProfileParam() {
    }

    public UpdateProfileParam(String login, String webProfile, String firstName, String lastName, Set<String> roles, String email, Long id, String uuid) {
        this.login = login;
        this.webProfile = webProfile;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
        this.email = email;
        this.id = id;
        this.uuid = uuid;
    }
}
