package com.example.demo.Comments;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

@Repository
public interface CommentService {
    public HashMap<Integer,Comments> getCommentList();
    
    public HashMap<Integer,Comments> addComment(Comments comment);

    public HashMap<Integer,Comments> updateComment(int id, Comments commentUpdate);

    public HashMap<Integer,Comments> deleteComment(int id);
}
