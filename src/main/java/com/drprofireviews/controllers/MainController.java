package com.drprofireviews.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {
    @GetMapping({"/"})
    public String index(Model model) {
        return "index";
    }


    @GetMapping({"/hello"})
    public String hello(Model model) {
        return "hello";
    }
}
