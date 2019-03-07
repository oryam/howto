package com.oryam.howto.domain.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.oryam.howto.domain.common.UserDetail;
import com.oryam.howto.domain.type.UserProfileType;

public class UserPrincipal implements UserDetails {

    private static final long serialVersionUID = -3031931827614829105L;

    private final UserDetail user;

    public UserPrincipal(UserDetail user) {
        this.user = user;
    }

    public UserDetail getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(getUserRoleName()));
    }

    private String getUserRoleName() {
        return UserProfileType.build(user.getProfile()).getRoleName();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isEnabled() {
        return user.isActive();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
