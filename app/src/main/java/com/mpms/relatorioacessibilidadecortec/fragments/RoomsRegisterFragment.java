package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.util.HeaderNames;

public class RoomsRegisterFragment extends Fragment {

    private static int chosenOption;

    public RoomsRegisterFragment() {
        // Required empty public constructor
    }

    public void setChosenOption(int choice) {
        RoomsRegisterFragment.chosenOption = choice;
    }

    public static RoomsRegisterFragment newInstance(int dropdownChoice) {
        RoomsRegisterFragment roomsRegisterFragment = new RoomsRegisterFragment();
        roomsRegisterFragment.setChosenOption(dropdownChoice);
        return roomsRegisterFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_rooms_register, container, false);
        setHeaderText(rootView);

        switch (chosenOption){
            case 3:
                getChildFragmentManager().beginTransaction().replace(R.id.room_child_fragment, new LibraryFragment()).commit();
                break;
            case 10:
                getChildFragmentManager().beginTransaction().replace(R.id.room_child_fragment, new CafeteriaFragment()).commit();
                break;
            case 11:
                getChildFragmentManager().beginTransaction().replace(R.id.room_child_fragment, new ClassroomFragment()).commit();
                break;
            default:
                break;
        }

        return rootView;
    }

    public void setHeaderText(View v) {
        TextView headerText = v.findViewById(R.id.room_register_header);
        String headerNames = HeaderNames.headerNames[chosenOption];
        headerText.setText(headerNames);
    }
}