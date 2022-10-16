package com.example.moneycare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private ImageButton cardAddUser,cardPendingApprovals,cardPendingWithdraws,cardAllUsers,cardShareUs,cardVersion;
    private ImageButton btnLogout;
    private  AlertDialog.Builder builder;
    private TextView lastLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btnLogout = findViewById(R.id.btn_logout);
        lastLogin = findViewById(R.id.last_login);

        cardAddUser = findViewById(R.id.imb_add_user);
        cardPendingApprovals = findViewById(R.id.imb_pending_approvals);
        cardPendingWithdraws = findViewById(R.id.imb_pending_withdraws);
        cardAllUsers = findViewById(R.id.imb_all_users);
        cardShareUs = findViewById(R.id.imb_share_us);
        //cardVersion = findViewById(R.id.imb_version);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        lastLogin.setText(String.format("Login Time: %s", dateFormat.format(date)));

        cardAllUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("All users Clicked..");
                Intent intent = new Intent(AdminActivity.this,DisplayAllUsersActivity.class);
                startActivity(intent);
            }
        });

        cardPendingApprovals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addUserIn = new Intent(AdminActivity.this,AdminApprovalSuperActivity.class);
                addUserIn.putExtra("userEmail",LoginActivity.adminUserId);
                startActivity(addUserIn);
            }
        });

        cardPendingWithdraws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addUserIn = new Intent(AdminActivity.this,WithdrawRequestsActivity.class);
                addUserIn.putExtra("userEmail",LoginActivity.adminUserId);
                startActivity(addUserIn);
            }
        });


        cardAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addUserIn = new Intent(AdminActivity.this,RegisterActivity.class);
                addUserIn.putExtra("userEmail",LoginActivity.adminUserId);
                startActivity(addUserIn);
            }
        });

        cardShareUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Intent> targetShareIntents = new ArrayList<Intent>();
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                PackageManager pm = AdminActivity.this.getPackageManager();
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
                            intent.putExtra(Intent.EXTRA_TEXT, "Share App");
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
                        Toast.makeText(AdminActivity.this, "No app to share.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(AdminActivity.this, "logout is clicked",Toast.LENGTH_SHORT).show();

                //logout popup
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
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

         /* cardVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent versionIn = new Intent(AdminActivity.this,VersionInfoActivity.class);
                startActivity(versionIn);
            }
        });*/

          /* cardAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aboutIn = new Intent(AdminActivity.this,AboutUsActivity.class);
                startActivity(aboutIn);
            }
        });*/


    }

    @Override
    public void onBackPressed() {
        builder = new AlertDialog.Builder(AdminActivity.this);
        builder.setTitle("Really Exit?");
        builder.setMessage("Are you sure you want to Logout?");
        builder.setNegativeButton(android.R.string.no, null);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface arg0, int arg1) {
                Intent in = new Intent(AdminActivity.this,LoginActivity.class);
                startActivity(in);
                //MainActivity.super.onBackPressed();
            }
        });
        builder.create().show();
    }
}