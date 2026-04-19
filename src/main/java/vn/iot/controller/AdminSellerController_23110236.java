package vn.iot.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.iot.model.Seller_23110236;
import vn.iot.model.Users_23110236;
import vn.iot.service.SellerService_23110236;

import java.util.List;

@Controller
@RequestMapping("/admin/sellers")
public class AdminSellerController_23110236 {
    
    @Autowired
    private SellerService_23110236 sellerService;
    
    @GetMapping
    public String sellersPage(HttpSession session, Model model) {
        // Check if user is admin
        Users_23110236 currentUser = (Users_23110236) session.getAttribute("user");
        if (currentUser == null || !"ADMIN".equalsIgnoreCase(currentUser.getUserRole().getRoleName())) {
            return "redirect:/login";
        }
        
        List<Seller_23110236> sellers = sellerService.getAllSellers();
        model.addAttribute("sellers", sellers);
        
        return "admin/sellers/list_23110236";
    }
}
