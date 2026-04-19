package vn.iot.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.iot.model.Users_23110236;
import vn.iot.model.UserRoles_23110236;
import vn.iot.service.UserService_23110236;
import vn.iot.service.UserRolesService_23110236;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController_23110236 {
    
    @Autowired
    private UserService_23110236 userService;
    
    @Autowired
    private UserRolesService_23110236 userRolesService;
    
    @GetMapping
    public String usersPage(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "5") int size,
                           HttpSession session,
                           Model model) {
        // Check if user is admin
        Users_23110236 currentUser = (Users_23110236) session.getAttribute("user");
        if (currentUser == null || !"ADMIN".equalsIgnoreCase(currentUser.getUserRole().getRoleName())) {
            return "redirect:/login";
        }
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Users_23110236> usersPage = userService.getAllUsersPaged(pageable);
        
        model.addAttribute("users", usersPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", usersPage.getTotalPages());
        model.addAttribute("totalItems", usersPage.getTotalElements());
        model.addAttribute("pageSize", size);
        
        return "admin/users/list_23110236";
    }
    
    @GetMapping("/add")
    public String addUserPage(HttpSession session, Model model) {
        // Check if user is admin
        Users_23110236 currentUser = (Users_23110236) session.getAttribute("user");
        if (currentUser == null || !"ADMIN".equalsIgnoreCase(currentUser.getUserRole().getRoleName())) {
            return "redirect:/login";
        }
        
        model.addAttribute("user", new Users_23110236());
        model.addAttribute("roles", userRolesService.getAllRoles());
        return "admin/users/form_23110236";
    }
    
    @PostMapping("/add")
    public String addUser(@ModelAttribute Users_23110236 user, HttpSession session) {
        // Check if user is admin
        Users_23110236 currentUser = (Users_23110236) session.getAttribute("user");
        if (currentUser == null || !"ADMIN".equalsIgnoreCase(currentUser.getUserRole().getRoleName())) {
            return "redirect:/login";
        }
        
        userService.saveUser(user);
        return "redirect:/admin/users";
    }
    
    @GetMapping("/edit/{id}")
    public String editUserPage(@PathVariable Integer id, HttpSession session, Model model) {
        // Check if user is admin
        Users_23110236 currentUser = (Users_23110236) session.getAttribute("user");
        if (currentUser == null || !"ADMIN".equalsIgnoreCase(currentUser.getUserRole().getRoleName())) {
            return "redirect:/login";
        }
        
        Users_23110236 user = userService.getUserById(id).orElse(null);
        if (user == null) {
            return "redirect:/admin/users";
        }
        
        model.addAttribute("user", user);
        model.addAttribute("roles", userRolesService.getAllRoles());
        return "admin/users/form_23110236";
    }
    
    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable Integer id, @ModelAttribute Users_23110236 user, HttpSession session) {
        // Check if user is admin
        Users_23110236 currentUser = (Users_23110236) session.getAttribute("user");
        if (currentUser == null || !"ADMIN".equalsIgnoreCase(currentUser.getUserRole().getRoleName())) {
            return "redirect:/login";
        }
        
        user.setUserId(id);
        userService.saveUser(user);
        return "redirect:/admin/users";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id, HttpSession session) {
        // Check if user is admin
        Users_23110236 currentUser = (Users_23110236) session.getAttribute("user");
        if (currentUser == null || !"ADMIN".equalsIgnoreCase(currentUser.getUserRole().getRoleName())) {
            return "redirect:/login";
        }
        
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
