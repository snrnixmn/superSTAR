package com.example.superstar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ModifyTaskActivity extends AppCompatActivity {

    TextView tvDate;
    EditText etName, etChores, etPoints;
    Button btnUpdate, btnDelete;
    Reward reward;
    OverallReward overallReward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_task);

        tvDate = (TextView) findViewById(R.id.tvDate);
        etName = (EditText) findViewById(R.id.etName);
        etChores = (EditText) findViewById(R.id.etChores);
        etPoints = (EditText) findViewById(R.id.etPoints);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        Intent i = getIntent();
        reward = (Reward) i.getSerializableExtra("reward");
        final ArrayList<Integer> total = i.getIntegerArrayListExtra("total");

        tvDate.setText("Date: " + reward.getDate());
        etName.setText(reward.getName());
        etChores.setText(reward.getChore());
        etPoints.setText(String.valueOf(reward.getPoints()));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyTaskActivity.this);
                reward.setName(etName.getText().toString());
                reward.setChore(etChores.getText().toString());
                reward.setPoints(Integer.parseInt(etPoints.getText().toString()));
                dbh.updateSummary(reward);
                setResult(RESULT_OK);
                dbh.close();
                finish();
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int DELETE_ITEM = 1;

                DBHelper dbh = new DBHelper(ModifyTaskActivity.this);
                int totalScore = total.get(0);
                int updatedScore = totalScore + reward.getPoints();
                dbh.deleteSummary(reward.getId());
                dbh.close();

                DBHelper2 dbh2 = new DBHelper2(ModifyTaskActivity.this);
                dbh2.insertTotal(updatedScore);
                dbh2.close();

                setResult(DELETE_ITEM);
                finish();
            }
        });

    }
}
