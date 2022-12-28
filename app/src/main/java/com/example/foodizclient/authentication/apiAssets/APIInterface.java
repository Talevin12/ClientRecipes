package com.example.foodizclient.authentication.apiAssets;

import com.example.foodizclient.boundaries.NewUserBoundary;
import com.example.foodizclient.boundaries.UserBoundary;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {
//    @GET("/api/unknown")
//    Call<MultipleResource> doGetListResources();

    @GET("/superapp/users/login/{superapp}/{email}")
    Call<UserBoundary> login(@Path("superapp") String superapp, @Path("email") String email);

//    @GET("/api/users?")
//    Call<UserList> doGetUserList(@Query("page") String page);
//
//    @FormUrlEncoded
    @POST("/superapp/users")
    Call<UserBoundary> register(@Body NewUserBoundary newUser);
}
