package com.example.demo.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;


import com.example.demo.DBUtil;

import java.sql.*;
import java.util.HashMap;

@Service
public class UserImpl implements UserService {

    @Autowired
    static HashMap<Integer, Users> users = new HashMap<Integer, Users>();

    Connection conn;

    public UserImpl() throws SQLException {
        conn = DBUtil.getConnection();
    }

    @Override
    public HashMap<Integer, Users> getUserList() {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM USERS");
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                users.put(res.getInt(1), new Users(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getInt(6),
                        res.getString(7),
                        res.getString(8)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // System.out.println("The current size is: " + users.size());
        return users;
    }

    @Override
    public HashMap<Integer, Users> addUser(Users user) {
        users.put(users.size(), user);
        return users;
    }

    @Override
    public HashMap<Integer, Users> updateUser(int id, Users userUpdate) {
        if(users.containsKey(id)){
            Users currUser=users.get(id);
            currUser.setUsername(userUpdate.getUsername());
		    currUser.setUPassword(userUpdate.getUPassword());
		    currUser.setEmail(userUpdate.getEmail());
		    currUser.setGender(userUpdate.getGender());
		    currUser.setAge(userUpdate.getAge());
		    currUser.setFirst_name(userUpdate.getFirst_name());
		    currUser.setLast_name(userUpdate.getLast_name());
        }

        return users;
    }

    @Override
    public HashMap<Integer, Users> deleteUser(int id) {
        if(users.containsKey(id)){
            users.remove(id);
        }
        return users;
    }
}
