package com.example.moneycare;

import androidx.annotation.NavigationRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.GameManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.moneycare.adapters.MemberSubAdapter;
import com.example.moneycare.adapters.MemberSuperAdapter;
import com.example.moneycare.model.MemberSuper;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class AdminApprovalSuperActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSuper;
    private List<MemberSuper> memberSuperList;
    private MemberSuperAdapter superAdapter;
    private String userEmail;
    private  AlertDialog.Builder builder;
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
        //actionBar.setDisplayHomeAsUpEnabled(true);


        Intent userDetails = getIntent();
        userEmail = userDetails.getStringExtra("userEmail");


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

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        builder = new AlertDialog.Builder(AdminApprovalSuperActivity.this);
                builder.setTitle("Really Exit?");
                builder.setMessage("Are you sure you want to Logout?");
                builder.setNegativeButton(android.R.string.no, null);
                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent in = new Intent(AdminApprovalSuperActivity.this,LoginActivity.class);
                        startActivity(in);
                        //MainActivity.super.onBackPressed();
                    }
                });
                builder.create().show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}