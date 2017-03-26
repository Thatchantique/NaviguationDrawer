package com.example.guillaume.naviguationdrawer.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.guillaume.naviguationdrawer.DrivingSchool;
import com.example.guillaume.naviguationdrawer.MainActivity;
import com.example.guillaume.naviguationdrawer.R;

public class DrivingFormFragment extends Fragment {
    private EditText editTextSchoolName;
    private EditText editTextSchoolAdr;
    private Button submitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_driving_form, container, false);
        editTextSchoolName = (EditText) view.findViewById(R.id.editTextSchoolName);
        editTextSchoolAdr = (EditText) view.findViewById(R.id.editTextSchoolAdr);
        submitButton = (Button) view.findViewById(R.id.buttonSubmit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adresseAutoEcole = editTextSchoolAdr.getText().toString();
                String nameDrivingSchool = editTextSchoolName.getText().toString();

                DrivingSchool newDrivingSchool = new DrivingSchool(adresseAutoEcole, nameDrivingSchool);
                ((MainActivity) getActivity()).sendBackToDrivingSchoolList(newDrivingSchool);
            }
        });

        return view;
    }
}
