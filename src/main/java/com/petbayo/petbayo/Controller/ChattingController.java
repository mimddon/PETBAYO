package com.petbayo.petbayo.Controller;

import com.petbayo.petbayo.Model.ChatMessage;
import com.petbayo.petbayo.Model.ChatRequest;
import com.petbayo.petbayo.Model.User;
import com.petbayo.petbayo.Service.ChattingService;
import com.petbayo.petbayo.Service.UserService;
import com.petbayo.petbayo.Service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/chat")
@Controller
public class ChattingController {

    private final ChattingService chattingService;

    private final UserServiceImpl userService;

    @GetMapping("")
    public ModelAndView getMainPage(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User user = null;
        try {
            user = (User) session.getAttribute("user");
        } catch (Exception e) {
            user = userService.findOne(1);
        }
        List<ChatMessage>  chatMessageList = chattingService.getMessageList(user.getUserId());
        modelAndView.addObject("userInfo", user);
        modelAndView.addObject("chatMessageList", chatMessageList);
        modelAndView.setViewName("/chat/main");
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getChattingRoom(@PathVariable("id") String id, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        HashMap<String, String> params = new HashMap();
        User user = null;
        try {
            user = (User) session.getAttribute("user");
            params.put("me", String.valueOf(user.getUserId()));
        } catch (Exception e) {
            user = userService.findOne(1);
        }

        params.put("targetUser", id);

        List<ChatMessage>  chatMessageLogList = chattingService.getChatLog(params);
        log.info(user.toString());
        log.info(chatMessageLogList.toString());
        modelAndView.addObject("userInfo", user);
        modelAndView.addObject("destUserId", id);
        modelAndView.addObject("chatMessageLogList", chatMessageLogList);
        modelAndView.setViewName("/chat/chatroom");
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView submitMessage(@PathVariable("id") String id, @ModelAttribute ChatRequest chatRequest, HttpSession session) {
        log.info(chatRequest.toString());
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/chat/"+ id);
        ModelAndView modelAndView = new ModelAndView(redirectView);
        HashMap<String, String> params = new HashMap();
        User user = null;
        try {
            user = (User) session.getAttribute("user");
            params.put("me", String.valueOf(user.getUserId()));
        } catch (Exception e) {
            user = userService.findOne(1);
        }
        chattingService.submitMessage(chatRequest);
        params.put("targetUser", id);
        List<ChatMessage>  chatMessageLogList = chattingService.getChatLog(params);
        log.info(user.toString());
        log.info(chatMessageLogList.toString());
        modelAndView.addObject("userInfo", user);
        modelAndView.addObject("destUserId", id);
        modelAndView.addObject("chatMessageLogList", chatMessageLogList);
        return modelAndView;
    }
}
