package com.challeng.foro.services;

import com.challeng.foro.domain.TopicSearch;
import com.challeng.foro.entities.CourseEntity;

import java.util.List;

public interface CourseService {

    /**
     * Look for a course and if it exists, return it
     * @param id {@link Long}
     * @return CourseEntity
     */
    CourseEntity findById(Long id);


    /**
     * Validate if exists course
     * @param id {@link Long}
     * @return boolean
     */
    boolean existsCourse(Long id);

    /**
     * Search all topic by id course
     * @param id {@link Long}
     * @return TopicSearch
     */
    List<TopicSearch> searchByCourse(Long id);
}
