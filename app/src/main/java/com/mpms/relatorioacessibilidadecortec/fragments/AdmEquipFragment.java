package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.BlockRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.AdmEquipEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

public class AdmEquipFragment extends Fragment {

    public static final String EQUIP_ID = "EQUIP_ID";

    TextInputLayout equipLocaleField, soundSignalHeightField, soundSignalObsField, phoneHeightField, phoneObsField,
            biometryHeightField, biometryObsField;
    TextInputEditText equipLocaleValue, soundSignalHeightValue, soundSignalObsValue, phoneHeightValue, phoneObsValue,
            biometryHeightValue, biometryObsValue;
    RadioGroup soundSignalRadio, phoneRadio, biometryRadio;
    TextView soundSignalError, phoneError, biometryError;
    MaterialButton cancelEquip, saveEquip;

    ViewModelEntry modelEntry;

    Bundle equipBundle = new Bundle();

    public AdmEquipFragment() {
        // Required empty public constructor
    }

    public static AdmEquipFragment newInstance() {
        return new AdmEquipFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null){
            equipBundle.putInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER, this.getArguments().getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER));
            equipBundle.putInt(EQUIP_ID, this.getArguments().getInt(EQUIP_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adm_equipment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        instantiateEquipView(view);

        if (equipBundle.getInt(EQUIP_ID) > 0)
            modelEntry.getOneAdmEquip(equipBundle.getInt(EQUIP_ID)).observe(getViewLifecycleOwner(), this::loadEquipData);


        saveEquip.setOnClickListener(v-> {
            if (checkEmptyEquipFields()) {
                AdmEquipEntry newEquip = createEquipEntry(equipBundle);
                if (equipBundle.getInt(EQUIP_ID) > 0) {
                    newEquip.setAdmEquipID(equipBundle.getInt(EQUIP_ID));
                    ViewModelEntry.updateAdmEquip(newEquip);
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                    clearEquipFields();
                    requireActivity().getSupportFragmentManager().popBackStackImmediate();
                } else if (equipBundle.getInt(EQUIP_ID) == 0) {
                    ViewModelEntry.insertAdmEquip(newEquip);
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                    clearEquipFields();
                }
                else {
                    equipBundle.putInt(EQUIP_ID, 0);
                    Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelEquip.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void instantiateEquipView(View view) {
//        TextInputLayout
        equipLocaleField = view.findViewById(R.id.equip_location_field);
        soundSignalHeightField = view.findViewById(R.id.bell_activation_height_field);
        soundSignalObsField = view.findViewById(R.id.bell_activation_obs_field);
        phoneHeightField = view.findViewById(R.id.intercom_height_field);
        phoneObsField = view.findViewById(R.id.locale_has_intercom_obs_field);
        biometryHeightField = view.findViewById(R.id.biometry_height_field);
        biometryObsField = view.findViewById(R.id.locale_has_biometry_obs_field);
//        TextInputEditText
        equipLocaleValue = view.findViewById(R.id.equip_location_value);
        soundSignalHeightValue = view.findViewById(R.id.bell_activation_height_value);
        soundSignalObsValue = view.findViewById(R.id.bell_activation_obs_value);
        phoneHeightValue = view.findViewById(R.id.intercom_height_value);
        phoneObsValue = view.findViewById(R.id.locale_has_intercom_obs_value);
        biometryHeightValue = view.findViewById(R.id.biometry_height_value);
        biometryObsValue = view.findViewById(R.id.locale_has_biometry_obs_value);
//        RadioGroup
        soundSignalRadio = view.findViewById(R.id.has_bell_activation_radio);
        phoneRadio = view.findViewById(R.id.locale_has_intercom_radio);
        biometryRadio = view.findViewById(R.id.locale_has_biometry_radio);
//        TextView
        soundSignalError = view.findViewById(R.id.has_bell_activation_error);
        phoneError = view.findViewById(R.id.locale_has_intercom_error);
        biometryError = view.findViewById(R.id.locale_has_biometry_error);
//        MaterialButton
        cancelEquip = view.findViewById(R.id.cancel_equip);
        saveEquip = view.findViewById(R.id.save_equip);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());

//        Listeners
        soundSignalRadio.setOnCheckedChangeListener(this::equipRadioListener);
        phoneRadio.setOnCheckedChangeListener(this::equipRadioListener);
        biometryRadio.setOnCheckedChangeListener(this::equipRadioListener);
    }

    private int getRadioCheck(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private void equipRadioListener(RadioGroup radio, int checkedID) {
        int index = getRadioCheck(radio);
        if (radio == soundSignalRadio) {
            if (index == 1) {
                soundSignalHeightField.setVisibility(View.VISIBLE);
                soundSignalObsField.setVisibility(View.VISIBLE);
            } else {
                soundSignalHeightValue.setText(null);
                soundSignalObsValue.setText(null);
                soundSignalHeightField.setVisibility(View.GONE);
                soundSignalObsField.setVisibility(View.GONE);
            }
        } else if (radio == phoneRadio) {
            if (index == 1) {
                phoneHeightField.setVisibility(View.VISIBLE);
                phoneObsField.setVisibility(View.VISIBLE);
            } else {
                phoneHeightValue.setText(null);
                phoneObsValue.setText(null);
                phoneHeightField.setVisibility(View.GONE);
                phoneObsField.setVisibility(View.GONE);
            }
        } else {
            if (index == 1) {
                biometryHeightField.setVisibility(View.VISIBLE);
                biometryObsField.setVisibility(View.VISIBLE);
            } else {
                biometryHeightValue.setText(null);
                biometryObsValue.setText(null);
                biometryHeightField.setVisibility(View.GONE);
                biometryObsField.setVisibility(View.GONE);
            }
        }
    }

    private void loadEquipData(AdmEquipEntry equip) {
        equipLocaleValue.setText(equip.getAdmEquipLocation());
        soundSignalRadio.check(soundSignalRadio.getChildAt(equip.getHasBellControl()).getId());
        if (equip.getHasBellControl() == 1) {
            soundSignalHeightValue.setText(String.valueOf(equip.getBellControlHeight()));
            if (equip.getBellControlObs() != null)
                soundSignalObsValue.setText(equip.getBellControlObs());
        }
        phoneRadio.check(phoneRadio.getChildAt(equip.getHasInternalPhone()).getId());
        if (equip.getHasInternalPhone() == 1) {
            phoneHeightValue.setText(String.valueOf(equip.getInternalPhoneHeight()));
            if (equip.getInternalPhoneObs() != null)
                phoneObsValue.setText(equip.getInternalPhoneObs());
        }
        biometryRadio.check(biometryRadio.getChildAt(equip.getHasBiometricClock()).getId());
        if (equip.getHasBiometricClock() == 1){
            biometryHeightValue.setText(String.valueOf(equip.getBiometricClockHeight()));
            if (equip.getBiometricClockObs() != null)
                biometryObsValue.setText(equip.getBiometricClockObs());
        }
    }

    private AdmEquipEntry createEquipEntry(Bundle bundle) {
        String equipLocale, soundSignalObs = null, internalPhoneObs = null, biometryObs = null;
        Double soundSignalHeight = null, internalPhoneHeight = null, biometryHeight = null;
        int hasSoundSignal, hasInternalPhone, hasBiometry;

        equipLocale = String.valueOf(equipLocaleValue.getText());
        hasSoundSignal = getRadioCheck(soundSignalRadio);
        if (getRadioCheck(soundSignalRadio) == 1) {
            soundSignalHeight = Double.valueOf(String.valueOf(soundSignalHeightValue.getText()));
            soundSignalObs = String.valueOf(soundSignalObsValue.getText());
        }
        hasInternalPhone = getRadioCheck(phoneRadio);
        if (getRadioCheck(phoneRadio) == 1) {
            internalPhoneHeight = Double.valueOf(String.valueOf(phoneHeightValue.getText()));
            internalPhoneObs = String.valueOf(phoneObsValue.getText());
        }
        hasBiometry = getRadioCheck(biometryRadio);
        if (getRadioCheck(biometryRadio) == 1) {
            biometryHeight = Double.valueOf(String.valueOf(biometryHeightValue.getText()));
            biometryObs = String.valueOf(biometryObsValue.getText());
        }
        return new AdmEquipEntry(bundle.getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER), equipLocale, hasSoundSignal, soundSignalHeight, soundSignalObs,
                hasInternalPhone, internalPhoneHeight, internalPhoneObs, hasBiometry, biometryHeight, biometryObs);

    }

    private boolean checkEmptyEquipFields() {
        clearErrorsEquipField();
        int i = 0;
        if (TextUtils.isEmpty(equipLocaleValue.getText())) {
            i++;
            equipLocaleField.setError(getString(R.string.blank_field_error));
        }
        if (getRadioCheck(soundSignalRadio) == -1) {
            i++;
            soundSignalError.setVisibility(View.VISIBLE);
        } else if (getRadioCheck(soundSignalRadio) == 1) {
            if (TextUtils.isEmpty(soundSignalHeightValue.getText())) {
                i++;
                soundSignalHeightField.setError(getString(R.string.blank_field_error));
            }
        }
        if (getRadioCheck(phoneRadio) == -1) {
            i++;
            phoneError.setVisibility(View.VISIBLE);
        } else if (getRadioCheck(phoneRadio) == 1) {
            if (TextUtils.isEmpty(phoneHeightValue.getText())) {
                i++;
                phoneHeightField.setError(getString(R.string.blank_field_error));
            }
        }
        if (getRadioCheck(biometryRadio) == -1) {
            i++;
            biometryError.setVisibility(View.VISIBLE);
        } else if (getRadioCheck(biometryRadio) == 1) {
            if (TextUtils.isEmpty(biometryHeightValue.getText())) {
                i++;
                biometryHeightField.setError(getString(R.string.blank_field_error));
            }
        }

        return i == 0;
    }

    private void clearErrorsEquipField() {
        equipLocaleField.setErrorEnabled(false);
        soundSignalError.setVisibility(View.GONE);
        soundSignalHeightField.setErrorEnabled(false);
        phoneError.setVisibility(View.GONE);
        phoneHeightField.setErrorEnabled(false);
        biometryError.setVisibility(View.GONE);
        biometryHeightField.setErrorEnabled(false);
    }

    private void clearEquipFields() {
        equipLocaleValue.setText(null);
        soundSignalRadio.clearCheck();
        soundSignalHeightField.setVisibility(View.GONE);
        soundSignalObsField.setVisibility(View.GONE);
        phoneRadio.clearCheck();
        phoneHeightField.setVisibility(View.GONE);
        phoneObsField.setVisibility(View.GONE);
        biometryRadio.clearCheck();
        biometryHeightField.setVisibility(View.GONE);
        biometryObsField.setVisibility(View.GONE);
    }
}
