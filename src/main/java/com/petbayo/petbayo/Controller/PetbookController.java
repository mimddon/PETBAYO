package com.petbayo.petbayo.Controller;

import com.petbayo.petbayo.Model.Book;
import com.petbayo.petbayo.Model.FileRequest;
import com.petbayo.petbayo.Model.FileResponse;
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

        // 2. 파일 업로드 (to disk)
        List<FileRequest> uploadFiles = fileUtils.uploadFiles(item.getFiles());

        // 3. 파일 정보 저장 (to database)
        fileService.saveFiles(item.getPetId(), uploadFiles);

        // 4. 삭제할 파일 정보 조회 (from database)
        List<FileResponse> deleteFiles = fileService.findAllFileByIds(item.getRemoveFileIds());

        // 5. 파일 삭제 (from disk)
        fileUtils.deleteFiles(deleteFiles);

        // 6. 파일 삭제 (from database)
        fileService.deleteAllFileByIds(item.getRemoveFileIds());

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


