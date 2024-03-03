package com.challeng.foro.services;

import com.challeng.foro.domain.User;
import com.challeng.foro.entities.UserEntity;

public interface UserService {

    User create(User user);

    boolean existsByEmail(String email);

    UserEntity existsById(Long id);
}
