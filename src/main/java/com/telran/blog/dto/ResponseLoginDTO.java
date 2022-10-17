package com.telran.blog.dto;

import com.telran.blog.entities.BlogUserSession;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ResponseLoginDTO {

    String sessionId;
}
