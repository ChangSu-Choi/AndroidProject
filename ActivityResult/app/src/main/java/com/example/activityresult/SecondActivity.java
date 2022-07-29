package com.example.activityresult;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

public class SecondActivity extends AppCompatActivity {

    Button btnComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        int[] voteResult = intent.getIntArrayExtra("VoteCount");

        RatingBar rbar[] = new RatingBar[voteResult.length];
        Integer rbarId[] = {R.id.ratingRed,R.id.ratingBlue,R.id.ratingBlack};

        for(int i = 0; i<voteResult.length; i++){
            final int index;
            int rating;
            index = i;

            rbar[index] = (RatingBar) findViewById(rbarId[index]);
            if(voteResult[index]>5)
                rating = 5;
            else
                rating = voteResult[i];
            rbar[index].setRating(rating);
            rbar[index].setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                    voteResult[index] = (int) ratingBar.getRating();
                }
            });
        }

        btnComplete = (Button) findViewById(R.id.btnComplete);
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                outIntent.putExtra("VoteCount2", voteResult);
                setResult(RESULT_OK,outIntent);
                finish();
            }
        });
    }

}