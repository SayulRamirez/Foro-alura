package com.challeng.foro.domain;

import java.time.LocalDateTime;

public record ResponseCreateTopic(
        Long id,
        String title,
        String content,
        LocalDateTime publicationDate,
        Author author,
        CourseR course
) {
}
