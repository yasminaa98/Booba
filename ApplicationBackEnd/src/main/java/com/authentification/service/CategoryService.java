package com.authentification.service;

import com.authentification.entities.Category;
import com.authentification.entities.User;
import com.authentification.payload.MessageResponse;
import com.authentification.repositories.CategoryRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long category_id) {
        return categoryRepository.findById(category_id);
    }

    public boolean categoryExists(Long category_id) {
        Optional<Category> categoryOptional = categoryRepository.findById(category_id);
        return categoryOptional.isPresent();
    }
    public Category createCategory(Category category) {return categoryRepository.save(category);}

    public ResponseEntity<MessageResponse> updateCategory(Long category_id, Category category) {

            Category existentCategory = categoryRepository.findById(category_id).orElse(null);

            if (existentCategory == null) {
                return ResponseEntity.badRequest().body(new MessageResponse("Not found"));
            }
            existentCategory.setName(category.getName());
            try {
                categoryRepository.save(existentCategory);
                return ResponseEntity.ok(new MessageResponse("modified successfully!"));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(new MessageResponse("Not modified"));
            }
    }

    public ResponseEntity<String> deleteCategory(Long category_id) throws NotFoundException {
        Optional<Category> existentCategory = categoryRepository.findById(category_id);
        if (existentCategory.isEmpty()) {
            throw new NotFoundException("Category not found with the provided id");
        }
        categoryRepository.deleteById(category_id);
        return ResponseEntity.ok("category has been deleted with id" + category_id);
        }
    }

