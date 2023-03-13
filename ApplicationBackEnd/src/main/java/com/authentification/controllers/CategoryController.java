package com.authentification.controllers;

import com.authentification.entities.Category;
import com.authentification.service.CategoryService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService ;

    // Get all categories :

    /*@GetMapping("")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }*/

    // Get categories by id :

    @GetMapping("")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long category_id) throws NotFoundException {
        Category category = categoryService.getCategoryById(category_id) ;
        return ResponseEntity.ok(category) ;
    }

    // Create a category

    @PostMapping("")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category) ;
        return ResponseEntity.created(URI.create("/categories/"+createdCategory.getCategory_id())).body(createdCategory) ;

    }

    // Update a category :
     @PutMapping("")
    public ResponseEntity<Category> updateCategory(@PathVariable Long category_id , @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(category_id,category) ;
        return ResponseEntity.ok(updatedCategory) ;
     }

     // Delete a category :

     @DeleteMapping("")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long category_id) {
        categoryService.deleteCategory(category_id) ;
        return ResponseEntity.noContent().build() ;
     }


}
