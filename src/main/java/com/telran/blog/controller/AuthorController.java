package com.telran.blog.controller;

import com.telran.blog.dto.ResponseAuthorByIdDTO;
import com.telran.blog.dto.ResponseAuthorGetDTO;
import com.telran.blog.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor

public class AuthorController {

    AuthorService authorService;

    @GetMapping("/authors")
    public List<ResponseAuthorGetDTO> getAuthors(){
        return authorService.getAuthors();
    }

    @GetMapping("/authors/{id}")
    public ResponseAuthorByIdDTO getAuthor(@PathVariable("id") Long id){
        return authorService.getAuthor(id);
    }
}
