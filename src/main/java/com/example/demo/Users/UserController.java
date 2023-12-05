package com.example.demo.Users;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController{

    @Autowired
    private UserService userService;

    
    @CrossOrigin(origins = "*")
    @GetMapping("/Users")
    public HashMap<Integer,Users> getUsers(){
        return this.userService.getUserList();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/Users")
    public HashMap<Integer,Users> addUser(@RequestBody Users user){
        return this.userService.addUser(user);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/Users/{id}")
    public HashMap<Integer,Users> updateUser(@PathVariable int id, @RequestBody Users userUpdate){
        return this.userService.updateUser(id, userUpdate);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/Users/{id}")
    public HashMap<Integer,Users> deleteUser(@PathVariable int id){
        return this.userService.deleteUser(id);
    }
}