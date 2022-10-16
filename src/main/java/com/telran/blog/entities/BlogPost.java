package com.telran.blog.entities;

import com.telran.blog.entities.type.BlogStatus;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "blog_post")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private BlogUser author;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BlogStatus status;

    @ManyToMany
    private List<Tag> tags;

    @Column(name = "updated_on")
    private Instant updatedOn;

    @Column(name = "created_on")
    private Instant createdOn;
}
