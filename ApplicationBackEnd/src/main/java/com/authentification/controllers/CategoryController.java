package com.authentification.controllers;

import com.authentification.entities.Category;
import com.authentification.entities.User;
import com.authentification.payload.MessageResponse;
import com.authentification.service.CategoryService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService ;

    // Get all categories :

    @GetMapping("/get-all-categories")
    public ResponseEntity<List<Category>> getAllCategories() {
      List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories) ;
    }

    // Get categories by id :

    @GetMapping("/{category_id}/get-by-id")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long category_id){
        Optional<Category>category = categoryService.getCategoryById(category_id);
        if (category.isPresent()) {
            return ResponseEntity.ok(category.get()) ;
        } else {
            return ResponseEntity.notFound().build() ;
        }
    }

    // Create a category

    @PostMapping("/create-category")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
        Category newCategory = categoryService.createCategory(category) ;
        return ResponseEntity.ok(newCategory) ;

    }

    // Update a category :
     @PutMapping("/{category_id}/update-category")
    public ResponseEntity<?> updateCategory(@PathVariable ("category_id") Long category_id , @Valid @RequestBody Category category) {

        ResponseEntity<MessageResponse> categoryModified = categoryService.updateCategory(category_id,category) ;

        if (categoryModified == null) {
            return ResponseEntity.ok(new MessageResponse("Not modified !")) ;
        }
        return ResponseEntity.ok(new MessageResponse("Modified successfully !")) ;
    }

     // Delete a category :


    @DeleteMapping("/{category_id}/delete-category")
    public ResponseEntity<String> deleteCategory(@PathVariable("category_id") Long category_id) throws NotFoundException {
        boolean categoryExists = categoryService.categoryExists(category_id);
        if (categoryExists == false) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found with the id " + category_id);
        }
        categoryService.deleteCategory(category_id);
        return ResponseEntity.ok("category has been deleted with id " + category_id);

    }
}
