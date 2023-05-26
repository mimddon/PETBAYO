package com.petbayo.petbayo.Dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.petbayo.petbayo.Model.User;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    SqlSession sql;

    @Override
    public User login(User user) {
        return sql.selectOne("users.login", user);
    }

    @Override
    public boolean register(User item) {
        int affectedRows = sql.insert("users.register", item);
        return affectedRows > 0;  // 영향을 받은 행의 수가 0보다 크면 true 반환, 그렇지 않으면 false 반환
    }

    @Override
    public int checkEmail(String email) {
        return sql.selectOne("users.checkEmail", email);
    }

    @Override
    public int checkNickname(String nickname) {
        return sql.selectOne("users.checkNickname", nickname);
    }

}
