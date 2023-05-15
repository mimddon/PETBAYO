package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Model.Book;
import com.petbayo.petbayo.Repository.PetbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetbookServiceImpl implements PetbookService{
    @Autowired
    PetbookRepository petbookRepository;

    @Override
    public List<Book> bookList() {
        return petbookRepository.bookList();
    }

    @Override
    public void bookCreate(Book item) {
        petbookRepository.bookCreate(item);
    }

    @Override
    public Book bookItem(int petId) {
        return petbookRepository.bookItem(petId);
    }

    @Override
    public void bookUpdate(Book item) {
        petbookRepository.bookUpdate(item);
    }
    @Override
    public void bookDelete(int petId) {
        petbookRepository.bookDelete(petId);
    }


}
