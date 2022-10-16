package com.telran.blog.dto;


import com.telran.blog.entities.Tag;
import com.telran.blog.entities.type.BlogStatus;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ResponsePostCreateDTO {

    private Long blogId;
    private String title;
    private String body;
    private List<Tag> tags;
    private BlogStatus status;
    private Long authorId;
}
