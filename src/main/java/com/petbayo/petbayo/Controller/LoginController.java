package com.petbayo.petbayo.Controller;

import com.petbayo.petbayo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/email-login")
    public String emailLogin() {
        return "email-login";
    }

    @PostMapping("/email-login")
    @ResponseBody
    public String processEmailLogin(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        User user = userService.findUserByEmail(email);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return "success";
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "fail";
        }
    }
}