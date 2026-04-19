package vn.iot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category_23110236 {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId")
    private Integer categoryId;
    
    @Column(name = "categoryName", nullable = false, length = 100)
    private String categoryName;
    
    @Column(name = "images", length = 255)
    private String images;
    
    @Column(name = "status")
    private Integer status;
}
