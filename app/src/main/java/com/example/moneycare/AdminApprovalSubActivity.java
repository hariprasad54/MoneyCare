package com.example.moneycare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.moneycare.adapters.MemberSubAdapter;
import com.example.moneycare.adapters.MemberSuperAdapter;
import com.example.moneycare.adapters.TeamAdapter;
import com.example.moneycare.model.MemberSub;

import java.util.ArrayList;
import java.util.List;

public class AdminApprovalSubActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSub;
    private List<MemberSub> memberSubList;
    private MemberSubAdapter subAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_approval_sub);

        memberSubList = new ArrayList<>();
        memberSubList.add(new MemberSub("abc@abc.com","9182736450","Trn123456789"));
        memberSubList.add(new MemberSub("def@abc.com","1234567890","Trn918273645"));
        memberSubList.add(new MemberSub("ghi@abc.com","9876543210","Trn918273645"));

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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, AdminApprovalSuperActivity.class);
        intent.putExtra("userEmail","admin@test.com");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}