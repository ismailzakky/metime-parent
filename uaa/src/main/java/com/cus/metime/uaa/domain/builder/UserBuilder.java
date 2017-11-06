package com.cus.metime.uaa.domain.builder;

import com.cus.metime.uaa.domain.Authority;
import com.cus.metime.uaa.domain.User;

import java.time.Instant;
import java.util.Set;

public class UserBuilder {
    private Long id;
    private String uuid;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private boolean activated;
    private String langKey;
    private String imageUrl;
    private String activationKey;
    private String resetKey;
    private Instant resetDate;
    private Set<Authority> authorities;

    public UserBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public UserBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setActivated(boolean activated) {
        this.activated = activated;
        return this;
    }

    public UserBuilder setLangKey(String langKey) {
        this.langKey = langKey;
        return this;
    }

    public UserBuilder setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public UserBuilder setActivationKey(String activationKey) {
        this.activationKey = activationKey;
        return this;
    }

    public UserBuilder setResetKey(String resetKey) {
        this.resetKey = resetKey;
        return this;
    }

    public UserBuilder setResetDate(Instant resetDate) {
        this.resetDate = resetDate;
        return this;
    }

    public UserBuilder setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
        return this;
    }

    public User createUser() {
        return new User(id, uuid, login, password, firstName, lastName, email, activated, langKey, imageUrl, activationKey, resetKey, resetDate, authorities);
    }
}
