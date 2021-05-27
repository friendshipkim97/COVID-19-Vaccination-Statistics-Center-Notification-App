package com.example.mobileprogrammingproject.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VaccineStat implements Serializable {

    @SerializedName("baseDate")
    private String baseDate;
    @SerializedName("sido")
    private String sido;
    @SerializedName("firstCnt")
    private int firstCnt;
    @SerializedName("secondCnt")
    private int secondCnt;
    @SerializedName("totalFirstCnt")
    private int totalFirstCnt;
    @SerializedName("totalSecondCnt")
    private int totalSecondCnt;
    @SerializedName("accumulatedFirstCnt")
    private int accumulatedFirstCnt;
    @SerializedName("accumulatedSecondCnt")
    private int accumulatedSecondCnt;
}
