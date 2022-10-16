package com.telran.blog.controller;

import com.telran.blog.dto.RequestRegistrationDTO;
import com.telran.blog.dto.ResponseRegistrationDTO;
import com.telran.blog.service.EntryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor

public class EntryController {

    private final EntryService entryService;

    @PostMapping("/entry/registration")
    ResponseRegistrationDTO registration(@RequestBody RequestRegistrationDTO requestRegistrationDTO){
        return entryService.registration(requestRegistrationDTO);
    }
}
