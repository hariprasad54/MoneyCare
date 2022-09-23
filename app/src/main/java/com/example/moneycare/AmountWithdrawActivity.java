package com.example.moneycare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AmountWithdrawActivity extends AppCompatActivity {

    private RadioButton rbn;
    private Button btnWithdrawAmount;
    private EditText etAmountToWithdraw;
    private List<BankAccount> bankAccountList;
    private CardView cardBankAccounts;
    private TextView selBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_withdraw);

        btnWithdrawAmount = findViewById(R.id.btn_amt_withdraw);
        etAmountToWithdraw = findViewById(R.id.amt_to_withdraw);
        cardBankAccounts = findViewById(R.id.card_bank_accounts);
        selBank = findViewById(R.id.sel_bank);

        RadioGroup rgp = (RadioGroup) findViewById(R.id.radio_bank_accounts_in_with);

        bankAccountList = new ArrayList<>();
        try {
            bankAccountList = new ObjectMapper().readValue(GetRequest.sendRequest(API.GETBANKACCOUNTS+LoginActivity.userId), new TypeReference<List<BankAccount>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        int buttons = bankAccountList.size() ;
        if (buttons > 0) {
            for (int i = 0; i < buttons; i++) {
                rbn = new RadioButton(this);
                rbn.setId(View.generateViewId());
                rbn.setText(BankDetailsActivity.bankAccountList.get(i).getBankName() + " " + BankDetailsActivity.bankAccountList.get(i).getAccountNo());
                rbn.setTextSize(16);
                rbn.setPadding(0, 25, 0, 25);
                rgp.addView(rbn);
            }
        }
        else {
            cardBankAccounts.setVisibility(View.INVISIBLE);
            btnWithdrawAmount.setVisibility(View.INVISIBLE);
            selBank.setText("No Bank Accounts Found! Add Bank Account to Withdraw");
        }
    }
}