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
    @RequestMapping("/pet-book")
    public String petBook() {
        return "/pet-book";
    }
    @RequestMapping("/register")
    public String register() {
        return "/register";
    }
    @RequestMapping("/login")
    public String login() {
        return "/login";
    }
    @RequestMapping("/question")
    public String question() {return  "/question";}

}
