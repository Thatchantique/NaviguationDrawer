package com.example.guillaume.naviguationdrawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guillaume on 12/02/2017.
 */

public class DrivingSchoolFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.divenfragment,container,false);
        RecyclerView recycler_view = (RecyclerView) view.findViewById(R.id.recycler_view_driving);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());

        recycler_view.setAdapter(new DrivingSchoolAdapter(buildDrivingSchool()));

        return view;
    }

    public List<DrivingSchool> buildDrivingSchool()
    {
        List<DrivingSchool> data = new ArrayList<DrivingSchool>();
        int adresse_icon = R.drawable.logo3;
        int adresse_icon2 = R.drawable.logo;
        int adresse_icon3 = R.drawable.logo2;
        DrivingSchool auto = new DrivingSchool("44 av Henry Chéron 14000 Caen",adresse_icon,"Super ecole");
        DrivingSchool auto1 = new DrivingSchool("44 av Henry Chéron 14000 Caen",adresse_icon2,"Ecole2");
        DrivingSchool auto2 = new DrivingSchool("44 av Henry Chéron 14000 Caen",adresse_icon3,"Ecole3");
        data.add(auto);
        return data;
    }

}