package com.telran.blog.service.impl;

import com.telran.blog.aggregation.BlogPostCount;
import com.telran.blog.dto.ResponseAuthorByIdDTO;
import com.telran.blog.dto.ResponseAuthorGetDTO;
import com.telran.blog.entities.BlogUser;
import com.telran.blog.entities.converter.AuthorConverter;
import com.telran.blog.repository.BlogPostRepository;
import com.telran.blog.repository.BlogUserRepository;
import com.telran.blog.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor

public class AuthorServiceImpl implements AuthorService {

    private final BlogUserRepository blogUserRepository;

    private final BlogPostRepository blogPostRepository;
    @Override
    public List<ResponseAuthorGetDTO> getAuthors() {
        List<BlogPostCount> blogPostCount = blogPostRepository.blogsCountByAuthor();
        return blogUserRepository.findAll().stream()
                .map( author -> AuthorConverter.convertToGetDTO(author, findBlogPostCount(blogPostCount, author)))
                .toList();
    }

    @Override
    public ResponseAuthorByIdDTO getAuthor(Long id) {
        BlogUser author = blogUserRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return AuthorConverter.convertToGetByIdDTO(author);
    }

    private Long findBlogPostCount(List<BlogPostCount> blogPostCount, BlogUser author){
        for(BlogPostCount b: blogPostCount){
            if(b.getBlogUserId() == author.getId()){
                return b.getBlogsCount();
            }
        }
        return 0L;
    }
}
