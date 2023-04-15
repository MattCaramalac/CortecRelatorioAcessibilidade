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
import com.mpms.relatorioacessibilidadecortec.data.entities.EquipmentEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;

public class EquipmentFragment extends Fragment implements TagInterface, ScrollEditText {

    TextInputLayout equipTypeField, equipLocalField, equipHeightField, equipObsField, photoField;
    TextInputEditText equipTypeValue, equipLocalValue, equipHeightValue, equipObsValue, photoValue;
    MaterialButton saveEquip, cancelEquip;

    ViewModelEntry modelEntry;

    Bundle equipBundle;

    public EquipmentFragment() {
        // Required empty public constructor
    }

    public static EquipmentFragment newInstance() {
        return new EquipmentFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            equipBundle = new Bundle(this.getArguments());
        else
            equipBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_equipment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateViews(view);

        if (equipBundle.getInt(EQUIP_ID) > 0)
            modelEntry.getSpecificEquipment(equipBundle.getInt(EQUIP_ID)).observe(getViewLifecycleOwner(), this::loadEquipData);
    }

    private void instantiateViews(View v) {

//        TextInputLayout
        equipTypeField = v.findViewById(R.id.equip_name_field);
        equipLocalField = v.findViewById(R.id.equip_locale_field);
        equipHeightField = v.findViewById(R.id.equip_height_field);
        equipObsField = v.findViewById(R.id.equip_obs_field);
        photoField = v.findViewById(R.id.equip_photo_field);
//        TextInputEditText
        equipTypeValue = v.findViewById(R.id.equip_name_value);
        equipLocalValue = v.findViewById(R.id.equip_locale_value);
        equipHeightValue = v.findViewById(R.id.equip_height_value);
        equipObsValue = v.findViewById(R.id.equip_obs_value);
        photoValue = v.findViewById(R.id.equip_photo_value);
//        MaterialButton
        saveEquip = v.findViewById(R.id.save_equip);
        saveEquip.setOnClickListener(this::buttonClick);
        cancelEquip = v.findViewById(R.id.cancel_equip);
        cancelEquip.setOnClickListener(this::buttonClick);

//        ViewModelEntry
        modelEntry = new ViewModelEntry(requireActivity().getApplication());

        allowObsScroll(equipObsValue);
    }

    private void loadEquipData(EquipmentEntry equip) {
        if (equip.getEquipName() != null)
            equipTypeValue.setText(equip.getEquipName());
        if (equip.getEquipLocale() != null)
            equipLocalValue.setText(equip.getEquipLocale());
        equipHeightValue.setText(String.valueOf(equip.getEquipHeight()));
        if (equip.getEquipObs() != null)
            equipObsValue.setText(equip.getEquipObs());
        if (equip.getEquipPhoto() != null)
            photoValue.setText(equip.getEquipPhoto());
    }

    private void buttonClick(View view) {
        if (view == saveEquip) {
            if (checkEmptyFields()) {
                EquipmentEntry nEquip = newEquip(equipBundle);
                if (equipBundle.getInt(EQUIP_ID) == 0) {
                    ViewModelEntry.insertEquip(nEquip);
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                    clearFields();
                }
                else if (equipBundle.getInt(EQUIP_ID) > 0) {
                    nEquip.setEquipID(equipBundle.getInt(EQUIP_ID));
                    ViewModelEntry.updateEquipment(nEquip);
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStackImmediate();
                }
                else {
                    equipBundle.putInt(EQUIP_ID, 0);
                    Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                }
            }
        } else
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
    }

    private void clearFields() {
        equipTypeValue.setText(null);
        equipLocalValue.setText(null);
        equipHeightValue.setText(null);
        equipObsValue.setText(null);
        photoValue.setText(null);
    }

    private EquipmentEntry newEquip(Bundle bundle) {
        Integer room = null, circ = null;
        String eType, eLocal = null, eObs = null, photo = null;
        double eHeight;

        if (bundle.getInt(AMBIENT_ID) > 0)
            room = bundle.getInt(AMBIENT_ID);
        else if (bundle.getInt(CIRC_ID) > 0)
            circ = bundle.getInt(CIRC_ID);

        eType = String.valueOf(equipTypeValue.getText());
        if (!TextUtils.isEmpty(equipLocalValue.getText()))
            eLocal = String.valueOf(equipLocalValue.getText());
        eHeight = Double.parseDouble(String.valueOf(equipHeightValue.getText()));
        if (!TextUtils.isEmpty(equipObsValue.getText()))
            eObs = String.valueOf(equipObsValue.getText());
        if (!TextUtils.isEmpty(photoValue.getText()))
            photo = String.valueOf(photoValue.getText());

        return new EquipmentEntry(room, circ, eType, eLocal, eHeight, eObs, photo);
    }

    private boolean checkEmptyFields() {
        equipTypeField.setErrorEnabled(false);
        equipHeightField.setErrorEnabled(false);
        int i = 0;
        if (TextUtils.isEmpty(equipTypeValue.getText())) {
            i++;
            equipTypeField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(equipHeightValue.getText())) {
            i++;
            equipHeightField.setError(getString(R.string.req_field_error));
        }

        return i == 0;
    }
}