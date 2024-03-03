package com.challeng.foro.services;

import com.challeng.foro.domain.User;
import com.challeng.foro.entities.UserEntity;
import com.challeng.foro.repositories.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Lazy
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public User create(User user) {

        UserEntity userEntity = new UserEntity(null, user.getName(), user.getEmail(), user.getPassword());

        repository.save(userEntity);

        user.setId(userEntity.getId());

        return user;
    }

    @Override
    public boolean existsByEmail(String email) {

        return repository.existsByEmail(email);
    }

    @Override
    public UserEntity existsById(Long id) {

        return repository.findById(id).orElse(null);
    }
}
