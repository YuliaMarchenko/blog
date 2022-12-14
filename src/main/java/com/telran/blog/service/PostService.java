package com.telran.blog.service;

import com.telran.blog.dto.*;

import java.util.List;

public interface PostService {
    ResponsePostCreateDTO createPost(RequestPostCreateDTO postCreateDTO);
    List<ResponsePostGetDTO> getPosts();
    ResponsePostGetFullDTO getPost(Long id);

    ResponsePostGetFullDTO  putPostPublishedStatus(Long id);
    ResponsePostGetFullDTO  putPostUnpublishedStatus(Long id);

    ResponsePostGetFullDTO  putPostBlockStatus(Long id);

    List<ResponsePostGetDTO> searchPosts(RequestPostSearchDTO requestPostSearchDTO);

    void deletePost(Long id);

    List<ResponsePostGetDTO> searchPostsByUserName(String userName);
}
