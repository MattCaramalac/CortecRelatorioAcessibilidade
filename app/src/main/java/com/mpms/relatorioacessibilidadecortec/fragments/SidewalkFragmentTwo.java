package com.mpms.relatorioacessibilidadecortec.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntryTwo;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SidewalkSlopeListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.PayPhoneListFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.ArrayList;

public class SidewalkFragmentTwo extends Fragment implements TagInterface {

    TextInputLayout accessFloorObsField, sidewalkObsField, aerialObsDescField, sidewalkLidDescField, sideConsObsField;
    TextInputEditText accessFloorObsValue, sidewalkObsValue, aerialObsDescValue, sidewalkLidDescValue, sideConsObsValue;
    RadioGroup accessibleFloorRadio, sideHasSlopeRadio, hasAerialObsRadio, hasLidRadio, payphoneRadio, sideHasRamps, sideHasStairs, sideConsRadio;
    TextView accessFloorError, sideHasSlopeError, aerialError, lidError, payphoneError, payphoneHeader, sideConsError;
    MaterialButton addSlope, addPayphone, addRamp, addStairs, returnSideOne, saveSidewalk;

    ArrayList<TextInputEditText> sidewalk2ObsArray = new ArrayList<>();

    int rowCounter = 0;

    Bundle sidewalk2Data;

    ViewModelEntry modelEntry;

    public SidewalkFragmentTwo() {
        // Required empty public constructor
    }

    public static SidewalkFragmentTwo newInstance() {
        return new SidewalkFragmentTwo();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            sidewalk2Data = new Bundle(this.getArguments());
        else
            sidewalk2Data = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sidewalk_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSideTwoViews(view);

        if (sidewalk2Data.getInt(AMBIENT_ID) == 0) {
            modelEntry.getLastSidewalkEntry().observe(getViewLifecycleOwner(), sidewalk ->
                    sidewalk2Data.putInt(AMBIENT_ID, sidewalk.getSidewalkID()));
        } else if (sidewalk2Data.getInt(AMBIENT_ID) > 0) {
            modelEntry.getSidewalkEntry(sidewalk2Data.getInt(AMBIENT_ID))
                    .observe(getViewLifecycleOwner(), this::loadSidewalk2Data);
        } else {
            Toast.makeText(getContext(), "Ocorreu um erro. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
            requireActivity().getSupportFragmentManager().popBackStack(SIDEWALK_LIST, 0);
        }

        modelEntry.getAllSidewalkSlopes(sidewalk2Data.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), slopeList -> {
            if (slopeList != null && slopeList.size() > 0) {
                rowCounter = slopeList.size();
            }
        });

        saveSidewalk.setOnClickListener(v -> {
            if (checkEmptySidewalkFields()) {
                if (sidewalk2Data.getInt(AMBIENT_ID) > 0) {
                    if (getCheckedSidewalkRadioButton(sideHasSlopeRadio) == 1 && rowCounter == 0) {
                        Toast.makeText(getContext(), "Por favor, adicione rebaixamentos para esta calçada", Toast.LENGTH_LONG).show();
                    } else if (getCheckedSidewalkRadioButton(sideHasSlopeRadio) == 0 && rowCounter > 0) {
//                        TODO - Deletar os slopes na hora de salvar. Chamar dialog - toast temporário.
                        Toast.makeText(getContext(), "A calçada possui rebaixamentos. Marque a opção correta ou delete os rebaixamentos", Toast.LENGTH_LONG).show();
                    } else {
                        ViewModelEntry.updateSidewalkTwo(updateSideTwo(sidewalk2Data));
                        Toast.makeText(getContext(), "Cadastro atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                        requireActivity().getSupportFragmentManager().popBackStack(InspectionActivity.SIDEWALK_LIST, 0);
                    }
                } else {
                    Toast.makeText(getContext(), "Ocorreu um erro. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStack(SIDEWALK_LIST, 0);
                }
            }
        });

        returnSideOne.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void instantiateSideTwoViews(View view) {
//        TextInputLayout
        accessFloorObsField = view.findViewById(R.id.accessible_sidewalk_field);
        sidewalkObsField = view.findViewById(R.id.sidewalk_obs_field);
        aerialObsDescField = view.findViewById(R.id.aerial_obstacle_desc_field);
        sidewalkLidDescField = view.findViewById(R.id.sidewalk_lid_desc_field);
        sideConsObsField = view.findViewById(R.id.side_conservation_obs_field);
//        TextInputEditText
        accessFloorObsValue = view.findViewById(R.id.accessible_sidewalk_value);
        sidewalkObsValue = view.findViewById(R.id.sidewalk_obs_value);
        aerialObsDescValue = view.findViewById(R.id.aerial_obstacle_desc_value);
        sidewalkLidDescValue = view.findViewById(R.id.sidewalk_lid_desc_value);
        sideConsObsValue = view.findViewById(R.id.side_conservation_obs_value);
//        RadioGroup
        accessibleFloorRadio = view.findViewById(R.id.accessible_sidewalk_radio);
        sideHasSlopeRadio = view.findViewById(R.id.radio_sidewalk_slope);
        hasAerialObsRadio = view.findViewById(R.id.aerial_obstacle_radio);
        hasLidRadio = view.findViewById(R.id.sidewalk_lid_radio);
        payphoneRadio = view.findViewById(R.id.radio_sidewalk_payphone);
        sideHasRamps = view.findViewById(R.id.sidewalk_has_ramps_radio);
        sideHasStairs = view.findViewById(R.id.sidewalk_has_stairs_radio);
        sideConsRadio = view.findViewById(R.id.side_conservation_radio);
//        TextView
        accessFloorError = view.findViewById(R.id.accessible_sidewalk_error);
        sideHasSlopeError = view.findViewById(R.id.sidewalk_has_slope_error);
        aerialError = view.findViewById(R.id.aerial_obstacle_error);
        lidError = view.findViewById(R.id.sidewalk_lid_error);
        payphoneError = view.findViewById(R.id.sidewalk_has_payphone_error);
        payphoneHeader = view.findViewById(R.id.label_sidewalk_payphone_register);
        sideConsError = view.findViewById(R.id.side_conservation_error);
//        MaterialButton
        addSlope = view.findViewById(R.id.add_sidewalk_slope);
        addPayphone = view.findViewById(R.id.add_sidewalk_payphone);
        addRamp = view.findViewById(R.id.add_sidewalk_ramps_button);
        addStairs = view.findViewById(R.id.add_sidewalk_stairs_button);
        returnSideOne = view.findViewById(R.id.return_side_one_button);
        saveSidewalk = view.findViewById(R.id.save_sidewalk_button);
//        RadioListener
        accessibleFloorRadio.setOnCheckedChangeListener(this::sidewalk2RadioListener);
        sideHasSlopeRadio.setOnCheckedChangeListener(this::sidewalk2RadioListener);
        hasLidRadio.setOnCheckedChangeListener(this::sidewalk2RadioListener);
        hasAerialObsRadio.setOnCheckedChangeListener(this::sidewalk2RadioListener);
        payphoneRadio.setOnCheckedChangeListener(this::sidewalk2RadioListener);
        sideHasRamps.setOnCheckedChangeListener(this::sidewalk2RadioListener);
        sideHasStairs.setOnCheckedChangeListener(this::sidewalk2RadioListener);
//        ButtonListener
        addSlope.setOnClickListener(this::childFragButtonListener);
        addPayphone.setOnClickListener(this::childFragButtonListener);
        addRamp.setOnClickListener(this::childFragButtonListener);
        addStairs.setOnClickListener(this::childFragButtonListener);

        modelEntry = new ViewModelEntry(requireActivity().getApplication());

        allowSidewalkObsScroll();
    }

    private void childFragButtonListener(View view) {
        ViewModelEntry.updateSidewalkTwo(updateSideTwo(sidewalk2Data));
        sidewalk2Data.putBoolean(FROM_SIDEWALK, true);
        if (view == addSlope) {
            openSidewalkSlopeFragment();
        } else if (view == addPayphone) {
            openPayPhoneListFragment();
        } else {
            Fragment fragment = RampStairsListFragment.newInstance();
            if (view == addRamp) {
                sidewalk2Data.putInt(RAMP_OR_STAIRS, 2);
            } else {
                sidewalk2Data.putInt(RAMP_OR_STAIRS, 1);
            }
            sidewalk2Data.putInt(RampStairsListFragment.AMBIENT_TYPE, 2);
            fragment.setArguments(sidewalk2Data);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.show_fragment_selected, fragment).addToBackStack(PARKING_LIST).commit();
        }
    }

    private void loadSidewalk2Data(SidewalkEntry sidewalk) {
        if (sidewalk.getSideConStatus() != null && sidewalk.getSideConStatus() > -1)
            sideConsRadio.check(sideConsRadio.getChildAt(sidewalk.getSideConStatus()).getId());
        if (sidewalk.getSideConsObs() != null)
            sideConsObsValue.setText(sidewalk.getSideConsObs());

        if (sidewalk.getSideFloorIsAccessible() != null && sidewalk.getSideFloorIsAccessible() > -1) {
            accessibleFloorRadio.check(accessibleFloorRadio.getChildAt(sidewalk.getSideFloorIsAccessible()).getId());
            if (sidewalk.getSideFloorIsAccessible() == 0) {
                if (sidewalk.getAccessFloorObs() != null)
                    accessFloorObsValue.setText(sidewalk.getAccessFloorObs());
            }
        }

        if (sidewalk.getSidewalkHasLids() != null && sidewalk.getSidewalkHasLids() > -1) {
            hasLidRadio.check(hasLidRadio.getChildAt(sidewalk.getSidewalkHasLids()).getId());
            if (sidewalk.getSidewalkHasLids() == 1) {
                if (sidewalk.getSidewalkLidDesc() != null)
                    sidewalkLidDescValue.setText(sidewalk.getSidewalkLidDesc());
            }
        }

        if (sidewalk.getHasAerialObstacle() != null && sidewalk.getHasAerialObstacle() > -1) {
            hasAerialObsRadio.check(hasAerialObsRadio.getChildAt(sidewalk.getHasAerialObstacle()).getId());
            if (sidewalk.getHasAerialObstacle() == 1) {
                if (sidewalk.getAerialObstacleDesc() != null)
                    aerialObsDescValue.setText(sidewalk.getAerialObstacleDesc());
            }
        }

        if (sidewalk.getSideHasSlope() != null && sidewalk.getSideHasSlope() > -1)
            sideHasSlopeRadio.check(sideHasSlopeRadio.getChildAt(sidewalk.getSideHasSlope()).getId());
        if (sidewalk.getSideHasPayphones() != null && sidewalk.getSideHasPayphones() > -1)
            payphoneRadio.check(payphoneRadio.getChildAt(sidewalk.getSideHasPayphones()).getId());
        if (sidewalk.getSideHasStairs() != null && sidewalk.getSideHasStairs() > -1)
            sideHasStairs.check(sideHasStairs.getChildAt(sidewalk.getSideHasStairs()).getId());
        if (sidewalk.getSideHasRamps() != null && sidewalk.getSideHasRamps() > -1)
            sideHasRamps.check(sideHasRamps.getChildAt(sidewalk.getSideHasRamps()).getId());
        if (sidewalk.getSidewalkObs() != null)
            sidewalkObsValue.setText(sidewalk.getSidewalkObs());
    }

    private void sidewalk2RadioListener(RadioGroup radio, int checkedID) {
        int index = radio.indexOfChild(radio.findViewById(checkedID));
        if (radio == accessibleFloorRadio) {
            if (index == 0) {
                accessFloorObsField.setVisibility(View.VISIBLE);
            } else {
                accessFloorObsValue.setText(null);
                accessFloorObsField.setVisibility(View.GONE);
            }
        } else if (radio == sideHasSlopeRadio) {
            if (index == 1)
                addSlope.setVisibility(View.VISIBLE);
            else
                addSlope.setVisibility(View.GONE);
        } else if (radio == hasAerialObsRadio) {
            if (index == 1)
                aerialObsDescField.setVisibility(View.VISIBLE);
            else {
                aerialObsDescValue.setText(null);
                aerialObsDescField.setVisibility(View.GONE);
            }
        } else if (radio == hasLidRadio) {
            if (index == 1)
                sidewalkLidDescField.setVisibility(View.VISIBLE);
            else {
                sidewalkLidDescValue.setText(null);
                sidewalkLidDescField.setVisibility(View.GONE);
            }
        } else if (radio == payphoneRadio) {
            if (index == 1) {
                payphoneHeader.setVisibility(View.VISIBLE);
                addPayphone.setVisibility(View.VISIBLE);
            } else {
                payphoneHeader.setVisibility(View.GONE);
                addPayphone.setVisibility(View.GONE);
            }
        } else if (radio == sideHasStairs) {
            if (index == 1)
                addStairs.setVisibility(View.VISIBLE);
            else
                addStairs.setVisibility(View.GONE);
        } else if (radio == sideHasRamps) {
            if (index == 1)
                addRamp.setVisibility(View.VISIBLE);
            else
                addRamp.setVisibility(View.GONE);
        }
    }

    private boolean checkEmptySidewalkFields() {
        clearSidewalkEmptyFieldErrors();
        int i = 0;

        if (getCheckedSidewalkRadioButton(sideConsRadio) == -1) {
            i++;
            sideConsError.setVisibility(View.VISIBLE);
        }

        if (getCheckedSidewalkRadioButton(accessibleFloorRadio) == -1) {
            i++;
            accessFloorError.setVisibility(View.VISIBLE);
        } else if (getCheckedSidewalkRadioButton(accessibleFloorRadio) == 0) {
            if (TextUtils.isEmpty(accessFloorObsValue.getText())) {
                i++;
                accessFloorObsField.setError(getString(R.string.blank_field_error));
            }
        }

        if (getCheckedSidewalkRadioButton(hasAerialObsRadio) == -1) {
            i++;
            aerialError.setVisibility(View.VISIBLE);
        } else if (getCheckedSidewalkRadioButton(hasAerialObsRadio) == 1) {
            if (TextUtils.isEmpty(aerialObsDescValue.getText())) {
                i++;
                aerialObsDescField.setError(getString(R.string.blank_field_error));
            }
        }

        if (getCheckedSidewalkRadioButton(hasLidRadio) == -1) {
            i++;
            lidError.setVisibility(View.VISIBLE);
        } else if (getCheckedSidewalkRadioButton(hasLidRadio) == 1) {
            if (TextUtils.isEmpty(sidewalkLidDescValue.getText())) {
                i++;
                sidewalkLidDescField.setError(getString(R.string.blank_field_error));
            }
        }

        if (getCheckedSidewalkRadioButton(sideHasSlopeRadio) == -1) {
            i++;
            sideHasSlopeError.setVisibility(View.VISIBLE);
        }

        if (getCheckedSidewalkRadioButton(payphoneRadio) == -1) {
            i++;
            payphoneError.setVisibility(View.VISIBLE);
        }

        return i == 0;
    }

    private int getCheckedSidewalkRadioButton(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private void openSidewalkSlopeFragment() {
        SidewalkSlopeListFragment slopeFragment = SidewalkSlopeListFragment.newInstance();
        slopeFragment.setArguments(sidewalk2Data);
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, slopeFragment).addToBackStack(null).commit();

    }

    private void openPayPhoneListFragment() {
        PayPhoneListFragment phoneList = PayPhoneListFragment.newInstance();
        phoneList.setArguments(sidewalk2Data);
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, phoneList).addToBackStack(null).commit();

    }

    private void clearSidewalkEmptyFieldErrors() {
        accessFloorObsField.setErrorEnabled(false);
        sidewalkLidDescField.setErrorEnabled(false);
        aerialObsDescField.setErrorEnabled(false);
        accessFloorError.setVisibility(View.GONE);
        sideHasSlopeError.setVisibility(View.GONE);
        lidError.setVisibility(View.GONE);
        aerialError.setVisibility(View.GONE);
        payphoneError.setVisibility(View.GONE);
        sideConsError.setVisibility(View.GONE);

    }

    private SidewalkEntryTwo updateSideTwo(Bundle bundle) {
        Integer consStatus = null, accessFloor = null, hasLid = null, hasAerial = null, hasSlope = null, hasPayphone = null,
                hasRamp = null, hasStairs = null;
        String accessObs = null, aerialDesc = null, lidDesc = null, consDesc = null, sidewalkObs = null;

        if (getCheckedSidewalkRadioButton(sideConsRadio) > -1)
            consStatus = getCheckedSidewalkRadioButton(sideConsRadio);
        if (!TextUtils.isEmpty(sideConsObsValue.getText()))
            consDesc = String.valueOf(sideConsObsValue.getText());
        if (getCheckedSidewalkRadioButton(accessibleFloorRadio) > -1)
            accessFloor = getCheckedSidewalkRadioButton(accessibleFloorRadio);
        if (!TextUtils.isEmpty(accessFloorObsValue.getText()))
            accessObs = String.valueOf(accessFloorObsValue.getText());
        if (getCheckedSidewalkRadioButton(hasLidRadio) > -1)
            hasLid = getCheckedSidewalkRadioButton(hasLidRadio);
        if (!TextUtils.isEmpty(sidewalkLidDescValue.getText()))
            lidDesc = String.valueOf(sidewalkLidDescValue.getText());
        if (getCheckedSidewalkRadioButton(hasAerialObsRadio) > -1)
            hasAerial = getCheckedSidewalkRadioButton(hasAerialObsRadio);
        if (!TextUtils.isEmpty(aerialObsDescValue.getText()))
            aerialDesc = String.valueOf(aerialObsDescValue.getText());
        if (getCheckedSidewalkRadioButton(sideHasSlopeRadio) > -1)
            hasSlope = getCheckedSidewalkRadioButton(sideHasSlopeRadio);
        if (getCheckedSidewalkRadioButton(sideHasStairs) > -1)
            hasStairs = getCheckedSidewalkRadioButton(sideHasStairs);
        if (getCheckedSidewalkRadioButton(sideHasRamps) > -1)
            hasRamp = getCheckedSidewalkRadioButton(sideHasRamps);
        if (getCheckedSidewalkRadioButton(payphoneRadio) > -1)
            hasPayphone = getCheckedSidewalkRadioButton(payphoneRadio);
        if (!TextUtils.isEmpty(sidewalkObsValue.getText()))
            sidewalkObs = String.valueOf(sidewalkObsValue.getText());

        return new SidewalkEntryTwo(bundle.getInt(AMBIENT_ID), accessFloor, accessObs, hasSlope, sidewalkObs, hasAerial, aerialDesc,
                hasLid, lidDesc, consStatus, consDesc, hasPayphone, hasStairs, hasRamp);
    }


    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    private void addObsFieldsToArray() {
        sidewalk2ObsArray.add(accessFloorObsValue);
        sidewalk2ObsArray.add(sidewalkObsValue);
        sidewalk2ObsArray.add(sidewalkLidDescValue);
        sidewalk2ObsArray.add(aerialObsDescValue);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowSidewalkObsScroll() {
        addObsFieldsToArray();
        for (TextInputEditText obsScroll : sidewalk2ObsArray) {
            obsScroll.setOnTouchListener(this::scrollingField);
        }
    }
}