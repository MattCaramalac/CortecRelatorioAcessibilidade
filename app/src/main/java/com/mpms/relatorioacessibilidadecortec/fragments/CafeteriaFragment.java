package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.util.HeaderNames;


public class CafeteriaFragment extends Fragment {

    public CafeteriaFragment() {
        // Required empty public constructor
    }

    public static CafeteriaFragment newInstance() {
        return new CafeteriaFragment();
    }

        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState){
            // Inflate the layout for this fragment
            View rootView = inflater.inflate(R.layout.fragment_cafeteria, container, false);
            return rootView;
        }

}