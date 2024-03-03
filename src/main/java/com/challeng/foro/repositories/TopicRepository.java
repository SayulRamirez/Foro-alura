package com.challeng.foro.repositories;

import com.challeng.foro.entities.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, Long> {

    /**
     * Validate that the title does not exist in the database
     * @param title {@link String}
     * @return boolean
     */
    boolean existsByTitle(String title);

    /**
     * Validate that the content the topic does not exist in the database
     * @param content {@link String}
     * @return boolean
     */
    boolean existsByContent(String content);

}
