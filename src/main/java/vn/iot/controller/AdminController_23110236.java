package vn.iot.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.iot.model.Users_23110236;

@Controller
@RequestMapping("/admin")
public class AdminController_23110236 {
    
    @GetMapping
    public String adminHome(HttpSession session, Model model) {
        // Check if user is admin
        Users_23110236 currentUser = (Users_23110236) session.getAttribute("user");
        if (currentUser == null || !"ADMIN".equalsIgnoreCase(currentUser.getUserRole().getRoleName())) {
            return "redirect:/login";
        }
        
        model.addAttribute("user", currentUser);
        return "admin/index_23110236";
    }
}
