package com.example.moneycare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.moneycare.adapters.UserItemAdapter;
import com.example.moneycare.apicontroler.API;
import com.example.moneycare.apicontroler.GetRequest;
import com.example.moneycare.model.User;
import com.example.moneycare.model.UserItem;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DisplayAllUsersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserItemAdapter adapter;
    public static List<UserItem> userItemList;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_users);

        searchView = findViewById(R.id.user_search_bar);
        searchView.clearFocus();

        userItemList = new ArrayList<>();

        try {
            userItemList = new ObjectMapper().readValue(GetRequest.sendRequest(API.GETALLUSERDETAILS + LoginActivity.adminUserId), new TypeReference<List<UserItem>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterSearch(newText);
                return true;
            }
        });


        initRecyclerView();
    }

    private void filterSearch(String newText) {
        List<UserItem> filteredList = new ArrayList<>();
        for(UserItem userItem: userItemList){
            if (userItem.getUserId().toLowerCase().contains(newText) || userItem.getUserEmail().toLowerCase().contains(newText)){
                filteredList.add(userItem);
            }
        }
        if (filteredList.isEmpty()){
            Toast.makeText(this, "No results Found", Toast.LENGTH_SHORT).show();
        }else{
            adapter.setFilteredList(filteredList);
        }

    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.users_rc_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserItemAdapter(userItemList);
        recyclerView.setAdapter(adapter);
    }
}