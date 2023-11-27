package com.example.demo.Products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import com.example.demo.DBUtil;
import com.example.demo.Users.UserService;
import com.example.demo.Users.Users;

import java.sql.*;

import java.util.HashMap;

@Service
public class ProductImpl implements ProductService {
    @Autowired
    static HashMap<Integer, Products> products = new HashMap<Integer, Products>();

    @Autowired
    UserService userService;

    Connection conn;

    public ProductImpl() throws SQLException {
        conn = DBUtil.getConnection();
    }

    @Override
    public HashMap<Integer, Products> getProductList() {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PRODUCTS");
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                Users curr_user = userService.getUserList().get(res.getInt(5));
                Products newProduct = new Products(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getInt(4),
                        res.getInt(5),
                         res.getString(6));
                newProduct.setUser(curr_user);
                products.put(res.getInt(1), newProduct);
                //userService.getUserList().get(res.getInt(5)).addProducts(newProduct);
                //System.out.println(userService.getUserList().get(res.getInt(5)).getProducts());
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        for(int i=0;i<products.size();i++){
            Users curr_user = userService.getUserList().get(products.get(i).getPUserId());
            //System.out.println(curr_user+" : "+userService.getUserList().get(i).getUsername());
            curr_user.addProducts(products.get(i));
            //System.out.println(curr_user.getProducts());
            //products.get(i).setUser(curr_user);
        }
        return products;
    }

    @Override
    public HashMap<Integer, Products> addProduct(Products product) {
        products.put(products.size(), product);
        Users pro_user = userService.getUserList().get(product.getPUserId());
        pro_user.addProducts(product);
        return products;
    }

    @Override
    public HashMap<Integer, Products> updateProduct(int id, Products productUpdate) {
        if(products.containsKey(id)){
            Products curr_product = products.get(id);
        curr_product.setPStatus(productUpdate.getPStatus());
        curr_product.setDesc(productUpdate.getDesc());
        curr_product.setPType(productUpdate.getPType());
        curr_product.setPrice(id);
        
        }
        return products;
    }

    @Override
    public HashMap<Integer, Products> deleteProduct(int id) {
        if(products.containsKey(id)){
            Users pro_user = userService.getUserList().get(products.get(id).getPUserId());
            pro_user.removeProducts(products.get(id));
            products.remove(id);
        }
        return products;
    }
}