package com.petbayo.petbayo.Controller;

import com.petbayo.petbayo.Model.Book;
import com.petbayo.petbayo.Model.FileRequest;
import com.petbayo.petbayo.Model.FileUtils;
import com.petbayo.petbayo.Service.FileService;
import com.petbayo.petbayo.Service.PetbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.nio.file.Paths;
import java.util.List;

@Controller
public class PetbookController {
    @Value("${upload-path}")
    private final String uploadPath = Paths.get("/images").toString();

    @Autowired
    private final FileService fileService;

    @Autowired
    private final FileUtils fileUtils;

    @Autowired
    private PetbookService bookService;

    public PetbookController(FileService fileService, FileUtils fileUtils) {
        this.fileService = fileService;
        this.fileUtils = fileUtils;
    }

    @GetMapping("/book/bookList")
    public String bookList(Model model, Book item) {
        List<Book> book = bookService.bookList();
        model.addAttribute("book", book);
        List<FileRequest> files = fileUtils.uploadFiles(item.getFiles());
        fileService.saveFiles(item.getPetId(), files);
        model.addAttribute("files", files);

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
        model.addAttribute("item", item);
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


