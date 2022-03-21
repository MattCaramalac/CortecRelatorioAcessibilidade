package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.SwitchEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

public class SwitchFragment extends Fragment {

    public static final String SWITCH_ID = "SWITCH_ID";

    TextInputLayout switchLocaleField, switchTypeField, switchHeightField, switchObsField;
    TextInputEditText switchLocaleValue, switchTypeValue, switchHeightValue, switchObsValue;
    TextView switchHeader;
    MaterialButton cancelSwitch, saveSwitch;

    Bundle switchBundle = new Bundle();

    ViewModelEntry modelEntry;

    public SwitchFragment() {
        // Required empty public constructor
    }

    public static SwitchFragment newInstance() {
        return new SwitchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            switchBundle.putInt(RoomsRegisterFragment.ROOM_ID, this.getArguments().getInt(RoomsRegisterFragment.ROOM_ID));
            switchBundle.putInt(SWITCH_ID, this.getArguments().getInt(SWITCH_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_switch, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        instantiateSwitchViews(view);

        if (switchBundle.getInt(SWITCH_ID) > 0)
            modelEntry.getSpecificSwitch(switchBundle.getInt(SWITCH_ID)).observe(getViewLifecycleOwner(), this::loadSwitchData);

        saveSwitch.setOnClickListener(v -> {
            if (switchNoEmptyFields()) {
                SwitchEntry newSwitch = newSwitchEntry(switchBundle);
                if (switchBundle.getInt(SWITCH_ID) > 0) {
                    newSwitch.setSwitchID(switchBundle.getInt(SWITCH_ID));
                    ViewModelEntry.updateSwitch(newSwitch);
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStackImmediate();
                } else if (switchBundle.getInt(SWITCH_ID) == 0) {
                    ViewModelEntry.insertSwitchEntry(newSwitch);
                    clearSwitchFields();
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                } else {
                    switchBundle.putInt(SWITCH_ID, 0);
                    Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelSwitch.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

    }

    private void instantiateSwitchViews(View v) {
//        TextInputLayout
        switchLocaleField = v.findViewById(R.id.switch_placement_field);
        switchTypeField = v.findViewById(R.id.switch_type_field);
        switchHeightField = v.findViewById(R.id.switch_height_field);
        switchObsField = v.findViewById(R.id.switch_obs_field);
//        TextInputEditText
        switchLocaleValue = v.findViewById(R.id.switch_placement_value);
        switchTypeValue = v.findViewById(R.id.switch_type_value);
        switchHeightValue = v.findViewById(R.id.switch_height_value);
        switchObsValue = v.findViewById(R.id.switch_obs_value);
//        MaterialButton
        cancelSwitch = v.findViewById(R.id.cancel_switch);
        saveSwitch = v.findViewById(R.id.save_switch);
//        TextView
        switchHeader = v.findViewById(R.id.switch_register_header);
        switchHeader.setText(R.string.header_switch_register);
//        ViewModels
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//
        allowSwitchScrollFields();
    }

    private SwitchEntry newSwitchEntry(Bundle bundle) {
        String switchLocale, switchType, switchObs = null;
        double switchHeight;

        switchLocale = String.valueOf(switchLocaleValue.getText());
        switchType = String.valueOf(switchTypeValue.getText());
        switchHeight = Double.parseDouble(String.valueOf(switchHeightValue.getText()));
        if (switchObsValue.getText() != null) {
            switchObs = String.valueOf(switchObsValue.getText());
        }

        return new SwitchEntry(bundle.getInt(RoomsRegisterFragment.ROOM_ID), switchLocale, switchType, switchHeight, switchObs);
    }

    private void loadSwitchData(SwitchEntry switchEntry) {
        switchLocaleValue.setText(switchEntry.getSwitchLocation());
        switchTypeValue.setText(switchEntry.getSwitchType());
        switchHeightValue.setText(String.valueOf(switchEntry.getSwitchHeight()));
        if (switchEntry.getSwitchObs() != null) {
            switchObsValue.setText(switchEntry.getSwitchObs());
        }
    }

    private boolean switchNoEmptyFields() {
        clearSwitchEmptyFieldError();
        int i = 0;
        if (TextUtils.isEmpty(switchLocaleValue.getText())) {
            i++;
            switchLocaleField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(switchTypeValue.getText())) {
            i++;
            switchTypeField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(switchHeightValue.getText())) {
            i++;
            switchHeightField.setError(getString(R.string.blank_field_error));
        }

        return i == 0;
    }

    private void clearSwitchEmptyFieldError() {
        switchLocaleField.setErrorEnabled(false);
        switchTypeField.setErrorEnabled(false);
        switchHeightField.setErrorEnabled(false);
    }

    private void clearSwitchFields() {
        switchLocaleValue.setText(null);
        switchTypeValue.setText(null);
        switchHeightValue.setText(null);
    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowSwitchScrollFields() {
        switchObsValue.setOnTouchListener(this::scrollingField);
    }
}