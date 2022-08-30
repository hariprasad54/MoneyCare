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
import android.widget.Button;
import android.widget.TextView;

import com.example.moneycare.adapters.TeamAdapter;
import com.example.moneycare.adapters.TransactionAdapter;
import com.example.moneycare.model.Transaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyReferralsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView refBal,depBal;
    private Button btnWithdraw,btnDeposit;
    private TransactionAdapter adapter;
    public List<Transaction> transactionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_referrals);

        refBal = findViewById(R.id.ref_bal_tot);
        depBal = findViewById(R.id.amount_dep_total);
        btnWithdraw = findViewById(R.id.btn_withdraw);
        btnDeposit = findViewById(R.id.btn_deposit);

        transactionList = new ArrayList<Transaction>();
        transactionList.add(new Transaction("TrnID: TRN100010001111","EMAIL: abc@abc.com","Date: 21/08/2022","19999"));
        transactionList.add(new Transaction("TrnID: TRN110011001000","EMAIL: def@abc.com","Date: 21/08/2022","24999"));
        transactionList.add(new Transaction("TrnID: TRN101010111022","EMAIL: ghi@abc.com","Date: 21/08/2022","32999"));
        transactionList.add(new Transaction("TrnID: TRN100010001321","EMAIL: jkl@abc.com","Date: 21/08/2022","1345999"));
        transactionList.add(new Transaction("TrnID: TRN100010043213","EMAIL: mno@abc.com","Date: 21/08/2022","999"));

        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent depositIn = new Intent(getApplicationContext(),AmountDepositActivity.class);
                startActivity(depositIn);
            }
        });

        btnWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent withdrawIn = new Intent(getApplicationContext(),AmountWithdrawActivity.class);
                startActivity(withdrawIn);
            }
        });

        initRecyclerView();


    }
    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_transaction);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TransactionAdapter(transactionList);
        recyclerView.setAdapter(adapter);
    }

}