package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.ExtAccessSocialFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelDialog;

import java.util.ArrayList;
import java.util.Objects;


public class SillStepFragment extends Fragment {

    public static final String STEP_HEIGHT = "STEP_HEIGHT";

    TextInputLayout stepHeightField;
    TextInputEditText stepHeightValue;

    ViewModelDialog modelDialog;

    ArrayList<String> childData = new ArrayList<>();

    Bundle sillStepBundle = new Bundle();


    public SillStepFragment() {
        // Required empty public constructor
    }

    public static SillStepFragment newInstance() {
        return new SillStepFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sill_step, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        stepHeightField = view.findViewById(R.id.sill_step_height_field);
        stepHeightValue = view.findViewById(R.id.sill_step_height_value);

        //        TODO - Retirar esse model dialog quando remover o DoorDialog
        modelDialog = new ViewModelProvider(requireActivity()).get(ViewModelDialog.class);

        modelDialog.getRestDoorBundle().observe(getViewLifecycleOwner(), this::gatherStepData);

        modelDialog.getSaveDoorAttempt().observe(getViewLifecycleOwner(), saveAttempt -> {
            if(Objects.equals(modelDialog.getSaveDoorAttempt().getValue(), 1)) {
                if (checkEmptySillStepField()) {
                    Bundle bundle = new Bundle();
                    bundle.putDouble(STEP_HEIGHT, Double.parseDouble(Objects.requireNonNull(stepHeightValue.getText()).toString()));
                    modelDialog.setDoorInfo(bundle);
                    clearStepField();
                }
                modelDialog.setSaveDoorAttempt(0);
            }
        });

        getParentFragmentManager().setFragmentResultListener(ExternalAccessFragment.EXT_ACCESS_SAVE_ATTEMPT, this, (key, bundle) -> {
            childData = bundle.getStringArrayList(ExternalAccessFragment.EXT_ARRAY);
            if (checkEmptySillStepField()) {
                sillStepBundle.putStringArrayList(ExternalAccessFragment.EXT_ARRAY, childData);
                getParentFragmentManager().setFragmentResult(ExtAccessSocialFragment.FRAG_DATA, sillStepBundle);
            } else
                getParentFragmentManager().setFragmentResult(ExtAccessSocialFragment.FRAG_DATA, bundle);
        });
    }

    private boolean checkEmptySillStepField() {
        clearEmptyErrorStepField();
        int error = 0;
        if (TextUtils.isEmpty(stepHeightValue.getText())) {
            stepHeightField.setError(getString(R.string.blank_field_error));
            error++;
        } else
            childData.set(9, String.valueOf(stepHeightValue.getText()));

        return error == 0;
    }

    private void clearEmptyErrorStepField() {
        stepHeightField.setErrorEnabled(false);
    }

    private void clearStepField() {
        stepHeightValue.setText(null);
    }

    private void gatherStepData(Bundle bundle) {
        stepHeightValue.setText(String.valueOf(bundle.getDouble(STEP_HEIGHT)));
    }
}