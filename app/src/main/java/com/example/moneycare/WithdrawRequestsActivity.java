package com.example.moneycare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.moneycare.adapters.TransactionAdapter;
import com.example.moneycare.adapters.WithdrawRequestAdapter;
import com.example.moneycare.model.Transaction;
import com.example.moneycare.model.WithdrawRequest;

import java.util.ArrayList;
import java.util.List;

public class WithdrawRequestsActivity extends AppCompatActivity {

    private WithdrawRequestAdapter adapter;
    public List<WithdrawRequest> withdrawRequestList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_requests);

        withdrawRequestList = new ArrayList<>();
        withdrawRequestList.add(new WithdrawRequest("user1","25/09/2022","1000"));
        withdrawRequestList.add(new WithdrawRequest("user2","22/09/2022","3000"));
        withdrawRequestList.add(new WithdrawRequest("user3","26/09/2022","2000"));

        initRecyclerView();
    }
    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_wr_req);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WithdrawRequestAdapter(withdrawRequestList);
        recyclerView.setAdapter(adapter);
    }
}