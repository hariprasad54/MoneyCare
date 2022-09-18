package com.example.moneycare;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.os.BuildCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private String userEmail;
    private TextView tvEmail;
    private CircleImageView profilePic;
    private ImageButton cardAddUser,cardMyTeam,cardBankAccounts,cardProfile,cardAboutUs,cardRateUs;
    private ImageButton socialMessage,socialWhatsapp,socialInstagram;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        //quick links buttons
        cardAddUser = findViewById(R.id.imb_add_user);
        cardMyTeam = findViewById(R.id.imb_my_team);
        cardBankAccounts = findViewById(R.id.imb_bank_accounts);
        cardProfile = findViewById(R.id.imb_profile);
        cardAboutUs = findViewById(R.id.imb_about_us);
        cardRateUs = findViewById(R.id.imb_rate_us);

        //social media buttons
        socialMessage = findViewById(R.id.social_message_app);
        socialWhatsapp = findViewById(R.id.social_whatsapp);
        socialInstagram = findViewById(R.id.social_instagram);

        //toolbar.inflateMenu(R.menu.menu_admin);

        Intent loginDetails = getIntent();
        userEmail = loginDetails.getStringExtra("userEmail");

        View nhView = navigationView.getHeaderView(0);
        tvEmail = nhView.findViewById(R.id.emailUser);
        tvEmail.setText(userEmail);


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
                        break;
                    case R.id.nav_term_view:
                        Toast.makeText(MainActivity.this, "Team View is Clicked",Toast.LENGTH_SHORT).show();
                        Intent teamIn = new Intent(getApplicationContext(),UserTeamActivity.class);
                        teamIn.putExtra("userEmail", userEmail);
                        startActivity(teamIn);
                        break;
                    case R.id.nav_our_banks:
                        Toast.makeText(MainActivity.this, "Our Banks is Clicked",Toast.LENGTH_SHORT).show();

                        Intent bankIn = new Intent(getApplicationContext(),BankDetailsActivity.class);
                        bankIn.putExtra("userEmail",userEmail);
                        startActivity(bankIn);
                        break;
                    case R.id.nav_new_reg:
                        Toast.makeText(MainActivity.this, "New Registration is Clicked",Toast.LENGTH_SHORT).show();
                        Intent regIn = new Intent(getApplicationContext(), RegisterActivity.class);
                        regIn.putExtra("userEmail", userEmail);
                        regIn.putExtra("req_mode","home_page");
                        startActivity(regIn);
                        break;
                    case R.id.nav_change_pass:
                        Toast.makeText(MainActivity.this, "Change Password is Clicked",Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(getApplicationContext(), OtpValidationActivity.class);
                        in.putExtra("from","change_pass");
                        startActivity(in);
                        break;
                    case R.id.nav_view_edit_profile:
                        Toast.makeText(MainActivity.this, "View/Edit Password is Clicked",Toast.LENGTH_SHORT).show();
                        Intent profile_in = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(profile_in);
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
                        Toast.makeText(MainActivity.this, "Rate us is Clicked",Toast.LENGTH_SHORT).show();
                        Intent rate_in = new Intent(MainActivity.this, RateUsActivity.class);
                        startActivity(rate_in);
                        break;
                    case R.id.nav_aboutus:
                        Toast.makeText(MainActivity.this, "About us is Clicked",Toast.LENGTH_SHORT).show();
                        Intent about_in = new Intent(MainActivity.this, AboutUsActivity.class);
                        startActivity(about_in);
                        break;
                    case R.id.nav_version:
                        Toast.makeText(MainActivity.this, "Version is Clicked",Toast.LENGTH_SHORT).show();
                        Intent version_in = new Intent(MainActivity.this, VersionInfoActivity.class);
                        startActivity(version_in);
                        break;
                    default:
                        return true;

                }
                return true;
            }
        });


        //quick link listeners
        cardAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addUserIn = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(addUserIn);
            }
        });

        cardMyTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myTeamIn = new Intent(MainActivity.this,UserTeamActivity.class);
                startActivity(myTeamIn);
            }
        });

        cardBankAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bankAcIn = new Intent(MainActivity.this,BankDetailsActivity.class);
                startActivity(bankAcIn);
            }
        });

        cardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIn = new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(profileIn);
            }
        });

        cardAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aboutIn = new Intent(MainActivity.this,AboutUsActivity.class);
                startActivity(aboutIn);
            }
        });

        cardRateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rateusIn = new Intent(MainActivity.this,RateUsActivity.class);
                startActivity(rateusIn);
            }
        });

        //social media links listeners

        socialMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                }catch (ActivityNotFoundException e){
                    Log.d("error","Activity not found");
                }

            }
        });

        socialInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        socialWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                        Intent in = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(in);
                        //MainActivity.super.onBackPressed();
                    }
                }).create().show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}