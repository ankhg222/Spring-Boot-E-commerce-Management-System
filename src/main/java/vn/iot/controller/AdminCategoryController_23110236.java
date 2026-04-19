package vn.iot.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.iot.model.Category_23110236;
import vn.iot.model.Users_23110236;
import vn.iot.service.CategoryService_23110236;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController_23110236 {
    
    @Autowired
    private CategoryService_23110236 categoryService;
    
    @GetMapping
    public String categoriesPage(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size,
                                HttpSession session,
                                Model model) {
        // Check if user is admin
        Users_23110236 currentUser = (Users_23110236) session.getAttribute("user");
        if (currentUser == null || !"ADMIN".equalsIgnoreCase(currentUser.getUserRole().getRoleName())) {
            return "redirect:/login";
        }
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Category_23110236> categoriesPage = categoryService.getAllCategoriesPaged(pageable);
        
        model.addAttribute("categories", categoriesPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", categoriesPage.getTotalPages());
        model.addAttribute("totalItems", categoriesPage.getTotalElements());
        model.addAttribute("pageSize", size);
        
        return "admin/categories/list_23110236";
    }
    
    @GetMapping("/add")
    public String addCategoryPage(HttpSession session, Model model) {
        // Check if user is admin
        Users_23110236 currentUser = (Users_23110236) session.getAttribute("user");
        if (currentUser == null || !"ADMIN".equalsIgnoreCase(currentUser.getUserRole().getRoleName())) {
            return "redirect:/login";
        }
        
        model.addAttribute("category", new Category_23110236());
        return "admin/categories/form_23110236";
    }
    
    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category_23110236 category, HttpSession session) {
        // Check if user is admin
        Users_23110236 currentUser = (Users_23110236) session.getAttribute("user");
        if (currentUser == null || !"ADMIN".equalsIgnoreCase(currentUser.getUserRole().getRoleName())) {
            return "redirect:/login";
        }
        
        categoryService.saveCategory(category);
        return "redirect:/admin/categories";
    }
    
    @GetMapping("/edit/{id}")
    public String editCategoryPage(@PathVariable Integer id, HttpSession session, Model model) {
        // Check if user is admin
        Users_23110236 currentUser = (Users_23110236) session.getAttribute("user");
        if (currentUser == null || !"ADMIN".equalsIgnoreCase(currentUser.getUserRole().getRoleName())) {
            return "redirect:/login";
        }
        
        Category_23110236 category = categoryService.getCategoryById(id).orElse(null);
        if (category == null) {
            return "redirect:/admin/categories";
        }
        
        model.addAttribute("category", category);
        return "admin/categories/form_23110236";
    }
    
    @PostMapping("/edit/{id}")
    public String editCategory(@PathVariable Integer id, @ModelAttribute Category_23110236 category, HttpSession session) {
        // Check if user is admin
        Users_23110236 currentUser = (Users_23110236) session.getAttribute("user");
        if (currentUser == null || !"ADMIN".equalsIgnoreCase(currentUser.getUserRole().getRoleName())) {
            return "redirect:/login";
        }
        
        category.setCategoryId(id);
        categoryService.saveCategory(category);
        return "redirect:/admin/categories";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Integer id, HttpSession session) {
        // Check if user is admin
        Users_23110236 currentUser = (Users_23110236) session.getAttribute("user");
        if (currentUser == null || !"ADMIN".equalsIgnoreCase(currentUser.getUserRole().getRoleName())) {
            return "redirect:/login";
        }
        
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }
}
