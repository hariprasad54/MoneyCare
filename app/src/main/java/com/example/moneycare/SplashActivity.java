package com.example.moneycare;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_DURATION = 1000;
    private boolean permissionStatus;
    ActivityResultLauncher<String[]> mPermissionsResultLauncher;
    private boolean readStoragePermission = false;
    private boolean writeStoragePermission = false;
    private boolean smsPermission = false;
    private boolean cameraPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        mPermissionsResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
            @Override
            public void onActivityResult(Map<String, Boolean> result) {
                if ((result.get(Manifest.permission.SEND_SMS)!=null) && (result.get(Manifest.permission.READ_EXTERNAL_STORAGE)!=null)
                && (result.get(Manifest.permission.CAMERA)!=null)){
                    smsPermission = true;
                    readStoragePermission = true;
                    cameraPermission = true;
                    permissionStatus = true;
                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run() {
                            Intent mainIntent = new Intent(SplashActivity.this,LoginActivity.class);
                            SplashActivity.this.startActivity(mainIntent);
                            SplashActivity.this.finish();
                        }
                    }, SPLASH_DISPLAY_DURATION);

                }
            }
        });

        if (!isNetworkAvailable(SplashActivity.this)){
            Toast.makeText(SplashActivity.this,"No Internet Available",Toast.LENGTH_LONG).show();
        }
        else {
            checkPermission();

            if (permissionStatus) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                        SplashActivity.this.startActivity(mainIntent);
                        SplashActivity.this.finish();
                    }
                }, SPLASH_DISPLAY_DURATION);
            }
        }
    }

    public void checkPermission()
    {
        smsPermission = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.SEND_SMS
        ) == PackageManager.PERMISSION_GRANTED;
        readStoragePermission = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED;
       /* writeStoragePermission = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED;*/
        cameraPermission = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED;

        List<String> requestsPermissions =  new ArrayList<String>();

        if (!smsPermission){
            requestsPermissions.add(Manifest.permission.SEND_SMS);
        }
        if (!readStoragePermission){
            requestsPermissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }/*if (!writeStoragePermission){
            requestsPermissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }*/
        if (!cameraPermission){
            requestsPermissions.add(Manifest.permission.CAMERA);
        }

        if (!requestsPermissions.isEmpty()){
            mPermissionsResultLauncher.launch(requestsPermissions.toArray(new String[0]));
        }
        if (requestsPermissions.isEmpty()){
            permissionStatus = true;
        }
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    /*public boolean isInternetAvailable() {

        try {
            InetAddress address = InetAddress.getByName("google.com");
            return !address.equals("");
        } catch (UnknownHostException e) {
            // Log error
        }
        return false;
    }*/

}
