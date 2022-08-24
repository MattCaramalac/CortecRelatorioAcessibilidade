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
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.data.entities.PlaygroundEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkSlopeEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.DoorFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;


public class SillInclinationFragment extends Fragment implements TagInterface {

    TextInputLayout sillInclinationField;
    TextInputEditText sillInclinationValue;

    ViewModelEntry modelEntry;

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

        instantiateInclinationSillView(view);

        getParentFragmentManager().setFragmentResultListener(LOAD_CHILD_DATA, this, (key, bundle) -> {
            if (bundle.getInt(DoorFragment.DOOR_ID) > 0) {
                modelEntry.getSpecificDoor(bundle.getInt(DoorFragment.DOOR_ID)).observe(getViewLifecycleOwner(), this::loadInclinationDoorData);
            } else if (bundle.getBoolean(FROM_EXT_ACCESS)) {
                modelEntry.getOneExternalAccess(bundle.getInt(AMBIENT_ID))
                        .observe(getViewLifecycleOwner(), this::loadInclinationExtAccData);
            } else if (bundle.getInt(PLAY_ID) > 0) {
                modelEntry.getOnePlayground(bundle.getInt(PLAY_ID))
                        .observe(getViewLifecycleOwner(), this::loadInclinationPlayData);
            } else if (bundle.getInt(SIDEWALK_SLOPE_ID) > 0) {
                modelEntry.getSidewalkSlopeEntry(bundle.getInt(SIDEWALK_SLOPE_ID))
                        .observe(getViewLifecycleOwner(), this::loadInclinationSlopeStreetData);
            } else if (bundle.getInt(REST_ID) > 0) {
                modelEntry.getOneRestroomEntry(bundle.getInt(REST_ID))
                        .observe(getViewLifecycleOwner(), this::loadRestIncSlope);
            }
        });

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA, this, (key, bundle) -> {
            checkInclinationNoEmptyFields(bundle);
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
        });

    }

    private void instantiateInclinationSillView(View view) {
//        TextInputLayout
        sillInclinationField = view.findViewById(R.id.sill_inclination_height_field);
//        TextInputEditText
        sillInclinationValue = view.findViewById(R.id.sill_inclination_height_value);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private boolean checkInclinationNoEmptyFields(Bundle bundle) {
        clearErrorInclinationSill();
        int i = 0;
        if (TextUtils.isEmpty(sillInclinationValue.getText())) {
            sillInclinationField.setError(getString(R.string.blank_field_error));
            i++;
        } else {
            bundle.putDouble(HEIGHT_INCLINED_SILL, Double.parseDouble(String.valueOf(sillInclinationValue.getText())));
        }
        if (!bundle.getBoolean(ADD_ITEM_REQUEST)) {
            bundle.putBoolean(CHILD_DATA_COMPLETE, i == 0);
        }
        return i == 0;
    }

    private void clearErrorInclinationSill() {
        sillInclinationField.setErrorEnabled(false);
    }

    private void loadInclinationExtAccData(ExternalAccess access) {
        if (access.getSillInclinationHeight() != null)
            sillInclinationValue.setText(String.valueOf(access.getSillInclinationHeight()));
    }

    private void loadInclinationPlayData(PlaygroundEntry playEntry) {
        if (playEntry.getInclinationSillHeight() != null)
            sillInclinationValue.setText(String.valueOf(playEntry.getInclinationSillHeight()));
    }

    private void loadInclinationDoorData(DoorEntry doorEntry) {
        if (doorEntry.getSillInclinationHeight() != null)
            sillInclinationValue.setText(String.valueOf(doorEntry.getSillInclinationHeight()));
    }

    private void loadInclinationSlopeStreetData(SidewalkSlopeEntry slopeEntry) {
        sillInclinationValue.setText(String.valueOf(slopeEntry.getInclinationJunctionHeight()));
    }

    private void loadRestIncSlope(RestroomEntry entry) {
        if (entry.getSillIncHeight() != null)
        sillInclinationValue.setText(String.valueOf(entry.getSillIncHeight()));
    }
}