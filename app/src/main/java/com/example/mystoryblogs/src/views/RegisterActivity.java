package com.example.mystoryblogs.src.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letschat.databinding.ActivityRegisterBinding;
import com.example.mystoryblogs.R;
import com.example.mystoryblogs.models.User;
import com.example.mystoryblogs.rest.RetrofitSingleton;
import com.example.mystoryblogs.rest.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegisterBinding binding = ActivityRegisterBinding.inflate(getLayoutInflater());

        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.editTextTextPersonUserName.getText()!=null && binding.editTextTextPersonUserName.getText().toString() != null
                        && binding.editTextTextPassword.getText()!=null && binding.editTextTextPassword.getText().toString() != null
                        && binding.editTextTextPersonUserName.getText()!=null && binding.editTextTextPersonUserName.getText().toString() != null) {
                    UserService service = RetrofitSingleton.getRetrofitInstance(RegisterActivity.this).create(UserService.class);
                    Call<User> callAsync = service.signUp(binding.editTextTextPersonName.getText().toString(),
                            binding.editTextTextPersonUserName.getText().toString(),
                            binding.editTextTextPassword.getText().toString());

                    callAsync.enqueue(new Callback<User>() {

                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.isSuccessful()) {
//                                User apiResponse = response.body();
//                                Log.i("tokenn",apiResponse.toString());
//                                //API response
//                               SharedPreferences.Editor editor= getSharedPreferences(CONSTANTS.SharedPreferenceName,0).edit();
//                               editor.putString("name",binding.editTextTextPersonName.getText().toString());
//                               editor.putString("token",apiResponse.getToken());
//                               editor.commit();
                               Intent in=new Intent(RegisterActivity.this,ChatActivity.class);
                                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                               startActivity(in);
                            } else {
                                Toast.makeText(RegisterActivity.this,getString(R.string.register_later),Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            System.out.println(t);
                        }
                    });
                }
                else{
                    Toast.makeText(RegisterActivity.this, getString(R.string.fields_empty), Toast.LENGTH_LONG).show();
                }
            }
        });

        setContentView(binding.getRoot());
    }
}