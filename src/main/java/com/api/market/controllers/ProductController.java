package com.api.market.controllers;

import com.api.market.models.ProductModel;
import com.api.market.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductModel>> getAll(@PageableDefault(size = 10, page = 0) Pageable pageable){
        return new ResponseEntity<>(productService.getAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<Optional<ArrayList<ProductModel>>> getByIdCategory(@RequestParam Integer idCategory){
        Optional<ArrayList<ProductModel>> response = productService.getByIdCategory(idCategory);
        if (response.isPresent()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<Optional<ProductModel>> getByIdProduct(@PathVariable("idProduct") Integer idProduct){
        Optional<ProductModel> response = productService.getByIdProduct(idProduct);
        if (response.isPresent()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ArrayList<Optional<ProductModel>>>  getByName(@PathVariable("name") String name){
        ArrayList<Optional<ProductModel>> response = productService.getByName(name.toLowerCase());
        if (!response.isEmpty()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idProduct}/stock")
    public ResponseEntity<Boolean> updateStock(@RequestBody ProductModel product, @PathVariable(name = "idProduct") Integer idProduct){
        try{
            productService.updateStock(product, idProduct);
            return new ResponseEntity<>(true,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{idProduct}/price")
    public ResponseEntity<Boolean> updatePrice(@RequestBody ProductModel product, @PathVariable(name = "idProduct") Integer idProduct){
        try {
            productService.updatePrice(product, idProduct);
            return new ResponseEntity<>(true,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/buy")
    public ResponseEntity<Boolean>  buyProducts(@RequestBody ArrayList<ProductModel> products){
        try{
            productService.buyProducts(products);
            return new ResponseEntity<>(true,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<ProductModel> save(@RequestBody ProductModel product){
        ProductModel response = productService.save(product);
        if ( response != null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idProduct}")
    public ResponseEntity<Boolean> delete(@PathVariable("idProduct") Integer idProduct){
        try{
            productService.delete(idProduct);
            return new ResponseEntity<>(true,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
