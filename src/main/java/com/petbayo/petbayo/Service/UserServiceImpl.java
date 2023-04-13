package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.User.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService{

    @Autowired //;

    @Override
    public User findUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserRowMapper());
        return user;

    @Override
        public User findUserByEmail (String email){
            // 이메일로 사용자를 찾아서 User 객체를 반환하는 코드를 작성합니다.
            User user = new User();
            user.setEmail(email);
            user.setPassword("password");
            return user;
        }
    }
}
