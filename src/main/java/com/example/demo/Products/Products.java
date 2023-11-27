package com.example.demo.Products;



import java.util.ArrayList;

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

import com.example.demo.Users.Users;
import com.example.demo.Ads.Ads;
import com.example.demo.Posts.Posts;

import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="products_table")
public class Products {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private final int ProductId;
    @Column
    private String PStatus;
    @Column
    private String PType;
    @Column
    private int Price;
    @Column
    private int PUserId;
    @Column
    private String Desc;

    

    public Products(int productId, String pStatus, String pType, int price, int userId, String desc) {
        ProductId = productId;
        PStatus = pStatus;
        PType = pType;
        Price = price;
        PUserId = userId;
        Desc = desc;
    }
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private Users user=null;

    @OneToMany(targetEntity=Posts.class, cascade = CascadeType.ALL, mappedBy = "product")
    private List<Posts> posts=new ArrayList<Posts>();

    @OneToMany(targetEntity=Ads.class, cascade = CascadeType.ALL, mappedBy = "product")
	private List<Ads> ads=new ArrayList<Ads>();

    public void addPosts(Posts post){
        posts.add(post);
    }

    public void removePosts(Posts post){
        posts.remove(post);
    }

    public int getPUserId() {
        return PUserId;
    }

    public Users getUser() {
        return user;
    }

    public String getPStatus() {
        return PStatus;
    }

    public void setPStatus(String pStatus) {
        PStatus = pStatus;
    }

    public String getPType() {
        return PType;
    }

    public void setPType(String pType) {
        PType = pType;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }


    

    public int getProductId() {
        return ProductId;
    }


    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
    public void addAds(Ads ad){
        this.ads.add(ad);
    }

    public void removeAds(Ads ad){
        this.ads.remove(ad);
    }
}
