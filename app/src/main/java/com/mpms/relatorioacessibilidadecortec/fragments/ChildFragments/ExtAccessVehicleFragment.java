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
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.data.parcels.VehicleExtAccParcel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.parceler.Parcels;

public class ExtAccessVehicleFragment extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

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
            extAccessID = this.getArguments().getInt(AMBIENT_ID);
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

        getParentFragmentManager().setFragmentResultListener(PARENT_SAVE_ATTEMPT, this, (key, bundle) -> {
            if (checkVehicleEmptyFields()) {
               createVehicleParcel(bundle);
               bundle.putBoolean(CHILD_DATA_COMPLETE, true);
            } else
                bundle.putBoolean(CHILD_DATA_COMPLETE, false);
            getParentFragmentManager().setFragmentResult(GATHER_CHILD_DATA, bundle);
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
        allowObsScroll(accessObsValue);
    }

    private void createVehicleParcel(Bundle bundle) {
        int hasSound;
        String soundObs = null, photos = null;

        hasSound = indexRadio(hasSoundSignRadio);
        if (!TextUtils.isEmpty(accessObsValue.getText()))
            soundObs = String.valueOf(accessObsValue.getText());

        VehicleExtAccParcel parcel = new VehicleExtAccParcel(hasSound, soundObs);
        bundle.putParcelable(CHILD_PARCEL, Parcels.wrap(parcel));
    }

    private boolean checkVehicleEmptyFields() {
        int i = 0;
        soundSingError.setVisibility(View.GONE);
        if (indexRadio(hasSoundSignRadio) == -1) {
            i++;
            soundSingError.setVisibility(View.VISIBLE);
        }
        return i == 0;
    }

    private void loadVehicleData(ExternalAccess access) {
        if (access.getGateHasSoundSign() != null)
            checkRadioGroup(hasSoundSignRadio, access.getGateHasSoundSign());
        if (access.getExtAccessObs() != null)
            accessObsValue.setText(access.getExtAccessObs());
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {

    }
}