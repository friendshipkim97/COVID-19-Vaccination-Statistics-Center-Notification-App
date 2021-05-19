package com.example.mobileprogrammingproject.valueObject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
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
}
