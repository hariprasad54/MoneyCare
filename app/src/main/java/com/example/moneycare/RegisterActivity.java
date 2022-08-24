package com.example.moneycare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText fullName,email,mobile,pass,repass,refCode;
    private Button btnReg,btnLogMove;
    private String reqType;
    private TextView swipeLeft;

    private  String strFullName,strEmail,strMobile,strPass,strRePass,strRefCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent in = getIntent();
        reqType = in.getStringExtra("req_mode");

        fullName = findViewById(R.id.et_name);
        email = findViewById(R.id.et_emailreg);
        mobile = findViewById(R.id.et_phone);
        pass = findViewById(R.id.et_passwordreg);
        repass = findViewById(R.id.et_repassword);
        refCode = findViewById(R.id.et_ref_code);
        btnReg = findViewById(R.id.btn_register);
        btnLogMove = findViewById(R.id.btnLogMove);
        swipeLeft = findViewById(R.id.swipeLeft);


        //checking the request
        if(reqType.equals("home_page")){
            btnLogMove.setVisibility(View.INVISIBLE);
            swipeLeft.setVisibility(View.INVISIBLE);
        }

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strFullName = fullName.getText().toString();
                strEmail = email.getText().toString();
                strMobile = mobile.getText().toString();
                strPass = pass.getText().toString();
                strRePass = repass.getText().toString();
                strRefCode = refCode.getText().toString();

                if (validateDetails(strFullName,strEmail,strMobile,strPass,strRePass)){
                    Toast.makeText(RegisterActivity.this, "Registration Sucess!!", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(in);
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

    boolean validateDetails(String strFullName, String strEmail,String strMobile, String pass, String strRePass){
        boolean stat = true;
        if (isEmpty(strFullName) ||isEmpty(strEmail) || isEmpty(strMobile) || isEmpty(strPass) ||isEmpty(strRePass)) {
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
                else{
                        if (pass.length() < 6 || !(strPass.equals(strRePass))){
                            Toast t = Toast.makeText(this, "Invalid Password/Password not Matched", Toast.LENGTH_LONG);
                            t.show();
                            stat = false;
                        }
                }
        }

        return stat;
    }

    @Override
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
    }
}