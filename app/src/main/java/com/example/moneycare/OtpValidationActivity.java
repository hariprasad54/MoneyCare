package com.example.moneycare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OtpValidationActivity extends AppCompatActivity {

    private EditText otp;
    private Button otpVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_validation);

        otp = findViewById(R.id.et_otp);
        otpVal = findViewById(R.id.btn_Otp_val);

        otpVal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strOtp = otp.getText().toString();
                Intent intent = getIntent();
                String req = intent.getStringExtra("from");
                if (req.equals("change_pass")){
                    Intent changePassIn = new Intent(getApplicationContext(),ChangePasswordActivity.class);
                    startActivity(changePassIn);
                    finish();
                }
                else{
                    Toast.makeText(OtpValidationActivity.this, "Else block Reached", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}