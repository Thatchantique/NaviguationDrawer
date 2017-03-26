package com.example.guillaume.naviguationdrawer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Guillaume on 12/02/2017.
 */

public class DrivingSchoolAdapter extends RecyclerView.Adapter<DrivingSchoolAdapter.DrivingSchoolViewHolder> {
    private Activity activity;
    private List<DrivingSchool> drivingSchools;

    public DrivingSchoolAdapter(List<DrivingSchool> drivingSchools, Activity activity) {
        this.drivingSchools = drivingSchools;
        this.activity = activity;
    }

    @Override
    public DrivingSchoolViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.driving_item, parent, false);
        return new DrivingSchoolViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(DrivingSchoolViewHolder holder, int position) {
        final DrivingSchool currentDrivingSchool = drivingSchools.get(position);

        if (currentDrivingSchool.getIcon() == -1) {
            holder.logo.setImageResource(0);
        } else {
            holder.logo.setImageResource(currentDrivingSchool.getIcon());
        }
        holder.autoEcoleAdresse.setText(currentDrivingSchool.getAdress());
        holder.autoEcoleTitle.setText(currentDrivingSchool.getName());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) activity).editSendBack(currentDrivingSchool);
            }
        });
    }

    @Override
    public int getItemCount() {
        return drivingSchools.size();
    }

    class DrivingSchoolViewHolder extends RecyclerView.ViewHolder {
        private TextView autoEcoleTitle;
        private TextView autoEcoleAdresse;
        private LinearLayout layout;
        private ImageView logo;

        DrivingSchoolViewHolder(View view) {
            super(view);
            logo = (ImageView) view.findViewById(R.id.logo);
            autoEcoleAdresse = (TextView) view.findViewById(R.id.Adresse);
            autoEcoleTitle = (TextView) view.findViewById(R.id.Nom);
            layout = (LinearLayout) view.findViewById(R.id.row_linear_layout);
        }
    }
}
