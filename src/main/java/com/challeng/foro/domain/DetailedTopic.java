package com.challeng.foro.domain;

import java.time.LocalDateTime;
import java.util.List;

public record DetailedTopic(

        Long id,
        String title,
        String content,
        LocalDateTime publicationDate,
        String status,
        Author author,
        Course course,
        List<Answer> answer
) {
}
