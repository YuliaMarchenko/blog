package com.telran.blog.service.impl;

import com.telran.blog.dto.*;
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

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor

public class PostServiceImpl implements PostService {

    private final BlogPostRepository postRepository;
    private final BlogUserRepository blogUserRepository;
    private final TagRepository tagRepository;

    private final EntityManager entityManager;

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
        return postRepository.findBlogPostByStatusOrderByCreatedOnDesc(BlogStatus.PUBLISHED).stream()
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
        BlogPost post = postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "post doesn't exist"));
        post.setStatus(BlogStatus.UNPUBLISHED);
        post = postRepository.save(post);
        return PostConverter.convertToPostGetFullDTO(post);
    }

    @Override
    public ResponsePostGetFullDTO putPostBlockStatus(Long id) {
        BlogPost post = postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        switch (post.getStatus()){
            case BLOCKED:
                post.setStatus(BlogStatus.PUBLISHED);
                break;
            default: post.setStatus(BlogStatus.BLOCKED);
        }
        post = postRepository.save(post);
        return PostConverter.convertToPostGetFullDTO(post);
    }

    @Override
    public List<ResponsePostGetDTO> searchPosts(RequestPostSearchDTO requestPostSearchDTO) {

        Map<String, Object> searchParams = new HashMap<>();
        StringBuilder query = new StringBuilder();

        query.append("from BlogPost bp where 1 = 1 ");

        if(requestPostSearchDTO.getFirstName() != null){
            query.append("and bp.author.firstName = :firstName ");
            searchParams.put("firstName", requestPostSearchDTO.getFirstName());
        }

        if(requestPostSearchDTO.getLastName() != null){
            query.append("and bp.author.lastName = :lastName ");
            searchParams.put("lastName", requestPostSearchDTO.getLastName());
        }

        if(requestPostSearchDTO.getTitle() != null){
            query.append("and bp.title = :title ");
            searchParams.put("title", requestPostSearchDTO.getTitle());
        }

        if(requestPostSearchDTO.getTags() != null && requestPostSearchDTO.getTags().size() > 0){
            query.append("and exists (select 1 from bp.tags t where t.name in (:tags)) ");
            searchParams.put("tags", requestPostSearchDTO.getTags());
        }

        Query emQuery = entityManager.createQuery(query.toString());
        searchParams.forEach(emQuery :: setParameter);
        return emQuery.getResultList().stream().map(blog -> PostConverter.convertToPostGetDTO((BlogPost) blog)).toList();

    }


    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<ResponsePostGetDTO> searchPostsByUserName(String userName) {
        BlogUser user = blogUserRepository.findBlogUserByUserName(userName);
        if (user == null) {
            new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<BlogPost> blogs = postRepository.findBlogPostByAuthorAndStatus(user, BlogStatus.PUBLISHED);

        return blogs.stream().map(blog -> PostConverter.convertToPostGetDTO(blog)).toList();
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
