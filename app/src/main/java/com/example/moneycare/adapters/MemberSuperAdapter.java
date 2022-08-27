package com.example.moneycare.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneycare.AdminApprovalSubActivity;
import com.example.moneycare.R;
import com.example.moneycare.model.MemberSuper;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MemberSuperAdapter extends RecyclerView.Adapter<MemberSuperAdapter.ViewHolder> {

    private List<MemberSuper> supMembersList;

    public MemberSuperAdapter(List<MemberSuper> supMembersList){
        this.supMembersList = supMembersList;
    }
    @NonNull
    @Override
    public MemberSuperAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_super_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberSuperAdapter.ViewHolder holder, int position) {
        String email = supMembersList.get(position).getEmail();
        int profileUrl = supMembersList.get(position).getProfileUrl();

        holder.setData(email,profileUrl);
    }

    @Override
    public int getItemCount() {
        return supMembersList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtEmail;
        private CircleImageView profilePic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            txtEmail = itemView.findViewById(R.id.mem_email_admin_sup);
            profilePic = itemView.findViewById(R.id.mem_profile_pic_admin_sup);

        }

        public void setData(String email, int profileUrl) {

            txtEmail.setText(email);
            profilePic.setImageResource(profileUrl);

        }

        @Override
        public void onClick(View view) {
           int position = this.getAbsoluteAdapterPosition();
           MemberSuper memberSuper = supMembersList.get(position);
           String email = memberSuper.getEmail();
            Toast.makeText(view.getContext(), email,Toast.LENGTH_LONG).show();
            Intent subIn = new Intent(view.getContext(), AdminApprovalSubActivity.class);
            view.getContext().startActivity(subIn);
        }
    }
}
