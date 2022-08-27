package com.example.moneycare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText email,password;
    private Button btnLogin,btnRegMove;
    private TextView forgotPass;

    private String strEmail,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegMove = findViewById(R.id.regMove);
        forgotPass = findViewById(R.id.forgot_pass);

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

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());
                final EditText etEmail = new EditText(getApplicationContext());
                alert.setMessage("Enter Your Email");
                alert.setTitle("Enter Email");

                alert.setView(etEmail);

                alert.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        String strEmailFromD = etEmail.getText().toString();
                        if (!(isEmail(strEmailFromD))&& isEmpty(strEmailFromD)){
                            Toast.makeText(getApplicationContext(),"Invalid Email or Email Empty",Toast.LENGTH_LONG).show();
                            etEmail.setError("Invalid Email");
                        }
                        else {
                            //check for the email in DB
                            //if exists then otp validation
                            Intent
                            //if not exists uncomment below line
                            //etEmail.setError("Email Not found")
                        }

                    }
                });

                alert.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // what ever you want to do with No option.
                    }
                });

                alert.show();
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

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to Exit from App?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        //Intent in = new Intent(getApplicationContext(),LoginActivity.class);
                        //startActivity(in);
                        //LoginActivity.super.onBackPressed();
                        ActivityCompat.finishAffinity(LoginActivity.this);
                    }
                }).create().show();
    }
}