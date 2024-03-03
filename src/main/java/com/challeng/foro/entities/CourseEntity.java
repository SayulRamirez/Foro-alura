package com.challeng.foro.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class CourseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_category_course"))
    private CategoryEntity category;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<TopicEntity> topics = new ArrayList<>();
    public CourseEntity(){}

    public CourseEntity(Long id, String name, CategoryEntity category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public List<TopicEntity> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicEntity> topics) {
        this.topics = topics;
    }
}
