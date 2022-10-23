package com.telran.blog.service.impl;

import com.telran.blog.dto.RequestPostCreateDTO;
import com.telran.blog.dto.ResponsePostCreateDTO;
import com.telran.blog.dto.ResponsePostGetDTO;
import com.telran.blog.dto.ResponsePostGetFullDTO;
import com.telran.blog.entities.BlogPost;
import com.telran.blog.entities.BlogUser;
import com.telran.blog.entities.Tag;
import com.telran.blog.entities.converter.PostConverter;
import com.telran.blog.entities.type.BlogStatus;
import com.telran.blog.repository.BlogPostRepository;
import com.telran.blog.repository.BlogUserRepository;
import com.telran.blog.repository.TagRepository;
import com.telran.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor

public class PostServiceImpl implements PostService {

    private final BlogPostRepository postRepository;
    private final BlogUserRepository blogUserRepository;

    private final TagRepository tagRepository;

    @Override
    public ResponsePostCreateDTO createPost(RequestPostCreateDTO requestPostCreateDTO) {

        List<Tag> tags = requestPostCreateDTO.getTags().stream()
                .map(tagName -> findOrCreateTag(tagName))
                .toList();

        BlogUser author = blogUserRepository.findById(requestPostCreateDTO.getAuthorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        BlogPost blogPost  = BlogPost.builder()
                .title(requestPostCreateDTO.getTitle())
                .body(requestPostCreateDTO.getBody())
                .tags(tags)
                .status(BlogStatus.UNPUBLISHED)
                .author(author)
                .build();

        blogPost.setCreatedOn(Instant.now());
        blogPost.setUpdatedOn(Instant.now());

        blogPost  = postRepository.save(blogPost);

        return ResponsePostCreateDTO.builder()
                .blogId(blogPost.getId())
                .title(blogPost.getTitle())
                .body(blogPost.getBody())
                .tags(blogPost.getTags().stream().map(tag -> tag.getName()).toList())
                .status(blogPost.getStatus())
                .authorId(author.getId())
                .build();
    }

    @Override
    public List<ResponsePostGetDTO> getPosts() {
        return postRepository.findAll().stream()
                .map(blogPost -> PostConverter.convertToPostGetDTO(blogPost)).toList();
    }

    @Override
    public ResponsePostGetFullDTO getPost(Long id) {
        BlogPost post = postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return PostConverter.convertToPostGetFullDTO(post);
    }

    @Override
    public ResponsePostGetFullDTO putPostPublishedStatus(Long id) {
        BlogPost post = postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        post.setStatus(BlogStatus.PUBLISHED);
        post = postRepository.save(post);
        return PostConverter.convertToPostGetFullDTO(post);
    }

    @Override
    public ResponsePostGetFullDTO putPostUnpublishedStatus(Long id) {
        BlogPost post = postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        post.setStatus(BlogStatus.UNPUBLISHED);
        post = postRepository.save(post);
        return PostConverter.convertToPostGetFullDTO(post);
    }

    private Tag findOrCreateTag(String str){
        Tag tag = tagRepository.findByName(str);
        if (tag == null){
            tag = Tag.builder()
                    .name(str)
                    .build();
            tag = tagRepository.save(tag);
        }
        return tag;
    }
}
