package com.challeng.foro.controllers;

import com.challeng.foro.domain.Answer;
import com.challeng.foro.domain.CreateAnswer;
import com.challeng.foro.domain.ResponseAnswerCreate;
import com.challeng.foro.domain.UpdateAnswer;
import com.challeng.foro.entities.TopicEntity;
import com.challeng.foro.entities.UserEntity;
import com.challeng.foro.exceptions.BadParameterRequestException;
import com.challeng.foro.exceptions.NotFoundAnswer;
import com.challeng.foro.services.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<List<Answer>> allAnswers(@PathVariable Long id) {

        List<Answer> answers = answerService.allAnswers(id);

        if (answers == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(answers);
    }

    @PutMapping
    public ResponseEntity<Answer> editAnswer(@Valid @RequestBody UpdateAnswer updateAnswer) {

        Answer answer = answerService.edit(updateAnswer);

        if (answer == null) throw new NotFoundAnswer("Not exists answer whit data provied");

        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long id) {

        answerService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
