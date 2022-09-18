package com.example.moneycare;

import static com.example.moneycare.Constants.EMPTY_STR;
import static com.example.moneycare.apicontroler.API.ADDBANKACCOUNT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneycare.apicontroler.PostRequest;
import com.example.moneycare.model.BankAccount;
import com.example.moneycare.model.UserAuthEntity;
import com.example.moneycare.requests.BankAccountRequest;

import java.io.IOException;

public class AddBankAccountActivity extends AppCompatActivity {

    private EditText bName,bAcNumber,bReAcNumber,bIfscCode;
    private TextView saveStaus;
    private Button btnAddAccount;

    private String bankName,acNumber,reAcNumber,IfscCode, userEmail;

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

        btnAddAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bankName = bName.getText().toString();
                acNumber = bAcNumber.getText().toString();
                reAcNumber = bReAcNumber.getText().toString();
                IfscCode = bIfscCode.getText().toString();

                if(validateDetails(bankName,acNumber,reAcNumber,IfscCode)){

                    BankAccount bankAccount = new BankAccount(acNumber,bankName,IfscCode, "NameTobeFixed");
                    UserAuthEntity srcUser = new UserAuthEntity().setUserName(userEmail);
                    BankAccountRequest br = new BankAccountRequest(srcUser,bankAccount);
                    try {
                       if(!PostRequest.sendRequest(ADDBANKACCOUNT, br.toString()).equals(EMPTY_STR)){
                        saveStaus.setText("Account Details Saved!!");
                        saveStaus.setVisibility(View.VISIBLE);
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
        if (isEmpty(bankName) ||isEmpty(acNumber) || isEmpty(reAcNumber) || isEmpty(ifscCode)) {
            Toast t = Toast.makeText(this, "All Fields Mandatory!!", Toast.LENGTH_LONG);
            t.show();
            stat = false;
        }
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