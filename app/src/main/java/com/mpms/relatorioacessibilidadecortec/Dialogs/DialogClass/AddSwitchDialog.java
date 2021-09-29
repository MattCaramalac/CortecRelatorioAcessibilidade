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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.entities.SwitchEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class AddSwitchDialog extends DialogFragment {

    TextInputLayout switchPlaceField, switchTypeField, switchHeightField, switchObsField;
    TextInputEditText switchPlaceValue, switchTypeValue, switchHeightValue, switchObsValue;
    Button saveSwitch, cancelSwitch;

    String switchLocation, switchType, switchObs;
    Double switchHeight;

    private static Bundle roomBundle;

    private Toolbar toolbar;

    static int schoolID, roomType, roomID;

    public static AddSwitchDialog displaySwitchDialog(FragmentManager fragmentManager, Bundle bundle) {
        AddSwitchDialog addSwitchDialog = new AddSwitchDialog();
        addSwitchDialog.show(fragmentManager, "SWITCH_DIALOG");
        roomBundle= bundle;
        return  addSwitchDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_switch_dialog, container, false);
        toolbar = view.findViewById(R.id.switch_toolbar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle(R.string.dialog_add_switch_header);

        instantiateSwitchViews(view);
        allowSwitchObsScroll();

        saveSwitch.setOnClickListener(v -> {
            if (checkEmptySwitchFields()) {
                SwitchEntry newSwitch = newSwitchEntry(roomBundle);
                ViewModelEntry.insertSwitchEntry(newSwitch);
                clearSwitchFields();
            }
        });

        cancelSwitch.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
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

    private void instantiateSwitchViews(View view) {
        switchPlaceField = view.findViewById(R.id.switch_placement_field);
        switchTypeField = view.findViewById(R.id.switch_type_field);
        switchHeightField = view.findViewById(R.id.switch_height_field);
        switchObsField = view.findViewById(R.id.switch_obs_field);

        switchPlaceValue = view.findViewById(R.id.switch_placement_value);
        switchTypeValue = view.findViewById(R.id.switch_type_value);
        switchHeightValue = view.findViewById(R.id.switch_height_value);
        switchObsValue = view.findViewById(R.id.switch_obs_value);

        saveSwitch = view.findViewById(R.id.save_switch);
        cancelSwitch = view.findViewById(R.id.cancel_switch);
    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowSwitchObsScroll() {
        switchObsValue.setOnTouchListener(this::scrollingField);
    }

    public boolean checkEmptySwitchFields() {
        clearErrorsSwitchFields();
        int error = 0;
        if (TextUtils.isEmpty(switchPlaceValue.getText())) {
            switchPlaceField.setError(getString(R.string.blank_field_error));
            error++;
        }
        if (TextUtils.isEmpty(switchTypeValue.getText())) {
            switchTypeField.setError(getString(R.string.blank_field_error));
            error++;
        }
        if (TextUtils.isEmpty(switchHeightValue.getText())) {
            switchHeightField.setError(getString(R.string.blank_field_error));
            error++;
        }
        return error == 0;
    }

    public void clearErrorsSwitchFields() {
        switchPlaceField.setErrorEnabled(false);
        switchTypeField.setErrorEnabled(false);
        switchHeightField.setErrorEnabled(false);
    }

    public SwitchEntry newSwitchEntry(Bundle bundle) {
        switchLocation = Objects.requireNonNull(switchPlaceValue.getText()).toString();
        switchType = Objects.requireNonNull(switchTypeValue.getText()).toString();
        switchHeight = Double.parseDouble(Objects.requireNonNull(switchHeightValue.getText()).toString());
        if (!TextUtils.isEmpty(switchObsValue.getText()))
            switchObs = Objects.requireNonNull(switchObsValue.getText()).toString();
        else
            switchObs = null;

        return new SwitchEntry(bundle.getInt(InspectionActivity.SCHOOL_ID_VALUE), bundle.getInt(RoomsRegisterFragment.ROOM_ID_VALUE),
                switchLocation, switchType,switchHeight,switchObs);
    }

    public void clearSwitchFields() {
        switchPlaceValue.setText(null);
        switchTypeValue.setText(null);
        switchHeightValue.setText(null);
        switchObsValue.setText(null);
    }
}