package com.jeecms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
	
	@RequestMapping("/login")
    public String helloHtml() {
       
        return "/index";
    }
	
	@RequestMapping("/notify")
    public String notifyHtml() {
       
        return "/notify";
    }
	
	@GetMapping("/return")
    public String returnHtml() {
       
        return "/return";
    }
}
