package com.bkda.security.models;

import java.util.List;

public class Scope {
    private List<AccountPermission> permissions;

    public List<AccountPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<AccountPermission> permissions) {
        this.permissions = permissions;
    }
}
