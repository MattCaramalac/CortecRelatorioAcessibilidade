package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.RestAccessColParcel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

import java.util.ArrayList;

public class RestCollectiveFragment extends Fragment implements TagInterface, ScrollEditText {

    TextInputLayout restLocationField, swHeightField, swObsField, floorObsField, drainObsField, winTypeField1, winTypeField2, winTypeField3, winHeightField1,
            winHeightField2, winHeightField3, winObsField, notAccessLengthField, notAccessWidthField, notAccessEntranceField, entranceSillObsField;
    TextInputEditText restLocationValue, swHeightValue, swObsValue, floorObsValue, drainObsValue, winTypeValue1, winTypeValue2, winTypeValue3, winHeightValue1,
            winHeightValue2, winHeightValue3, winObsValue, notAccessLengthValue, notAccessWidthValue, notAccessEntranceValue, entranceSillObsValue;
    RadioGroup hasDoorRadio, restTypeRadio, hasSwitchRadio, floorRadio, drainRadio, hasWindowRadio, entranceSillRadio;
    TextView hasDoorHeader, hasDoorError, restTypeError, restSwitchError, restFloorError, restDrainError, restWinError, restDrainHeader, entranceSillHeader, entranceSillError, boxHeader;
    ImageView frSpaceCheck, boxCheck;
    MaterialButton addWinButton, addFrSpaceButton, addBoxButton;
    ImageButton delWinButton;

    ViewModelEntry modelEntry;
    ArrayList<TextInputEditText> obsArray = new ArrayList<>();
    ArrayList<TextInputLayout> winTypeArray = new ArrayList<>();
    ArrayList<TextInputLayout> winHeightArray = new ArrayList<>();
    int winQnt = 0;

    static Bundle colBundle;
    static int layout;


    public RestCollectiveFragment() {
        // Required empty public constructor
    }

    public static RestCollectiveFragment newInstance(Bundle bundle, int option) {
        colBundle = bundle;
        layout = option;
        return new RestCollectiveFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rest_collective, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateCollectiveViews(view);

        if (colBundle.getInt(REST_ID) > 0)
            modelEntry.getRestFirstData(colBundle.getInt(REST_ID)).observe(getViewLifecycleOwner(), this::loadAccessRestData);

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA, this, (key, bundle) -> {
            if (checkRestroomFields()) {
                bundle.putBoolean(CHILD_DATA_COMPLETE, true);
                createColRestParcel(bundle);
            } else
                bundle.putBoolean(CHILD_DATA_COMPLETE, false);
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
        });
    }

    private void instantiateCollectiveViews(View view) {
//        TextInputLayout
        restLocationField = view.findViewById(R.id.cRest_location_field);
        swHeightField = view.findViewById(R.id.cRest_switch_height_field);
        swObsField = view.findViewById(R.id.cRest_switch_obs_field);
        floorObsField = view.findViewById(R.id.cRest_drifting_obs_field);
        drainObsField = view.findViewById(R.id.cRest_drain_obs_field);
        winTypeField1 = view.findViewById(R.id.cRest_win_handle_id_1_field);
        winTypeField2 = view.findViewById(R.id.cRest_win_handle_id_2_field);
        winTypeField3 = view.findViewById(R.id.cRest_win_handle_id_3_field);
        winHeightField1 = view.findViewById(R.id.cRest_win_handle_height_field_1);
        winHeightField2 = view.findViewById(R.id.cRest_win_handle_height_field_2);
        winHeightField3 = view.findViewById(R.id.cRest_win_handle_height_field_3);
        winObsField = view.findViewById(R.id.cRest_window_obs_field);
        notAccessLengthField = view.findViewById(R.id.not_access_length_field);
        notAccessWidthField = view.findViewById(R.id.not_access_width_field);
        notAccessEntranceField = view.findViewById(R.id.not_access_entrance_width_field);
        entranceSillObsField = view.findViewById(R.id.not_access_door_sill_obs_field);
//        TextInputEditText
        restLocationValue = view.findViewById(R.id.cRest_location_value);
        swHeightValue = view.findViewById(R.id.cRest_switch_height_value);
        swObsValue = view.findViewById(R.id.cRest_switch_obs_value);
        floorObsValue = view.findViewById(R.id.cRest_drifting_obs_value);
        drainObsValue = view.findViewById(R.id.cRest_drain_obs_value);
        winTypeValue1 = view.findViewById(R.id.cRest_win_handle_id_1_value);
        winTypeValue2 = view.findViewById(R.id.cRest_win_handle_id_2_value);
        winTypeValue3 = view.findViewById(R.id.cRest_win_handle_id_3_value);
        winHeightValue1 = view.findViewById(R.id.cRest_win_handle_height_value_1);
        winHeightValue2 = view.findViewById(R.id.cRest_win_handle_height_value_2);
        winHeightValue3 = view.findViewById(R.id.cRest_win_handle_height_value_3);
        winObsValue = view.findViewById(R.id.cRest_window_obs_value);
        notAccessLengthValue = view.findViewById(R.id.not_access_length_value);
        notAccessWidthValue = view.findViewById(R.id.not_access_width_value);
        notAccessEntranceValue = view.findViewById(R.id.not_access_entrance_width_value);
        entranceSillObsValue = view.findViewById(R.id.not_access_door_sill_obs_value);
//        RadioGroup
        restTypeRadio = view.findViewById(R.id.cRest_type_radio);
        hasSwitchRadio = view.findViewById(R.id.cRest_switch_radio);
        floorRadio = view.findViewById(R.id.cRest_drifting_radio);
        drainRadio = view.findViewById(R.id.cRest_drain_radio);
        hasWindowRadio = view.findViewById(R.id.cRest_window_radio);
        entranceSillRadio = view.findViewById(R.id.not_access_door_sill_radio);
        hasDoorRadio = view.findViewById(R.id.col_has_door_radio);
//        TextView
        restTypeError = view.findViewById(R.id.cRest_type_error);
        restSwitchError = view.findViewById(R.id.cRest_switch_error);
        restFloorError = view.findViewById(R.id.cRest_drifting_error);
        restDrainHeader = view.findViewById(R.id.cRest_drain_header);
        restDrainError = view.findViewById(R.id.cRest_drain_error);
        restWinError = view.findViewById(R.id.cRest_window_error);
        entranceSillHeader = view.findViewById(R.id.not_access_door_sill_header);
        entranceSillError = view.findViewById(R.id.not_access_door_sill_type_error);
        hasDoorHeader = view.findViewById(R.id.col_has_door_header);
        hasDoorError = view.findViewById(R.id.col_has_door_error);
        boxHeader = view.findViewById(R.id.label_cRest_boxes);
//        MaterialButton
        addWinButton = view.findViewById(R.id.add_cRest_window_command_button);
        addFrSpaceButton = view.findViewById(R.id.cRest_free_space_button);
        addBoxButton = view.findViewById(R.id.cRest_boxes_button);
//        ImageButton
        delWinButton = view.findViewById(R.id.delete_cRest_window_command_button);
//        ImageView
        frSpaceCheck = view.findViewById(R.id.cRest_fSpace_check);
        boxCheck = view.findViewById(R.id.cRest_boxes_check);
//        ViewModelEntry
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listeners
        hasSwitchRadio.setOnCheckedChangeListener(this::colRadioListener);
        hasWindowRadio.setOnCheckedChangeListener(this::colRadioListener);
        hasDoorRadio.setOnCheckedChangeListener(this::colRadioListener);
        addWinButton.setOnClickListener(this::buttonClickListener);
        delWinButton.setOnClickListener(this::buttonClickListener);
        addFrSpaceButton.setOnClickListener(this::buttonClickListener);
        addBoxButton.setOnClickListener(this::buttonClickListener);
//

        if (layout == 1) {
            hasDoorHeader.setVisibility(View.VISIBLE);
            hasDoorRadio.setVisibility(View.VISIBLE);
        } else  {
            notAccessWidthField.setVisibility(View.VISIBLE);
            notAccessLengthField.setVisibility(View.VISIBLE);
            notAccessEntranceField.setVisibility(View.VISIBLE);
            entranceSillHeader.setVisibility(View.VISIBLE);
            entranceSillRadio.setVisibility(View.VISIBLE);
            entranceSillObsField.setVisibility(View.VISIBLE);
            restDrainHeader.setVisibility(View.GONE);
            drainRadio.setVisibility(View.GONE);
            drainObsField.setVisibility(View.GONE);
            if (layout == 3) {
                addBoxButton.setVisibility(View.GONE);
                boxHeader.setVisibility(View.GONE);
            }
        }
        viewsToArrays();
        allowObsScroll(obsArray);
    }

    private int getCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private void createColRestParcel(Bundle bundle) {
        Integer hasDoor = null, restType = null, antiDriftFloor = null, restDrain = null, restSwitch = null, hasWin = null, entSill = null;
        Double switchHeight = null, winHeight1 = null, winHeight2 = null, winHeight3 = null, notAccLength = null, notAccWidth = null, notAccEntWidth = null;
        String restLocation = null, antiDriftFloorObs = null, restDrainObs = null, switchObs = null, winType1 = null, winType2 = null, winType3 = null, winObs = null, entObs = null;

        if (getCheckedRadio(restTypeRadio) != -1)
            restType = getCheckedRadio(restTypeRadio);
        if (!TextUtils.isEmpty(restLocationValue.getText()))
            restLocation = String.valueOf(restLocationValue.getText());
        if (getCheckedRadio(floorRadio) != -1)
            antiDriftFloor = getCheckedRadio(floorRadio);
        if (!TextUtils.isEmpty(floorObsValue.getText()))
            antiDriftFloorObs = String.valueOf(floorObsValue.getText());

        if (getCheckedRadio(hasSwitchRadio) != -1) {
            restSwitch = getCheckedRadio(hasSwitchRadio);
            if (restSwitch == 1) {
                if (!TextUtils.isEmpty(swHeightValue.getText()))
                    switchHeight = Double.parseDouble(String.valueOf(swHeightValue.getText()));
            }
        }
        if (!TextUtils.isEmpty(swObsValue.getText()))
            switchObs = String.valueOf(swObsValue.getText());

        if (getCheckedRadio(hasWindowRadio) != -1) {
            hasWin = getCheckedRadio(hasWindowRadio);
            if (hasWin == 1) {
                switch (winQnt) {
                    case 3:
                        if (!TextUtils.isEmpty(winTypeValue3.getText()))
                            winType3 = String.valueOf(winTypeValue3.getText());
                        if (!TextUtils.isEmpty(winHeightValue3.getText()))
                            winHeight3 = Double.parseDouble(String.valueOf(winHeightValue3.getText()));
                    case 2:
                        if (!TextUtils.isEmpty(winTypeValue2.getText()))
                            winType2 = String.valueOf(winTypeValue2.getText());
                        if (!TextUtils.isEmpty(winHeightValue2.getText()))
                            winHeight2 = Double.parseDouble(String.valueOf(winHeightValue2.getText()));
                    case 1:
                        if (!TextUtils.isEmpty(winTypeValue1.getText()))
                            winType1 = String.valueOf(winTypeValue1.getText());
                        if (!TextUtils.isEmpty(winHeightValue1.getText()))
                            winHeight1 = Double.parseDouble(String.valueOf(winHeightValue1.getText()));
                        break;
                }
            }
        }

        if (!TextUtils.isEmpty(winObsValue.getText()))
            winObs = String.valueOf(winObsValue.getText());


        if (layout == 1) {
            if (getCheckedRadio(drainRadio) != -1)
                restDrain = getCheckedRadio(drainRadio);
            if (!TextUtils.isEmpty(drainObsValue.getText()))
                restDrainObs = String.valueOf(drainObsValue.getText());
            if (getCheckedRadio(hasDoorRadio) != -1) {
                hasDoor = getCheckedRadio(hasDoorRadio);
                if (hasDoor == 0) {
                    if (!TextUtils.isEmpty(notAccessEntranceValue.getText()))
                        notAccEntWidth = Double.parseDouble(String.valueOf(notAccessEntranceValue.getText()));
                    if (getCheckedRadio(entranceSillRadio) != -1)
                        entSill = getCheckedRadio(entranceSillRadio);
                    if (!TextUtils.isEmpty(entranceSillObsValue.getText()))
                        entObs = String.valueOf(entranceSillObsValue.getText());
                }
            }
        } else {
            if (!TextUtils.isEmpty(notAccessLengthValue.getText()))
                notAccLength = Double.parseDouble(String.valueOf(notAccessLengthValue.getText()));
            if (!TextUtils.isEmpty(notAccessWidthValue.getText()))
                notAccWidth = Double.parseDouble(String.valueOf(notAccessWidthValue.getText()));
            if (!TextUtils.isEmpty(notAccessEntranceValue.getText()))
                notAccEntWidth = Double.parseDouble(String.valueOf(notAccessEntranceValue.getText()));
            if (getCheckedRadio(entranceSillRadio) != -1)
                entSill = getCheckedRadio(entranceSillRadio);
            if (!TextUtils.isEmpty(entranceSillObsValue.getText()))
                entObs = String.valueOf(entranceSillObsValue.getText());
        }

        RestAccessColParcel parcel = new RestAccessColParcel(restType, restLocation, notAccLength, notAccWidth, hasDoor, null, null,
                null, null, antiDriftFloor, antiDriftFloorObs, restDrain, restDrainObs, notAccEntWidth, entSill, entObs, restSwitch,
                switchHeight, switchObs, hasWin, winQnt, winType1, winHeight1, winType2, winHeight2, winType3, winHeight3, winObs);
        bundle.putParcelable(CHILD_PARCEL, Parcels.wrap(parcel));
    }

    private void colRadioListener(RadioGroup radio, int checkedID) {
        int index = getCheckedRadio(radio);

        if (radio == hasSwitchRadio) {
            if (index == 1)
                swHeightField.setVisibility(View.VISIBLE);
            else {
                swHeightValue.setText(null);
                swHeightField.setVisibility(View.GONE);
                restSwitchError.setVisibility(View.GONE);
            }
        } else if (radio == hasWindowRadio) {
            if (index == 1) {
                addWinButton.setVisibility(View.VISIBLE);
                winTypeField1.setVisibility(View.VISIBLE);
                winHeightField1.setVisibility(View.VISIBLE);
                winQnt = 1;
            } else {
                winQnt = 0;
                addWinButton.setVisibility(View.GONE);
                delWinButton.setVisibility(View.GONE);
                restWinError.setVisibility(View.GONE);
                winTypeValue1.setText(null);
                winHeightValue1.setText(null);
                winTypeValue2.setText(null);
                winHeightValue2.setText(null);
                winTypeValue3.setText(null);
                winHeightValue3.setText(null);
                winTypeField1.setVisibility(View.GONE);
                winHeightField1.setVisibility(View.GONE);
                winTypeField2.setVisibility(View.GONE);
                winHeightField2.setVisibility(View.GONE);
                winTypeField3.setVisibility(View.GONE);
                winHeightField3.setVisibility(View.GONE);
            }
        } else if (radio == hasDoorRadio) {
            if (index == 0) {
                notAccessEntranceField.setVisibility(View.VISIBLE);
                entranceSillRadio.setVisibility(View.VISIBLE);
                entranceSillHeader.setVisibility(View.VISIBLE);
                entranceSillObsField.setVisibility(View.VISIBLE);
            } else if (index == 1) {
                clearColDoorFields();
                notAccessEntranceField.setVisibility(View.GONE);
                entranceSillRadio.setVisibility(View.GONE);
                entranceSillHeader.setVisibility(View.GONE);
                entranceSillObsValue.setVisibility(View.GONE);
            }
        }
    }

    private void clearColDoorErrors() {
        hasDoorError.setVisibility(View.GONE);
        notAccessEntranceField.setErrorEnabled(false);
        entranceSillError.setVisibility(View.GONE);
    }

    private void clearColDoorFields() {
        notAccessEntranceValue.setText(null);
        entranceSillRadio.clearCheck();
        entranceSillObsValue.setText(null);
        clearColDoorErrors();
    }

    private void loadAccessRestData(RestroomEntry rest) {
        if (rest.getRestType() == layout) {
            if (rest.getRestGender() != null)
                restTypeRadio.check(restTypeRadio.getChildAt(rest.getRestGender()).getId());
            if (rest.getRestLocation() != null)
                restLocationValue.setText(rest.getRestLocation());
            if (rest.getAntiDriftFloor() != null)
                floorRadio.check(floorRadio.getChildAt(rest.getAntiDriftFloor()).getId());
            if (rest.getAntiDriftFloorObs() != null && rest.getAntiDriftFloorObs().length() > 0)
                floorObsValue.setText(rest.getAntiDriftFloorObs());
            if (rest.getRestSwitch() != null) {
                hasSwitchRadio.check(hasSwitchRadio.getChildAt(rest.getRestSwitch()).getId());
                if (rest.getRestSwitch() == 1 && rest.getSwitchHeight() != null)
                    swHeightValue.setText(String.valueOf(rest.getSwitchHeight()));
            }
            if (rest.getSwitchObs() != null && rest.getSwitchObs().length() > 0)
                swObsValue.setText(rest.getSwitchObs());

            if (rest.getHasWindow() != null && rest.getHasWindow() > -1) {
                hasWindowRadio.check(hasWindowRadio.getChildAt(rest.getHasWindow()).getId());
                if (rest.getHasWindow() == 1) {
                    winQnt = rest.getWinQnt();
                    switch (winQnt) {
                        case 3:
                            winTypeField3.setVisibility(View.VISIBLE);
                            if (rest.getWinComType3() != null)
                                winTypeValue3.setText(rest.getWinComType3());
                            winHeightField3.setVisibility(View.VISIBLE);
                            if (rest.getWinComHeight3() != null)
                                winHeightValue3.setText(String.valueOf(rest.getWinComHeight3()));
                        case 2:
                            delWinButton.setVisibility(View.VISIBLE);
                            winTypeField2.setVisibility(View.VISIBLE);
                            if (rest.getWinComType2() != null)
                                winTypeValue2.setText(rest.getWinComType2());
                            winHeightField2.setVisibility(View.VISIBLE);
                            if (rest.getWinComHeight2() != null)
                                winHeightValue2.setText(String.valueOf(rest.getWinComHeight2()));
                        case 1:
                            addWinButton.setVisibility(View.VISIBLE);
                            winTypeField1.setVisibility(View.VISIBLE);
                            if (rest.getWinComType1() != null)
                                winTypeValue1.setText(rest.getWinComType1());
                            winHeightField1.setVisibility(View.VISIBLE);
                            if (rest.getWinComHeight1() != null)
                                winHeightValue1.setText(String.valueOf(rest.getWinComHeight1()));
                            break;
                    }
                }
            }

            if (layout == 1) {
                if (rest.getRestDrain() != null)
                    drainRadio.check(drainRadio.getChildAt(rest.getRestDrain()).getId());
                if (rest.getRestDrainObs() != null && rest.getRestDrainObs().length() > 0)
                    drainObsValue.setText(rest.getRestDrainObs());
                if (rest.getCollectiveHasDoor() != null) {
                    hasDoorRadio.check(hasDoorRadio.getChildAt(rest.getCollectiveHasDoor()).getId());
                    if (rest.getCollectiveHasDoor() == 0) {
                        if (rest.getEntranceWidth() != null)
                            notAccessEntranceValue.setText(String.valueOf(rest.getEntranceWidth()));
                        if (rest.getEntranceDoorSill() != null && rest.getEntranceDoorSill() > -1)
                            entranceSillRadio.check(entranceSillRadio.getChildAt(rest.getEntranceDoorSill()).getId());
                        if (rest.getEntranceDoorSillObs() != null)
                            entranceSillObsValue.setText(rest.getEntranceDoorSillObs());
                    }
                }
            } else {
                if (rest.getUpViewLength() != null)
                    notAccessLengthValue.setText(String.valueOf(rest.getUpViewLength()));
                if (rest.getUpViewWidth() != null)
                    notAccessWidthValue.setText(String.valueOf(rest.getUpViewWidth()));
                if (rest.getEntranceWidth() != null)
                    notAccessEntranceValue.setText(String.valueOf(rest.getEntranceWidth()));
                if (rest.getEntranceDoorSill() != null)
                    entranceSillRadio.check(entranceSillRadio.getChildAt(rest.getEntranceDoorSill()).getId());
                if (rest.getEntranceDoorSillObs() != null)
                    entranceSillObsValue.setText(rest.getEntranceDoorSillObs());
            }
        }
    }

    private boolean checkRestroomFields() {
        clearRestroomErrors();
        int error = 0;
        if (TextUtils.isEmpty(restLocationValue.getText())) {
            error++;
            restLocationField.setError(getString(R.string.req_field_error));
        }
        if (restTypeRadio.getCheckedRadioButtonId() == -1) {
            restTypeError.setVisibility(View.VISIBLE);
            error++;
        }
        if (getCheckedRadio(floorRadio) == -1) {
            restFloorError.setVisibility(View.VISIBLE);
            error++;
        }
        if (getCheckedRadio(hasSwitchRadio) == -1) {
            restSwitchError.setVisibility(View.VISIBLE);
            error++;
        } else if (getCheckedRadio(hasSwitchRadio) == 1) {
            if (TextUtils.isEmpty(swHeightValue.getText())) {
                swHeightField.setError(getString(R.string.req_field_error));
            }
        }

        if (getCheckedRadio(hasWindowRadio) == -1) {
            error++;
            restWinError.setVisibility(View.VISIBLE);
        } else if (getCheckedRadio(hasWindowRadio) == 1) {
            switch (winQnt) {
                case 3:
                    if (TextUtils.isEmpty(winHeightValue3.getText())) {
                        error++;
                        winHeightField3.setError(getString(R.string.req_field_error));
                        if (TextUtils.isEmpty(winTypeValue3.getText()))
                            winTypeField3.setError(getString(R.string.req_field_error));
                    }
                case 2:
                    if (TextUtils.isEmpty(winHeightValue2.getText())) {
                        error++;
                        winHeightField2.setError(getString(R.string.req_field_error));
                        if (TextUtils.isEmpty(winTypeValue2.getText()))
                            winTypeField2.setError(getString(R.string.req_field_error));
                    }
                case 1:
                    if (TextUtils.isEmpty(winHeightValue1.getText())) {
                        error++;
                        winHeightField1.setError(getString(R.string.req_field_error));
                        if (TextUtils.isEmpty(winTypeValue1.getText()))
                            winTypeField1.setError(getString(R.string.req_field_error));
                    }
                    break;
            }
        }

        if (layout == 1) {
            if (getCheckedRadio(hasDoorRadio) == -1) {
                error++;
                hasDoorError.setVisibility(View.VISIBLE);
            } else if (getCheckedRadio(hasDoorRadio) == 0) {
                if (TextUtils.isEmpty(notAccessEntranceValue.getText())) {
                    error++;
                    notAccessEntranceField.setError(getString(R.string.req_field_error));
                }
                if (getCheckedRadio(entranceSillRadio) == -1) {
                    error++;
                    entranceSillError.setVisibility(View.VISIBLE);
                }
            }
            if (getCheckedRadio(drainRadio) == -1) {
                restDrainError.setVisibility(View.VISIBLE);
                error++;
            }
        } else {
            if (TextUtils.isEmpty(notAccessLengthValue.getText())) {
                error++;
                notAccessLengthField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(notAccessWidthValue.getText())) {
                error++;
                notAccessWidthField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(notAccessEntranceValue.getText())) {
                error++;
                notAccessEntranceField.setError(getString(R.string.req_field_error));
            }
            if (getCheckedRadio(entranceSillRadio) == -1) {
                entranceSillError.setVisibility(View.VISIBLE);
                error++;
            }
        }

        return error == 0;
    }

    private void clearRestroomErrors() {
        restLocationField.setErrorEnabled(false);
        restTypeError.setVisibility(View.GONE);
        restFloorError.setVisibility(View.GONE);
        restDrainError.setVisibility(View.GONE);
        restSwitchError.setVisibility(View.GONE);
        restWinError.setVisibility(View.GONE);
        winTypeField1.setErrorEnabled(false);
        winHeightField1.setErrorEnabled(false);
        winTypeField2.setErrorEnabled(false);
        winHeightField2.setErrorEnabled(false);
        winTypeField3.setErrorEnabled(false);
        winHeightField3.setErrorEnabled(false);
        notAccessLengthField.setErrorEnabled(false);
        notAccessWidthField.setErrorEnabled(false);
        notAccessEntranceField.setErrorEnabled(false);
        entranceSillError.setVisibility(View.GONE);
        hasDoorError.setVisibility(View.GONE);
    }

    private void buttonClickListener(View v) {
        if (v == addWinButton) {
            if (winQnt < 1) {
                winQnt = 1;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (winQnt < 3) {
                if (winQnt == 1)
                    delWinButton.setVisibility(View.VISIBLE);
                winTypeArray.get(winQnt).setVisibility(View.VISIBLE);
                winHeightArray.get(winQnt).setVisibility(View.VISIBLE);
                winQnt++;
            } else
                Toast.makeText(getContext(), getString(R.string.toast_max_measurements), Toast.LENGTH_SHORT).show();
        } else if (v == delWinButton) {
            if (winQnt > 1) {
                winTypeArray.get(winQnt - 1).getEditText().setText(null);
                winTypeArray.get(winQnt - 1).setVisibility(View.GONE);
                winHeightArray.get(winQnt - 1).getEditText().setText(null);
                winHeightArray.get(winQnt - 1).setVisibility(View.GONE);
                winQnt--;
                if (winQnt == 1)
                    delWinButton.setVisibility(View.GONE);
            }
        } else {
            createColRestParcel(colBundle);
            colBundle.putBoolean(ADD_ITEM_REQUEST, true);
            colBundle.putBoolean(BOX_ENTRY, v != addFrSpaceButton);
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, colBundle);
        }
    }

    private void viewsToArrays() {
        obsArray.add(swObsValue);
        obsArray.add(floorObsValue);
        obsArray.add(winObsValue);
        obsArray.add(drainObsValue);

        winTypeArray.add(winTypeField1);
        winTypeArray.add(winTypeField2);
        winTypeArray.add(winTypeField3);

        winHeightArray.add(winHeightField1);
        winHeightArray.add(winHeightField2);
        winHeightArray.add(winHeightField3);

    }
}