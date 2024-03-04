package com.challeng.foro.services;

import com.challeng.foro.domain.*;
import com.challeng.foro.entities.CourseEntity;
import com.challeng.foro.entities.TopicEntity;
import com.challeng.foro.entities.UserEntity;
import com.challeng.foro.repositories.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository repository;

    public TopicServiceImpl(TopicRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsByTitle(String title) {
        return repository.existsByTitle(title);
    }

    @Override
    public boolean existsByContent(String content) {
        return repository.existsByContent(content);
    }

    @Override
    public ResponseCreateTopic create(CreateTopic createTopic, UserEntity user, CourseEntity course) {

        TopicEntity topic = new TopicEntity(null, createTopic.title(), createTopic.content(), null, null, user, course);

        repository.save(topic);

        return new ResponseCreateTopic(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getPublicationDate(),
                new Author(topic.getAuthor().getId(), topic.getAuthor().getName()),
                new Course(topic.getCourse().getName(), topic.getCourse().getCategory().getNameCategory())
        );
    }

    @Override
    public List<ResponseTopic> findAll() {

        List<TopicEntity> topicEntities = repository.findAll();

        List<ResponseTopic> topics = new ArrayList<>();

        topicEntities.forEach(topic -> topics.add(new ResponseTopic(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getPublicationDate(),
                topic.getStatus().getStatus(),
                new Author(topic.getAuthor().getId(), topic.getAuthor().getName()),
                new Course(topic.getCourse().getName(), topic.getCourse().getCategory().getNameCategory())
        )));

        return topics;
    }

    @Override
    public ResponseTopic findById(Long id) {

        TopicEntity topic = repository.findById(id).orElse(null);

        if (topic == null) return null;

        return new ResponseTopic(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getPublicationDate(),
                topic.getStatus().getStatus(),
                new Author(topic.getAuthor().getId(), topic.getAuthor().getName()),
                new Course(topic.getCourse().getName(), topic.getCourse().getCategory().getNameCategory())
        );

    }
}
