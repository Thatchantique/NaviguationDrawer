package com.example.guillaume.naviguationdrawer.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guillaume.naviguationdrawer.MainActivity;
import com.example.guillaume.naviguationdrawer.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Guillaume on 12/02/2017.
 */
public class SettingFragment extends android.support.v4.app.Fragment {
    private final int SELECT_AVATAR = 1;
    private Uri avatarPath;

    private TextView textViewErrors;

    private EditText editTextName, editTextEmail;
    private Button buttonAvatar, buttonValidation;

    public String getRealPathFromURI(Uri contentUri) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((MainActivity) getActivity()).loadUserPreferences();
        View view = inflater.inflate(R.layout.setting_fragment, container, false);


        textViewErrors = (TextView) view.findViewById(R.id.text_view_errors);

        editTextName = (EditText) view.findViewById(R.id.nameText);
        editTextEmail = (EditText) view.findViewById(R.id.emailText);

        buttonAvatar = (Button) view.findViewById(R.id.loadImage);
        buttonAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageIntent = new Intent(Intent.ACTION_PICK);
                imageIntent.setType("image/*");
                startActivityForResult(imageIntent, SELECT_AVATAR);
            }

        });

        buttonValidation = (Button) view.findViewById(R.id.submit);
        buttonValidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = ((MainActivity) getActivity()).getSharedPreferences();
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                if (name.length() <= 0 || email.length() <= 0) {
                    textViewErrors.setVisibility(View.VISIBLE);
                    textViewErrors.setText("Veuillez entrer les champs");
                } else {
                    textViewErrors.setVisibility(View.INVISIBLE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name", name);
                    //TODO Faire correspondre la bonne valeur de name aux préférences
                    editor.putString("email", email);
                    if (avatarPath != null) {
                        editor.putString("avatar", getRealPathFromURI(avatarPath));

                    }
                    Toast.makeText(getActivity(), "Vos préférences ont été sauvegardées", Toast.LENGTH_SHORT).show();
                    editor.apply();
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case SELECT_AVATAR:
                if (resultCode == RESULT_OK) {
                    avatarPath = imageReturnedIntent.getData();
                }
                break;
        }
    }
}