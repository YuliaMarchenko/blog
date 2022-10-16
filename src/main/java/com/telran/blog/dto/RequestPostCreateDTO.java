package com.telran.blog.dto;

import com.telran.blog.entities.Tag;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RequestPostCreateDTO {

    private String title;
    private String body;
    private List<Tag> tags;
    private Long authorId;
}
