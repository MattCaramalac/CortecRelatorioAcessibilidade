package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

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
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExtAccessSocialTwo;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.DoorLockListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import java.util.ArrayList;

public class ExtAccessSocialFragment extends Fragment {

    public static final String NEW_REGISTER_ACCESS = "NEW_REGISTER_ACCESS";

    RadioGroup hasSIARadio, gateHandleRadio, hasGateTracksRadio, hasTrackRampRadio;
    MultiLineRadioGroup gateTypeRadio;
    TextView siaError, gateTypeError, gateHandleHeader, gateHandleError, gateLockHeader, gateTrackError, hasTrackRampHeader, trackRampError, trackRampValueError;
    TextInputLayout siaObsField, gateTypeDescField, freeSpaceWidthField1, freeSpaceWidthField2, gateHandleHeightField, gateObsField, trackHeightField,
            trackRampField1, trackRampField2, trackRampField3, trackRampField4;
    TextInputEditText siaObsValue, gateTypeDescValue, freeSpaceWidthValue1, freeSpaceWidthValue2, gateHandleHeightValue, gateObsValue, trackHeightValue,
            trackRampValue1, trackRampValue2, trackRampValue3, trackRampValue4;
    MaterialButton addGateLockButton, addTrackRampButton, returnButton, continueButton;
    ImageButton delTrackRampButton;

    ViewModelEntry modelEntry;

    ViewModelFragments modelFragments;

    FragmentManager manager;

    int rampTrackCounter = 0;

    ArrayList<TextInputLayout> rampTrackFields = new ArrayList<>();

    Bundle extAccSocialBundle = new Bundle();

    public ExtAccessSocialFragment() {
        // Required empty public constructor
    }

    public static ExtAccessSocialFragment newInstance() {
        return new ExtAccessSocialFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            extAccSocialBundle.putInt(ExternalAccessFragment.EXT_ACCESS_ID, this.getArguments().getInt(ExternalAccessFragment.EXT_ACCESS_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ext_access_social, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        instantiateSocialViews(view);

        if (extAccSocialBundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID) == 0) {
            modelEntry.getLastExternalAccess().observe(getViewLifecycleOwner(), access -> {
                extAccSocialBundle.putInt(ExternalAccessFragment.EXT_ACCESS_ID, access.getExternalAccessID());
                extAccSocialBundle.putBoolean(NEW_REGISTER_ACCESS, true);
            });
        } else if (extAccSocialBundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID) > 0) {
            modelEntry.getOneExternalAccess(extAccSocialBundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID)).observe(getViewLifecycleOwner(), this::loadSocialFragData);
        }

        addTrackRampButton.setOnClickListener(v -> {
            if (rampTrackCounter < 0) {
                rampTrackCounter = 0;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (rampTrackCounter < 4) {
                if (rampTrackCounter == 0)
                    delTrackRampButton.setVisibility(View.VISIBLE);
                rampTrackFields.get(rampTrackCounter).setVisibility(View.VISIBLE);
                rampTrackCounter++;
            } else
                Toast.makeText(getContext(), "O limite de medições foi atingido!", Toast.LENGTH_SHORT).show();
        });

        delTrackRampButton.setOnClickListener(v -> {
            if (rampTrackCounter > 0) {
                rampTrackFields.get(rampTrackCounter - 1).getEditText().setText(null);
                rampTrackFields.get(rampTrackCounter - 1).setVisibility(View.GONE);
                rampTrackCounter--;
                if (rampTrackCounter == 0)
                    delTrackRampButton.setVisibility(View.GONE);
            }
        });

        returnButton.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        continueButton.setOnClickListener(v -> openNextFragment(extAccSocialBundle, v));

        addGateLockButton.setOnClickListener(v -> openNextFragment(extAccSocialBundle, v));
    }

    private void instantiateSocialViews(View v) {
//        RadioGroups
        hasSIARadio = v.findViewById(R.id.has_SIA_radio);
        gateHandleRadio = v.findViewById(R.id.gate_handle_type_radio);
        hasGateTracksRadio = v.findViewById(R.id.has_gate_tracks_radio);
        hasTrackRampRadio = v.findViewById(R.id.gate_has_track_ramp_radio);
//        MultilineRadioGroup
        gateTypeRadio = v.findViewById(R.id.ext_gate_type_radio);
//        TextViews
        siaError = v.findViewById(R.id.has_SIA_error);
        gateTrackError = v.findViewById(R.id.has_gate_tracks_error);
        hasTrackRampHeader = v.findViewById(R.id.label_gate_has_track_ramp);
        trackRampError = v.findViewById(R.id.has_track_ramp_error);
        gateTypeError = v.findViewById(R.id.ext_gate_type_error);
        gateHandleHeader = v.findViewById(R.id.label_gate_handle);
        gateHandleError = v.findViewById(R.id.gate_handle_type_error);
        gateLockHeader = v.findViewById(R.id.label_gate_lock_register);
        trackRampValueError = v.findViewById(R.id.ramp_track_values_error);
//        TextInputLayouts
        siaObsField = v.findViewById(R.id.social_entrance_sia_obs_field);
        gateTypeDescField = v.findViewById(R.id.gate_type_desc_field);
        freeSpaceWidthField1 = v.findViewById(R.id.free_space_width_1_field);
        freeSpaceWidthField2 = v.findViewById(R.id.free_space_width_2_field);
        gateHandleHeightField = v.findViewById(R.id.gate_handle_height_field);
        gateObsField = v.findViewById(R.id.gate_obs_field);
        trackHeightField = v.findViewById(R.id.ext_tracks_height_field);
        trackRampField1 = v.findViewById(R.id.ramp_track_1_field);
        trackRampField2 = v.findViewById(R.id.ramp_track_2_field);
        trackRampField3 = v.findViewById(R.id.ramp_track_3_field);
        trackRampField4 = v.findViewById(R.id.ramp_track_4_field);
//        TextInputEditText
        siaObsValue = v.findViewById(R.id.social_entrance_sia_obs_value);
        gateTypeDescValue = v.findViewById(R.id.gate_type_desc_value);
        freeSpaceWidthValue1 = v.findViewById(R.id.free_space_width_1_value);
        freeSpaceWidthValue2 = v.findViewById(R.id.free_space_width_2_value);
        gateHandleHeightValue = v.findViewById(R.id.gate_handle_height_value);
        gateObsValue = v.findViewById(R.id.gate_obs_value);
        trackHeightValue = v.findViewById(R.id.ext_tracks_height_value);
        trackRampValue1 = v.findViewById(R.id.ramp_track_1_value);
        trackRampValue2 = v.findViewById(R.id.ramp_track_2_value);
        trackRampValue3 = v.findViewById(R.id.ramp_track_3_value);
        trackRampValue4 = v.findViewById(R.id.ramp_track_4_value);
//        MaterialButtons
        addTrackRampButton = v.findViewById(R.id.add_gate_track_ramp_button);
        addGateLockButton = v.findViewById(R.id.add_gate_lock_button);
        returnButton = v.findViewById(R.id.return_ext_access_button);
        continueButton = v.findViewById(R.id.continue_ext_access2_button);
//        ImageButton
        delTrackRampButton = v.findViewById(R.id.delete_ramp_track_measure);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
//        Fragment Manager
        manager = getChildFragmentManager();
//        Obs Fields Scrolling
        allowObsScroll();
//        RadioListeners
        hasGateTracksRadio.setOnCheckedChangeListener(this::extAccessRadioListener);
        hasTrackRampRadio.setOnCheckedChangeListener(this::extAccessRadioListener);
//        MultiRadio Listeners
        gateTypeRadio.setOnCheckedChangeListener((MultiLineRadioGroup.OnCheckedChangeListener)
                (view, r) -> extAccessOneMultiRadioListener(gateTypeRadio));
//        ArrayList
        addRampTrackToArrays();
    }

    private void openNextFragment(Bundle bundle, View v) {
        Fragment frag;
        if (v == continueButton) {
            if (socialFragOneNoEmptyFields()) {
                ExtAccessSocialTwo newSocialTwo = socialTwo(bundle);
                ViewModelEntry.updateExtAccessRegTwo(newSocialTwo);
                frag = new ExtAccessSocialFragment2();
                frag.setArguments(bundle);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, frag)
                        .addToBackStack(null).commit();
            }
        } else if (v == addGateLockButton) {
            ExtAccessSocialTwo newSocialTwo = socialTwo(bundle);
            ViewModelEntry.updateExtAccessRegTwo(newSocialTwo);
            frag = new DoorLockListFragment();
            frag.setArguments(bundle);
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, frag)
                    .addToBackStack(null).commit();
        }
    }

    private void addRampTrackToArrays() {
        rampTrackFields.add(trackRampField1);
        rampTrackFields.add(trackRampField2);
        rampTrackFields.add(trackRampField3);
        rampTrackFields.add(trackRampField4);
    }

    private void extAccessRadioListener(RadioGroup radio, int checkedID) {
        int index = radio.indexOfChild(radio.findViewById(checkedID));
        if (radio == hasGateTracksRadio) {
            if (index == 1) {
                trackHeightField.setVisibility(View.VISIBLE);
                hasTrackRampHeader.setVisibility(View.VISIBLE);
                hasTrackRampRadio.setVisibility(View.VISIBLE);
            } else {
                trackHeightValue.setText(null);
                trackHeightField.setVisibility(View.GONE);
                hasTrackRampHeader.setVisibility(View.GONE);
                hasTrackRampRadio.clearCheck();
                hasTrackRampRadio.setVisibility(View.GONE);
            }
        } else if (radio == hasTrackRampRadio) {
            if (index == 1)
                addTrackRampButton.setVisibility(View.VISIBLE);
            else
                addTrackRampButton.setVisibility(View.GONE);
        }
    }

    private void extAccessOneMultiRadioListener(MultiLineRadioGroup multi) {
        int index = multi.getCheckedRadioButtonIndex();
        clearMultilineFields();
        switch (index) {
            case 0:
                freeSpaceWidthField1.setVisibility(View.VISIBLE);
                freeSpaceWidthField1.setHint("Largura do Vão Livre (m)");
                break;
            case 1:
                freeSpaceWidthField1.setVisibility(View.VISIBLE);
                freeSpaceWidthField1.setHint("Largura do Vão Livre - Folha 1 (m)");
                freeSpaceWidthField2.setVisibility(View.VISIBLE);
                break;
            case 2:
                freeSpaceWidthField1.setVisibility(View.VISIBLE);
                freeSpaceWidthField1.setHint("Largura do Vão Livre (m)");
                gateTypeDescField.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
        gateHandleHeader.setVisibility(View.VISIBLE);
        gateHandleRadio.setVisibility(View.VISIBLE);
        gateHandleHeightField.setVisibility(View.VISIBLE);
        gateLockHeader.setVisibility(View.VISIBLE);
        addGateLockButton.setVisibility(View.VISIBLE);
        gateObsField.setVisibility(View.VISIBLE);
    }

    private void clearMultilineFields() {
        gateTypeDescValue.setText(null);
        gateTypeDescField.setVisibility(View.GONE);
        freeSpaceWidthValue1.setText(null);
        freeSpaceWidthField1.setVisibility(View.GONE);
        freeSpaceWidthValue2.setText(null);
        freeSpaceWidthField2.setVisibility(View.GONE);
    }

    private void loadSocialFragData(ExternalAccess access) {
        if (access.getHasSIA() != null)
            hasSIARadio.check(hasSIARadio.getChildAt(access.getHasSIA()).getId());
        if (access.getObsSIA() != null)
            siaObsValue.setText(access.getObsSIA());

        if (access.getEntranceGateType() != null) {
            gateTypeRadio.checkAt(access.getEntranceGateType());
            freeSpaceWidthValue1.setText(String.valueOf(access.getFreeSpaceWidth1()));
            if (access.getEntranceGateType() == 1)
                freeSpaceWidthValue2.setText(String.valueOf(access.getFreeSpaceWidth2()));
            else if (access.getEntranceGateType() == 2)
                gateTypeDescValue.setText(access.getEntranceGateDesc());
            gateHandleRadio.check(gateHandleRadio.getChildAt(access.getGateHandleType()).getId());
            gateHandleHeightValue.setText(String.valueOf(access.getGateHandleHeight()));
        }

        if (access.getGateHasTracks() != null) {
            hasGateTracksRadio.check(hasGateTracksRadio.getChildAt(access.getGateHasTracks()).getId());
            if (access.getGateHasTracks() == 1) {
                if (access.getGateTrackHeight() != null)
                    trackHeightValue.setText(String.valueOf(access.getGateTrackHeight()));
                if (access.getGateHasTrackRamp() != null) {
                    hasTrackRampRadio.check(hasTrackRampRadio.getChildAt(access.getGateHasTrackRamp()).getId());
                    rampTrackCounter = access.getTrackRampQuantity();
                    if (rampTrackCounter > 0)
                        delTrackRampButton.setVisibility(View.VISIBLE);
                    switch (rampTrackCounter) {
                        case 4:
                            trackRampField4.setVisibility(View.VISIBLE);
                            trackRampValue4.setText(String.valueOf(access.getTrackRampMeasure4()));
                        case 3:
                            trackRampField3.setVisibility(View.VISIBLE);
                            trackRampValue3.setText(String.valueOf(access.getTrackRampMeasure3()));
                        case 2:
                            trackRampField2.setVisibility(View.VISIBLE);
                            trackRampValue2.setText(String.valueOf(access.getTrackRampMeasure2()));
                        case 1:
                            trackRampField1.setVisibility(View.VISIBLE);
                            trackRampValue1.setText(String.valueOf(access.getTrackRampMeasure1()));
                        default:
                            break;
                    }
                }
            }
        }
    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    private boolean socialFragOneNoEmptyFields() {
        clearSocialFragOneErrors();
        int i = 0;

        if (getCheckedRadioIndex(hasSIARadio) == -1) {
            i++;
            siaError.setVisibility(View.VISIBLE);
        }
        if (gateTypeRadio.getCheckedRadioButtonIndex() == -1) {
            i++;
            gateTypeError.setVisibility(View.VISIBLE);
        } else {
            if (TextUtils.isEmpty(freeSpaceWidthValue1.getText())) {
                i++;
                freeSpaceWidthField1.setError(getString(R.string.blank_field_error));
            }
            if (gateTypeRadio.getCheckedRadioButtonIndex() == 1) {
                if (TextUtils.isEmpty(freeSpaceWidthValue2.getText())) {
                    i++;
                    freeSpaceWidthField2.setError(getString(R.string.blank_field_error));
                }
            } else if (gateTypeRadio.getCheckedRadioButtonIndex() == 2) {
                if (TextUtils.isEmpty(gateTypeDescValue.getText())) {
                    i++;
                    gateTypeDescField.setError(getString(R.string.blank_field_error));
                }
            }
            if (getCheckedRadioIndex(gateHandleRadio) == -1) {
                i++;
                gateHandleError.setVisibility(View.VISIBLE);
            }
            if (TextUtils.isEmpty(gateHandleHeightValue.getText())) {
                i++;
                gateHandleHeightField.setError(getString(R.string.blank_field_error));
            }
        }

        if (getCheckedRadioIndex(hasGateTracksRadio) == -1) {
            i++;
            gateTrackError.setVisibility(View.VISIBLE);
        } else if (getCheckedRadioIndex(hasGateTracksRadio) == 1) {
            if (TextUtils.isEmpty(trackHeightValue.getText())) {
                i++;
                trackHeightField.setError(getString(R.string.blank_field_error));
            }
            if (getCheckedRadioIndex(hasTrackRampRadio) == -1) {
                i++;
                trackRampError.setVisibility(View.VISIBLE);
            } else if (getCheckedRadioIndex(hasTrackRampRadio) == 1) {
                switch (rampTrackCounter) {
                    case 4:
                        if (TextUtils.isEmpty(trackRampValue4.getText())) {
                            i++;
                            trackRampValueError.setVisibility(View.VISIBLE);
                        }
                    case 3:
                        if (TextUtils.isEmpty(trackRampValue3.getText())) {
                            i++;
                            trackRampValueError.setVisibility(View.VISIBLE);
                        }
                    case 2:
                        if (TextUtils.isEmpty(trackRampValue2.getText())) {
                            i++;
                            trackRampValueError.setVisibility(View.VISIBLE);
                        }
                    case 1:
                        if (TextUtils.isEmpty(trackRampValue1.getText())) {
                            i++;
                            trackRampValueError.setVisibility(View.VISIBLE);
                        }
                    default:
                        break;
                }
            }
        }
        return i == 0;
    }

    private void clearSocialFragOneErrors() {
        siaError.setVisibility(View.GONE);
        gateTypeError.setVisibility(View.GONE);
        gateTypeDescField.setErrorEnabled(false);
        freeSpaceWidthField1.setErrorEnabled(false);
        freeSpaceWidthField2.setErrorEnabled(false);
        gateHandleHeightField.setErrorEnabled(false);
        gateHandleError.setVisibility(View.GONE);
        gateTrackError.setVisibility(View.GONE);
        trackHeightField.setErrorEnabled(false);
        trackRampError.setVisibility(View.GONE);
        trackRampValueError.setVisibility(View.GONE);
    }

    private int getCheckedRadioIndex(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowObsScroll() {
        siaObsValue.setOnTouchListener(this::scrollingField);
        gateObsValue.setOnTouchListener(this::scrollingField);
    }

    private ExtAccessSocialTwo socialTwo(Bundle bundle) {
        Integer sia = null, gateType = null, gateTracks = null, gateHandle = null, gateTrackRamps = null;
        String siaObs = null, gateDesc = null, gateObs = null;
        Double fSpace1 = null, fSpace2 = null, handleHeight = null, trackHeight = null, rampMeasure1 = null, rampMeasure2 = null, rampMeasure3 = null, rampMeasure4 = null;

        if (getCheckedRadioIndex(hasSIARadio) > -1)
            sia = getCheckedRadioIndex(hasSIARadio);
        if (!TextUtils.isEmpty(siaObsValue.getText()))
            siaObs = String.valueOf(siaObsValue.getText());
        if (gateTypeRadio.getCheckedRadioButtonIndex() > -1) {
            gateType = gateTypeRadio.getCheckedRadioButtonIndex();
            fSpace1 = Double.parseDouble(String.valueOf(freeSpaceWidthValue1.getText()));
            switch (gateType) {
                case 2:
                    if (!TextUtils.isEmpty(gateTypeDescValue.getText()))
                        gateDesc = String.valueOf(gateTypeDescValue.getText());
                    break;
                case 1:
                    if (!TextUtils.isEmpty(freeSpaceWidthValue2.getText()))
                        fSpace2 = Double.parseDouble(String.valueOf(freeSpaceWidthValue2.getText()));
                    break;
                default:
                    break;
            }
            if (getCheckedRadioIndex(gateHandleRadio) > -1) {
                gateHandle = getCheckedRadioIndex(gateHandleRadio);
                if (!TextUtils.isEmpty(gateHandleHeightValue.getText()))
                    handleHeight = Double.parseDouble(String.valueOf(gateHandleHeightValue.getText()));
            }
            if (!TextUtils.isEmpty(gateObsValue.getText()))
                gateObs = String.valueOf(gateObsValue.getText());
        }
        if (getCheckedRadioIndex(hasGateTracksRadio) > -1) {
            gateTracks = getCheckedRadioIndex(hasGateTracksRadio);
            if (gateTracks == 1) {
                if (!TextUtils.isEmpty(trackHeightValue.getText()))
                    trackHeight = Double.parseDouble(String.valueOf(trackHeightValue.getText()));
                if (getCheckedRadioIndex(hasTrackRampRadio) > -1) {
                    gateTrackRamps = getCheckedRadioIndex(hasTrackRampRadio);
                    if (gateTrackRamps == 1) {
                        switch (rampTrackCounter) {
                            case 4:
                                if (!TextUtils.isEmpty(trackRampValue4.getText()))
                                    rampMeasure4 = Double.parseDouble(String.valueOf(trackRampValue4.getText()));
                            case 3:
                                if (!TextUtils.isEmpty(trackRampValue3.getText()))
                                    rampMeasure3 = Double.parseDouble(String.valueOf(trackRampValue3.getText()));
                            case 2:
                                if (!TextUtils.isEmpty(trackRampValue2.getText()))
                                    rampMeasure2 = Double.parseDouble(String.valueOf(trackRampValue2.getText()));
                            case 1:
                                if (!TextUtils.isEmpty(trackRampValue1.getText()))
                                    rampMeasure1 = Double.parseDouble(String.valueOf(trackRampValue1.getText()));
                            default:
                                break;
                        }
                    }
                }
            }
        }
        return new ExtAccessSocialTwo(bundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID), sia, siaObs, gateType, gateDesc, fSpace1, fSpace2, gateHandle, handleHeight,
                gateObs, gateTracks, trackHeight, gateTrackRamps, rampTrackCounter, rampMeasure1, rampMeasure2, rampMeasure3, rampMeasure4);
    }

}