package com.telran.blog.entities;

import com.telran.blog.entities.type.BlogStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "blog_post")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class BlogPost extends AbstractEntity<Long>{

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

    @Column(name = "status")
    private BlogStatus status;

    @ManyToMany
    private List<Tag> tags;
}
