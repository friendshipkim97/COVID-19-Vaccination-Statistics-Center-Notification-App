package com.example.mobileprogrammingproject.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String email;
    private String nickName;
    private String strProfileImg;

    public User(String email, String nickName, String strProfileImg){
        this.email = email;
        this.nickName = nickName;
        this.strProfileImg = strProfileImg;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNickName() { return nickName; }
    public void setNickName(String nickName) { this.nickName = nickName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getStrProfileImg() { return strProfileImg; }
    public void setStrProfileImg(String strProfileImg) { this.strProfileImg = strProfileImg; }
}
