package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Model.Book;
import java.util.List;

public interface PetbookService {

    List<Book> bookList();

    Long bookCreate(Book item);

    Book bookItem(Long petId);

    void bookUpdate(Book item);

    void bookDelete(int id);
    List<Book> searchBooks(String keyword, String category);


}
