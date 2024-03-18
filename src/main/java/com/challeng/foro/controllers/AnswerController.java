package com.challeng.foro.controllers;

import com.challeng.foro.domain.Answer;
import com.challeng.foro.domain.CreateAnswer;
import com.challeng.foro.domain.ResponseAnswerCreate;
import com.challeng.foro.domain.UpdateAnswer;
import com.challeng.foro.entities.TopicEntity;
import com.challeng.foro.entities.UserEntity;
import com.challeng.foro.exceptions.NotFoundAnswer;
import com.challeng.foro.exceptions.NotFoundTopic;
import com.challeng.foro.exceptions.NotFoundUser;
import com.challeng.foro.services.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Answer Controller",
        description = "In general, it allows you to add a new answer to a topic, list all the answers that a single topic contains. Also edit and delete a reply."
)
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

    @Operation(summary = "Create a new answer",
            parameters = @Parameter(name = "createAnswer", schema = @Schema(contains = CreateAnswer.class)),
            responses = {
                    @ApiResponse(description = "If  user not found or topic not found", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Create a new response and return it and its location.", responseCode = "201",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseAnswerCreate.class)))
            }
    )
    @PostMapping
    public ResponseEntity<ResponseAnswerCreate> createAnswer(@Valid @RequestBody CreateAnswer createAnswer) {

        UserEntity userEntity = userService.existsById(createAnswer.author_id());

        if (userEntity == null) throw new NotFoundUser("The author " + createAnswer.author_id() + " don´t exists");

        TopicEntity topicEntity = topicService.getTopic(createAnswer.topic_id());

        if (topicEntity == null) throw new NotFoundTopic("The topic " + createAnswer.topic_id() + " don't exists");

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

        if (answer == null) throw new NotFoundAnswer("Not exists answer whit data provided");

        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long id) {

        answerService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
