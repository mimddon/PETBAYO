package com.petbayo.petbayo.Controller;

import com.petbayo.petbayo.Model.Question;
import com.petbayo.petbayo.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Question> list = questionService.list();
        model.addAttribute("list", list);
        return "question/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("question", new Question());
        return "question/add";
    }

    @PostMapping("/add")
    public String add(Question question) {
        questionService.add(question);
        return "redirect:/question/list";
    }

    @GetMapping("/update/{qsId}")
    public String update(@PathVariable int qsId, Model model) {
        Question question = questionService.item(qsId);
        model.addAttribute("question", question);
        model.addAttribute("processList", Arrays.asList(Process.values()));
        return "question/update";
    }

    @PostMapping("/update/{qsId}")
    public String update(Question question) {
        questionService.update(question);
        return "redirect:/question/list";
    }

    @GetMapping("/delete/{qsId}")
    public String delete(@PathVariable int qsId) {
        questionService.delete(qsId);
        return "redirect:/question/list";
    }
}