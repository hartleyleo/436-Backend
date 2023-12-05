package com.example.demo.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PostController {
 
    @Autowired
    private PostService postServ;
    
    @CrossOrigin(origins = "*")
    @GetMapping("/Posts")
    public HashMap<Integer,Posts> getPosts(){
        return this.postServ.getPostList();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/Posts")
    public HashMap<Integer,Posts> addPost(@RequestBody Posts post){
        return this.postServ.addPost(post);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/Posts")
    public HashMap<Integer,Posts> updatePost(@PathVariable int id, @RequestBody Posts postUpdate){
        return this.postServ.updatePost(id, postUpdate);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/Posts")
    public HashMap<Integer,Posts> deletePost(@PathVariable int id){
        return this.postServ.deletePost(id);
    }
}
