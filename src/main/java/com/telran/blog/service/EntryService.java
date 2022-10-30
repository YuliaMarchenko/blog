package com.telran.blog.service;

import com.telran.blog.dto.*;
import com.telran.blog.entities.BlogUserSession;

public interface EntryService {

    ResponseRegistrationDTO registration(RequestRegistrationDTO requestRegistrationDTO);
    ResponseLoginDTO login(RequestLoginDTO requestLoginDTO);

    void logout(BlogUserSession blogUserSession);

    void updatePassword(RequestUpdatePasswordDTO requestUpdatePasswordDTO);
}
