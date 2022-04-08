package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

public class ExtAccessVehicleFragment extends Fragment {

    public static final String HAS_SOUND = "HAS_SOUND";
    public static final String ACCESS_OBS = "ACCESS_OBS";

    RadioGroup hasSoundSignRadio;
    TextView soundSingError;
    TextInputLayout accessObsField;
    TextInputEditText accessObsValue;

    ViewModelEntry modelEntry;

    int extAccessID = 0;

    public ExtAccessVehicleFragment() {
        // Required empty public constructor
    }

    public static ExtAccessVehicleFragment newInstance() {
        return new ExtAccessVehicleFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            extAccessID = this.getArguments().getInt(ExternalAccessFragment.EXT_ACCESS_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ext_access_vehicle, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        instantiateVehicleViews(view);

        getParentFragmentManager().setFragmentResultListener(ExternalAccessFragment.EXT_ACCESS_SAVE_ATTEMPT, this, (key,bundle) -> {
            if (checkVehicleEmptyFields()) {
                bundle.putInt(HAS_SOUND, getRadioCheckIndex(hasSoundSignRadio));
                if (!TextUtils.isEmpty(accessObsValue.getText()))
                    bundle.putString(ACCESS_OBS, String.valueOf(accessObsValue.getText()));
                bundle.putBoolean(InspectionActivity.CHILD_DATA_COMPLETE, true);
            } else
                bundle.putBoolean(InspectionActivity.CHILD_DATA_COMPLETE, false);
            getParentFragmentManager().setFragmentResult(InspectionActivity.GATHER_CHILD_DATA, bundle);
        });

        if (extAccessID > 0)
            modelEntry.getOneExternalAccess(extAccessID).observe(getViewLifecycleOwner(), this::loadVehicleData);
    }

    private void instantiateVehicleViews(View view) {
//        RadioGroup
        hasSoundSignRadio = view.findViewById(R.id.has_sound_sign_radio);
//        TextView
        soundSingError = view.findViewById(R.id.has_sound_sign_error);
//        TextInputEditText
        accessObsField = view.findViewById(R.id.external_access_sound_obs_field);
//        TextInputEditText
        accessObsValue = view.findViewById(R.id.external_access_sound_obs_value);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private int getRadioCheckIndex(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private boolean checkVehicleEmptyFields() {
        int i = 0;
        soundSingError.setVisibility(View.GONE);
        if (getRadioCheckIndex(hasSoundSignRadio) == -1) {
            i++;
            soundSingError.setVisibility(View.VISIBLE);
        }
        return i == 0;
    }

    private void loadVehicleData(ExternalAccess access) {
        hasSoundSignRadio.check(hasSoundSignRadio.getChildAt(access.getGateHasSoundSign()).getId());
        if (access.getExtAccessObs() != null)
            accessObsValue.setText(access.getExtAccessObs());
    }
}