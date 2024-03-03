package com.challeng.foro.controllers;

import com.challeng.foro.domain.Course;
import com.challeng.foro.domain.CreateTopic;
import com.challeng.foro.domain.Topic;
import com.challeng.foro.domain.User;
import com.challeng.foro.entities.CourseEntity;
import com.challeng.foro.entities.UserEntity;
import com.challeng.foro.exceptions.*;
import com.challeng.foro.services.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;
    private final UserService  userService;
    private final CourseService courseService;

    public TopicController(TopicServiceImpl topicService, UserServiceImpl userService,
                           CourseServiceImpl courseService) {
        this.topicService = topicService;
        this.userService = userService;
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateTopic createTopic) {

        UserEntity user = userService.existsById(createTopic.author_id());
        if (user == null) throw new NotExistsAuthorException("Don't exists the user: " + createTopic.author_id());

        CourseEntity course = courseService.existById(createTopic.courso_id());
        if (course == null) throw new NotExistsCourseException("Don't exists the course: " + createTopic.courso_id());

        if (!topicService.existsByTitle(createTopic.title())) throw new DuplicateTitleException("The content of the title already exists");

        if (!topicService.existsByContent(createTopic.content())) throw new DuplicateContentException("The content of the message already exists");

        Topic topic = topicService.create(createTopic, user, course);

        // creamos la url de respuesta

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(topic.getId()).toUri();

        // respondemos

        return ResponseEntity.created(location).body(topic);
    }
}
