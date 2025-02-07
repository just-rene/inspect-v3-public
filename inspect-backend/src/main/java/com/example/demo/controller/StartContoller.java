package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartContoller {

    @GetMapping("/")
    public String serveMain(Model model){
        return "index";
    }
}
