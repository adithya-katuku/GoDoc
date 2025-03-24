package com.godoc.service.credentials;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    HOSPITAL, BRANCH, DOCTOR, STAFF, PATIENT;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
