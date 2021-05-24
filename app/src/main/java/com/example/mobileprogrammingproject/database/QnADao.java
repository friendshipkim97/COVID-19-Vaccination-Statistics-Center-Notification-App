package com.example.mobileprogrammingproject.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mobileprogrammingproject.model.QnA;

import java.util.List;

@Dao
public interface QnADao {

    @Query("SELECT * FROM QnA where userId=(:userId)")
    List<QnA> findAllQnAByuserId(int userId);

    @Insert
    void insert(QnA user);

    @Delete
    void delete(QnA user);

    @Update
    void update(QnA user);
}
