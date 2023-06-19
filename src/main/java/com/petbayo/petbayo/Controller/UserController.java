package com.petbayo.petbayo.Controller;

import com.petbayo.petbayo.Model.User;
import com.petbayo.petbayo.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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

    @ResponseBody
    @PostMapping("/login")
    public String loginRegister(@RequestParam("email") String email, @RequestParam("pwd") String pwd, HttpSession session) {
        User user = new User();

        user.setEmail(email);
        user.setPwd(pwd);

        if (service.login(user)) {
            session.setAttribute("user", user);
            System.out.println("!!!!!!" + session.getAttribute("user"));
            return "loginSuccess";
        } else {
            return "loginFail";
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

    @GetMapping("/mypage/{userId}")
    public String myPage(@PathVariable int userId, Model model, HttpSession session) {
        User findUser = service.findOne(userId);
        model.addAttribute("item", findUser);
        System.out.println(model.getAttribute("item"));
        return "mypage/myPageView";
    }

    @GetMapping("/api/users/img/{targetId}")
    @ResponseBody
    public ResponseEntity<byte[]> findMyPageImg (@PathVariable int targetId) throws Exception{
        User findUser = service.findOne(targetId);
        File file = new File(findUser.getImagePath());
        byte[] bytes = Files.readAllBytes(file.toPath());

        HttpHeaders headers = new HttpHeaders();
        String filename = file.getName();
        String fileExtension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

        switch (fileExtension) {
            case "png":
                headers.setContentType(MediaType.IMAGE_PNG);
                break;
            case "jpg":
            case "jpeg":
                headers.setContentType(MediaType.IMAGE_JPEG);
                break;
            case "gif":
                headers.setContentType(MediaType.IMAGE_GIF);
                break;
            default:
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                break;
        }

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(bytes);
    }

    @PostMapping("/myPageView/{userId}")
    public String addProfile(@PathVariable int userId, @RequestParam("file") MultipartFile file) throws IOException {
        User user = service.findOne(userId);

        service.uploadProfile(user, file);

        return "redirect:/mypage/" + userId;
    }

    @GetMapping("/api/user/{userId}")
    @ResponseBody
    public ResponseEntity<User> getUserInfo(@PathVariable int userId) {
        User user = service.findOne(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/myPageView/intro/{userId}")
    public String intro(@PathVariable int userId, @RequestParam("intro") String intro) {
        User user = service.findOne(userId);

        service.uploadIntro(user, intro);

        return "redirect:/mypage/" + userId;
    }

}
