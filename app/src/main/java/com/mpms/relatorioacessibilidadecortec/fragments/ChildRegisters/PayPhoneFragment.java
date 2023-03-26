package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

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

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.ArrayList;

public class PayPhoneFragment extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

    TextInputLayout payphoneRefField, keyboardHeightField, phoneHeightField, payphoneObsField, tactFloorDistField, phoneTactileFloorWidthField, tactileFloorObsField,
            photoField;
    TextInputEditText payphoneRefValue, keyboardHeightValue, phoneHeightValue, payphoneObsValue, tactFloorDistValue, phoneTactileFloorWidthValue, tactileFloorObsValue,
            photoValue;
    TextView payphoneFloorError, payphoneTactileColorError, phoneTactColorHeader, phoneTactDimensionsHeader, phoneTactDimensionError, payphoneWorkingError;
    RadioGroup payphoneTactileFloorRadio, payphoneTactileColorRadio, payphoneWorkingRadio;
    Button savePayphone, cancelPayphone;

    ArrayList<TextInputEditText> eTexts = new ArrayList<>();

    Bundle phoneBundle;

    ViewModelEntry modelEntry;

    public PayPhoneFragment() {
        // Required empty public constructor
    }

    public static PayPhoneFragment newInstance() {
        return new PayPhoneFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            phoneBundle = new Bundle(this.getArguments());
        else
            phoneBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pay_phone, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        instantiatePayPhoneViews(view);


        if (phoneBundle.getInt(PHONE_ID) > 0)
            modelEntry.getPayPhoneEntry(phoneBundle.getInt(PHONE_ID)).observe(getViewLifecycleOwner(), this::loadPayPhoneData);

        savePayphone.setOnClickListener(v -> {
            if (checkEmptyFields()) {
                PayPhoneEntry newPhone = newPayPhone(phoneBundle);
                if (phoneBundle.getInt(PHONE_ID) == 0) {
                    ViewModelEntry.insertPayPhone(newPhone);
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                    clearPhoneFields();
                } else if (phoneBundle.getInt(PHONE_ID) > 0) {
                    newPhone.setPayPhoneID(phoneBundle.getInt(PHONE_ID));
                    ViewModelEntry.updatePayPhone(newPhone);
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStackImmediate();
                } else
                    Toast.makeText(getContext(), R.string.unexpected_error, Toast.LENGTH_SHORT).show();

                phoneBundle.putInt(PHONE_ID, 0);
            } else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });

        cancelPayphone.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

    }

    private void instantiatePayPhoneViews(View view) {
//        TextInputLayout
        payphoneRefField = view.findViewById(R.id.gate_payphone_ref_field);
        keyboardHeightField = view.findViewById(R.id.payphone_keyboard_height_field);
        phoneHeightField = view.findViewById(R.id.payphone_phone_height_field);
        payphoneObsField = view.findViewById(R.id.gate_payphone_obs_field);
        tactFloorDistField = view.findViewById(R.id.phone_tact_floor_dist_field);
        phoneTactileFloorWidthField = view.findViewById(R.id.phone_tact_floor_width_field);
        tactileFloorObsField = view.findViewById(R.id.phone_tact_floor_obs_field);
        photoField = view.findViewById(R.id.phone_photo_field);
//        TextInputEditText
        payphoneRefValue = view.findViewById(R.id.gate_payphone_ref_value);
        keyboardHeightValue = view.findViewById(R.id.payphone_keyboard_height_value);
        phoneHeightValue = view.findViewById(R.id.payphone_phone_height_value);
        payphoneObsValue = view.findViewById(R.id.gate_payphone_obs_value);
        tactFloorDistValue = view.findViewById(R.id.phone_tact_floor_dist_value);
        phoneTactileFloorWidthValue = view.findViewById(R.id.phone_tact_floor_width_value);
        tactileFloorObsValue = view.findViewById(R.id.phone_tact_floor_obs_value);
        photoValue = view.findViewById(R.id.phone_photo_value);
//        TextView
        payphoneFloorError = view.findViewById(R.id.gate_payphone_floor_error);
        payphoneWorkingError = view.findViewById(R.id.payphone_working_error);
        payphoneTactileColorError = view.findViewById(R.id.payphone_tactile_color_error);
        phoneTactColorHeader = view.findViewById(R.id.payphone_tactile_color_label);
        phoneTactDimensionsHeader = view.findViewById(R.id.label_payphone_tactile_floor_dimensions);
        phoneTactDimensionError = view.findViewById(R.id.payphone_tactile_dimension_error);
//        RadioGroup
        payphoneTactileFloorRadio = view.findViewById(R.id.payphone_tactile_floor_radio);
        payphoneWorkingRadio = view.findViewById(R.id.payphone_working_radio);
        payphoneTactileColorRadio = view.findViewById(R.id.payphone_tactile_color_radio);
//        MaterialButton
        savePayphone = view.findViewById(R.id.save_gate_payphone);
        cancelPayphone = view.findViewById(R.id.cancel_gate_payphone);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//
        eTexts.add(payphoneObsValue);
        eTexts.add(tactileFloorObsValue);
        allowObsScroll(eTexts);
        payphoneTactileFloorRadio.setOnCheckedChangeListener(this::radioListener);
    }

    private PayPhoneEntry newPayPhone(Bundle bundle) {
        Integer extID = null, sideID = null, rightColor = null;
        int hasTactFloor, isWorking;
        String payphoneRefLocation, tactFloorObs = null, payphoneObs = null, photo = null;
        double keyboardHeight, phoneHeight;
        Double tactFloorDist = null, tactFloorWidth = null;

        if (bundle.getBoolean(FROM_EXT_ACCESS))
            extID = bundle.getInt(AMBIENT_ID);
        else if (bundle.getBoolean(FROM_SIDEWALK))
            sideID = bundle.getInt(AMBIENT_ID);
        payphoneRefLocation = String.valueOf(payphoneRefValue.getText());
        keyboardHeight = Double.parseDouble(String.valueOf(keyboardHeightValue.getText()));
        phoneHeight = Double.parseDouble(String.valueOf(keyboardHeightValue.getText()));
        hasTactFloor = indexRadio(payphoneTactileFloorRadio);
        if (hasTactFloor == 1) {
            rightColor = indexRadio(payphoneTactileColorRadio);
            tactFloorDist = Double.parseDouble(String.valueOf(tactFloorDistValue.getText()));
            tactFloorWidth = Double.parseDouble(String.valueOf(phoneTactileFloorWidthValue.getText()));
            if (!TextUtils.isEmpty(tactileFloorObsValue.getText()))
                tactFloorObs = String.valueOf(tactileFloorObsValue.getText());
        }
        isWorking = indexRadio(payphoneWorkingRadio);
        if (!TextUtils.isEmpty(payphoneObsValue.getText()))
            payphoneObs = String.valueOf(payphoneObsValue.getText());
        if (!TextUtils.isEmpty(photoValue.getText()))
            photo = String.valueOf(photoValue.getText());

        return new PayPhoneEntry(extID, sideID, payphoneRefLocation, keyboardHeight, phoneHeight, hasTactFloor, rightColor, tactFloorDist, tactFloorWidth, tactFloorObs,
                isWorking, payphoneObs, photo);
    }

    private boolean checkEmptyFields() {
        clearErrorsPayphone();
        int error = 0;
        if (TextUtils.isEmpty(payphoneRefValue.getText())) {
            error++;
            payphoneRefField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(keyboardHeightValue.getText())) {
            error++;
            keyboardHeightField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(phoneHeightValue.getText())) {
            error++;
            phoneHeightField.setError(getString(R.string.req_field_error));
        }
        if (indexRadio(payphoneTactileFloorRadio) == -1) {
            error++;
            payphoneFloorError.setVisibility(View.VISIBLE);
        } else if (indexRadio(payphoneTactileFloorRadio) == 1) {
            if (indexRadio(payphoneTactileColorRadio) == -1) {
                error++;
                payphoneTactileColorError.setVisibility(View.VISIBLE);
            }
            if (TextUtils.isEmpty(tactFloorDistValue.getText())) {
                error++;
//                phoneTactileFloorLengthField.setBoxStrokeColor(getResources().getColor(R.color.error_message, null));
                phoneTactDimensionError.setVisibility(View.VISIBLE);
            }
            if (TextUtils.isEmpty(phoneTactileFloorWidthValue.getText())) {
                error++;
//                phoneTactileFloorLengthField.setBoxStrokeColor(getResources().getColor(R.color.error_message, null));
                phoneTactDimensionError.setVisibility(View.VISIBLE);
            }
        }
        if (indexRadio(payphoneWorkingRadio) == -1) {
            error++;
            payphoneWorkingError.setVisibility(View.VISIBLE);
        }

        return error == 0;
    }

    private void clearErrorsPayphone() {
        payphoneRefField.setErrorEnabled(false);
        keyboardHeightField.setErrorEnabled(false);
        phoneHeightField.setErrorEnabled(false);
        payphoneFloorError.setVisibility(View.GONE);
        payphoneTactileColorError.setVisibility(View.GONE);
        phoneTactDimensionError.setVisibility(View.GONE);
        payphoneWorkingError.setVisibility(View.GONE);
    }

    private void clearPhoneFields() {
        payphoneRefValue.setText(null);
        keyboardHeightValue.setText(null);
        phoneHeightValue.setText(null);
        payphoneTactileFloorRadio.clearCheck();
        payphoneTactileColorRadio.clearCheck();
        phoneTactColorHeader.setVisibility(View.GONE);
        payphoneTactileColorRadio.setVisibility(View.GONE);
        tactFloorDistValue.setText(null);
        phoneTactileFloorWidthValue.setText(null);
        tactileFloorObsValue.setText(null);
        phoneTactDimensionsHeader.setVisibility(View.GONE);
        tactFloorDistField.setVisibility(View.GONE);
        phoneTactileFloorWidthField.setVisibility(View.GONE);
        tactileFloorObsField.setVisibility(View.GONE);
        payphoneObsValue.setText(null);
        payphoneWorkingRadio.clearCheck();
        photoValue.setText(null);
    }

    private void loadPayPhoneData(PayPhoneEntry payPhone) {
        payphoneRefValue.setText(payPhone.getPhoneRefPoint());
        keyboardHeightValue.setText(String.valueOf(payPhone.getPhoneKeyboardHeight()));
        phoneHeightValue.setText(String.valueOf(payPhone.getPhoneHeight()));
        checkRadioGroup(payphoneTactileFloorRadio, payPhone.getHasTactileFloor());
        if (payPhone.getHasTactileFloor() == 1) {
            checkRadioGroup(payphoneTactileColorRadio, payPhone.getRightColorTactileFloor());
            tactFloorDistValue.setText(String.valueOf(payPhone.getTactFloorDist()));
            phoneTactileFloorWidthValue.setText(String.valueOf(payPhone.getTactFloorWidth()));
            if (payPhone.getTactFloorObs() != null)
                tactileFloorObsValue.setText(payPhone.getTactFloorObs());
        }
        checkRadioGroup(payphoneWorkingRadio, payPhone.getPhoneIsWorking());
        if (payPhone.getPayPhoneObs() != null)
            payphoneObsValue.setText(payPhone.getPayPhoneObs());
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
        int index = indexRadio(radio);

        if (index == 1) {
            phoneTactColorHeader.setVisibility(View.VISIBLE);
            payphoneTactileColorRadio.setVisibility(View.VISIBLE);
            phoneTactDimensionsHeader.setVisibility(View.VISIBLE);
            tactFloorDistField.setVisibility(View.VISIBLE);
            phoneTactileFloorWidthField.setVisibility(View.VISIBLE);
        } else {
            phoneTactColorHeader.setVisibility(View.GONE);
            payphoneTactileColorRadio.clearCheck();
            payphoneTactileColorRadio.setVisibility(View.GONE);
            phoneTactDimensionsHeader.setVisibility(View.GONE);
            tactFloorDistValue.setText(null);
            tactFloorDistField.setVisibility(View.GONE);
            phoneTactileFloorWidthValue.setText(null);
            phoneTactileFloorWidthField.setVisibility(View.GONE);
        }
    }
}