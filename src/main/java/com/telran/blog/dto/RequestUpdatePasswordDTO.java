package com.telran.blog.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RequestUpdatePasswordDTO {

    private String userName;
    private String oldPassword;
    private String newPassword;
}
