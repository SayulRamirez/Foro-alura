package com.challeng.foro.services;

import com.challeng.foro.domain.User;

public interface UserService {

    User create(User user);

    boolean existsByEmail(String email);

}
