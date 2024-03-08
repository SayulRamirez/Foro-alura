package com.challeng.foro.controllers;

import com.challeng.foro.domain.*;
import com.challeng.foro.entities.CourseEntity;
import com.challeng.foro.entities.TopicEntity;
import com.challeng.foro.entities.UserEntity;
import com.challeng.foro.exceptions.*;
import com.challeng.foro.services.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
        if (user == null) throw new BadParameterRequestException("Don't exists the user: " + createTopic.author_id());

        CourseEntity course = courseService.findById(createTopic.courso_id());
        if (course == null) throw new BadParameterRequestException("Don't exists the course: " + createTopic.courso_id());

        if (topicService.existsByTitle(createTopic.title())) throw new BadParameterRequestException("The content of the title already exists");

        if (topicService.existsByContent(createTopic.content())) throw new BadParameterRequestException("The content of the message already exists");

        ResponseCreateTopic topic = topicService.create(createTopic, user, course);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(topic.id()).toUri();

        return ResponseEntity.created(location).body(topic);
    }

    @GetMapping
    public ResponseEntity<List<ResponseTopic>> findall() {

        List<ResponseTopic> topics = topicService.findAll();

        return ResponseEntity.ok(topics);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DetailedTopic> findTopic(@PathVariable Long id) {

        DetailedTopic topic = topicService.findById(id);

        if (topic == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(topic);
    }

    @GetMapping("/shearch/{id}")
    public ResponseEntity<List<TopicSearch>> searchTopic(@PathVariable Long id) {

        if (!courseService.existsCourse(id)) throw new BadParameterRequestException("Don´t exist course");

        List<TopicSearch> topics = courseService.searchByCourse(id);

        return ResponseEntity.ok(topics);
    }

    @PutMapping
    public ResponseEntity<ResponseTopic> updateTopic(@Valid @RequestBody RequestUpdateTopic updateTopic) {

        if (topicService.existsByTitle(updateTopic.title())) throw new BadParameterRequestException("The content of the title already exists");

        if (topicService.existsByContent(updateTopic.content())) throw new BadParameterRequestException("The content of the message already exists");

        ResponseTopic topic = topicService.update(updateTopic);

        if (topic == null) throw new NotFoundTopic("The topic was not found with the topic number and user number provided");

        return ResponseEntity.ok(topic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable Long id) {

        topicService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
