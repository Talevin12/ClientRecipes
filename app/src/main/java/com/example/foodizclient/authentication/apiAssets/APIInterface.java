package com.example.foodizclient.authentication.apiAssets;

import com.example.foodizclient.boundaries.MiniAppCommandBoundary;
import com.example.foodizclient.boundaries.NewUserBoundary;
import com.example.foodizclient.boundaries.ObjectBoundary;
import com.example.foodizclient.boundaries.ObjectId;
import com.example.foodizclient.boundaries.UserBoundary;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("/superapp/users/login/{superapp}/{email}")
    Call<UserBoundary> login(@Path("superapp") String superapp, @Path("email") String email);

    @POST("/superapp/users")
    Call<UserBoundary> register(@Body NewUserBoundary newUser);

    @POST("/superapp/objects")
    Call<ObjectBoundary> createObject(@Body ObjectBoundary newObject);

    @GET("/superapp/objects/search/byType/{type}?")
    Call<ArrayList<ObjectBoundary>> getObjectsByType(@Path("type") String type, @Query("userSuperapp") String superapp, @Query("userEmail") String email);

    @GET("/superapp/objects/search/byTypeAndCreatedBy/{type}/{userSuperapp}/{userEmail}")
    Call<ObjectBoundary> getObjectsByTypeAndCreatedBy(@Path("type") String type, @Path("userSuperapp") String superapp, @Path("userEmail") String email);

    @PUT("/superapp/users/{userSuperapp}/{userEmail}")
    Call<ResponseBody> updateUser(@Path("userSuperapp") String superapp, @Path("userEmail") String email, @Body UserBoundary user);

    @PUT("/superapp/objects/{superapp}/{internalObjectId}")
    Call<ResponseBody> updateObject(@Path("superapp") String superapp, @Path("internalObjectId") String internalObjectId,
                                    @Query("userSuperapp") String userSuperapp, @Query("userEmail") String email,
                                    @Body ObjectBoundary object);

    @PUT("/superapp/objects/{superapp}/{internalObjectId}/children")
    Call<ResponseBody> bindObjects(@Path("superapp") String superapp, @Path("internalObjectId") String internalObjectId,
                                    @Query("userSuperapp") String userSuperapp, @Query("userEmail") String email,
                                    @Body ObjectId superAppObjectId);

    @GET("/superapp/objects/{superapp}/{internalObjectId}/children")
    Call<ArrayList<ObjectBoundary>> getAllChildrenObjects(@Path("superapp") String superapp, @Path("internalObjectId") String internalObjectId,
                                   @Query("userSuperapp") String userSuperapp, @Query("userEmail") String email);

    @POST("/superapp/miniapp/{miniAppName}")
    Call<Object> invokeCommand(@Path("miniAppName") String miniAppName, @Body MiniAppCommandBoundary command);
}
