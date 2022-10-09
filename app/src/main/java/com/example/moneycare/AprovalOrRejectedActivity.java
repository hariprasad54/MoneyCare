package com.example.moneycare;

import static com.example.moneycare.Constants.AMOUNT;

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

import com.example.moneycare.adapters.MemberSubAdapter;
import com.example.moneycare.adapters.MemberSuperAdapter;
import com.example.moneycare.apicontroler.API;
import com.example.moneycare.apicontroler.PostRequest;
import com.example.moneycare.model.ApprovalRequest;
import com.example.moneycare.model.BasicUserEntity;
import com.example.moneycare.model.MemberSub;
import com.example.moneycare.model.MemberSuper;
import com.example.moneycare.model.UserAuthEntity;

import java.io.IOException;

public class AprovalOrRejectedActivity extends AppCompatActivity {

    private RadioGroup statGroup;
    private RadioButton aproveStat;
    private EditText userEmail;
    private EditText userTrnID;
    private TextView tvRejectionFeedback;
    private EditText feedBack;
    private Button btnSubmitStat;
    private String status;
    private int amount;

    /**
     * TODO Upon approval it should remove the element but it is still showing in the list
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aproval_or_rejected);

        statGroup = findViewById(R.id.radio_group_approve);
        userEmail = findViewById(R.id.et_userEmail);
        userTrnID = findViewById(R.id.et_trnID);
        tvRejectionFeedback = findViewById(R.id.tv_rejection_feedback);
        feedBack = findViewById(R.id.et_multi_line_txt);
        btnSubmitStat = findViewById(R.id.btn_submit_status);
        Intent curIn = getIntent();
        userEmail.setText( curIn.getStringExtra("userEmail"));
        userTrnID.setText(curIn.getStringExtra("transactionId"));
        amount = AMOUNT;
        statGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                aproveStat = (RadioButton) radioGroup.findViewById(i);

                status = aproveStat.getText().toString();
                if (status.equals("Reject")){
                    tvRejectionFeedback.setVisibility(View.VISIBLE);
                    feedBack.setVisibility(View.VISIBLE);
                }
                else if (status.equals("Approve")){
                    tvRejectionFeedback.setVisibility(View.INVISIBLE);
                    feedBack.setVisibility(View.INVISIBLE);
                }
            }
        });

        btnSubmitStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectID = statGroup.getCheckedRadioButtonId();
                if (selectID == -1){
                    Toast.makeText(getApplicationContext(),"Nothing Selected",Toast.LENGTH_SHORT).show();
                }
                else{
                    MemberSub curUser = new MemberSub();
                    for (MemberSub curMmber : AdminApprovalSubActivity.memberSubList){
                        if(curMmber.getEmail().equals(userEmail.getText().toString())){
                            curUser = curMmber;
                        }
                    }
//                    new ApprovalRequest(new UserAuthEntity()
//                            .setUserName(AdminApprovalSubActivity.srcUsrerId),
//                            new BasicUserEntity().setEmail(userEmail.getText().toString()));
                    try {
                        PostRequest.sendRequest(API.APPROVEUSER,new ApprovalRequest(new UserAuthEntity()
                                .setUserName(AdminApprovalSubActivity.srcUsrerId),
                                new BasicUserEntity(curUser), amount).toString());
                        AdminApprovalSubActivity.memberSubList.remove(curUser);
                        //AdminApprovalSuperActivity.memberSuperList.remove(AdminApprovalSubActivity.srcUsrerId);
                        AdminApprovalSubActivity.subAdapter.notifyDataSetChanged();
                        //AdminApprovalSuperActivity.superAdapter.notifyDataSetChanged();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(),"Submitted",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}