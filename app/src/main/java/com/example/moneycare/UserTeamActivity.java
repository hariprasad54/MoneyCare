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
import com.example.moneycare.model.BankAccount;
import com.example.moneycare.model.TeamMember;

import java.util.ArrayList;
import java.util.List;

public class UserTeamActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<TeamMember> teamMemberList;
    private TeamAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_team);

        teamMemberList = new ArrayList<>();
        teamMemberList.add(new TeamMember("abc@abc.com","1234567890",R.drawable.ic_baseline_account_circle_24));
        teamMemberList.add(new TeamMember("def@abc.com","4567890123",R.drawable.ic_baseline_account_circle_24));
        teamMemberList.add(new TeamMember("ghi@abc.com","3456789012",R.drawable.ic_baseline_account_circle_24));
        teamMemberList.add(new TeamMember("klm@abc.com","8901234567",R.drawable.ic_baseline_account_circle_24));
        teamMemberList.add(new TeamMember("nop@abc.com","6789054321",R.drawable.ic_baseline_account_circle_24));

        initRecyclerView();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_team);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TeamAdapter(teamMemberList);
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