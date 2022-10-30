package com.telran.blog.controller;

import com.telran.blog.dto.*;
import com.telran.blog.entities.BlogUserSession;
import com.telran.blog.service.EntryService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping("/entry/login")
    ResponseLoginDTO login(@RequestBody RequestLoginDTO requestLoginDTO){
        return entryService.login(requestLoginDTO);
    }

    @PostMapping("/entry/logout")
    public void logout(@AuthenticationPrincipal BlogUserSession blogUserSession){
        entryService.logout(blogUserSession);
    }

    @PutMapping("/entry/update-password")
    public void updatePassword(@RequestBody RequestUpdatePasswordDTO requestUpdatePasswordDTO){
        entryService.updatePassword(requestUpdatePasswordDTO);
    }
}
