package com.example.roomdatabase.DaoClass;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.roomdatabase.EntityClass.UserModel;

import java.util.List;

@Dao
public interface Daoclass {

    @Insert
    void insertAllData(UserModel model);

    //Select All Data
    @Query("select * from  usertable")
    List<UserModel> getAllData();


    //DELETE DATA
    @Query("delete from usertable where `key`= :id")
    void deleteData(int id);

    //Update Data

    @Query("update usertable SET name= :name ,address =:address, phoneno =:phoneno where `key`= :key")
    void updateData(String name, String phoneno, String address, int key);


}
