package vn.iot.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.iot.model.Product_23110236;
import vn.iot.model.Users_23110236;
import vn.iot.service.ProductService_23110236;
import vn.iot.service.UserService_23110236;
import vn.iot.service.CategoryService_23110236;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/seller")
public class SellerController_23110236 {
    
    @Autowired
    private ProductService_23110236 productService;
    
    @Autowired
    private UserService_23110236 userService;
    
    @Autowired
    private CategoryService_23110236 categoryService;
    
    @GetMapping
    public String sellerHome(HttpSession session, Model model) {
        // Check if user is seller
        Users_23110236 currentUser = (Users_23110236) session.getAttribute("user");
        if (currentUser == null || !"SELLER".equalsIgnoreCase(currentUser.getUserRole().getRoleName())) {
            return "redirect:/login";
        }
        
        model.addAttribute("user", currentUser);
        return "seller/index_23110236";
    }
    
    @GetMapping("/products")
    public String sellerProducts(HttpSession session, Model model) {
        // Check if user is seller
        Users_23110236 sessionUser = (Users_23110236) session.getAttribute("user");
        if (sessionUser == null) {
            return "redirect:/login";
        }
        
        // Load user from database to avoid LazyInitializationException
        Optional<Users_23110236> userOpt = userService.getUserById(sessionUser.getUserId());
        if (!userOpt.isPresent()) {
            return "redirect:/login";
        }
        Users_23110236 currentUser = userOpt.get();
        if (!"SELLER".equalsIgnoreCase(currentUser.getUserRole().getRoleName())) {
            return "redirect:/login";
        }
        
        // Get products for this seller
        List<Product_23110236> products = productService.getProductsBySellerId(currentUser.getSeller().getSellerId());
        
        model.addAttribute("products", products);
        model.addAttribute("user", currentUser);
        
        return "seller/products_23110236";
    }
    
    @GetMapping("/stats")
    public String sellerStats(HttpSession session, Model model) {
        // Check if user is seller
        Users_23110236 sessionUser = (Users_23110236) session.getAttribute("user");
        if (sessionUser == null) {
            return "redirect:/login";
        }
        
        // Load user from database to avoid LazyInitializationException
        Optional<Users_23110236> userOpt = userService.getUserById(sessionUser.getUserId());
        if (!userOpt.isPresent()) {
            return "redirect:/login";
        }
        Users_23110236 currentUser = userOpt.get();
        if (!"SELLER".equalsIgnoreCase(currentUser.getUserRole().getRoleName())) {
            return "redirect:/login";
        }
        
        // Get products for this seller
        List<Product_23110236> products = productService.getProductsBySellerId(currentUser.getSeller().getSellerId());
        
        model.addAttribute("products", products);
        model.addAttribute("user", currentUser);
        
        return "seller/stats_23110236";
    }
    
    @GetMapping("/products/add")
    public String addProductPage(HttpSession session, Model model) {
        Users_23110236 sessionUser = (Users_23110236) session.getAttribute("user");
        if (sessionUser == null) {
            return "redirect:/login";
        }
        
        Optional<Users_23110236> userOpt = userService.getUserById(sessionUser.getUserId());
        if (!userOpt.isPresent()) {
            return "redirect:/login";
        }
        Users_23110236 currentUser = userOpt.get();
        if (!"SELLER".equalsIgnoreCase(currentUser.getUserRole().getRoleName())) {
            return "redirect:/login";
        }
        
        model.addAttribute("product", new Product_23110236());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("user", currentUser);
        
        return "seller/products/add_23110236";
    }
    
    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute Product_23110236 product, 
                           @RequestParam("category.categoryId") Integer categoryId,
                           HttpSession session, Model model) {
        Users_23110236 sessionUser = (Users_23110236) session.getAttribute("user");
        if (sessionUser == null) {
            return "redirect:/login";
        }
        
        Optional<Users_23110236> userOpt = userService.getUserById(sessionUser.getUserId());
        if (!userOpt.isPresent()) {
            return "redirect:/login";
        }
        Users_23110236 currentUser = userOpt.get();
        if (!"SELLER".equalsIgnoreCase(currentUser.getUserRole().getRoleName())) {
            return "redirect:/login";
        }
        
        // Set seller for the product
        product.setSeller(currentUser.getSeller());
        
        // Set category
        var categoryOpt = categoryService.getCategoryById(categoryId);
        if (categoryOpt.isPresent()) {
            product.setCategory(categoryOpt.get());
        }
        
        productService.saveProduct(product);
        return "redirect:/seller/products";
    }
    
    @GetMapping("/products/edit/{id}")
    public String editProductPage(@PathVariable Integer id, HttpSession session, Model model) {
        Users_23110236 sessionUser = (Users_23110236) session.getAttribute("user");
        if (sessionUser == null) {
            return "redirect:/login";
        }
        
        Optional<Users_23110236> userOpt = userService.getUserById(sessionUser.getUserId());
        if (!userOpt.isPresent()) {
            return "redirect:/login";
        }
        Users_23110236 currentUser = userOpt.get();
        if (!"SELLER".equalsIgnoreCase(currentUser.getUserRole().getRoleName())) {
            return "redirect:/login";
        }
        
        Optional<Product_23110236> productOpt = productService.getProductById(id);
        if (!productOpt.isPresent()) {
            return "redirect:/seller/products";
        }
        
        Product_23110236 product = productOpt.get();
        // Check if product belongs to this seller
        if (!product.getSeller().getSellerId().equals(currentUser.getSeller().getSellerId())) {
            return "redirect:/seller/products";
        }
        
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("user", currentUser);
        
        return "seller/products/edit_23110236";
    }
    
    @PostMapping("/products/edit/{id}")
    public String editProduct(@PathVariable Integer id, 
                            @ModelAttribute Product_23110236 product,
                            @RequestParam("category.categoryId") Integer categoryId,
                            HttpSession session, Model model) {
        Users_23110236 sessionUser = (Users_23110236) session.getAttribute("user");
        if (sessionUser == null) {
            return "redirect:/login";
        }
        
        Optional<Users_23110236> userOpt = userService.getUserById(sessionUser.getUserId());
        if (!userOpt.isPresent()) {
            return "redirect:/login";
        }
        Users_23110236 currentUser = userOpt.get();
        if (!"SELLER".equalsIgnoreCase(currentUser.getUserRole().getRoleName())) {
            return "redirect:/login";
        }
        
        Optional<Product_23110236> existingProductOpt = productService.getProductById(id);
        if (!existingProductOpt.isPresent()) {
            return "redirect:/seller/products";
        }
        
        Product_23110236 existingProduct = existingProductOpt.get();
        // Check if product belongs to this seller
        if (!existingProduct.getSeller().getSellerId().equals(currentUser.getSeller().getSellerId())) {
            return "redirect:/seller/products";
        }
        
        // Update product fields
        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductCode(product.getProductCode());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setAmount(product.getAmount());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setImages(product.getImages());
        existingProduct.setStock(product.getStock());
        existingProduct.setWishlist(product.getWishlist());
        
        // Set category
        var categoryOpt = categoryService.getCategoryById(categoryId);
        if (categoryOpt.isPresent()) {
            existingProduct.setCategory(categoryOpt.get());
        }
        
        productService.saveProduct(existingProduct);
        return "redirect:/seller/products";
    }
    
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Integer id, HttpSession session) {
        Users_23110236 sessionUser = (Users_23110236) session.getAttribute("user");
        if (sessionUser == null) {
            return "redirect:/login";
        }
        
        Optional<Users_23110236> userOpt = userService.getUserById(sessionUser.getUserId());
        if (!userOpt.isPresent()) {
            return "redirect:/login";
        }
        Users_23110236 currentUser = userOpt.get();
        if (!"SELLER".equalsIgnoreCase(currentUser.getUserRole().getRoleName())) {
            return "redirect:/login";
        }
        
        Optional<Product_23110236> productOpt = productService.getProductById(id);
        if (productOpt.isPresent()) {
            Product_23110236 product = productOpt.get();
            // Check if product belongs to this seller
            if (product.getSeller().getSellerId().equals(currentUser.getSeller().getSellerId())) {
                productService.deleteProduct(id);
            }
        }
        
        return "redirect:/seller/products";
    }
}
