package com.telran.blog.service;

import com.telran.blog.dto.RequestLoginDTO;
import com.telran.blog.dto.RequestRegistrationDTO;
import com.telran.blog.dto.ResponseLoginDTO;
import com.telran.blog.dto.ResponseRegistrationDTO;
import com.telran.blog.entities.BlogUserSession;

public interface EntryService {

    ResponseRegistrationDTO registration(RequestRegistrationDTO requestRegistrationDTO);
    ResponseLoginDTO login(RequestLoginDTO requestLoginDTO);

    void logout(BlogUserSession blogUserSession);
}
