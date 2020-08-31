package com.example.mystoryblogs.src.repositories;

import android.util.Log;

import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;

import com.example.mystoryblogs.src.models.DataErrorWrapper;
import com.example.mystoryblogs.src.models.User;
import com.example.mystoryblogs.src.rest.RetrofitSingleton;
import com.example.mystoryblogs.src.rest.service.UserService;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private User user;


    // private constructor : singleton access
    private LoginRepository() {

    }

    public static LoginRepository getInstance() {
        if (instance == null) {
            instance = new LoginRepository();
        }
        return instance;
    }


    public Flowable<DataErrorWrapper<User>> login(String username, String password) {
        // handle login
        return RetrofitSingleton.getRetrofitInstance().create(UserService.class).login(username, password)
                .subscribeOn(Schedulers.io())
                .map(new Function<User, DataErrorWrapper<User>>() {
                    @Override
                    public DataErrorWrapper<User> apply(User user) throws Exception {

                        return new DataErrorWrapper<>(user);
                    }
                })
                .onErrorReturn(new Function<Throwable, DataErrorWrapper<User>>() {
                    @Override
                    public DataErrorWrapper<User> apply(Throwable throwable) throws Exception {
                        return new DataErrorWrapper<>(throwable);
                    }
                })
                ;

    }
}