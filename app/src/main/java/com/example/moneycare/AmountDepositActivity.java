package com.example.moneycare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.moneycare.model.BankAccount;
import com.example.moneycare.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class AmountDepositActivity extends AppCompatActivity {

    private  RadioButton rbn;
    private Button btnDepositAmount;
    private EditText etAmountToDeposit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_deposit);

        btnDepositAmount = findViewById(R.id.btn_amt_deposit);
        etAmountToDeposit = findViewById(R.id.amt_to_deposit);

        RadioGroup rgp = (RadioGroup) findViewById(R.id.radio_bank_accounts);

        int buttons = 2 ;
        for (int i = 1; i <= buttons ; i++) {
            rbn = new RadioButton(this);
            rbn.setId(View.generateViewId());
            rbn.setText("Bank Name"+i+" - 1234");
            rbn.setTextSize(16);
            rbn.setPadding(0,25,0,25);
            rgp.addView(rbn);
        }
    }
}