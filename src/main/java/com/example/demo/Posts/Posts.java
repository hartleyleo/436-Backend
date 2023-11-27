package com.example.demo.Posts;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.example.demo.Comments.Comments;
import com.example.demo.Products.Products;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="posts_table")
public class Posts {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private final int PostId;
    @Column
    private int Likes;
    @Column
    private String PLocation;
    @Column 
    private String Post_Date;
    @Column
    private int PProductId;
    public Posts(int postId, int likes, String pLocation, String post_Date, int pProductId) {
        PostId = postId;
        Likes = likes;
        PLocation = pLocation;
        Post_Date = post_Date;
        PProductId = pProductId;
    }

    @ManyToOne
    @JoinColumn(name="product_id")
    private Products product=null;

    @OneToMany(targetEntity=Comments.class, cascade = CascadeType.ALL, mappedBy = "post")
    private List<Comments> comments=new ArrayList<Comments>();

    public int getPostId() {
        return PostId;
    }

    public int getLikes() {
        return Likes;
    }

    public void setLikes(int likes) {
        Likes = likes;
    }

    public String getPLocation() {
        return PLocation;
    }

    public void setPLocation(String pLocation) {
        PLocation = pLocation;
    }

    public String getPost_Date() {
        return Post_Date;
    }

    public void setPost_Date(String post_Date) {
        Post_Date = post_Date;
    }

    public int getPProductId() {
        return PProductId;
    }

    public void setPProductId(int pProductId) {
        PProductId = pProductId;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public void addComments(Comments comment){
        this.comments.add(comment);
    }

    public void removeComments(Comments comment){
        this.comments.remove(comment);
    }
}
