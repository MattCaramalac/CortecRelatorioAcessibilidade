package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.AddDoorDialog;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.AddFreeSpaceDialog;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.AddSwitchDialog;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.AddTableDialog;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.AddWindowDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;
import com.mpms.relatorioacessibilidadecortec.util.HeaderNames;

import java.util.Objects;

public class RoomsRegisterFragment extends Fragment {

    public static final String SCHOOL_ID_VALUE = "SCHOOL_ID_VALUE";
    public static final String ROOM_TYPE = "ROOM_TYPE";
    public static int schoolID;
    private static int chosenOption;
    public int update = 0;
    public int recentRoomID = 0;
    Button cancelRoomRegister, saveRoomRegister, doorRegister, switchRegister, windowRegister, tableRegister, freeSpaceRegister;
    RadioGroup hasVisualSign, hasTactileSign;
    TextInputLayout obsVisualSignField, obsTactileSignField;
    TextInputEditText obsVisualSignValue, obsTactileSignValue;
    Integer hasVisSign, hasTactSign, libShelvesDistOK, libPcrManeuverOK, libAccessPcOK, cafeSpinOK, secFixedSeat, secHasPcrSpace,
            secSpinOK;
    Double classBoardHeight, secWidthPcrSpace, secDepthPcrSpace;
    String obsVisSign, obsTactSign;
    FragmentManager manager;
    Bundle roomBundleID = new Bundle();
    private ViewModelFragments modelFragments;
    private ViewModelEntry modelEntry;

    public RoomsRegisterFragment() {
        // Required empty public constructor
    }

    public static RoomsRegisterFragment newInstance(int dropdownChoice) {
        RoomsRegisterFragment roomsRegisterFragment = new RoomsRegisterFragment();
        roomsRegisterFragment.setChosenOption(dropdownChoice);
        return roomsRegisterFragment;
    }

    public void setChosenOption(int choice) {
        RoomsRegisterFragment.chosenOption = choice;
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

        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
//        modelEntry = new ViewModelProvider(requireActivity()).get(ViewModelEntry.class);

        roomBundleID.putInt(SCHOOL_ID_VALUE, schoolID);
        roomBundleID.putInt(ROOM_TYPE, chosenOption);

        manager = getChildFragmentManager();

        switch (chosenOption) {
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

//        modelEntry.getLastRoomEntry().observe(getViewLifecycleOwner(), lastRoom -> {
//            if (update > 0) {
//                recentRoomID = lastRoom.getRoomID();
//            }
//        });

        modelFragments.getRoomBundle().observe(getViewLifecycleOwner(), roomBundle -> {
            if (roomBundle != null) {
                if (update == 0) {
                    RoomEntry newEntry = newRoomEntry(roomBundle);
                    ViewModelEntry.insertRoomEntry(newEntry);
                } else {
                    RoomEntry updateEntry = newRoomEntry(roomBundle);
                    updateEntry.setRoomID(recentRoomID);
                    ViewModelEntry.updateRoom(updateEntry);
                }
                modelFragments.setRoomBundle(null);
                update = 0;
            }
        });

        doorRegister.setOnClickListener(v -> {
            saveRoomData();
            addDoorDialog();
            update++;
        });

        switchRegister.setOnClickListener(v -> {
            saveRoomData();
            addSwitchDialog();
            update++;
        });

        windowRegister.setOnClickListener(v -> {
            saveRoomData();
            addWindowDialog();
            update++;
        });

        tableRegister.setOnClickListener(v -> {
            saveRoomData();
            addTableDialog();
            update++;
        });

        freeSpaceRegister.setOnClickListener(v -> {
            saveRoomData();
            addFreeSpaceDialog();
            update++;
        });

        cancelRoomRegister.setOnClickListener(v -> Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction().remove(this).commit());

        saveRoomRegister.setOnClickListener(v -> saveRoomData());
    }

    public void saveRoomData() {
        checkEmptyRoomFields();
        if (update == 0) {
            if (checkEmptyRoomFields()) {
                if (chosenOption == 3 || chosenOption == 10 || chosenOption == 11 || chosenOption == 15)
                    modelFragments.setSaveAttemptRooms(1);
                else {
                    RoomEntry newEntry = newRoomEntry(roomBundleID);
                    ViewModelEntry.insertRoomEntry(newEntry);
                }
            } else
                Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        } else {
            if (checkEmptyRoomFields()) {
                if (chosenOption == 3 || chosenOption == 10 || chosenOption == 11 || chosenOption == 15)
                    modelFragments.setSaveAttemptRooms(1);
                else {
                    RoomEntry updateEntry = newRoomEntry(roomBundleID);
                    updateEntry.setRoomID(recentRoomID);
                    ViewModelEntry.updateRoom(updateEntry);
                }
            } else
                Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();

        }
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

    public RoomEntry newRoomEntry(Bundle bundle) {
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

        switch (chosenOption) {
            case 3:
                libShelvesDistOK = bundle.getInt(LibraryFragment.DISTANCE_SHELVES, 0);
                libPcrManeuverOK = bundle.getInt(LibraryFragment.MANEUVER_PCR, 0);
                libAccessPcOK = bundle.getInt(LibraryFragment.COMPUTER_ACCESSIBLE, 0);
                break;
            case 10:
                cafeSpinOK = bundle.getInt(CafeteriaFragment.CAFE_SPIN, 0);
                break;
            case 11:
                classBoardHeight = bundle.getDouble(ClassroomFragment.BOARD_HEIGHT, 0.0);
                break;
            case 15:
                secFixedSeat = bundle.getInt(SecretariatFragment.FIXED_SEATS, 0);
                secHasPcrSpace = bundle.getInt(SecretariatFragment.HAS_PCR_SPACE, 0);
                secWidthPcrSpace = bundle.getDouble(SecretariatFragment.PCR_WIDTH, 0.0);
                secDepthPcrSpace = bundle.getDouble(SecretariatFragment.PCR_DEPTH, 0.0);
                secSpinOK = bundle.getInt(SecretariatFragment.SECRETARIAT_SPIN, 0);
                break;
            default:
                break;
        }

        return new RoomEntry(schoolID, chosenOption, hasVisSign, obsVisSign, hasTactSign, obsTactSign, libShelvesDistOK,
                libPcrManeuverOK, libAccessPcOK, cafeSpinOK, classBoardHeight, secFixedSeat, secHasPcrSpace, secWidthPcrSpace,
                secDepthPcrSpace, secSpinOK);
    }

    private void addDoorDialog() {
        AddDoorDialog.displayDoorDialog(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), roomBundleID);
    }

    private void addSwitchDialog() {
        AddSwitchDialog.displaySwitchDialog(Objects.requireNonNull(getActivity()).getSupportFragmentManager());
    }

    private void addWindowDialog() {
        AddWindowDialog.displayWindowDialog(Objects.requireNonNull(getActivity()).getSupportFragmentManager());
    }

    private void addFreeSpaceDialog() {
        AddFreeSpaceDialog.displayFreeSpaceDialog(Objects.requireNonNull(getActivity()).getSupportFragmentManager());
    }

    private void addTableDialog() {
        AddTableDialog.addTableDialog(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), roomBundleID);
    }

}