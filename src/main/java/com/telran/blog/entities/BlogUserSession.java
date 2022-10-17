package com.telran.blog.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "blog_user_session")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class BlogUserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "session_id", unique = true, nullable = false)
    private String sessionId;

    @JoinColumn(name = "user_id", nullable = false)
    @OneToOne
    private BlogUser blogUser;
}
