package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.AddDoorDialog;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.AddSwitchDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.HeaderNames;

import java.util.Objects;

public class RoomsRegisterFragment extends Fragment {

    Button cancelRoomRegister, saveRoomRegister, doorRegister, switchRegister, windowRegister, tableRegister, freeSpaceRegister;
    RadioGroup hasVisualSign, hasTactileSign;
    TextInputLayout obsVisualSignField, obsTactileSignField;
    TextInputEditText obsVisualSignValue, obsTactileSignValue;

    Integer hasVisSign, hasTactSign, libShelvesDistOK, libPcrManeuverOK, libAccessPcOK, cafeSpinOK, secFixedSeat, secHasPcrSpace,
    secSpinOK;
    Double classBoardHeight, secWidthPcrSpace, secLengthPcrSpace;
    String obsVisSign, obsTactSign;

    public static final String SCHOOL_ID_VALUE = "SCHOOL_ID_VALUE";
    public static final String ROOM_TYPE = "ROOM_TYPE";
    public static int schoolID;
    private static int chosenOption;

    public int update = 0;

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

        Bundle schoolBundle = this.getArguments();
        if (schoolBundle != null) {
            schoolID = schoolBundle.getInt(SCHOOL_ID_VALUE);
        }

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

        cancelRoomRegister = view.findViewById(R.id.cancel_room);
        saveRoomRegister = view.findViewById(R.id.save_room);
        doorRegister = view.findViewById(R.id.room_add_door_button);
        switchRegister = view.findViewById(R.id.room_add_switch_button);
        windowRegister = view.findViewById(R.id.room_add_window_button);
        tableRegister = view.findViewById(R.id.room_add_tables_button);
        freeSpaceRegister = view.findViewById(R.id.room_add_free_space_button);

        hasVisualSign = view.findViewById(R.id.room_has_visual_sign_radio);
        hasTactileSign = view.findViewById(R.id.room_has_tactile_sign_radio);

        obsVisualSignField = view.findViewById(R.id.visual_sign_obs_field);
        obsTactileSignField = view.findViewById(R.id.tactile_sign_obs_field);

        obsVisualSignValue = view.findViewById(R.id.visual_sign_obs_value);
        obsTactileSignValue = view.findViewById(R.id.tactile_sign_obs_value);

        doorRegister.setOnClickListener(v -> { addDoorDialog();
//            if (update == 0) {
//                update++;
//            } else {
//
//            }



        });

        switchRegister.setOnClickListener(v -> { addSwitchDialog();
//            if (update == 0) {
//                update++;
//            } else {
//
//            }

        });

        windowRegister.setOnClickListener(v -> {
            if (update == 0) {
                update++;
            } else {

            }

        });

        tableRegister.setOnClickListener(v -> {
            if (update == 0) {
                update++;
            } else {

            }

        });

        freeSpaceRegister.setOnClickListener(v -> {
            if (update == 0) {
                update++;
            } else {

            }

        });

        cancelRoomRegister.setOnClickListener(v -> Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction().remove(this).commit());

        saveRoomRegister.setOnClickListener(v -> {
            RoomEntry newRoomEntry = newRoomEntry();
            if (update != 0) {
                Toast.makeText(getContext(), "Clicou nos outros botões", Toast.LENGTH_SHORT).show();
            } else {
                ViewModelEntry.insertRoomEntry(newRoomEntry);
                Toast.makeText(getContext(), "Salvo com Sucesso", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void setHeaderText(View v) {
        TextView headerText = v.findViewById(R.id.room_register_header);
        String headerNames = HeaderNames.headerNames[chosenOption];
        headerText.setText(headerNames);
    }

    public int getCheckedRadioButton(RadioGroup radioGroup) {
        return radioGroup.indexOfChild(radioGroup.findViewById(radioGroup.getCheckedRadioButtonId()));
    }

    public boolean checkEmptyRoomFields() {
        int i = 0;
        if (hasVisualSign.getCheckedRadioButtonId() == -1) {
            i++;
        }
        if (hasTactileSign.getCheckedRadioButtonId() == -1) {
            i++;
        }
        return i == 0;
    }

    public RoomEntry newRoomEntry() {
        if (hasVisualSign.getCheckedRadioButtonId() != -1) {
            hasVisSign = getCheckedRadioButton(hasVisualSign);
        }
        if (!TextUtils.isEmpty(obsVisualSignValue.getText())) {
            obsVisSign = Objects.requireNonNull(obsVisualSignValue.getText()).toString();
        }
        if (hasTactileSign.getCheckedRadioButtonId() != -1) {
            hasTactSign = getCheckedRadioButton(hasVisualSign);
        }
        if (!TextUtils.isEmpty(obsTactileSignValue.getText())) {
            obsTactSign = Objects.requireNonNull(obsTactileSignValue.getText()).toString();
        }

        return new RoomEntry(schoolID, chosenOption, hasVisSign, obsVisSign, hasTactSign, obsTactSign, libShelvesDistOK,
                libPcrManeuverOK, libAccessPcOK, cafeSpinOK, classBoardHeight,secFixedSeat,secHasPcrSpace,secWidthPcrSpace,
                secLengthPcrSpace, secSpinOK);
    }

    private void addDoorDialog() {
        AddDoorDialog.displayDoorDialog(Objects.requireNonNull(getActivity()).getSupportFragmentManager());
    }

    private void addSwitchDialog() {
        AddSwitchDialog.displaySwitchDialog(Objects.requireNonNull(getActivity()).getSupportFragmentManager());
    }



}