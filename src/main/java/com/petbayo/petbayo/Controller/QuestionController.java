package com.petbayo.petbayo.Controller;

import com.petbayo.petbayo.Model.Question;
import com.petbayo.petbayo.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/loginRegister")
    public String loginSuccess() {
        // 로그인 성공 시 리다이렉트할 경로를 "/question/list"로 변경
        return "redirect:/question/list";
    }

    @GetMapping("/list")
    public String list(Model model, Principal principal) {
        List<Question> questions;
        String loggedInUsername = principal != null ? principal.getName() : null;

        if (loggedInUsername != null && isAdmin(loggedInUsername)) {
            // 관리자인 경우 모든 질문 표시
            questions = questionService.list();
        } else {
            // 사용자인 경우 작성자와 공개된 질문 표시
            questions = questionService.findByUserOrDisclosure(loggedInUsername, Question.Disclosure.O);
        }

        model.addAttribute("list", questions);
        return "Question/list";
    }

    private boolean isAdmin(String loggedInUsername) {
        // 관리자를 나타내는 조건을 추가.
        // 예를 들어, "admin"이라는 사용자명을 관리자로 가정한다면:
        return "admin".equals(loggedInUsername);
    }

    @GetMapping("/add")
    public String add(Model model, Principal principal) {
        String loggedInUsername = principal != null ? principal.getName() : null;


        model.addAttribute("question", new Question());
        model.addAttribute("processList", Arrays.asList(Question.Process.values()));
        model.addAttribute("disclosureList", Arrays.asList(Question.Disclosure.values()));
        return "Question/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("question") Question question) {
        questionService.add(question);
        return "redirect:/question/list";
    }

    @GetMapping("/update/{qsId}")
    public String update(@PathVariable("qsId") Long qsId, Model model) {
        Question question = questionService.item(qsId);
        model.addAttribute("question", question);
        model.addAttribute("processList", Arrays.asList(Question.Process.values()));
        model.addAttribute("disclosureList", Arrays.asList(Question.Disclosure.values()));
        return "Question/update";
    }

    @PostMapping("/update/{qsId}")
    public String update(@ModelAttribute("question") Question question) {
        questionService.update(question);
        return "redirect:/question/list";
    }

    @GetMapping("/delete/{qsId}")
    public String delete(@PathVariable("qsId") Long qsId) {
        questionService.delete(qsId);
        return "redirect:/question/list";
    }
}
