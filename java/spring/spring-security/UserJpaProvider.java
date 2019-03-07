package com.oryam.howto.domain.spi.jpa.provider;

import static java.util.stream.Collectors.toList;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.oryam.howto.common.util.log.LogName;
import com.oryam.howto.common.util.log.LogTime;
import com.oryam.howto.domain.common.UserDetail;
import com.oryam.howto.domain.spi.UserProvider;
import com.oryam.howto.persistence.jpa.model.UserJpa;
import com.oryam.howto.persistence.jpa.repository.UserJpaRepository;

@Service
public class UserJpaProvider implements UserProvider {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @LogName
    @LogTime
    @Override
    public UserDetail getByLogin(String login) {
        return mapFromJpa(userJpaRepository.findByUserLogin(login));
    }

    private UserDetail mapFromJpa(UserJpa userJpa) {
        return new UserDetail()
                               .setId(userJpa.getUserId())
                               .setLogin(userJpa.getUserLogin())
                               .setProfile(userJpa.getUserProfile())
                               .setFirstname(userJpa.getUserFname())
                               .setLastname(userJpa.getUserLname())
                               .setActive(userJpa.isActive());
    }

}
