package com.petbayo.petbayo.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.petbayo.petbayo.Dao.UserDao;
import com.petbayo.petbayo.Model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    UserDao dao;

    @Override
    public boolean login(User user) {
        User item = dao.login(user);

        if(item != null) {

            user.setUserId(item.getUserId());
            user.setEmail(item.getEmail());
            user.setPwd(item.getPwd());
            user.setNickname(item.getNickname());
            user.setBirth(item.getBirth());
            user.setGender(item.getGender());

            return true;
        } else
            return false;
    }

    @Override
    public boolean register(User item) {
        return dao.register(item);
    }

    @Override
    public boolean checkEmail(String email) {
        String count = Integer.toString(dao.checkEmail(email));
        if(count.equals("0")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkNickname(String nickname) {
        String count = Integer.toString(dao.checkNickname(nickname));
        if(count.equals("0")) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/mailCheck")
    @ResponseBody
    public String mailCheck(String email) {
        System.out.println("이메일 인증 요청이 들어옴!");
        System.out.println("이메일 인증 이메일: " + email);
        return "Success";
    }


}
