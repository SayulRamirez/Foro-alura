package com.challeng.foro.controllers;

import com.challeng.foro.domain.CreateAnswer;
import com.challeng.foro.domain.ResponseAnswerCreate;
import com.challeng.foro.entities.TopicEntity;
import com.challeng.foro.entities.UserEntity;
import com.challeng.foro.exceptions.BadParameterRequestException;
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
@RequestMapping("/answers")
public class AnswerController {

    private final AnswerService answerService;
    private final UserService userService;
    private final TopicService topicService;

    public AnswerController(AnswerServiceImpl answerService, UserServiceImpl userService,
                            TopicServiceImpl topicService) {
        this.answerService = answerService;
        this.userService = userService;
        this.topicService = topicService;
    }

    @PostMapping
    public ResponseEntity<ResponseAnswerCreate> createAnswer(@Valid @RequestBody CreateAnswer createAnswer) {

        UserEntity userEntity = userService.existsById(createAnswer.author_id());

        if (userEntity == null) throw new BadParameterRequestException("The author " + createAnswer.author_id() + " don´t exists");

        TopicEntity topicEntity = topicService.getTopic(createAnswer.topic_id());

        if (topicEntity == null) throw new BadParameterRequestException("The topic " + createAnswer.topic_id() + " don't exists");

        ResponseAnswerCreate response = answerService.addAnswer(createAnswer, userEntity, topicEntity);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.answer_id()).toUri();

        return ResponseEntity.created(location).body(response);
    }
}
