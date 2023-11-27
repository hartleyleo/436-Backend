package com.example.demo.Ads;

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

import com.example.demo.Products.Products;
import com.example.demo.Users.Users;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="ads_table")
public class Ads {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private final int AdId;
    @Column
    private int AProductId;
    @Column
    private int AUserId;
    @Column
    private int clickCounter;
    @Column
    private String Text;
    @Column
    private String MediaLink;

    public Ads(int adId, int aProductId, int aUserId, int clickCounter, String text, String mediaLink) {
        AdId = adId;
        AProductId = aProductId;
        AUserId = aUserId;
        this.clickCounter = clickCounter;
        Text = text;
        MediaLink = mediaLink;
    }

    @ManyToOne
    @JoinColumn(name="user_id")
    private Users user=null;
    
    @ManyToOne
    @JoinColumn(name="product_id")
    private Products product=null;

    public int getAdId() {
        return AdId;
    }

    public int getAProductId() {
        return AProductId;
    }

    public void setAProductId(int aProductId) {
        AProductId = aProductId;
    }

    public int getAUserId() {
        return AUserId;
    }

    public void setAUserId(int aUserId) {
        AUserId = aUserId;
    }

    public int getClickCounter() {
        return clickCounter;
    }

    public void setClickCounter(int clickCounter) {
        this.clickCounter = clickCounter;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getMediaLink() {
        return MediaLink;
    }

    public void setMediaLink(String mediaLink) {
        MediaLink = mediaLink;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    

}
