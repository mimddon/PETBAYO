package com.petbayo.petbayo.Dao;

import com.petbayo.petbayo.Model.Pet;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetDaoImpl implements PetDao {

    @Autowired
    SqlSession sql;

    @Override
    public List<Pet> petList(int userId) {
        return sql.selectList("petList", userId);
    }

    @Override
    public void petCreate(Pet item) {
        sql.insert("petCreate", item);
    }

    @Override
    public void petDelete(int petId) {

    }

    @Override
    public Pet findOne(int petId) {
        return sql.selectOne("petInfo", petId);
    }
}
