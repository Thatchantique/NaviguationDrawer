package com.example.guillaume.naviguationdrawer.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guillaume.naviguationdrawer.DrivingSchool;
import com.example.guillaume.naviguationdrawer.DrivingSchoolAdapter;
import com.example.guillaume.naviguationdrawer.R;

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
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_driving);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(new DrivingSchoolAdapter(buildDrivingSchool()));

        return view;
    }

    public List<DrivingSchool> buildDrivingSchool()
    {
        List<DrivingSchool> data = new ArrayList<DrivingSchool>();
        int adressIcon = R.drawable.logo3;
        int adressIcon2 = R.drawable.logo;
        int adressIcon3 = R.drawable.logo2;
        DrivingSchool auto = new DrivingSchool("44 av Henry Chéron 14000 Caen", adressIcon,"Super ecole");
        DrivingSchool auto1 = new DrivingSchool("44 av Henry Chéron 14000 Caen", adressIcon2,"Ecole2");
        DrivingSchool auto2 = new DrivingSchool("44 av Henry Chéron 14000 Caen", adressIcon3,"Ecole3");
        data.add(auto);
        data.add(auto1);
        data.add(auto2);
        return data;
    }
}