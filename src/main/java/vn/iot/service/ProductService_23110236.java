package vn.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.iot.model.Product_23110236;
import vn.iot.model.Seller_23110236;
import vn.iot.repository.ProductRepository_23110236;
import vn.iot.repository.SellerRepository_23110236;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService_23110236 {
    
    @Autowired
    private ProductRepository_23110236 productRepository;
    
    @Autowired
    private SellerRepository_23110236 sellerRepository;
    
    public List<Product_23110236> getAllProducts() {
        return productRepository.findAll();
    }
    
    public List<Product_23110236> getProductsBySellerId(Integer sellerId) {
        return productRepository.findBySellerId(sellerId);
    }
    
    public Optional<Product_23110236> getProductById(Integer id) {
        return productRepository.findById(id);
    }
    
    public Product_23110236 getProductByProductId(Integer productId) {
        return productRepository.findByProductId(productId);
    }
    
    public Product_23110236 saveProduct(Product_23110236 product) {
        return productRepository.save(product);
    }
    
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
    
    public Page<Product_23110236> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    
    public Page<Product_23110236> getProductsByCategory(String categoryName, Pageable pageable) {
        return productRepository.findByCategoryCategoryName(categoryName, pageable);
    }
    
    public Product_23110236 getProductById(Long id) {
        Optional<Product_23110236> product = productRepository.findById(id.intValue());
        return product.orElse(null);
    }
    
    public List<Product_23110236> getProductsBySeller(Integer sellerId) {
        return productRepository.findBySellerId(sellerId);
    }
    
    public Seller_23110236 getSellerById(Integer sellerId) {
        Optional<Seller_23110236> seller = sellerRepository.findById(sellerId);
        return seller.orElse(null);
    }
    
    public List<Seller_23110236> getSellersWithProducts() {
        return sellerRepository.findAll();
    }
}
