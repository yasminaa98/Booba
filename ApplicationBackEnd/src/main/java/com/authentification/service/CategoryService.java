package com.authentification.service;

import com.authentification.entities.Category;
import com.authentification.repositories.CategoryRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository ;
    /* public List<Category> getAllCategories() {
         return categoryRepository.findAll() ;
     }*/
     public Category getCategoryById(Long category_id) {
         try {
             return categoryRepository.findById(category_id).orElseThrow(() ->
                     new NotFoundException("Category not found !"));
         } catch (NotFoundException e) {
             throw new RuntimeException(e);
         }
     }
     public Category createCategory(Category category) {
         return categoryRepository.save(category) ;
     }
     public Category updateCategory(Long category_id,Category category) {
         Category existingCategory = getCategoryById(category_id);
         existingCategory.setName(category.getName()) ;
         return categoryRepository.save(existingCategory);
     }
    public void deleteCategory(Long id) {
        Category existingCategory = getCategoryById(id);
        categoryRepository.delete(existingCategory);
    }
}
