package com.mpms.relatorioacessibilidadecortec.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;

import java.util.ArrayList;



public class InspectionMemorial extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    OnFragmentInteractionListener listener;

    TextInputLayout dropdownMenuLocations;
    AutoCompleteTextView listItemsMemorial;
    String[] memorialLocations;
    ArrayAdapter<String> adapterLocations;

    private static final int NO_CHOICE = -1;
    private int chosenOption = NO_CHOICE;

    public InspectionMemorial() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InspectionMemorial.
     */
    // TODO: Rename and change types and number of parameters
    public static InspectionMemorial newInstance(String param1, String param2) {
        InspectionMemorial fragment = new InspectionMemorial();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_inspection_memorial, container, false);
        dropdownMenuLocations = rootView.findViewById(R.id.menu_options_memorial);
        listItemsMemorial = rootView.findViewById(R.id.list_item_memorial);
        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        }
        else {
            throw new ClassCastException(context.toString());
        }

    }

    @Override
    public void onResume() {
        memorialLocations = getResources().getStringArray(R.array.memorial_items);
        adapterLocations = new ArrayAdapter<>(getContext(), R.layout.dropdown_list_memorial, memorialLocations);
        listItemsMemorial.setAdapter(adapterLocations);

        listItemsMemorial.setOnItemClickListener((parent, view, position, id) -> {
            Log.d("Chosen Option", "onItemSelected: " + position);
            chosenOption = position;
            listener.onDropdownChoice(position);
        });

//        listItemsMemorial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String test = (String) parent.getItemAtPosition(position);
//                Log.d("Chosen Option", "onItemSelected: " + test);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                Log.d("Chosen Option", "onItemSelected: " + NO_CHOICE);
//
//            }
//        });
        super.onResume();
    }

    public interface OnFragmentInteractionListener {
        void onDropdownChoice (int choice);
    }
}