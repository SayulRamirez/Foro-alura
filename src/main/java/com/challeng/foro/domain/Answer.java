package com.challeng.foro.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record Answer(

        @Schema(description = "Response content", example = "This is the response to the topic, it should not exceed 2000 characters")
        String content,

        @Schema(description = "Response publication date")
        LocalDateTime answer_date,

        @Schema(description = "User name", example = "Juan Antonio")
        String author
) {


}
