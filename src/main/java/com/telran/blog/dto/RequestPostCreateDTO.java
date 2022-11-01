package com.telran.blog.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RequestPostCreateDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String body;

    private List<String> tags;

    @NotNull
    private Long authorId;
}
