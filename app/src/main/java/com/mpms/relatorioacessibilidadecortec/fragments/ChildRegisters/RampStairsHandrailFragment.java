package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

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
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import java.util.ArrayList;

public class RampStairsHandrailFragment extends Fragment implements TagInterface {

    Bundle handrailBundle;

    TextInputLayout handrailHeightField, handrailGripField, handrailObsField, initExtLengthField, finalExtLengthField, extensionObsField;
    TextInputEditText handrailHeightValue, handrailGripValue, handrailObsValue, initExtLengthValue, finalExtLengthValue, extensionObsValue;
    MultiLineRadioGroup handrailPlacementRadio;
    RadioGroup hasInitExtensionRadio, hasFinalExtensionRadio;
    TextView handrailLocationError, initExtensionError, finalExtensionError;
    Button saveHandrail, cancelHandrail;

    ViewModelEntry modelEntry;

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
        if (this.getArguments() != null)
            handrailBundle = new Bundle(this.getArguments());
        else
            handrailBundle = new Bundle();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ramp_stairs_handrail, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateHandrailFragmentViews(view);

        if (handrailBundle.getInt(HANDRAIL_ID) > 0)
            modelEntry.getOneHandrail(handrailBundle.getInt(HANDRAIL_ID)).observe(getViewLifecycleOwner(), this::loadHandrailData);

        saveHandrail.setOnClickListener(v -> {
            if (checkHandrailEmptyFields()) {
                RampStairsHandrailEntry newHandrail = handrailEntry(handrailBundle);
                if (handrailBundle.getInt(HANDRAIL_ID) > 0) {
                    newHandrail.setHandrailID(handrailBundle.getInt(HANDRAIL_ID));
                    ViewModelEntry.updateRampStairsHandrail(newHandrail);
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                } else if (handrailBundle.getInt(HANDRAIL_ID) == 0) {
                    ViewModelEntry.insertRampStairsHandrail(newHandrail);
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();

                requireActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });

        cancelHandrail.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void instantiateHandrailFragmentViews(View view) {
//        TextInputLayout
        handrailHeightField = view.findViewById(R.id.handrail_height_field);
        handrailGripField = view.findViewById(R.id.handrail_grip_field);
        handrailObsField = view.findViewById(R.id.handrail_obs_field);
        initExtLengthField = view.findViewById(R.id.handrail_initial_extension_length_field);
        finalExtLengthField = view.findViewById(R.id.handrail_final_extension_length_field);
        extensionObsField = view.findViewById(R.id.handrail_extension_obs_field);
//        TextInputEditText
        handrailHeightValue = view.findViewById(R.id.handrail_height_value);
        handrailGripValue = view.findViewById(R.id.handrail_grip_value);
        handrailObsValue = view.findViewById(R.id.handrail_obs_value);
        initExtLengthValue = view.findViewById(R.id.handrail_initial_extension_length_value);
        finalExtLengthValue = view.findViewById(R.id.handrail_final_extension_length_value);
        extensionObsValue = view.findViewById(R.id.handrail_extension_obs_value);
//        RadioGroups
        hasInitExtensionRadio = view.findViewById(R.id.handrail_has_initial_extension_radio);
        hasFinalExtensionRadio = view.findViewById(R.id.handrail_has_final_extension_radio);
//        MultilineRadio
        handrailPlacementRadio = view.findViewById(R.id.handrail_placement_radio);
//        Buttons
        saveHandrail = view.findViewById(R.id.save_handrail);
        cancelHandrail = view.findViewById(R.id.cancel_handrail);
//        TextView
        handrailLocationError = view.findViewById(R.id.handrail_placement_error);
        initExtensionError = view.findViewById(R.id.has_initial_extension_error);
        finalExtensionError = view.findViewById(R.id.has_final_extension_error);
//        Listeners
        hasFinalExtensionRadio.setOnCheckedChangeListener(this::radioExtensionListener);
        hasInitExtensionRadio.setOnCheckedChangeListener(this::radioExtensionListener);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Initial Methods
        allowHandrailObsScroll();
        initializeHandrailFragment();
    }

    private void initializeHandrailFragment() {
//        RadioGroups
        hasFinalExtensionRadio.clearCheck();
        hasInitExtensionRadio.clearCheck();
//        MultilineRadio
        handrailPlacementRadio.clearCheck();
//        TextInputEditText
        handrailHeightValue.setText(null);
        handrailGripValue.setText(null);
        handrailObsValue.setText(null);
        initExtLengthValue.setText(null);
        finalExtLengthValue.setText(null);
        extensionObsValue.setText(null);
//        TextInputLayout
        initExtLengthField.setVisibility(View.GONE);
        finalExtLengthField.setVisibility(View.GONE);
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

    private void loadHandrailData(RampStairsHandrailEntry entry) {
        handrailPlacementRadio.checkAt(entry.getHandrailPlacement());
        handrailHeightValue.setText(String.valueOf(entry.getHandrailHeight()));
        handrailGripValue.setText(String.valueOf(entry.getHandrailGrip()));
        if (entry.getHandrailObs() != null)
            handrailObsValue.setText(entry.getHandrailObs());
        hasInitExtensionRadio.check(hasInitExtensionRadio.getChildAt(entry.getHasInitExtension()).getId());
        if (entry.getHasInitExtension() == 1)
            initExtLengthValue.setText(String.valueOf(entry.getInitExtLength()));
        hasFinalExtensionRadio.check(hasFinalExtensionRadio.getChildAt(entry.getHasFinalExtension()).getId());
        if (entry.getHasFinalExtension() == 1)
            finalExtLengthValue.setText(String.valueOf(entry.getFinalExtLength()));
        if (entry.getHandrailObs() != null)
            extensionObsValue.setText(entry.getExtensionObs());
    }

    private int getCheckedHandrailRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private boolean checkHandrailEmptyFields() {
        clearHandrailEmptyFieldError();
        int i = 0;
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
        if (getCheckedHandrailRadio(hasInitExtensionRadio) == -1) {
            i++;
            initExtensionError.setVisibility(View.VISIBLE);
        } else if (getCheckedHandrailRadio(hasInitExtensionRadio) == 1) {
            if (TextUtils.isEmpty(initExtLengthValue.getText())) {
                i++;
                initExtLengthField.setError(getString(R.string.blank_field_error));
            }
        }
        if (getCheckedHandrailRadio(hasFinalExtensionRadio) == -1) {
            i++;
            finalExtensionError.setVisibility(View.VISIBLE);
        } else if (getCheckedHandrailRadio(hasFinalExtensionRadio) == 1) {
            if (TextUtils.isEmpty(finalExtLengthValue.getText())) {
                i++;
                finalExtLengthField.setError(getString(R.string.blank_field_error));
            }
        }

        return i == 0;
    }

    private void clearHandrailEmptyFieldError() {
        handrailLocationError.setVisibility(View.GONE);
        initExtensionError.setVisibility(View.GONE);
        finalExtensionError.setVisibility(View.GONE);

        handrailHeightField.setErrorEnabled(false);
        handrailGripField.setErrorEnabled(false);
        initExtLengthField.setErrorEnabled(false);
        finalExtLengthField.setErrorEnabled(false);
    }

    private void radioExtensionListener(RadioGroup radio, int checkedID) {
        int index = getCheckedHandrailRadio(radio);
        if (radio == hasInitExtensionRadio) {
            if (index == 1)
                initExtLengthField.setVisibility(View.VISIBLE);
            else {
                initExtLengthValue.setText(null);
                initExtLengthField.setVisibility(View.GONE);
            }
        } else if (radio == hasFinalExtensionRadio) {
            if (index == 1)
                finalExtLengthField.setVisibility(View.VISIBLE);
            else {
                finalExtLengthValue.setText(null);
                finalExtLengthField.setVisibility(View.GONE);
            }
        }
    }

    private RampStairsHandrailEntry handrailEntry(Bundle bundle) {
        int handrailPlace, hasInitExtension, hasFinalExtension;
        double handrailHeight, handrailGrip;
        Double initExtLength = null, finalExtLength = null;
        String handObs = null, extObs = null;


        handrailPlace = handrailPlacementRadio.getCheckedRadioButtonIndex();
        handrailHeight = Double.parseDouble(String.valueOf(handrailHeightValue.getText()));
        handrailGrip = Double.parseDouble(String.valueOf(handrailGripValue.getText()));
        if (!TextUtils.isEmpty(handrailObsValue.getText()))
            handObs = String.valueOf(handrailObsValue.getText());
        hasInitExtension = getCheckedHandrailRadio(hasInitExtensionRadio);
        if (hasInitExtension == 1)
            initExtLength = Double.parseDouble(String.valueOf(initExtLengthValue.getText()));
        hasFinalExtension = getCheckedHandrailRadio(hasFinalExtensionRadio);
        if (hasFinalExtension == 1)
            finalExtLength = Double.parseDouble(String.valueOf(finalExtLengthValue.getText()));
        if (!TextUtils.isEmpty(extensionObsValue.getText()))
            extObs = String.valueOf(extensionObsValue.getText());

        return new RampStairsHandrailEntry(bundle.getInt(FLIGHT_ID), handrailPlace, handrailHeight, handrailGrip, handObs, hasInitExtension,
                initExtLength, hasFinalExtension, finalExtLength, extObs);

    }

}