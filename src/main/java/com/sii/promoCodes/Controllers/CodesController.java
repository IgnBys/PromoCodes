package com.sii.promoCodes.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CodesController {
    @GetMapping("/promocodes")
    public String promocodes_main(
//            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Model model) {
        model.addAttribute("title", "Home page");
        return "promocodes";
    }
}
