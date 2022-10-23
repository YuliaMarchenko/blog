package com.telran.blog.controller;

import com.telran.blog.dto.RequestPostCreateDTO;
import com.telran.blog.dto.ResponsePostCreateDTO;
import com.telran.blog.dto.ResponsePostGetDTO;
import com.telran.blog.dto.ResponsePostGetFullDTO;
import com.telran.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

public class BlogPostController {

    private final PostService postService;

    @PostMapping("/posts/create")
    public ResponsePostCreateDTO createPost(@RequestBody RequestPostCreateDTO requestPostCreateDTO){
        return postService.createPost(requestPostCreateDTO);
    }

    @GetMapping("/posts")
    public List<ResponsePostGetDTO> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/posts/{id}")
    public ResponsePostGetFullDTO getPost(@PathVariable("id") Long id){
        return postService.getPost(id);
    }

    @PutMapping("/posts/{id}/publish")
    public ResponsePostGetFullDTO putPostPublishedStatus(@PathVariable("id") Long id){
        return postService.putPostPublishedStatus(id);
    }

    @PutMapping("/posts/{id}/unpublish")
    public ResponsePostGetFullDTO putPostUnpublishedStatus(@PathVariable("id") Long id){
        return postService.putPostUnpublishedStatus(id);
    }
}
