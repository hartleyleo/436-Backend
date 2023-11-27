package com.example.demo.Products;

import org.springframework.stereotype.Repository;


import java.util.HashMap;

@Repository
public interface ProductService {
    public HashMap<Integer,Products> getProductList();

    public HashMap<Integer,Products> addProduct(Products user);

    public HashMap<Integer,Products> updateProduct(int id, Products userUpdate);

    public HashMap<Integer,Products> deleteProduct(int id);
}
