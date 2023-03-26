package com.example.healthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    EditText edUsernmae,edPassword;
    Button login;
    TextView Account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsernmae=findViewById(R.id.editTextUsername);
        edPassword=findViewById(R.id.editTextPassword);
        login=findViewById(R.id.buttonLogin);
        Account=findViewById(R.id.HaveAccount);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  startActivity(new Intent(LoginActivity.this,HomeActivity.class));

                String userName= edUsernmae.getText().toString();
                String password=edPassword.getText().toString();

                Database db = new Database(getApplicationContext(),"Healthcare",null,1);

                if(userName.length() ==0 || password.length()==0){
                    Toast.makeText(getApplicationContext(),"The Username or Password cannot be empty",Toast.LENGTH_LONG).show();
                }
                  else {

                      if(db.login(userName,password)==1){
                          Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();
                          SharedPreferences sharedPreferences= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                          SharedPreferences.Editor editor= sharedPreferences.edit();
                          editor.putString("username",userName);
                          // to save our data with key and value
                          editor.apply();
                          startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                      }else{
                          Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
                      }


                }
            }
        });

        Account.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View v) {
                 Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                 startActivity(intent);
            }
            });

    }
}

























