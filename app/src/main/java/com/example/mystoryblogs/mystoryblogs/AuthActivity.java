package com.example.mystoryblogs.mystoryblogs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letschat.databinding.ActivityAuthBinding;
import com.example.mystoryblogs.R;
import com.example.mystoryblogs.models.User;
import com.example.mystoryblogs.rest.RetrofitSingleton;
import com.example.mystoryblogs.rest.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityAuthBinding binding = ActivityAuthBinding.inflate(getLayoutInflater());

        if(getSharedPreferences(CONSTANTS.SharedPreferenceName,0).getString("token",null)!=null)
        {   Log.d("token?",getSharedPreferences(CONSTANTS.SharedPreferenceName,0).getString("token",null));
            Intent in = new Intent(AuthActivity.this, ChatActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(in);
        }

        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AuthActivity.this,RegisterActivity.class));
            }
        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(binding.username.getText()!=null && binding.username.getText().toString()!=null
                        && binding.password.getText()!=null && binding.password.getText().toString()!=null ) {


                    UserService service = RetrofitSingleton.getRetrofitInstance(AuthActivity.this).create(UserService.class);

                    Call<User> callAsync = service.login(binding.username.getText().toString(), binding.password.getText().toString());

                    callAsync.enqueue(new Callback<User>() {

                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.isSuccessful()) {
                                if (response.isSuccessful()) {
//                                    User apiResponse = response.body();
//                                    Log.i("tokennnnn",apiResponse.toString());
//                                    //API response
//                                    SharedPreferences.Editor editor = getSharedPreferences(CONSTANTS.SharedPreferenceName, 0).edit();
//                                    editor.putString("token", apiResponse.getToken());
//                                    editor.putString("name",binding.username.getText().toString());
//                                    editor.commit();
                                    Intent in = new Intent(AuthActivity.this, ChatActivity.class);
                                    in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(in);
                                } else {
                                    Toast.makeText(AuthActivity.this, getString(R.string.login_later), Toast.LENGTH_LONG).show();
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(AuthActivity.this, getString(R.string.login_later), Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else{
                    Toast.makeText(AuthActivity.this, getString(R.string.fields_empty), Toast.LENGTH_LONG).show();
                }
            }
        });



        setContentView(binding.getRoot());
    }
}


