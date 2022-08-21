package com.example.moneycare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText email,password;
    private Button btnLogin,btnRegMove;

    private String strEmail,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegMove = findViewById(R.id.regMove);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strEmail = email.getText().toString();
                pass = password.getText().toString();
                //if(validateDetails(strEmail,pass)) {
                if(true) {
                    //if (strEmail.equals("test@test.com") && pass.equals("pass123")){
                    if (true){
                        Toast.makeText(LoginActivity.this, "Login Suceess", Toast.LENGTH_LONG).show();
                        Intent in = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(in);
                    }

                }
            }
        });

        btnRegMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(LoginActivity.this, RegisterActivity.class);
                in.putExtra("req_mode","login_page");
                startActivity(in);
            }
        });

    }
    boolean isEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(String str) {
        return TextUtils.isEmpty(str);
    }


    boolean validateDetails(String strEmail, String pass){
        boolean stat = true;
        if (isEmpty(strEmail)) {
            Toast t = Toast.makeText(this, "You must enter emain/phone and password to login!", Toast.LENGTH_SHORT);
            t.show();
            stat = false;
        }

        if (isEmpty(pass)) {
            password.setError("Password is required!");
            stat = false;
        }

        if (isEmail(strEmail) == false) {
            email.setError("Enter valid email!");
            Toast t = Toast.makeText(this, "Invalid Email", Toast.LENGTH_LONG);
            t.show();
            stat = false;
        }
        if (pass.length() < 6){
            password.setError("Password length must be greaterthan 6 characters");
            Toast t = Toast.makeText(this, "Password length must be greaterthan 6 characters", Toast.LENGTH_LONG);
            t.show();
            stat = false;
        }
        return stat;
    }

}