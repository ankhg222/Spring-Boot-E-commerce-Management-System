package vn.iot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.iot.model.Product_23110236;

import java.util.List;

@Repository
public interface ProductRepository_23110236 extends JpaRepository<Product_23110236, Integer> {
    
    @Query("SELECT p FROM Product_23110236 p WHERE p.seller.sellerId = :sellerId")
    List<Product_23110236> findBySellerId(@Param("sellerId") Integer sellerId);
    
    @Query("SELECT p FROM Product_23110236 p WHERE p.productId = :productId")
    Product_23110236 findByProductId(@Param("productId") Integer productId);
    
    @Query("SELECT p FROM Product_23110236 p WHERE p.category.categoryName = :categoryName")
    Page<Product_23110236> findByCategoryCategoryName(@Param("categoryName") String categoryName, Pageable pageable);
}
