package com.telran.blog.service;

import com.telran.blog.dto.RequestPostCreateDTO;
import com.telran.blog.dto.ResponsePostCreateDTO;
import com.telran.blog.dto.ResponsePostGetDTO;
import com.telran.blog.dto.ResponsePostGetFullDTO;

import java.util.List;

public interface PostService {
    ResponsePostCreateDTO createPost(RequestPostCreateDTO postCreateDTO);
    List<ResponsePostGetDTO> getPosts();
    ResponsePostGetFullDTO getPost(Long id);
}
