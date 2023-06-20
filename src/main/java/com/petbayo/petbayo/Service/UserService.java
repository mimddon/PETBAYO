package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    boolean login(User user);

    boolean register(User item);

    boolean checkEmail(String email);

    boolean checkNickname(String nickname);

    User findOne(int userId);

    User findByEmail(String email);

    void uploadProfile(User user, MultipartFile file) throws IOException;

    void uploadIntro(User user, String intro);
}
