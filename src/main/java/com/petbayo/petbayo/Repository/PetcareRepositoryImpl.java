package com.petbayo.petbayo.Repository;

import com.petbayo.petbayo.Model.Care;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.util.List;

@Repository
public class PetcareRepositoryImpl implements PetcareRepository {

    @Autowired
    SqlSession sql;

    @Override
    public List<Care> careList() {
        return sql.selectList("care.careList");
    }

    @Override
    public void careCreate(Care item) {
        sql.insert("care.create", item);
    }

    @Override
    public Care careItem(int textId) {
        return sql.selectOne("care.item", textId);
    }

    @Override

    public void careUpdate(Care item) {
        sql.update("care.update", item);
    }

    @Override
    public void careDelete(int textId) {
        sql.delete("care.delete", textId);
    }
}
