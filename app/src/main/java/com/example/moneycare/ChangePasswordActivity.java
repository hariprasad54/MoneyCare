package com.example.moneycare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moneycare.apicontroler.API;
import com.example.moneycare.apicontroler.GetRequest;
import com.example.moneycare.apicontroler.PostRequest;
import com.example.moneycare.model.UserAuthEntity;

import java.io.IOException;

public class ChangePasswordActivity extends AppCompatActivity {

    private String pass;
    private EditText etPass,etRePass;
    private Button btnUpdatePass;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        etPass = findViewById(R.id.et_pass_update);
        etRePass = findViewById(R.id.et_RePass_update);
        btnUpdatePass = findViewById(R.id.btnUpdatePassword);

        userId = LoginActivity.userId;

        btnUpdatePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String password = etPass.getText().toString();
                //if (etPass.length() < 6)
                if (!etRePass.getText().toString().equalsIgnoreCase(password)){
                    etRePass.setError("Not Matching");
                }else{
                    try {
                        UserAuthEntity userAuthEntity = new UserAuthEntity(userId,password);
                        String response = PostRequest.sendRequest(API.RESETPASSWORD,userAuthEntity.toString());
                        if (response.equalsIgnoreCase("ERROR")){
                            Toast.makeText(ChangePasswordActivity.this, "Invalid Response", Toast.LENGTH_SHORT).show();
                        }
                        else if (response.equalsIgnoreCase("success "+password)){
                            Toast.makeText(ChangePasswordActivity.this, "Password Changed!!", Toast.LENGTH_SHORT).show();
                            Intent in = new Intent(ChangePasswordActivity.this,LoginActivity.class);
                            startActivity(in);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

   /* @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

   /* @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }*/
}