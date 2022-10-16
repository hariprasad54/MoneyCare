package com.example.moneycare;

import static com.example.moneycare.Constants.EMPTY_STR;
import static com.example.moneycare.apicontroler.API.ADDBANKACCOUNT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneycare.apicontroler.PostRequest;
import com.example.moneycare.model.BankAccount;
import com.example.moneycare.model.TermsAndConditions;
import com.example.moneycare.model.UserAuthEntity;
import com.example.moneycare.requests.BankAccountRequest;

import java.io.IOException;

public class AddBankAccountActivity extends AppCompatActivity {

    private EditText bName,bAcNumber,bReAcNumber,bIfscCode,bAcHolderName,bUpiID;
    private TextView saveStaus;
    private Button btnAddAccount;
    private RadioButton rbtnTerms;
    private boolean termsStatus;
    private String bankName,acNumber,reAcNumber,IfscCode, userEmail,acHolderName,userUpi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_account);
        Intent userDetails = getIntent();
        userEmail = userDetails.getStringExtra("userEmail");
        bName = findViewById(R.id.et_bank_name_in_add_account);
        bAcNumber = findViewById(R.id.et_acNum_add_account);
        bReAcNumber = findViewById(R.id.et_re_enter_account);
        bIfscCode = findViewById(R.id.et_ifsc_code_in_add_account);
        btnAddAccount = findViewById(R.id.btn_save_details);
        saveStaus = findViewById(R.id.status_add_account);
        rbtnTerms = findViewById(R.id.radio_terms_bankac);
        bAcHolderName = findViewById(R.id.et_name_in_add_account);
        bUpiID = findViewById(R.id.et_upi_in_add_account);


        //terms and conditions
        rbtnTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(AddBankAccountActivity.this);
                alert.setTitle("Terms and Conditions");
                alert.setMessage(TermsAndConditions.tcAddBankAccount);


                alert.setPositiveButton("Agree", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        termsStatus = true;
                        dialog.dismiss();
                    }
                });
                alert.create().show();
            }
        });

        btnAddAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bankName = bName.getText().toString();
                acNumber = bAcNumber.getText().toString();
                reAcNumber = bReAcNumber.getText().toString();
                IfscCode = bIfscCode.getText().toString();
                acHolderName = bAcHolderName.getText().toString();
                userUpi = bUpiID.getText().toString();

                if(validateDetails(bankName,acNumber,reAcNumber,IfscCode)){

                    BankAccount bankAccount = new BankAccount(acNumber,bankName,IfscCode, userUpi,acHolderName);
                    UserAuthEntity srcUser = new UserAuthEntity().setUserName(userEmail);
                    BankAccountRequest br = new BankAccountRequest(srcUser,bankAccount);
                    try {
                       if(!PostRequest.sendRequest(ADDBANKACCOUNT, br.toString()).equals(EMPTY_STR)){
                        saveStaus.setText("Account Details Saved!!");
                        saveStaus.setVisibility(View.VISIBLE);
                           //startActivity(new Intent(getApplicationContext(),BankDetailsActivity.class));
                       }
                       else {
                           saveStaus.setText("Account Details Couldn't be Saved!!");
                           Log.i("AddBank", "Unable to save bank details ");
                       }
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    boolean isEmpty(String str) {
        return TextUtils.isEmpty(str);
    }
    private boolean validateDetails(String bankName, String acNumber, String reAcNumber, String ifscCode) {

        boolean stat = true;
        if (isEmpty(bankName) ||isEmpty(acNumber) || isEmpty(reAcNumber) || isEmpty(ifscCode) || !termsStatus) {
            Toast t = Toast.makeText(this, "All Fields Mandatory!!", Toast.LENGTH_LONG);
            t.show();
            stat = false;
        }
        else if (!acNumber.equalsIgnoreCase(reAcNumber)){
            Toast t = Toast.makeText(this, "Account Number Not Matched", Toast.LENGTH_LONG);
            t.show();
            return false;
        }
        /*if (!termsStatus){
            Toast t = Toast.makeText(this, "Agree to Terms And Conditions", Toast.LENGTH_LONG);
            t.show();
            return false;
        }*/
        return stat;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                Intent intent = new Intent(this, BankDetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, BankDetailsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}