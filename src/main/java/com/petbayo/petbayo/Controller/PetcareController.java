package com.petbayo.petbayo.Controller;

import com.petbayo.petbayo.Model.Care;
import com.petbayo.petbayo.Service.PetcareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
public class PetcareController {

    @Autowired
    private PetcareService careService;

    @GetMapping("/care/careList")
    public String careList(Model model) {
        List<Care> care = careService.careList();
        model.addAttribute("care", care);
        return "care/careList";
    }

    @GetMapping("/careCreate")
    public String careCreate() {
        return "/care/careCreate";
    }
    @PostMapping("/careCreate")
    public String add(Care item) {
        careService.careCreate(item);

        return "redirect:/care/careList";
    }

    @GetMapping("/care/delete/{textId}/{userId}")
    public String careDelete(@PathVariable int textId, @PathVariable int userId) {
        careService.careDelete(textId);

        return "redirect:/mypage/" + userId;
    }

    @GetMapping("/api/care")
    @ResponseBody
    public List<Care> getAllCare() {
        return careService.careList();
    }

    @GetMapping("/care/careView/{textId}")
    public String careView(@PathVariable int textId, Model model) {
        Care care = careService.careItem(textId);
        model.addAttribute("care", care);
        return "care/careView";
    }


}
