package com.example.mobileprogrammingproject.valueObject;

public class VUser {

    private int id;
    private String password;
    private String email;
    private String name;
    private String profileImg;
    private String dateOfBirth;
    private String phoneNumber;
    private String gender;
    private String emailType;

    public VUser(int id, String password, String email, String name, String profileImg, String dateOfBirth, String phoneNumber, String gender, String emailType) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.name = name;
        this.profileImg = profileImg;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.emailType = emailType;
    }

    // Getters
    public int getId() {return id; }
    public String getPassword() {return password; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getProfileImg() { return profileImg; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getGender() { return gender; }
    public String getEmailType() {return emailType; }
}
