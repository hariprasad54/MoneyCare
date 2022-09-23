package com.example.moneycare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneycare.apicontroler.API;
import com.example.moneycare.apicontroler.GetRequest;
import com.example.moneycare.apicontroler.PostRequest;
import com.example.moneycare.model.BankAccount;
import com.example.moneycare.model.BasicUserEntity;
import com.example.moneycare.model.UserAuthEntity;
import com.example.moneycare.requests.AddUserRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private EditText fName, lName, email,mobile;
    private Button btnSaveChanges;
    private TextView msgSaved;
    public static BasicUserEntity profile;
    private String sFname,sLname,sEmail,sMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fName = findViewById(R.id.pro_firstName);
        lName = findViewById(R.id.pro_lastName);
        email = findViewById(R.id.pro_email);
        mobile = findViewById(R.id.pro_mobile);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);
        msgSaved = findViewById(R.id.pro_msgSaved);

        email.setFocusable(false);
        email.setBackground(getResources().getDrawable(R.drawable.et_custom_disable));
        mobile.setFocusable(false);
        mobile.setBackground(getResources().getDrawable(R.drawable.et_custom_disable));


        try {
           profile =  new ObjectMapper().readValue(GetRequest.sendRequest(API.GETUSERDETAILS+LoginActivity.userId), new TypeReference<BasicUserEntity>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        fName.setText(profile.getFirstName());
        lName.setText(profile.getLastName());
        email.setText(profile.getEmail());
        mobile.setText(profile.getMobileNo());

        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sFname = fName.getText().toString();
                sLname = lName.getText().toString();
                sEmail = email.getText().toString();
                sMobile = mobile.getText().toString();

                profile.setFirstName(sFname);
                profile.setLastName(sLname);
                profile.setEmail(sEmail);
                profile.setMobileNo(sMobile);

                UserAuthEntity srcUser = new UserAuthEntity().setUserName(LoginActivity.userId);
                //BasicUserEntity basicUserEntity = new BasicUserEntity(strFirstName, strLastName, strEmail, strMobile, strRefCode);
                AddUserRequest editProfileReq = new AddUserRequest(srcUser,profile);

                try {
                    if(!PostRequest.sendRequest(API.EDITPROFILE, editProfileReq.toString()).equals(Constants.EMPTY_STR)){
                        Toast.makeText(ProfileActivity.this, "Edit Profile Sucess!!", Toast.LENGTH_SHORT).show();
                        //Intent in = new Intent(RegisterActivity.this, MainActivity.class);
                        //startActivity(in);
                        msgSaved.setVisibility(View.VISIBLE);
                    }
                    else{
                        Toast.makeText(ProfileActivity.this, "Edit Profile Failed!!", Toast.LENGTH_SHORT).show();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });




    }

}