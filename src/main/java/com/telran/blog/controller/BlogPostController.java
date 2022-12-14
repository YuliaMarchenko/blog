package com.telran.blog.controller;

import com.telran.blog.dto.*;
import com.telran.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor

public class BlogPostController {

    private final PostService postService;

    @PostMapping("/posts/create")
    public ResponsePostCreateDTO createPost(@RequestBody @Valid RequestPostCreateDTO requestPostCreateDTO){
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

    @PutMapping("/posts/{id}/block")
    public ResponsePostGetFullDTO putPostBlockStatus(@PathVariable("id") Long id){
        return postService.putPostBlockStatus(id);
    }

    @PostMapping("/posts/search")
    public List<ResponsePostGetDTO> searchPosts(@RequestBody RequestPostSearchDTO requestPostSearchDTO){
        return postService.searchPosts(requestPostSearchDTO);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable("id") Long id){
        postService.deletePost(id);
    }

    @GetMapping("/posts/user/{name}")
    public List<ResponsePostGetDTO> searchPostsByUserName(@PathVariable("name") String userName){
        return postService.searchPostsByUserName(userName);
    }
}
