package com.petbayo.petbayo.Repository;

import com.petbayo.petbayo.Model.Book;
import java.util.List;

public interface PetbookRepository {

    List<Book> bookList();

    void bookCreate(Book item);

    Book bookItem(int petId);

    void bookUpdate(Book item);

    void bookDelete(int id);

}
