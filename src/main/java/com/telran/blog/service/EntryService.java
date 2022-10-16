package com.telran.blog.service;

import com.telran.blog.dto.RequestRegistrationDTO;
import com.telran.blog.dto.ResponseRegistrationDTO;

public interface EntryService {

    ResponseRegistrationDTO registration(RequestRegistrationDTO requestRegistrationDTO);
}
