package com.example.demo.Ads;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DBUtil;
import com.example.demo.Products.ProductService;
import com.example.demo.Products.Products;
import com.example.demo.Users.UserService;
import com.example.demo.Users.Users;


@Service
public class AdImpl implements AdService{
    @Autowired
    static HashMap<Integer, Ads> ads  = new HashMap<Integer, Ads>();

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;


    Connection conn;

    public AdImpl() throws SQLException {
        conn = DBUtil.getConnection();
    }

    @Override
    public HashMap<Integer, Ads> getAdList() {
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ADS");
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                Products curr_product = productService.getProductList().get(res.getInt(1));
                Users curr_user = userService.getUserList().get(res.getInt(2));
                Ads newAd = new Ads(
                            res.getInt(3), 
                            res.getInt(1),
                            res.getInt(2), 
                            res.getInt(4), 
                            res.getString(5), 
                            res.getString(6));
                newAd.setUser(curr_user);
                newAd.setProduct(curr_product);
                ads.put(res.getInt(3),newAd);
            }

            for(int i=0;i<ads.size();i++){
                Products curr_product = productService.getProductList().get(ads.get(i).getAProductId());
                Users curr_user = userService.getUserList().get(ads.get(i).getAUserId());
                //System.out.println(curr_user+" : "+userService.getUserList().get(i).getUsername());
                curr_product.addAds(ads.get(i));
                curr_user.addAds(ads.get(i));
                //System.out.println(curr_user.getProducts());
                //products.get(i).setUser(curr_user);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return ads;
    }

    @Override
    public HashMap<Integer, Ads> addAd(Ads ad) {
        ads.put(ads.size(), ad);
        Users ad_user = userService.getUserList().get(ad.getAUserId());
        Products ad_product = productService.getProductList().get(ad.getAProductId());
        ad_user.addAds(ad);
        ad_product.addAds(ad);
        return ads;
    }

    @Override
    public HashMap<Integer, Ads> updateAd(int id, Ads adUpdate) {
        if(ads.containsKey(id)){
            Ads curr_ad=ads.get(id);
            curr_ad.setClickCounter(adUpdate.getClickCounter());
            curr_ad.setText(adUpdate.getText());
            curr_ad.setMediaLink(adUpdate.getMediaLink());
        }
        return ads;
    }

    @Override
    public HashMap<Integer, Ads> deleteAd(int id) {
       if(ads.containsKey(id)){
        Products ad_product = productService.getProductList().get(ads.get(id).getAProductId());
        Users ad_user = userService.getUserList().get(ads.get(id).getAUserId());
        ad_product.removeAds(ads.get(id));
        ad_user.removeAds(ads.get(id));
        ads.remove(id);
       }
        return ads;
    }
    
}
