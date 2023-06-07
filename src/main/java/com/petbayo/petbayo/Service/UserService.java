package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Model.User;

public interface UserService {

    boolean login(User user);

    boolean register(User item);

    boolean checkEmail(String email);

    boolean checkNickname(String nickname);

    User findOne(final String nickName);
}
