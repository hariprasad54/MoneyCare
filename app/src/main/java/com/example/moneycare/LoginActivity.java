package com.example.moneycare;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneycare.apicontroler.API;
import com.example.moneycare.apicontroler.PostRequest;
import com.example.moneycare.model.LoginType;
import com.example.moneycare.model.UserAuthEntity;
import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * TODO Check if default login working or not
 * (once approved by admin the user should be able login with mny123 password)
 */
public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private TextInputLayout password;
    private Button btnLogin,btnRegMove;
    private TextView forgotPass;
    public static String userId;
    public static String adminUserId;
    private String strEmail,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        //btnRegMove = findViewById(R.id.regMove);
        forgotPass = findViewById(R.id.forgot_pass);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strEmail = email.getText().toString();
                pass = password.getEditText().getText().toString();
                //if(validateDetails(strEmail,pass)) {
                if(true) {
                    UserAuthEntity loginItem = new UserAuthEntity(strEmail, pass);
                    String response = "";

                    try {
                      response =  PostRequest.sendRequest(API.MAKELOGIN, loginItem.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("The response from server: " + response);
                    if (!response.isEmpty() && response.equalsIgnoreCase("\"success\"")){
                    //if (true){
                        Toast.makeText(LoginActivity.this, "Login Suceess", Toast.LENGTH_LONG).show();
                        Intent in = new Intent(LoginActivity.this,MainActivity.class);
                        in.putExtra("userEmail",strEmail);
                        userId = strEmail;
                        startActivity(in);
                    }
                    else if(response.equalsIgnoreCase("\"adminlogin\"")){
                        Toast.makeText(LoginActivity.this, "Login Suceess", Toast.LENGTH_LONG).show();
                        Intent in = new Intent(LoginActivity.this,AdminActivity.class);
                        in.putExtra("userEmail",strEmail);
                        adminUserId = strEmail;
                        userId = strEmail;
                        startActivity(in);
                        finish();
                    }
                    else if(response.equalsIgnoreCase("\"firstime\"")){
                        Toast.makeText(LoginActivity.this, "Login Suceess", Toast.LENGTH_LONG).show();
                        Intent in = new Intent(LoginActivity.this,ChangePasswordActivity.class);
                        in.putExtra("userEmail",strEmail);
                        userId = strEmail;
                        startActivity(in);
                        finish();
                    }
                    else if (strEmail.equals("admin@test.com") && pass.equals("pass123")){
                        //if (true){
                        Toast.makeText(LoginActivity.this, "Login Suceess", Toast.LENGTH_LONG).show();
                        Intent in = new Intent(LoginActivity.this,AdminApprovalSuperActivity.class);
                        in.putExtra("userEmail",strEmail);
                        startActivity(in);
                        finish();
                    }

                }
            }
        });


        forgotPass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = getLayoutInflater();
                final View viewDialog = layoutInflater.inflate(R.layout.email_dialog, null);
                AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                alert.setTitle("Enter Email");
                alert.setView(viewDialog);

                EditText etEmail = viewDialog.findViewById(R.id.et_email_dialog);


                alert.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        String strEmailFromD = etEmail.getText().toString();
                        if (!(isEmail(strEmailFromD))|| isEmpty(strEmailFromD)){
                              Toast.makeText(getApplicationContext(),"Invalid Email or Email Empty",Toast.LENGTH_LONG).show();
                            etEmail.setError("Invalid Email");

                        }
                        else {
                            //check for the email in DB
                            //if exists then otp validation
                            Intent forgotPassIn = new Intent(LoginActivity.this,ChangePasswordActivity.class);
                            forgotPassIn.putExtra("from","change_pass");
                            startActivity(forgotPassIn);
                            finish();
                            //if not exists uncomment below line
                            //etEmail.setError("Email Not found")
                        }

                    }
                });

                alert.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // what ever you want to do with No option.
                        dialog.dismiss();
                    }
                });

                alert.create().show();
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