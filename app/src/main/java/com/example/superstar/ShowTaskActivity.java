package com.example.superstar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ShowTaskActivity extends AppCompatActivity {

    RewardAdapter ra;
    ListView lvTasks;
    ArrayList<Reward> al;
    ArrayList<OverallReward> arr;
    Button btnAddNewTask, btnUseStars;
    Reward reward;
    TextView tvTotalPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);

        lvTasks = (ListView) findViewById(R.id.lvTasks);
        btnAddNewTask = (Button) findViewById(R.id.bnAddNewTask);
        tvTotalPoints = (TextView) findViewById(R.id.tvTotalPoints);
        btnUseStars = (Button) findViewById(R.id.btnUseStars);

        DBHelper db = new DBHelper(ShowTaskActivity.this);

        al = new ArrayList<>();
        al.addAll(db.getRewardSummary());
        db.close();
        ra = new RewardAdapter(this, R.layout.row, al);
        lvTasks.setAdapter(ra);
        ra.notifyDataSetChanged();

        DBHelper2 db2 = new DBHelper2(ShowTaskActivity.this);
        arr = new ArrayList<>();
        arr.addAll(db2.getTotal());

        OverallReward tmp = arr.get(arr.size()-1);
        int score = tmp.getTotalScores();

        tvTotalPoints.setText(Integer.toString(score));

        btnAddNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowTaskActivity.this, AddTaskActivity.class);
                startActivityForResult(i, 2);
            }
        });

        lvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Reward r = al.get(position);
                Intent i = new Intent(ShowTaskActivity.this, ModifyTaskActivity.class);
                i.putExtra("reward", r);
                int value = Integer.parseInt(tvTotalPoints.getText().toString());
                ArrayList<Integer> valueAdded = new ArrayList<>();
                valueAdded.add(value);
                i.putExtra("total", valueAdded);
                startActivityForResult(i, 2);
            }
        });

        btnUseStars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowTaskActivity.this, UseStarActivity.class);
                startActivityForResult(i, 3);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 1) {

            DBHelper db = new DBHelper(ShowTaskActivity.this);
            al.clear();
            al.addAll(db.getRewardSummary());
            db.close();
            lvTasks.setAdapter(ra);
            ra.notifyDataSetChanged();
            db.close();

        } else if (resultCode == RESULT_OK && requestCode == 2) {

            DBHelper db = new DBHelper(ShowTaskActivity.this);
            al.clear();
            al.addAll(db.getRewardSummary());
            db.close();
            lvTasks.setAdapter(ra);
            ra.notifyDataSetChanged();
            db.close();

        } else {

            DBHelper db = new DBHelper(ShowTaskActivity.this);
            al.clear();
            al.addAll(db.getRewardSummary());
            db.close();
            lvTasks.setAdapter(ra);
            ra.notifyDataSetChanged();
            db.close();

            DBHelper2 db2 = new DBHelper2(ShowTaskActivity.this);
            arr = new ArrayList<>();
            arr.addAll(db2.getTotal());

            OverallReward tmp = arr.get(arr.size()-1);
            int score = tmp.getTotalScores();

            tvTotalPoints.setText(Integer.toString(score));

        }
    }
}

