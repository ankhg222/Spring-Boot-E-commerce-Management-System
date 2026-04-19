package vn.iot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "Product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product_23110236 {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Integer productId;
    
    @Column(name = "productName", nullable = false, length = 200)
    private String productName;
    
    @Column(name = "productCode", nullable = false)
    private BigInteger productCode;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
    private Category_23110236 category;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "price", nullable = false)
    private Double price;
    
    @Column(name = "amount", nullable = false)
    private Integer amount;
    
    @Column(name = "stock", nullable = false)
    private Integer stock;
    
    @Column(name = "images", length = 255)
    private String images;
    
    @Column(name = "wishlist")
    private Integer wishlist;
    
    @Column(name = "status", nullable = false)
    private Integer status;
    
    @Column(name = "createDate")
    private LocalDate createDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sellerId", referencedColumnName = "sellerId")
    private Seller_23110236 seller;
}
