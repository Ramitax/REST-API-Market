package com.api.market.repositories;

import com.api.market.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Integer> {

    public abstract Optional<ProductModel> getByIdProduct(Integer idProduct);

    public abstract Optional<ArrayList<ProductModel>> getByIdCategory(Integer idCategory);

    public abstract ArrayList<Optional<ProductModel>> getByNameContaining(String name);
}
