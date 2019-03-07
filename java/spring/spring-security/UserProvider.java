package com.oryam.howto.domain.spi;

import java.util.List;

import com.oryam.howto.domain.common.UserDetail;

public interface UserProvider {

    UserDetail getByLogin(String login);

}
