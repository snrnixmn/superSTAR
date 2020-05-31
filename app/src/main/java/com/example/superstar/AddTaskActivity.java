package com.example.superstar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {

    EditText etName, etChores, etPoints;
    Button btnAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        etName = (EditText) findViewById(R.id.etName);
        etChores = (EditText) findViewById(R.id.etChores);
        etPoints = (EditText) findViewById(R.id.etPoints);
        btnAddTask = (Button) findViewById(R.id.btnAddTask);

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbhelper = new DBHelper(AddTaskActivity.this);
                String name = etName.getText().toString();
                String date = getDate();
                String chores = etChores.getText().toString();
                String points = etPoints.getText().toString();
                if (!chores.isEmpty()) {
                    dbhelper.insertRewardSummary(name, date, chores, Integer.valueOf(points), 0);
                }

                setResult(RESULT_OK);
                dbhelper.close();
                finish();

            }
        });
    }

    protected String getDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String formattedDate = df.format(c);
        Log.i("Today's date : ", formattedDate);
        return formattedDate;
    }
}
