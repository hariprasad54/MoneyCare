package com.example.moneycare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.GameManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import com.example.moneycare.adapters.MemberSubAdapter;
import com.example.moneycare.adapters.MemberSuperAdapter;
import com.example.moneycare.model.MemberSuper;

import java.util.ArrayList;
import java.util.List;

public class AdminApprovalSuperActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSuper;
    private List<MemberSuper> memberSuperList;
    private MemberSuperAdapter superAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_approval_super);

        memberSuperList = new ArrayList<>();
        memberSuperList.add(new MemberSuper("abc@abc.com",R.drawable.ic_baseline_account_circle_24));
        memberSuperList.add(new MemberSuper("def@abc.com",R.drawable.ic_baseline_account_circle_24));
        memberSuperList.add(new MemberSuper("ghi@abc.com",R.drawable.ic_baseline_account_circle_24));
        memberSuperList.add(new MemberSuper("jkl@abc.com",R.drawable.ic_baseline_account_circle_24));
        memberSuperList.add(new MemberSuper("mno@abc.com",R.drawable.ic_baseline_account_circle_24));
        memberSuperList.add(new MemberSuper("pqr@abc.com",R.drawable.ic_baseline_account_circle_24));
        memberSuperList.add(new MemberSuper("stu@abc.com",R.drawable.ic_baseline_account_circle_24));
        memberSuperList.add(new MemberSuper("vwx@abc.com",R.drawable.ic_baseline_account_circle_24));
        memberSuperList.add(new MemberSuper("xyz@abc.com",R.drawable.ic_baseline_account_circle_24));

        initRecyclerView();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_refresh1,menu);
        return true;

    }

    private void initRecyclerView() {
        recyclerViewSuper = (RecyclerView) findViewById(R.id.recyclerview_sup_req);
        recyclerViewSuper.setHasFixedSize(true);
        recyclerViewSuper.setLayoutManager(new LinearLayoutManager(this));
        superAdapter = new MemberSuperAdapter(memberSuperList);
        recyclerViewSuper.setAdapter(superAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.refresh_sup:
                break;
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