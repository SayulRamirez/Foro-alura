package com.challeng.foro.services;

import com.challeng.foro.domain.*;
import com.challeng.foro.entities.CourseEntity;
import com.challeng.foro.entities.UserEntity;

public interface TopicService {

    /**
     * Compare if there is a title with the same name
     * @param title {@link String}
     * @return if exists id return true and false if not
     */
    boolean existsByTitle(String title);

    /**
     * Compare id there is a topic with the same content
     * @param content {@link String}
     * @return if exists id return true and false if not
     */
    boolean existsByContent(String content);

    /**
     * Create new ResponseCreateTopic
     * @param createTopic Parameters from topic
     * @param user {@link UserEntity}
     * @param course {@link CourseEntity}
     * @return Topic
     */
    ResponseCreateTopic create(CreateTopic createTopic, UserEntity user, CourseEntity course);
}
