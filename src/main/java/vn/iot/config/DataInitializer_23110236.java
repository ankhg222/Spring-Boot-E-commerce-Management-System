package vn.iot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vn.iot.model.*;
import vn.iot.service.*;

import java.math.BigInteger;
import java.time.LocalDate;

// @Component
public class DataInitializer_23110236 implements CommandLineRunner {
    
    @Autowired
    private UserRolesService_23110236 userRolesService;
    
    @Autowired
    private SellerService_23110236 sellerService;
    
    @Autowired
    private CategoryService_23110236 categoryService;
    
    @Autowired
    private UserService_23110236 userService;
    
    @Autowired
    private ProductService_23110236 productService;
    
    @Override
    public void run(String... args) throws Exception {
        initializeData();
    }
    
    private void initializeData() {
        // Initialize User Roles
        if (userRolesService.getAllRoles().isEmpty()) {
            UserRoles_23110236 adminRole = new UserRoles_23110236();
            adminRole.setRoleName("ADMIN");
            userRolesService.saveRole(adminRole);
            
            UserRoles_23110236 sellerRole = new UserRoles_23110236();
            sellerRole.setRoleName("SELLER");
            userRolesService.saveRole(sellerRole);
            
            UserRoles_23110236 userRole = new UserRoles_23110236();
            userRole.setRoleName("USER");
            userRolesService.saveRole(userRole);
        }
        
        // Initialize Sellers
        if (sellerService.getAllSellers().isEmpty()) {
            Seller_23110236 seller1 = new Seller_23110236();
            seller1.setSellerName("Cửa hàng điện tử TechStore");
            seller1.setImages("https://via.placeholder.com/300x200?text=TechStore");
            seller1.setStatus(1);
            sellerService.saveSeller(seller1);
            
            Seller_23110236 seller2 = new Seller_23110236();
            seller2.setSellerName("Thời trang Fashion Hub");
            seller2.setImages("https://via.placeholder.com/300x200?text=FashionHub");
            seller2.setStatus(1);
            sellerService.saveSeller(seller2);
            
            Seller_23110236 seller3 = new Seller_23110236();
            seller3.setSellerName("Đồ gia dụng HomeMart");
            seller3.setImages("https://via.placeholder.com/300x200?text=HomeMart");
            seller3.setStatus(1);
            sellerService.saveSeller(seller3);
        }
        
        // Initialize Categories
        if (categoryService.getAllCategories().isEmpty()) {
            Category_23110236 category1 = new Category_23110236();
            category1.setCategoryName("Điện tử");
            category1.setImages("https://via.placeholder.com/200x200?text=Electronics");
            category1.setStatus(1);
            categoryService.saveCategory(category1);
            
            Category_23110236 category2 = new Category_23110236();
            category2.setCategoryName("Thời trang");
            category2.setImages("https://via.placeholder.com/200x200?text=Fashion");
            category2.setStatus(1);
            categoryService.saveCategory(category2);
            
            Category_23110236 category3 = new Category_23110236();
            category3.setCategoryName("Đồ gia dụng");
            category3.setImages("https://via.placeholder.com/200x200?text=Home");
            category3.setStatus(1);
            categoryService.saveCategory(category3);
            
            Category_23110236 category4 = new Category_23110236();
            category4.setCategoryName("Sách");
            category4.setImages("https://via.placeholder.com/200x200?text=Books");
            category4.setStatus(1);
            categoryService.saveCategory(category4);
        }
        
        // Initialize Users
        if (userService.getAllUsers().isEmpty()) {
            // Admin user
            Users_23110236 admin = new Users_23110236();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setFullname("Administrator");
            admin.setPassword("123456");
            admin.setPhone("0123456789");
            admin.setStatus(1);
            admin.setCode("ADMIN001");
            admin.setUserRole(userRolesService.getRoleById(1).orElse(null));
            userService.saveUser(admin);
            
            // Seller user
            Users_23110236 seller = new Users_23110236();
            seller.setUsername("seller1");
            seller.setEmail("seller1@example.com");
            seller.setFullname("Nguyễn Văn Bán");
            seller.setPassword("123456");
            seller.setPhone("0987654321");
            seller.setStatus(1);
            seller.setCode("SELLER001");
            seller.setUserRole(userRolesService.getRoleById(2).orElse(null));
            seller.setSeller(sellerService.getSellerById(1).orElse(null));
            userService.saveUser(seller);
            
            // Regular user
            Users_23110236 user = new Users_23110236();
            user.setUsername("user1");
            user.setEmail("user1@example.com");
            user.setFullname("Nguyễn Văn Mua");
            user.setPassword("123456");
            user.setPhone("0555666777");
            user.setStatus(1);
            user.setCode("USER001");
            user.setUserRole(userRolesService.getRoleById(3).orElse(null));
            userService.saveUser(user);
        }
        
        // Initialize Products
        if (productService.getAllProducts().isEmpty()) {
            // Products for seller 1 (TechStore)
            Product_23110236 product1 = new Product_23110236();
            product1.setProductName("iPhone 15 Pro Max");
            product1.setProductCode(BigInteger.valueOf(123456789));
            product1.setCategory(categoryService.getCategoryById(1).orElse(null));
            product1.setDescription("iPhone 15 Pro Max với chip A17 Pro mạnh mẽ, camera 48MP và màn hình Super Retina XDR 6.7 inch");
            product1.setPrice(29990000.0);
            product1.setAmount(50);
            product1.setStock(50);
            product1.setImages("https://via.placeholder.com/400x400?text=iPhone+15+Pro+Max");
            product1.setWishlist(25);
            product1.setStatus(1);
            product1.setCreateDate(LocalDate.now());
            product1.setSeller(sellerService.getSellerById(1).orElse(null));
            productService.saveProduct(product1);
            
            Product_23110236 product2 = new Product_23110236();
            product2.setProductName("MacBook Air M2");
            product2.setProductCode(BigInteger.valueOf(123456790));
            product2.setCategory(categoryService.getCategoryById(1).orElse(null));
            product2.setDescription("MacBook Air với chip M2, màn hình Liquid Retina 13.6 inch, pin dùng cả ngày");
            product2.setPrice(24990000.0);
            product2.setAmount(30);
            product2.setStock(30);
            product2.setImages("https://via.placeholder.com/400x400?text=MacBook+Air+M2");
            product2.setWishlist(15);
            product2.setStatus(1);
            product2.setCreateDate(LocalDate.now());
            product2.setSeller(sellerService.getSellerById(1).orElse(null));
            productService.saveProduct(product2);
            
            // Products for seller 2 (Fashion Hub)
            Product_23110236 product3 = new Product_23110236();
            product3.setProductName("Áo thun nam cao cấp");
            product3.setProductCode(BigInteger.valueOf(123456791));
            product3.setCategory(categoryService.getCategoryById(2).orElse(null));
            product3.setDescription("Áo thun nam chất liệu cotton 100%, thiết kế đơn giản, thoải mái");
            product3.setPrice(299000.0);
            product3.setAmount(100);
            product3.setStock(100);
            product3.setImages("https://via.placeholder.com/400x400?text=Áo+thun+nam");
            product3.setWishlist(8);
            product3.setStatus(1);
            product3.setCreateDate(LocalDate.now());
            product3.setSeller(sellerService.getSellerById(2).orElse(null));
            productService.saveProduct(product3);
            
            Product_23110236 product4 = new Product_23110236();
            product4.setProductName("Quần jean nữ");
            product4.setProductCode(BigInteger.valueOf(123456792));
            product4.setCategory(categoryService.getCategoryById(2).orElse(null));
            product4.setDescription("Quần jean nữ kiểu dáng slim fit, chất liệu denim cao cấp");
            product4.setPrice(599000.0);
            product4.setAmount(80);
            product4.setStock(80);
            product4.setImages("https://via.placeholder.com/400x400?text=Quần+jean+nữ");
            product4.setWishlist(12);
            product4.setStatus(1);
            product4.setCreateDate(LocalDate.now());
            product4.setSeller(sellerService.getSellerById(2).orElse(null));
            productService.saveProduct(product4);
            
            // Products for seller 3 (HomeMart)
            Product_23110236 product5 = new Product_23110236();
            product5.setProductName("Máy lọc nước RO");
            product5.setProductCode(BigInteger.valueOf(123456793));
            product5.setCategory(categoryService.getCategoryById(3).orElse(null));
            product5.setDescription("Máy lọc nước RO công nghệ tiên tiến, lọc sạch 99.9% vi khuẩn và tạp chất");
            product5.setPrice(3500000.0);
            product5.setAmount(20);
            product5.setStock(20);
            product5.setImages("https://via.placeholder.com/400x400?text=Máy+lọc+nước+RO");
            product5.setWishlist(5);
            product5.setStatus(1);
            product5.setCreateDate(LocalDate.now());
            product5.setSeller(sellerService.getSellerById(3).orElse(null));
            productService.saveProduct(product5);
            
            Product_23110236 product6 = new Product_23110236();
            product6.setProductName("Bộ nồi inox cao cấp");
            product6.setProductCode(BigInteger.valueOf(123456794));
            product6.setCategory(categoryService.getCategoryById(3).orElse(null));
            product6.setDescription("Bộ nồi inox 304 cao cấp, đáy dày, chống dính, bền đẹp");
            product6.setPrice(1200000.0);
            product6.setAmount(40);
            product6.setStock(40);
            product6.setImages("https://via.placeholder.com/400x400?text=Bộ+nồi+inox");
            product6.setWishlist(7);
            product6.setStatus(1);
            product6.setCreateDate(LocalDate.now());
            product6.setSeller(sellerService.getSellerById(3).orElse(null));
            productService.saveProduct(product6);
        }
    }
}
