package vn.iot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iot.model.Category_23110236;

@Repository
public interface CategoryRepository_23110236 extends JpaRepository<Category_23110236, Integer> {
    
    Page<Category_23110236> findAll(Pageable pageable);
}
