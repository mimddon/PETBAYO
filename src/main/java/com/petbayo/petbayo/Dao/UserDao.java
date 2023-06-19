package com.petbayo.petbayo.Dao;

import com.petbayo.petbayo.Model.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserDao {

    User login(User user);

    boolean register(User item);

    int checkEmail(String email);

    int checkNickname(String nickname);

    User findOne(int userId);

    void updateProfile(User user);

    void uploadIntro(User user, String intro);
}
