package com.example.moneycare;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.moneycare.adapters.MemberSubAdapter;
import com.example.moneycare.adapters.MemberSuperAdapter;
import com.example.moneycare.adapters.TeamAdapter;
import com.example.moneycare.apicontroler.API;
import com.example.moneycare.apicontroler.GetRequest;
import com.example.moneycare.model.ApprovalRequest;
import com.example.moneycare.model.MemberSub;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AdminApprovalSubActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSub;
    public static List<MemberSub> memberSubList;
    public static MemberSubAdapter subAdapter;
    public static String srcUsrerId;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_approval_sub);
        Intent currentIn = getIntent();
        srcUsrerId = currentIn.getStringExtra("srcUserId");

        memberSubList = new ArrayList<>();
        for(ApprovalRequest ar : AdminApprovalSuperActivity.memberSuperList){
           if(ar.srcUser.getUserName().equals(srcUsrerId))
               memberSubList.add(new MemberSub(ar.targetUser.getFirstName(),ar.targetUser.getLastName(),ar.targetUser.getEmail(), ar.targetUser.getMobileNo(), ar.targetUser.getTranscationId()));
        }
//        memberSubList.add(new MemberSub("abc@abc.com","9182736450","Trn123456789"));
//        memberSubList.add(new MemberSub("def@abc.com","1234567890","Trn918273645"));
//        memberSubList.add(new MemberSub("ghi@abc.com","9876543210","Trn918273645"));

        initRecyclerView();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_refresh2,menu);
        return true;

    }

    private void initRecyclerView() {
        recyclerViewSub = (RecyclerView) findViewById(R.id.recyclerview_sub_req);
        recyclerViewSub.setHasFixedSize(true);
        recyclerViewSub.setLayoutManager(new LinearLayoutManager(this));
        subAdapter = new MemberSubAdapter(memberSubList);
        recyclerViewSub.setAdapter(subAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.refresh_sub:
                try {
                    refreshList();
                } catch (IOException e) {
                    Log.i(TAG, "refresh failed: "+ e.getMessage());
                }
                break;
            case android.R.id.home:
                this.finish();
                Intent intent = new Intent(this, AdminApprovalSuperActivity.class);
                intent.putExtra("userEmail","admin@test.com");
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshList() throws IOException {
        AdminApprovalSuperActivity.memberSuperList.clear();
        List<ApprovalRequest> tmpList = new ObjectMapper().readValue(GetRequest.sendRequest(API.GETPENDINGAPPROVALS+LoginActivity.adminUserId), new TypeReference<List<ApprovalRequest>>(){});
        AdminApprovalSuperActivity.memberSuperList.addAll(tmpList);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, AdminApprovalSuperActivity.class);
        intent.putExtra("userEmail","admin@test.com");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}