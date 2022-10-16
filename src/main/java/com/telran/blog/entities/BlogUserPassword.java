package com.telran.blog.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "blog_user_password")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class BlogUserPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "salt")
    private String salt;

    @OneToOne
    @JoinColumn(name = "user_id")
    private BlogUser user;
}
