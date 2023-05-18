package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Model.Book;
import com.petbayo.petbayo.Model.Care;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PetbookService {

    List<Book> bookList();

    void bookCreate(Book item);

    Book bookItem(int petId);

    void bookUpdate(Book item);

    void bookDelete(int id);
    List<Book> searchBooks(String keyword, String category);

}
