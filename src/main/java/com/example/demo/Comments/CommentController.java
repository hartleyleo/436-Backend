package com.example.demo.Comments;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentServ;
    
    @CrossOrigin(origins = "*")
    @GetMapping("/Comments")
    public HashMap<Integer,Comments> getComments(){
        return this.commentServ.getCommentList();
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping("/Comments")
    public HashMap<Integer,Comments> addComment(@RequestBody Comments comment){
        return this.commentServ.addComment(comment);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/Comments/{id}")
    public HashMap<Integer,Comments> updateComment(@PathVariable int id, @RequestBody Comments commentUpdate){
        return this.commentServ.updateComment(id, commentUpdate);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/Comments/{id}")
    public HashMap<Integer,Comments> deleteComment(@PathVariable int id){
        return this.commentServ.deleteComment(id);
    }
}
