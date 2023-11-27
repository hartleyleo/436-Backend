package com.example.demo.Users;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

@Repository
public interface UserService {
    public HashMap<Integer,Users> getUserList();

    public HashMap<Integer,Users> addUser(Users user);

    public HashMap<Integer,Users> updateUser(int id, Users userUpdate);

    public HashMap<Integer,Users> deleteUser(int id);
}