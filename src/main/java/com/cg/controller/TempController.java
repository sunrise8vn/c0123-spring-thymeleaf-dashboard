package com.cg.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/temp")
public class TempController {

    @GetMapping
    public String getTemp(){
        return "/temp";
    }
}
