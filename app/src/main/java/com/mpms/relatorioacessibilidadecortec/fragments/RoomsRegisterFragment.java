package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.util.HeaderNames;

import java.util.Objects;

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
            case 15:
                getChildFragmentManager().beginTransaction().replace(R.id.room_child_fragment, new SecretariatFragment()).commit();
                break;
            default:
                break;
        }

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button cancelRoomRegister = view.findViewById(R.id.cancel_room);
//        Button saveWaterFountain = view.findViewById(R.id.save_waterfountain);

//        saveWaterFountain.setOnClickListener(v -> {
//            if(verifyWaterFountainErrors()) {
//                WaterFountainEntry newWaterFountain = createWaterFountainEntry(schoolID);
//                ViewModelEntry.insertWaterFountain(newWaterFountain);
//                clearTextFields();
//            }
//        });

        cancelRoomRegister.setOnClickListener(v -> Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction().remove(this).commit());
    }

    public void setHeaderText(View v) {
        TextView headerText = v.findViewById(R.id.room_register_header);
        String headerNames = HeaderNames.headerNames[chosenOption];
        headerText.setText(headerNames);
    }
}