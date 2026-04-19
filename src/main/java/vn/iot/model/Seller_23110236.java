package vn.iot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Seller")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seller_23110236 {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sellerId")
    private Integer sellerId;
    
    @Column(name = "sellerName", nullable = false, length = 100)
    private String sellerName;
    
    @Column(name = "images", length = 255)
    private String images;
    
    @Column(name = "status")
    private Integer status;
}
