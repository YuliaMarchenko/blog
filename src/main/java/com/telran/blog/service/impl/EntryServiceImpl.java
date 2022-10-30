package com.telran.blog.service.impl;

import com.telran.blog.dto.*;
import com.telran.blog.entities.BlogUser;
import com.telran.blog.entities.BlogUserPassword;
import com.telran.blog.entities.BlogUserSession;
import com.telran.blog.repository.BlogUserPasswordRepository;
import com.telran.blog.repository.BlogUserRepository;
import com.telran.blog.repository.BlogUserSessionRepository;
import com.telran.blog.service.EntryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@AllArgsConstructor

public class EntryServiceImpl implements EntryService {

    private final BlogUserRepository blogUserRepository;
    private final BlogUserPasswordRepository blogUserPasswordRepository;

    private final BlogUserSessionRepository blogUserSessionRepository;

    public void generateAndSavePassword(BlogUser user, String password) {
        String salt = BCrypt.gensalt();
        String encryptedPassword = BCrypt.hashpw(password, salt);

        BlogUserPassword userPassword = BlogUserPassword.builder()
                .user(user)
                .salt(salt)
                .password(encryptedPassword)
                .build();

        blogUserPasswordRepository.save(userPassword);
    }

    public BlogUser getMatchedBlogUser(String userName, String password) {
        BlogUser blogUser = blogUserRepository.findBlogUserByUserName(userName);

        if (blogUser == null) {
            return null;
        }

       BlogUserPassword blogUserPassword = blogUserPasswordRepository.findBlogUserPasswordByUser(blogUser);

        var actualPasswordHash = BCrypt.hashpw(password, blogUserPassword.getSalt());

        return actualPasswordHash.equals(blogUserPassword.getPassword()) ? blogUser : null;
    }

    @Override
    public ResponseRegistrationDTO registration(RequestRegistrationDTO requestRegistrationDTO) {

        BlogUser blogUser = BlogUser.builder()
                .firstName(requestRegistrationDTO.getFirstName())
                .lastName(requestRegistrationDTO.getLastName())
                .userName(requestRegistrationDTO.getUserName())
                .build();

        blogUser = blogUserRepository.save(blogUser);

        generateAndSavePassword(blogUser, requestRegistrationDTO.getPassword());

        return ResponseRegistrationDTO.builder()
                .id(blogUser.getId())
                .build();
    }

    @Override
    public ResponseLoginDTO login(RequestLoginDTO requestLoginDTO) {

        BlogUser blogUser = getMatchedBlogUser(requestLoginDTO.getUserName(), requestLoginDTO.getPassword());
        if (blogUser == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        BlogUserSession blogUserSession = BlogUserSession.builder()
                .blogUser(blogUser)
                .sessionId(UUID.randomUUID().toString())
                .build();

        blogUserSessionRepository.save(blogUserSession);

        return ResponseLoginDTO.builder()
                .sessionId(blogUserSession.getSessionId())
                .build();
    }

    @Override
    public void logout(BlogUserSession blogUserSession) {
        blogUserSessionRepository.delete(blogUserSession);
    }

    @Override
    public void updatePassword(RequestUpdatePasswordDTO requestUpdatePasswordDTO) {
        BlogUser blogUser = getMatchedBlogUser(requestUpdatePasswordDTO.getUserName(), requestUpdatePasswordDTO.getOldPassword());
        if (blogUser == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        BlogUserPassword blogUserPassword = blogUserPasswordRepository.findBlogUserPasswordByUser(blogUser);

        String salt = BCrypt.gensalt();
        String encryptedPassword = BCrypt.hashpw(requestUpdatePasswordDTO.getNewPassword(), salt);

        blogUserPassword.setSalt(salt);
        blogUserPassword.setPassword(encryptedPassword);
        blogUserPasswordRepository.save(blogUserPassword);
    }
}
