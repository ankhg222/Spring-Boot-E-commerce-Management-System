package vn.iot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController_23110236 {
    
    @GetMapping("/")
    public String home() {
        return "home/index_23110236";
    }
}
