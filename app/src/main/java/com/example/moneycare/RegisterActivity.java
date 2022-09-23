package com.example.moneycare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneycare.apicontroler.API;
import com.example.moneycare.apicontroler.PostRequest;
import com.example.moneycare.model.BasicUserEntity;
import com.example.moneycare.model.UserAuthEntity;
import com.example.moneycare.requests.AddUserRequest;

import java.io.IOException;

/**
 * TODO While adding user add first name and lastname chang referal code to transactionid
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText firstName,lastName,email,mobile,refCode;
    private Button btnReg,btnLogMove;
    private String reqType;
    private TextView swipeLeft;
    private String userEmail;

    private  String strFirstName,strLastName,strEmail,strMobile,strPass,strRePass,strRefCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Intent in = getIntent();

        //userEmail = in.getStringExtra("userEmail");
        userEmail =  LoginActivity.userId;
        reqType = in.getStringExtra("req_mode");

        firstName = findViewById(R.id.et_name);
        lastName = findViewById(R.id.et_emailreg);
        email = findViewById(R.id.et_phone);
        mobile = findViewById(R.id.et_passwordreg);
        refCode = findViewById(R.id.et_ref_code);
        btnReg = findViewById(R.id.btn_register);
        btnLogMove = findViewById(R.id.btnLogMove);
        swipeLeft = findViewById(R.id.swipeLeft);


        //checking the request

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strFirstName = firstName.getText().toString();
                strLastName = lastName.getText().toString();
                strEmail = email.getText().toString();
                strMobile = mobile.getText().toString();
                strRefCode = refCode.getText().toString();

                if (validateDetails(strFirstName,strLastName,strEmail,strMobile,strRefCode)){
                    UserAuthEntity srcUser = new UserAuthEntity().setUserName(userEmail);
                    BasicUserEntity basicUserEntity = new BasicUserEntity(strFirstName, strLastName, strEmail, strMobile, strRefCode);
                    AddUserRequest adRequest = new AddUserRequest(srcUser,basicUserEntity);
                    try {
                       if(!PostRequest.sendRequest(API.ADDUSER, adRequest.toString()).equals(Constants.EMPTY_STR)){
                        Toast.makeText(RegisterActivity.this, "Registration Sucess!!", Toast.LENGTH_SHORT).show();
                        //Intent in = new Intent(RegisterActivity.this, MainActivity.class);
                        //startActivity(in);
                       }
                       else {
                           Toast.makeText(RegisterActivity.this, "Registration Failed!!", Toast.LENGTH_SHORT).show();
                           Log.i("AddUser", "adding user failed: ");
                       }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


            }
        });

        btnLogMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(RegisterActivity.this,LoginActivity.class);
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

    boolean validateDetails(String strFirstName, String strLastName,String strEmail,String strMobile, String strRefCode){
        boolean stat = true;
        if (isEmpty(strFirstName) ||isEmpty(strEmail) || isEmpty(strMobile) || isEmpty(strLastName) ||isEmpty(strRefCode)) {
            Toast t = Toast.makeText(this, "All Fields Mandatory to Register!", Toast.LENGTH_LONG);
            t.show();
            stat = false;
        }

        else{
                if (isEmail(strEmail) == false) {
                    email.setError("Enter valid email!");
                    Toast t = Toast.makeText(this, "Invalid Email", Toast.LENGTH_LONG);
                    t.show();
                    stat = false;
                }
               /* else{
                        if (pass.length() < 6 || !(strPass.equals(strRePass))){
                            Toast t = Toast.makeText(this, "Invalid Password/Password not Matched", Toast.LENGTH_LONG);
                            t.show();
                            stat = false;
                        }
                }*/
        }

        return stat;
    }

  /*  @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (reqType.equals("home_page")) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        else if(reqType.equals("login_page")){
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }*/
}