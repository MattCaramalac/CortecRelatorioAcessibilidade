package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;

public class CirculationFragment extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

    TextInputLayout circLocField, vertSignObsField, carpetObsField, accessFloorField, intercomHeightField, intercomObsField, biometryHeightField, biometryObsField,
            unevenHeightField, inclField, photoField;
    TextInputEditText circLocValue, vertSignObsValue, carpetObsValue, accessFloorValue, intercomHeightValue, intercomObsValue, biometryHeightValue, biometryObsValue,
            unevenHeightValue, inclValue, photoValue;
    RadioGroup vertSignRadio, carpetRadio, accessFloorRadio, intercomRadio, bioClockRadio, hasUnevenRadio, unevenHeightRadio, taludeRadio, taludeInclRadio,
            hasProtectRadio, protectTypeRadio;
    TextView vertSignError, carpetError, accessError, intercomError, bioClockError, hasUnevenError, unevenHeightHeader, unevenHeightError, hasTaludeHeader, hasTaludeError,
            taludeInclHeader, taludeInclError, hasProtectHeader, hasProtectError, protectTypeHeader, protectTypeError;
    ImageButton protectType1, protectType2, protectType3;
    MaterialButton cancelCirc, continueCirc;
    FrameLayout protectFrame;

    Bundle circBundle;

    ViewModelEntry modelEntry;


    public CirculationFragment() {
        // Required empty public constructor
    }


    public static CirculationFragment newInstance() {
        return new CirculationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            circBundle = new Bundle(this.getArguments());
        else
            circBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_circulation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void instantiateCircViews(View view) {
//        TextInputLayout
        circLocField = view.findViewById(R.id.circ_location_field);
        vertSignObsField = view.findViewById(R.id.circ_visual_sign_obs_field);
        carpetObsField = view.findViewById(R.id.circ_carpet_obs_field);
        accessFloorField = view.findViewById(R.id.circ_access_floor_obs_field);
        intercomHeightField = view.findViewById(R.id.circ_intercom_height_field);
        intercomObsField = view.findViewById(R.id.circ_has_intercom_obs_field);
        biometryHeightField = view.findViewById(R.id.circ_biometry_height_field);
        biometryObsField = view.findViewById(R.id.circ_has_biometry_obs_field);
        unevenHeightField = view.findViewById(R.id.circ_uneven_height_field);
        inclField = view.findViewById(R.id.circ_talude_incl_field);
        photoField = view.findViewById(R.id.circ_photo_field);
//        TextInputEditText
        circLocValue = view.findViewById(R.id.circ_location_value);
        vertSignObsValue = view.findViewById(R.id.circ_visual_sign_obs_value);
        carpetObsValue = view.findViewById(R.id.circ_carpet_obs_value);
        accessFloorValue = view.findViewById(R.id.circ_access_floor_obs_value);
        intercomHeightValue = view.findViewById(R.id.circ_intercom_height_value);
        intercomObsValue = view.findViewById(R.id.circ_has_intercom_obs_value);
        biometryHeightValue = view.findViewById(R.id.circ_biometry_height_value);
        biometryObsValue = view.findViewById(R.id.circ_has_biometry_obs_value);
        unevenHeightValue = view.findViewById(R.id.circ_uneven_height_value);
        inclValue = view.findViewById(R.id.circ_talude_incl_value);
        photoValue = view.findViewById(R.id.circ_photo_value);
//        RadioGroup
        vertSignRadio = view.findViewById(R.id.circ_has_visual_sign_radio);
        carpetRadio = view.findViewById(R.id.circ_has_carpet_radio);
        accessFloorRadio = view.findViewById(R.id.circ_accessible_floor_radio);
        intercomRadio = view.findViewById(R.id.circ_has_intercom_radio);
        bioClockRadio = view.findViewById(R.id.circ_has_biometry_radio);
        hasUnevenRadio = view.findViewById(R.id.circ_uneven_radio);
        unevenHeightRadio = view.findViewById(R.id.circ_uneven_height_radio);
        taludeRadio = view.findViewById(R.id.circ_uneven_talude_radio);
        taludeInclRadio = view.findViewById(R.id.circ_talude_incl_radio);
        hasProtectRadio = view.findViewById(R.id.circ_fall_protect_radio);
        protectTypeRadio = view.findViewById(R.id.protect_type_radio);
//        TextView
        vertSignError = view.findViewById(R.id.circ_visual_sign_error);
        carpetError = view.findViewById(R.id.circ_carpet_error);
        accessError = view.findViewById(R.id.circ_accessible_floor_error);
        intercomError = view.findViewById(R.id.circ_has_intercom_error);
        bioClockError = view.findViewById(R.id.circ_has_biometry_error);
        hasUnevenError = view.findViewById(R.id.circ_uneven_error);
        unevenHeightHeader = view.findViewById(R.id.circ_uneven_height_header);
        unevenHeightError = view.findViewById(R.id.circ_uneven_height_error);
        hasTaludeHeader = view.findViewById(R.id.circ_uneven_talude_header);
        hasTaludeError = view.findViewById(R.id.circ_uneven_talude_error);
        taludeInclHeader = view.findViewById(R.id.circ_talude_incl_header);
        taludeInclError = view.findViewById(R.id.circ_talude_incl_error);
        hasProtectHeader = view.findViewById(R.id.circ_fall_protect_header);
        hasProtectError = view.findViewById(R.id.circ_fall_protect_error);
        protectTypeError = view.findViewById(R.id.protect_type_error);
        protectTypeHeader = view.findViewById(R.id.circ_protect_type_header);
//        ImageButton
        protectType1 = view.findViewById(R.id.protect_type_1);
        protectType2 = view.findViewById(R.id.protect_type_2);
        protectType3 = view.findViewById(R.id.protect_type_3);
//        MaterialButton
        cancelCirc = view.findViewById(R.id.cancel_circ);
        continueCirc = view.findViewById(R.id.continue_circ);
//        FrameLayout
        protectFrame = view.findViewById(R.id.fall_protect_frame);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listeners
        accessFloorRadio.setOnCheckedChangeListener(this::radioListener);
        intercomRadio.setOnCheckedChangeListener(this::radioListener);
        bioClockRadio.setOnCheckedChangeListener(this::radioListener);
        hasUnevenRadio.setOnCheckedChangeListener(this::radioListener);
        unevenHeightRadio.setOnCheckedChangeListener(this::radioListener);
        taludeRadio.setOnCheckedChangeListener(this::radioListener);
        taludeInclRadio.setOnCheckedChangeListener(this::radioListener);
        hasProtectRadio.setOnCheckedChangeListener(this::radioListener);
        protectTypeRadio.setOnCheckedChangeListener(this::radioListener);
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
        int index = indexRadio(radio);

        if (radio == accessFloorRadio) {
            if (index == 1)
                accessFloorField.setVisibility(View.VISIBLE);
            else {
                accessFloorValue.setText(null);
                accessFloorField.setVisibility(View.GONE);
            }
        } else if (radio == intercomRadio) {
            if (index == 1)
                intercomHeightField.setVisibility(View.VISIBLE);
            else {
                intercomHeightValue.setText(null);
                intercomHeightField.setVisibility(View.GONE);
            }
        } else if (radio == bioClockRadio) {
            if (index == 1) {
                biometryHeightField.setVisibility(View.VISIBLE);
                biometryObsField.setVisibility(View.VISIBLE);
            } else {
                biometryHeightValue.setText(null);
                biometryObsValue.setText(null);
                biometryHeightField.setVisibility(View.GONE);
                biometryObsField.setVisibility(View.GONE);
            }
        } else if (radio == hasUnevenRadio) {
            if (index == 1) {
                unevenHeightHeader.setVisibility(View.VISIBLE);
                unevenHeightRadio.setVisibility(View.VISIBLE);
            } else {
//                Tirar todas as views de desnível
                unevenHeightHeader.setVisibility(View.GONE);
                unevenHeightRadio.setVisibility(View.GONE);
                unevenHeightValue.setText(null);
                unevenHeightField.setVisibility(View.GONE);
                hasTaludeHeader.setVisibility(View.GONE);
                taludeRadio.clearCheck();
                taludeRadio.setVisibility(View.GONE);
                taludeInclHeader.setVisibility(View.GONE);
                taludeInclRadio.clearCheck();
                taludeInclRadio.setVisibility(View.GONE);
                inclValue.setText(null);
                inclField.setVisibility(View.GONE);
                hasProtectHeader.setVisibility(View.GONE);
                hasProtectRadio.clearCheck();
                hasProtectRadio.setVisibility(View.GONE);
                removeChildFragments();
                protectTypeHeader.setVisibility(View.GONE);
                protectType1.setVisibility(View.GONE);
                protectType2.setVisibility(View.GONE);
                protectType3.setVisibility(View.GONE);
                protectTypeRadio.clearCheck();
                protectTypeRadio.setVisibility(View.GONE);
            }
        } else if (radio == unevenHeightRadio) {
            if (index == 1) {
                unevenHeightField.setVisibility(View.VISIBLE);
                hasTaludeHeader.setVisibility(View.VISIBLE);
                taludeRadio.setVisibility(View.VISIBLE);
            } else {
                unevenHeightValue.setText(null);
                unevenHeightField.setVisibility(View.GONE);
                hasTaludeHeader.setVisibility(View.GONE);
                taludeRadio.clearCheck();
                taludeRadio.setVisibility(View.GONE);
            }
        } else if (radio == taludeRadio) {
            if (index == 1) {
                taludeInclHeader.setVisibility(View.VISIBLE);
                taludeInclRadio.setVisibility(View.VISIBLE);
            }
            else if (index == 0) {
                taludeInclHeader.setVisibility(View.GONE);
                taludeInclRadio.clearCheck();
                taludeInclRadio.setVisibility(View.GONE);
                hasProtectHeader.setVisibility(View.VISIBLE);
                hasProtectRadio.setVisibility(View.VISIBLE);
            }

        } else if (radio == taludeInclRadio) {
            if (index == 1) {
                inclField.setVisibility(View.VISIBLE);
                hasProtectHeader.setVisibility(View.VISIBLE);
                hasProtectRadio.setVisibility(View.VISIBLE);
            } else {
                inclValue.setText(null);
                inclField.setVisibility(View.GONE);
                hasProtectHeader.setVisibility(View.GONE);
                hasProtectRadio.clearCheck();
                hasProtectRadio.setVisibility(View.GONE);
                removeChildFragments();
                protectTypeHeader.setVisibility(View.GONE);
                protectType1.setVisibility(View.GONE);
                protectType2.setVisibility(View.GONE);
                protectType3.setVisibility(View.GONE);
                protectTypeRadio.clearCheck();
                protectTypeRadio.setVisibility(View.GONE);
            }
        } else if (radio == hasProtectRadio) {
            if (index == 1) {
                protectTypeHeader.setVisibility(View.VISIBLE);
                protectType1.setVisibility(View.VISIBLE);
                protectType2.setVisibility(View.VISIBLE);
                protectType3.setVisibility(View.VISIBLE);
                protectTypeRadio.setVisibility(View.VISIBLE);
            } else {
                removeChildFragments();
                protectTypeHeader.setVisibility(View.GONE);
                protectType1.setVisibility(View.GONE);
                protectType2.setVisibility(View.GONE);
                protectType3.setVisibility(View.GONE);
                protectTypeRadio.clearCheck();
                protectTypeRadio.setVisibility(View.GONE);
            }
        } else {
//            TODO - Criar os fragmentos para os tipos de proteção contra queda
        }
    }

    private void removeChildFragments() {
        Fragment fragment = getChildFragmentManager().findFragmentById(R.id.fall_protect_frame);
        if (fragment != null)
            getChildFragmentManager().beginTransaction().remove(fragment).commit();
    }

    private boolean noEmptyFields() {
        int i = 0;
        if (!TextUtils.isEmpty(circLocValue.getText())) {
            i++;
            circLocField.setError(getString(R.string.req_field_error));
        }
        if (indexRadio(vertSignRadio) == -1) {
            i++;
            vertSignError.setVisibility(View.VISIBLE);
        }
        if (indexRadio(carpetRadio) == -1) {
            i++;
            carpetError.setVisibility(View.VISIBLE);
        }
        if (indexRadio(accessFloorRadio) == -1) {
            i++;
            accessError.setVisibility(View.VISIBLE);
        } else if (indexRadio(accessFloorRadio) == 1) {
            if (TextUtils.isEmpty(accessFloorValue.getText())) {
                i++;
                accessFloorField.setError(getString(R.string.req_field_error));
            }
        }
        if (indexRadio(intercomRadio) == -1) {
            i++;
            intercomError.setVisibility(View.VISIBLE);
        } else if (indexRadio(intercomRadio) == 1) {
            if (TextUtils.isEmpty(intercomHeightValue.getText())) {
                i++;
                intercomHeightField.setError(getString(R.string.req_field_error));
            }
        }
        if (indexRadio(bioClockRadio) == -1) {
            i++;
            bioClockError.setVisibility(View.VISIBLE);
        } else if (indexRadio(bioClockRadio) == 1) {
            if (TextUtils.isEmpty(biometryHeightValue.getText())) {
                i++;
                biometryHeightField.setError(getString(R.string.req_field_error));
            }
        }

        return i == 0;
    }
}