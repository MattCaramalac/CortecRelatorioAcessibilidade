package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.annotation.SuppressLint;
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
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class PayPhoneFragment extends Fragment {

    public static final String PHONE_ID = "PHONE_ID";

    TextInputLayout payphoneRefField, keyboardHeightField, phoneHeightField, payphoneObsField;
    TextInputEditText payphoneRefValue, keyboardHeightValue, phoneHeightValue, payphoneObsValue;
    TextView payphoneFloorError, payphoneWorkingError;
    RadioGroup payphoneTactileFloorRadio, payphoneWorkingRadio;
    Button savePayphone, cancelPayphone;

    Bundle phoneBundle = new Bundle();

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
        if (this.getArguments() != null) {
            phoneBundle.putInt(ExternalAccessFragment.EXT_ACCESS_ID, this.getArguments().getInt(ExternalAccessFragment.EXT_ACCESS_ID));
            phoneBundle.putInt(PHONE_ID, this.getArguments().getInt(PHONE_ID));
        }
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
        allowPayPhoneObsScroll();

        if (phoneBundle.getInt(PHONE_ID) > 0)
            modelEntry.getPayPhoneEntry(phoneBundle.getInt(PHONE_ID)).observe(getViewLifecycleOwner(), this::loadPayPhoneData);

        savePayphone.setOnClickListener(v -> {
            if (checkEmptyFields()) {
                PayPhoneEntry newPhone = newPayPhone(phoneBundle);
                if (phoneBundle.getInt(PHONE_ID) == 0) {
                    ViewModelEntry.insertPayPhone(newPhone);
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                }
                else if (phoneBundle.getInt(PHONE_ID) > 0) {
                    newPhone.setPayPhoneID(phoneBundle.getInt(PHONE_ID));
                    ViewModelEntry.updatePayPhone(newPhone);
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                }
                clearPhoneFields();
                phoneBundle.putInt(PHONE_ID, 0);
            }
        });

        cancelPayphone.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

    }

    private void instantiatePayPhoneViews(View view) {
//        TextInputLayout
        payphoneRefField = view.findViewById(R.id.gate_payphone_ref_field);
        keyboardHeightField = view.findViewById(R.id.payphone_keyboard_height_field);
        phoneHeightField = view.findViewById(R.id.payphone_phone_height_field);
        payphoneObsField = view.findViewById(R.id.gate_payphone_obs_field);
//        TextInputEditText
        payphoneRefValue = view.findViewById(R.id.gate_payphone_ref_value);
        keyboardHeightValue = view.findViewById(R.id.payphone_keyboard_height_value);
        phoneHeightValue = view.findViewById(R.id.payphone_phone_height_value);
        payphoneObsValue = view.findViewById(R.id.gate_payphone_obs_value);
//        TextView
        payphoneFloorError = view.findViewById(R.id.gate_payphone_floor_error);
        payphoneWorkingError = view.findViewById(R.id.payphone_working_error);
//        RadioGroup
        payphoneTactileFloorRadio = view.findViewById(R.id.payphone_tactile_floor_radio);
        payphoneWorkingRadio = view.findViewById(R.id.payphone_working_radio);
//        MaterialButton
        savePayphone = view.findViewById(R.id.save_gate_payphone);
        cancelPayphone = view.findViewById(R.id.cancel_gate_payphone);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowPayPhoneObsScroll() {
        payphoneObsValue.setOnTouchListener(this::scrollingField);
    }

    private int getCheckedRadioFloor(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private PayPhoneEntry newPayPhone(Bundle bundle) {
        String payphoneRefLocation = null, payphoneObs = null;
        double keyboardHeight = 0, phoneHeight = 0;

        if (!TextUtils.isEmpty(payphoneRefValue.getText()))
            payphoneRefLocation = Objects.requireNonNull(payphoneRefValue.getText()).toString();
        if (!TextUtils.isEmpty(keyboardHeightValue.getText()))
            keyboardHeight = Double.parseDouble(Objects.requireNonNull(keyboardHeightValue.getText()).toString());
        if (!TextUtils.isEmpty(phoneHeightValue.getText()))
            phoneHeight = Double.parseDouble(Objects.requireNonNull(keyboardHeightValue.getText()).toString());
        if (!TextUtils.isEmpty(payphoneObsValue.getText()))
            payphoneObs = Objects.requireNonNull(payphoneObsValue.getText()).toString();

        return new PayPhoneEntry(bundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID), payphoneRefLocation, keyboardHeight, phoneHeight,
                getCheckedRadioFloor(payphoneTactileFloorRadio), getCheckedRadioFloor(payphoneWorkingRadio), payphoneObs);
    }

    private boolean checkEmptyFields() {
        clearErrorsPayphone();
        int error = 0;
        if (TextUtils.isEmpty(payphoneRefValue.getText())) {
            error++;
            payphoneRefField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(keyboardHeightValue.getText())) {
            error++;
            keyboardHeightField.setError(getString(R.string.blank_field_error));
        }
        if (getCheckedRadioFloor(payphoneTactileFloorRadio) == -1) {
            error++;
            payphoneFloorError.setVisibility(View.VISIBLE);
        }
        return error == 0;
    }

    private void clearErrorsPayphone() {
        payphoneRefField.setErrorEnabled(false);
        keyboardHeightField.setErrorEnabled(false);
        payphoneFloorError.setVisibility(View.GONE);
    }

    private void clearPhoneFields() {
        payphoneRefValue.setText(null);
        keyboardHeightValue.setText(null);
        payphoneTactileFloorRadio.clearCheck();
        payphoneObsValue.setText(null);
    }

    private void loadPayPhoneData(PayPhoneEntry payPhone) {
        payphoneRefValue.setText(payPhone.getPhoneRefPoint());
        keyboardHeightValue.setText(String.valueOf(payPhone.getPhoneKeyboardHeight()));
        phoneHeightValue.setText(String.valueOf(payPhone.getPhoneHeight()));
        payphoneTactileFloorRadio.check(payphoneTactileFloorRadio.getChildAt(payPhone.getHasTactileFloor()).getId());
        payphoneWorkingRadio.check(payphoneWorkingRadio.getChildAt(payPhone.getPhoneIsWorking()).getId());
        payphoneObsValue.setText(payPhone.getPayPhoneObs());
    }
}