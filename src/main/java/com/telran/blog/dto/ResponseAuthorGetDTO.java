package com.telran.blog.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ResponseAuthorGetDTO {

    private Long authorId;
    private String authorFirstName;
    private String authorLastName;
    private String authorUserName;
    private Long blogsCount;
}
