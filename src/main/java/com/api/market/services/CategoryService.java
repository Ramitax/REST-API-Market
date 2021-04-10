package com.api.market.services;

import com.api.market.models.CategoryModel;
import com.api.market.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public ArrayList<CategoryModel> getAll(){
        return (ArrayList<CategoryModel>) categoryRepository.findAll();
    }

    public CategoryModel save(CategoryModel category){
        return categoryRepository.save(category);
    }

    public String mensaje (){
        return "Hola";
    }

    public boolean update(CategoryModel category, Integer idCategory){
        Optional<CategoryModel> preCategory = categoryRepository.findByIdCategory(idCategory);
        if (preCategory.isPresent()){
            preCategory.get().setName(category.getName());
            categoryRepository.save(preCategory.get());
            return true;
        } else {
            return false;
        }
    }

    public boolean delete (Integer idCategory){
        if (categoryRepository.findById(idCategory).isPresent()) {
            categoryRepository.deleteById(idCategory);
            return true;
        } else {
            return false;
        }
    }
}
