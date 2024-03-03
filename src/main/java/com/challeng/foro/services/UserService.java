package com.challeng.foro.services;

import com.challeng.foro.domain.User;
import com.challeng.foro.entities.UserEntity;

public interface UserService {

    /**
     * Create new User
     * @param user {@link User}
     * @return User
     */
    User create(User user);

    /**
     * Validate that the email does not exist in the database
     * @param email {@link String}
     * @return boolean
     */
    boolean existsByEmail(String email);

    /**
     * Look for a user and if it exists, return it
     * @param id {@link Long}
     * @return UserEntity
     */
    UserEntity existsById(Long id);
}
