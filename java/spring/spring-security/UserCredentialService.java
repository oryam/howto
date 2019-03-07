package com.oryam.howto.domain.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oryam.howto.domain.common.UserDetail;
import com.oryam.howto.domain.spi.UserProvider;

@Service
public class UserCredentialService implements UserDetailsService {

    @Autowired
    private UserProvider userProvider;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetail user = userProvider.getByLogin(username);
        return new UserPrincipal(user);
    }

}
