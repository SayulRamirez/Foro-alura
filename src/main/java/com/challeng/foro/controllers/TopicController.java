package com.challeng.foro.controllers;

import com.challeng.foro.domain.*;
import com.challeng.foro.entities.CourseEntity;
import com.challeng.foro.entities.UserEntity;
import com.challeng.foro.exceptions.*;
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

@Tag(name = "Topic controller", description = "CRUD the topics and search a topic by a course")
@RestController
@RequestMapping("/api/v1/topics")
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

    @Operation(summary = "Create a topic", description = "Create a topic", parameters = @Parameter(schema = @Schema(contains = CreateTopic.class)),
            responses = {@ApiResponse(description = "User not found", responseCode = "404"),
                    @ApiResponse(description = "Course not found or Existing title or Existing message\"", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Creation of the successful topic", responseCode = "201",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseCreateTopic.class))
                    )
            }
    )
    @PostMapping
    public ResponseEntity<ResponseCreateTopic> create(@Valid @RequestBody CreateTopic createTopic) {

        UserEntity user = userService.existsById(createTopic.author_id());
        if (user == null) throw new NotFoundUser("Don't exists the user: " + createTopic.author_id());

        CourseEntity course = courseService.findById(createTopic.course_id());
        if (course == null) throw new BadParameterRequestException("Don't exists the course: " + createTopic.course_id());

        if (topicService.existsByTitle(createTopic.title())) throw new BadParameterRequestException("The content of the title already exists");

        if (topicService.existsByContent(createTopic.content())) throw new BadParameterRequestException("The content of the message already exists");

        ResponseCreateTopic topic = topicService.create(createTopic, user, course);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(topic.id()).toUri();

        return ResponseEntity.created(location).body(topic);
    }

    @Operation(summary = "List all topics", description = "List all topics",
            responses = @ApiResponse(description = "List all topics", responseCode = "200")
    )
    @GetMapping
    public ResponseEntity<List<ResponseTopic>> findAll() {

        List<ResponseTopic> topics = topicService.findAll();

        return ResponseEntity.ok(topics);
    }

    @Operation(summary = "Find a topic by identify",
            parameters = @Parameter(name = "id", description = "Topic identify", example = "34"),
            responses = {@ApiResponse(description = "Topic not found", responseCode = "404"),
                    @ApiResponse(description = "Detailed topic with answers", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = DetailedTopic.class)))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<DetailedTopic> findTopic(@PathVariable Long id) {

        DetailedTopic topic = topicService.findById(id);

        if (topic == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(topic);
    }

    @Operation(summary = "Search topics by course", description = "Search all topics by course identifier",
            parameters = @Parameter(name = "id", description = "Course identifier", example = "12"),
            responses = {@ApiResponse(description = "Course not found", responseCode = "400", content = @Content),
                    @ApiResponse(description = "List all topics", responseCode = "200",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TopicSearch.class)))
            }
    )
    @GetMapping("/search/{id}")
    public ResponseEntity<List<TopicSearch>> searchTopic(@PathVariable Long id) {

        if (!courseService.existsCourse(id)) throw new BadParameterRequestException("Don´t exist course");

        List<TopicSearch> topics = courseService.searchByCourse(id);

        return ResponseEntity.ok(topics);
    }

    @Operation(summary = "Update topic", description = "Update topic",
            parameters = @Parameter(name = "RequestUpdateTopic", schema = @Schema(contains = RequestUpdateTopic.class)),
            responses = {@ApiResponse(description = "Existing title or Existing message", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not found topic", responseCode = "404"),
                    @ApiResponse(description = "Updated topic returns", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseTopic.class)))
            }
    )
    @PutMapping
    public ResponseEntity<ResponseTopic> updateTopic(@Valid @RequestBody RequestUpdateTopic updateTopic) {

        if (topicService.existsByTitle(updateTopic.title())) throw new BadParameterRequestException("The content of the title already exists");

        if (topicService.existsByContent(updateTopic.content())) throw new BadParameterRequestException("The content of the message already exists");

        ResponseTopic topic = topicService.update(updateTopic);

        if (topic == null) throw new NotFoundTopic("The topic was not found with the topic number and user number provided");

        return ResponseEntity.ok(topic);
    }

    @Operation(summary = "Delete topic", description = "Delete topic", parameters = @Parameter(name = "id", description = "Topic identifier", example = "4"),
            responses = @ApiResponse(description = "The topic was deleted", responseCode = "204", content = @Content(mediaType = "NOT CONTENT"))
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable Long id) {

        topicService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
