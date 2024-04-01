package com.challeng.foro.services;

import com.challeng.foro.entities.UserEntity;

public interface UserService {


    /**
     * Look for a user and if it exists, return it
     * @param id {@link Long}
     * @return UserEntity
     */
    UserEntity existsById(Long id);
}
