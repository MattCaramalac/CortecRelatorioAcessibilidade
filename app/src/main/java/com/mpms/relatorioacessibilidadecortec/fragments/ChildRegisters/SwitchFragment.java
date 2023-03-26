package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.SwitchEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

public class SwitchFragment extends Fragment implements TagInterface, ScrollEditText {

    TextInputLayout switchLocaleField, switchTypeField, switchHeightField, switchObsField, photoField;
    TextInputEditText switchLocaleValue, switchTypeValue, switchHeightValue, switchObsValue, photoValue;
    MaterialButton cancelSwitch, saveSwitch;

    Bundle switchBundle;

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
        if (this.getArguments() != null)
            switchBundle = new Bundle(this.getArguments());
        else
            switchBundle = new Bundle();
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
            else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });

        cancelSwitch.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

    }

    private void instantiateSwitchViews(View v) {
//        TextInputLayout
        switchLocaleField = v.findViewById(R.id.switch_placement_field);
        switchTypeField = v.findViewById(R.id.switch_type_field);
        switchHeightField = v.findViewById(R.id.switch_height_field);
        switchObsField = v.findViewById(R.id.switch_obs_field);
        photoField = v.findViewById(R.id.switch_photo_field);
//        TextInputEditText
        switchLocaleValue = v.findViewById(R.id.switch_placement_value);
        switchTypeValue = v.findViewById(R.id.switch_type_value);
        switchHeightValue = v.findViewById(R.id.switch_height_value);
        switchObsValue = v.findViewById(R.id.switch_obs_value);
        photoValue = v.findViewById(R.id.switch_photo_value);
//        MaterialButton
        cancelSwitch = v.findViewById(R.id.cancel_switch);
        saveSwitch = v.findViewById(R.id.save_switch);
//        ViewModels
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//
        allowObsScroll(switchObsValue);
    }

    private SwitchEntry newSwitchEntry(Bundle bundle) {
        String switchLocale, switchType, switchObs = null, photo = null;
        double switchHeight;

        switchLocale = String.valueOf(switchLocaleValue.getText());
        switchType = String.valueOf(switchTypeValue.getText());
        switchHeight = Double.parseDouble(String.valueOf(switchHeightValue.getText()));
        if (switchObsValue.getText() != null)
            switchObs = String.valueOf(switchObsValue.getText());
        if (photoValue.getText() != null)
            photo = String.valueOf(photoValue.getText());

        return new SwitchEntry(bundle.getInt(AMBIENT_ID), switchLocale, switchType, switchHeight, switchObs, photo);
    }

    private void loadSwitchData(SwitchEntry switchEntry) {
        switchLocaleValue.setText(switchEntry.getSwitchLocation());
        switchTypeValue.setText(switchEntry.getSwitchType());
        switchHeightValue.setText(String.valueOf(switchEntry.getSwitchHeight()));
        if (switchEntry.getSwitchObs() != null) {
            switchObsValue.setText(switchEntry.getSwitchObs());
        }
        if (switchEntry.getSwitchPhoto() != null) {
            photoValue.setText(switchEntry.getSwitchPhoto());
        }
    }

    private boolean switchNoEmptyFields() {
        clearSwitchEmptyFieldError();
        int i = 0;
        if (TextUtils.isEmpty(switchLocaleValue.getText())) {
            i++;
            switchLocaleField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(switchTypeValue.getText())) {
            i++;
            switchTypeField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(switchHeightValue.getText())) {
            i++;
            switchHeightField.setError(getString(R.string.req_field_error));
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
        switchObsValue.setText(null);
        photoValue.setText(null);
    }
}