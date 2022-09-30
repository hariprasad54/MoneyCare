package com.example.moneycare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.moneycare.apicontroler.API;
import com.example.moneycare.apicontroler.GetRequest;
import com.example.moneycare.apicontroler.PostRequest;
import com.example.moneycare.model.BasicUserEntity;
import com.example.moneycare.model.Rating;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class RateUsActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private EditText feedbackRating;
    private Button btnRating;
    private TextView ratingsTitle;
    private static float rating;
    private Rating ratingObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_us);

        ratingBar = findViewById(R.id.ratingBar);
        feedbackRating = findViewById(R.id.et_rating_feedback);
        btnRating = findViewById(R.id.btn_rating_submit);
        ratingsTitle = findViewById(R.id.ratings_title);
        try {
            ratingObj =  new ObjectMapper()
                    .readValue(GetRequest.sendRequest(API.GETUSERRATING+LoginActivity.userId),new TypeReference<Rating>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (ratingObj.getRatingPoints() == null){

            btnRating.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    rating = ratingBar.getRating();
                    Toast.makeText(RateUsActivity.this,String.valueOf(rating),Toast.LENGTH_LONG).show();
                    Rating ratingObj = new Rating();
                    ratingObj.setUserId(LoginActivity.userId);
                    ratingObj.setRatingPoints(String.valueOf(rating));
                    if (!isEmpty(String.valueOf(feedbackRating.getText()))){
                        ratingObj.setRatingMsg(String.valueOf(feedbackRating.getText()));
                    }

                    String response = "";
                    try {
                        response = PostRequest.sendRequest(API.ADDRATING,ratingObj.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (response.equalsIgnoreCase("Invalid User")){
                        Toast.makeText(RateUsActivity.this,"Invalid User",Toast.LENGTH_LONG).show();
                    }
                    else if (response.equalsIgnoreCase("Ratings Added")){
                        //Toast.makeText(RateUsActivity.this,"Ratings Added",Toast.LENGTH_LONG).show();
                        ratingBar.setRating(rating);
                        if (!isEmpty(String.valueOf(feedbackRating.getText()))){
                            feedbackRating.setText(String.valueOf(feedbackRating.getText()));
                            feedbackRating.setFocusable(false);
                            feedbackRating.setBackground(getDrawable(R.drawable.et_custom_disable));
                        }
                        else feedbackRating.setVisibility(View.INVISIBLE);
                        btnRating.setVisibility(View.INVISIBLE);
                        ratingsTitle.setText("Thanks for Ratings");

                    }
                }
            });


        }
        else {
            ratingBar.setRating(Float.parseFloat(ratingObj.getRatingPoints()));
            if (ratingObj.getRatingMsg() != null) {
                feedbackRating.setText(ratingObj.getRatingMsg());
                feedbackRating.setFocusable(false);
                feedbackRating.setBackground(getDrawable(R.drawable.et_custom_disable));
            }
            else feedbackRating.setVisibility(View.INVISIBLE);
            btnRating.setVisibility(View.INVISIBLE);
            ratingsTitle.setText("Thanks for Ratings");
        }


    }
    boolean isEmpty(String str) {
        return TextUtils.isEmpty(str);
    }
}