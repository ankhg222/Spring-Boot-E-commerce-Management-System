package vn.iot.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.iot.model.Product_23110236;
import vn.iot.model.Users_23110236;
import vn.iot.service.ProductService_23110236;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController_23110236 {
    
    @Autowired
    private ProductService_23110236 productService;
    
    @GetMapping
    public String productsPage(HttpSession session, Model model) {
        // Check if user is admin
        Users_23110236 currentUser = (Users_23110236) session.getAttribute("user");
        if (currentUser == null || !"ADMIN".equalsIgnoreCase(currentUser.getUserRole().getRoleName())) {
            return "redirect:/login";
        }
        
        List<Product_23110236> products = productService.getAllProducts();
        model.addAttribute("products", products);
        
        return "admin/products/list_23110236";
    }
}
