package com.telran.blog.service;

import com.telran.blog.dto.ResponseAuthorByIdDTO;
import com.telran.blog.dto.ResponseAuthorGetDTO;

import java.util.List;

public interface AuthorService {
    List<ResponseAuthorGetDTO> getAuthors();
    ResponseAuthorByIdDTO getAuthor(Long id);
}

