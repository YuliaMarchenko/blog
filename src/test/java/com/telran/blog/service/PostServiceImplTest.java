package com.telran.blog.service;

import com.telran.blog.dto.ResponsePostGetFullDTO;
import com.telran.blog.entities.BlogPost;
import com.telran.blog.entities.BlogUser;
import com.telran.blog.repository.BlogPostRepository;
import com.telran.blog.repository.BlogUserRepository;
import com.telran.blog.service.impl.PostServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)

public class PostServiceImplTest {

    @Mock
    private BlogPostRepository blogPostRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @Nested
    @DisplayName("getPost()")
    class getPost{
        @Test
        @DisplayName("should return blog post with same id")
        void shouldReturnPostWithId(){
            Long expectedId = 12345L;
            BlogUser author = BlogUser.builder().id(1L).userName("Vasia").build();
            BlogPost expectedPost = BlogPost.builder().id(expectedId).author(author).build();

            Mockito
                    .when(blogPostRepository.findById(expectedId))
                    .thenReturn(Optional.ofNullable(expectedPost));

            ResponsePostGetFullDTO result = postService.getPost(expectedId);

            Assertions.assertEquals(expectedId, result.getId());
        }

        @Test
        @DisplayName("should return error, when blog post doesn't found")
        void shouldReturnErrorWhenNoSuchPost(){
            HttpStatus expectedErrorStatus = HttpStatus.NOT_FOUND;

            Mockito
                    .when(blogPostRepository.findById(ArgumentMatchers.anyLong()))
                    .thenReturn(Optional.ofNullable(null));

            var exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
                postService.getPost(12L);
            });

            Assertions.assertEquals(expectedErrorStatus, exception.getStatus());
        }
    }
}
