package com.petbayo.petbayo.Controller;

import com.petbayo.petbayo.Model.User;
import com.petbayo.petbayo.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.tools.jconsole.JConsole;

import javax.servlet.http.HttpSession;

@Slf4j
@AllArgsConstructor
@Controller
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/loginRegister")
    public String login() {
        return "loginRegister";
    }

    @PostMapping("/login")
    public String loginRegister(@RequestParam("email") String email, @RequestParam("pwd") String pwd, HttpSession session) {
        User user = new User();

        user.setEmail(email);
        user.setPwd(pwd);

        if (service.login(user)) {
            session.setAttribute("user", user);
            System.out.println(session.getAttribute("user"));
            return "redirect:/";
        } else {
            return "redirect:/login?error";
        }
        /*session.setAttribute("user", user);

        String targetUrl = (String) session.getAttribute("target_url");

        session.removeAttribute("target_url");

        if (targetUrl == null) {
            return "redirect:/";
        } else {
            return "redirect:/" + targetUrl;

        }*/
    }

    /*@GetMapping("/register")
    public String register() {
        return "loginRegister";
    }*/
    @PostMapping("/register")
    public String register(@RequestParam("email") String email, @RequestParam("pwd") String pwd, @RequestParam("nickname") String nickname, @RequestParam("birth") String birth, @RequestParam("gender") int gender) {
        User user = new User();
        user.setEmail(email);
        user.setPwd(pwd);
        user.setNickname(nickname);
        user.setBirth(birth);
        user.setGender(gender);

        boolean success = service.register(user);
        if (success) {
            return "redirect:/loginRegister";
        } else {
            return "redirect:/";
        }
    }

    @ResponseBody
    @GetMapping("/checkEmail/{email}")
    public String checkEmail(@PathVariable String email) {
        if(service.checkEmail(email))
            return "OK";
        else
            return "FAIL";
    }

    @ResponseBody
    @GetMapping("/checkNickname/{nickname}")
    public String checkNickname(@PathVariable String nickname) {
        if(service.checkNickname(nickname))
            return "OK";
        else
            return "FAIL";
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/";
    }

    @GetMapping("/mypage/{nickname}")
    public String myPage(@PathVariable String nickname, Model model, HttpSession session) {
        User findUser = service.findOne(nickname);
        model.addAttribute("item", findUser);
        System.out.println(model.getAttribute("item"));
        return "mypage/myPageView";



        /*User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        } else {
            User findUser = service.findOne(nickname);
            model.addAttribute("item", findUser);
            System.out.println(model.getAttribute("item"));
            return "mypage/myPageView";

        }*/


    }

}
