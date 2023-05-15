package com.petbayo.petbayo.Controller;

import com.petbayo.petbayo.Model.Book;
import com.petbayo.petbayo.Service.PetbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class PetbookController {

    @Autowired
    private PetbookService bookService;


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
    public String add(Book item) {
        bookService.bookCreate(item);

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

    @GetMapping("/api/book")
    @ResponseBody
    public List<Book> getAllBook() {
        return bookService.bookList();
    }
}


