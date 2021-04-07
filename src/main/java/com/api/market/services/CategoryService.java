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

    public void update(CategoryModel category, Integer idCategory){
        Optional<CategoryModel> preCategory = categoryRepository.findByIdCategory(idCategory);
        preCategory.get().setName(category.getName());
        categoryRepository.save(preCategory.get());
    }

    public boolean delete (Integer idCategory){
        try{
            categoryRepository.deleteById(idCategory);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
