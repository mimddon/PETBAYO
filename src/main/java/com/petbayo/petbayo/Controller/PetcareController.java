package com.petbayo.petbayo.Controller;

import com.petbayo.petbayo.Model.Care;
import com.petbayo.petbayo.Service.PetcareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PetcareController {

    @Autowired
    private PetcareService careService;

    @GetMapping("/care/list")
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

        return "redirect: care/careCreate";
    }

    @GetMapping("care/delete/{text_id}")
    public String careDelete(@PathVariable int text_id) {
        careService.careDelete(text_id);

        return "redirect: delete";
    }


}
