package com.petbayo.petbayo.Repository;

import com.petbayo.petbayo.Model.Book;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Book> searchBooks(String keyword, String category) {
        // 검색 쿼리를 실행하고 결과를 반환합니다.
        // 예제에서는 MyBatis를 사용하므로, 적절한 SQL 매퍼 파일에 쿼리를 작성하고 실행합니다.
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("keyword", keyword);
        parameters.put("category", category);
        return sql.selectList("book.searchBooks", parameters);
    }

}
