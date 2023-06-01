package com.petbayo.petbayo.Model;

import lombok.Data;

@Data
public class User {
    private int userId;
    private String email;
    private String pwd;
    private String nickname;
    private String birth;
    private int gender;
}
