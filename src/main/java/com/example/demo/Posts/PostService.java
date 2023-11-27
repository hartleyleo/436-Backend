package com.example.demo.Posts;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

@Repository
public interface PostService {
    public HashMap<Integer,Posts> getPostList();
    
    public HashMap<Integer,Posts> addPost(Posts post);

    public HashMap<Integer,Posts> updatePost(int id, Posts postUpdate);

    public HashMap<Integer,Posts> deletePost(int id);
}
