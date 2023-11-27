package com.example.demo.Comments;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.example.demo.Posts.Posts;
import com.example.demo.Users.Users;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "comments_table")
public class Comments {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private final int CommentId;
    @Column
    private String Content;
    @Column
    private String Com_date;
    @Column
    private int CUser_id;
    @Column
    private int CPost_id;
    public Comments(int commentId, String content, String com_date) {
        CommentId = commentId;
        Content = content;
        Com_date = com_date;
        
    }
    
    @ManyToOne
    @JoinColumn(name="post_id")
    private Posts post=null;
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private Users user=null;


    public int getCommentId() {
        return CommentId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getCom_date() {
        return Com_date;
    }

    public void setCom_date(String com_date) {
        Com_date = com_date;
    }

    public int getCUser_id() {
        return CUser_id;
    }

    public void setCUser_id(int cUser_id) {
        CUser_id = cUser_id;
    }

    public int getCPost_id() {
        return CPost_id;
    }

    public void setCPost_id(int cPost_id) {
        CPost_id = cPost_id;
    }


    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Posts getPost() {
        return post;
    }

    public void setPost(Posts post) {
        this.post = post;
    }

    
}
