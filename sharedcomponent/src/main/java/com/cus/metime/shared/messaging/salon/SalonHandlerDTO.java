package com.cus.metime.shared.messaging.salon;

import com.cus.metime.shared.security.uaa.AuthoritiesConstants;

import java.io.Serializable;

public class SalonHandlerDTO implements Serializable{

    private String salonId;
    private AuthoritiesConstants role;
    private String email;
    private String firstName;
    private String lastName;
    private String login;

    public SalonHandlerDTO(String salonId, AuthoritiesConstants role, String email, String firstName, String lastName, String login) {
        this.salonId = salonId;
        this.role = role;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
    }

    public SalonHandlerDTO() {
    }

    public String getSalonId() {
        return salonId;
    }

    public void setSalonId(String salonId) {
        this.salonId = salonId;
    }

    public AuthoritiesConstants getRole() {
        return role;
    }

    public void setRole(AuthoritiesConstants role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
