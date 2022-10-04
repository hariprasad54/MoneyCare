package com.example.moneycare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneycare.apicontroler.API;
import com.example.moneycare.apicontroler.PostRequest;
import com.example.moneycare.model.BankAccount;
import com.example.moneycare.model.UserAuthEntity;
import com.example.moneycare.requests.BankAccountRequest;

import java.io.IOException;

public class EditBankAccountActivity extends AppCompatActivity {

    private EditText bName,bAcNumber,bIfscCode,bAcHolderName,bUpi;
    private TextView saveStaus;
    private Button btnSaveDetails;

    private String bankName,AcNumber,IfscCode,acHolderName,UpiId;
    private String bNameIntent,accountNumberIntent,ifscCodeIntent,acHolderNameIntent,bUpiID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bank_account);

        bName = findViewById(R.id.et_bank_name_in_edit_account);
        bAcNumber = findViewById(R.id.et_acNum_edit_account);
        bIfscCode = findViewById(R.id.et_ifsc_code_in_edit_account);
        btnSaveDetails = findViewById(R.id.btn_save_details);
        saveStaus = findViewById(R.id.status_edit_account);
        bAcHolderName = findViewById(R.id.et_name_in_edit_account);
        bUpi = findViewById(R.id.et_upi_in_edit_account);

        bAcNumber.setFocusable(false);
        bAcNumber.setBackground(getResources().getDrawable(R.drawable.et_custom_disable));

        Intent bDetails = getIntent();
        bNameIntent = bDetails.getStringExtra("bName");
        accountNumberIntent = bDetails.getStringExtra("acNumber");
        ifscCodeIntent = bDetails.getStringExtra("ifscCode");
        acHolderNameIntent = bDetails.getStringExtra("acHolderName");
        bUpiID = bDetails.getStringExtra("UPI");
        initDetails(bNameIntent,accountNumberIntent,ifscCodeIntent,acHolderNameIntent,bUpiID);

        btnSaveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bankName = bName.getText().toString();
                AcNumber = bAcNumber.getText().toString();
                IfscCode = bIfscCode.getText().toString();
                acHolderName = bAcHolderName.getText().toString();
                UpiId = bUpi.getText().toString();

                if(validateDetails(bankName,AcNumber,IfscCode,acHolderName,UpiId)){

                    BankAccount bankAccount = new BankAccount(AcNumber,bankName, IfscCode, UpiId,acHolderName);
                    UserAuthEntity srcUser = new UserAuthEntity().setUserName(LoginActivity.userId);
                    BankAccountRequest br = new BankAccountRequest(srcUser, bankAccount);
                    try {
                        PostRequest.sendRequest(API.ADDBANKACCOUNT, br.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    saveStaus.setText("Account Details Saved!!");
                    saveStaus.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private void initDetails(String bNameIntent, String accountNumberIntent, String ifscCodeIntent, String acHolderNameIntent, String bUpiID) {
        bName.setText(bNameIntent);
        bAcNumber.setText(accountNumberIntent);
        bIfscCode.setText(ifscCodeIntent);
        bAcHolderName.setText(acHolderNameIntent);
        bUpi.setText(bUpiID);
    }

    boolean isEmpty(String str) {
        return TextUtils.isEmpty(str);
    }
    private boolean validateDetails(String bankName, String acNumber, String ifscCode, String holderName, String strUpi) {
        boolean stat = true;
        if (isEmpty(bankName) ||isEmpty(acNumber) || isEmpty(ifscCode) || isEmpty(holderName)|| isEmpty(strUpi)) {
            Toast t = Toast.makeText(this, "All Fields Mandatory to Save!", Toast.LENGTH_LONG);
            t.show();
            stat = false;
        }
        return stat;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, BankDetailsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}