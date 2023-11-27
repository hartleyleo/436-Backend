package com.example.demo.Comments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DBUtil;
import com.example.demo.Posts.PostService;
import com.example.demo.Posts.Posts;
import com.example.demo.Users.UserService;
import com.example.demo.Users.Users;



@Service
public class CommentImpl implements CommentService{
    @Autowired
    static HashMap<Integer, Comments> comments = new HashMap<Integer, Comments>();

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;


    Connection conn;

    public CommentImpl() throws SQLException {
        conn = DBUtil.getConnection();
    }

    @Override
    public HashMap<Integer, Comments> getCommentList() {
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM COMMENTS");
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                Posts cur_post = postService.getPostList().get(res.getInt(4));
                Users curr_user = userService.getUserList().get(res.getInt(5));
                Comments newComment = new Comments(
                                    res.getInt(1), 
                                    res.getString(2), 
                                    res.getString(3));
                newComment.setUser(curr_user);
                newComment.setPost(cur_post);
                comments.put(res.getInt(1),newComment);
            }

            for(int i=0;i<comments.size();i++){
                Posts curr_post = postService.getPostList().get(comments.get(i).getCPost_id());
                Users curr_user = userService.getUserList().get(comments.get(i).getCUser_id());
                //System.out.println(curr_user+" : "+userService.getUserList().get(i).getUsername());
                curr_post.addComments(comments.get(i));
                curr_user.addComments(comments.get(i));
                //System.out.println(curr_user.getProducts());
                //products.get(i).setUser(curr_user);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public HashMap<Integer, Comments> addComment(Comments comment) {
        comments.put(comments.size(), comment);
        Users co_user = userService.getUserList().get(comment.getCUser_id());
        Posts co_post = postService.getPostList().get(comment.getCPost_id());
        co_user.addComments(comment);
        co_post.addComments(comment);
        return comments;
    }

    @Override
    public HashMap<Integer, Comments> updateComment(int id, Comments commentUpdate) {
        if(comments.containsKey(id)){
            Comments curr_comment=comments.get(id);
            curr_comment.setCom_date(commentUpdate.getCom_date());
            curr_comment.setContent(commentUpdate.getContent());
        }
        return comments;
    }

    @Override
    public HashMap<Integer, Comments> deleteComment(int id) {
        if(comments.containsKey(id)){
            Posts co_post = postService.getPostList().get(comments.get(id).getCPost_id());
            Users co_user = userService.getUserList().get(comments.get(id).getCUser_id());
            co_post.removeComments(comments.get(id));
            co_user.removeComments(comments.get(id));
            comments.remove(id);
        }
        return comments;
    }
    
}
