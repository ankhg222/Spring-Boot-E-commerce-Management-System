package vn.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.iot.model.Category_23110236;
import vn.iot.repository.CategoryRepository_23110236;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService_23110236 {
    
    @Autowired
    private CategoryRepository_23110236 categoryRepository;
    
    public List<Category_23110236> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    public Page<Category_23110236> getAllCategoriesPaged(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }
    
    public Optional<Category_23110236> getCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }
    
    public Category_23110236 saveCategory(Category_23110236 category) {
        return categoryRepository.save(category);
    }
    
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }
}
