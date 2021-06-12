package com.reddit.spring.model;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.reddit.spring.model.Permission.*;

public enum Role {
    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(ADMIN_READ, ADMIN_WRITE, USER_READ, USER_WRITE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}
