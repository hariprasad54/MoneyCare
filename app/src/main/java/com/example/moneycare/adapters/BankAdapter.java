package com.example.moneycare.adapters;

import android.content.DialogInterface;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneycare.BankDetailsActivity;
import com.example.moneycare.EditBankAccountActivity;
import com.example.moneycare.LoginActivity;
import com.example.moneycare.MainActivity;
import com.example.moneycare.R;
import com.example.moneycare.apicontroler.API;
import com.example.moneycare.apicontroler.PostRequest;
import com.example.moneycare.model.BankAccount;
import com.example.moneycare.model.UserAuthEntity;
import com.example.moneycare.requests.BankAccountRequest;

import java.io.IOException;
import java.util.List;

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.ViewHolder> {

    private List<BankAccount> bankAccounts;

    public BankAdapter(List<BankAccount> bankAccounts){ this.bankAccounts = bankAccounts;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_bank_details,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String bankName = bankAccounts.get(position).getBankName();
        String ifscName = bankAccounts.get(position).getIfsc();
        String acNumber = bankAccounts.get(position).getAccountNo();
        String acHolderName = bankAccounts.get(position).getName();

        holder.setData(bankName,ifscName,acNumber,acHolderName);

    }

    @Override
    public int getItemCount() {
        return bankAccounts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvBankName,tvIfsc,tvAcNumber,tvAcHolderName;
        private Button btnEditBank, btnDeleteBank;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvBankName = itemView.findViewById(R.id.txt_bank_name);
            tvIfsc = itemView.findViewById(R.id.txt_ifsc_code);
            tvAcNumber = itemView.findViewById(R.id.txt_bank_account);
            tvAcHolderName = itemView.findViewById(R.id.txt_ac_holder_name);

            btnEditBank = itemView.findViewById(R.id.btn_edit_bank);
            btnDeleteBank = itemView.findViewById(R.id.btn_del_bank);


            btnDeleteBank.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(view.getContext())
                            .setTitle("Deleting Account?")
                            .setMessage("Are you sure you want to Delete?")
                            .setNegativeButton(android.R.string.no, null)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1) {
                                    BankAccount bankAccount = bankAccounts.remove(getAbsoluteAdapterPosition());
                                    UserAuthEntity srcUser = new UserAuthEntity().setUserName(LoginActivity.userId);
                                    BankAccountRequest br = new BankAccountRequest(srcUser, bankAccount);
                                    try {
                                        PostRequest.sendRequest(API.REMOVEBANKACCOUNT, br.toString());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    notifyItemRangeChanged(getAbsoluteAdapterPosition(), bankAccounts.size());
                                    notifyItemRemoved(getAbsoluteAdapterPosition());
                                }
                            }).create().show();
                }
            });

            btnEditBank.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent editBankIn = new Intent(view.getContext(),EditBankAccountActivity.class);
                    editBankIn.putExtra("bName",bankAccounts.get(getAbsoluteAdapterPosition()).getBankName());
                    editBankIn.putExtra("acNumber",bankAccounts.get(getAbsoluteAdapterPosition()).getAccountNo());
                    editBankIn.putExtra("ifscCode",bankAccounts.get(getAbsoluteAdapterPosition()).getIfsc());
                    editBankIn.putExtra("acHolderName",bankAccounts.get(getAbsoluteAdapterPosition()).getName());
                    view.getContext().startActivity(editBankIn);
                }
            });
        }

        public void setData(String bankName, String ifscName, String acNumber, String acHolderName) {
            tvBankName.setText(bankName);
            tvIfsc.setText(ifscName);
            tvAcNumber.setText(acNumber);
            tvAcHolderName.setText(acHolderName);
        }
    }
}
