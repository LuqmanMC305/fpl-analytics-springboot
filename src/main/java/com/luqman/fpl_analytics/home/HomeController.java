package com.luqman.fpl_analytics.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home() {
        return "index";
    }
    
}
