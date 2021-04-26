package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.util.HeaderNames;

public class ExternalAccessFragment extends Fragment {

    private static int chosenOption;

    public ExternalAccessFragment() {
        // Required empty public constructor
    }

    public void setChosenOption(int choice) {
        ExternalAccessFragment.chosenOption = choice;
    }

    public static ExternalAccessFragment newInstance(int dropdownChoice) {
        ExternalAccessFragment externalAccessFragment = new ExternalAccessFragment();
        externalAccessFragment.setChosenOption(dropdownChoice);
        return externalAccessFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_external_access, container, false);
        setHeaderText(rootView);
        return rootView;
    }

    public void setHeaderText(View v) {
        TextView headerText = v.findViewById(R.id.external_access_header);
        HeaderNames headerNames = HeaderNames.headerNames[chosenOption];
        headerText.setText(headerNames.getName());
    }

}
