package com.challeng.foro.repositories;

import com.challeng.foro.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Exist by email
     * @param email {@link String}
     * @return true if exists or false if not
     */
    boolean existsByEmail(String email);

}
