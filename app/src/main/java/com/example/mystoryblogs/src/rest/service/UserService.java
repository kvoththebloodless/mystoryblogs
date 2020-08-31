package com.example.mystoryblogs.src.rest.service;
import com.example.mystoryblogs.src.models.User;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService  {

    @POST("/user/signUp")
    @FormUrlEncoded
    Flowable<User> signUp(@Field("authorName") String authorName,
                          @Field("userName") String userName,
                          @Field("password") String password);

    @POST("/user/login")
    @FormUrlEncoded
   Flowable<User> login(@Field("userName") String userName,
                     @Field("password") String password);

//    @GET("/user/refreshToken")
//    Observable<null> refreshToken();
}
