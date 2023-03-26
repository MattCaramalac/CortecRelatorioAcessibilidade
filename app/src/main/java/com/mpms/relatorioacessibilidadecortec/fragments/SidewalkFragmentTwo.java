package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
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
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.ArrayList;

public class SidewalkFragmentTwo extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

    TextInputLayout accessFloorObsField, sidewalkObsField, aerialObsDescField, sidewalkLidDescField, sideConsObsField, photoField;
    TextInputEditText accessFloorObsValue, sidewalkObsValue, aerialObsDescValue, sidewalkLidDescValue, sideConsObsValue, photoValue;
    RadioGroup accessibleFloorRadio, sideHasSlopeRadio, hasAerialObsRadio, hasLidRadio, payphoneRadio, sideConsRadio, sideReqSlopeRadio;
    TextView accessFloorError, sideHasSlopeError, aerialError, lidError, payphoneError, payphoneHeader, sideConsError, sideReqSlopeHeader, sideReqSlopeError;
    MaterialButton addSlope, addPayphone, returnSideOne, saveSidewalk;

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
            Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            requireActivity().getSupportFragmentManager().popBackStack(SIDEWALK_LIST, 0);
        }

        modelEntry.getSideSlopes(sidewalk2Data.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), slopeList -> {
            if (slopeList != null && slopeList.size() > 0) {
                rowCounter = slopeList.size();
            }
        });

        saveSidewalk.setOnClickListener(v -> {
            if (checkEmptySidewalkFields()) {
                if (sidewalk2Data.getInt(AMBIENT_ID) > 0) {
                    if (indexRadio(sideHasSlopeRadio) == 1 && rowCounter == 0) {
                        Toast.makeText(getContext(), "Por favor, adicione rebaixamentos para esta calçada", Toast.LENGTH_LONG).show();
                    } else if (indexRadio(sideHasSlopeRadio) == 0 && rowCounter > 0) {
//                        TODO - Deletar os slopes na hora de salvar. Chamar dialog - toast temporário.
                        Toast.makeText(getContext(), "A calçada possui rebaixamentos. Marque a opção correta ou delete os rebaixamentos", Toast.LENGTH_LONG).show();
                    } else {
                        ViewModelEntry.updateSidewalkTwo(updateSideTwo(sidewalk2Data));
                        Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                        requireActivity().getSupportFragmentManager().popBackStack(InspectionActivity.SIDEWALK_LIST, 0);
                    }
                } else {
                    Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStack(SIDEWALK_LIST, 0);
                }
            }
            else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });

        returnSideOne.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void instantiateSideTwoViews(View view) {
//        TextInputLayout
        accessFloorObsField = view.findViewById(R.id.accessible_sidewalk_field);
        sidewalkObsField = view.findViewById(R.id.sidewalk_obs_field_2);
        aerialObsDescField = view.findViewById(R.id.aerial_obstacle_desc_field);
        sidewalkLidDescField = view.findViewById(R.id.sidewalk_lid_desc_field);
        sideConsObsField = view.findViewById(R.id.side_conservation_obs_field);
        photoField = view.findViewById(R.id.sidewalk_photo_field_2);
//        TextInputEditText
        accessFloorObsValue = view.findViewById(R.id.accessible_sidewalk_value);
        sidewalkObsValue = view.findViewById(R.id.sidewalk_obs_value_2);
        aerialObsDescValue = view.findViewById(R.id.aerial_obstacle_desc_value);
        sidewalkLidDescValue = view.findViewById(R.id.sidewalk_lid_desc_value);
        sideConsObsValue = view.findViewById(R.id.side_conservation_obs_value);
        photoValue = view.findViewById(R.id.sidewalk_photo_value_2);
//        RadioGroup
        accessibleFloorRadio = view.findViewById(R.id.accessible_sidewalk_radio);
        sideHasSlopeRadio = view.findViewById(R.id.radio_sidewalk_slope);
        sideReqSlopeRadio =
        hasAerialObsRadio = view.findViewById(R.id.aerial_obstacle_radio);
        hasLidRadio = view.findViewById(R.id.sidewalk_lid_radio);
        payphoneRadio = view.findViewById(R.id.radio_sidewalk_payphone);
        sideConsRadio = view.findViewById(R.id.side_conservation_radio);
        sideReqSlopeRadio = view.findViewById(R.id.radio_sidewalk_req_slope);
//        TextView
        accessFloorError = view.findViewById(R.id.accessible_sidewalk_error);
        sideHasSlopeError = view.findViewById(R.id.sidewalk_has_slope_error);
        aerialError = view.findViewById(R.id.aerial_obstacle_error);
        lidError = view.findViewById(R.id.sidewalk_lid_error);
        payphoneError = view.findViewById(R.id.sidewalk_has_payphone_error);
        payphoneHeader = view.findViewById(R.id.label_sidewalk_payphone_register);
        sideConsError = view.findViewById(R.id.side_conservation_error);
        sideReqSlopeHeader = view.findViewById(R.id.label_sidewalk_req_slope_header);
        sideReqSlopeError = view.findViewById(R.id.sidewalk_req_slope_error);
//        MaterialButton
        addSlope = view.findViewById(R.id.add_sidewalk_slope);
        addPayphone = view.findViewById(R.id.add_sidewalk_payphone);
        returnSideOne = view.findViewById(R.id.return_side_one_button);
        saveSidewalk = view.findViewById(R.id.save_sidewalk_button);
//        RadioListener
        accessibleFloorRadio.setOnCheckedChangeListener(this::radioListener);
        sideHasSlopeRadio.setOnCheckedChangeListener(this::radioListener);
        hasLidRadio.setOnCheckedChangeListener(this::radioListener);
        hasAerialObsRadio.setOnCheckedChangeListener(this::radioListener);
        payphoneRadio.setOnCheckedChangeListener(this::radioListener);
//        ButtonListener
        addSlope.setOnClickListener(this::childFragButtonListener);
        addPayphone.setOnClickListener(this::childFragButtonListener);

        modelEntry = new ViewModelEntry(requireActivity().getApplication());

        addObsFieldsToArray();
        allowObsScroll(sidewalk2ObsArray);
    }

    private void childFragButtonListener(View view) {
        ViewModelEntry.updateSidewalkTwo(updateSideTwo(sidewalk2Data));
        sidewalk2Data.putBoolean(FROM_SIDEWALK, true);
        if (view == addSlope) {
            openSidewalkSlopeFragment();
        } else
            openPayPhoneListFragment();
    }

    private void loadSidewalk2Data(SidewalkEntry sidewalk) {
        if (sidewalk.getSideConStatus() != null && sidewalk.getSideConStatus() > -1)
           checkRadioGroup(sideConsRadio, sidewalk.getSideConStatus());
        if (sidewalk.getSideConsObs() != null)
            sideConsObsValue.setText(sidewalk.getSideConsObs());

        if (sidewalk.getSideFloorIsAccessible() != null && sidewalk.getSideFloorIsAccessible() > -1) {
            checkRadioGroup(accessibleFloorRadio, sidewalk.getSideFloorIsAccessible());
            if (sidewalk.getSideFloorIsAccessible() == 0) {
                if (sidewalk.getAccessFloorObs() != null)
                    accessFloorObsValue.setText(sidewalk.getAccessFloorObs());
            }
        }

        if (sidewalk.getSidewalkHasLids() != null && sidewalk.getSidewalkHasLids() > -1) {
           checkRadioGroup(hasLidRadio, sidewalk.getSidewalkHasLids());
            if (sidewalk.getSidewalkHasLids() == 1) {
                if (sidewalk.getSidewalkLidDesc() != null)
                    sidewalkLidDescValue.setText(sidewalk.getSidewalkLidDesc());
            }
        }

        if (sidewalk.getHasAerialObstacle() != null && sidewalk.getHasAerialObstacle() > -1) {
           checkRadioGroup(hasAerialObsRadio, sidewalk.getHasAerialObstacle());
            if (sidewalk.getHasAerialObstacle() == 1) {
                if (sidewalk.getAerialObstacleDesc() != null)
                    aerialObsDescValue.setText(sidewalk.getAerialObstacleDesc());
            }
        }

        if (sidewalk.getSideHasSlope() != null && sidewalk.getSideHasSlope() > -1) {
            checkRadioGroup(sideHasSlopeRadio, sidewalk.getSideHasSlope());
            if (sidewalk.getSideHasSlope() == 0) {
                if (sidewalk.getSideReqSlopes() != null && sidewalk.getSideReqSlopes() > -1)
                    checkRadioGroup(sideReqSlopeRadio, sidewalk.getSideReqSlopes());
            }
        }

        if (sidewalk.getSideHasPayphones() != null && sidewalk.getSideHasPayphones() > -1)
            checkRadioGroup(payphoneRadio, sidewalk.getSideHasPayphones());
        if (sidewalk.getSidewalkObs2() != null)
            sidewalkObsValue.setText(sidewalk.getSidewalkObs2());
        if (sidewalk.getSidePhotos2() != null)
            photoValue.setText(sidewalk.getSidePhotos2());
    }

    private boolean checkEmptySidewalkFields() {
        clearSidewalkEmptyFieldErrors();
        int i = 0;

        if (indexRadio(sideConsRadio) == -1) {
            i++;
            sideConsError.setVisibility(View.VISIBLE);
        }

        if (indexRadio(accessibleFloorRadio) == -1) {
            i++;
            accessFloorError.setVisibility(View.VISIBLE);
        } else if (indexRadio(accessibleFloorRadio) == 0) {
            if (TextUtils.isEmpty(accessFloorObsValue.getText())) {
                i++;
                accessFloorObsField.setError(getString(R.string.req_field_error));
            }
        }

        if (indexRadio(hasAerialObsRadio) == -1) {
            i++;
            aerialError.setVisibility(View.VISIBLE);
        } else if (indexRadio(hasAerialObsRadio) == 1) {
            if (TextUtils.isEmpty(aerialObsDescValue.getText())) {
                i++;
                aerialObsDescField.setError(getString(R.string.req_field_error));
            }
        }

        if (indexRadio(hasLidRadio) == -1) {
            i++;
            lidError.setVisibility(View.VISIBLE);
        } else if (indexRadio(hasLidRadio) == 1) {
            if (TextUtils.isEmpty(sidewalkLidDescValue.getText())) {
                i++;
                sidewalkLidDescField.setError(getString(R.string.req_field_error));
            }
        }

        if (indexRadio(sideHasSlopeRadio) == -1) {
            i++;
            sideHasSlopeError.setVisibility(View.VISIBLE);
        } else if (indexRadio(sideHasSlopeRadio) == 0) {
            if (indexRadio(sideReqSlopeRadio) == -1) {
                i++;
                sideReqSlopeError.setVisibility(View.VISIBLE);
            }
        }

        if (indexRadio(payphoneRadio) == -1) {
            i++;
            payphoneError.setVisibility(View.VISIBLE);
        }

        return i == 0;
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
        sideReqSlopeError.setVisibility(View.GONE);
        lidError.setVisibility(View.GONE);
        aerialError.setVisibility(View.GONE);
        payphoneError.setVisibility(View.GONE);
        sideConsError.setVisibility(View.GONE);

    }

    private SidewalkEntryTwo updateSideTwo(Bundle bundle) {
        Integer consStatus = null, accessFloor = null, hasLid = null, hasAerial = null, hasSlope = null, hasPayphone = null, reqSlope = null;
        String accessObs = null, aerialDesc = null, lidDesc = null, consDesc = null, sidewalkObs = null, photo = null;

        if (indexRadio(sideConsRadio) > -1)
            consStatus = indexRadio(sideConsRadio);
        if (!TextUtils.isEmpty(sideConsObsValue.getText()))
            consDesc = String.valueOf(sideConsObsValue.getText());
        if (indexRadio(accessibleFloorRadio) > -1)
            accessFloor = indexRadio(accessibleFloorRadio);
        if (!TextUtils.isEmpty(accessFloorObsValue.getText()))
            accessObs = String.valueOf(accessFloorObsValue.getText());
        if (indexRadio(hasLidRadio) > -1)
            hasLid = indexRadio(hasLidRadio);
        if (!TextUtils.isEmpty(sidewalkLidDescValue.getText()))
            lidDesc = String.valueOf(sidewalkLidDescValue.getText());
        if (indexRadio(hasAerialObsRadio) > -1)
            hasAerial = indexRadio(hasAerialObsRadio);
        if (!TextUtils.isEmpty(aerialObsDescValue.getText()))
            aerialDesc = String.valueOf(aerialObsDescValue.getText());
        if (indexRadio(sideHasSlopeRadio) > -1) {
            hasSlope = indexRadio(sideHasSlopeRadio);
            if (hasSlope == 0) {
                if (indexRadio(sideReqSlopeRadio) > -1)
                    reqSlope = indexRadio(sideReqSlopeRadio);
            }
        }
        if (indexRadio(payphoneRadio) > -1)
            hasPayphone = indexRadio(payphoneRadio);
        if (!TextUtils.isEmpty(sidewalkObsValue.getText()))
            sidewalkObs = String.valueOf(sidewalkObsValue.getText());
        if (!TextUtils.isEmpty(photoValue.getText()))
            photo = String.valueOf(photoValue.getText());

        return new SidewalkEntryTwo(bundle.getInt(AMBIENT_ID), accessFloor, accessObs, hasSlope, hasAerial, aerialDesc,
                hasLid, lidDesc, consStatus, consDesc, hasPayphone, reqSlope, sidewalkObs, photo);
    }

    private void addObsFieldsToArray() {
        sidewalk2ObsArray.add(accessFloorObsValue);
        sidewalk2ObsArray.add(sidewalkObsValue);
        sidewalk2ObsArray.add(sidewalkLidDescValue);
        sidewalk2ObsArray.add(aerialObsDescValue);
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
        int index = indexRadio(radio);
        if (radio == accessibleFloorRadio) {
            if (index == 0) {
                accessFloorObsField.setVisibility(View.VISIBLE);
            } else {
                accessFloorObsValue.setText(null);
                accessFloorObsField.setVisibility(View.GONE);
            }
        } else if (radio == sideHasSlopeRadio) {
            if (index == 1) {
                addSlope.setVisibility(View.VISIBLE);
                sideReqSlopeHeader.setVisibility(View.GONE);
                sideReqSlopeRadio.clearCheck();
                sideReqSlopeRadio.setVisibility(View.GONE);
            }
            else {
                addSlope.setVisibility(View.GONE);
                sideReqSlopeHeader.setVisibility(View.VISIBLE);
                sideReqSlopeRadio.setVisibility(View.VISIBLE);
            }
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
        }
    }
}