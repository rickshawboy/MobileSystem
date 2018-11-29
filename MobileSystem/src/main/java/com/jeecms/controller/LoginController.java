package com.jeecms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {
	
	@RequestMapping("/login")
    public String helloHtml() {
       
        return "/index";
    }
	
	@RequestMapping("/notify")
    public String notifyHtml() {
       
        return "/notify";
    }
	
	@RequestMapping("/return")
    public String returnHtml() {
       
        return "/return";
    }
}
