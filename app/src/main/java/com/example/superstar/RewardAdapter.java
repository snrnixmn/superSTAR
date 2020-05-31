package com.example.superstar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RewardAdapter extends ArrayAdapter<Reward> {
    Context context;
    ArrayList<Reward> rewards;
    int resource;
    TextView tvNameDate, tvChores, tvPoints;

    public RewardAdapter(Context context, int resource, ArrayList<Reward> rewards) {
        super(context, resource, rewards);
        this.context = context;
        this.rewards = rewards;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        //Match the UI components with Java variables
        tvNameDate = (TextView) rowView.findViewById(R.id.tvNameDate);
        tvPoints = (TextView) rowView.findViewById(R.id.tvPoints);
        tvChores = (TextView) rowView.findViewById(R.id.tvChores);

        Reward reward = rewards.get(position);

        tvNameDate.setText("Updated By: " + reward.getName() + " • " + reward.getDate());
        tvPoints.setText("Points: " + reward.getPoints());
        tvChores.setText("Chores: " + reward.getChore());

        return rowView;
    }



}

