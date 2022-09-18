package com.example.moneycare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.moneycare.adapters.BankAdapter;
import com.example.moneycare.apicontroler.API;
import com.example.moneycare.apicontroler.GetRequest;
import com.example.moneycare.model.BankAccount;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BankDetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<BankAccount> bankAccountList;
    private BankAdapter adapter;
    private TextView accounts;
    private ExtendedFloatingActionButton fab;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_details);

        accounts = findViewById(R.id.your_accounts);
        Intent userDetails = getIntent();
        userEmail = LoginActivity.userId;
        fab = findViewById(R.id.btn_add_bankac);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addAccountIn = new Intent(getApplicationContext(),AddBankAccountActivity.class);
                addAccountIn.putExtra("userEmail", userEmail);
                startActivity(addAccountIn);
                finish();
            }
        });


        bankAccountList = new ArrayList<>();
        try {
             bankAccountList = new ObjectMapper().readValue(GetRequest.sendRequest(API.GETBANKACCOUNTS+userEmail), new TypeReference<List<BankAccount>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
//        bankAccountList.add(new BankAccount("CICIC BANK","IFSC00001","1234567890","Alex Fleming"));
//        bankAccountList.add(new BankAccount("ABC BANK","IFSC00002","9081726354","Robin Sharma"));

        if(bankAccountList.size() <= 0){
            accounts.setText("No Account Details Found..Click the Add Account Details to add ");
        }


        //initData();
        initRecyclerView();

    }

    private void initData() {

    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_bank);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BankAdapter(bankAccountList);
        recyclerView.setAdapter(adapter);
    }

}