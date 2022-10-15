package com.example.moneycare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.moneycare.adapters.BankAdapter;
import com.example.moneycare.adapters.TeamAdapter;
import com.example.moneycare.apicontroler.API;
import com.example.moneycare.apicontroler.GetRequest;
import com.example.moneycare.model.BankAccount;
import com.example.moneycare.model.BasicUserEntity;
import com.example.moneycare.model.TeamMember;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserTeamActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<BasicUserEntity> teamMemberList;
    private TeamAdapter adapter;
    private String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_team);

        Intent userIn = getIntent();
        userEmail = LoginActivity.userId;
        if (userEmail.equalsIgnoreCase("admin")){
            userEmail = userIn.getStringExtra("userEmail");
        }
        try {
            teamMemberList = new ArrayList<>();
            Set<BasicUserEntity> set = new HashSet<>( new ObjectMapper()
                    .readValue(GetRequest.sendRequest(API.GETTEAM+userEmail),new TypeReference<List<BasicUserEntity>>(){}));
            teamMemberList.addAll(set);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        teamMemberList.add(new TeamMember("abc@abc.com","1234567890",R.drawable.ic_baseline_account_circle_24));
//        teamMemberList.add(new TeamMember("def@abc.com","4567890123",R.drawable.ic_baseline_account_circle_24));
//        teamMemberList.add(new TeamMember("ghi@abc.com","3456789012",R.drawable.ic_baseline_account_circle_24));
//        teamMemberList.add(new TeamMember("klm@abc.com","8901234567",R.drawable.ic_baseline_account_circle_24));
//        teamMemberList.add(new TeamMember("nop@abc.com","6789054321",R.drawable.ic_baseline_account_circle_24));

        initRecyclerView();

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_team);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TeamAdapter(teamMemberList);
        recyclerView.setAdapter(adapter);
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
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }*/
}