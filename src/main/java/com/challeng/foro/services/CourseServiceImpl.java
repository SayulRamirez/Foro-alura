package com.challeng.foro.services;

import com.challeng.foro.domain.TopicSearch;
import com.challeng.foro.entities.CourseEntity;
import com.challeng.foro.entities.TopicEntity;
import com.challeng.foro.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;

    public CourseServiceImpl(CourseRepository repository) {this.repository = repository;}


    @Override
    public CourseEntity findById(Long id) {return repository.findById(id).orElse(null);}

    @Override
    public boolean existsCourse(Long id) {
        return repository.existsById(id);
    }

    @Override
    public List<TopicSearch> searchByCourse(Long id) {

        CourseEntity courseEntity = repository.findById(id).orElse(null);

        if (courseEntity == null) return null;

        List<TopicEntity> topicEntities = courseEntity.getTopics();

        if (topicEntities == null) return null;

        List<TopicSearch> topics = new ArrayList<>();

        topicEntities.forEach(entity ->
                topics.add(new TopicSearch(entity.getId(), entity.getTitle(), entity.getPublicationDate())));

        return topics;
    }
}
