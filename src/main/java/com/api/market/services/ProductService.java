package com.api.market.services;

import com.api.market.models.ProductModel;
import com.api.market.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Page<ProductModel> getAll(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    public Optional<ProductModel> getByIdProduct(Integer idProduct){
        return productRepository.getByIdProduct(idProduct);
    }

    public Optional<ArrayList<ProductModel>> getByIdCategory(Integer idCategory){
        return productRepository.getByIdCategory(idCategory);
    }

    public ArrayList<Optional<ProductModel>>  getByName(String name){
        return  productRepository.getByNameContaining(name);
    }

    public ProductModel save (ProductModel product){
        return productRepository.save(product);
    }

    public void updateStock (ProductModel product, Integer idProduct){
        Optional<ProductModel> preProduct = productRepository.getByIdProduct(idProduct);
        preProduct.get().setStock(product.getStock());
        productRepository.save(preProduct.get());
    }

    public void updatePrice (ProductModel product, Integer idProduct){
        Optional<ProductModel> preProduct = productRepository.getByIdProduct(idProduct);
        preProduct.get().setPrice(product.getPrice());
        productRepository.save(preProduct.get());
    }

    public void buyProducts(ArrayList<ProductModel> products){
        products.forEach(product -> {
            Optional<ProductModel> preProduct = productRepository.getByIdProduct(product.getIdProduct());
            Integer newStock = preProduct.get().getStock() - 1;
            preProduct.get().setStock(newStock);
            productRepository.save(preProduct.get());
        }
        );
    }

    public boolean delete (Integer idProduct){
        try{
            productRepository.deleteById(idProduct);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
