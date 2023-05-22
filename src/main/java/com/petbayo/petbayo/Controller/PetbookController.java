package com.petbayo.petbayo.Controller;

import com.petbayo.petbayo.Model.Book;
import com.petbayo.petbayo.Model.FileRequest;
import com.petbayo.petbayo.Model.FileUtils;
import com.petbayo.petbayo.Service.FileService;
import com.petbayo.petbayo.Service.PetbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PetbookController {

    private final String uploadPath = Paths.get("C:", "develop", "upload-files").toString();

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
    public String bookList(Model model) {
        List<Book> book = bookService.bookList();
        model.addAttribute("book", book);
        return "book/bookList";
    }


    @GetMapping("/bookCreate")
    public String bookCreate() {

        return "/book/bookCreate";
    }

    @PostMapping("/bookCreate")
    public String add(final Book item, Model model) {
        Long id = bookService.bookCreate(item);
        List<FileRequest> files = fileUtils.uploadFiles(item.getFiles());
        fileService.saveFiles(id, files);

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


