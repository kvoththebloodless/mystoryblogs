package com.example.mystoryblogs.src.views.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;


import android.view.View;

import android.widget.Toast;

import com.example.mystoryblogs.R;
import com.example.mystoryblogs.databinding.ActivityLoginBinding;

import com.example.mystoryblogs.src.models.DataErrorWrapper;
import com.example.mystoryblogs.src.models.User;


public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        loginViewModel.getLoginResult().observe(this, new Observer<DataErrorWrapper<User>>() {
            @Override
            public void onChanged(DataErrorWrapper<User> userDataErrorWrapper) {
                binding.loading.setVisibility(View.GONE);
                if(userDataErrorWrapper.isErr())
                {
                    Toast.makeText(LoginActivity.this,getString(R.string.login_failed),Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(userDataErrorWrapper.getResult()!=null)
                    {
                        Toast.makeText(LoginActivity.this,getString(R.string.welcome),Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.loading.setVisibility(View.VISIBLE);
                loginViewModel.login(binding.username.getText().toString(),
                        binding.password.getText().toString());
            }
        });

        setContentView(binding.getRoot());
    }


}