package com.api.market.controllers;

import com.api.market.models.CategoryModel;
import com.api.market.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ArrayList<CategoryModel> get(){
        return categoryService.getAll();
    }

    @PostMapping
    public CategoryModel save(@RequestBody CategoryModel category){
        return categoryService.save(category);
    }

    @PutMapping("/{idCategory}")
    public boolean update(@RequestBody CategoryModel category, @PathVariable("idCategory") Integer idCategory){
        try{
            categoryService.update(category,idCategory);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @DeleteMapping("/{idCategory}")
    public boolean delete(@PathVariable("idCategory") Integer idCategory){
        return categoryService.delete(idCategory);
    }
}
