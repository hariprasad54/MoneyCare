package com.example.moneycare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.moneycare.model.UserItem;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserDetailsActivity extends AppCompatActivity {

    public UserItem userItem;
    private EditText userId,userEmail,userAvailBalance,userTotalEarnings;
    private Button btnUserTeam,btnUserBankAccounts;
    private String strUserId,strUserEmail,strAvailBal,strTotEarnings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        userId = findViewById(R.id.usr_user_id);
        userEmail = findViewById(R.id.usr_email);
        userAvailBalance = findViewById(R.id.usr_avail_bal);
        userTotalEarnings = findViewById(R.id.usr_total_earnings);
        btnUserTeam = findViewById(R.id.btn_user_team);
        btnUserBankAccounts = findViewById(R.id.btn_user_bankac);

        Intent userIn = getIntent();
        String userItemJson = userIn.getStringExtra("user_item");
        System.out.println(userItemJson);
        strUserId = userIn.getStringExtra("userId");
        strUserEmail = userIn.getStringExtra("userEmail");
        strAvailBal = userIn.getStringExtra("userAvailBal");
        strTotEarnings = userIn.getStringExtra("userTotEarnings");
        /*ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        try {
            userItem = mapper.readValue(userItemJson,UserItem.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/

        userId.setText(strUserId);
        userEmail.setText(strUserEmail);
        userAvailBalance.setText(String.valueOf(strAvailBal));
        userTotalEarnings.setText(String.valueOf(strTotEarnings));


        btnUserTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentUserTeam = new Intent(UserDetailsActivity.this,UserTeamActivity.class);
                intentUserTeam.putExtra("userEmail",strUserId);
                startActivity(intentUserTeam);
            }
        });

        btnUserBankAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBank = new Intent(UserDetailsActivity.this,BankDetailsActivity.class);
                intentBank.putExtra("userEmail",strUserId);
                startActivity(intentBank);
            }
        });


    }
}