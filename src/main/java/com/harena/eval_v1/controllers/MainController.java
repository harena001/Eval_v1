package com.harena.eval_v1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String defaultPage(){
        return "login";
    }

    @GetMapping("/admin")
    public String toAdmin(){
        return "loginAdmin";
    }

}
