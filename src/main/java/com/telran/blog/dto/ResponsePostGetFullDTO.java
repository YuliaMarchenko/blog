package com.telran.blog.dto;

import com.telran.blog.entities.BlogUser;
import com.telran.blog.entities.Tag;
import com.telran.blog.entities.type.BlogStatus;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ResponsePostGetFullDTO {

    private Long id;
    private String title;
    private String body;
    private List<ResponseTagDTO> tags;
    private BlogUser author;
    private BlogStatus status;
    private Instant updatedDate;
    private Instant createdDate;

}
