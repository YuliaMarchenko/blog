package com.telran.blog.repository;

import com.telran.blog.aggregation.BlogPostCount;
import com.telran.blog.entities.BlogPost;
import com.telran.blog.entities.BlogUser;
import com.telran.blog.entities.type.BlogStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    @Query("SELECT new com.telran.blog.aggregation.BlogPostCount(p.author.id, COUNT(p.author.id)) FROM BlogPost AS p WHERE p.status=com.telran.blog.entities.type.BlogStatus.PUBLISHED GROUP BY p.author")
    List<BlogPostCount> blogsCountByAuthor();

    List<BlogPost> findByTitle(String title);

    List<BlogPost> findBlogPostByStatusOrderByCreatedOnDesc(BlogStatus status);

    List<BlogPost> findBlogPostByAuthorAndStatus(BlogUser user, BlogStatus status);
}
