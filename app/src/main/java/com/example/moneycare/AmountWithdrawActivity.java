package com.example.moneycare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AmountWithdrawActivity extends AppCompatActivity {

    private RadioButton rbn;
    private Button btnWithdrawAmount;
    private EditText etAmountToWithdraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_withdraw);

        btnWithdrawAmount = findViewById(R.id.btn_amt_withdraw);
        etAmountToWithdraw = findViewById(R.id.amt_to_withdraw);

        RadioGroup rgp = (RadioGroup) findViewById(R.id.radio_bank_accounts_in_with);

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