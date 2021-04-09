package com.api.market.controllers;

import com.api.market.models.CategoryModel;
import com.api.market.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ArrayList<CategoryModel>> get(){
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }

    @PutMapping("/{idCategory}")
    public ResponseEntity<Boolean> update(@RequestBody CategoryModel category, @PathVariable("idCategory") Integer idCategory){
        try{
            categoryService.update(category,idCategory);
            return new ResponseEntity<>(true,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CategoryModel> save(@RequestBody CategoryModel category){
        if (categoryService.save(category) != null){
            return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(categoryService.save(category), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idCategory}")
    public ResponseEntity<Boolean> delete(@PathVariable("idCategory") Integer idCategory){
        try{
            categoryService.delete(idCategory);
            return new ResponseEntity<>(true,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
