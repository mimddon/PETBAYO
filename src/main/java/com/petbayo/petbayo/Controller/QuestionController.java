package com.petbayo.petbayo.Controller;

import com.petbayo.petbayo.Model.Question;
import com.petbayo.petbayo.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Question> questions = questionService.list();
        model.addAttribute("list", questions); // 변경: "questions"에서 "list"로 수정
        return "Question/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
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
