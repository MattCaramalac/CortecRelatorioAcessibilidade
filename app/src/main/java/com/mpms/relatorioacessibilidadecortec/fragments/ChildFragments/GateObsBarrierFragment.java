package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.GateObsEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.GateObsFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class GateObsBarrierFragment extends Fragment {

    public static final String OBS_BARRIER_HEIGHT = "OBS_BARRIER_HEIGHT";
    public static final String OBS_BARRIER_WIDTH = "OBS_BARRIER_WIDTH";

    TextInputLayout barrierHeightField, barrierWidthField;
    TextInputEditText barrierHeightValue, barrierWidthValue;

    int gateObsID = 0;

    ViewModelEntry modelEntry;

    public GateObsBarrierFragment() {
        // Required empty public constructor
    }

    public static GateObsBarrierFragment newInstance() {
        return new GateObsBarrierFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            gateObsID = this.getArguments().getInt(GateObsFragment.GATE_OBS_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gate_obs_barrier_type, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        instantiateBarrierViews(view);

        if (gateObsID > 0)
            modelEntry.getOneGateObsEntry(gateObsID).observe(getViewLifecycleOwner(), this::loadObsBarrierData);

        getParentFragmentManager().setFragmentResultListener(GateObsFragment.GATE_OBS_SAVE_ATTEMPT, this, (key, bundle) -> {
            checkEmptyBarrierObsField(bundle);
            getParentFragmentManager().setFragmentResult(GateObsFragment.GATE_OBS_CHILD_FRAG_DATA, bundle);

        });
    }

    private void instantiateBarrierViews(View view) {
//        TextInputLayout
        barrierHeightField = view.findViewById(R.id.gate_obs_barrier_height_field);
        barrierWidthField = view.findViewById(R.id.gate_obs_barrier_width_field);
//        TextInputEditText
        barrierHeightValue = view.findViewById(R.id.gate_obs_barrier_height_value);
        barrierWidthValue = view.findViewById(R.id.gate_obs_barrier_width_value);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private void checkEmptyBarrierObsField(Bundle bundle) {
        clearEmptyBarrierFieldsErrors();
        int error = 0;
        if (TextUtils.isEmpty(barrierHeightValue.getText())) {
            barrierHeightField.setError(getString(R.string.blank_field_error));
            error++;
        } else
            bundle.putDouble(OBS_BARRIER_HEIGHT, Double.parseDouble(Objects.requireNonNull(barrierHeightValue.getText()).toString()));
        if (TextUtils.isEmpty(barrierWidthValue.getText())) {
            barrierWidthField.setError(getString(R.string.blank_field_error));
            error++;
        } else
            bundle.putDouble(OBS_BARRIER_WIDTH, Double.parseDouble(Objects.requireNonNull(barrierWidthValue.getText()).toString()));
        if (error > 0)
            bundle.putInt(GateObsFragment.GATE_OBS_EMPTY_CHECK, 1);
    }

    private void clearEmptyBarrierFieldsErrors() {
        barrierWidthField.setErrorEnabled(false);
        barrierHeightField.setErrorEnabled(false);
    }

    private void loadObsBarrierData(GateObsEntry obsEntry) {
        barrierHeightValue.setText(String.valueOf(obsEntry.getBarrierHeight()));
        barrierWidthValue.setText(String.valueOf(obsEntry.getBarrierWidth()));
    }
}