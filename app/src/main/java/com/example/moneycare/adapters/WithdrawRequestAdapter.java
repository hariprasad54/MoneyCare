package com.example.moneycare.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneycare.ApproveWithdrawRequestActivity;
import com.example.moneycare.AprovalOrRejectedActivity;
import com.example.moneycare.R;
import com.example.moneycare.model.WithdrawRequest;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class WithdrawRequestAdapter extends RecyclerView.Adapter<WithdrawRequestAdapter.ViewHolder>{

    private List<WithdrawRequest> withdrawRequestList;

    public WithdrawRequestAdapter(List<WithdrawRequest> withdrawRequestList){this.withdrawRequestList = withdrawRequestList;}

    @NonNull
    @Override
    public WithdrawRequestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_withdraw_request,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WithdrawRequestAdapter.ViewHolder holder, int position) {
        String wrUserID = withdrawRequestList.get(position).getUserId();
        String wrDate = withdrawRequestList.get(position).getDate();
        String wrAmount = withdrawRequestList.get(position).getAmount();

        holder.setData(wrUserID,wrDate,wrAmount);
    }

    @Override
    public int getItemCount() {
        return withdrawRequestList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvWrUserID,tvWrDate,tvWrAmount;
        private CircleImageView circleImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvWrUserID = itemView.findViewById(R.id.item_wr_email);
            tvWrDate = itemView.findViewById(R.id.item_wr_date);
            tvWrAmount = itemView.findViewById(R.id.item_wr_amt);
            circleImageView = itemView.findViewById(R.id.mem_profile_pic);
            circleImageView.setImageResource(R.drawable.user1);

        }

        public void setData(String wrUserID, String wrDate, String wrAmount) {
            tvWrUserID.setText(wrUserID);
            tvWrDate.setText(wrDate);
            tvWrAmount.setText(wrAmount);
        }

        @Override
        public void onClick(View view) {
            int position = this.getAbsoluteAdapterPosition();
            WithdrawRequest withdrawRequest = withdrawRequestList.get(position);
            String userId = withdrawRequest.getUserId();
            String amount = withdrawRequest.getAmount();
            Toast.makeText(view.getContext(), userId+" Clicked", Toast.LENGTH_SHORT).show();
            Intent aprovalIn = new Intent(view.getContext(), ApproveWithdrawRequestActivity.class);
            aprovalIn.putExtra("userId", userId);
            aprovalIn.putExtra("amount", amount);
            view.getContext().startActivity(aprovalIn);
        }
    }
}
