package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.mpms.relatorioacessibilidadecortec.R;

public class ExtAccessVehicleFragment extends Fragment {

    public ExtAccessVehicleFragment() {
        // Required empty public constructor
    }

    public static ExtAccessVehicleFragment newInstance(String param1, String param2) {
        return new ExtAccessVehicleFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ext_access_vehicle, container, false);
    }
}