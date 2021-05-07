package com.example.mobileprogrammingproject.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.database.annotations.NotNull;

@Entity
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

    // Getters and Setters
    public int getId() {return id; }
    public void setId(int id) {this.id = id; }
    public String getPassword() {return password; }
    public void setPassword(String password) {this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getProfileImg() { return profileImg; }
    public void setProfileImg(String profileImg) { this.profileImg = profileImg; }
    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBith) { this.dateOfBirth = dateOfBirth; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getEmailType() {return emailType; }
    public void setEmailType(String emailType) { this.emailType = emailType; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", profileImg='" + profileImg + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", emailType='" + emailType + '\'' +
                '}';
    }
}
