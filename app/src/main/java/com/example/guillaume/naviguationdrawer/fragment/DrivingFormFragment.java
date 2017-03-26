package com.example.guillaume.naviguationdrawer.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.guillaume.naviguationdrawer.model.DrivingSchool;
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

        if (getArguments() != null) {
            DrivingSchool ds = (DrivingSchool) getArguments().getSerializable("drivingSchool");
            editTextSchoolName.setText(ds.getName());
            editTextSchoolAdr.setText(ds.getAdress());
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adresseAutoEcole = editTextSchoolAdr.getText().toString();
                String nameDrivingSchool = editTextSchoolName.getText().toString();

                DrivingSchool newDrivingSchool;
                if (getArguments() != null) {
                    newDrivingSchool =(DrivingSchool) getArguments().getSerializable("drivingSchool");
                    newDrivingSchool.setAdresse(adresseAutoEcole);
                    newDrivingSchool.setName(nameDrivingSchool);
                } else {
                    newDrivingSchool = new DrivingSchool(adresseAutoEcole, nameDrivingSchool);
                }
                ((MainActivity) getActivity()).sendBackToDrivingSchoolList(newDrivingSchool);
            }
        });

        return view;
    }
}
