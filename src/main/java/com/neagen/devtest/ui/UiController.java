package com.neagen.devtest.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UiController {

    @GetMapping("/ui")
    public String ui(@RequestParam(name="name", required=false, defaultValue="stranger") String name, Model model) {
        model.addAttribute("name", name);
        return "ui";
    }
}
