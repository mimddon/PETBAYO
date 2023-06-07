package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Domain.FileMapper;
import com.petbayo.petbayo.Model.Book;
import com.petbayo.petbayo.Model.FileRequest;
import com.petbayo.petbayo.Model.FileUtils;
import com.petbayo.petbayo.Repository.PetbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
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
    @Transactional
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

    @Override
    public List<Book> searchBooks(String keyword, String category) {
        // 검색 로직을 구현합니다.
        return petbookRepository.searchBooks(keyword, category);
    }




}
