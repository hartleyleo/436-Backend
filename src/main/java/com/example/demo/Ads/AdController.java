package com.example.demo.Ads;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdController {
    @Autowired
    private AdService adService;
    
    @GetMapping("/Ads")
    public HashMap<Integer,Ads> getAds(){
        return this.adService.getAdList();
    }
    
    @PostMapping("/Ads")
    public HashMap<Integer,Ads> addAd(@RequestBody Ads ad){
        return this.adService.addAd(ad);
    }

    @PutMapping("/Ads")
    public HashMap<Integer,Ads> updateAd(@PathVariable int id, @RequestBody Ads adUpdate){
        return this.adService.updateAd(id, adUpdate);
    }

    @DeleteMapping("/Ads")
    public HashMap<Integer,Ads> deleteAd(@PathVariable int id){
        return this.adService.deleteAd(id);
    }
}
