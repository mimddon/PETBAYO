package com.petbayo.petbayo.Repository;

import com.petbayo.petbayo.Model.Book;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetbookRepositoryImpl implements PetbookRepository {

    @Autowired
    SqlSession sql;

    @Override
    public List<Book> bookList() {
        return sql.selectList("book.bookList");
    }

    @Override
    public void bookCreate(Book item) {
        sql.insert("book.create", item);
    }

    @Override
    public Book bookItem(int petId) {
        return sql.selectOne("book.item", petId);
    }

    @Override
    public void bookUpdate(Book item) {
        sql.update("book.update", item);
    }

    @Override
    public void bookDelete(int petId) {

        sql.delete("book.delete", petId);
    }
}
