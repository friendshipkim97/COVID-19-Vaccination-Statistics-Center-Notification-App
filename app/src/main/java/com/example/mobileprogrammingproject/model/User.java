package com.example.mobileprogrammingproject.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.firebase.database.annotations.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class User {

    // Attributes
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String password;
    private String email;
    private String name;
    private String profileImg;
    private String dateOfBirth;
    private String phoneNumber;
    private String gender;
    private String emailType;

    // Construtor
    public User(String password, String email, String name, String profileImg, String dateOfBirth, String phoneNumber, String gender, String emailType) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.profileImg = profileImg;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.emailType = emailType;
    }


}
