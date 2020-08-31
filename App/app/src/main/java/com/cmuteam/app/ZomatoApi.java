package com.cmuteam.app;

import com.cmuteam.app.Models.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


public interface ZomatoApi {
    @GET("search")
    Call<ApiResponse> getNearbyRestaurants(@Header("user-key") String apiKey, @Query("lat") double lat, @Query("lon") double lon,
                                           @Query("count") int count, @Query("radius") double radius, @Query("sort") String sort,
                                           @Query("order") String order);
    Call<ApiResponse> getNearbyRestaurants(@Header("user-key") String apiKey, @Query("city_id") int cityId);
}