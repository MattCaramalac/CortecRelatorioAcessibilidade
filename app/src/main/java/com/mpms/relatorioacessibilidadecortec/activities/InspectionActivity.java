package com.mpms.relatorioacessibilidadecortec.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.fragments.InspectionMemorial;

import java.util.ArrayList;

public class InspectionActivity extends AppCompatActivity implements InspectionMemorial.OnFragmentInteractionListener {

    private int dropdownChoice = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection);

    }

    @Override
    public void onDropdownChoice(int choice) {
        Log.d("Chosen option", "onDropdownChoice: " + choice);
        dropdownChoice = choice;
        Toast.makeText(this, "The item chosen was " + choice, Toast.LENGTH_SHORT).show();

    }
}