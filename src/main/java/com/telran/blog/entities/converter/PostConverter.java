package com.telran.blog.entities.converter;

import com.telran.blog.dto.ResponsePostGetDTO;
import com.telran.blog.entities.BlogPost;

public class PostConverter {

    public static ResponsePostGetDTO convertToPostGetDTO(BlogPost blogPost){
        return ResponsePostGetDTO.builder()
                .blogId(blogPost.getId())
                .blogTitle(blogPost.getTitle())
                .build();
    }
}
