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
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.StrictMode;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneycare.apicontroler.API;
import com.example.moneycare.apicontroler.GetRequest;
import com.example.moneycare.model.BankAccount;
import com.example.moneycare.model.BasicUserEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private String userEmail;
    private TextView tvEmail,tvRefAmount, tvTermDeposited;
    private CircleImageView profilePic;
    private TextView welcome,lastLogin;
    private ImageButton btnLogout;
    private BasicUserEntity userProfile;
    public static List<Integer> totalEarningsList;
    public static int totalEarnings;
    public static int availBalance;
    private ImageButton cardAddUser,cardMyTeam,cardBankAccounts,cardProfile,cardAboutUs,cardRateUs,cardShareUs,cardVersion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);*/

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        btnLogout = findViewById(R.id.btn_logout);
        welcome = findViewById(R.id.username_welcome);
        lastLogin = findViewById(R.id.last_login);
        tvRefAmount = findViewById(R.id.tv_ref_amount);
        tvTermDeposited = findViewById(R.id.tv_term_deposited);


        //quick links buttons
        cardAddUser = findViewById(R.id.imb_add_user);
        cardMyTeam = findViewById(R.id.imb_my_team);
        cardBankAccounts = findViewById(R.id.imb_bank_accounts);
        cardProfile = findViewById(R.id.imb_profile);
        cardAboutUs = findViewById(R.id.imb_about_us);
        cardRateUs = findViewById(R.id.imb_rate_us);
        cardShareUs = findViewById(R.id.imb_share_us);
        cardVersion = findViewById(R.id.imb_version);


        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        lastLogin.setText(String.format("Login Time: %s", dateFormat.format(date)));


        Intent loginDetails = getIntent();
        userEmail = loginDetails.getStringExtra("userEmail");
        totalEarningsList = new ArrayList<>();
        try {
            totalEarningsList = new ObjectMapper().readValue(GetRequest.sendRequest(API.GETTOTALAMOIUNT + LoginActivity.userId), new TypeReference<List<Integer>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        totalEarnings = totalEarningsList.get(0);
        availBalance = totalEarningsList.get(1);
        tvRefAmount.setText("₹ "+String.valueOf(totalEarnings));


        try {
            userProfile =  new ObjectMapper().readValue(GetRequest.sendRequest(API.GETUSERDETAILS+LoginActivity.userId), new TypeReference<BasicUserEntity>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        welcome.setText(String.format("%s %s", userProfile.getFirstName(), userProfile.getLastName()));
        View nhView = navigationView.getHeaderView(0);
        tvEmail = nhView.findViewById(R.id.emailUser);
        tvEmail.setText(userProfile.getEmail());


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
                        Intent in = new Intent(getApplicationContext(), ChangePasswordActivity.class);
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
                addUserIn.putExtra("userEmail",userEmail);
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

        cardShareUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Intent> targetShareIntents = new ArrayList<Intent>();
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                PackageManager pm = MainActivity.this.getPackageManager();
                List<ResolveInfo> resInfos = pm.queryIntentActivities(shareIntent, 0);
                if (!resInfos.isEmpty()) {
                    System.out.println("Have package");
                    for (ResolveInfo resInfo : resInfos) {
                        String packageName = resInfo.activityInfo.packageName;
                        Log.i("Package Name", packageName);

                        if (packageName.contains("com.twitter.android") || packageName.contains("com.facebook.katana")
                                || packageName.contains("com.whatsapp") || packageName.contains("com.google.android.apps.plus")
                                || packageName.contains("com.google.android.talk")
                                || packageName.contains("com.google.android.gm") || packageName.contains("com.facebook.orca")
                                || packageName.contains("com.yahoo.mobile") || packageName.contains("com.android.messaging")
                                || packageName.contains("com.android.mms")|| packageName.contains("com.android.bluetooth")
                                || packageName.contains("com.google.android.apps.messaging")) {
                            Intent intent = new Intent();

                            intent.setComponent(new ComponentName(packageName, resInfo.activityInfo.name));
                            intent.putExtra("AppName", resInfo.loadLabel(pm).toString());
                            intent.setAction(Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_TEXT, "https://website.com/");
                            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_text));
                            intent.setPackage(packageName);
                            targetShareIntents.add(intent);
                        }
                    }
                    if (!targetShareIntents.isEmpty()) {
                        Collections.sort(targetShareIntents, new Comparator<Intent>() {
                            @Override
                            public int compare(Intent o1, Intent o2) {
                                return o1.getStringExtra("AppName").compareTo(o2.getStringExtra("AppName"));
                            }
                        });
                        Intent chooserIntent = Intent.createChooser(targetShareIntents.remove(0), "Select app to share");
                        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetShareIntents.toArray(new Parcelable[]{}));
                        startActivity(chooserIntent);
                    } else {
                        Toast.makeText(MainActivity.this, "No app to share.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        cardVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent versionIn = new Intent(MainActivity.this,VersionInfoActivity.class);
                startActivity(versionIn);
            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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