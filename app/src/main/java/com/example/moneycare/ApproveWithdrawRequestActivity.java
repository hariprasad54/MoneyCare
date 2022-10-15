package com.example.moneycare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneycare.apicontroler.API;
import com.example.moneycare.apicontroler.PostRequest;
import com.example.moneycare.model.ApprovalRequest;
import com.example.moneycare.model.ApprovalWithdrawRequest;
import com.example.moneycare.model.BasicUserEntity;
import com.example.moneycare.model.MemberSub;
import com.example.moneycare.model.Transaction;
import com.example.moneycare.model.UserAuthEntity;
import com.example.moneycare.model.WithdrawRequest;

import java.io.IOException;

public class ApproveWithdrawRequestActivity extends AppCompatActivity {

    private RadioGroup statGroup;
    private RadioButton aproveStat;
    private EditText userEmail,userUpiId;
    private EditText userTrnID;
    private TextView tvRejectionFeedback;
    private EditText feedBack;
    private Button btnSubmitStat;
    private String status,trnStatus;
    private String response;
    private String upiId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_withdraw_request);

        statGroup = findViewById(R.id.radio_group_approve);
        userEmail = findViewById(R.id.et_userId);
        userTrnID = findViewById(R.id.et_Amount);
        userUpiId = findViewById(R.id.et_UpiId);
        tvRejectionFeedback = findViewById(R.id.tv_rejection_feedback);
        feedBack = findViewById(R.id.et_multi_line_txt);
        btnSubmitStat = findViewById(R.id.btn_submit_status);
        Intent curIn = getIntent();
        userEmail.setText( curIn.getStringExtra("userId"));
        userTrnID.setText(curIn.getStringExtra("amount"));
        upiId = curIn.getStringExtra("UPI");
        userUpiId.setText(upiId);
        statGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                aproveStat = (RadioButton) radioGroup.findViewById(i);

                status = aproveStat.getText().toString();
                if (status.equals("Reject")){
                    trnStatus = "Reject";
                    tvRejectionFeedback.setVisibility(View.VISIBLE);
                    feedBack.setVisibility(View.VISIBLE);
                }
                else if (status.equals("Approve")){
                    trnStatus = "Approve";
                    tvRejectionFeedback.setVisibility(View.INVISIBLE);
                    feedBack.setVisibility(View.INVISIBLE);
                }
            }
        });

        btnSubmitStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectID = statGroup.getCheckedRadioButtonId();
                System.out.println(aproveStat.getText().toString());
                if (selectID == -1){
                    Toast.makeText(getApplicationContext(),"Nothing Selected",Toast.LENGTH_SHORT).show();
                }
                else{

                    WithdrawRequest withdrawRequest = new WithdrawRequest();
                    for (WithdrawRequest wr : WithdrawRequestsActivity.withdrawRequestList){
                        if (wr.getUserId().equalsIgnoreCase(userEmail.getText().toString())){
                            withdrawRequest = wr;
                        }
                    }
                    withdrawRequest.setStatus(trnStatus);
                    withdrawRequest.setTrnUpiId(upiId);
                    try {
                        response = PostRequest.sendRequest(API.APPROVEWITHDRAWREQUEST,new ApprovalWithdrawRequest(new UserAuthEntity().setUserName(userEmail.getText().toString()),new Transaction(withdrawRequest)).toString());
                        System.out.println(response);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (response.equalsIgnoreCase("Invalid User")){
                        Toast.makeText(getApplicationContext(),"Invalid User",Toast.LENGTH_SHORT).show();

                    }
                    else if (response.equalsIgnoreCase("Success") ||response.equalsIgnoreCase("Insuficient") ) {
                        WithdrawRequestsActivity.withdrawRequestList.remove(withdrawRequest);
                        WithdrawRequestsActivity.adapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "Submitted", Toast.LENGTH_SHORT).show();
                        btnSubmitStat.setVisibility(View.INVISIBLE);
                        statGroup.setEnabled(false);
                        //startActivity(new Intent(getApplicationContext(),WithdrawRequestsActivity.class));
                    }
                }
            }
        });

    }
}