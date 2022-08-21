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
import com.example.moneycare.model.BankAccount;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class BankDetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<BankAccount> bankAccountList;
    private BankAdapter adapter;
    private TextView accounts;
    private ExtendedFloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_details);

        accounts = findViewById(R.id.your_accounts);
        fab = findViewById(R.id.btn_add_bankac);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addAccountIn = new Intent(getApplicationContext(),AddBankAccountActivity.class);
                startActivity(addAccountIn);
                finish();
            }
        });


        bankAccountList = new ArrayList<>();
        bankAccountList.add(new BankAccount("CICIC BANK","IFSC00001","1234567890"));
        bankAccountList.add(new BankAccount("ABC BANK","IFSC00002","9081726354"));

        if(bankAccountList.size() <= 0){
            accounts.setText("No Account Details Found..Click the Add Account Details to add ");
        }


        //initData();
        initRecyclerView();


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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

    @Override
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
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}