package com.example.mystoryblogs.mystoryblogs.rest.service;
import com.example.mystoryblogs.mystoryblogs.models.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService  {

    @POST("/user/signUp")
    @FormUrlEncoded
    Call<User> signUp(@Field("authorName") String authorName,
                      @Field("userName") String userName,
                      @Field("password") String password);

    @POST("/user/login")
    @FormUrlEncoded
    Call<User> login(@Field("userName") String userName,
                     @Field("password") String password);

    @GET("/user/refreshToken")
    Call<User> refreshToken();
}
