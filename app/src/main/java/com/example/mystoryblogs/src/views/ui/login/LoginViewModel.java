package com.example.mystoryblogs.src.views.ui.login;


import androidx.lifecycle.MutableLiveData;

import com.example.mystoryblogs.src.DisposableViewModel;
import com.example.mystoryblogs.src.models.DataErrorWrapper;

import com.example.mystoryblogs.src.models.User;
import com.example.mystoryblogs.src.repositories.LoginRepository;


import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class LoginViewModel extends DisposableViewModel {


    private MutableLiveData<DataErrorWrapper<User>> loginResult = new MutableLiveData<>();


    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
    public MutableLiveData<DataErrorWrapper<User>> getLoginResult() {
        return loginResult;
    }




    public void login(String username, String password) {

        addDisposable(loginRepository.login(username, password).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSubscriber<DataErrorWrapper<User>>() {

            @Override
            public void onNext(DataErrorWrapper<User> userDataErrorWrapper) {
                loginResult.setValue(userDataErrorWrapper);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        }));
    }

}