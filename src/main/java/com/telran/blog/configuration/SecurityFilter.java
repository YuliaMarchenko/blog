package com.telran.blog.configuration;

import com.telran.blog.entities.BlogUserSession;
import com.telran.blog.repository.BlogUserSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
@AllArgsConstructor

public class SecurityFilter extends OncePerRequestFilter {

    private final BlogUserSessionRepository blogUserSessionRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header == null) {
            filterChain.doFilter(request, response);
            return;
        }

        BlogUserSession accountSession = blogUserSessionRepository.findBySessionId(header);
        if (accountSession == null) {
            filterChain.doFilter(request, response);
            return;
        }

        Authentication key = new UsernamePasswordAuthenticationToken(
                accountSession,
                null,
                new ArrayList<>()
        );

        SecurityContextHolder
                .getContext()
                .setAuthentication(key);

        filterChain.doFilter(request, response);
    }
}
