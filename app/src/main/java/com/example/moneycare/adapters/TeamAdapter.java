package com.example.moneycare.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneycare.R;
import com.example.moneycare.model.BasicUserEntity;
import com.example.moneycare.model.TeamMember;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    private List<BasicUserEntity> teamMembers;

    public TeamAdapter(List<BasicUserEntity> teamMembers) { this.teamMembers = teamMembers;}

    @NonNull
    @Override
    public TeamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team_member,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamAdapter.ViewHolder holder, int position) {

//        int profilePic = teamMembers.get(position).getProfileImage();
        String email = teamMembers.get(position).getEmail();
        String phone = teamMembers.get(position).getMobileNo();

        holder.setData(1,email,phone);

    }

    @Override
    public int getItemCount() {
        return teamMembers.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

       private ImageView ivProfilePic;
       private TextView tvEmail,tvPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProfilePic = itemView.findViewById(R.id.mem_profile_pic);
            tvEmail = itemView.findViewById(R.id.mem_email);
            tvPhone = itemView.findViewById(R.id.mem_phone);
        }

        public void setData(int profilePic, String email, String phone) {

            ivProfilePic.setImageResource(R.drawable.user1);
            tvEmail.setText(email);
            tvPhone.setText(phone);
        }
    }
}
