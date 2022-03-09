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
import com.mpms.relatorioacessibilidadecortec.data.entities.GateObsEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.GateObsFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class GateObsDoorFragment extends Fragment {

    public static final String OBS_GATE_WIDTH = "OBS_GATE_WIDTH";

    TextInputLayout obsGateWidthField;
    TextInputEditText obsGateWidthValue;

    ViewModelEntry modelEntry;

    int gateObsID = 0;

    public GateObsDoorFragment() {
        // Required empty public constructor
    }

    public static GateObsDoorFragment newInstance() {
        return new GateObsDoorFragment();
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
        return inflater.inflate(R.layout.fragment_gate_obs_door_type, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        instantiateDoorViews(view);

        if (gateObsID > 0)
            modelEntry.getOneGateObsEntry(gateObsID).observe(getViewLifecycleOwner(), this::loadObsDoorData);

        getParentFragmentManager().setFragmentResultListener(GateObsFragment.GATE_OBS_SAVE_ATTEMPT, this, (key, bundle) -> {
            checkEmptyGateObsField(bundle);
            getParentFragmentManager().setFragmentResult(GateObsFragment.GATE_OBS_CHILD_FRAG_DATA, bundle);

        });
    }

    private void checkEmptyGateObsField(Bundle bundle) {
        int error = 0;
        if (TextUtils.isEmpty(obsGateWidthValue.getText())) {
            error++;
            obsGateWidthField.setError(getString(R.string.blank_field_error));
        } else
            bundle.putDouble(OBS_GATE_WIDTH, Double.parseDouble(Objects.requireNonNull(obsGateWidthValue.getText()).toString()));
        if (error > 0)
            bundle.putInt(GateObsFragment.GATE_OBS_EMPTY_CHECK, 1);
    }

    private void instantiateDoorViews(View view) {
//        TextInputLayout
        obsGateWidthField = view.findViewById(R.id.gate_obs_door_width_field);
//        TextInputEditText
        obsGateWidthValue = view.findViewById(R.id.gate_obs_door_width_value);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private void loadObsDoorData(GateObsEntry obsEntry){
        obsGateWidthValue.setText(String.valueOf(obsEntry.getGateDoorWidth()));
    }
}