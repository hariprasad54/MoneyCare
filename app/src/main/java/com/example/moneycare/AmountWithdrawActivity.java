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
import android.widget.Toast;

import com.example.moneycare.apicontroler.API;
import com.example.moneycare.apicontroler.GetRequest;
import com.example.moneycare.apicontroler.PostRequest;
import com.example.moneycare.model.BankAccount;
import com.example.moneycare.model.Transaction;
import com.example.moneycare.model.UserAuthEntity;
import com.example.moneycare.requests.AddWithdrawRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AmountWithdrawActivity extends AppCompatActivity {

    private RadioButton rbn,rbnBankAccount,rbnUpi;
    private RadioGroup paymentOpt;
    private Button btnWithdrawAmount;
    private EditText etAmountToWithdraw,etUpiId;
    private List<BankAccount> bankAccountList;
    private CardView cardBankAccounts;
    private TextView selBank;
    private String userId,trnDate,trnAmout;
    private int totalAmount;
    private String response;

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
                rbn.setText(bankAccountList.get(i).getBankName() + " " + bankAccountList.get(i).getAccountNo());
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

        btnWithdrawAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId = LoginActivity.userId;
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                trnDate = dateFormat.format(date);
                trnAmout = etAmountToWithdraw.getText().toString();
                totalAmount = MainActivity.totalEarnings;

                /*if (Integer.parseInt(trnAmout) > totalAmount){
                    etAmountToWithdraw.setError("Insufficient Amount");
                    Toast.makeText(AmountWithdrawActivity.this, "Insufficient Amount", Toast.LENGTH_SHORT).show();
                }
                else{

                }*/

                UserAuthEntity srcUser = new UserAuthEntity().setUserName(userId);
                Transaction userTransaction = new Transaction(userId,trnDate,trnAmout);

                AddWithdrawRequest addWithdrawRequest = new AddWithdrawRequest(srcUser,userTransaction);

                try {
                    response = PostRequest.sendRequest(API.ADDWITHDRAWREQUEST,addWithdrawRequest.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (response.equalsIgnoreCase("Invalid User")){
                    Toast.makeText(AmountWithdrawActivity.this, "Invalid User", Toast.LENGTH_SHORT).show();
                }
                else if (response.equalsIgnoreCase("request added")){
                    Toast.makeText(AmountWithdrawActivity.this, "Withdraw Request Added", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}