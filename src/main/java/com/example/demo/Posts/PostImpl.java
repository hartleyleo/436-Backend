package com.example.demo.Posts;
import com.example.demo.Products.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DBUtil;

@Service
public class PostImpl implements PostService{
    @Autowired
    static HashMap<Integer, Posts> posts = new HashMap<Integer, Posts>();

    @Autowired
    ProductService productService;

    Connection conn;

    public PostImpl() throws SQLException {
        conn = DBUtil.getConnection();
    }

    @Override
    public HashMap<Integer, Posts> getPostList() {
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM POSTS");
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                Products curr_product = productService.getProductList().get(res.getInt(5));
                //System.out.println(res.toString());
                Posts newPost = new Posts(
                        res.getInt(1),
                        res.getInt(2),
                        res.getString(3),
                        res.getString(4),
                        res.getInt(5));
                newPost.setProduct(curr_product);
                posts.put(res.getInt(1), newPost);
                //userService.getUserList().get(res.getInt(5)).addProducts(newProduct);
                //System.out.println(userService.getUserList().get(res.getInt(5)).getProducts());
                
            }

            for(int i=0;i<posts.size();i++){
                Products curr_product = productService.getProductList().get(posts.get(i).getPostId());
                //System.out.println(curr_user+" : "+userService.getUserList().get(i).getUsername());
                curr_product.addPosts(posts.get(i));
                //System.out.println(curr_user.getProducts());
                //products.get(i).setUser(curr_user);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public HashMap<Integer, Posts> addPost(Posts post) {
        posts.put(posts.size(), post);
        Products po_prod = productService.getProductList().get(post.getPProductId());
        po_prod.addPosts(post);
        return posts;
    }

    @Override
    public HashMap<Integer, Posts> updatePost(int id, Posts postUpdate) {
        if(posts.containsKey(id)){
            Posts curr_post = posts.get(id);
            curr_post.setLikes(postUpdate.getLikes());
            curr_post.setPLocation(postUpdate.getPLocation());
            curr_post.setPost_Date(postUpdate.getPost_Date());
        }
        return posts;
    }

    @Override
    public HashMap<Integer, Posts> deletePost(int id) {
        if(posts.containsKey(id)){
            Products po_prod = productService.getProductList().get(posts.get(id).getPProductId());
        po_prod.removePosts(posts.get(id));
        posts.remove(id);
        }
        return posts;
    }
    
}
