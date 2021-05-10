package com.example.mobileprogrammingproject.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mobileprogrammingproject.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    List<User> findAll();

    @Query("SELECT email FROM User")
    List<String> findAllEmail();

    @Query("SELECT emailType FROM User where email=(:email)")
    String findTypeByEmail(String email);

    @Query("SELECT email FROM User where name=(:name) and dateOfBirth=(:dateOfBirth)")
    String findEmailByNameAndBirth(String name, String dateOfBirth);

    @Query("SELECT email FROM User where name=(:name) and phoneNumber=(:phoneNumber)")
    String findEmailByNameAndPhone(String name, String phoneNumber);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);


}
