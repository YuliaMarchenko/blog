package com.telran.blog.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestPostSearchDTO {
    private String title;
    private String firstName;
    private String lastName;
    private List<String> tags;
}

