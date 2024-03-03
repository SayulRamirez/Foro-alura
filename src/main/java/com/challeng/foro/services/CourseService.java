package com.challeng.foro.services;

import com.challeng.foro.entities.CourseEntity;

public interface CourseService {

    /**
     * Look for a course and if it exists, return it
     * @param id {@link Long}
     * @return CourseEntity
     */
    CourseEntity existById(Long id);
}
