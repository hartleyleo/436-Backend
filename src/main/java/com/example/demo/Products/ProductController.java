package com.example.demo.Products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import com.example.demo.Products.ProductService;

import java.util.HashMap;

@RestController
public class ProductController {
    
    @Autowired
    private ProductService productServ;

    @CrossOrigin(origins = "*")
    @GetMapping("/Products")
    public HashMap<Integer,Products> getProducts(){
        return this.productServ.getProductList();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/Products")
    public HashMap<Integer,Products> addProduct(@RequestBody Products product){
        return this.productServ.addProduct(product);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/Products/{id}")
    public HashMap<Integer,Products> updateProduct(@PathVariable int id, @RequestBody Products productUpdate){
        return this.productServ.updateProduct(id, productUpdate);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/Products/{id}")
    public HashMap<Integer,Products> deleteProduct(@PathVariable int id){
        return this.productServ.deleteProduct(id);
    }
}