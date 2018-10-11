package com.bkda.security.models;

import org.springframework.security.authentication.BadCredentialsException;

public class AccountPermission {

    private long userId;
    private String application;
    private String group;
    private String role;

    public AccountPermission() { }

    public AccountPermission(String strPermission) {
        String[] permission = strPermission.split(":");
        if (permission.length != 3) {
            throw new BadCredentialsException("Invalid scope");
        }

        application = permission[0];
        group = permission[1];
        role = permission[2];
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return application + ":" + group + ":" + role;
    }
}
