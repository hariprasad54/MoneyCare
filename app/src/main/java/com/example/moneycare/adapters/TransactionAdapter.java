package com.example.moneycare.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneycare.R;
import com.example.moneycare.model.Transaction;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private List<Transaction> transactions;

    public TransactionAdapter(List<Transaction> transactions){ this.transactions=transactions;}

    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position) {
        String tID = transactions.get(position).getTrnId();
        String tEmail = transactions.get(position).getTrnEmail();
        String tDate = transactions.get(position).getTrnDate();
        String tAmount = transactions.get(position).getTrnAmount();

        holder.setData(tID,tEmail,tDate,tAmount);
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTrnID,tvTrnEmail,tvTrnDate,tvTrnAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTrnID = itemView.findViewById(R.id.item_trn_id);
            tvTrnEmail = itemView.findViewById(R.id.item_trn_email);
            tvTrnDate = itemView.findViewById(R.id.item_trn_date);
            tvTrnAmount = itemView.findViewById(R.id.item_trn_amt);
        }

        public void setData(String tID, String tEmail, String tDate, String tAmount) {
            tvTrnID.setText(tID);
            tvTrnEmail.setText(tEmail);
            tvTrnDate.setText(tDate);
            tvTrnAmount.setText(tAmount);
        }
    }
}
