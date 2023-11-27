package com.example.demo.Users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.example.demo.Ads.Ads;
import com.example.demo.Comments.Comments;
import com.example.demo.Products.Products;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "users_table")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final int UserID;
	@Column
	private String Username;
	@Column
	private String UPassword;
	@Column
	private String Email;
	@Column
	private String Gender;
	@Column
	private int Age;
	@Column
	private String First_name;
	@Column
	private String Last_name;

	public Users(int userID, String username, String uPassword, String email, String gender, int age, String first_name,
			String last_name) {
		UserID = userID;
		Username = username;
		UPassword = uPassword;
		Email = email;
		Gender = gender;
		Age = age;
		First_name = first_name;
		Last_name = last_name;
	}

	@OneToMany(targetEntity = Products.class, cascade = CascadeType.ALL, mappedBy = "user")
	// @JoinColumn(name = "up_fk",referencedColumnName = "UserID")
	private List<Products> products = new ArrayList<Products>();

	@OneToMany(targetEntity = Comments.class, cascade = CascadeType.ALL, mappedBy = "user")
	private List<Comments> comments = new ArrayList<Comments>();

	@OneToMany(targetEntity = Ads.class, cascade = CascadeType.ALL, mappedBy = "user")
	private List<Ads> ads = new ArrayList<Ads>();

	public void addProducts(Products product) {
		products.add(product);
	}

	public void removeProducts(Products product){
		products.remove(product);
	}

	public List<Products> getProducts() {
		return products;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getUPassword() {
		return UPassword;
	}

	public void setUPassword(String uPassword) {
		UPassword = uPassword;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public String getFirst_name() {
		return First_name;
	}

	public void setFirst_name(String first_name) {
		First_name = first_name;
	}

	public String getLast_name() {
		return Last_name;
	}

	public void setLast_name(String last_name) {
		Last_name = last_name;
	}

	public int getUserID() {
		return UserID;
	}

	public void addComments(Comments comment) {
		this.comments.add(comment);
	}

	public void removeComments(Comments comment){
		this.comments.remove(comment);
	}

	public void addAds(Ads ad){
		this.ads.add(ad);
	}

	public void removeAds(Ads ad){
		this.ads.remove(ad);
	}

}