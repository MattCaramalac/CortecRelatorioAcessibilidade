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
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;


public class AddPayPhoneDialog extends DialogFragment {

    private Toolbar toolbar;

    TextInputLayout payphoneRefField, payphoneHeightField, payphoneObsField;
    TextInputEditText payphoneRefValue, payphoneHeightValue, payphoneObsValue;
    TextView payphoneFloorError;
    RadioGroup payphoneTactileFloor;
    Button savePayphone, cancelPayphone;

    String payphoneRefLocation, payphoneObs;
    Double payphoneHeight;

    ViewModelEntry modelEntry;

    static Bundle extBundle;

    public static AddPayPhoneDialog displayPayPhoneDialog(FragmentManager fragmentManager, Bundle bundle) {
        AddPayPhoneDialog addPayPhoneDialog = new AddPayPhoneDialog();
        addPayPhoneDialog.show(fragmentManager, "PAYPHONE_DIALOG");
        extBundle = bundle;
        return addPayPhoneDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_pay_phone_dialog, container, false);
        toolbar = view.findViewById(R.id.gate_payphone_toolbar);
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle(getString(R.string.dialog_add_payphone_header));

        instantiatePayPhoneViews(view);
        allowPayPhoneObsScroll();

        savePayphone.setOnClickListener(v -> {
            if(checkEmptyFields()) {
                PayPhoneEntry newPhone = newPayPhone(extBundle);
                ViewModelEntry.insertPayPhone(newPhone);
                Toast.makeText(getContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                clearPhoneFields();
            }
        });

        cancelPayphone.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .beginTransaction().remove(this).commit());

    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int length = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width,length);
        }
    }

    private void instantiatePayPhoneViews(View view) {
        payphoneRefField = view.findViewById(R.id.gate_payphone_ref_field);
        payphoneHeightField = view.findViewById(R.id.gate_payphone_op_height_field);
        payphoneObsField = view.findViewById(R.id.gate_payphone_obs_field);

        payphoneRefValue = view.findViewById(R.id.gate_payphone_ref_value);
        payphoneHeightValue = view.findViewById(R.id.gate_payphone_op_height_value);
        payphoneObsValue = view.findViewById(R.id.gate_payphone_obs_value);

        payphoneFloorError = view.findViewById(R.id.gate_payphone_floor_error);
        payphoneTactileFloor = view.findViewById(R.id.payphone_tactile_floor_radio);

        savePayphone = view.findViewById(R.id.save_gate_payphone);
        cancelPayphone = view.findViewById(R.id.cancel_gate_payphone);
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

    public int getCheckedRadioFloor(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public PayPhoneEntry newPayPhone(Bundle bundle) {
        payphoneRefLocation = null;
        payphoneHeight = null;
        payphoneObs = null;

        if (!TextUtils.isEmpty(payphoneRefValue.getText()))
            payphoneRefLocation = Objects.requireNonNull(payphoneRefValue.getText()).toString();
        if (!TextUtils.isEmpty(payphoneHeightValue.getText()))
            payphoneHeight = Double.parseDouble(Objects.requireNonNull(payphoneHeightValue.getText()).toString());
        if (!TextUtils.isEmpty(payphoneObsValue.getText()))
            payphoneObs = Objects.requireNonNull(payphoneObsValue.getText()).toString();

        return new PayPhoneEntry(bundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID), payphoneRefLocation,
                payphoneHeight, getCheckedRadioFloor(payphoneTactileFloor),payphoneObs);
    }

    public boolean checkEmptyFields() {
        clearErrorsPayphone();
        int error = 0;
        if(TextUtils.isEmpty(payphoneRefValue.getText())){
            error++;
            payphoneRefField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(payphoneHeightValue.getText())) {
            error++;
            payphoneHeightField.setError(getString(R.string.blank_field_error));
        }
        if (getCheckedRadioFloor(payphoneTactileFloor) == -1) {
            error++;
            payphoneFloorError.setVisibility(View.VISIBLE);
        }
        return error == 0;
    }

    public void clearErrorsPayphone() {
        payphoneRefField.setErrorEnabled(false);
        payphoneHeightField.setErrorEnabled(false);
        payphoneFloorError.setVisibility(View.GONE);
    }

    public void clearPhoneFields() {
        payphoneRefValue.setText(null);
        payphoneHeightValue.setText(null);
        payphoneTactileFloor.clearCheck();
        payphoneObsValue.setText(null);
    }
}