package com.example.mobileprogrammingproject.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Center implements Serializable {

    @SerializedName("address")
    private String address;
    @SerializedName("centerName")
    private String centerName;
    @SerializedName("centerType")
    private String centerType;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("facilityName")
    private String facilityName;
    @SerializedName("id")
    private int id;
    @SerializedName("lat")
    private String lat;
    @SerializedName("lng")
    private String lng;
    @SerializedName("org")
    private String org;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("sido")
    private String sido;
    @SerializedName("sigungu")
    private String sigungu;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("zipCode")
    private String zipCode;



}
