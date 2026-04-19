package vn.iot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users_23110236 {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Integer userId;
    
    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username;
    
    @Column(name = "email", length = 100)
    private String email;
    
    @Column(name = "fullname", length = 100)
    private String fullname;
    
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    
    @Column(name = "images", length = 255)
    private String images;
    
    @Column(name = "phone", length = 20)
    private String phone;
    
    @Column(name = "status")
    private Integer status;
    
    @Column(name = "code", length = 10)
    private String code;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId", referencedColumnName = "roleId")
    private UserRoles_23110236 userRole;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sellerId", referencedColumnName = "sellerId")
    private Seller_23110236 seller;
}
