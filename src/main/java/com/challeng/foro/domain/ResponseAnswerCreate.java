package com.challeng.foro.domain;

import java.time.LocalDateTime;

public record ResponseAnswerCreate(

        Long answer_id,
        String content,
        LocalDateTime answer_date,
        Author author,
        Topic topic

) {
}
