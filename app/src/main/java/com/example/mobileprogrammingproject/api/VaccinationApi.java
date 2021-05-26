package com.example.mobileprogrammingproject.api;

import com.example.mobileprogrammingproject.model.Center;
import com.example.mobileprogrammingproject.model.CenterResponse;
import com.example.mobileprogrammingproject.model.PageRequest;
import com.example.mobileprogrammingproject.model.Pagination;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface VaccinationApi {

    @Headers({"accept: application/json", "Authorization: 91L7g5h4Uzl%2FlRV34MsnMRF2uLY%2F4lW3STU2J%2Bawycm38VIzk5f6ErydPZ78TfJY6eyJMFW8x7vR07wMZsKHcA%3D%3D"})
    @GET("15077586/v1/centers?page=1&perPage=263&returnType=JSON&serviceKey=91L7g5h4Uzl%2FlRV34MsnMRF2uLY%2F4lW3STU2J%2Bawycm38VIzk5f6ErydPZ78TfJY6eyJMFW8x7vR07wMZsKHcA%3D%3D")
    Call<Pagination<List<Center>>> getCenter();
}
