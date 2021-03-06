package com.example.mobileprogrammingproject.database;

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

    @Query("SELECT * FROM User where name=(:name) and dateOfBirth=(:dateOfBirth) and emailType=(:emailType)")
    List<User> findEmailByNameAndBirth(String name, String dateOfBirth, String emailType);

    @Query("SELECT * FROM User where name=(:name) and phoneNumber=(:phoneNumber) and emailType=(:emailType)")
    List<User> findEmailByNameAndPhone(String name, String phoneNumber, String emailType);

    @Query("SELECT password FROM User where email=(:email) and emailType=(:emailType)")
    String findPasswordByEmail(String email, String emailType);

    @Query("SELECT name FROM User where email=(:email)")
    String findNameByEmail(String email);

    @Query("SELECT * FROM User where email=(:email) and emailType=(:emailType)")
    User findByEmail(String email, String emailType);

    @Query("SELECT * FROM User where email=(:email) and emailType=(:emailType)")
    User findByEmailAndType(String email, String emailType);

    @Query("SELECT id FROM User where email=(:email) and emailType=(:emailType)")
    int findIdByEmailAndType(String email, String emailType);

    @Query("UPDATE User set password=(:password), name=(:name), dateOfBirth=(:dateOfBirth), phoneNumber=(:phoneNumber), gender=(:gender) where id=(:id)")
    void updatePersonalInfoById(int id, String password, String name, String dateOfBirth, String phoneNumber, String gender);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);



}
