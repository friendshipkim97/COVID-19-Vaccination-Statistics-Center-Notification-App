package com.example.mobileprogrammingproject.valueObject;

public class VUser {

    private String email;
    private String nickName;
    private String strProfileImg;

    public VUser(String email, String nickName, String strProfileImg){
        this.email = email;
        this.nickName = nickName;
        this.strProfileImg = strProfileImg;
    }

    public String getNickName() { return nickName; }
    public void setNickName(String nickName) { this.nickName = nickName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getStrProfileImg() { return strProfileImg; }
    public void setStrProfileImg(String strProfileImg) { this.strProfileImg = strProfileImg; }
}
