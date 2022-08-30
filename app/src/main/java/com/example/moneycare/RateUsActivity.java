package com.example.moneycare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TimePicker;
import android.widget.Toast;

public class RateUsActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private EditText feedbackRating;
    private Button btnRating;
    private float rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_us);

        ratingBar = findViewById(R.id.ratingBar);
        feedbackRating = findViewById(R.id.et_rating_feedback);
        btnRating = findViewById(R.id.btn_rating_submit);

        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = ratingBar.getRating();
                Toast.makeText(RateUsActivity.this,String.valueOf(rating),Toast.LENGTH_LONG).show();
            }
        });


    }
}