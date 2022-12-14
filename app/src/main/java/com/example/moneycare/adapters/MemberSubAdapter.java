package com.example.moneycare.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.moneycare.AprovalOrRejectedActivity;
import com.example.moneycare.R;
import com.example.moneycare.model.MemberSub;

import java.util.List;

public class MemberSubAdapter extends RecyclerView.Adapter<MemberSubAdapter.ViewHolder> {

    private List<MemberSub> memberSubList;

    public MemberSubAdapter(List<MemberSub> memberSubList){
        this.memberSubList = memberSubList;
    }

    @NonNull
    @Override
    public MemberSubAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_sub_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberSubAdapter.ViewHolder holder, int position) {

        String email = memberSubList.get(position).getEmail();
        String phone = memberSubList.get(position).getPhone();
        String trnId = memberSubList.get(position).getTransactionID();

        holder.setData(email,phone,trnId);
    }

    @Override
    public int getItemCount() {
        return memberSubList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvEmail,tvPhone, tvTrnId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            tvEmail = itemView.findViewById(R.id.item_trn_email_sub);
            tvPhone = itemView.findViewById(R.id.item_trn_phone_sub);
            tvTrnId = itemView.findViewById(R.id.item_trn_id_sub);


        }

        public void setData(String email, String phone, String trnId) {
            tvEmail.setText(email);
            tvPhone.setText(phone);
            tvTrnId.setText(trnId);
        }

        @Override
        public void onClick(View view) {
            int position = this.getAbsoluteAdapterPosition();
            MemberSub memberSub = memberSubList.get(position);
            String email = memberSub.getEmail();
            String trnId = memberSub.getTransactionID();
            Toast.makeText(view.getContext(), email +"\n"+trnId,Toast.LENGTH_SHORT).show();
            Intent aprovalIn = new Intent(view.getContext(), AprovalOrRejectedActivity.class);
            aprovalIn.putExtra("userEmail", email);
            aprovalIn.putExtra("transactionId", trnId);
            view.getContext().startActivity(aprovalIn);
        }
    }
}
