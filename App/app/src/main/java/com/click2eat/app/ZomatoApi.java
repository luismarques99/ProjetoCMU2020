package com.click2eat.app;

import com.click2eat.app.models.Restaurant_;
import com.click2eat.app.models.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


public interface ZomatoApi {
    @GET("search")
    Call<SearchResponse> getNearbyRestaurants(@Query("lat") double lat, @Query("lon") double lon,
                                              @Query("count") int count, @Query("radius") double radius, @Query("sort") String sort,
                                              @Query("order") String order,
                                              @Header("user-key") String apiKey);

    @GET("search")
    Call<SearchResponse> searchByName(@Query("q") String q, @Query("lat") double lat, @Query("lon") double lon,
                                      @Query("count") int count, @Query("radius") double radius, @Query("sort") String sort,
                                      @Query("order") String order, @Header("user-key") String apiKey);

    @GET("restaurant")
    Call<Restaurant_> getRestaurantDetails(@Query("res_id") int id, @Header("user-key") String apiKey);

}