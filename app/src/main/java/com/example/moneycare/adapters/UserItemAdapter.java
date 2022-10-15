package com.example.moneycare.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneycare.R;
import com.example.moneycare.UserDetailsActivity;
import com.example.moneycare.model.User;
import com.example.moneycare.model.UserItem;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserItemAdapter extends  RecyclerView.Adapter<UserItemAdapter.ViewHolder>{

    public List<UserItem> userItemList;

    public UserItemAdapter(List<UserItem> userItemList){ this.userItemList = userItemList;}

    public void setFilteredList(List<UserItem> filteredList){
        this.userItemList = filteredList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public UserItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_in_admin,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserItemAdapter.ViewHolder holder, int position) {
            String userId = userItemList.get(position).getUserId();
            String userEmail = userItemList.get(position).getUserEmail();

            holder.initData(userId,userEmail);
    }

    @Override
    public int getItemCount() {
        return userItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvUserId,tvUserEmail;
        public CircleImageView circleImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            tvUserId = itemView.findViewById(R.id.user_user_id);
            tvUserEmail = itemView.findViewById(R.id.user_email);
            circleImageView = itemView.findViewById(R.id.mem_profile_pic);
        }

        public void initData(String userId, String userEmail) {

            tvUserId.setText(userId);
            tvUserEmail.setText(userEmail);
            circleImageView.setImageResource(R.drawable.user1);
        }

        @Override
        public void onClick(View v) {
            UserItem userItem = userItemList.get(getAbsoluteAdapterPosition());
            //Toast.makeText(v.getContext(), userItem.toString(), Toast.LENGTH_LONG).show();
            Intent userIn = new Intent(v.getContext(), UserDetailsActivity.class);
            userIn.putExtra("user_item",userItem.toString());
            userIn.putExtra("userId",userItem.getUserId());
            userIn.putExtra("userEmail",userItem.getUserEmail());
            userIn.putExtra("userAvailBal",String.valueOf(userItem.getAvailBalance()));
            userIn.putExtra("userTotEarnings",String.valueOf(userItem.getTotalEarnings()));
            v.getContext().startActivity(userIn);
        }
    }
}
