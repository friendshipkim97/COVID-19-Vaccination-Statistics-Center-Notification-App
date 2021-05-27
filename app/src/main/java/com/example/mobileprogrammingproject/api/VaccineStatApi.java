package com.example.mobileprogrammingproject.api;

import com.example.mobileprogrammingproject.model.Center;
import com.example.mobileprogrammingproject.model.Pagination;
import com.example.mobileprogrammingproject.model.VaccineStat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VaccineStatApi {

    @GET("15077756/v1/vaccine-stat?page=1&perPage=18&returnType=JSON&serviceKey=91L7g5h4Uzl%2FlRV34MsnMRF2uLY%2F4lW3STU2J%2Bawycm38VIzk5f6ErydPZ78TfJY6eyJMFW8x7vR07wMZsKHcA%3D%3D")
    Call<Pagination<List<VaccineStat>>> getVaccineStat(@Query("cond[baseDate::EQ]") String dataTime);
}
