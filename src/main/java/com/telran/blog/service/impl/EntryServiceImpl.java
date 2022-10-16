package com.telran.blog.service.impl;

import com.telran.blog.dto.RequestRegistrationDTO;
import com.telran.blog.dto.ResponseRegistrationDTO;
import com.telran.blog.entities.BlogUser;
import com.telran.blog.entities.BlogUserPassword;
import com.telran.blog.repository.BlogUserPasswordRepository;
import com.telran.blog.repository.BlogUserRepository;
import com.telran.blog.service.EntryService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class EntryServiceImpl implements EntryService {

    private final BlogUserRepository blogUserRepository;
    private final BlogUserPasswordRepository blogUserPasswordRepository;

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
}
