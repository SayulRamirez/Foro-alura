package com.challeng.foro.services;

import com.challeng.foro.domain.Course;
import com.challeng.foro.entities.CourseEntity;

public interface CourseService {

    CourseEntity existById(Long id);
}
