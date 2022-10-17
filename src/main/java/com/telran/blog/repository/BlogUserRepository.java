package com.telran.blog.repository;

import com.telran.blog.entities.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogUserRepository extends JpaRepository<BlogUser, Long> {

    BlogUser findBlogUserByUserName(String userName);
}
