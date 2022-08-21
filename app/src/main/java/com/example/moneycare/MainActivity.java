package com.example.moneycare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.BuildCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(GravityCompat.START);

            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {

                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id)
                {

                    case R.id.nav_home:
                        Toast.makeText(MainActivity.this, "Home is Clicked", Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_myreferrals:
                        Toast.makeText(MainActivity.this, "My Referrals is Clicked",Toast.LENGTH_SHORT).show();
                        Intent refIn = new Intent(getApplicationContext(),MyReferralsActivity.class);
                        startActivity(refIn);
                        finish();
                        break;
                    case R.id.nav_term_view:
                        Toast.makeText(MainActivity.this, "Team View is Clicked",Toast.LENGTH_SHORT).show();
                        Intent teamIn = new Intent(getApplicationContext(),UserTeamActivity.class);
                        startActivity(teamIn);
                        finish();
                        break;
                    case R.id.nav_our_banks:
                        Toast.makeText(MainActivity.this, "Our Banks is Clicked",Toast.LENGTH_SHORT).show();
                        Intent bankIn = new Intent(getApplicationContext(),BankDetailsActivity.class);
                        startActivity(bankIn);
                        finish();
                        break;
                    case R.id.nav_new_reg:
                        Toast.makeText(MainActivity.this, "New Registration is Clicked",Toast.LENGTH_SHORT).show();
                        Intent regIn = new Intent(getApplicationContext(), RegisterActivity.class);
                        regIn.putExtra("req_mode","home_page");
                        startActivity(regIn);
                        finish();
                        break;
                    case R.id.nav_change_pass:
                        Toast.makeText(MainActivity.this, "Change Password is Clicked",Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(getApplicationContext(), OtpValidationActivity.class);
                        in.putExtra("from","change_pass");
                        startActivity(in);
                        finish();
                        break;
                    case R.id.nav_view_edit_profile:
                        Toast.makeText(MainActivity.this, "View/Edit Password is Clicked",Toast.LENGTH_SHORT).show();
                        Intent profile_in = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(profile_in);
                        finish();
                        break;
                    case R.id.nav_logout:
                        Toast.makeText(MainActivity.this, "logout is clicked",Toast.LENGTH_SHORT).show();

                        //logout popup
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Confirmation!!").
                                setMessage("You sure, that you want to logout?");
                        builder.setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent i = new Intent(getApplicationContext(),
                                                LoginActivity.class);
                                        startActivity(i);
                                    }
                                });
                        builder.setNegativeButton("No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert11 = builder.create();
                        alert11.show();
                        break;
                    case R.id.nav_rate:
                        Toast.makeText(MainActivity.this, "Rate us is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_aboutus:
                        Toast.makeText(MainActivity.this, "About us is Clicked",Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_version:
                        Toast.makeText(MainActivity.this, "Version is Clicked",Toast.LENGTH_SHORT).show();break;
                    default:
                        return true;

                }
                return true;
            }
        });



    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to Logout?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        MainActivity.super.onBackPressed();
                    }
                }).create().show();
    }

}