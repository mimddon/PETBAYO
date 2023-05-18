package com.petbayo.petbayo.Dao;

import com.petbayo.petbayo.Model.User;

public interface UserDao {

    User login(User user);

    void register(User item);

    int checkEmail(String email);
}
