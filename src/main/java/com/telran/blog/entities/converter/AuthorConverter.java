package com.telran.blog.entities.converter;

import com.telran.blog.dto.ResponseAuthorByIdDTO;
import com.telran.blog.dto.ResponseAuthorGetDTO;
import com.telran.blog.dto.ResponsePostGetDTO;
import com.telran.blog.entities.BlogPost;
import com.telran.blog.entities.BlogUser;
import com.telran.blog.entities.type.BlogStatus;

import java.util.List;

public class AuthorConverter {

    public static ResponseAuthorGetDTO convertToGetDTO(BlogUser user, Long blogsCount){
        return ResponseAuthorGetDTO.builder()
                .authorId(user.getId())
                .authorFirstName(user.getFirstName())
                .authorLastName(user.getLastName())
                .authorUserName(user.getUserName())
                .blogsCount(blogsCount)
                .build();
    }

    public static ResponseAuthorByIdDTO convertToGetByIdDTO(BlogUser author){
        return ResponseAuthorByIdDTO.builder()
                .authorId(author.getId())
                .authorFirstName(author.getFirstName())
                .authorLastName(author.getLastName())
                .authorUserName(author.getUserName())
                .blogs(author.getPosts()
                        .stream()
                        .filter(post -> post.getStatus() == BlogStatus.PUBLISHED)
                        .map(post -> ResponsePostGetDTO.builder()
                                .blogId(post.getId())
                                .blogTitle(post.getTitle())
                                .build())
                        .toList())
                .build();
    }
}
