package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Model.User;

public interface UserService {

    boolean login(User user);

    void register(User item);

    boolean checkEmail(String email);
}
