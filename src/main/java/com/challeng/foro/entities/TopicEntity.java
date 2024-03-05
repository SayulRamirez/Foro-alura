package com.challeng.foro.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topics")
public class TopicEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true)
    private String title;

    @Column(length = 2000, unique = true)
    private String content;

    @Column(name = "publication_date")
    private LocalDateTime publicationDate;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_status_topic"))
    private StatusEntity status;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_author_topic"))
    private UserEntity author;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_course_topic"))
    private CourseEntity course;

    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY)
    private List<AnswerEntity> answers = new ArrayList<>();

    public TopicEntity(){}

    public TopicEntity(Long id, String title, String content, LocalDateTime publicationDate, StatusEntity status, UserEntity author, CourseEntity course) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.publicationDate = publicationDate;
        this.status = status;
        this.author = author;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return content;
    }

    public void setMessage(String message) {
        this.content = message;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    @PrePersist
    public void prePersist() {
        this.publicationDate = LocalDateTime.now();
        this.status = new StatusEntity();
        status.setId(1);
    }
}
