package com.example.moneycare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.moneycare.apicontroler.API;
import com.example.moneycare.apicontroler.GetRequest;
import com.example.moneycare.model.BankAccount;
import com.example.moneycare.model.Transaction;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AmountDepositActivity extends AppCompatActivity {

    private  RadioButton rbn;
    private Button btnDepositAmount;
    private EditText etAmountToDeposit;
    private List<BankAccount> bankAccountList;
    private CardView cardBankAccounts;
    private TextView selBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_deposit);

        btnDepositAmount = findViewById(R.id.btn_amt_deposit);
        etAmountToDeposit = findViewById(R.id.amt_to_deposit);
        cardBankAccounts = findViewById(R.id.card_bank_accounts);
        selBank = findViewById(R.id.sel_bank);

        RadioGroup rgp = (RadioGroup) findViewById(R.id.radio_bank_accounts);

        bankAccountList = new ArrayList<>();
        try {
            bankAccountList = new ObjectMapper().readValue(GetRequest.sendRequest(API.GETBANKACCOUNTS+LoginActivity.userId), new TypeReference<List<BankAccount>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        int buttons = bankAccountList.size();
        if (buttons > 0) {
            for (int i = 0; i < buttons; i++) {
                rbn = new RadioButton(this);
                rbn.setId(View.generateViewId());
                rbn.setText(bankAccountList.get(i).getBankName() + " " + bankAccountList.get(i).getAccountNo());
                rbn.setTextSize(16);
                rbn.setPadding(0, 25, 0, 25);
                rgp.addView(rbn);
            }
        }
        else{
            cardBankAccounts.setVisibility(View.INVISIBLE);
            btnDepositAmount.setVisibility(View.INVISIBLE);
            selBank.setText("No Bank Accounts Found! Add Bank Account to Deposit");
        }
    }
}