package com.challeng.foro.services;

import com.challeng.foro.entities.CourseEntity;
import com.challeng.foro.repositories.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;

    public CourseServiceImpl(CourseRepository repository) {this.repository = repository;}


    @Override
    public CourseEntity existById(Long id) {

        return repository.findById(id).orElse(null);
    }
}
