package com.challeng.foro.services;

import com.challeng.foro.domain.Course;
import com.challeng.foro.domain.CreateTopic;
import com.challeng.foro.domain.Topic;
import com.challeng.foro.domain.User;
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
     * Create new Topic
     * @param createTopic Parameters from topic
     * @param user {@link User}
     * @param course {@link Course}
     * @return Topic
     */
    Topic create(CreateTopic createTopic, UserEntity user, CourseEntity course);
}
