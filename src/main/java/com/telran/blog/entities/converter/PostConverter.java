package com.telran.blog.entities.converter;

import com.telran.blog.dto.ResponsePostGetDTO;
import com.telran.blog.dto.ResponsePostGetFullDTO;
import com.telran.blog.dto.ResponseTagDTO;
import com.telran.blog.entities.BlogPost;
import com.telran.blog.entities.Tag;

import java.time.Instant;
import java.util.List;

public class PostConverter {

    public static ResponsePostGetDTO convertToPostGetDTO(BlogPost blogPost){
        return ResponsePostGetDTO.builder()
                .blogId(blogPost.getId())
                .blogTitle(blogPost.getTitle())
                .build();
    }

    public static ResponsePostGetFullDTO convertToPostGetFullDTO(BlogPost blogPost){

        return ResponsePostGetFullDTO.builder()
                .id(blogPost.getId())
                .title(blogPost.getTitle())
                .body(blogPost.getBody())
                .tags(blogPost.getTags() == null ? null : blogPost.getTags()
                        .stream()
                        .map(tag -> ResponseTagDTO.builder()
                                .id(tag.getId())
                                .name(tag.getName())
                                .build()).toList())
                .authorId(blogPost.getAuthor().getId())
                .status(blogPost.getStatus())
                .updatedDate(blogPost.getUpdatedOn())
                .createdDate(blogPost.getCreatedOn())
                .build();
    }
}
