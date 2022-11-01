package com.telran.blog.entities;

import com.telran.blog.entities.type.AccountStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "blog_user")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class BlogUser extends AbstractEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status")
    private AccountStatus accountStatus;

    @OneToMany(mappedBy = "author")
    private List<BlogPost> posts;
 }
