package com.example.guillaume.naviguationdrawer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Guillaume on 12/02/2017.
 */

public class DrivingSchoolAdapter extends RecyclerView.Adapter<DrivingSchoolAdapter.DrivingSchoolViewHolder> {
    private List<DrivingSchool> drivingSchools;

    public DrivingSchoolAdapter(List<DrivingSchool> drivingSchools) {
        this.drivingSchools = drivingSchools;
    }

    @Override
    public DrivingSchoolViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.driving_item, parent, false);
        return new DrivingSchoolViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(DrivingSchoolViewHolder holder, int position) {
        DrivingSchool currentDrivingSchool = drivingSchools.get(position);
        if (currentDrivingSchool.getIcon() == -1) {
            holder.logo.setImageResource(0);
        } else {
            holder.logo.setImageResource(currentDrivingSchool.getIcon());
        }
        holder.autoEcoleAdresse.setText(currentDrivingSchool.getAdress());
        holder.autoEcoleTitle.setText(currentDrivingSchool.getName());
    }

    @Override
    public int getItemCount() {
        return drivingSchools.size();
    }

    class DrivingSchoolViewHolder extends RecyclerView.ViewHolder {
        private TextView autoEcoleTitle;
        private TextView autoEcoleAdresse;
        private ImageView logo;

        DrivingSchoolViewHolder(View view) {
            super(view);
            logo = (ImageView) view.findViewById(R.id.logo);
            autoEcoleAdresse = (TextView) view.findViewById(R.id.Adresse);
            autoEcoleTitle = (TextView) view.findViewById(R.id.Nom);
        }
    }
}
