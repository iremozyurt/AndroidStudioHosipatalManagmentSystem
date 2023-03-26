package com.example.healthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {


    EditText edUsername, edEmail, edPassword, edConfirmPassword;
    Button register;
    TextView alreadyAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.editTextLabTestBookFullNmae);
        edEmail = findViewById(R.id.editTextLabTestBookAddress);
        edPassword = findViewById(R.id.editTextAppContactNumber);
        edConfirmPassword = findViewById(R.id.editTextAppFees);
        register = findViewById(R.id.buttonLabTestBook);
        alreadyAccount = findViewById(R.id.alreadyAccount);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirmPassword.getText().toString();

                Database db = new Database(getApplicationContext(),"Healthcare",null,1);


                if (username.length() == 0 || email.length() == 0 || password.length() == 0 || confirm.length() == 0) {

                    Toast.makeText(getApplicationContext(), "The Username or Password cannot be empty", Toast.LENGTH_LONG);
                } else {
                    if (password.compareTo(confirm) == 0) {

                        if(isValid(password)){
                                db.register(username,email,password);

                            Toast.makeText(getApplicationContext(), "Register Success", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

                        }else{
                            Toast.makeText(getApplicationContext(),"Password must contain at least 8 characters,having letter,digit and special symbol ",Toast.LENGTH_LONG).show();
                        }



                    } else {
                        Toast.makeText(getApplicationContext(), "Password and Confirm Password didn't match", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        alreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }


    public static boolean isValid(String passwordhere) {
        int f1 = 0, f2 = 0, f3 = 0;
        if (passwordhere.length() < 8) {
            return false;
        } else {
            for (int p = 0; p < passwordhere.length(); p++) {
                if (Character.isLetter(passwordhere.charAt(p))) {
                    f1 = 1;
                }
            }
            for (int r = 0; r < passwordhere.length(); r++) {
                if (Character.isDigit(passwordhere.charAt(r))) {
                    f2 = 1;
                }
            }
            for (int s = 0; s < passwordhere.length(); s++) {
                char c = passwordhere.charAt(s);
                if (c > 33 && c <= 46 || c == 64) {
                    f3 = 1;
                }
            }
            if (f1 == 1 && f2 == 1 && f3 == 1)
                return true;
            return false;

        }
    }
}

