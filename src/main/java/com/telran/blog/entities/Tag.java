package com.telran.blog.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tag")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Tag{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
