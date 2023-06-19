package com.petbayo.petbayo.Controller;

import com.petbayo.petbayo.Model.Book;
import com.petbayo.petbayo.Model.FileRequest;
import com.petbayo.petbayo.Model.FileUtils;
import com.petbayo.petbayo.Service.FileService;
import com.petbayo.petbayo.Service.PetbookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
public class PetbookController {


    @Autowired
    private final FileService fileService;

    @Autowired
    private final FileUtils fileUtils;

    @Autowired
    private PetbookService bookService;

    @Value("${upload.path}")
    String uploadPath;

    public PetbookController(FileService fileService, FileUtils fileUtils) {
        this.fileService = fileService;
        this.fileUtils = fileUtils;
    }

    @GetMapping("/book/bookList")
    public String bookList(Model model, Book item) {
        List<Book> book = bookService.bookList();
        for(Book b : book){
            String imgList = b.getImgList();

            if (imgList != null && !imgList.isEmpty()) {
                List<String> list = Arrays.asList(imgList.split(","));
                b.setImgs(list);
            }
        }


        model.addAttribute("book", book);
//        List<FileRequest> files = fileUtils.uploadFiles(item.getFiles());
//        fileService.saveFiles(item.getPetId(), files);
//        model.addAttribute("files", files);
        log.info(book.toString());
        return "book/bookList";
    }


    @GetMapping("/bookCreate")
    public String bookCreate() {

        return "/book/bookCreate";
    }

    @PostMapping("/bookCreate")
    public String add(Book item, Model model) {
        bookService.bookCreate(item);
        List<FileRequest> files = fileUtils.uploadFiles(item.getFiles());
        fileService.saveFiles(item.getPetId(), files);
        return "redirect:/book/bookList";
    }




    @GetMapping("/book/delete/{petId}")
    public String bookDelete(@PathVariable int petId) {
        bookService.bookDelete(petId);
        return "redirect:/book/bookList";
    }


    @GetMapping("/book/update/{petId}")
    public String bookUpdate(@PathVariable int petId, Model model) {
        Book item = bookService.bookItem(petId);

        List<HashMap<String,String>> fileList = fileService.findOne(petId);
        log.info(fileList.toString());
        model.addAttribute("item", item);
        model.addAttribute("files", fileList);
        return "book/bookUpdate";
    }

    @PostMapping("/book/update/{petId}")
    public String bookUpdate(@PathVariable int petId, @ModelAttribute("item") Book item) {

        bookService.bookUpdate(item);

        return "redirect:/book/bookList";
    }

    @GetMapping("/book/search")
    public String searchBooks(@RequestParam("keyword") String keyword,
                              @RequestParam("category") String category,
                              Model model) {
        List<Book> searchResults = bookService.searchBooks(keyword, category);
        model.addAttribute("book", searchResults);
        return "/book/bookList";
    }

    @GetMapping("/api/book")
    @ResponseBody
    public List<Book> getAllBook() {
        return bookService.bookList();
    }



}


