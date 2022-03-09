package com.mpms.relatorioacessibilidadecortec.fragments;

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
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsHandrailEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import java.util.ArrayList;

public class RampStairsHandrailFragment extends Fragment {

    Bundle handrailBundle = new Bundle();
    Bundle flightBundle = new Bundle();

    TextInputLayout handrailHeightField, handrailGripField, handrailObsField, extensionLengthField, extensionObsField;
    TextInputEditText handrailHeightValue, handrailGripValue, handrailObsValue, extensionLengthValue, extensionObsValue;
    MultiLineRadioGroup handrailPlacementRadio;
    RadioGroup hasHandrailRadio, hasExtensionRadio;
    TextView hasHandrailError, handrailLocationError, handrailExtensionError, hasExtensionHeader, handrailSideHeader;
    Button saveHandrail, cancelHandrail;
    ArrayList<TextInputEditText> obsHandrailArray = new ArrayList<>();

    public RampStairsHandrailFragment() {
        // Required empty public constructor
    }

    public static RampStairsHandrailFragment newInstance() {
        return new RampStairsHandrailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ramp_stairs_handrail, container, false);

        flightBundle = this.getArguments();
        if (flightBundle != null)
            handrailBundle.putInt(RampStairsFlightFragment.FLIGHT_ID, flightBundle.getInt(RampStairsFlightFragment.FLIGHT_ID));
        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateHandrailFragmentViews(view);
        allowHandrailObsScroll();
        initializeHandrailFragment();
        hasHandrailRadio.setOnCheckedChangeListener(this::hasHandrailListener);
        hasExtensionRadio.setOnCheckedChangeListener(this::hasExtensionListener);

        saveHandrail.setOnClickListener(v -> {
            if (checkHandrailEmptyFields()) {
                RampStairsHandrailEntry newHandrail = handrailEntry(handrailBundle);
                ViewModelEntry.insertRampStairsHandrail(newHandrail);
                Toast.makeText(getContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                initializeHandrailFragment();
            }
        });

        cancelHandrail.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void instantiateHandrailFragmentViews(View view) {
//        TextInputLayout
        handrailHeightField = view.findViewById(R.id.handrail_height_field);
        handrailGripField = view.findViewById(R.id.handrail_grip_field);
        handrailObsField = view.findViewById(R.id.handrail_obs_field);
        extensionLengthField = view.findViewById(R.id.handrail_extension_length_field);
        extensionObsField = view.findViewById(R.id.handrail_extension_obs_field);
//        TextInputEditText
        handrailHeightValue = view.findViewById(R.id.handrail_height_value);
        handrailGripValue = view.findViewById(R.id.handrail_grip_value);
        handrailObsValue = view.findViewById(R.id.handrail_obs_value);
        extensionLengthValue = view.findViewById(R.id.handrail_extension_length_value);
        extensionObsValue = view.findViewById(R.id.handrail_extension_obs_value);
//        RadioGroups
        hasHandrailRadio = view.findViewById(R.id.has_handrail_radio);
        hasExtensionRadio = view.findViewById(R.id.handrail_has_extension_radio);
//        MultilineRadio
        handrailPlacementRadio = view.findViewById(R.id.handrail_placement_radio);
//        Buttons
        saveHandrail = view.findViewById(R.id.save_handrail);
        cancelHandrail = view.findViewById(R.id.cancel_handrail);
//        TextView
        hasExtensionHeader = view.findViewById(R.id.handrail_has_extension_header);
        handrailSideHeader = view.findViewById(R.id.handrail_choice_header);
        hasHandrailError = view.findViewById(R.id.has_handrail_error);
        handrailLocationError = view.findViewById(R.id.handrail_placement_error);
        handrailExtensionError = view.findViewById(R.id.has_extension_error);
    }

    private void initializeHandrailFragment() {
//        RadioGroups
        hasHandrailRadio.clearCheck();
        hasExtensionRadio.clearCheck();
        hasExtensionRadio.setVisibility(View.GONE);
//        MultilineRadio
        handrailPlacementRadio.clearCheck();
        handrailPlacementRadio.setVisibility(View.GONE);
//        TextInputEditText
        handrailHeightValue.setText(null);
        handrailGripValue.setText(null);
        handrailObsValue.setText(null);
        extensionLengthValue.setText(null);
        extensionObsValue.setText(null);
//        TextInputLayout
        handrailHeightField.setVisibility(View.GONE);
        handrailGripField.setVisibility(View.GONE);
        handrailObsField.setVisibility(View.GONE);
        extensionLengthField.setVisibility(View.GONE);
        extensionObsField.setVisibility(View.GONE);
//        TextView
        hasExtensionHeader.setVisibility(View.GONE);
        handrailSideHeader.setVisibility(View.GONE);
    }

    public boolean scrollingDoorField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    private void addRailingFieldsToArrays() {
        obsHandrailArray.add(handrailObsValue);
        obsHandrailArray.add(extensionObsValue);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowHandrailObsScroll() {
        addRailingFieldsToArrays();
        for (TextInputEditText obsScroll : obsHandrailArray) {
            obsScroll.setOnTouchListener(this::scrollingDoorField);
        }
    }

    private int getCheckedHandrailRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private boolean checkHandrailEmptyFields() {
        clearHandrailEmptyFieldError();
        int i = 0;
        if (getCheckedHandrailRadio(hasHandrailRadio) == -1) {
            i++;
            hasHandrailError.setVisibility(View.VISIBLE);
        } else if (getCheckedHandrailRadio(hasHandrailRadio) == 1) {
            if (handrailPlacementRadio.getCheckedRadioButtonIndex() == -1) {
                i++;
                handrailLocationError.setVisibility(View.VISIBLE);
            }
            if (TextUtils.isEmpty(handrailHeightValue.getText())) {
                i++;
                handrailHeightField.setError(getString(R.string.blank_field_error));
            }
            if (TextUtils.isEmpty(handrailGripValue.getText())) {
                i++;
                handrailGripField.setError(getString(R.string.blank_field_error));
            }
            if (getCheckedHandrailRadio(hasExtensionRadio) == -1) {
                i++;
                handrailExtensionError.setVisibility(View.VISIBLE);
            } else if (getCheckedHandrailRadio(hasExtensionRadio) == 1) {
                if (TextUtils.isEmpty(extensionLengthValue.getText())) {
                    i++;
                    extensionLengthField.setError(getString(R.string.blank_field_error));
                }
            }
        }
        return i == 0;
    }

    private void clearHandrailEmptyFieldError() {
        hasHandrailError.setVisibility(View.GONE);
        handrailLocationError.setVisibility(View.GONE);
        handrailExtensionError.setVisibility(View.GONE);

        handrailHeightField.setErrorEnabled(false);
        handrailGripField.setErrorEnabled(false);
        extensionLengthField.setErrorEnabled(false);
    }

    private void hasHandrailListener(RadioGroup radio, int checkedID) {
        int index = getCheckedHandrailRadio(radio);
        if (index == 1) {
//        RadioGroups
            hasExtensionRadio.setVisibility(View.VISIBLE);
//        MultilineRadio
            handrailPlacementRadio.setVisibility(View.VISIBLE);
//        TextInputLayout
            handrailHeightField.setVisibility(View.VISIBLE);
            handrailGripField.setVisibility(View.VISIBLE);
            handrailObsField.setVisibility(View.VISIBLE);
//        TextView
            hasExtensionHeader.setVisibility(View.VISIBLE);
            handrailSideHeader.setVisibility(View.VISIBLE);
        } else {
//        RadioGroups
            hasExtensionRadio.clearCheck();
            hasExtensionRadio.setVisibility(View.GONE);
//        MultilineRadio
            handrailPlacementRadio.clearCheck();
            handrailPlacementRadio.setVisibility(View.GONE);
//        TextInputEditText
            handrailHeightValue.setText(null);
            handrailGripValue.setText(null);
            handrailObsValue.setText(null);
            extensionLengthValue.setText(null);
            extensionObsValue.setText(null);
//        TextInputLayout
            handrailHeightField.setVisibility(View.GONE);
            handrailGripField.setVisibility(View.GONE);
            handrailObsField.setVisibility(View.GONE);
            extensionLengthField.setVisibility(View.GONE);
            extensionObsField.setVisibility(View.GONE);
//        TextViews
            hasExtensionHeader.setVisibility(View.GONE);
            handrailSideHeader.setVisibility(View.GONE);
        }
    }

    private void hasExtensionListener(RadioGroup radio, int checkedID) {
        int index = getCheckedHandrailRadio(radio);
        if (index == 1) {
//        TextInputLayout
            extensionLengthField.setVisibility(View.VISIBLE);
            extensionObsField.setVisibility(View.VISIBLE);
        } else {
//        TextInputEditText
            extensionLengthValue.setText(null);
            extensionObsValue.setText(null);
//        TextInputLayout
            extensionLengthField.setVisibility(View.GONE);
            extensionObsField.setVisibility(View.GONE);
        }
    }

    private RampStairsHandrailEntry handrailEntry(Bundle bundle) {
        int hasHandrail = getCheckedHandrailRadio(hasHandrailRadio);
        if (hasHandrail == 1) {
            Integer handrailPlacement = handrailPlacementRadio.getCheckedRadioButtonIndex();
            Double handrailHeight = Double.valueOf(String.valueOf(handrailHeightValue.getText()));
            Double handrailGrip = Double.valueOf(String.valueOf(handrailGripValue.getText()));
            String handrailObs = String.valueOf(handrailObsValue.getText());
            Integer handrailHasExtension = getCheckedHandrailRadio(hasExtensionRadio);
            if (handrailHasExtension == 1) {
                Double extensionLength = Double.valueOf(String.valueOf(extensionLengthValue.getText()));
                String extensionObs = String.valueOf(extensionObsValue.getText());
                return new RampStairsHandrailEntry(bundle.getInt(RampStairsFlightFragment.FLIGHT_ID), hasHandrail, handrailPlacement, handrailHeight,
                        handrailGrip, handrailObs, handrailHasExtension, extensionLength, extensionObs);

            } else {
                return new RampStairsHandrailEntry(bundle.getInt(RampStairsFlightFragment.FLIGHT_ID), hasHandrail, handrailPlacement, handrailHeight,
                        handrailGrip, handrailObs, handrailHasExtension, null, null);
            }

        } else {
            return new RampStairsHandrailEntry(bundle.getInt(RampStairsFlightFragment.FLIGHT_ID), hasHandrail, null, null,
                    null, null, null, null, null);
        }
    }


}