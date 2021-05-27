package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

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
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogFragments.InclinationSillFragment;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogFragments.SlopeSillFragment;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogFragments.StepSillFragment;
import com.mpms.relatorioacessibilidadecortec.R;

import java.util.Objects;


public class AddDoorDialog extends DialogFragment {


    private static final String SCHOOL_ID_VALUE = "SCHOOL_ID_VALUE";
    private static final String ROOM_TYPE = "ROOM_TYPE";

    private Toolbar toolbar;

    TextInputLayout doorLocationField, doorWidthField, doorSillObsField;
    TextInputEditText doorLocationValue, doorWidthValue, doorSillObsValue;
    RadioGroup sillType;
    Button saveDoor, cancelDoor;
    TextView sillTypeError;

    FragmentManager manager;

    public static AddDoorDialog displayDoorDialog(FragmentManager fragmentManager) {
        AddDoorDialog addDoorDialog = new AddDoorDialog();
        addDoorDialog.show(fragmentManager, "DOOR_DIALOG");
        return addDoorDialog;
    }


//    public AddDoorFragment() {
//        // Required empty public constructor
//    }
//
//    public static AddDoorFragment newInstance() {
//        return new AddDoorFragment();
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_add_door, container, false);
        toolbar = view.findViewById(R.id.door_toolbar);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle(getString(R.string.dialog_add_door_header));

        doorLocationField = view.findViewById(R.id.door_placement_field);
        doorWidthField = view.findViewById(R.id.door_width_field);
        doorSillObsField = view.findViewById(R.id.door_sill_obs_field);

        doorLocationValue = view.findViewById(R.id.door_placement_value);
        doorWidthValue = view.findViewById(R.id.door_width_value);
        doorSillObsValue = view.findViewById(R.id.door_sill_obs_value);

        sillType = view.findViewById(R.id.type_sill_radio);

        sillTypeError = view.findViewById(R.id.sill_type_error);

        saveDoor = view.findViewById(R.id.save_door);
        cancelDoor = view.findViewById(R.id.cancel_door);

        manager = getChildFragmentManager();

        sillType.setOnCheckedChangeListener((group, checkedId) -> {
            int index = getCheckedRadio(group);
            switch (index) {
                case 0:
                    getChildFragmentManager().beginTransaction().replace(R.id.sill_type_child_fragment, new InclinationSillFragment()).commit();
                    break;
                case 1:
                    getChildFragmentManager().beginTransaction().replace(R.id.sill_type_child_fragment, new StepSillFragment()).commit();
                    break;
                case 2:
                    getChildFragmentManager().beginTransaction().replace(R.id.sill_type_child_fragment, new SlopeSillFragment()).commit();
                    break;
                default:
                    removeFragments();
                    break;
            }

        });

        saveDoor.setOnClickListener(v -> checkEmptyFields());

        cancelDoor.setOnClickListener(v -> Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction().remove(this).commit());

    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int lenght = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width,lenght);
        }
    }

    public boolean checkEmptyFields() {
        clearErrorsDoorDialog();
        int error = 0;
        if (sillType.getCheckedRadioButtonId() == -1) {
            sillTypeError.setVisibility(View.VISIBLE);
            error++;
        }
        if (TextUtils.isEmpty(doorLocationValue.getText())) {
            doorLocationField.setError(getString(R.string.blank_field_error));
            error++;
        }
        if (TextUtils.isEmpty(doorWidthValue.getText())) {
            doorWidthField.setError(getString(R.string.blank_field_error));
            error++;
        }
        return error == 0;
    }

    public int getCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public void clearErrorsDoorDialog() {
        sillTypeError.setVisibility(View.GONE);
        doorLocationField.setErrorEnabled(false);
        doorWidthField.setErrorEnabled(false);

    }

    public void removeFragments() {
        try {
            InclinationSillFragment inclination = (InclinationSillFragment) manager.findFragmentById(R.id.sill_type_child_fragment);
            if (inclination != null)
                manager.beginTransaction().remove(inclination).commit();
        } catch (Exception e) {
            try {
                StepSillFragment step = (StepSillFragment) manager.findFragmentById(R.id.sill_type_child_fragment);
                if (step != null)
                manager.beginTransaction().remove(step).commit();
            } catch (Exception f) {
                try {
                    SlopeSillFragment slope = (SlopeSillFragment) manager.findFragmentById(R.id.sill_type_child_fragment);
                    if (slope != null)
                        manager.beginTransaction().remove(slope).commit();
                } catch (Exception g) {
                    Toast.makeText(getContext(), "Algo deu errado, tente novamente", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}