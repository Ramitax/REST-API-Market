package com.api.market.controllers;

import com.api.market.models.ProductModel;
import com.api.market.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping // Devolver nada mas  nombre y productoID
    public Page<ProductModel> getAll(@PageableDefault(size = 10, page = 0) Pageable pageable){
        return productService.getAll(pageable);
    }

    @GetMapping("/")
    @ResponseBody
    public Optional<ArrayList<ProductModel>> getByIdCategory(@RequestParam Integer idCategory){
        return productService.getByIdCategory(idCategory);
    }

    @GetMapping("/{idProduct}")
    public Optional<ProductModel> getByIdProduct(@PathVariable("idProduct") Integer idProduct){
        return productService.getByIdProduct(idProduct);
    }

    @GetMapping("/name/{name}")
    public ArrayList<Optional<ProductModel>>  getByName(@PathVariable("name") String name){
        return productService.getByName(name);
    }

    @PutMapping("/{idProduct}/stock")
    public boolean updateStock(@RequestBody ProductModel product, @PathVariable(name = "idProduct") Integer idProduct){
        try {
            productService.updateStock(product, idProduct);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @PutMapping("/{idProduct}/price")
    public boolean updatePrice(@RequestBody ProductModel product, @PathVariable(name = "idProduct") Integer idProduct){
        try {
            productService.updatePrice(product, idProduct);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @PutMapping("/buy")
    public boolean buyProducts(@RequestBody ArrayList<ProductModel> products){
        try{
            productService.buyProducts(products);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @PostMapping()
    public ProductModel save(@RequestBody ProductModel product){
        return productService.save(product);
    }


    @DeleteMapping("/{idProduct}")
    public boolean delete(@PathVariable("idProduct") Integer idProduct){
        return productService.delete(idProduct);
    }
}
