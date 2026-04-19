package vn.iot.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.iot.model.Users_23110236;
import vn.iot.model.UserRoles_23110236;
import vn.iot.service.AuthService_23110236;
import vn.iot.service.UserRolesService_23110236;

@Controller
public class AuthController_23110236 {
    
    @Autowired
    private AuthService_23110236 authService;
    
    @Autowired
    private UserRolesService_23110236 userRolesService;
    
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new Users_23110236());
        return "auth/login_23110236";
    }
    
    @PostMapping("/login")
    public String login(@ModelAttribute Users_23110236 user, HttpSession session, Model model) {
        var authenticatedUser = authService.authenticate(user.getEmail(), user.getPassword());
        
        if (authenticatedUser.isPresent()) {
            Users_23110236 loggedInUser = authenticatedUser.get();
            session.setAttribute("user", loggedInUser);
            
            // Redirect based on role
            if (authService.isAdmin(loggedInUser)) {
                return "redirect:/admin";
            } else if (authService.isSeller(loggedInUser)) {
                return "redirect:/seller";
            } else {
                return "redirect:/";
            }
        } else {
            model.addAttribute("error", "Email hoặc mật khẩu không đúng!");
            model.addAttribute("user", new Users_23110236()); // Thêm lại user object
            return "auth/login_23110236";
        }
    }
    
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new Users_23110236());
        model.addAttribute("roles", userRolesService.getAllRoles());
        return "auth/register_23110236";
    }
    
    @PostMapping("/register")
    public String register(@ModelAttribute Users_23110236 user, Model model) {
        if (authService.register(user)) {
            model.addAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
            model.addAttribute("user", new Users_23110236()); // Thêm user object cho login page
            return "auth/login_23110236";
        } else {
            model.addAttribute("error", "Tên đăng nhập hoặc email đã tồn tại!");
            model.addAttribute("roles", userRolesService.getAllRoles());
            return "auth/register_23110236";
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
