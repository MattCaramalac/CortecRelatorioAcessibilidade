package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsRailingEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import java.util.ArrayList;

public class RampStairsRailingFragment extends Fragment implements TagInterface {

    TextInputLayout railingHeightField, railingObsField, beaconHeightField, beaconObsField;
    TextInputEditText railingHeightValue, railingObsValue, beaconHeightValue, beaconObsValue;
    RadioGroup railingSideRadio, hasBeaconRadio;
    MultiLineRadioGroup hasRailingRadio;
    TextView railingSideError, hasRailingError, hasBeaconError, hasBeaconHeader;
    Button saveRailing, cancelRailing;

    Bundle railingBundle;

    ViewModelEntry modelEntry;

    ArrayList<TextInputEditText> obsRailingArray = new ArrayList<>();

    public RampStairsRailingFragment() {
        // Required empty public constructor
    }


    public static RampStairsRailingFragment newInstance() {
        return new RampStairsRailingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            railingBundle = new Bundle(this.getArguments());
        else
            railingBundle = new Bundle();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ramp_stairs_railing, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateRailingFragmentViews(view);

        if (railingBundle.getInt(RAIL_ID) > 0)
            modelEntry.getRampStairsRailing(railingBundle.getInt(RAIL_ID)).observe(getViewLifecycleOwner(), this::loadRailData);

        saveRailing.setOnClickListener(v -> {
            if (checkRailingEmptyFields()) {
                RampStairsRailingEntry newRailing = railingEntry(railingBundle);
                if (railingBundle.getInt(RAIL_ID) > 0) {
                    newRailing.setRailingID(railingBundle.getInt(RAIL_ID));
                    ViewModelEntry.updateRampStairsRailing(newRailing);
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                } else if (railingBundle.getInt(RAIL_ID) == 0) {
                    ViewModelEntry.insertRampStairsRailing(newRailing);
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();

                requireActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });

        cancelRailing.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void instantiateRailingFragmentViews(View view) {
//        TextInputLayout
        railingHeightField = view.findViewById(R.id.railing_height_field);
        railingObsField = view.findViewById(R.id.railing_obs_field);
        beaconHeightField = view.findViewById(R.id.beacon_height_field);
        beaconObsField = view.findViewById(R.id.beacon_obs_field);
//        TextInputEditText
        railingHeightValue = view.findViewById(R.id.railing_height_value);
        railingObsValue = view.findViewById(R.id.railing_obs_value);
        beaconHeightValue = view.findViewById(R.id.beacon_height_value);
        beaconObsValue = view.findViewById(R.id.beacon_obs_value);
//        RadioGroup
        railingSideRadio = view.findViewById(R.id.railing_side_radio);
        hasBeaconRadio = view.findViewById(R.id.has_beacon_radio);
//        MultiLineRadioGroup
        hasRailingRadio = view.findViewById(R.id.has_railing_radio);
//        TextView
        railingSideError = view.findViewById(R.id.railing_side_error);
        hasRailingError = view.findViewById(R.id.has_railing_error);
        hasBeaconHeader = view.findViewById(R.id.has_beacon_header);
        hasBeaconError = view.findViewById(R.id.has_beacon_error);
//        Button
        saveRailing = view.findViewById(R.id.save_railing);
        cancelRailing = view.findViewById(R.id.cancel_railing);
//        Listeners
        hasRailingRadio.setOnCheckedChangeListener(this::hasRailingListener);
        hasBeaconRadio.setOnCheckedChangeListener(this::hasBeaconListener);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Initialize Methods
        allowRailingObsScroll();
        initializeRailingFragment();
    }

    private void loadRailData(RampStairsRailingEntry entry) {
        railingSideRadio.check(railingSideRadio.getChildAt(entry.getRailingSide()).getId());
        hasRailingRadio.checkAt(entry.getHasRailing());
        if (entry.getRailingHeight() != null)
            railingHeightValue.setText(String.valueOf(entry.getRailingHeight()));
        if (entry.getRailingObs() != null)
            railingObsValue.setText(entry.getRailingObs());
        if (entry.getHasBeacon() != null)
            hasBeaconRadio.check(hasBeaconRadio.getChildAt(entry.getHasBeacon()).getId());
        if (entry.getBeaconHeight() != null)
            beaconHeightValue.setText(String.valueOf(entry.getBeaconHeight()));
        if (entry.getBeaconObs() != null)
            beaconObsValue.setText(entry.getBeaconObs());
    }

    private void initializeRailingFragment() {
//        TextInputEditText
        railingHeightValue.setText(null);
        railingObsValue.setText(null);
        beaconHeightValue.setText(null);
        beaconObsValue.setText(null);
//        TextInputLayout
        railingHeightField.setVisibility(View.GONE);
        beaconHeightField.setVisibility(View.GONE);
//        RadioGroup
        railingSideRadio.clearCheck();
        hasBeaconRadio.clearCheck();
//        MultiLineRadioGroup
        hasRailingRadio.clearCheck();
    }

    public boolean scrollingDoorField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    private void addRailingFieldsToArrays() {
        obsRailingArray.add(railingObsValue);
        obsRailingArray.add( beaconObsValue);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowRailingObsScroll() {
        addRailingFieldsToArrays();
        for (TextInputEditText obsScroll : obsRailingArray) {
            obsScroll.setOnTouchListener(this::scrollingDoorField);
        }
    }

    private int getCheckedRailingRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private boolean checkRailingEmptyFields() {
        clearRailingEmptyFieldError();
        int i = 0;
        if (getCheckedRailingRadio(railingSideRadio) == -1) {
            i++;
            railingSideError.setVisibility(View.VISIBLE);
        }
        if (hasRailingRadio.getCheckedRadioButtonIndex() == -1) {
            i++;
            hasRailingError.setVisibility(View.VISIBLE);
        } else {
            if (hasRailingRadio.getCheckedRadioButtonIndex() == 2) {
                if (TextUtils.isEmpty(railingHeightValue.getText())) {
                    i++;
                    railingHeightField.setError(getString(R.string.blank_field_error));
                }
            } else if (hasRailingRadio.getCheckedRadioButtonIndex() == 1) {
                if (TextUtils.isEmpty(railingHeightValue.getText())) {
                    i++;
                    railingHeightField.setError(getString(R.string.blank_field_error));
                }
                if (getCheckedRailingRadio(hasBeaconRadio) == -1) {
                    i++;
                    hasBeaconError.setVisibility(View.VISIBLE);
                } else if (getCheckedRailingRadio(hasBeaconRadio) == 1) {
                    if (TextUtils.isEmpty(beaconHeightValue.getText())) {
                        i++;
                        beaconHeightField.setError(getString(R.string.blank_field_error));
                    }
                }
            } else if (hasRailingRadio.getCheckedRadioButtonIndex() == 0) {
                if (getCheckedRailingRadio(hasBeaconRadio) == -1) {
                    i++;
                    hasBeaconError.setVisibility(View.VISIBLE);
                } else if (getCheckedRailingRadio(hasBeaconRadio) == 1) {
                    if (TextUtils.isEmpty(beaconHeightValue.getText())) {
                        i++;
                        beaconHeightField.setError(getString(R.string.blank_field_error));
                    }
                }
            }
        }

        return i == 0;
    }

    private void clearRailingEmptyFieldError() {
        hasRailingError.setVisibility(View.GONE);
        railingSideError.setVisibility(View.GONE);
        hasBeaconError.setVisibility(View.GONE);

        railingHeightField.setErrorEnabled(false);
        beaconHeightField.setErrorEnabled(false);
    }

    private void hasRailingListener(ViewGroup multi, RadioButton rButton) {
        int index = hasRailingRadio.getCheckedRadioButtonIndex();
        if (index == 3) {
            railingHeightValue.setText(null);
            railingHeightField.setVisibility(View.GONE);
            hasBeaconHeader.setVisibility(View.GONE);
            hasBeaconRadio.clearCheck();
            hasBeaconRadio.setVisibility(View.GONE);
            beaconHeightValue.setText(null);
            beaconHeightField.setVisibility(View.GONE);
        } else if (index == 2) {
            railingHeightField.setVisibility(View.VISIBLE);
            hasBeaconHeader.setVisibility(View.GONE);
            hasBeaconRadio.clearCheck();
            hasBeaconRadio.setVisibility(View.GONE);
            beaconHeightValue.setText(null);
            beaconHeightField.setVisibility(View.GONE);
        } else if (index == 1) {
            railingHeightField.setVisibility(View.VISIBLE);
            hasBeaconHeader.setVisibility(View.VISIBLE);
            hasBeaconRadio.setVisibility(View.VISIBLE);
        } else if (index == 0) {
            railingHeightValue.setText(null);
            railingHeightField.setVisibility(View.GONE);
            hasBeaconHeader.setVisibility(View.VISIBLE);
            hasBeaconRadio.setVisibility(View.VISIBLE);
        }
    }

    private void hasBeaconListener(RadioGroup radio, int checkedID) {
        int index = getCheckedRailingRadio(radio);
        if (index ==1) {
//        TextInputLayout
            beaconHeightField.setVisibility(View.VISIBLE);
        } else {
//        TextInputEditText
            beaconHeightValue.setText(null);
//        TextInputLayout
            beaconHeightField.setVisibility(View.GONE);
        }
    }

    private RampStairsRailingEntry railingEntry(Bundle bundle) {
        int railSide, hasRailing;
        Integer hasBeacon = null;
        Double railingHeight = null, beaconHeight = null;
        String railingObs = null, beaconObs = null;

        railSide = getCheckedRailingRadio(railingSideRadio);
        hasRailing = hasRailingRadio.getCheckedRadioButtonIndex();
        if (hasRailing == 2 || hasRailing == 1)
            railingHeight = Double.parseDouble(String.valueOf(railingHeightValue.getText()));
        if (!TextUtils.isEmpty(railingObsValue.getText()))
            railingObs = String.valueOf(railingObsValue.getText());
        if (hasRailing == 1 || hasRailing == 0) {
            hasBeacon = getCheckedRailingRadio(hasBeaconRadio);
            if (hasBeacon == 1)
                beaconHeight = Double.parseDouble(String.valueOf(beaconHeightValue.getText()));
        }
        if (!TextUtils.isEmpty(beaconObsValue.getText()))
            beaconObs = String.valueOf(beaconObsValue.getText());

        return new RampStairsRailingEntry(bundle.getInt(FLIGHT_ID), railSide, hasRailing, railingHeight,
                railingObs, hasBeacon, beaconHeight, beaconObs);
    }

}