package com.telran.blog.controller;

import com.telran.blog.dto.RequestPostCreateDTO;
import com.telran.blog.dto.ResponsePostCreateDTO;
import com.telran.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor

public class BlogPostController {

    private final PostService postService;

    @PostMapping("/posts/create")
    public ResponsePostCreateDTO createPost(@RequestBody RequestPostCreateDTO requestPostCreateDTO){
        return postService.createPost(requestPostCreateDTO);
    }
}
