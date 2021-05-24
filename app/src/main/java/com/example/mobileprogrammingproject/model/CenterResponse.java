package com.example.mobileprogrammingproject.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CenterResponse {

    @SerializedName("currentCount")
    public int currentCount;
    @SerializedName("matchCount")
    public int matchCount;
    @SerializedName("page")
    public int page;
    @SerializedName("perPage")
    public int perPage;
    @SerializedName("totalCount")
    public int totalCount;
    @SerializedName("data")
    private List<Center> data;
}
