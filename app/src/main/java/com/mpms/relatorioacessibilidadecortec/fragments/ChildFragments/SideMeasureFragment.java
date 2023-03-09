package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.SideMeasureParcel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

import java.util.ArrayList;

public class SideMeasureFragment extends Fragment implements TagInterface, ScrollEditText {

    TextInputLayout sideWidthField, sideFreeSpaceWidthField, sideMeasureObsField, sideTransSlopeField1, sideTransSlopeField2, sideTransSlopeField3,
            sideTransSlopeField4, sideTransSlopeField5, sideTransSlopeField6, directionTileWidthField, alertTileWidthField, tactileFloorObsField;
    TextInputEditText sideWidthValue, sideFreeSpaceWidthValue, sideMeasureObsValue, sideTransSlopeValue1, sideTransSlopeValue2, sideTransSlopeValue3,
            sideTransSlopeValue4, sideTransSlopeValue5, sideTransSlopeValue6, directionTileWidthValue, alertTileWidthValue, tactileFloorObsValue;
    RadioGroup hasTactileFloorRadio, tactileFloorColorRadio;
    TextView sideSlopeMeasureError, sideHasTactileFloorError, tactileFloorColorHeader, tactileFloorColorError, directionTileMeasureHeader, alertTileMeasureHeader;
    MaterialButton saveProceedSidewalk, cancelSidewalk, addSideMeasure;
    ImageButton delSideMeasure;

    int slopeMeasureQnt = 1;

    ArrayList<TextInputEditText> sidewalkObsArray = new ArrayList<>();

    ViewModelEntry modelEntry;

    static Bundle measureBundle;

    public SideMeasureFragment() {
        // Required empty public constructor
    }

    public static SideMeasureFragment newInstance(Bundle bundle) {
        measureBundle = bundle;
        return new SideMeasureFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_side_measure, container, false);

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSideMeasures(view);

        if (measureBundle.getInt(AMBIENT_ID) > 0)
            modelEntry.getSidewalkEntry(measureBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), this::loadSideMeasureData);

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA, this, (key, bundle) -> {
            checkEmptySideMeasureFields(bundle);
            createSideParcel(bundle);
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
        });

    }

    private void createSideParcel(Bundle bundle) {
        Double sidewalkWidth = null, sideFreeSpaceWidth = null, sideTransSlope1 = null, sideTransSlope2 = null, sideTransSlope3 = null, sideTransSlope4 = null,
                sideTransSlope5 = null, sideTransSlope6 = null, specialTileDirectionWidth = null, specialTileAlertWidth = null;
        String sideMeasureObs = null, specialFloorObs = null;
        Integer specialFloorRightColor = null;
        int hasSpecialFloor;

        if (!TextUtils.isEmpty(sideWidthValue.getText()))
            sidewalkWidth = Double.parseDouble(String.valueOf(sideWidthValue.getText()));
        if (!TextUtils.isEmpty(sideFreeSpaceWidthValue.getText()))
            sideFreeSpaceWidth = Double.parseDouble(String.valueOf(sideFreeSpaceWidthValue.getText()));
        if (!TextUtils.isEmpty(sideMeasureObsValue.getText()))
            sideMeasureObs = String.valueOf(sideMeasureObsValue.getText());
        switch (slopeMeasureQnt) {
            case 6:
                if (!TextUtils.isEmpty(sideTransSlopeValue6.getText()))
                    sideTransSlope6 = Double.parseDouble(String.valueOf(sideTransSlopeValue6.getText()));
            case 5:
                if (!TextUtils.isEmpty(sideTransSlopeValue5.getText()))
                    sideTransSlope5 = Double.parseDouble(String.valueOf(sideTransSlopeValue5.getText()));
            case 4:
                if (!TextUtils.isEmpty(sideTransSlopeValue4.getText()))
                    sideTransSlope4 = Double.parseDouble(String.valueOf(sideTransSlopeValue4.getText()));
            case 3:
                if (!TextUtils.isEmpty(sideTransSlopeValue3.getText()))
                    sideTransSlope3 = Double.parseDouble(String.valueOf(sideTransSlopeValue3.getText()));
            case 2:
                if (!TextUtils.isEmpty(sideTransSlopeValue2.getText()))
                    sideTransSlope2 = Double.parseDouble(String.valueOf(sideTransSlopeValue2.getText()));
            case 1:
                if (!TextUtils.isEmpty(sideTransSlopeValue1.getText()))
                    sideTransSlope1 = Double.parseDouble(String.valueOf(sideTransSlopeValue1.getText()));
                break;
        }
        hasSpecialFloor = getCheckSideMeasureRadio(hasTactileFloorRadio);
        if (hasSpecialFloor == 1) {
            specialFloorRightColor = getCheckSideMeasureRadio(tactileFloorColorRadio);
            if (!TextUtils.isEmpty(directionTileWidthValue.getText()))
                specialTileDirectionWidth = Double.parseDouble(String.valueOf(directionTileWidthValue.getText()));
            if (!TextUtils.isEmpty(alertTileWidthValue.getText()))
                specialTileAlertWidth = Double.parseDouble(String.valueOf(alertTileWidthValue.getText()));
            if (!TextUtils.isEmpty(tactileFloorObsValue.getText()))
                specialFloorObs = String.valueOf(directionTileWidthValue);
        }

        SideMeasureParcel parcel = new SideMeasureParcel(sidewalkWidth, sideFreeSpaceWidth, sideMeasureObs, slopeMeasureQnt, sideTransSlope1,
                sideTransSlope2, sideTransSlope3, sideTransSlope4, sideTransSlope5, sideTransSlope6, hasSpecialFloor, specialFloorRightColor,
                specialTileDirectionWidth, specialTileAlertWidth, specialFloorObs);
        bundle.putParcelable(CHILD_PARCEL, Parcels.wrap(parcel));

    }

    private void instantiateSideMeasures(View view) {
        //        TextInputLayout
        sideWidthField = view.findViewById(R.id.sidewalk_width_field);
        sideFreeSpaceWidthField = view.findViewById(R.id.sidewalk_free_space_field);
        sideMeasureObsField = view.findViewById(R.id.sidewalk_measurements_obs_field);
        sideTransSlopeField1 = view.findViewById(R.id.sidewalk_measure_1_field);
        sideTransSlopeField2 = view.findViewById(R.id.sidewalk_measure_2_field);
        sideTransSlopeField3 = view.findViewById(R.id.sidewalk_measure_3_field);
        sideTransSlopeField4 = view.findViewById(R.id.sidewalk_measure_4_field);
        sideTransSlopeField5 = view.findViewById(R.id.sidewalk_measure_5_field);
        sideTransSlopeField6 = view.findViewById(R.id.sidewalk_measure_6_field);
        directionTileWidthField = view.findViewById(R.id.direction_floor_width_field);
        alertTileWidthField = view.findViewById(R.id.alert_floor_width_field);
        tactileFloorObsField = view.findViewById(R.id.sidewalk_special_floor_obs_field);
//        TextInputEditText
        sideWidthValue = view.findViewById(R.id.sidewalk_width_value);
        sideFreeSpaceWidthValue = view.findViewById(R.id.sidewalk_free_space_value);
        sideMeasureObsValue = view.findViewById(R.id.sidewalk_measurements_obs_value);
        sideTransSlopeValue1 = view.findViewById(R.id.sidewalk_measure_1_value);
        sideTransSlopeValue2 = view.findViewById(R.id.sidewalk_measure_2_value);
        sideTransSlopeValue3 = view.findViewById(R.id.sidewalk_measure_3_value);
        sideTransSlopeValue4 = view.findViewById(R.id.sidewalk_measure_4_value);
        sideTransSlopeValue5 = view.findViewById(R.id.sidewalk_measure_5_value);
        sideTransSlopeValue6 = view.findViewById(R.id.sidewalk_measure_6_value);
        directionTileWidthValue = view.findViewById(R.id.direction_floor_width_value);
        alertTileWidthValue = view.findViewById(R.id.alert_floor_width_value);
        tactileFloorObsValue = view.findViewById(R.id.sidewalk_special_floor_obs_value);
//        RadioGroup
        hasTactileFloorRadio = view.findViewById(R.id.radio_sidewalk_special_floor);
        tactileFloorColorRadio = view.findViewById(R.id.special_floor_color_radio);
//        TextView
        sideSlopeMeasureError = view.findViewById(R.id.sidewalk_measure_error);
        sideHasTactileFloorError = view.findViewById(R.id.has_tactile_floor_error);
        tactileFloorColorHeader = view.findViewById(R.id.label_special_floor_color);
        tactileFloorColorError = view.findViewById(R.id.special_floor_color_error);
        directionTileMeasureHeader = view.findViewById(R.id.label_direction_special_floor_size);
        alertTileMeasureHeader = view.findViewById(R.id.label_alert_special_floor_size);
//        MaterialButton
        saveProceedSidewalk = view.findViewById(R.id.save_proceed_sidewalk);
        cancelSidewalk = view.findViewById(R.id.cancel_sidewalk);
        addSideMeasure = view.findViewById(R.id.add_sidewalk_measure_button);
        addSideMeasure.setOnClickListener(this::addFieldListener);
//        ImageButton
        delSideMeasure = view.findViewById(R.id.delete_sidewalk_measure);
        delSideMeasure.setOnClickListener(this::addFieldListener);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listeners
        hasTactileFloorRadio.setOnCheckedChangeListener(this::sideMeasureRadioListener);
        tactileFloorColorRadio.setOnCheckedChangeListener(this::sideMeasureRadioListener);
//        Methods
        addObsFieldsToArray();
        allowObsScroll(sidewalkObsArray);

    }

    private void addFieldListener(View v) {
        if (v == addSideMeasure) {
            if (slopeMeasureQnt < 1) {
                slopeMeasureQnt = 1;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (slopeMeasureQnt == 1) {
                delSideMeasure.setVisibility(View.VISIBLE);
                sideTransSlopeField2.setVisibility(View.VISIBLE);
                slopeMeasureQnt++;
            } else if (slopeMeasureQnt == 2) {
                sideTransSlopeField3.setVisibility(View.VISIBLE);
                slopeMeasureQnt++;
            } else if (slopeMeasureQnt == 3) {
                sideTransSlopeField4.setVisibility(View.VISIBLE);
                slopeMeasureQnt++;
            } else if (slopeMeasureQnt == 4) {
                sideTransSlopeField5.setVisibility(View.VISIBLE);
                slopeMeasureQnt++;
            } else if (slopeMeasureQnt == 5) {
                sideTransSlopeField6.setVisibility(View.VISIBLE);
                slopeMeasureQnt++;
            } else
                Toast.makeText(getContext(), "O limite de medições foi atingido!", Toast.LENGTH_SHORT).show();
        } else if (v == delSideMeasure) {
            if (slopeMeasureQnt < 1) {
                slopeMeasureQnt = 1;
                sideTransSlopeValue2.setText(null);
                sideTransSlopeField2.setVisibility(View.GONE);
                sideTransSlopeValue3.setText(null);
                sideTransSlopeField3.setVisibility(View.GONE);
                sideTransSlopeValue4.setText(null);
                sideTransSlopeField4.setVisibility(View.GONE);
                sideTransSlopeValue5.setText(null);
                sideTransSlopeField5.setVisibility(View.GONE);
                sideTransSlopeValue6.setText(null);
                sideTransSlopeField6.setVisibility(View.GONE);
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (slopeMeasureQnt == 6) {
                sideTransSlopeValue6.setText(null);
                sideTransSlopeField6.setVisibility(View.GONE);
            } else if (slopeMeasureQnt == 5) {
                sideTransSlopeValue5.setText(null);
                sideTransSlopeField5.setVisibility(View.GONE);
            } else if (slopeMeasureQnt == 4) {
                sideTransSlopeValue4.setText(null);
                sideTransSlopeField4.setVisibility(View.GONE);
            } else if (slopeMeasureQnt == 3) {
                sideTransSlopeValue3.setText(null);
                sideTransSlopeField3.setVisibility(View.GONE);
            } else if (slopeMeasureQnt == 2) {
                sideTransSlopeValue2.setText(null);
                sideTransSlopeField2.setVisibility(View.GONE);
                delSideMeasure.setVisibility(View.GONE);
            }
            if (slopeMeasureQnt > 1)
                slopeMeasureQnt--;
        }
    }

    private void sideMeasureRadioListener(RadioGroup radio, int checkedID) {
        int index = radio.indexOfChild(radio.findViewById(checkedID));
        if (radio == hasTactileFloorRadio) {
            if (index == 1) {
                tactileFloorColorHeader.setVisibility(View.VISIBLE);
                tactileFloorColorRadio.setVisibility(View.VISIBLE);
                directionTileMeasureHeader.setVisibility(View.VISIBLE);
                directionTileWidthField.setVisibility(View.VISIBLE);
                alertTileMeasureHeader.setVisibility(View.VISIBLE);
                alertTileWidthField.setVisibility(View.VISIBLE);
            } else {
                tactileFloorColorHeader.setVisibility(View.GONE);
                tactileFloorColorRadio.setVisibility(View.GONE);
                directionTileMeasureHeader.setVisibility(View.GONE);
                directionTileWidthValue.setText(null);
                directionTileWidthField.setVisibility(View.GONE);
                alertTileMeasureHeader.setVisibility(View.GONE);
                alertTileWidthValue.setText(null);
                alertTileWidthField.setVisibility(View.GONE);
            }
        }
    }

    private int getCheckSideMeasureRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private void loadSideMeasureData(SidewalkEntry sidewalk) {
        if (sidewalk.getSidewalkWidth() != null)
            sideWidthValue.setText(String.valueOf(sidewalk.getSidewalkWidth()));
        if (sidewalk.getSideFreeSpaceWidth() != null)
            sideFreeSpaceWidthValue.setText(String.valueOf(sidewalk.getSideFreeSpaceWidth()));
        if (sidewalk.getSideMeasureObs() != null)
            sideMeasureObsValue.setText(sidewalk.getSideMeasureObs());
        if (sidewalk.getSlopeMeasureQnt() != null)
            slopeMeasureQnt = sidewalk.getSlopeMeasureQnt();
        if (slopeMeasureQnt > 1)
            delSideMeasure.setVisibility(View.VISIBLE);

        switch (slopeMeasureQnt) {
            case 6:
                if (sidewalk.getSideTransSlope6() != null) {
                    sideTransSlopeField6.setVisibility(View.VISIBLE);
                    sideTransSlopeValue6.setText(String.valueOf(sidewalk.getSideTransSlope6()));
                }
            case 5:
                if (sidewalk.getSideTransSlope5() != null) {
                    sideTransSlopeField5.setVisibility(View.VISIBLE);
                    sideTransSlopeValue5.setText(String.valueOf(sidewalk.getSideTransSlope5()));
                }
            case 4:
                if (sidewalk.getSideTransSlope4() != null) {
                    sideTransSlopeField4.setVisibility(View.VISIBLE);
                    sideTransSlopeValue4.setText(String.valueOf(sidewalk.getSideTransSlope4()));
                }
            case 3:
                if (sidewalk.getSideTransSlope3() != null) {
                    sideTransSlopeField3.setVisibility(View.VISIBLE);
                    sideTransSlopeValue3.setText(String.valueOf(sidewalk.getSideTransSlope3()));
                }
            case 2:
                if (sidewalk.getSideTransSlope2() != null) {
                    sideTransSlopeField2.setVisibility(View.VISIBLE);
                    sideTransSlopeValue2.setText(String.valueOf(sidewalk.getSideTransSlope2()));
                }
            case 1:
                if (sidewalk.getSideTransSlope1() != null) {
                    sideTransSlopeField1.setVisibility(View.VISIBLE);
                    sideTransSlopeValue1.setText(String.valueOf(sidewalk.getSideTransSlope1()));
                }
                break;
            default:
                slopeMeasureQnt = 1;
                break;
        }


        if (sidewalk.getHasSpecialFloor() != null && sidewalk.getHasSpecialFloor() > -1) {
            hasTactileFloorRadio.check(hasTactileFloorRadio.getChildAt(sidewalk.getHasSpecialFloor()).getId());
            if (sidewalk.getHasSpecialFloor() == 1) {
                if (sidewalk.getSpecialFloorRightColor() != null && sidewalk.getSpecialFloorRightColor() > -1)
                    tactileFloorColorRadio.check(tactileFloorColorRadio.getChildAt(sidewalk.getSpecialFloorRightColor()).getId());
                if (sidewalk.getSpecialTileDirectionWidth() != null)
                    directionTileWidthValue.setText(String.valueOf(sidewalk.getSpecialTileDirectionWidth()));
                if (sidewalk.getSpecialTileAlertWidth() != null)
                    alertTileWidthValue.setText(String.valueOf(sidewalk.getSpecialTileAlertWidth()));
            }
        }

        if (sidewalk.getSpecialFloorObs() != null)
            tactileFloorObsValue.setText(sidewalk.getSpecialFloorObs());

    }

    private void checkEmptySideMeasureFields(Bundle bundle) {
        clearSideMeasureErrors();
        int i = 0;

        if (TextUtils.isEmpty(sideWidthValue.getText())) {
            i++;
            sideWidthField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(sideFreeSpaceWidthValue.getText())) {
            i++;
            sideFreeSpaceWidthField.setError(getString(R.string.req_field_error));
        }

        switch (slopeMeasureQnt) {
            case 6:
                if (TextUtils.isEmpty(sideTransSlopeValue6.getText())) {
                    i++;
                    sideSlopeMeasureError.setVisibility(View.VISIBLE);
                }
            case 5:
                if (TextUtils.isEmpty(sideTransSlopeValue5.getText())) {
                    i++;
                    sideSlopeMeasureError.setVisibility(View.VISIBLE);
                }
            case 4:
                if (TextUtils.isEmpty(sideTransSlopeValue4.getText())) {
                    i++;
                    sideSlopeMeasureError.setVisibility(View.VISIBLE);
                }
            case 3:
                if (TextUtils.isEmpty(sideTransSlopeValue3.getText())) {
                    i++;
                    sideSlopeMeasureError.setVisibility(View.VISIBLE);
                }
            case 2:
                if (TextUtils.isEmpty(sideTransSlopeValue2.getText())) {
                    i++;
                    sideSlopeMeasureError.setVisibility(View.VISIBLE);
                }
            case 1:
                if (TextUtils.isEmpty(sideTransSlopeValue1.getText())) {
                    i++;
                    sideSlopeMeasureError.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }

        if (getCheckSideMeasureRadio(hasTactileFloorRadio) == -1) {
            i++;
            sideHasTactileFloorError.setVisibility(View.VISIBLE);
        } else if (getCheckSideMeasureRadio(hasTactileFloorRadio) == 1) {
            if (getCheckSideMeasureRadio(tactileFloorColorRadio) == -1) {
                i++;
                tactileFloorColorError.setVisibility(View.VISIBLE);
            }
            if (TextUtils.isEmpty(alertTileWidthValue.getText())) {
                i++;
                alertTileWidthField.setError(getString(R.string.req_field_error));
            }
        }

        bundle.putBoolean(CHILD_DATA_COMPLETE, i == 0);
    }

    private void clearSideMeasureErrors() {
        sideWidthField.setErrorEnabled(false);
        sideFreeSpaceWidthField.setErrorEnabled(false);
        sideMeasureObsField.setErrorEnabled(false);
        sideSlopeMeasureError.setVisibility(View.GONE);
        sideHasTactileFloorError.setVisibility(View.GONE);
        tactileFloorColorError.setVisibility(View.GONE);
    }

    private void addObsFieldsToArray() {
        sidewalkObsArray.add(sideMeasureObsValue);
        sidewalkObsArray.add(tactileFloorObsValue);
    }
}