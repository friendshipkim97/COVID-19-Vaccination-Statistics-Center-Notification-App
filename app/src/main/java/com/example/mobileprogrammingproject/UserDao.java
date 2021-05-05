package com.example.mobileprogrammingproject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mobileprogrammingproject.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    List<User> findAll();

//    @Query("SELECT * FROM user where id=id")
//    User findById(int uid);

    @Insert
    void insert(User mUser);

    @Delete
    void delete(User mUser);
}
