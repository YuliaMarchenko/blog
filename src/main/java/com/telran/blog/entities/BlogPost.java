package com.telran.blog.entities;

import com.telran.blog.entities.type.Status;
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

    @OneToOne(mappedBy = "author")
    private BlogUser author;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToMany(mappedBy = "tag")
    private List<Tag> tags;

    @Column(name = "updated_on")
    private Instant updatedOn;

    @Column(name = "created_on")
    private Instant createdOn;
}
