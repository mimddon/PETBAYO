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
        return sql.selectOne("user.login", user);
    }

    @Override
    public void register(User item) {
        sql.insert("user.register", item);
    }

    @Override
    public int checkEmail(String email) {
        return sql.selectOne("user.checkEmail", email);
    }

}
