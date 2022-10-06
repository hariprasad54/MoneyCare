package com.example.moneycare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.moneycare.adapters.TransactionAdapter;
import com.example.moneycare.adapters.WithdrawRequestAdapter;
import com.example.moneycare.apicontroler.API;
import com.example.moneycare.apicontroler.GetRequest;
import com.example.moneycare.model.ApprovalRequest;
import com.example.moneycare.model.ApprovalWithdrawRequest;
import com.example.moneycare.model.MemberSub;
import com.example.moneycare.model.Transaction;
import com.example.moneycare.model.WithdrawRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WithdrawRequestsActivity extends AppCompatActivity {

    public static WithdrawRequestAdapter adapter;
    public List<ApprovalWithdrawRequest> approvalWithdrawRequestList;
    public static List<WithdrawRequest> withdrawRequestList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_requests);

        withdrawRequestList = new ArrayList<>();
        approvalWithdrawRequestList = new ArrayList<>();
        try {
            List<ApprovalWithdrawRequest> tmpList = new ObjectMapper().readValue(GetRequest.sendRequest(API.GETWITHDRAWREQUESTS+LoginActivity.userId), new TypeReference<List<ApprovalWithdrawRequest>>(){});
            HashSet<ApprovalWithdrawRequest> set = new HashSet<>(tmpList);
            approvalWithdrawRequestList.addAll(tmpList);

        } catch (IOException e) {
            e.printStackTrace();
        }

        for(ApprovalWithdrawRequest ar : approvalWithdrawRequestList){
                withdrawRequestList.add(new WithdrawRequest(ar.userTransaction.getTrnEmail(),ar.userTransaction.getTrnDate(),ar.userTransaction.getTrnAmount(),ar.userTransaction.getTrnStatus(),ar.userTransaction.getTrnUpiId()));
        }

       // withdrawRequestList.add(new WithdrawRequest("user1","25/09/2022","1000"));
        //withdrawRequestList.add(new WithdrawRequest("user2","22/09/2022","3000"));
        //withdrawRequestList.add(new WithdrawRequest("user3","26/09/2022","2000"));

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