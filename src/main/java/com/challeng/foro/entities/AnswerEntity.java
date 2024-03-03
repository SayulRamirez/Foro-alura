package com.challeng.foro.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "answers")
public class AnswerEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000)
    private String content;

    @Column(name = "answer_date")
    private LocalDateTime answerDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "fk_author_answer"))
    private UserEntity author;

    private Boolean solution;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "topic_id", foreignKey = @ForeignKey(name = "fk_topic_answer"))
    private TopicEntity topic;

    public AnswerEntity(){}

    public AnswerEntity(Long id, String content, LocalDateTime answerDate, UserEntity author, Boolean solution, TopicEntity topic) {
        this.id = id;
        this.content = content;
        this.answerDate = answerDate;
        this.author = author;
        this.solution = solution;
        this.topic = topic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(LocalDateTime answerDate) {
        this.answerDate = answerDate;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public Boolean getSolution() {
        return solution;
    }

    public void setSolution(Boolean solution) {
        this.solution = solution;
    }

    public TopicEntity getTopic() {
        return topic;
    }

    public void setTopic(TopicEntity topic) {
        this.topic = topic;
    }
}
