package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.BlockRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SillInclinationFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SillSlopeFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SillStepFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelDialog;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;


public class AddDoorDialog extends DialogFragment {


    private Toolbar toolbar;

    TextInputLayout doorLocationField, doorWidthField, doorSillObsField;
    TextInputEditText doorLocationValue, doorWidthValue, doorSillObsValue;
    RadioGroup sillType;
    Button saveDoor, cancelDoor;
    TextView sillTypeError;

    FragmentManager manager;

    static Bundle roomBundle;

    ViewModelDialog modelDialog;

    ViewModelEntry modelEntry;

    Double sillInclinationHeight, sillStepHeight, sillSlopeInclination, sillSlopeWidth;
    String doorSillObs;

    public static AddDoorDialog displayDoorDialog(FragmentManager fragmentManager, Bundle bundle) {
        AddDoorDialog addDoorDialog = new AddDoorDialog();
        addDoorDialog.show(fragmentManager, "DOOR_DIALOG");
        roomBundle = bundle;
        return addDoorDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_add_door, container, false);
        toolbar = view.findViewById(R.id.door_toolbar);
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        modelDialog = new ViewModelProvider(requireActivity()).get(ViewModelDialog.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle(getString(R.string.dialog_add_door_header));

        instantiateDoorObsField(view);
        allowDoorSillObsScroll();

        manager = getChildFragmentManager();

        modelDialog.getDoorInfo().observe(getViewLifecycleOwner(), sillBundle -> {
            if (sillBundle != null) {
                sillBundle.putInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER, roomBundle.getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER));
                sillBundle.putInt(RoomsRegisterFragment.ROOM_ID, roomBundle.getInt(RoomsRegisterFragment.ROOM_ID));
                DoorEntry doorEntry = newDoor(sillBundle);
                ViewModelEntry.insertDoorEntry(doorEntry);
                Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                clearDoorFields();
                modelDialog.setDoorInfo(null);
            }
        });

        sillType.setOnCheckedChangeListener((group, checkedId) -> {
            int index = getCheckedRadio(group);
            switch (index) {
                case 1:
                    getChildFragmentManager().beginTransaction().replace(R.id.sill_type_child_fragment, new SillInclinationFragment()).commit();
                    break;
                case 2:
                    getChildFragmentManager().beginTransaction().replace(R.id.sill_type_child_fragment, new SillStepFragment()).commit();
                    break;
                case 3:
                    getChildFragmentManager().beginTransaction().replace(R.id.sill_type_child_fragment, new SillSlopeFragment()).commit();
                    break;
                default:
                    removeFragments();
                    break;
            }

        });

        saveDoor.setOnClickListener(v -> {
            if(checkEmptyFields()){
                if(getCheckedRadio(sillType) == 0) {
                    DoorEntry doorEntry = newDoor(roomBundle);
                    ViewModelEntry.insertDoorEntry(doorEntry);
                    clearDoorFields();
                } else {
                    modelDialog.setSaveDoorAttempt(1);
                }
            }
        });

        cancelDoor.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .beginTransaction().remove(this).commit());
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int length = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width,length);
        }
    }

    private void instantiateDoorObsField(View view) {
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
    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowDoorSillObsScroll() {
        doorSillObsValue.setOnTouchListener(this::scrollingField);
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
        Fragment fragment = manager.findFragmentById(R.id.sill_type_child_fragment);
        if (fragment != null)
            manager.beginTransaction().remove(fragment).commit();
    }

    public DoorEntry newDoor(Bundle bundle) {
        sillInclinationHeight = null;
        sillStepHeight = null;
        sillSlopeInclination = null;
        sillSlopeWidth = null;
        doorSillObs = null;
        if (getCheckedRadio(sillType) == 1)
            sillInclinationHeight = bundle.getDouble(SillInclinationFragment.HEIGHT_INCLINED_SILL);
        else if (getCheckedRadio(sillType) == 2)
            sillStepHeight = bundle.getDouble(SillStepFragment.STEP_HEIGHT);
        else if (getCheckedRadio(sillType) == 3) {
            sillSlopeInclination = bundle.getDouble(SillSlopeFragment.SLOPE_INCLINATION);
            sillSlopeWidth = bundle.getDouble(SillSlopeFragment.SLOPE_WIDTH);
        }

        if (!TextUtils.isEmpty(doorSillObsValue.getText())) {
            doorSillObs = Objects.requireNonNull(doorSillObsValue.getText()).toString();
        }

        return new DoorEntry(bundle.getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER), bundle.getInt(RoomsRegisterFragment.ROOM_ID),
                Objects.requireNonNull(doorLocationValue.getText()).toString(), Double.parseDouble(Objects.requireNonNull(doorWidthValue.getText()).toString()),
                getCheckedRadio(sillType),sillInclinationHeight, sillStepHeight, sillSlopeInclination, sillSlopeWidth, doorSillObs);

    }

    public void clearDoorFields() {
        doorLocationValue.setText(null);
        doorWidthValue.setText(null);
        doorSillObsValue.setText(null);
        sillType.clearCheck();
    }
}