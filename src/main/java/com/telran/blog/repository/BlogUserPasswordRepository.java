package com.telran.blog.repository;

import com.telran.blog.entities.BlogUserPassword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogUserPasswordRepository extends JpaRepository<BlogUserPassword, Long> {
}
