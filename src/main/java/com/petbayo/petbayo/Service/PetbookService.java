package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Model.Book;
import java.util.List;

public interface PetbookService {

    List<Book> bookList();

    void bookCreate(Book item);

    Book bookItem(int petId);

    void bookUpdate(Book item);

    void bookDelete(int petId);
    List<Book> searchBooks(String keyword, String category);


}
