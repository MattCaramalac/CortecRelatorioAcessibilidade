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
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkSlopeEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import java.util.ArrayList;

public class SidewalkSlopeFragment extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

    TextInputLayout slopeLocaleField, slopeWidthField, longMeasureField1, longMeasureField2, longMeasureField3, longMeasureField4,
            leftMeasureField1, leftMeasureField2, leftMeasureField3, leftMeasureField4, rightMeasureField1, rightMeasureField2,
            rightMeasureField3, rightMeasureField4, tactileFloorObsField, accessFloorObsField, slopeObsField, slopeStreetObsField,
            photosField;
    TextInputEditText slopeLocaleValue, slopeWidthValue, longMeasureValue1, longMeasureValue2, longMeasureValue3, longMeasureValue4,
            leftMeasureValue1, leftMeasureValue2, leftMeasureValue3, leftMeasureValue4, rightMeasureValue1, rightMeasureValue2,
            rightMeasureValue3, rightMeasureValue4, tactileFloorObsValue, accessFloorObsValue, slopeObsValue, slopeStreetObsValue,
            photosValue;
    MaterialButton longButton, leftButton, rightButton, cancelSlope, saveSlope;
    ImageButton deleteLong, deleteLeft, deleteRight;
    RadioGroup hasTactileFloor, hasLeftWing, hasRightWing, slopeIsAccessible, slopeNearPCD, slopeStreetJunction;
    TextView tactileFloorError, longitudinalError, leftWingRadioError, leftWingHeader, leftWingError, rightWingRadioError, rightWingHeader,
            rightWingError, slopeAccessFloorRadioError, streetSlopeError;

    ArrayList<TextInputEditText> eText = new ArrayList<>();

    Bundle slopeBundle;

    ViewModelEntry modelEntry;

    int longCounter = 1;
    int leftCounter = 0;
    int rightCounter = 0;

    public SidewalkSlopeFragment() {
        // Required empty public constructor
    }

    public static SidewalkSlopeFragment newInstance() {
        return new SidewalkSlopeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            slopeBundle = new Bundle(this.getArguments());
        else
            slopeBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sidewalk_slope, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSlopeViews(view);

        if (slopeBundle.getInt(SIDEWALK_SLOPE_ID) > 0)
            modelEntry.getSidewalkSlopeEntry(slopeBundle.getInt(SIDEWALK_SLOPE_ID)).observe(getViewLifecycleOwner(), this::loadSlopeData);

        cancelSlope.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        saveSlope.setOnClickListener(v -> {
            if (checkSideSlopeEmptyFields())
                saveSideSlopeEntry(slopeBundle);
            else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });
    }

    private void saveSideSlopeEntry(Bundle bundle) {
        SidewalkSlopeEntry newSlopeEntry = newSlope(bundle);
        if (bundle.getInt(SIDEWALK_SLOPE_ID) > 0) {
            newSlopeEntry.setSideSlopeID(bundle.getInt(SIDEWALK_SLOPE_ID));
            ViewModelEntry.updateSidewalkSlope(newSlopeEntry);
            Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        } else if (bundle.getInt(SIDEWALK_SLOPE_ID) == 0) {
            ViewModelEntry.insertSidewalkSlopeEntry(newSlopeEntry);
            Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
            clearSideSlopeFields();
        } else {
            bundle.putInt(SIDEWALK_SLOPE_ID, 0);
            Toast.makeText(getContext(), getString(R.string.register_error_try_again), Toast.LENGTH_SHORT).show();
        }
    }

    private void instantiateSlopeViews(View view) {
//        TextInputLayout
        slopeLocaleField = view.findViewById(R.id.slope_locale_field);
        slopeWidthField = view.findViewById(R.id.slope_width_field);
        longMeasureField1 = view.findViewById(R.id.slope_longitudinal_1_field);
        longMeasureField2 = view.findViewById(R.id.slope_longitudinal_2_field);
        longMeasureField3 = view.findViewById(R.id.slope_longitudinal_3_field);
        longMeasureField4 = view.findViewById(R.id.slope_longitudinal_4_field);
        leftMeasureField1 = view.findViewById(R.id.left_wing_1_field);
        leftMeasureField2 = view.findViewById(R.id.left_wing_2_field);
        leftMeasureField3 = view.findViewById(R.id.left_wing_3_field);
        leftMeasureField4 = view.findViewById(R.id.left_wing_4_field);
        rightMeasureField1 = view.findViewById(R.id.right_wing_1_field);
        rightMeasureField2 = view.findViewById(R.id.right_wing_2_field);
        rightMeasureField3 = view.findViewById(R.id.right_wing_3_field);
        rightMeasureField4 = view.findViewById(R.id.right_wing_4_field);
        tactileFloorObsField = view.findViewById(R.id.slope_tactile_floor_obs_field);
        accessFloorObsField = view.findViewById(R.id.slope_accessible_floor_obs_field);
        slopeObsField = view.findViewById(R.id.slope_obs_field);
        slopeStreetObsField = view.findViewById(R.id.street_slope_obs_field);
        photosField = view.findViewById(R.id.side_slope_photos_field);
//        TextInputEditText
        slopeLocaleValue = view.findViewById(R.id.slope_locale_value);
        slopeWidthValue = view.findViewById(R.id.slope_width_value);
        longMeasureValue1 = view.findViewById(R.id.slope_longitudinal_1_value);
        longMeasureValue2 = view.findViewById(R.id.slope_longitudinal_2_value);
        longMeasureValue3 = view.findViewById(R.id.slope_longitudinal_3_value);
        longMeasureValue4 = view.findViewById(R.id.slope_longitudinal_4_value);
        leftMeasureValue1 = view.findViewById(R.id.left_wing_1_value);
        leftMeasureValue2 = view.findViewById(R.id.left_wing_2_value);
        leftMeasureValue3 = view.findViewById(R.id.left_wing_3_value);
        leftMeasureValue4 = view.findViewById(R.id.left_wing_4_value);
        rightMeasureValue1 = view.findViewById(R.id.right_wing_1_value);
        rightMeasureValue2 = view.findViewById(R.id.right_wing_2_value);
        rightMeasureValue3 = view.findViewById(R.id.right_wing_3_value);
        rightMeasureValue4 = view.findViewById(R.id.right_wing_4_value);
        tactileFloorObsValue = view.findViewById(R.id.slope_tactile_floor_obs_value);
        accessFloorObsValue = view.findViewById(R.id.slope_accessible_floor_obs_value);
        slopeObsValue = view.findViewById(R.id.slope_obs_value);
        slopeStreetObsValue = view.findViewById(R.id.street_slope_obs_value);
        photosValue = view.findViewById(R.id.side_slope_photos_value);
//        MaterialButtons
        longButton = view.findViewById(R.id.add_longitudinal_measurement);
        leftButton = view.findViewById(R.id.add_left_wing_measurement);
        rightButton = view.findViewById(R.id.add_right_wing_measurement);
        cancelSlope = view.findViewById(R.id.cancel_sidewalk_slope);
        saveSlope = view.findViewById(R.id.save_sidewalk_slope);
//        ImageButton
        deleteLong = view.findViewById(R.id.delete_long_measurement);
        deleteLeft = view.findViewById(R.id.delete_left_measurement);
        deleteRight = view.findViewById(R.id.delete_right_measurement);
//        RadioGroup
        hasTactileFloor = view.findViewById(R.id.slope_has_tactile_floor_radio);
        hasLeftWing = view.findViewById(R.id.has_left_wing_radio);
        hasRightWing = view.findViewById(R.id.has_right_wing_radio);
        slopeIsAccessible = view.findViewById(R.id.slope_has_accessible_floor_radio);
        slopeStreetJunction = view.findViewById(R.id.street_slope_radio);
//        TextView
        tactileFloorError = view.findViewById(R.id.slope_has_tactile_floor_error);
        longitudinalError = view.findViewById(R.id.longitudinal_fields_error);
        leftWingHeader = view.findViewById(R.id.left_wing_inclination_header);
        leftWingError = view.findViewById(R.id.left_wing_fields_error);
        rightWingHeader = view.findViewById(R.id.right_wing_inclination_header);
        rightWingError = view.findViewById(R.id.right_wing_fields_error);
        leftWingRadioError = view.findViewById(R.id.has_left_wing_header_error);
        rightWingRadioError = view.findViewById(R.id.has_right_wing_error);
        slopeAccessFloorRadioError = view.findViewById(R.id.slope_has_accessible_floor_error);
        streetSlopeError = view.findViewById(R.id.slope_street_junction_error);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());

//        addLayoutsToArrays();
        hasRightWing.setOnCheckedChangeListener(this::radioListener);
        hasLeftWing.setOnCheckedChangeListener(this::radioListener);
        slopeIsAccessible.setOnCheckedChangeListener(this::radioListener);
        longButton.setOnClickListener(this::addFieldClickListener);
        leftButton.setOnClickListener(this::addFieldClickListener);
        rightButton.setOnClickListener(this::addFieldClickListener);
        deleteLeft.setOnClickListener(this::addFieldClickListener);
        deleteLong.setOnClickListener(this::addFieldClickListener);
        deleteRight.setOnClickListener(this::addFieldClickListener);
        editTextFields();
        allowObsScroll(eText);
    }

    private void editTextFields() {
        eText.add(tactileFloorObsValue);
        eText.add(accessFloorObsValue);
        eText.add(slopeObsValue);
        eText.add(slopeStreetObsValue);
    }

    private void addFieldClickListener(View v) {
        if (v == longButton) {
            if (longCounter < 1) {
                longCounter = 1;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (longCounter == 1) {
                deleteLong.setVisibility(View.VISIBLE);
                longMeasureField2.setVisibility(View.VISIBLE);
                longCounter++;
            } else if (longCounter == 2) {
                longMeasureField3.setVisibility(View.VISIBLE);
                longCounter++;
            } else if (longCounter == 3) {
                longMeasureField4.setVisibility(View.VISIBLE);
                longCounter++;
            } else
                Toast.makeText(getContext(), "O limite de medições foi atingido!", Toast.LENGTH_SHORT).show();
        } else if (v == deleteLong) {
            if (longCounter < 1) {
                longCounter = 1;
                deleteLong.setVisibility(View.GONE);
                longMeasureValue2.setText(null);
                longMeasureField2.setVisibility(View.GONE);
                longMeasureValue3.setText(null);
                longMeasureField3.setVisibility(View.GONE);
                longMeasureValue4.setText(null);
                longMeasureField4.setVisibility(View.GONE);
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (longCounter == 4) {
                longMeasureValue4.setText(null);
                longMeasureField4.setVisibility(View.GONE);
            } else if (longCounter == 3) {
                longMeasureValue3.setText(null);
                longMeasureField3.setVisibility(View.GONE);
            } else if (longCounter == 2) {
                longMeasureValue2.setText(null);
                longMeasureField2.setVisibility(View.GONE);
                deleteLong.setVisibility(View.GONE);
            }
            if (longCounter > 1)
                longCounter--;
        } else if (v == leftButton) {
            if (leftCounter < 1) {
                leftCounter = 1;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (leftCounter == 1) {
                deleteLeft.setVisibility(View.VISIBLE);
                leftMeasureField2.setVisibility(View.VISIBLE);
                leftCounter++;
            } else if (leftCounter == 2) {
                leftMeasureField3.setVisibility(View.VISIBLE);
                leftCounter++;
            } else if (leftCounter == 3) {
                leftMeasureField4.setVisibility(View.VISIBLE);
                leftCounter++;
            } else
                Toast.makeText(getContext(), "O limite de medições foi atingido!", Toast.LENGTH_SHORT).show();
        } else if (v == deleteLeft) {
            if (leftCounter < 1) {
                leftCounter = 1;
                deleteLeft.setVisibility(View.GONE);
                leftMeasureValue2.setText(null);
                leftMeasureField2.setVisibility(View.GONE);
                leftMeasureValue3.setText(null);
                leftMeasureField3.setVisibility(View.GONE);
                leftMeasureValue4.setText(null);
                leftMeasureField4.setVisibility(View.GONE);
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (leftCounter == 4) {
                leftMeasureValue4.setText(null);
                leftMeasureField4.setVisibility(View.GONE);
            } else if (leftCounter == 3) {
                leftMeasureValue3.setText(null);
                leftMeasureField3.setVisibility(View.GONE);
            } else if (leftCounter == 2) {
                leftMeasureValue2.setText(null);
                leftMeasureField2.setVisibility(View.GONE);
                deleteLeft.setVisibility(View.GONE);
            }
            if (leftCounter > 1)
                leftCounter--;
        } else if (v == rightButton) {
            if (rightCounter < 1) {
                rightCounter = 1;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (rightCounter == 1) {
                deleteRight.setVisibility(View.VISIBLE);
                rightMeasureField2.setVisibility(View.VISIBLE);
                rightCounter++;
            } else if (rightCounter == 2) {
                rightMeasureField3.setVisibility(View.VISIBLE);
                rightCounter++;
            } else if (rightCounter == 3) {
                rightMeasureField4.setVisibility(View.VISIBLE);
                rightCounter++;
            } else
                Toast.makeText(getContext(), "O limite de medições foi atingido!", Toast.LENGTH_SHORT).show();
        } else if (v == deleteRight) {
            if (rightCounter < 1) {
                rightCounter = 1;
                deleteRight.setVisibility(View.GONE);
                rightMeasureValue2.setText(null);
                rightMeasureField2.setVisibility(View.GONE);
                rightMeasureValue3.setText(null);
                rightMeasureField3.setVisibility(View.GONE);
                rightMeasureValue4.setText(null);
                rightMeasureField4.setVisibility(View.GONE);
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (rightCounter == 4) {
                rightMeasureValue4.setText(null);
                rightMeasureField4.setVisibility(View.GONE);
            } else if (rightCounter == 3) {
                rightMeasureValue3.setText(null);
                rightMeasureField3.setVisibility(View.GONE);
            } else if (rightCounter == 2) {
                rightMeasureValue2.setText(null);
                rightMeasureField2.setVisibility(View.GONE);
                deleteRight.setVisibility(View.GONE);
            }
            if (rightCounter > 1)
                rightCounter--;
        }
    }

    private void loadSlopeData(SidewalkSlopeEntry sideSlope) {
        slopeLocaleValue.setText(sideSlope.getSlopeLocation());
        slopeWidthValue.setText(String.valueOf(sideSlope.getSlopeWidth()));
        longCounter = sideSlope.getLongMeasureQnt();
        switch (longCounter) {
            case 4:
                longMeasureField4.setVisibility(View.VISIBLE);
                longMeasureValue4.setText(String.valueOf(sideSlope.getLongMeasure4()));
            case 3:
                longMeasureField3.setVisibility(View.VISIBLE);
                longMeasureValue3.setText(String.valueOf(sideSlope.getLongMeasure3()));
            case 2:
                longMeasureField2.setVisibility(View.VISIBLE);
                longMeasureValue2.setText(String.valueOf(sideSlope.getLongMeasure2()));
            case 1:
                longMeasureField1.setVisibility(View.VISIBLE);
                longMeasureValue1.setText(String.valueOf(sideSlope.getLongMeasure1()));
                deleteLong.setVisibility(View.VISIBLE);
            default:
                break;
        }
        checkRadioGroup(hasLeftWing, sideSlope.getHasLeftWingSlope());
        leftCounter = sideSlope.getLeftWingMeasureQnt();
        switch (leftCounter) {
            case 4:
                leftMeasureField4.setVisibility(View.VISIBLE);
                leftMeasureValue4.setText(String.valueOf(sideSlope.getLeftMeasure4()));
            case 3:
                leftMeasureField3.setVisibility(View.VISIBLE);
                leftMeasureValue3.setText(String.valueOf(sideSlope.getLeftMeasure3()));
            case 2:
                leftMeasureField2.setVisibility(View.VISIBLE);
                leftMeasureValue2.setText(String.valueOf(sideSlope.getLeftMeasure2()));
            case 1:
                leftMeasureField1.setVisibility(View.VISIBLE);
                leftMeasureValue1.setText(String.valueOf(sideSlope.getLeftMeasure1()));
                deleteLeft.setVisibility(View.VISIBLE);
            default:
                break;
        }
        checkRadioGroup(hasRightWing, sideSlope.getHasRightWingSlope());
        rightCounter = sideSlope.getRightWingMeasureQnt();
        switch (rightCounter) {
            case 4:
                rightMeasureField4.setVisibility(View.VISIBLE);
                rightMeasureValue4.setText(String.valueOf(sideSlope.getRightMeasure4()));
            case 3:
                rightMeasureField3.setVisibility(View.VISIBLE);
                rightMeasureValue3.setText(String.valueOf(sideSlope.getRightMeasure3()));
            case 2:
                rightMeasureField2.setVisibility(View.VISIBLE);
                rightMeasureValue2.setText(String.valueOf(sideSlope.getRightMeasure2()));
            case 1:
                rightMeasureField1.setVisibility(View.VISIBLE);
                rightMeasureValue1.setText(String.valueOf(sideSlope.getRightMeasure1()));
                deleteRight.setVisibility(View.VISIBLE);
            default:
                break;
        }
        hasTactileFloor.check(hasTactileFloor.getChildAt(sideSlope.getHasTactileFloor()).getId());
        if (sideSlope.getTactileFloorObs() != null)
            tactileFloorObsValue.setText(sideSlope.getTactileFloorObs());
        slopeIsAccessible.check(slopeIsAccessible.getChildAt(sideSlope.getAccessibleSlopeFloor()).getId());
        if (sideSlope.getAccessibleSlopeFloor() == 0) {
            accessFloorObsField.setVisibility(View.VISIBLE);
            accessFloorObsValue.setText(sideSlope.getAccessibleSlopeFloorObs());
        }
        checkRadioGroup(slopeStreetJunction, sideSlope.getStreetSlopeJunction());
        if (sideSlope.getStreetSlopeObs() != null) {
            slopeStreetObsValue.setText(sideSlope.getStreetSlopeObs());
        }
        if (sideSlope.getSlopeObs() != null)
            slopeObsValue.setText(sideSlope.getSlopeObs());
        if (sideSlope.getSideSlopePhotos() != null)
            photosValue.setText(sideSlope.getSideSlopePhotos());
    }

    private boolean checkSideSlopeEmptyFields() {
        clearSideSlopeErrors();
        int i = 0;
        if (TextUtils.isEmpty(slopeLocaleValue.getText())) {
            i++;
            slopeLocaleField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(slopeWidthValue.getText())) {
            i++;
            slopeWidthField.setError(getString(R.string.req_field_error));
        }
        switch (longCounter) {
            case 4:
                if (TextUtils.isEmpty(longMeasureValue4.getText())) {
                    i++;
                    longitudinalError.setVisibility(View.VISIBLE);
                }
            case 3:
                if (TextUtils.isEmpty(longMeasureValue3.getText())) {
                    i++;
                    longitudinalError.setVisibility(View.VISIBLE);
                }
            case 2:
                if (TextUtils.isEmpty(longMeasureValue2.getText())) {
                    i++;
                    longitudinalError.setVisibility(View.VISIBLE);
                }
            case 1:
                if (TextUtils.isEmpty(longMeasureValue1.getText())) {
                    i++;
                    longitudinalError.setVisibility(View.VISIBLE);
                }
                break;
            case 0:
                i++;
                longitudinalError.setVisibility(View.VISIBLE);
            default:
                break;
        }
        if (indexRadio(hasLeftWing) == -1) {
            i++;
            leftWingRadioError.setVisibility(View.VISIBLE);
        } else if (indexRadio(hasLeftWing) == 1) {
            switch (leftCounter) {
                case 4:
                    if (TextUtils.isEmpty(leftMeasureValue4.getText())) {
                        i++;
                        leftWingError.setVisibility(View.VISIBLE);
                    }
                case 3:
                    if (TextUtils.isEmpty(leftMeasureValue3.getText())) {
                        i++;
                        leftWingError.setVisibility(View.VISIBLE);
                    }
                case 2:
                    if (TextUtils.isEmpty(leftMeasureValue2.getText())) {
                        i++;
                        leftWingError.setVisibility(View.VISIBLE);
                    }
                case 1:
                    if (TextUtils.isEmpty(leftMeasureValue1.getText())) {
                        i++;
                        leftWingError.setVisibility(View.VISIBLE);
                    }
                    break;
                case 0:
                    i++;
                    leftWingError.setVisibility(View.VISIBLE);
                default:
                    break;
            }
        }
        if (indexRadio(hasRightWing) == -1) {
            i++;
            rightWingRadioError.setVisibility(View.VISIBLE);
        } else if (indexRadio(hasRightWing) == 1) {
            switch (rightCounter) {
                case 4:
                    if (TextUtils.isEmpty(rightMeasureValue4.getText())) {
                        i++;
                        rightWingError.setVisibility(View.VISIBLE);
                    }
                case 3:
                    if (TextUtils.isEmpty(rightMeasureValue3.getText())) {
                        i++;
                        rightWingError.setVisibility(View.VISIBLE);
                    }
                case 2:
                    if (TextUtils.isEmpty(rightMeasureValue2.getText())) {
                        i++;
                        rightWingError.setVisibility(View.VISIBLE);
                    }
                case 1:
                    if (TextUtils.isEmpty(rightMeasureValue1.getText())) {
                        i++;
                        rightWingError.setVisibility(View.VISIBLE);
                    }
                    break;
                case 0:
                    i++;
                    rightWingError.setVisibility(View.VISIBLE);
                default:
                    break;
            }
        }
        if (indexRadio(hasTactileFloor) == -1) {
            i++;
            tactileFloorError.setVisibility(View.VISIBLE);
        }
        if (indexRadio(slopeStreetJunction) == -1) {
            i++;
            streetSlopeError.setVisibility(View.VISIBLE);
        }
        if (indexRadio(slopeIsAccessible) == -1) {
            i++;
            slopeAccessFloorRadioError.setVisibility(View.VISIBLE);
        } else if (indexRadio(slopeIsAccessible) == 0) {
            if (TextUtils.isEmpty(accessFloorObsValue.getText())) {
                i++;
                accessFloorObsField.setError(getString(R.string.req_field_error));
            }
        }

        return i == 0;
    }

    private void clearSideSlopeErrors() {
        slopeLocaleField.setErrorEnabled(false);
        slopeWidthField.setErrorEnabled(false);
        longitudinalError.setVisibility(View.GONE);
        leftWingRadioError.setVisibility(View.GONE);
        leftWingError.setVisibility(View.GONE);
        rightWingRadioError.setVisibility(View.GONE);
        rightWingError.setVisibility(View.GONE);
        slopeAccessFloorRadioError.setVisibility(View.GONE);
        accessFloorObsField.setErrorEnabled(false);
        streetSlopeError.setVisibility(View.GONE);
    }

    private void clearSideSlopeFields() {
        slopeLocaleValue.setText(null);
        slopeWidthValue.setText(null);
        longCounter = 1;
        longMeasureValue1.setText(null);
        longMeasureValue2.setText(null);
        longMeasureValue3.setText(null);
        longMeasureValue4.setText(null);
        longMeasureField1.setVisibility(View.GONE);
        longMeasureField2.setVisibility(View.GONE);
        longMeasureField3.setVisibility(View.GONE);
        longMeasureField4.setVisibility(View.GONE);
        deleteLong.setVisibility(View.GONE);
        leftCounter = 0;
        hasLeftWing.clearCheck();
        rightCounter = 0;
        hasRightWing.clearCheck();
        hasTactileFloor.clearCheck();
        tactileFloorObsValue.setText(null);
        slopeIsAccessible.clearCheck();
        accessFloorObsValue.setText(null);
        accessFloorObsField.setVisibility(View.GONE);
        slopeObsValue.setText(null);
        slopeStreetJunction.clearCheck();
    }

    private SidewalkSlopeEntry newSlope(Bundle bundle) {
        String slopeLocation, tactFloorObs = null, accessSlopeFloorObs = null, slopeObs = null, junctionObs = null, photos = null;
        double slopeWidth;
        Double long1 = null, long2 = null, long3 = null, long4 = null, left1 = null, left2 = null, left3 = null, left4 = null,
                right1 = null, right2 = null, right3 = null, right4 = null;
        int hasLeft, hasRight, hasTactile, hasAccess, streetJunction;

        slopeLocation = String.valueOf(slopeLocaleValue.getText());
        slopeWidth = Double.parseDouble(String.valueOf(slopeWidthValue.getText()));
        switch (longCounter) {
            case 4:
                long4 = Double.valueOf(String.valueOf(longMeasureValue4.getText()));
            case 3:
                long3 = Double.valueOf(String.valueOf(longMeasureValue3.getText()));
            case 2:
                long2 = Double.valueOf(String.valueOf(longMeasureValue2.getText()));
            case 1:
                long1 = Double.valueOf(String.valueOf(longMeasureValue1.getText()));
            default:
                break;
        }
        hasLeft = indexRadio(hasLeftWing);
        switch (leftCounter) {
            case 4:
                left4 = Double.valueOf(String.valueOf(leftMeasureValue4.getText()));
            case 3:
                left3 = Double.valueOf(String.valueOf(leftMeasureValue3.getText()));
            case 2:
                left2 = Double.valueOf(String.valueOf(leftMeasureValue2.getText()));
            case 1:
                left1 = Double.valueOf(String.valueOf(leftMeasureValue1.getText()));
            default:
                break;
        }
        hasRight = indexRadio(hasRightWing);
        switch (rightCounter) {
            case 4:
                right4 = Double.valueOf(String.valueOf(rightMeasureValue4.getText()));
            case 3:
                right3 = Double.valueOf(String.valueOf(rightMeasureValue3.getText()));
            case 2:
                right2 = Double.valueOf(String.valueOf(rightMeasureValue2.getText()));
            case 1:
                right1 = Double.valueOf(String.valueOf(rightMeasureValue1.getText()));
            default:
                break;
        }
        hasTactile = indexRadio(hasTactileFloor);
        if (!TextUtils.isEmpty(tactileFloorObsValue.getText()))
            tactFloorObs = String.valueOf(tactileFloorObsValue.getText());
        hasAccess = indexRadio(slopeIsAccessible);
        if (!TextUtils.isEmpty(accessFloorObsValue.getText()))
            accessSlopeFloorObs = String.valueOf(accessFloorObsValue.getText());
        streetJunction = indexRadio(slopeStreetJunction);
        if (!TextUtils.isEmpty(slopeStreetObsValue.getText()))
            junctionObs = String.valueOf(slopeStreetObsValue.getText());
        if (!TextUtils.isEmpty(slopeObsValue.getText()))
            slopeObs = String.valueOf(slopeObsValue.getText());

        if (!TextUtils.isEmpty(photosValue.getText()))
            photos = String.valueOf(photosValue.getText());

        return new SidewalkSlopeEntry(bundle.getInt(AMBIENT_ID), slopeLocation, slopeWidth, longCounter, long1, long2, long3, long4,
                hasLeft, leftCounter, left1, left2, left3, left4, hasRight, rightCounter, right1, right2, right3, right4, hasTactile, tactFloorObs,
                hasAccess, accessSlopeFloorObs, streetJunction, junctionObs, slopeObs, photos);
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
        int index = indexRadio(radio);

        if (radio == hasLeftWing) {
            if (index == 1) {
                leftCounter = 1;
                leftWingHeader.setVisibility(View.VISIBLE);
                leftButton.setVisibility(View.VISIBLE);
                leftMeasureField1.setVisibility(View.VISIBLE);
            } else
                closeSlopeWingFields(radio);
        } else if (radio == hasRightWing) {
            if (index == 1) {
                rightCounter = 1;
                rightWingHeader.setVisibility(View.VISIBLE);
                rightButton.setVisibility(View.VISIBLE);
                rightMeasureField1.setVisibility(View.VISIBLE);
            } else
                closeSlopeWingFields(radio);
        } else if (radio == slopeIsAccessible) {
            if (index == 0) {
                accessFloorObsField.setVisibility(View.VISIBLE);
            } else {
                accessFloorObsValue.setText(null);
                accessFloorObsField.setVisibility(View.GONE);
            }
        }
    }

    private void closeSlopeWingFields(RadioGroup radio) {
        if (radio == hasLeftWing) {
            leftMeasureValue1.setText(null);
            leftMeasureValue2.setText(null);
            leftMeasureValue3.setText(null);
            leftMeasureValue4.setText(null);
            leftWingHeader.setVisibility(View.GONE);
            leftButton.setVisibility(View.GONE);
            deleteLeft.setVisibility(View.GONE);
            leftMeasureField1.setVisibility(View.GONE);
            leftMeasureField2.setVisibility(View.GONE);
            leftMeasureField3.setVisibility(View.GONE);
            leftMeasureField4.setVisibility(View.GONE);
            leftCounter = 0;
        } else if (radio == hasRightWing) {
            rightMeasureValue1.setText(null);
            rightMeasureValue2.setText(null);
            rightMeasureValue3.setText(null);
            rightMeasureValue4.setText(null);
            rightWingHeader.setVisibility(View.GONE);
            rightButton.setVisibility(View.GONE);
            deleteRight.setVisibility(View.GONE);
            rightMeasureField1.setVisibility(View.GONE);
            rightMeasureField2.setVisibility(View.GONE);
            rightMeasureField3.setVisibility(View.GONE);
            rightMeasureField4.setVisibility(View.GONE);
            rightCounter = 0;
        }
    }

}