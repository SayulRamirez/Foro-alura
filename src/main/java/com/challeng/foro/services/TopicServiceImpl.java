package com.challeng.foro.services;

import com.challeng.foro.domain.*;
import com.challeng.foro.entities.CourseEntity;
import com.challeng.foro.entities.TopicEntity;
import com.challeng.foro.entities.UserEntity;
import com.challeng.foro.repositories.TopicRepository;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public boolean existsByTitle(String title) {
        return topicRepository.existsByTitle(title);
    }

    @Override
    public boolean existsByContent(String content) {
        return topicRepository.existsByContent(content);
    }

    @Override
    public ResponseCreateTopic create(CreateTopic createTopic, UserEntity user, CourseEntity course) {

        TopicEntity topic = new TopicEntity(null, createTopic.title(), createTopic.content(), null, null, user, course);

        topicRepository.save(topic);

        return new ResponseCreateTopic(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getPublicationDate(),
                new Author(topic.getAuthor().getId(), topic.getAuthor().getName()),
                new CourseR(topic.getCourse().getName(), topic.getCourse().getCategory().getNameCategory())
        );
    }
}
