package com.example.roomdatabase;

import com.example.roomdatabase.DaoClass.UserDao;
import com.example.roomdatabase.EntityClass.UserModeler;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {UserModeler.class}, version = 4, exportSchema = false)
public abstract class UserDataBase extends RoomDatabase {

    public abstract UserDao getUserDao();


}



//    private static final String dbName="user";
//    private static UserDataBase userDataBase;
//
//    public static synchronized  UserDataBase getUserDataBase(Context context){
//
//        if(userDataBase==null){
//            userDataBase= Room.databaseBuilder(context,UserDataBase.class,dbName).fallbackToDestructiveMigration().build();
//
//        }
//        return userDataBase;
//    }
//
//    public abstract UserDao userDao();
//

