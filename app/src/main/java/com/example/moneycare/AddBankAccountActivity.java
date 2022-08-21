package com.example.moneycare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.transition.MaterialSharedAxis;

public class AddBankAccountActivity extends AppCompatActivity {

    private EditText bName,bAcNumber,bReAcNumber,bIfscCode;
    private TextView saveStaus;
    private Button btnAddAccount;

    private String bankName,AcNumber,reAcNumber,IfscCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_account);

        bName = findViewById(R.id.et_bank_name_in_add_account);
        bAcNumber = findViewById(R.id.et_acNum_add_account);
        bReAcNumber = findViewById(R.id.et_re_enter_account);
        bIfscCode = findViewById(R.id.et_ifsc_code_in_add_account);
        btnAddAccount = findViewById(R.id.btn_add_account);
        saveStaus = findViewById(R.id.status_add_account);

        btnAddAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bankName = bName.getText().toString();
                AcNumber = bAcNumber.getText().toString();
                reAcNumber = bReAcNumber.getText().toString();
                IfscCode = bIfscCode.getText().toString();

                if(validateDetails(bankName,AcNumber,reAcNumber,IfscCode)){

                    saveStaus.setText("Account Details Saved!!");
                    saveStaus.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    boolean isEmpty(String str) {
        return TextUtils.isEmpty(str);
    }
    private boolean validateDetails(String bankName, String acNumber, String reAcNumber, String ifscCode) {

        boolean stat = true;
        if (isEmpty(bankName) ||isEmpty(acNumber) || isEmpty(reAcNumber) || isEmpty(ifscCode)) {
            Toast t = Toast.makeText(this, "All Fields Mandatory to Register!", Toast.LENGTH_LONG);
            t.show();
            stat = false;
        }
        return stat;
    }
}