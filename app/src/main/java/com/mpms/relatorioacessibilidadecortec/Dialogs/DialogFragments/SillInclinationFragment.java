package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelDialog;

import java.util.Objects;


public class SillInclinationFragment extends Fragment {

    public static final String HEIGHT_INCLINED_SILL = "HEIGHT_INCLINED_SILL";

    TextInputLayout sillInclinationField;
    TextInputEditText sillInclinationValue;

    ViewModelDialog modelDialog;

    public SillInclinationFragment() {
        // Required empty public constructor
    }


    public static SillInclinationFragment newInstance() {
        return new SillInclinationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sill_inclination, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sillInclinationField = view.findViewById(R.id.sill_inclination_height_field);
        sillInclinationValue = view.findViewById(R.id.sill_inclination_height_value);

        modelDialog = new ViewModelProvider(requireActivity()).get(ViewModelDialog.class);

        modelDialog.getSaveDoorAttempt().observe(getViewLifecycleOwner(), saveAttempt -> {
            if (Objects.equals(modelDialog.getSaveDoorAttempt().getValue(), 1)) {
                if (checkEmptyInclinationField()) {
                    Bundle bundle = new Bundle();
                    bundle.putDouble(HEIGHT_INCLINED_SILL, Double.parseDouble(Objects.requireNonNull(sillInclinationValue.getText()).toString()));
                    modelDialog.setDoorInfo(bundle);
                    clearInclinationFieldSill();
                }
                modelDialog.setSaveDoorAttempt(0);
            }
        });

    }

    public boolean checkEmptyInclinationField() {
        clearErrorInclinationSill();
        int error = 0;
        if (TextUtils.isEmpty(sillInclinationValue.getText())) {
            error++;
            sillInclinationField.setError(getString(R.string.blank_field_error));
        }
        return error == 0;
    }

    public void clearErrorInclinationSill() {
        sillInclinationField.setErrorEnabled(false);
    }

    public void clearInclinationFieldSill() {
        sillInclinationValue.setText(null);
    }
}