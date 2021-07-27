package com.example.roomdatabase.DaoClass;


import com.example.roomdatabase.EntityClass.UserModeler;

import java.util.List;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@androidx.room.Dao
public interface UserDao {

    @Query("SELECT * FROM User where userName= :username and password= :password")
    UserModeler getUser(String username, String password);

    //Get all items
    @Query("select * from user")
    public List<UserModeler> getMyData();

    @Insert
    void insert(UserModeler user);

    @Update
    void update(UserModeler user);

    @Delete
    void delete(UserModeler user);
}
