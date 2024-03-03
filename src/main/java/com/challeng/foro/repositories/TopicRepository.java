package com.challeng.foro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<TopicRepository, Long> {

    boolean existsByTitle(String title);

    boolean existsByContent(String content);
}
