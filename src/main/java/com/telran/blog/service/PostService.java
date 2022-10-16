package com.telran.blog.service;

import com.telran.blog.dto.RequestPostCreateDTO;
import com.telran.blog.dto.ResponsePostCreateDTO;

public interface PostService {

    ResponsePostCreateDTO createPost(RequestPostCreateDTO postCreateDTO);
}
