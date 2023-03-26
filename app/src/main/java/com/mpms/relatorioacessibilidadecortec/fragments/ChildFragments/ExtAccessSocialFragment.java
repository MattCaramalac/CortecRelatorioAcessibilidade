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
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExtAccessSocialTwo;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.DoorLockListFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import java.util.ArrayList;

public class ExtAccessSocialFragment extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

    RadioGroup hasSIARadio, gateHandleRadio, hasGateTracksRadio, hasTrackRampRadio, hasTrackGapsRadio;
    MultiLineRadioGroup gateTypeRadio;
    TextView siaError, gateTypeError, gateHandleHeader, gateHandleError, gateLockHeader, gateTrackError, hasTrackRampHeader, trackRampError, trackGapHeader, trackGapError;
    TextInputLayout siaObsField, gateTypeDescField, freeSpaceWidthField1, freeSpaceWidthField2, gateHandleHeightField, gateObsField, trackHeightField,
            trackRampField1, trackRampField2, trackRampField3, trackRampField4, trackGapField1, trackGapField2, trackGapField3, trackGapField4, photoField;
    TextInputEditText siaObsValue, gateTypeDescValue, freeSpaceWidthValue1, freeSpaceWidthValue2, gateHandleHeightValue, gateObsValue, trackHeightValue,
            trackRampValue1, trackRampValue2, trackRampValue3, trackRampValue4, trackGapValue1, trackGapValue2, trackGapValue3, trackGapValue4, photoValue;
    MaterialButton addGateLockButton, addTrackRampButton, addTrackGapButton, returnButton, continueButton;
    ImageButton delTrackRampButton, delTrackGapButton;

    ViewModelEntry modelEntry;

    ViewModelFragments modelFragments;

    FragmentManager manager;

    int rampTrackCounter = 0;
    int trackGapCounter = 0;
    ArrayList<TextInputEditText> eText = new ArrayList<>();
    ArrayList<TextInputLayout> rampTrackFields = new ArrayList<>();
    ArrayList<TextInputLayout> trackGapFields = new ArrayList<>();

    Bundle extAccSocialBundle;

    public ExtAccessSocialFragment() {
        // Required empty public constructor
    }

    public static ExtAccessSocialFragment newInstance() {
        return new ExtAccessSocialFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            extAccSocialBundle = new Bundle(this.getArguments());
        else
            extAccSocialBundle = new Bundle();
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

        if (extAccSocialBundle.getInt(AMBIENT_ID) == 0)
            modelEntry.getLastExternalAccess().observe(getViewLifecycleOwner(), access ->
                    extAccSocialBundle.putInt(AMBIENT_ID, access.getExternalAccessID()));
        else if (extAccSocialBundle.getInt(AMBIENT_ID) > 0)
            modelEntry.getOneExternalAccess(extAccSocialBundle.getInt(AMBIENT_ID))
                    .observe(getViewLifecycleOwner(), this::loadSocialFragData);

        returnButton.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        continueButton.setOnClickListener(v -> openNextFragment(extAccSocialBundle, v));

        addGateLockButton.setOnClickListener(v -> openNextFragment(extAccSocialBundle, v));
    }

    @Override
    public void onResume() {
        super.onResume();
        extAccSocialBundle.putBoolean(FROM_EXT_ACCESS, false);
        if (trackGapFields.size() == 0 && rampTrackFields.size() == 0)
            addRampTrackToArrays();
    }

    private void instantiateSocialViews(View v) {
//        RadioGroups
        hasSIARadio = v.findViewById(R.id.has_SIA_radio);
        gateHandleRadio = v.findViewById(R.id.gate_handle_type_radio);
        hasGateTracksRadio = v.findViewById(R.id.has_gate_tracks_radio);
        hasTrackRampRadio = v.findViewById(R.id.gate_has_track_ramp_radio);
        hasTrackGapsRadio = v.findViewById(R.id.has_track_gaps_radio);
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
        trackGapHeader = v.findViewById(R.id.label_has_track_gaps);
        trackGapError = v.findViewById(R.id.track_gap_radio_error);
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
        trackGapField1 = v.findViewById(R.id.track_gap_1_field);
        trackGapField2 = v.findViewById(R.id.track_gap_2_field);
        trackGapField3 = v.findViewById(R.id.track_gap_3_field);
        trackGapField4 = v.findViewById(R.id.track_gap_4_field);
        photoField = v.findViewById(R.id.ext_acc_social_photos_field_1);
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
        trackGapValue1 = v.findViewById(R.id.track_gap_1_value);
        trackGapValue2 = v.findViewById(R.id.track_gap_2_value);
        trackGapValue3 = v.findViewById(R.id.track_gap_3_value);
        trackGapValue4 = v.findViewById(R.id.track_gap_4_value);
        photoValue = v.findViewById(R.id.ext_acc_social_photos_value_1);
//        MaterialButtons
        addTrackRampButton = v.findViewById(R.id.add_gate_track_ramp_button);
        addTrackGapButton = v.findViewById(R.id.add_track_gap_button);
        addGateLockButton = v.findViewById(R.id.add_gate_lock_button);
        returnButton = v.findViewById(R.id.return_ext_access_button);
        continueButton = v.findViewById(R.id.continue_ext_access2_button);
//        ImageButton
        delTrackRampButton = v.findViewById(R.id.delete_ramp_track_measure);
        delTrackGapButton = v.findViewById(R.id.delete_track_gap_measure);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
//        Fragment Manager
        manager = getChildFragmentManager();
//        Obs Fields Scrolling
        eText.add(siaObsValue);
        eText.add(gateObsValue);
        allowObsScroll(eText);
//        RadioListeners
        hasGateTracksRadio.setOnCheckedChangeListener(this::radioListener);
        hasTrackRampRadio.setOnCheckedChangeListener(this::radioListener);
        hasTrackGapsRadio.setOnCheckedChangeListener(this::radioListener);
//        MultiRadio Listeners
        gateTypeRadio.setOnCheckedChangeListener((MultiLineRadioGroup.OnCheckedChangeListener)
                (view, r) -> extAccessOneMultiRadioListener(gateTypeRadio));
//        ClickListeners
        addTrackRampButton.setOnClickListener(this::addFieldClickListener);
        delTrackRampButton.setOnClickListener(this::addFieldClickListener);
        addTrackGapButton.setOnClickListener(this::addFieldClickListener);
        delTrackGapButton.setOnClickListener(this::addFieldClickListener);
    }

    private void addFieldClickListener(View v) {
        if (v == addTrackRampButton) {
            if (rampTrackCounter < 1) {
                rampTrackCounter = 1;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (rampTrackCounter == 1) {
                delTrackRampButton.setVisibility(View.VISIBLE);
                trackRampField2.setVisibility(View.VISIBLE);
                rampTrackCounter++;
            } else if (rampTrackCounter == 2) {
                trackRampField3.setVisibility(View.VISIBLE);
                rampTrackCounter++;
            } else if (rampTrackCounter == 3) {
                trackRampField4.setVisibility(View.VISIBLE);
                rampTrackCounter++;
            } else
                Toast.makeText(getContext(), "O limite de medições foi atingido!", Toast.LENGTH_SHORT).show();
        } else if (v == delTrackRampButton) {
            if (rampTrackCounter < 1) {
                rampTrackCounter = 1;
                delTrackRampButton.setVisibility(View.GONE);
                trackRampValue2.setText(null);
                trackRampField2.setVisibility(View.GONE);
                trackRampValue3.setText(null);
                trackRampField3.setVisibility(View.GONE);
                trackRampValue4.setText(null);
                trackRampField4.setVisibility(View.GONE);
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (rampTrackCounter == 4) {
                trackRampValue4.setText(null);
                trackRampField4.setVisibility(View.GONE);
            } else if (rampTrackCounter == 3) {
                trackRampValue3.setText(null);
                trackRampField3.setVisibility(View.GONE);
            } else if (rampTrackCounter == 2) {
                trackRampValue2.setText(null);
                trackRampField2.setVisibility(View.GONE);
                delTrackRampButton.setVisibility(View.GONE);
            }
            if (rampTrackCounter > 1)
                rampTrackCounter--;
        } else if (v == addTrackGapButton) {
            if (trackGapCounter < 1) {
                trackGapCounter = 1;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (trackGapCounter == 1) {
                delTrackGapButton.setVisibility(View.VISIBLE);
                trackGapField2.setVisibility(View.VISIBLE);
                trackGapCounter++;
            } else if (trackGapCounter == 2) {
                trackGapField3.setVisibility(View.VISIBLE);
                trackGapCounter++;
            } else if (trackGapCounter == 3) {
                trackGapField4.setVisibility(View.VISIBLE);
                trackGapCounter++;
            } else
                Toast.makeText(getContext(), "O limite de medições foi atingido!", Toast.LENGTH_SHORT).show();
        } else if (v == delTrackGapButton) {
            if (trackGapCounter < 1) {
                trackGapCounter = 1;
                delTrackGapButton.setVisibility(View.GONE);
                trackGapValue2.setText(null);
                trackGapField2.setVisibility(View.GONE);
                trackGapValue3.setText(null);
                trackGapField3.setVisibility(View.GONE);
                trackGapValue4.setText(null);
                trackGapField4.setVisibility(View.GONE);
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (trackGapCounter == 4) {
                trackGapValue4.setText(null);
                trackGapField4.setVisibility(View.GONE);
            } else if (trackGapCounter == 3) {
                trackGapValue3.setText(null);
                trackGapField3.setVisibility(View.GONE);
            } else if (trackGapCounter == 2) {
                trackGapValue2.setText(null);
                trackGapField2.setVisibility(View.GONE);
                delTrackGapButton.setVisibility(View.GONE);
            }
            if (trackGapCounter > 1)
                trackGapCounter--;
        }
    }


    private void openNextFragment(Bundle bundle, View v) {
        Fragment frag = null;
        if (v == continueButton) {
            if (socialFragOneNoEmptyFields()) {
                ExtAccessSocialTwo newSocialTwo = socialTwo(bundle);
                ViewModelEntry.updateExtAccessRegTwo(newSocialTwo);
                frag = new ExtAccessSocialFragment2();
                frag.setArguments(bundle);
            } else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        } else if (v == addGateLockButton) {
            bundle.putBoolean(FROM_EXT_ACCESS, true);
            ExtAccessSocialTwo newSocialTwo = socialTwo(bundle);
            ViewModelEntry.updateExtAccessRegTwo(newSocialTwo);
            frag = new DoorLockListFragment();
            frag.setArguments(bundle);
        }
        if (frag != null)
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, frag)
                    .addToBackStack(null).commit();
    }

    private void addRampTrackToArrays() {
        rampTrackFields.add(trackRampField1);
        rampTrackFields.add(trackRampField2);
        rampTrackFields.add(trackRampField3);
        rampTrackFields.add(trackRampField4);

        trackGapFields.add(trackGapField1);
        trackGapFields.add(trackGapField2);
        trackGapFields.add(trackGapField3);
        trackGapFields.add(trackGapField4);
    }

    private void closeTrackRampFields() {
        trackRampError.setVisibility(View.GONE);
        addTrackRampButton.setVisibility(View.GONE);
        delTrackRampButton.setVisibility(View.GONE);
        for (TextInputLayout trackFields : rampTrackFields) {
            trackFields.getEditText().setText(null);
            trackFields.setVisibility(View.GONE);
        }
        rampTrackCounter = 0;
    }


    private void closeTrackGapFields() {
        trackGapError.setVisibility(View.GONE);
        addTrackGapButton.setVisibility(View.GONE);
        delTrackGapButton.setVisibility(View.GONE);
        for (TextInputLayout gapFields : trackGapFields) {
            gapFields.getEditText().setText(null);
            gapFields.setVisibility(View.GONE);
        }
        trackGapCounter = 0;
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
            checkRadioGroup(hasSIARadio, access.getHasSIA());
        if (access.getObsSIA() != null)
            siaObsValue.setText(access.getObsSIA());

        if (access.getEntranceGateType() != null) {
            gateTypeRadio.checkAt(access.getEntranceGateType());
            if (access.getFreeSpaceWidth1() != null)
                freeSpaceWidthValue1.setText(String.valueOf(access.getFreeSpaceWidth1()));
            if (access.getEntranceGateType() == 1) {
                if (access.getFreeSpaceWidth2() != null)
                    freeSpaceWidthValue2.setText(String.valueOf(access.getFreeSpaceWidth2()));
            } else if (access.getEntranceGateType() == 2) {
                if (access.getEntranceGateDesc() != null) {
                    gateTypeDescValue.setText(access.getEntranceGateDesc());
                }
            }
        }

        if (access.getGateHandleType() != null)
            checkRadioGroup(gateHandleRadio, access.getGateHandleType());

        if (access.getGateHandleHeight() != null)
            gateHandleHeightValue.setText(String.valueOf(access.getGateHandleHeight()));

        if (access.getGateObs() != null)
            gateObsValue.setText(access.getGateObs());

        if (access.getGateHasTracks() != null) {
            checkRadioGroup(hasGateTracksRadio, access.getGateHasTracks());
            if (access.getGateHasTracks() == 1) {
                if (access.getGateTrackHeight() != null)
                    trackHeightValue.setText(String.valueOf(access.getGateTrackHeight()));
                if (access.getGateHasTrackRamp() != null) {
                    checkRadioGroup(hasTrackRampRadio, access.getGateHasTrackRamp());
                    rampTrackCounter = access.getTrackRampQuantity();
                    if (rampTrackCounter > 1)
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
            if (access.getHasFloorGap() != null) {
                checkRadioGroup(hasTrackGapsRadio, access.getHasFloorGap());
                trackGapCounter = access.getGapCounter();
                if (trackGapCounter > 1)
                    delTrackGapButton.setVisibility(View.VISIBLE);
                switch (trackGapCounter) {
                    case 4:
                        trackGapField4.setVisibility(View.VISIBLE);
                        trackGapValue4.setText(String.valueOf(access.getGapMeasure4()));
                    case 3:
                        trackGapField3.setVisibility(View.VISIBLE);
                        trackGapValue3.setText(String.valueOf(access.getGapMeasure3()));
                    case 2:
                        trackGapField2.setVisibility(View.VISIBLE);
                        trackGapValue2.setText(String.valueOf(access.getGapMeasure2()));
                    case 1:
                        trackGapField1.setVisibility(View.VISIBLE);
                        trackGapValue1.setText(String.valueOf(access.getGapMeasure1()));
                    default:
                        break;
                }
            }
        }

        if (access.getExtAccPhotos2() != null)
            photoValue.setText(access.getExtAccPhotos2());
    }

    private boolean socialFragOneNoEmptyFields() {
        clearSocialFragOneErrors();
        int i = 0;

        if (indexRadio(hasSIARadio) == -1) {
            i++;
            siaError.setVisibility(View.VISIBLE);
        }
        if (gateTypeRadio.getCheckedRadioButtonIndex() == -1) {
            i++;
            gateTypeError.setVisibility(View.VISIBLE);
        } else {
            if (TextUtils.isEmpty(freeSpaceWidthValue1.getText())) {
                i++;
                freeSpaceWidthField1.setError(getString(R.string.req_field_error));
            }
            if (gateTypeRadio.getCheckedRadioButtonIndex() == 1) {
                if (TextUtils.isEmpty(freeSpaceWidthValue2.getText())) {
                    i++;
                    freeSpaceWidthField2.setError(getString(R.string.req_field_error));
                }
            } else if (gateTypeRadio.getCheckedRadioButtonIndex() == 2) {
                if (TextUtils.isEmpty(gateTypeDescValue.getText())) {
                    i++;
                    gateTypeDescField.setError(getString(R.string.req_field_error));
                }
            }
            if (indexRadio(gateHandleRadio) == -1) {
                i++;
                gateHandleError.setVisibility(View.VISIBLE);
            }
            if (TextUtils.isEmpty(gateHandleHeightValue.getText())) {
                i++;
                gateHandleHeightField.setError(getString(R.string.req_field_error));
            }
        }

        if (indexRadio(hasGateTracksRadio) == -1) {
            i++;
            gateTrackError.setVisibility(View.VISIBLE);
        } else if (indexRadio(hasGateTracksRadio) == 1) {
            if (TextUtils.isEmpty(trackHeightValue.getText())) {
                i++;
                trackHeightField.setError(getString(R.string.req_field_error));
            }
            if (indexRadio(hasTrackRampRadio) == -1) {
                i++;
                trackRampError.setVisibility(View.VISIBLE);
            } else if (indexRadio(hasTrackRampRadio) == 1) {
                switch (rampTrackCounter) {
                    case 4:
                        if (TextUtils.isEmpty(trackRampValue4.getText())) {
                            i++;
                            trackRampField4.setError(getString(R.string.req_field_error));
                        }
                    case 3:
                        if (TextUtils.isEmpty(trackRampValue3.getText())) {
                            i++;
                            trackRampField3.setError(getString(R.string.req_field_error));
                        }
                    case 2:
                        if (TextUtils.isEmpty(trackRampValue2.getText())) {
                            i++;
                            trackRampField2.setError(getString(R.string.req_field_error));
                        }
                    case 1:
                        if (TextUtils.isEmpty(trackRampValue1.getText())) {
                            i++;
                            trackRampField1.setError(getString(R.string.req_field_error));
                        }
                    default:
                        break;
                }
            }
            if (indexRadio(hasTrackGapsRadio) == -1) {
                i++;
                trackGapError.setVisibility(View.VISIBLE);
            } else if (indexRadio(hasTrackGapsRadio) == 1) {
                switch (trackGapCounter) {
                    case 4:
                        if (TextUtils.isEmpty(trackGapValue4.getText())) {
                            i++;
                            trackGapField4.setError(getString(R.string.req_field_error));
                        }
                    case 3:
                        if (TextUtils.isEmpty(trackGapValue3.getText())) {
                            i++;
                            trackGapField3.setError(getString(R.string.req_field_error));
                        }
                    case 2:
                        if (TextUtils.isEmpty(trackGapValue2.getText())) {
                            i++;
                            trackGapField2.setError(getString(R.string.req_field_error));
                        }
                    case 1:
                        if (TextUtils.isEmpty(trackGapValue1.getText())) {
                            i++;
                            trackGapField1.setError(getString(R.string.req_field_error));
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
        trackRampField1.setErrorEnabled(false);
        trackRampField2.setErrorEnabled(false);
        trackRampField3.setErrorEnabled(false);
        trackRampField4.setErrorEnabled(false);
        trackGapError.setVisibility(View.GONE);
        trackGapField1.setErrorEnabled(false);
        trackGapField2.setErrorEnabled(false);
        trackGapField3.setErrorEnabled(false);
        trackGapField4.setErrorEnabled(false);

    }

    private ExtAccessSocialTwo socialTwo(Bundle bundle) {
        Integer sia = null, gateType = null, gateTracks = null, gateHandle = null, gateTrackRamps = null, floorGaps = null;
        String siaObs = null, gateDesc = null, gateObs = null, photos = null;
        Double fSpace1 = null, fSpace2 = null, handleHeight = null, trackHeight = null, rampMeasure1 = null, rampMeasure2 = null, rampMeasure3 = null, rampMeasure4 = null,
                gapMeasure1 = null, gapMeasure2 = null, gapMeasure3 = null, gapMeasure4 = null;

        if (indexRadio(hasSIARadio) > -1)
            sia = indexRadio(hasSIARadio);
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
            if (indexRadio(gateHandleRadio) > -1) {
                gateHandle = indexRadio(gateHandleRadio);
                if (!TextUtils.isEmpty(gateHandleHeightValue.getText()))
                    handleHeight = Double.parseDouble(String.valueOf(gateHandleHeightValue.getText()));
            }
            if (!TextUtils.isEmpty(gateObsValue.getText()))
                gateObs = String.valueOf(gateObsValue.getText());
        }
        if (indexRadio(hasGateTracksRadio) > -1) {
            gateTracks = indexRadio(hasGateTracksRadio);
            if (gateTracks == 1) {
                if (!TextUtils.isEmpty(trackHeightValue.getText()))
                    trackHeight = Double.parseDouble(String.valueOf(trackHeightValue.getText()));
                if (indexRadio(hasTrackRampRadio) > -1) {
                    gateTrackRamps = indexRadio(hasTrackRampRadio);
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
                if (indexRadio(hasTrackGapsRadio) > -1) {
                    floorGaps = indexRadio(hasTrackGapsRadio);
                    if (floorGaps == 1) {
                        switch (trackGapCounter) {
                            case 4:
                                if (!TextUtils.isEmpty(trackGapValue4.getText()))
                                    gapMeasure4 = Double.parseDouble(String.valueOf(trackGapValue4.getText()));
                            case 3:
                                if (!TextUtils.isEmpty(trackGapValue3.getText()))
                                    gapMeasure3 = Double.parseDouble(String.valueOf(trackGapValue3.getText()));
                            case 2:
                                if (!TextUtils.isEmpty(trackGapValue2.getText()))
                                    gapMeasure2 = Double.parseDouble(String.valueOf(trackGapValue2.getText()));
                            case 1:
                                if (!TextUtils.isEmpty(trackGapValue1.getText()))
                                    gapMeasure1 = Double.parseDouble(String.valueOf(trackGapValue1.getText()));
                            default:
                                break;
                        }
                    }
                }
            }
        }

        if (!TextUtils.isEmpty(photoValue.getText()))
            photos = String.valueOf(photoValue.getText());

        return new ExtAccessSocialTwo(bundle.getInt(AMBIENT_ID), sia, siaObs, gateType, gateDesc, fSpace1, fSpace2, gateHandle, handleHeight,
                gateObs, gateTracks, trackHeight, gateTrackRamps, rampTrackCounter, rampMeasure1, rampMeasure2, rampMeasure3, rampMeasure4,
                floorGaps, trackGapCounter, gapMeasure1, gapMeasure2, gapMeasure3, gapMeasure4, photos);
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
        int index = indexRadio(radio);
        if (radio == hasGateTracksRadio) {
            if (index == 1) {
                trackHeightField.setVisibility(View.VISIBLE);
                hasTrackRampHeader.setVisibility(View.VISIBLE);
                hasTrackRampRadio.setVisibility(View.VISIBLE);
                trackGapHeader.setVisibility(View.VISIBLE);
                hasTrackGapsRadio.setVisibility(View.VISIBLE);
            } else {
                trackHeightValue.setText(null);
                trackHeightField.setVisibility(View.GONE);
                hasTrackRampHeader.setVisibility(View.GONE);
                hasTrackRampRadio.clearCheck();
                hasTrackRampRadio.setVisibility(View.GONE);
                closeTrackRampFields();
                trackGapHeader.setVisibility(View.GONE);
                hasTrackGapsRadio.clearCheck();
                hasTrackGapsRadio.setVisibility(View.GONE);
                closeTrackGapFields();
            }
        } else if (radio == hasTrackRampRadio) {
            if (index == 1) {
                trackRampError.setVisibility(View.GONE);
                addTrackRampButton.setVisibility(View.VISIBLE);
                trackRampField1.setVisibility(View.VISIBLE);
                rampTrackCounter = 1;
            } else
                closeTrackRampFields();

        } else if (radio == hasTrackGapsRadio) {
            if (index == 1) {
                addTrackGapButton.setVisibility(View.VISIBLE);
                trackGapField1.setVisibility(View.VISIBLE);
                trackGapCounter = 1;
            } else
                closeTrackGapFields();
        }
    }
}