package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
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
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

public class RampStairsHandrailFragment extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

    Bundle handrailBundle;

    TextInputLayout handrailHeightField, handrailGripField, handrailDistField, handrailObsField, initExtLengthField, finalExtLengthField, photoField;
    TextInputEditText handrailHeightValue, handrailGripValue, handrailDistValue, handrailObsValue, initExtLengthValue, finalExtLengthValue, photoValue;
    MultiLineRadioGroup handrailPlacementRadio;
    RadioGroup hasHandrailRadio, hasInitExtensionRadio, hasFinalExtensionRadio;
    TextView hasHandError, handrailLocationError, hasInitExtensionHeader, helper1, initExtensionError, hasFinalExtensionHeader, helper2, finalExtensionError;
    Button saveHandrail, cancelHandrail;

    ViewModelEntry modelEntry;

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
            else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });

        cancelHandrail.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void instantiateHandrailFragmentViews(View view) {
//        TextInputLayout
        handrailHeightField = view.findViewById(R.id.handrail_height_field);
        handrailGripField = view.findViewById(R.id.handrail_grip_field);
        handrailDistField = view.findViewById(R.id.handrail_dist_field);
        handrailObsField = view.findViewById(R.id.handrail_obs_field);
        initExtLengthField = view.findViewById(R.id.handrail_initial_extension_length_field);
        finalExtLengthField = view.findViewById(R.id.handrail_final_extension_length_field);
        photoField = view.findViewById(R.id.handrail_photo_field);
//        TextInputEditText
        handrailHeightValue = view.findViewById(R.id.handrail_height_value);
        handrailGripValue = view.findViewById(R.id.handrail_grip_value);
        handrailDistValue = view.findViewById(R.id.handrail_dist_value);
        handrailObsValue = view.findViewById(R.id.handrail_obs_value);
        initExtLengthValue = view.findViewById(R.id.handrail_initial_extension_length_value);
        finalExtLengthValue = view.findViewById(R.id.handrail_final_extension_length_value);
        photoValue = view.findViewById(R.id.phone_photo_value);
//        RadioGroups
        hasHandrailRadio = view.findViewById(R.id.side_has_handrail_radio);
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
        hasHandError = view.findViewById(R.id.side_has_handrail_error);
        hasInitExtensionHeader = view.findViewById(R.id.handrail_has_initial_extension_header);
        helper1 = view.findViewById(R.id.init_ext_ref_header);
        hasFinalExtensionHeader = view.findViewById(R.id.handrail_has_final_extension_header);
        helper2 = view.findViewById(R.id.final_ext_ref_header);
//        Listeners
        hasFinalExtensionRadio.setOnCheckedChangeListener(this::radioListener);
        hasInitExtensionRadio.setOnCheckedChangeListener(this::radioListener);
        hasHandrailRadio.setOnCheckedChangeListener(this::radioListener);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Initial Methods
        allowObsScroll(handrailObsValue);
        initializeHandrailFragment();
    }

    private void initializeHandrailFragment() {
//        RadioGroups
        hasHandrailRadio.clearCheck();
        hasFinalExtensionRadio.clearCheck();
        hasInitExtensionRadio.clearCheck();
//        MultilineRadio
        handrailPlacementRadio.clearCheck();
//        TextInputEditText
        handrailHeightValue.setText(null);
        handrailGripValue.setText(null);
        handrailDistValue.setText(null);
        initExtLengthValue.setText(null);
        finalExtLengthValue.setText(null);
        handrailObsValue.setText(null);
//        TextInputLayout & TextView
        hasHandError.setVisibility(View.GONE);
        handrailHeightField.setVisibility(View.GONE);
        handrailGripField.setVisibility(View.GONE);
        handrailDistField.setVisibility(View.GONE);
        hasInitExtensionHeader.setVisibility(View.GONE);
        hasInitExtensionRadio.setVisibility(View.GONE);
        initExtensionError.setVisibility(View.GONE);
        helper1.setVisibility(View.GONE);
        hasFinalExtensionHeader.setVisibility(View.GONE);
        hasFinalExtensionRadio.setVisibility(View.GONE);
        finalExtensionError.setVisibility(View.GONE);
        helper2.setVisibility(View.GONE);
    }

    private void loadHandrailData(RampStairsHandrailEntry entry) {
        handrailPlacementRadio.checkAt(entry.getHandrailPlacement());
        checkRadioGroup(hasHandrailRadio, entry.getHasHandrail());
        if (entry.getHasHandrail() == 1) {
            if (entry.getHandrailHeight() != null)
                handrailHeightValue.setText(String.valueOf(entry.getHandrailHeight()));
            if (entry.getHandrailGrip() != null)
                handrailGripValue.setText(String.valueOf(entry.getHandrailGrip()));
            if (entry.getHandrailDist() != null)
                handrailDistValue.setText(String.valueOf(entry.getHandrailDist()));
            if (entry.getHasInitExtension() != null) {
                checkRadioGroup(hasInitExtensionRadio, entry.getHasInitExtension());
                if (entry.getHasInitExtension() == 1)
                    if (entry.getInitExtLength() != null)
                        initExtLengthValue.setText(String.valueOf(entry.getInitExtLength()));
            }
            if (entry.getHasFinalExtension() != null) {
               checkRadioGroup(hasFinalExtensionRadio, entry.getHasFinalExtension());
                if (entry.getHasFinalExtension() == 1)
                    if (entry.getFinalExtLength() != null)
                        finalExtLengthValue.setText(String.valueOf(entry.getFinalExtLength()));
            }
        }
        if (entry.getHandrailObs() != null)
            handrailObsValue.setText(entry.getHandrailObs());
        if (entry.getHandrailPhoto() != null)
            photoValue.setText(entry.getHandrailPhoto());

    }

    private boolean checkHandrailEmptyFields() {
        clearHandrailEmptyFieldError();
        int i = 0;
        if (handrailPlacementRadio.getCheckedRadioButtonIndex() == -1) {
            i++;
            handrailLocationError.setVisibility(View.VISIBLE);
        }
        if (indexRadio(hasHandrailRadio) == -1) {
            i++;
            hasHandError.setVisibility(View.VISIBLE);
        } else if (indexRadio(hasHandrailRadio) == 1) {
            if (TextUtils.isEmpty(handrailHeightValue.getText())) {
                i++;
                handrailHeightField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(handrailGripValue.getText())) {
                i++;
                handrailGripField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(handrailDistValue.getText())) {
                i++;
                handrailDistField.setError(getString(R.string.req_field_error));
            }
            if (indexRadio(hasInitExtensionRadio) == -1) {
                i++;
                initExtensionError.setVisibility(View.VISIBLE);
            } else if (indexRadio(hasInitExtensionRadio) == 1) {
                if (TextUtils.isEmpty(initExtLengthValue.getText())) {
                    i++;
                    initExtLengthField.setError(getString(R.string.req_field_error));
                }
            }
            if (indexRadio(hasFinalExtensionRadio) == -1) {
                i++;
                finalExtensionError.setVisibility(View.VISIBLE);
            } else if (indexRadio(hasFinalExtensionRadio) == 1) {
                if (TextUtils.isEmpty(finalExtLengthValue.getText())) {
                    i++;
                    finalExtLengthField.setError(getString(R.string.req_field_error));
                }
            }
        }
        return i == 0;
    }

    private void clearHandrailEmptyFieldError() {
        hasHandError.setVisibility(View.GONE);
        handrailLocationError.setVisibility(View.GONE);
        initExtensionError.setVisibility(View.GONE);
        finalExtensionError.setVisibility(View.GONE);

        handrailHeightField.setErrorEnabled(false);
        handrailGripField.setErrorEnabled(false);
        handrailDistField.setErrorEnabled(false);
        initExtLengthField.setErrorEnabled(false);
        finalExtLengthField.setErrorEnabled(false);
    }

    private RampStairsHandrailEntry handrailEntry(Bundle bundle) {
        int handrailPlace, hasHandrail;
        Integer hasInitExtension = null, hasFinalExtension = null;
        Double initExtLength = null, finalExtLength = null, handrailHeight = null, handrailGrip = null, handrailDist = null;
        String handObs = null, photo = null;

        handrailPlace = handrailPlacementRadio.getCheckedRadioButtonIndex();
        hasHandrail = indexRadio(hasHandrailRadio);
        if (hasHandrail == 1) {
            if (!TextUtils.isEmpty(handrailHeightValue.getText()))
                handrailHeight = Double.parseDouble(String.valueOf(handrailHeightValue.getText()));
            if (!TextUtils.isEmpty(handrailGripValue.getText()))
                handrailGrip = Double.parseDouble(String.valueOf(handrailGripValue.getText()));
            if (!TextUtils.isEmpty(handrailDistValue.getText()))
                handrailDist = Double.parseDouble(String.valueOf(handrailDistValue.getText()));

            if (indexRadio(hasInitExtensionRadio) != -1) {
                hasInitExtension = indexRadio(hasInitExtensionRadio);
                if (hasInitExtension == 1)
                    initExtLength = Double.parseDouble(String.valueOf(initExtLengthValue.getText()));
            }
            if (indexRadio(hasFinalExtensionRadio) != -1) {
                hasFinalExtension = indexRadio(hasFinalExtensionRadio);
                if (hasFinalExtension == 1)
                    finalExtLength = Double.parseDouble(String.valueOf(finalExtLengthValue.getText()));
            }
        }

        if (!TextUtils.isEmpty(handrailObsValue.getText()))
            handObs = String.valueOf(handrailObsValue.getText());

        if (!TextUtils.isEmpty(photoValue.getText()))
            photo = String.valueOf(photoValue.getText());

        return new RampStairsHandrailEntry(bundle.getInt(FLIGHT_ID), handrailPlace, hasHandrail, handrailHeight, handrailGrip, handrailDist, hasInitExtension,
                initExtLength, hasFinalExtension, finalExtLength, handObs, photo);

    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
        int index = indexRadio(radio);
        if (radio == hasHandrailRadio) {
            if (index == 1) {
                handrailHeightField.setVisibility(View.VISIBLE);
                handrailGripField.setVisibility(View.VISIBLE);
                handrailDistField.setVisibility(View.VISIBLE);
                handrailObsField.setVisibility(View.VISIBLE);
                hasInitExtensionHeader.setVisibility(View.VISIBLE);
                hasInitExtensionRadio.setVisibility(View.VISIBLE);
                helper1.setVisibility(View.VISIBLE);
                hasFinalExtensionHeader.setVisibility(View.VISIBLE);
                hasFinalExtensionRadio.setVisibility(View.VISIBLE);
                helper2.setVisibility(View.VISIBLE);
            } else {
                handrailHeightValue.setText(null);
                handrailGripValue.setText(null);
                handrailDistValue.setText(null);
                initExtLengthValue.setText(null);
                finalExtLengthValue.setText(null);

                hasInitExtensionRadio.clearCheck();
                hasFinalExtensionRadio.clearCheck();

                hasHandError.setVisibility(View.GONE);
                handrailHeightField.setVisibility(View.GONE);
                handrailGripField.setVisibility(View.GONE);
                handrailDistField.setVisibility(View.GONE);
                hasInitExtensionHeader.setVisibility(View.GONE);
                hasInitExtensionRadio.setVisibility(View.GONE);
                initExtensionError.setVisibility(View.GONE);
                helper1.setVisibility(View.GONE);
                hasFinalExtensionHeader.setVisibility(View.GONE);
                hasFinalExtensionRadio.setVisibility(View.GONE);
                finalExtensionError.setVisibility(View.GONE);
                helper2.setVisibility(View.GONE);
            }
        }
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
}