package com.example.demo.Ads;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

@Repository
public interface AdService {
    public HashMap<Integer,Ads> getAdList();

    public HashMap<Integer,Ads> addAd(Ads ad);

    public HashMap<Integer,Ads> updateAd(int id, Ads adUpdate);

    public HashMap<Integer,Ads> deleteAd(int id);
}
