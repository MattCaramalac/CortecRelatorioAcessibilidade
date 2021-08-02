package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mpms.relatorioacessibilidadecortec.R;

public class GateObsBarrierType extends Fragment {

    public GateObsBarrierType() {
        // Required empty public constructor
    }

    public static GateObsBarrierType newInstance(String param1, String param2) {
        return new GateObsBarrierType();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gate_obs_barrier_type, container, false);
    }
}