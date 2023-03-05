package com.mpms.relatorioacessibilidadecortec.fragments;

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

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.ExpandImageDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestAccessUpdateTwo;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxAccTwoUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RestAccessTwoFragment extends Fragment implements TagInterface, ScrollEditText {

    TextInputLayout emergencyHeightField, emergencyObsField, valveHeightField, valveObsField, winTypeField1, winTypeField2, winTypeField3, winHeightField1,
            winHeightField2, winHeightField3, mirrorFieldA, mirrorFieldB, mirrorObsField, winObsField;
    TextInputEditText emergencyHeightValue, emergencyObsValue, valveHeightValue, valveObsValue, winTypeValue1, winTypeValue2, winTypeValue3, winHeightValue1,
            winHeightValue2, winHeightValue3, mirrorValueA, mirrorValueB, mirrorObsValue, winObsValue;
    RadioGroup hasEmergencyRadio, hasValveRadio, valveTypeRadio, hasWindowRadio, mirrorRadio;
    TextView emergencyError, valveError, valveTypeHeader, valveTypeError, winHeader, winError, mirrorError;
    ImageButton delWindow, mirrorImage;
    MaterialButton addWindow, returnAccessOne, continueSink;

    Bundle rAccBundle2, imgBundle;
    ViewModelEntry modelEntry;
    ArrayList<TextInputEditText> accessObsArray = new ArrayList<>();
    ArrayList<TextInputLayout> winTypeArray = new ArrayList<>();
    ArrayList<TextInputLayout> winHeightArray = new ArrayList<>();
    int winQnt = 0;


    public RestAccessTwoFragment() {
        // Required empty public constructor
    }

    public static RestAccessTwoFragment newInstance() {
        return new RestAccessTwoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            rAccBundle2 = new Bundle(this.getArguments());
        else
            rAccBundle2 = new Bundle();

        imgBundle = new Bundle();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rest_access_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instRestAccessTwoView(view);

        if (rAccBundle2.getInt(BOX_ID) > 0)
            modelEntry.getBoxAccessDataTwo(rAccBundle2.getInt(BOX_ID)).observe(getViewLifecycleOwner(), this::loadBoxAccessData2);
        else if (rAccBundle2.getInt(REST_ID) > 0)
            modelEntry.getRestAccessDataTwo(rAccBundle2.getInt(REST_ID)).observe(getViewLifecycleOwner(), this::loadAccessData2);
    }


    private void instRestAccessTwoView(View view) {
//        TextInputLayout
        emergencyHeightField = view.findViewById(R.id.emergency_height_field);
        emergencyObsField = view.findViewById(R.id.emergency_obs_field);
        valveHeightField = view.findViewById(R.id.water_valve_height_field);
        valveObsField = view.findViewById(R.id.water_valve_obs_field);
        winTypeField1 = view.findViewById(R.id.rest_win_handle_id_1_field);
        winTypeField2 = view.findViewById(R.id.rest_win_handle_id_2_field);
        winTypeField3 = view.findViewById(R.id.rest_win_handle_id_3_field);
        winHeightField1 = view.findViewById(R.id.rest_win_handle_height_field_1);
        winHeightField2 = view.findViewById(R.id.rest_win_handle_height_field_2);
        winHeightField3 = view.findViewById(R.id.rest_win_handle_height_field_3);
        mirrorFieldA = view.findViewById(R.id.wall_mirror_measureA_field);
        mirrorFieldB = view.findViewById(R.id.wall_mirror_measureB_field);
        mirrorObsField = view.findViewById(R.id.wall_mirror_obs_field);
        winObsField = view.findViewById(R.id.window_obs_field);
//        TextInputEditText
        emergencyHeightValue = view.findViewById(R.id.emergency_height_value);
        emergencyObsValue = view.findViewById(R.id.emergency_obs_value);
        valveHeightValue = view.findViewById(R.id.water_valve_height_value);
        valveObsValue = view.findViewById(R.id.water_valve_obs_value);
        winTypeValue1 = view.findViewById(R.id.rest_win_handle_id_1_value);
        winTypeValue2 = view.findViewById(R.id.rest_win_handle_id_2_value);
        winTypeValue3 = view.findViewById(R.id.rest_win_handle_id_3_value);
        winHeightValue1 = view.findViewById(R.id.rest_win_handle_height_value_1);
        winHeightValue2 = view.findViewById(R.id.rest_win_handle_height_value_2);
        winHeightValue3 = view.findViewById(R.id.rest_win_handle_height_value_3);
        mirrorValueA = view.findViewById(R.id.wall_mirror_measureA_value);
        mirrorValueB = view.findViewById(R.id.wall_mirror_measureB_value);
        mirrorObsValue = view.findViewById(R.id.wall_mirror_obs_value);
        winObsValue = view.findViewById(R.id.window_obs_value);
//        RadioGroup
        hasEmergencyRadio = view.findViewById(R.id.emergency_radio);
        hasValveRadio = view.findViewById(R.id.water_valve_radio);
        valveTypeRadio = view.findViewById(R.id.water_valve_type_radio);
        hasWindowRadio = view.findViewById(R.id.rest_window_radio);
        mirrorRadio = view.findViewById(R.id.wall_mirror_radio);
//        TextView
        emergencyError = view.findViewById(R.id.emergency_error);
        valveError = view.findViewById(R.id.water_valve_error);
        valveTypeHeader = view.findViewById(R.id.water_valve_type_header);
        valveTypeError = view.findViewById(R.id.water_valve_type_error);
        winHeader = view.findViewById(R.id.rest_window_header);
        winError = view.findViewById(R.id.rest_window_error);
        mirrorError = view.findViewById(R.id.wall_mirror_error);
//      Material Button
        addWindow = view.findViewById(R.id.add_window_command_button);
        returnAccessOne = view.findViewById(R.id.return_access_one);
        continueSink = view.findViewById(R.id.continue_sink);
//        ImageButton
        delWindow = view.findViewById(R.id.delete_window_command_button);
        mirrorImage = view.findViewById(R.id.wall_mirror_img);
        Glide.with(this).load(R.drawable.wallmirror).centerCrop().into(mirrorImage);
//        Listeners
        hasEmergencyRadio.setOnCheckedChangeListener(this::radioListener);
        hasValveRadio.setOnCheckedChangeListener(this::radioListener);
        valveTypeRadio.setOnCheckedChangeListener(this::radioListener);
        hasWindowRadio.setOnCheckedChangeListener(this::radioListener);
        mirrorRadio.setOnCheckedChangeListener(this::radioListener);
        mirrorImage.setOnClickListener(this::imgExpandClick);
        addWindow.setOnClickListener(this::buttonClickListener);
        delWindow.setOnClickListener(this::buttonClickListener);
        continueSink.setOnClickListener(this::buttonClickListener);
        returnAccessOne.setOnClickListener(this::buttonClickListener);
        viewsToArrays();
        allowObsScroll(accessObsArray);
        if (rAccBundle2.getBoolean(FROM_BOX)) {
            winHeader.setVisibility(View.GONE);
            hasWindowRadio.setVisibility(View.GONE);
            winObsField.setVisibility(View.GONE);
        }
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private void loadAccessData2(RestroomEntry rest) {
        if (rest.getHasEmergencyButton() != null && rest.getHasEmergencyButton() > -1) {
            hasEmergencyRadio.check(hasEmergencyRadio.getChildAt(rest.getHasEmergencyButton()).getId());
            if (rest.getHasEmergencyButton() == 1) {
                if (rest.getEmergencyHeight() != null)
                    emergencyHeightValue.setText(String.valueOf(rest.getEmergencyHeight()));
            }
        }
        if (rest.getEmergencyObs() != null)
            emergencyObsValue.setText(rest.getEmergencyObs());

        if (rest.getHasWaterValve() != null && rest.getHasWaterValve() > -1) {
            hasValveRadio.check(hasValveRadio.getChildAt(rest.getHasWaterValve()).getId());
            if (rest.getHasWaterValve() == 1) {
                if (rest.getWaterValveType() != null && rest.getWaterValveType() > -1)
                    valveTypeRadio.check(valveTypeRadio.getChildAt(rest.getWaterValveType()).getId());
                if (rest.getWaterValveHeight() != null)
                    valveHeightValue.setText(String.valueOf(rest.getWaterValveHeight()));
            }
        }
        if (rest.getWaterValveObs() != null)
            valveObsValue.setText(rest.getWaterValveObs());

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
                        delWindow.setVisibility(View.VISIBLE);
                        winTypeField2.setVisibility(View.VISIBLE);
                        if (rest.getWinComType2() != null)
                            winTypeValue2.setText(rest.getWinComType2());
                        winHeightField2.setVisibility(View.VISIBLE);
                        if (rest.getWinComHeight2() != null)
                            winHeightValue2.setText(String.valueOf(rest.getWinComHeight2()));
                    case 1:
                        addWindow.setVisibility(View.VISIBLE);
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
        if (rest.getWinObs() != null)
            winObsValue.setText(rest.getWinObs());

        if (rest.getHasWallMirror() != null) {
            mirrorRadio.check(mirrorRadio.getChildAt(rest.getHasWallMirror()).getId());
            if (rest.getHasWallMirror() == 1) {
                if (rest.getWallMirrorLow() != null)
                    mirrorValueA.setText(String.valueOf(rest.getWallMirrorLow()));
                if (rest.getWallMirrorHigh() != null)
                    mirrorValueB.setText(String.valueOf(rest.getWallMirrorHigh()));
            }
        }
        if (rest.getWallMirrorObs() != null)
            mirrorObsValue.setText(rest.getWallMirrorObs());

    }

    private void loadBoxAccessData2(RestBoxEntry rest) {
        if (rest.getHasEmergencyButton() != null && rest.getHasEmergencyButton() > -1) {
            hasEmergencyRadio.check(hasEmergencyRadio.getChildAt(rest.getHasEmergencyButton()).getId());
            if (rest.getHasEmergencyButton() == 1) {
                if (rest.getEmergencyHeight() != null)
                    emergencyHeightValue.setText(String.valueOf(rest.getEmergencyHeight()));
            }
        }
        if (rest.getEmergencyObs() != null)
            emergencyObsValue.setText(rest.getEmergencyObs());

        if (rest.getHasWaterValve() != null && rest.getHasWaterValve() > -1) {
            hasValveRadio.check(hasValveRadio.getChildAt(rest.getHasWaterValve()).getId());
            if (rest.getHasWaterValve() == 1) {
                if (rest.getWaterValveType() != null && rest.getWaterValveType() > -1)
                    valveTypeRadio.check(valveTypeRadio.getChildAt(rest.getWaterValveType()).getId());
                if (rest.getWaterValveHeight() != null)
                    valveHeightValue.setText(String.valueOf(rest.getWaterValveHeight()));
            }
        }
        if (rest.getWaterValveObs() != null)
            valveObsValue.setText(rest.getWaterValveObs());

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
                        delWindow.setVisibility(View.VISIBLE);
                        winTypeField2.setVisibility(View.VISIBLE);
                        if (rest.getWinComType2() != null)
                            winTypeValue2.setText(rest.getWinComType2());
                        winHeightField2.setVisibility(View.VISIBLE);
                        if (rest.getWinComHeight2() != null)
                            winHeightValue2.setText(String.valueOf(rest.getWinComHeight2()));
                    case 1:
                        addWindow.setVisibility(View.VISIBLE);
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
        if (rest.getWinObs() != null)
            winObsValue.setText(rest.getWinObs());

        if (rest.getHasWallMirror() != null) {
            mirrorRadio.check(mirrorRadio.getChildAt(rest.getHasWallMirror()).getId());
            if (rest.getHasWallMirror() == 1) {
                if (rest.getWallMirrorLow() != null)
                    mirrorValueA.setText(String.valueOf(rest.getWallMirrorLow()));
                if (rest.getWallMirrorHigh() != null)
                    mirrorValueB.setText(String.valueOf(rest.getWallMirrorHigh()));
            }
        }
        if (rest.getWallMirrorObs() != null)
            mirrorObsValue.setText(rest.getWallMirrorObs());

    }

    private void callSinkFragment(Bundle bundle) {
        RestSinkAccessFragment sinkFragment = RestSinkAccessFragment.newInstance();
        sinkFragment.setArguments(bundle);
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, sinkFragment).addToBackStack(null).commit();
    }

    private boolean checkEmptyFields() {
        clearEmptyFieldErrors();
        int i = 0;

        if (getCheckedRadio(hasEmergencyRadio) == -1) {
            i++;
            emergencyError.setVisibility(View.VISIBLE);
        } else if (getCheckedRadio(hasEmergencyRadio) == 1) {
            if (TextUtils.isEmpty(emergencyHeightValue.getText())) {
                i++;
                emergencyHeightField.setError(getString(R.string.req_field_error));
            }
        }

        if (getCheckedRadio(hasValveRadio) == -1) {
            i++;
            valveError.setVisibility(View.VISIBLE);
        } else if (getCheckedRadio(hasValveRadio) == 1) {
            if (TextUtils.isEmpty(valveHeightValue.getText())) {
                i++;
                valveHeightField.setError(getString(R.string.req_field_error));
            }
        }

        if (!rAccBundle2.getBoolean(FROM_BOX) && getCheckedRadio(hasWindowRadio) == -1) {
            i++;
            winError.setVisibility(View.VISIBLE);
        } else if (!rAccBundle2.getBoolean(FROM_BOX) && getCheckedRadio(hasWindowRadio) == 1) {
            switch (winQnt) {
                case 3:
                    if (TextUtils.isEmpty(winHeightValue3.getText())) {
                        i++;
                        winHeightField3.setError(getString(R.string.req_field_error));
                        if (TextUtils.isEmpty(winTypeValue3.getText()))
                            winTypeField3.setError(getString(R.string.req_field_error));
                    }
                case 2:
                    if (TextUtils.isEmpty(winHeightValue2.getText())) {
                        i++;
                        winHeightField2.setError(getString(R.string.req_field_error));
                        if (TextUtils.isEmpty(winTypeValue2.getText()))
                            winTypeField2.setError(getString(R.string.req_field_error));
                    }
                case 1:
                    if (TextUtils.isEmpty(winHeightValue1.getText())) {
                        i++;
                        winHeightField1.setError(getString(R.string.req_field_error));
                        if (TextUtils.isEmpty(winTypeValue1.getText()))
                            winTypeField1.setError(getString(R.string.req_field_error));
                    }
                    break;
            }
        }

        if (getCheckedRadio(mirrorRadio) == -1) {
            i++;
            mirrorError.setVisibility(View.VISIBLE);
        } else if (getCheckedRadio(mirrorRadio) == 1) {
            if (TextUtils.isEmpty(mirrorValueA.getText())) {
                i++;
                mirrorFieldA.setError(getText(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(mirrorValueB.getText())) {
                i++;
                mirrorFieldB.setError(getText(R.string.req_field_error));
            }
        }

        return i == 0;
    }

    private void clearEmptyFieldErrors() {
        emergencyError.setVisibility(View.GONE);
        valveError.setVisibility(View.GONE);
        valveTypeError.setVisibility(View.GONE);
        winError.setVisibility(View.GONE);
        mirrorError.setVisibility(View.GONE);
        emergencyHeightField.setErrorEnabled(false);
        valveHeightField.setErrorEnabled(false);
        winHeightField3.setErrorEnabled(false);
        winTypeField3.setErrorEnabled(false);
        winHeightField2.setErrorEnabled(false);
        winTypeField2.setErrorEnabled(false);
        winHeightField1.setErrorEnabled(false);
        winTypeField1.setErrorEnabled(false);
        mirrorFieldA.setErrorEnabled(false);
        mirrorFieldB.setErrorEnabled(false);

    }

    private int getCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private void radioListener(RadioGroup radio, int check) {
        int index = getCheckedRadio(radio);
        if (radio == hasEmergencyRadio) {
            if (index == 1)
                emergencyHeightField.setVisibility(View.VISIBLE);
            else {
                emergencyHeightValue.setText(null);
                emergencyHeightField.setVisibility(View.GONE);
                emergencyError.setVisibility(View.GONE);
            }
        } else if (radio == hasValveRadio) {
            if (index == 1) {
                valveTypeHeader.setVisibility(View.VISIBLE);
                valveTypeRadio.setVisibility(View.VISIBLE);
                valveHeightField.setVisibility(View.VISIBLE);
            } else {
                valveTypeRadio.clearCheck();
                valveHeightValue.setText(null);
                valveTypeHeader.setVisibility(View.GONE);
                valveTypeRadio.setVisibility(View.GONE);
                valveTypeError.setVisibility(View.GONE);
                valveHeightField.setVisibility(View.GONE);
            }
        } else if (radio == hasWindowRadio) {
            if (index == 1) {
                addWindow.setVisibility(View.VISIBLE);
                winTypeField1.setVisibility(View.VISIBLE);
                winHeightField1.setVisibility(View.VISIBLE);
                winQnt = 1;
            } else {
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
                addWindow.setVisibility(View.GONE);
                delWindow.setVisibility(View.GONE);
                winError.setVisibility(View.GONE);
                winQnt = 0;
            }
        } else if (radio == mirrorRadio) {
            if (index == 1) {
                mirrorFieldA.setVisibility(View.VISIBLE);
                mirrorFieldB.setVisibility(View.VISIBLE);
                mirrorImage.setVisibility(View.VISIBLE);
            } else {
                mirrorValueA.setText(null);
                mirrorValueB.setText(null);
                mirrorFieldA.setVisibility(View.GONE);
                mirrorFieldB.setVisibility(View.GONE);
                mirrorImage.setVisibility(View.GONE);
            }
        }
    }

    private void buttonClickListener(View v) {
        if (v == addWindow) {
            if (winQnt < 1) {
                winQnt = 1;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (winQnt < 3) {
                if (winQnt == 1)
                    delWindow.setVisibility(View.VISIBLE);
                winTypeArray.get(winQnt).setVisibility(View.VISIBLE);
                winHeightArray.get(winQnt).setVisibility(View.VISIBLE);
                winQnt++;
            } else
                Toast.makeText(getContext(), getString(R.string.toast_max_measurements), Toast.LENGTH_SHORT).show();
        } else if (v == delWindow) {
            if (winQnt > 1) {
                winTypeArray.get(winQnt - 1).getEditText().setText(null);
                winTypeArray.get(winQnt - 1).setVisibility(View.GONE);
                winHeightArray.get(winQnt - 1).getEditText().setText(null);
                winHeightArray.get(winQnt - 1).setVisibility(View.GONE);
                winQnt--;
                if (winQnt == 1)
                    delWindow.setVisibility(View.GONE);
            }
        } else if (v == continueSink) {
            if (checkEmptyFields()) {
                if (rAccBundle2.getBoolean(FROM_BOX)) {
                    RestBoxAccTwoUpdate upTwo = upAccTwo(rAccBundle2);
                    ViewModelEntry.updateBoxAccTwo(upTwo);
                } else {
                    RestAccessUpdateTwo upTwo = updateTwo(rAccBundle2);
                    ViewModelEntry.updateRestAccessDataTwo(upTwo);
                }
                callSinkFragment(rAccBundle2);
            } else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        } else {
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        }
    }

    private RestAccessUpdateTwo updateTwo(Bundle bundle) {
        int hasEmer, hasValve, hasMirror;
        Integer hasWindow = null, valveType = null;
        Double emerHeight = null, valveHeight = null,  height1 = null, height2 = null, height3 = null, mirA = null, mirB = null;
        String emerObs = null, valveObs = null, type1 = null, type2 = null, type3 = null, winObs = null, mirObs = null;

        hasEmer = getCheckedRadio(hasEmergencyRadio);
        if (hasEmer == 1) {
            if (!TextUtils.isEmpty(emergencyHeightValue.getText()))
                emerHeight = Double.parseDouble(String.valueOf(emergencyHeightValue.getText()));
        }
        if (!TextUtils.isEmpty(emergencyObsValue.getText()))
            emerObs = String.valueOf(emergencyObsValue.getText());

        hasValve = getCheckedRadio(hasValveRadio);
        if (hasValve == 1) {
            if (getCheckedRadio(valveTypeRadio) != -1)
                valveType = getCheckedRadio(valveTypeRadio);
            if (!TextUtils.isEmpty(valveHeightValue.getText()))
                valveHeight = Double.parseDouble(String.valueOf(valveHeightValue.getText()));
        }
        if (!TextUtils.isEmpty(valveObsValue.getText()))
            valveObs = String.valueOf(valveObsValue.getText());

        if (!rAccBundle2.getBoolean(FROM_BOX)) {
            hasWindow = getCheckedRadio(hasWindowRadio);
            if (hasWindow == 1) {
                switch (winQnt) {
                    case 3:
                        if (!TextUtils.isEmpty(winHeightValue3.getText()))
                            height3 = Double.parseDouble(String.valueOf(winHeightValue3.getText()));
                        if (!TextUtils.isEmpty(winTypeValue3.getText()))
                            type3 = String.valueOf(winTypeValue3.getText());
                    case 2:
                        if (!TextUtils.isEmpty(winHeightValue2.getText()))
                            height2 = Double.parseDouble(String.valueOf(winHeightValue2.getText()));
                        if (!TextUtils.isEmpty(winTypeValue2.getText()))
                            type2 = String.valueOf(winTypeValue2.getText());
                    case 1:
                        if (!TextUtils.isEmpty(winHeightValue1.getText()))
                            height1 = Double.parseDouble(String.valueOf(winHeightValue1.getText()));
                        if (!TextUtils.isEmpty(winTypeValue1.getText()))
                            type1 = String.valueOf(winTypeValue1.getText());
                        break;
                }
            }
            if (!TextUtils.isEmpty(winObsValue.getText()))
                winObs = String.valueOf(winObsValue.getText());
        }

        hasMirror = getCheckedRadio(mirrorRadio);
        if (hasMirror == 1) {
            if (!TextUtils.isEmpty(mirrorValueA.getText()))
                mirA = Double.parseDouble(String.valueOf(mirrorValueA.getText()));
            if (!TextUtils.isEmpty(mirrorValueB.getText()))
                mirB = Double.parseDouble(String.valueOf(mirrorValueB.getText()));
        }
        if (!TextUtils.isEmpty(mirrorObsValue.getText()))
            mirObs = String.valueOf(mirrorObsValue.getText());

        return new RestAccessUpdateTwo(bundle.getInt(REST_ID), hasEmer, emerHeight, emerObs, hasValve, valveType, valveHeight, valveObs, hasWindow, winQnt, type1, height1,
                type2, height2, type3, height3, winObs, hasMirror, mirA, mirB, mirObs);
    }

    private RestBoxAccTwoUpdate upAccTwo(Bundle bundle) {
        int hasEmer, hasValve, hasWindow, hasMirror;
        Integer valveType = null;
        Double emerHeight = null, valveHeight = null, height1 = null, height2 = null, height3 = null, mirA = null, mirB = null;
        String emerObs = null, valveObs = null, type1 = null, type2 = null, type3 = null, winObs = null, mirObs = null;

        hasEmer = getCheckedRadio(hasEmergencyRadio);
        if (hasEmer == 1) {
            if (!TextUtils.isEmpty(emergencyHeightValue.getText()))
                emerHeight = Double.parseDouble(String.valueOf(emergencyHeightValue.getText()));
        }
        if (!TextUtils.isEmpty(emergencyObsValue.getText()))
            emerObs = String.valueOf(emergencyObsValue.getText());

        hasValve = getCheckedRadio(hasValveRadio);
        if (hasValve == 1) {
            if (getCheckedRadio(valveTypeRadio) != -1)
                valveType = getCheckedRadio(valveTypeRadio);
            if (!TextUtils.isEmpty(valveHeightValue.getText()))
                valveHeight = Double.parseDouble(String.valueOf(valveHeightValue.getText()));
        }
        if (!TextUtils.isEmpty(valveObsValue.getText()))
            valveObs = String.valueOf(valveObsValue.getText());

        hasWindow = getCheckedRadio(hasWindowRadio);
        if (hasWindow == 1) {
            switch (winQnt) {
                case 3:
                    if (!TextUtils.isEmpty(winHeightValue3.getText()))
                        height3 = Double.parseDouble(String.valueOf(winHeightValue3.getText()));
                    if (!TextUtils.isEmpty(winTypeValue3.getText()))
                        type3 = String.valueOf(winTypeValue3.getText());
                case 2:
                    if (!TextUtils.isEmpty(winHeightValue2.getText()))
                        height2 = Double.parseDouble(String.valueOf(winHeightValue2.getText()));
                    if (!TextUtils.isEmpty(winTypeValue2.getText()))
                        type2 = String.valueOf(winTypeValue2.getText());
                case 1:
                    if (!TextUtils.isEmpty(winHeightValue1.getText()))
                        height1 = Double.parseDouble(String.valueOf(winHeightValue1.getText()));
                    if (!TextUtils.isEmpty(winTypeValue1.getText()))
                        type1 = String.valueOf(winTypeValue1.getText());
                    break;
            }
        }
        if (!TextUtils.isEmpty(winObsValue.getText()))
            winObs = String.valueOf(winObsValue.getText());

        hasMirror = getCheckedRadio(mirrorRadio);
        if (hasMirror == 1) {
            if (!TextUtils.isEmpty(mirrorValueA.getText()))
                mirA = Double.parseDouble(String.valueOf(mirrorValueA.getText()));
            if (!TextUtils.isEmpty(mirrorValueB.getText()))
                mirB = Double.parseDouble(String.valueOf(mirrorValueB.getText()));
        }
        if (!TextUtils.isEmpty(mirrorObsValue.getText()))
            mirObs = String.valueOf(mirrorObsValue.getText());

        return new RestBoxAccTwoUpdate(bundle.getInt(BOX_ID), hasEmer, emerHeight, emerObs, hasValve, valveType, valveHeight, valveObs, hasWindow, winQnt, type1, height1,
                type2, height2, type3, height3, winObs, hasMirror, mirA, mirB, mirObs);
    }

    private void imgExpandClick(View view) {
        if (view == mirrorImage)
            imgBundle.putInt(IMAGE_ID, R.drawable.wallmirror);
        ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundle);
    }

    private void viewsToArrays() {
        accessObsArray.add(emergencyObsValue);
        accessObsArray.add(valveObsValue);
        accessObsArray.add(winObsValue);
        accessObsArray.add(mirrorObsValue);

        winTypeArray.add(winTypeField1);
        winTypeArray.add(winTypeField2);
        winTypeArray.add(winTypeField3);

        winHeightArray.add(winHeightField1);
        winHeightArray.add(winHeightField2);
        winHeightArray.add(winHeightField3);
    }
}