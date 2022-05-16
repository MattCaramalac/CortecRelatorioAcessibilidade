package com.mpms.relatorioacessibilidadecortec.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntryOne;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.ArrayList;

public class SidewalkFragment extends Fragment implements TagInterface {

    TextInputLayout sideLocationField, sideWidthField, sideFreeSpaceWidthField, sideMeasureObsField, sideTransSlopeField1, sideTransSlopeField2, sideTransSlopeField3,
            sideTransSlopeField4, sideTransSlopeField5, sideTransSlopeField6, directionTileLengthField, directionTileWidthField, alertTileLengthField, alertTileWidthField,
            tactileFloorObsField;
    TextInputEditText sideLocationValue, sideWidthValue, sideFreeSpaceWidthValue, sideMeasureObsValue, sideTransSlopeValue1, sideTransSlopeValue2, sideTransSlopeValue3,
            sideTransSlopeValue4, sideTransSlopeValue5, sideTransSlopeValue6, directionTileLengthValue, directionTileWidthValue, alertTileLengthValue, alertTileWidthValue,
            tactileFloorObsValue;
    RadioGroup streetPavementRadio, hasTactileFloorRadio, tactileFloorColorRadio;
    TextView streetPavementError, sideSlopeMeasureError, sideHasTactileFloorError, tactileFloorColorHeader, tactileFloorColorError, directionTileMeasureHeader,
            directionTileMeasureError, alertTileMeasureHeader, alertTileMeasureError;
    MaterialButton saveProceedSidewalk, cancelSidewalk, addSideMeasure;
    ImageButton delSideMeasure;

    int slopeMeasureQnt = 1;

    ArrayList<TextInputEditText> sidewalkObsArray = new ArrayList<>();
    ArrayList<TextInputLayout> sideMeasureArray = new ArrayList<>();

    Bundle sidewalkData;

    int savedRegister = 0;

    ViewModelEntry modelEntry;

    public SidewalkFragment() {
        // Required empty public constructor
    }

    public static SidewalkFragment newInstance() {
        return new SidewalkFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            sidewalkData = new Bundle(this.getArguments());
        else
            sidewalkData = new Bundle();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sidewalk, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSidewalkFragmentViews(view);


        if (sidewalkData.getInt(AMBIENT_ID) > 0) {
            modelEntry.getSidewalkEntry(sidewalkData.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), this::loadSidewalkData);
            savedRegister = 1;
        }

        addSideMeasure.setOnClickListener(v -> {
            if (slopeMeasureQnt < 1) {
                slopeMeasureQnt = 1;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (slopeMeasureQnt < 6) {
                if (slopeMeasureQnt == 1)
                    delSideMeasure.setVisibility(View.VISIBLE);
                sideMeasureArray.get(slopeMeasureQnt).setVisibility(View.VISIBLE);
                slopeMeasureQnt++;
            } else
                Toast.makeText(getContext(), "O limite de medições foi atingido!", Toast.LENGTH_SHORT).show();
        });

        delSideMeasure.setOnClickListener(v -> {
            if (slopeMeasureQnt > 1) {
                sideMeasureArray.get(slopeMeasureQnt - 1).getEditText().setText(null);
                sideMeasureArray.get(slopeMeasureQnt - 1).setVisibility(View.GONE);
                slopeMeasureQnt--;
                if (slopeMeasureQnt == 1)
                    delSideMeasure.setVisibility(View.GONE);
            }
        });

        cancelSidewalk.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack(InspectionActivity.SIDEWALK_LIST, 0));

        saveProceedSidewalk.setOnClickListener(v -> {
            if (checkEmptySidewalkFields()) {
                if (sidewalkData.getInt(AMBIENT_ID) > 0) {
                    ViewModelEntry.updateSidewalkOne(updateSidewalkOne(sidewalkData));
                } else if (sidewalkData.getInt(AMBIENT_ID) == 0) {
                    ViewModelEntry.insertSidewalkEntry(newSidewalk(sidewalkData));
                } else {
                    return;
                }
                callNextSidewalkFrag(sidewalkData);
            }
        });


    }

    private void callNextSidewalkFrag(Bundle bundle) {
        SidewalkFragmentTwo sideTwo = SidewalkFragmentTwo.newInstance();
        sideTwo.setArguments(bundle);
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, sideTwo).addToBackStack(null).commit();
    }

    private void instantiateSidewalkFragmentViews(View view) {
//        TextInputLayout
        sideLocationField = view.findViewById(R.id.sidewalk_location_field);
        sideWidthField = view.findViewById(R.id.sidewalk_width_field);
        sideFreeSpaceWidthField = view.findViewById(R.id.sidewalk_free_space_field);
        sideMeasureObsField = view.findViewById(R.id.sidewalk_measurements_obs_field);
        sideTransSlopeField1 = view.findViewById(R.id.sidewalk_measure_1_field);
        sideTransSlopeField2 = view.findViewById(R.id.sidewalk_measure_2_field);
        sideTransSlopeField3 = view.findViewById(R.id.sidewalk_measure_3_field);
        sideTransSlopeField4 = view.findViewById(R.id.sidewalk_measure_4_field);
        sideTransSlopeField5 = view.findViewById(R.id.sidewalk_measure_5_field);
        sideTransSlopeField6 = view.findViewById(R.id.sidewalk_measure_6_field);
        directionTileLengthField = view.findViewById(R.id.direction_floor_length_field);
        directionTileWidthField = view.findViewById(R.id.direction_floor_width_field);
        alertTileLengthField = view.findViewById(R.id.alert_floor_length_field);
        alertTileWidthField = view.findViewById(R.id.alert_floor_width_field);
        tactileFloorObsField = view.findViewById(R.id.sidewalk_special_floor_obs_field);
//        TextInputEditText
        sideLocationValue = view.findViewById(R.id.sidewalk_location_value);
        sideWidthValue = view.findViewById(R.id.sidewalk_width_value);
        sideFreeSpaceWidthValue = view.findViewById(R.id.sidewalk_free_space_value);
        sideMeasureObsValue = view.findViewById(R.id.sidewalk_measurements_obs_value);
        sideTransSlopeValue1 = view.findViewById(R.id.sidewalk_measure_1_value);
        sideTransSlopeValue2 = view.findViewById(R.id.sidewalk_measure_2_value);
        sideTransSlopeValue3 = view.findViewById(R.id.sidewalk_measure_3_value);
        sideTransSlopeValue4 = view.findViewById(R.id.sidewalk_measure_4_value);
        sideTransSlopeValue5 = view.findViewById(R.id.sidewalk_measure_5_value);
        sideTransSlopeValue6 = view.findViewById(R.id.sidewalk_measure_6_value);
        directionTileLengthValue = view.findViewById(R.id.direction_floor_length_value);
        directionTileWidthValue = view.findViewById(R.id.direction_floor_width_value);
        alertTileLengthValue = view.findViewById(R.id.alert_floor_length_value);
        alertTileWidthValue = view.findViewById(R.id.alert_floor_width_value);
        tactileFloorObsValue = view.findViewById(R.id.sidewalk_special_floor_obs_value);
//        RadioGroup
        streetPavementRadio = view.findViewById(R.id.street_pavement_radio);
        hasTactileFloorRadio = view.findViewById(R.id.radio_sidewalk_special_floor);
        tactileFloorColorRadio = view.findViewById(R.id.special_floor_color_radio);
//        TextView
        streetPavementError = view.findViewById(R.id.street_pavement_error);
        sideSlopeMeasureError = view.findViewById(R.id.sidewalk_measure_error);
        sideHasTactileFloorError = view.findViewById(R.id.has_tactile_floor_error);
        tactileFloorColorHeader = view.findViewById(R.id.label_special_floor_color);
        tactileFloorColorError = view.findViewById(R.id.special_floor_color_error);
        directionTileMeasureHeader = view.findViewById(R.id.label_direction_special_floor_size);
        directionTileMeasureError = view.findViewById(R.id.direction_floor_measurements_error);
        alertTileMeasureHeader = view.findViewById(R.id.label_alert_special_floor_size);
        alertTileMeasureError = view.findViewById(R.id.alert_floor_measurements_error);
//        MaterialButton
        saveProceedSidewalk = view.findViewById(R.id.save_proceed_sidewalk);
        cancelSidewalk = view.findViewById(R.id.cancel_sidewalk);
        addSideMeasure = view.findViewById(R.id.add_sidewalk_measure_button);
//        ImageButton
        delSideMeasure = view.findViewById(R.id.delete_sidewalk_measure);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listeners
        hasTactileFloorRadio.setOnCheckedChangeListener(this::sidewalkRadioListener);
        tactileFloorColorRadio.setOnCheckedChangeListener(this::sidewalkRadioListener);
//        Methods
        allowSidewalkObsScroll();
        sideMeasureArray.add(sideTransSlopeField1);
        sideMeasureArray.add(sideTransSlopeField2);
        sideMeasureArray.add(sideTransSlopeField3);
        sideMeasureArray.add(sideTransSlopeField4);
        sideMeasureArray.add(sideTransSlopeField5);
        sideMeasureArray.add(sideTransSlopeField6);
    }

    private void loadSidewalkData(SidewalkEntry sidewalk) {
        if (sidewalk.getSidewalkLocation() != null)
            sideLocationValue.setText(sidewalk.getSidewalkLocation());
        if (sidewalk.getStreetPavement() != null && sidewalk.getStreetPavement() > -1)
            streetPavementRadio.check(streetPavementRadio.getChildAt(sidewalk.getStreetPavement()).getId());
        if (sidewalk.getSidewalkWidth() != null)
            sideWidthValue.setText(String.valueOf(sidewalk.getSidewalkWidth()));
        if (sidewalk.getSideFreeSpaceWidth() != null)
            sideFreeSpaceWidthValue.setText(String.valueOf(sidewalk.getSideFreeSpaceWidth()));
        if (sidewalk.getSideMeasureObs() != null)
            sideMeasureObsValue.setText(sidewalk.getSideMeasureObs());
        if (sidewalk.getSlopeMeasureQnt() != null)
            slopeMeasureQnt = sidewalk.getSlopeMeasureQnt();

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
                if (sidewalk.getSpecialTileDirectionLength() != null)
                    directionTileLengthValue.setText(String.valueOf(sidewalk.getSpecialTileDirectionLength()));
                if (sidewalk.getSpecialTileDirectionWidth() != null)
                    directionTileWidthValue.setText(String.valueOf(sidewalk.getSpecialTileDirectionWidth()));
                if (sidewalk.getSpecialTileAlertLength() != null)
                    alertTileLengthValue.setText(String.valueOf(sidewalk.getSpecialTileAlertLength()));
                if (sidewalk.getSpecialTileAlertWidth() != null)
                    alertTileWidthValue.setText(String.valueOf(sidewalk.getSpecialTileAlertWidth()));
            }
        }

        if (sidewalk.getSpecialFloorObs() != null)
            tactileFloorObsValue.setText(sidewalk.getSpecialFloorObs());

    }

    private void sidewalkRadioListener(RadioGroup radio, int checkedID) {
        int index = radio.indexOfChild(radio.findViewById(checkedID));
        if (radio == hasTactileFloorRadio) {
            if (index == 1) {
                tactileFloorColorHeader.setVisibility(View.VISIBLE);
                tactileFloorColorRadio.setVisibility(View.VISIBLE);
                directionTileMeasureHeader.setVisibility(View.VISIBLE);
                directionTileLengthField.setVisibility(View.VISIBLE);
                directionTileWidthField.setVisibility(View.VISIBLE);
                alertTileMeasureHeader.setVisibility(View.VISIBLE);
                alertTileLengthField.setVisibility(View.VISIBLE);
                alertTileWidthField.setVisibility(View.VISIBLE);
            } else {
                tactileFloorColorHeader.setVisibility(View.GONE);
                tactileFloorColorRadio.setVisibility(View.GONE);
                directionTileMeasureHeader.setVisibility(View.GONE);
                directionTileLengthValue.setText(null);
                directionTileLengthField.setVisibility(View.GONE);
                directionTileWidthValue.setText(null);
                directionTileWidthField.setVisibility(View.GONE);
                alertTileMeasureHeader.setVisibility(View.GONE);
                alertTileLengthValue.setText(null);
                alertTileLengthField.setVisibility(View.GONE);
                alertTileWidthValue.setText(null);
                alertTileWidthField.setVisibility(View.GONE);
            }
        }
    }

    private int getCheckedSidewalkRadioButton(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private boolean checkEmptySidewalkFields() {
        clearSidewalkEmptyFieldErrors();
        int i = 0;

        if (TextUtils.isEmpty(sideLocationValue.getText())) {
            i++;
            sideLocationField.setError(getString(R.string.blank_field_error));
        }

        if (getCheckedSidewalkRadioButton(streetPavementRadio) == -1) {
            i++;
            streetPavementError.setVisibility(View.VISIBLE);
        } else if (getCheckedSidewalkRadioButton(streetPavementRadio) == 0) {
            if (TextUtils.isEmpty(sideMeasureObsValue.getText())) {
                i++;
                sideMeasureObsField.setError(getString(R.string.blank_field_error));
            }
        } else {
            if (TextUtils.isEmpty(sideWidthValue.getText())) {
                i++;
                sideWidthField.setError(getString(R.string.blank_field_error));
            }
            if (TextUtils.isEmpty(sideFreeSpaceWidthValue.getText())) {
                i++;
                sideFreeSpaceWidthField.setError(getString(R.string.blank_field_error));
            }
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

        if (getCheckedSidewalkRadioButton(hasTactileFloorRadio) == -1) {
            i++;
            sideHasTactileFloorError.setVisibility(View.VISIBLE);
        } else if (getCheckedSidewalkRadioButton(hasTactileFloorRadio) == 1) {
            if (getCheckedSidewalkRadioButton(tactileFloorColorRadio) == -1) {
                i++;
                tactileFloorColorError.setVisibility(View.VISIBLE);
            }
            if (TextUtils.isEmpty(directionTileLengthValue.getText())) {
                i++;
                directionTileLengthField.setError(getString(R.string.blank_field_error));
            }
            if (TextUtils.isEmpty(directionTileWidthValue.getText())) {
                i++;
                directionTileWidthField.setError(getString(R.string.blank_field_error));
            }
            if (TextUtils.isEmpty(alertTileLengthValue.getText())) {
                i++;
                alertTileLengthField.setError(getString(R.string.blank_field_error));
            }
            if (TextUtils.isEmpty(alertTileWidthValue.getText())) {
                i++;
                alertTileWidthField.setError(getString(R.string.blank_field_error));
            }
        }

        return i == 0;
    }

    private void clearSidewalkEmptyFieldErrors() {
        sideLocationField.setErrorEnabled(false);
        sideWidthField.setErrorEnabled(false);
        sideFreeSpaceWidthField.setErrorEnabled(false);
        sideMeasureObsField.setErrorEnabled(false);
        streetPavementError.setVisibility(View.GONE);
        sideSlopeMeasureError.setVisibility(View.GONE);
        sideHasTactileFloorError.setVisibility(View.GONE);
        tactileFloorColorError.setVisibility(View.GONE);
        directionTileMeasureError.setVisibility(View.GONE);
        alertTileMeasureError.setVisibility(View.GONE);
    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    private void addObsFieldsToArray() {
        sidewalkObsArray.add(sideMeasureObsValue);
        sidewalkObsArray.add(tactileFloorObsValue);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowSidewalkObsScroll() {
        addObsFieldsToArray();
        for (TextInputEditText obsScroll : sidewalkObsArray) {
            obsScroll.setOnTouchListener(this::scrollingField);
        }
    }





    private SidewalkEntry newSidewalk(Bundle bundle) {
        String sideLocale = null, sideMeasureObs = null, tactFloorObs = null;
        Integer streetPavement = null, hasTactFloor = null, tactFloorColor = null;
        Double sideWidth = null, sideFSpaceWidth = null, sideSlope1 = null, sideSlope2 = null, sideSlope3 = null, sideSlope4 = null, sideSlope5 = null,
                sideSlope6 = null, tacTileDirLength = null, tacTileDirWidth = null, tacTileAlertLength = null, tacTileAlertWidth = null;

        if (!TextUtils.isEmpty(sideLocationValue.getText()))
            sideLocale = String.valueOf(sideLocationValue.getText());
        if (getCheckedSidewalkRadioButton(streetPavementRadio) != -1)
            streetPavement = getCheckedSidewalkRadioButton(streetPavementRadio);
        if (!TextUtils.isEmpty(sideWidthValue.getText()))
            sideWidth = Double.parseDouble(String.valueOf(sideWidthValue.getText()));
        if (!TextUtils.isEmpty(sideFreeSpaceWidthValue.getText()))
            sideFSpaceWidth = Double.parseDouble(String.valueOf(sideFreeSpaceWidthValue.getText()));
        if (!TextUtils.isEmpty(sideMeasureObsValue.getText()))
            sideMeasureObs = String.valueOf(sideMeasureObsValue.getText());
        switch (slopeMeasureQnt) {
            case 6:
                if (!TextUtils.isEmpty(sideTransSlopeValue6.getText()))
                    sideSlope6 = Double.parseDouble(String.valueOf(sideTransSlopeValue6.getText()));
            case 5:
                if (!TextUtils.isEmpty(sideTransSlopeValue5.getText()))
                    sideSlope5 = Double.parseDouble(String.valueOf(sideTransSlopeValue5.getText()));
            case 4:
                if (!TextUtils.isEmpty(sideTransSlopeValue4.getText()))
                    sideSlope4 = Double.parseDouble(String.valueOf(sideTransSlopeValue4.getText()));
            case 3:
                if (!TextUtils.isEmpty(sideTransSlopeValue3.getText()))
                    sideSlope3 = Double.parseDouble(String.valueOf(sideTransSlopeValue3.getText()));
            case 2:
                if (!TextUtils.isEmpty(sideTransSlopeValue2.getText()))
                    sideSlope2 = Double.parseDouble(String.valueOf(sideTransSlopeValue2.getText()));
            case 1:
                if (!TextUtils.isEmpty(sideTransSlopeValue1.getText()))
                    sideSlope1 = Double.parseDouble(String.valueOf(sideTransSlopeValue1.getText()));
                break;
            default:
                break;
        }

        if (getCheckedSidewalkRadioButton(hasTactileFloorRadio) != -1) {
            hasTactFloor = getCheckedSidewalkRadioButton(hasTactileFloorRadio);
            if (hasTactFloor == 1) {
                if (getCheckedSidewalkRadioButton(tactileFloorColorRadio) != -1)
                    tactFloorColor = getCheckedSidewalkRadioButton(tactileFloorColorRadio);
                if (!TextUtils.isEmpty(directionTileLengthValue.getText()))
                    tacTileDirLength = Double.parseDouble(String.valueOf(directionTileLengthValue.getText()));
                if (!TextUtils.isEmpty(directionTileWidthValue.getText()))
                    tacTileDirWidth = Double.parseDouble(String.valueOf(directionTileWidthValue.getText()));
                if (!TextUtils.isEmpty(alertTileLengthValue.getText()))
                    tacTileAlertLength = Double.parseDouble(String.valueOf(alertTileLengthValue.getText()));
                if (!TextUtils.isEmpty(alertTileWidthValue.getText()))
                    tacTileAlertWidth = Double.parseDouble(String.valueOf(alertTileWidthValue.getText()));
            }
        }

        if (!TextUtils.isEmpty(tactileFloorObsValue.getText()))
            tactFloorObs = String.valueOf(tactileFloorObsValue.getText());

        return new SidewalkEntry(bundle.getInt(BLOCK_ID), sideLocale, streetPavement, sideWidth, sideFSpaceWidth, sideMeasureObs, slopeMeasureQnt,
                sideSlope1, sideSlope2, sideSlope3, sideSlope4, sideSlope5, sideSlope6, hasTactFloor, tactFloorColor, tacTileDirLength, tacTileDirWidth, tacTileAlertLength,
                tacTileAlertWidth, tactFloorObs, null, null, null,null, null, null, null,
                null, null, null, null, null, null);
    }

    private SidewalkEntryOne updateSidewalkOne(Bundle bundle) {
        String sideLocale = null, sideMeasureObs = null, tactFloorObs = null;
        Integer streetPavement = null, hasTactFloor = null, tactFloorColor = null;
        Double sideWidth = null, sideFSpaceWidth = null, sideSlope1 = null, sideSlope2 = null, sideSlope3 = null, sideSlope4 = null, sideSlope5 = null,
                sideSlope6 = null, tacTileDirLength = null, tacTileDirWidth = null, tacTileAlertLength = null, tacTileAlertWidth = null;

        if (!TextUtils.isEmpty(sideLocationValue.getText()))
            sideLocale = String.valueOf(sideLocationValue.getText());
        if (getCheckedSidewalkRadioButton(streetPavementRadio) != -1)
            streetPavement = getCheckedSidewalkRadioButton(streetPavementRadio);
        if (!TextUtils.isEmpty(sideWidthValue.getText()))
            sideWidth = Double.parseDouble(String.valueOf(sideWidthValue.getText()));
        if (!TextUtils.isEmpty(sideFreeSpaceWidthValue.getText()))
            sideFSpaceWidth = Double.parseDouble(String.valueOf(sideFreeSpaceWidthValue.getText()));
        if (!TextUtils.isEmpty(sideMeasureObsValue.getText()))
            sideMeasureObs = String.valueOf(sideMeasureObsValue.getText());
        switch (slopeMeasureQnt) {
            case 6:
                if (!TextUtils.isEmpty(sideTransSlopeValue6.getText()))
                    sideSlope6 = Double.parseDouble(String.valueOf(sideTransSlopeValue6.getText()));
            case 5:
                if (!TextUtils.isEmpty(sideTransSlopeValue5.getText()))
                    sideSlope5 = Double.parseDouble(String.valueOf(sideTransSlopeValue5.getText()));
            case 4:
                if (!TextUtils.isEmpty(sideTransSlopeValue4.getText()))
                    sideSlope4 = Double.parseDouble(String.valueOf(sideTransSlopeValue4.getText()));
            case 3:
                if (!TextUtils.isEmpty(sideTransSlopeValue3.getText()))
                    sideSlope3 = Double.parseDouble(String.valueOf(sideTransSlopeValue3.getText()));
            case 2:
                if (!TextUtils.isEmpty(sideTransSlopeValue2.getText()))
                    sideSlope2 = Double.parseDouble(String.valueOf(sideTransSlopeValue2.getText()));
            case 1:
                if (!TextUtils.isEmpty(sideTransSlopeValue1.getText()))
                    sideSlope1 = Double.parseDouble(String.valueOf(sideTransSlopeValue1.getText()));
                break;
            default:
                break;
        }

        if (getCheckedSidewalkRadioButton(hasTactileFloorRadio) != -1) {
            hasTactFloor = getCheckedSidewalkRadioButton(hasTactileFloorRadio);
            if (hasTactFloor == 1) {
                if (getCheckedSidewalkRadioButton(tactileFloorColorRadio) != -1)
                    tactFloorColor = getCheckedSidewalkRadioButton(tactileFloorColorRadio);
                if (!TextUtils.isEmpty(directionTileLengthValue.getText()))
                    tacTileDirLength = Double.parseDouble(String.valueOf(directionTileLengthValue.getText()));
                if (!TextUtils.isEmpty(directionTileWidthValue.getText()))
                    tacTileDirWidth = Double.parseDouble(String.valueOf(directionTileWidthValue.getText()));
                if (!TextUtils.isEmpty(alertTileLengthValue.getText()))
                    tacTileAlertLength = Double.parseDouble(String.valueOf(alertTileLengthValue.getText()));
                if (!TextUtils.isEmpty(alertTileWidthValue.getText()))
                    tacTileAlertWidth = Double.parseDouble(String.valueOf(alertTileWidthValue.getText()));
            }
        }

        if (!TextUtils.isEmpty(tactileFloorObsValue.getText()))
            tactFloorObs = String.valueOf(tactileFloorObsValue.getText());

        return new SidewalkEntryOne(bundle.getInt(AMBIENT_ID), sideLocale, streetPavement, sideWidth, sideFSpaceWidth, sideMeasureObs, slopeMeasureQnt,
                sideSlope1, sideSlope2, sideSlope3, sideSlope4, sideSlope5, sideSlope6, hasTactFloor, tactFloorColor, tacTileDirLength, tacTileDirWidth, tacTileAlertLength,
                tacTileAlertWidth, tactFloorObs);

    }

    private void resetInitialLayout() {
        clearSidewalkFields();
        slopeMeasureQnt = 1;
        sideTransSlopeField2.setVisibility(View.GONE);
        sideTransSlopeField3.setVisibility(View.GONE);
        sideTransSlopeField4.setVisibility(View.GONE);
        sideTransSlopeField5.setVisibility(View.GONE);
        sideTransSlopeField6.setVisibility(View.GONE);
        tactileFloorColorHeader.setVisibility(View.GONE);
        tactileFloorColorRadio.setVisibility(View.GONE);
        directionTileMeasureHeader.setVisibility(View.GONE);
        alertTileMeasureHeader.setVisibility(View.GONE);
    }

    private void clearSidewalkFields() {
        sideLocationValue.setText(null);
        sideWidthValue.setText(null);
        sideFreeSpaceWidthValue.setText(null);
        sideMeasureObsValue.setText(null);
        sideTransSlopeValue1.setText(null);
        sideTransSlopeValue2.setText(null);
        sideTransSlopeValue3.setText(null);
        sideTransSlopeValue4.setText(null);
        sideTransSlopeValue5.setText(null);
        sideTransSlopeValue6.setText(null);
        directionTileLengthValue.setText(null);
        directionTileWidthValue.setText(null);
        alertTileLengthValue.setText(null);
        alertTileWidthValue.setText(null);
        tactileFloorObsValue.setText(null);

        streetPavementRadio.clearCheck();
        hasTactileFloorRadio.clearCheck();
        tactileFloorColorRadio.clearCheck();

    }
}