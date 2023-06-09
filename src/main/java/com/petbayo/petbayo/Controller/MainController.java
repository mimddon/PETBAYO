package com.petbayo.petbayo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String mainPage() {
        return "main";
    }

    @RequestMapping("/careList")
    public String petCare() {
        return "/care/careList";
    }

    @RequestMapping("" + "/bookList")
    public String petBook() {
        return "/book/bookList";
    }

    @RequestMapping("/board/list")
    public String board() {
        return "/board/list";
    }

}