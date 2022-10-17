package com.telran.blog.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ResponsePostGetDTO {

    private Long blogId;
    private String blogTitle;
}
