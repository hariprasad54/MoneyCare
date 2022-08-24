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

public class EditBankAccountActivity extends AppCompatActivity {

    private EditText bName,bAcNumber,bIfscCode;
    private TextView saveStaus;
    private Button btnSaveDetails;

    private String bankName,AcNumber,IfscCode;
    private String bNameIntent,accountNumberIntent,ifscCodeIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bank_account);

        bName = findViewById(R.id.et_bank_name_in_edit_account);
        bAcNumber = findViewById(R.id.et_acNum_edit_account);
        bIfscCode = findViewById(R.id.et_ifsc_code_in_edit_account);
        btnSaveDetails = findViewById(R.id.btn_save_details);
        saveStaus = findViewById(R.id.status_edit_account);

        Intent bDetails = getIntent();
        bNameIntent = bDetails.getStringExtra("bName");
        accountNumberIntent = bDetails.getStringExtra("acNumber");
        ifscCodeIntent = bDetails.getStringExtra("ifscCode");
        initDetails(bNameIntent,accountNumberIntent,ifscCodeIntent);

        btnSaveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bankName = bName.getText().toString();
                AcNumber = bAcNumber.getText().toString();
                IfscCode = bIfscCode.getText().toString();

                if(validateDetails(bankName,AcNumber,IfscCode)){

                    saveStaus.setText("Account Details Saved!!");
                    saveStaus.setVisibility(View.VISIBLE);
                }
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initDetails(String bNameIntent, String accountNumberIntent, String ifscCodeIntent) {
        bName.setText(bNameIntent);
        bAcNumber.setText(accountNumberIntent);
        bIfscCode.setText(ifscCodeIntent);
    }

    boolean isEmpty(String str) {
        return TextUtils.isEmpty(str);
    }
    private boolean validateDetails(String bankName, String acNumber, String ifscCode) {
        boolean stat = true;
        if (isEmpty(bankName) ||isEmpty(acNumber) || isEmpty(ifscCode)) {
            Toast t = Toast.makeText(this, "All Fields Mandatory to Save!", Toast.LENGTH_LONG);
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