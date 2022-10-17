package com.telran.blog.dto;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RequestLoginDTO {

    private String userName;
    private String password;
}
