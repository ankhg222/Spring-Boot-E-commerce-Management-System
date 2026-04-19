package vn.iot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart_23110236 {
    
    @Id
    @Column(name = "cartId", length = 50)
    private String cartId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Users_23110236 user;
    
    @Column(name = "buyDate")
    private LocalDateTime buyDate;
    
    @Column(name = "status", nullable = false)
    private Integer status;
    
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem_23110236> cartItems;
}
