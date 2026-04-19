package vn.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.iot.model.Product_23110236;
import vn.iot.service.ProductService_23110236;
import vn.iot.service.CategoryService_23110236;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController_23110236 {
    
    @Autowired
    private ProductService_23110236 productService;
    
    @Autowired
    private CategoryService_23110236 categoryService;
    
    @GetMapping("/products")
    public String products(@RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "12") int size,
                          @RequestParam(required = false) String category,
                          Model model) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Product_23110236> products;
        
        if (category != null && !category.isEmpty()) {
            products = productService.getProductsByCategory(category, pageable);
        } else {
            products = productService.getAllProducts(pageable);
        }
        
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("currentCategory", category);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", products.getTotalPages());
        
        return "products/list_23110236";
    }
    
    @GetMapping("/products/by-sellers")
    public String productsBySellers(Model model) {
        var sellersWithProducts = productService.getSellersWithProducts();
        
        // Create a map to store products for each seller
        Map<Integer, List<Product_23110236>> sellerProductsMap = new HashMap<>();
        for (var seller : sellersWithProducts) {
            var products = productService.getProductsBySeller(seller.getSellerId());
            sellerProductsMap.put(seller.getSellerId(), products);
        }
        
        model.addAttribute("sellersWithProducts", sellersWithProducts);
        model.addAttribute("sellerProductsMap", sellerProductsMap);
        return "products/by-sellers_23110236";
    }
    
    @GetMapping("/products/by-seller/{sellerId}")
    public String productsBySeller(@PathVariable Integer sellerId, Model model) {
        var seller = productService.getSellerById(sellerId);
        if (seller == null) {
            return "redirect:/products";
        }
        
        var products = productService.getProductsBySeller(sellerId);
        model.addAttribute("seller", seller);
        model.addAttribute("products", products);
        
        return "products/by-seller_23110236";
    }
    
    @GetMapping("/products/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        Product_23110236 product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/products";
        }
        
        model.addAttribute("product", product);
        return "products/detail_23110236";
    }
}