package com.petbayo.petbayo.Controller;

import com.petbayo.petbayo.Model.User;
import com.petbayo.petbayo.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@AllArgsConstructor
@Controller
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/loginRegister")
    public String login() {
        return "login";
    }

    @PostMapping("/loginRegister")
    public String login(@RequestParam("email") String email, @RequestParam("pwd") String pwd, HttpSession session) {
        User user = new User();
        user.setEmail(email);
        user.setPwd(pwd);
        if(service.login(user)) {
            session.setAttribute("user", user);

            String targetUrl = (String) session.getAttribute("target_url");
            System.out.println("RootController: " + targetUrl);
            session.removeAttribute("target_url");

            if(targetUrl == null)
                return "redirect:.";
            else
                return "redirect:" + targetUrl;
        } else
            return "redirect:main";
    }

    @ResponseBody
    @GetMapping("/checkEmail/{email}")
    public String checkEmail(@PathVariable String email) {
        if(service.checkEmail(email))
            return "OK";
        else
            return "FAIL";
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:.";
    }

    @GetMapping("/register")
    public String register() {

        return "register";
    }

    @PostMapping("/register")
    public String register(User item) {

        service.register(item);

        return "redirect:.";
    }


}
