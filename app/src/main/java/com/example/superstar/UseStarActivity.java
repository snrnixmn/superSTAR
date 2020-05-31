package com.example.superstar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UseStarActivity extends AppCompatActivity {

    EditText etScore;
    Button btnUseStars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_star);

        etScore = (EditText) findViewById(R.id.etCurrentStars);
        btnUseStars = (Button) findViewById(R.id.btnDeduct);

        Intent i = getIntent();
        final ArrayList<Integer> score = i.getIntegerArrayListExtra("score");

        btnUseStars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int STARS = 2;

                DBHelper2 dbh2 = new DBHelper2(UseStarActivity.this);
                int totalScore = score.get(0);
                int minusStars = Integer.parseInt(etScore.getText().toString());
                int updatedScore = totalScore - minusStars;
                dbh2.insertTotal(updatedScore);
                dbh2.close();

                setResult(STARS);
                finish();
            }
        });
    }
}
