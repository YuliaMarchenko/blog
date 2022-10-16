package com.telran.blog.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RequestRegistrationDTO {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
}
