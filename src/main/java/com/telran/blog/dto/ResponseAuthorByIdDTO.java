package com.telran.blog.dto;

import com.telran.blog.entities.BlogPost;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ResponseAuthorByIdDTO {

    private Long authorId;
    private String authorFirstName;
    private String authorLastName;
    private String authorUserName;
    private List<ResponsePostGetDTO> blogs;
}
