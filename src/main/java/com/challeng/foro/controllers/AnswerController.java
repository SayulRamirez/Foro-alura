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
@RequestMapping("/api/v1/answers")
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


    @Operation(summary = "Enlist all answer", description = "Lists all the responses of a topic passing the topic identifier as a parameter",
            parameters = @Parameter(name = "id", description = "Topic identifier", example = "1"),
            responses = {@ApiResponse(description = "If not found topic", responseCode = "404"),
                    @ApiResponse(description = "Return answers list", responseCode = "200")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<List<Answer>> allAnswers(@PathVariable Long id) {

        List<Answer> answers = answerService.allAnswers(id);

        if (answers == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(answers);
    }

    @Operation(summary = "Edit answer", description = "Edit the content of the response",
            parameters = @Parameter(name = "UpdateAnswer", schema = @Schema(contains = UpdateAnswer.class)),
            responses = {
                    @ApiResponse(description = "If answer not found", responseCode = "404"),
                    @ApiResponse(description = "Return edited answer", responseCode = "200",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Answer.class)))
            }
    )
    @PutMapping
    public ResponseEntity<Answer> editAnswer(@Valid @RequestBody UpdateAnswer updateAnswer) {

        Answer answer = answerService.edit(updateAnswer);

        if (answer == null) throw new NotFoundAnswer("Not exists answer whit data provided");

        return ResponseEntity.ok(answer);
    }

    @Operation(summary = "Delete an answer", description = "Delete a response using its identifier",
            parameters = @Parameter(name = "id", example = "12"),
            responses = @ApiResponse(description = "Deletion of the answer.", responseCode = "204", content = @Content(mediaType = "Not content"))
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long id) {

        answerService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
