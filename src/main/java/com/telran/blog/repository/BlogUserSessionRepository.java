package com.telran.blog.repository;

import com.telran.blog.entities.BlogUserSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogUserSessionRepository extends JpaRepository<BlogUserSession, Long> {
    BlogUserSession findBySessionId(String sessionId);
}
