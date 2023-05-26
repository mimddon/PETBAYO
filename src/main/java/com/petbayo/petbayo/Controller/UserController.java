package com.petbayo.petbayo.Controller;

import com.petbayo.petbayo.Model.User;
import com.petbayo.petbayo.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

        return "loginRegister";
    }

    @PostMapping("/loginRegister")
    public String loginRegister(@RequestParam("email") String email, @RequestParam("pwd") String pwd, HttpSession session, Model model) {
        User user = new User();
        user.setEmail(email);
        user.setPwd(pwd);
        if(service.login(user)) {
            session.setAttribute("user", user);

            String targetUrl = (String) session.getAttribute("target_url");

            session.removeAttribute("target_url");

            if(targetUrl == null) {
                return "redirect:/";
            } else {
                return "redirect:/" + targetUrl;

            }
        } else {
            // 로그인 실패 시 회원가입 처리
            service.register(user);

            model.addAttribute("user", user);
            System.out.println("회원가입 성공");
            return "redirect:/";  // 회원가입 성공 페이지로 리다이렉트
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

        return "redirect:.";
    }

}
