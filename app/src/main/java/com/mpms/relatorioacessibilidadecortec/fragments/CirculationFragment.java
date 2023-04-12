package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.CancelEntryDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.CirculationEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.FallProtectParcel;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.FallProtectFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

import java.util.ArrayList;

public class CirculationFragment extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

    TextInputLayout circLocField, vertSignObsField, carpetObsField, accessFloorField, intercomHeightField, intercomObsField, biometryHeightField, biometryObsField,
            unevenHeightField, inclField, photoField, obsField;
    TextInputEditText circLocValue, vertSignObsValue, carpetObsValue, accessFloorValue, intercomHeightValue, intercomObsValue, biometryHeightValue, biometryObsValue,
            unevenHeightValue, inclValue, photoValue, obsValue;
    RadioGroup vertSignRadio, carpetRadio, accessFloorRadio, intercomRadio, bioClockRadio, hasUnevenRadio, unevenHeightRadio, taludeRadio, taludeInclRadio,
            hasProtectRadio, protectTypeRadio;
    TextView vertSignError, carpetError, accessError, intercomError, bioClockError, hasUnevenError, unevenHeightHeader, unevenHeightError, hasTaludeHeader, hasTaludeError,
            taludeInclHeader, taludeInclError, hasProtectHeader, hasProtectError, protectTypeHeader, protectTypeError;
    ImageButton protectType1, protectType2, protectType3;
    MaterialButton cancelCirc, continueCirc;
    FrameLayout protectFrame;

    Bundle circBundle;
    ViewModelEntry modelEntry;
    ArrayList<TextInputEditText> eText = new ArrayList<>();

    public CirculationFragment() {
        // Required empty public constructor
    }


    public static CirculationFragment newInstance() {
        return new CirculationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            circBundle = new Bundle(this.getArguments());
        else
            circBundle = new Bundle();

        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (circBundle.getBoolean(RECENT_ENTRY))
                    cancelClick();
                else {
                    setEnabled(false);
                    requireActivity().onBackPressed();
                }
                setEnabled(true);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_circulation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateCircViews(view);

        if (circBundle.getInt(CIRC_ID) > 0)
            modelEntry.getOneCirculation(circBundle.getInt(CIRC_ID)).observe(getViewLifecycleOwner(), this::loadCirculationData);

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER, getViewLifecycleOwner(), (key, bundle) -> {
            if (noEmptyFields() && bundle.getBoolean(CHILD_DATA_COMPLETE))
                saveUpdateCirculation(bundle);
            else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });

        continueCirc.setOnClickListener(v -> {
            if (indexRadio(protectTypeRadio) != -1)
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA, circBundle);
            else if (noEmptyFields())
                saveUpdateCirculation(circBundle);
            else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });

        cancelCirc.setOnClickListener(v -> cancelClick());
    }

    private void instantiateCircViews(View view) {
//        TextInputLayout
        circLocField = view.findViewById(R.id.circ_location_field);
        vertSignObsField = view.findViewById(R.id.circ_visual_sign_obs_field);
        carpetObsField = view.findViewById(R.id.circ_carpet_obs_field);
        accessFloorField = view.findViewById(R.id.circ_access_floor_obs_field);
        intercomHeightField = view.findViewById(R.id.circ_intercom_height_field);
        intercomObsField = view.findViewById(R.id.circ_has_intercom_obs_field);
        biometryHeightField = view.findViewById(R.id.circ_biometry_height_field);
        biometryObsField = view.findViewById(R.id.circ_has_biometry_obs_field);
        unevenHeightField = view.findViewById(R.id.circ_uneven_height_field);
        inclField = view.findViewById(R.id.circ_talude_incl_field);
        photoField = view.findViewById(R.id.circ_photo_field);
        obsField = view.findViewById(R.id.circ_obs_field);
//        TextInputEditText
        circLocValue = view.findViewById(R.id.circ_location_value);
        vertSignObsValue = view.findViewById(R.id.circ_visual_sign_obs_value);
        carpetObsValue = view.findViewById(R.id.circ_carpet_obs_value);
        accessFloorValue = view.findViewById(R.id.circ_access_floor_obs_value);
        intercomHeightValue = view.findViewById(R.id.circ_intercom_height_value);
        intercomObsValue = view.findViewById(R.id.circ_has_intercom_obs_value);
        biometryHeightValue = view.findViewById(R.id.circ_biometry_height_value);
        biometryObsValue = view.findViewById(R.id.circ_has_biometry_obs_value);
        unevenHeightValue = view.findViewById(R.id.circ_uneven_height_value);
        inclValue = view.findViewById(R.id.circ_talude_incl_value);
        photoValue = view.findViewById(R.id.circ_photo_value);
        obsValue = view.findViewById(R.id.circ_obs_value);
//        RadioGroup
        vertSignRadio = view.findViewById(R.id.circ_has_visual_sign_radio);
        carpetRadio = view.findViewById(R.id.circ_has_carpet_radio);
        accessFloorRadio = view.findViewById(R.id.circ_accessible_floor_radio);
        intercomRadio = view.findViewById(R.id.circ_has_intercom_radio);
        bioClockRadio = view.findViewById(R.id.circ_has_biometry_radio);
        hasUnevenRadio = view.findViewById(R.id.circ_uneven_radio);
        unevenHeightRadio = view.findViewById(R.id.circ_uneven_height_radio);
        taludeRadio = view.findViewById(R.id.circ_uneven_talude_radio);
        taludeInclRadio = view.findViewById(R.id.circ_talude_incl_radio);
        hasProtectRadio = view.findViewById(R.id.circ_fall_protect_radio);
        protectTypeRadio = view.findViewById(R.id.protect_type_radio);
//        TextView
        vertSignError = view.findViewById(R.id.circ_visual_sign_error);
        carpetError = view.findViewById(R.id.circ_carpet_error);
        accessError = view.findViewById(R.id.circ_accessible_floor_error);
        intercomError = view.findViewById(R.id.circ_has_intercom_error);
        bioClockError = view.findViewById(R.id.circ_has_biometry_error);
        hasUnevenError = view.findViewById(R.id.circ_uneven_error);
        unevenHeightHeader = view.findViewById(R.id.circ_uneven_height_header);
        unevenHeightError = view.findViewById(R.id.circ_uneven_height_error);
        hasTaludeHeader = view.findViewById(R.id.circ_uneven_talude_header);
        hasTaludeError = view.findViewById(R.id.circ_uneven_talude_error);
        taludeInclHeader = view.findViewById(R.id.circ_talude_incl_header);
        taludeInclError = view.findViewById(R.id.circ_talude_incl_error);
        hasProtectHeader = view.findViewById(R.id.circ_fall_protect_header);
        hasProtectError = view.findViewById(R.id.circ_fall_protect_error);
        protectTypeError = view.findViewById(R.id.protect_type_error);
        protectTypeHeader = view.findViewById(R.id.circ_protect_type_header);
//        ImageButton
        protectType1 = view.findViewById(R.id.protect_type_1);
        protectType2 = view.findViewById(R.id.protect_type_2);
        protectType3 = view.findViewById(R.id.protect_type_3);
//        MaterialButton
        cancelCirc = view.findViewById(R.id.cancel_circ);
        continueCirc = view.findViewById(R.id.continue_circ);
//        FrameLayout
        protectFrame = view.findViewById(R.id.fall_protect_frame);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listeners
        accessFloorRadio.setOnCheckedChangeListener(this::radioListener);
        intercomRadio.setOnCheckedChangeListener(this::radioListener);
        bioClockRadio.setOnCheckedChangeListener(this::radioListener);
        hasUnevenRadio.setOnCheckedChangeListener(this::radioListener);
        unevenHeightRadio.setOnCheckedChangeListener(this::radioListener);
        taludeRadio.setOnCheckedChangeListener(this::radioListener);
        taludeInclRadio.setOnCheckedChangeListener(this::radioListener);
        hasProtectRadio.setOnCheckedChangeListener(this::radioListener);
        protectTypeRadio.setOnCheckedChangeListener(this::radioListener);
//        Utility
        editTextFields();
        allowObsScroll(eText);
    }

    private void saveUpdateCirculation(Bundle bundle) {
        if (bundle.getInt(CIRC_ID) > 0) {
            CirculationEntry entry = createCirculation(bundle);
            entry.setCircID(bundle.getInt(CIRC_ID));
            ViewModelEntry.updateCirculation(entry);
            callNextFragment(bundle);
        } else if (bundle.getInt(CIRC_ID) == 0) {
            ViewModelEntry.insertCirculation(createCirculation(bundle));
            callNextFragment(bundle);
        } else {
            circBundle.putInt(CIRC_ID, 0);
            Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
        }
    }

    private void callNextFragment(Bundle bundle) {
//        TODO - Chamar o Prox. Fragment qnd criado
        bundle.putBoolean(RECENT_ENTRY, true);
        CirculationTwoFragment fragment = new CirculationTwoFragment();
        fragment.setArguments(bundle);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, fragment).addToBackStack(null).commit();
//        requireActivity().getSupportFragmentManager().popBackStack(CIRC_LIST, 0);
    }

    private void removeChildFragments() {
        Fragment fragment = getChildFragmentManager().findFragmentById(R.id.fall_protect_frame);
        if (fragment != null)
            getChildFragmentManager().beginTransaction().remove(fragment).commit();
    }

    private void loadCirculationData(CirculationEntry entry) {
        if (entry.getCircLocation() != null)
            circLocValue.setText(entry.getCircLocation());
        if (entry.getHasVertSign() != null)
            checkRadioGroup(vertSignRadio, entry.getHasVertSign());
        if (entry.getVertSignObs() != null)
            vertSignObsValue.setText(entry.getVertSignObs());
        if (entry.getHasLooseCarpet() != null)
            checkRadioGroup(carpetRadio, entry.getHasLooseCarpet());
        if (entry.getLooseCarpetObs() != null)
            carpetObsValue.setText(entry.getLooseCarpetObs());
        if (entry.getAccessFloor() != null) {
            checkRadioGroup(accessFloorRadio, entry.getAccessFloor());
            if (entry.getAccessFloor() == 0) {
                if (entry.getAccessFloorObs() != null)
                    accessFloorValue.setText(entry.getAccessFloorObs());
            }
        }
        if (entry.getHasIntercom() != null) {
            checkRadioGroup(intercomRadio, entry.getHasIntercom());
            if (entry.getHasIntercom() == 1) {
                intercomHeightValue.setText(String.valueOf(entry.getIntercomHeight()));
                if (entry.getIntercomObs() != null)
                    intercomObsValue.setText(entry.getIntercomObs());
            }
        }

        if (entry.getHasBioClock() != null) {
            checkRadioGroup(bioClockRadio, entry.getHasBioClock());
            if (entry.getHasBioClock() == 1) {
                if (entry.getBioClockHeight() != null)
                    biometryHeightValue.setText(String.valueOf(entry.getBioClockHeight()));
                if (entry.getBioClockObs() != null)
                    biometryObsValue.setText(entry.getBioClockObs());
            }
        }
        if (entry.getHasUnevenFloor() != null) {
            checkRadioGroup(hasUnevenRadio, entry.getHasUnevenFloor());
            if (entry.getHasUnevenFloor() == 1) {
                if (entry.getUnevenHigher() != null) {
                    checkRadioGroup(unevenHeightRadio, entry.getUnevenHigher());
                    if (entry.getUnevenHigher() == 1) {
                        if (entry.getUnevenHeight() != null)
                            unevenHeightValue.setText(String.valueOf(entry.getUnevenHeight()));
                        if (entry.getHasSlope() != null) {
                            checkRadioGroup(taludeRadio, entry.getHasSlope());
                            if (entry.getHasSlope() == 1) {
                                if (entry.getSlopeHigher() != null) {
                                    checkRadioGroup(taludeInclRadio, entry.getSlopeHigher());
                                    if (entry.getSlopeHigher() == 1) {
                                        if (entry.getSlopeAngle() != null)
                                            inclValue.setText(String.valueOf(entry.getSlopeAngle()));
                                        if (entry.getHasFallProtect() != null) {
                                            checkRadioGroup(hasProtectRadio, entry.getHasFallProtect());
                                            if (entry.getHasFallProtect() == 1) {
                                                if (entry.getFallProtectType() != null)
                                                    checkRadioGroup(protectTypeRadio, entry.getFallProtectType());
                                            }
                                        }
                                    }
                                }
                            } else if (entry.getHasSlope() == 0) {
                                if (entry.getHasFallProtect() != null) {
                                    checkRadioGroup(hasProtectRadio, entry.getHasFallProtect());
                                    if (entry.getHasFallProtect() == 1) {
                                        if (entry.getFallProtectType() != null)
                                            checkRadioGroup(protectTypeRadio, entry.getFallProtectType());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (entry.getCircPhoto() != null)
            photoValue.setText(entry.getCircPhoto());
        if (entry.getCircObs() != null)
            obsValue.setText(entry.getCircObs());
    }

    private boolean noEmptyFields() {
        clearErrors();
        int i = 0;
        if (TextUtils.isEmpty(circLocValue.getText())) {
            i++;
            circLocField.setError(getString(R.string.req_field_error));
        }
        if (indexRadio(vertSignRadio) == -1) {
            i++;
            vertSignError.setVisibility(View.VISIBLE);
        }
        if (indexRadio(carpetRadio) == -1) {
            i++;
            carpetError.setVisibility(View.VISIBLE);
        }
        if (indexRadio(accessFloorRadio) == -1) {
            i++;
            accessError.setVisibility(View.VISIBLE);
        } else if (indexRadio(accessFloorRadio) == 0) {
            if (TextUtils.isEmpty(accessFloorValue.getText())) {
                i++;
                accessFloorField.setError(getString(R.string.req_field_error));
            }
        }
        if (indexRadio(intercomRadio) == -1) {
            i++;
            intercomError.setVisibility(View.VISIBLE);
        } else if (indexRadio(intercomRadio) == 1) {
            if (TextUtils.isEmpty(intercomHeightValue.getText())) {
                i++;
                intercomHeightField.setError(getString(R.string.req_field_error));
            }
        }
        if (indexRadio(bioClockRadio) == -1) {
            i++;
            bioClockError.setVisibility(View.VISIBLE);
        } else if (indexRadio(bioClockRadio) == 1) {
            if (TextUtils.isEmpty(biometryHeightValue.getText())) {
                i++;
                biometryHeightField.setError(getString(R.string.req_field_error));
            }
        }
        if (indexRadio(hasUnevenRadio) == -1) {
            i++;
            hasUnevenError.setVisibility(View.VISIBLE);
        } else if (indexRadio(hasUnevenRadio) == 1) {
            if (indexRadio(unevenHeightRadio) == -1) {
                i++;
                unevenHeightError.setVisibility(View.VISIBLE);
            } else if (indexRadio(unevenHeightRadio) == 1) {
                if (TextUtils.isEmpty(unevenHeightValue.getText())) {
                    i++;
                    unevenHeightField.setError(getString(R.string.req_field_error));
                }
                if (indexRadio(taludeRadio) == -1) {
                    i++;
                    hasTaludeError.setVisibility(View.VISIBLE);
                } else if (indexRadio(taludeRadio) == 0) {
                    if (indexRadio(hasProtectRadio) == -1) { ///
                        i++;
                        hasProtectError.setVisibility(View.VISIBLE);
                    } else if (indexRadio(hasProtectRadio) == 1) {
                        if (indexRadio(protectTypeRadio) == -1) {
                            i++;
                            protectTypeError.setVisibility(View.VISIBLE);
                        }
                    }
                } else {
                    if (indexRadio(taludeInclRadio) == -1) {
                        i++;
                        taludeInclError.setVisibility(View.VISIBLE);
                    } else if (indexRadio(taludeInclRadio) == 1) {
                        if (TextUtils.isEmpty(inclValue.getText())) {
                            i++;
                            inclField.setError(getString(R.string.req_field_error));
                        }
                        if (indexRadio(hasProtectRadio) == -1) {
                            i++;
                            hasProtectError.setVisibility(View.VISIBLE);
                        } else if (indexRadio(hasProtectRadio) == 1) {
                            if (indexRadio(protectTypeRadio) == -1) {
                                i++;
                                protectTypeError.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }
            }

        }
        return i == 0;
    }

    private void cancelClick() {
        if (circBundle.getBoolean(RECENT_ENTRY)) {
            CancelEntryDialog dialog = CancelEntryDialog.newInstance();
            dialog.setListener(() -> {
                ViewModelEntry.deleteDoor(circBundle.getInt(CIRC_ID));
                circBundle = null;
                requireActivity().getSupportFragmentManager().popBackStack(CIRC_LIST, 0);
            });
            FragmentManager manager = requireActivity().getSupportFragmentManager();
            dialog.show(manager, "MOSTRA");
        } else
            requireActivity().getSupportFragmentManager().popBackStack(CIRC_LIST, 0);
    }

    private void clearErrors() {
        circLocField.setErrorEnabled(false);
        accessFloorField.setErrorEnabled(false);
        intercomHeightField.setErrorEnabled(false);
        biometryHeightField.setErrorEnabled(false);
        unevenHeightField.setErrorEnabled(false);
        inclField.setErrorEnabled(false);

        vertSignError.setVisibility(View.GONE);
        carpetError.setVisibility(View.GONE);
        accessError.setVisibility(View.GONE);
        intercomError.setVisibility(View.GONE);
        bioClockError.setVisibility(View.GONE);
        hasUnevenError.setVisibility(View.GONE);
        unevenHeightError.setVisibility(View.GONE);
        hasTaludeError.setVisibility(View.GONE);
        taludeInclError.setVisibility(View.GONE);
        hasProtectError.setVisibility(View.GONE);
        protectTypeError.setVisibility(View.GONE);
    }

    private void editTextFields() {
        eText.add(vertSignObsValue);
        eText.add(carpetObsValue);
        eText.add(accessFloorValue);
        eText.add(intercomObsValue);
        eText.add(biometryObsValue);
        eText.add(obsValue);
    }

    private CirculationEntry createCirculation(Bundle bundle) {
        String location, vertObs = null, carpetObs = null, accessObs = null, intercomObs = null, bioObs = null, protectObs = null, photo = null, circObs = null;
        Double interHeight = null, bioHeight = null, unevenHeight = null, angle = null, protectDimen = null;
        int vertSign, carpet, access, intercom, bioClock, unevenFloor;
        Integer unevenHigh = null, talude = null, taludeAngle = null, hasProtect = null, protectType = null, hasTactile = null, hasVisible = null;

        location = String.valueOf(circLocValue.getText());
        vertSign = indexRadio(vertSignRadio);
        if (!TextUtils.isEmpty(vertSignObsValue.getText()))
            vertObs = String.valueOf(vertSignObsValue.getText());
        carpet = indexRadio(carpetRadio);
        if (!TextUtils.isEmpty(carpetObsValue.getText()))
            carpetObs = String.valueOf(carpetObsValue.getText());
        access = indexRadio(accessFloorRadio);
        if (!TextUtils.isEmpty(accessFloorValue.getText()))
            accessObs = String.valueOf(accessFloorValue.getText());
        intercom = indexRadio(intercomRadio);
        if (!TextUtils.isEmpty(intercomHeightValue.getText()))
            interHeight = Double.parseDouble(String.valueOf(intercomHeightValue.getText()));
        if (!TextUtils.isEmpty(intercomObsValue.getText()))
            intercomObs = String.valueOf(intercomObsValue.getText());
        bioClock = indexRadio(bioClockRadio);
        if (!TextUtils.isEmpty(biometryHeightValue.getText()))
            bioHeight = Double.parseDouble(String.valueOf(biometryHeightValue.getText()));
        if (!TextUtils.isEmpty(biometryObsValue.getText()))
            bioObs = String.valueOf(biometryObsValue.getText());
        unevenFloor = indexRadio(hasUnevenRadio);
        if (unevenFloor == 1) {
            unevenHigh = indexRadio(unevenHeightRadio);
            if (unevenHigh == 1) {
                unevenHeight = Double.parseDouble(String.valueOf(unevenHeightValue.getText()));
                talude = indexRadio(taludeRadio);
                if (talude == 1) {
                    taludeAngle = indexRadio(taludeInclRadio);
                    if (taludeAngle == 1) {
                        angle = Double.parseDouble(String.valueOf(inclValue.getText()));
                        hasProtect = indexRadio(hasProtectRadio);
                        if (hasProtect == 1) {
                            protectType = indexRadio(protectTypeRadio);

                            FallProtectParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));

                            switch (protectType) {
                                case 0:
                                    hasTactile = parcel.getTactileContrast();
                                case 1:
                                    hasVisible = parcel.getVisualContrast();
                                case 2:
                                    protectDimen = parcel.getHeightLength();
                                default:
                                    protectObs = parcel.getProtectObs();
                                    break;
                            }
                        }
                    }
                } else if (talude == 0) {
                    hasProtect = indexRadio(hasProtectRadio);
                    if (hasProtect == 1) {
                        protectType = indexRadio(protectTypeRadio);

                        FallProtectParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));

                        switch (protectType) {
                            case 0:
                                hasTactile = parcel.getTactileContrast();
                            case 1:
                                hasVisible = parcel.getVisualContrast();
                            case 2:
                                protectDimen = parcel.getHeightLength();
                            default:
                                protectObs = parcel.getProtectObs();
                                break;
                        }
                    }
                }
            }
        }

        if (!TextUtils.isEmpty(photoValue.getText()))
            photo = String.valueOf(photoValue.getText());
        if (!TextUtils.isEmpty(obsValue.getText()))
            circObs = String.valueOf(obsValue.getText());

        return new CirculationEntry(bundle.getInt(SCHOOL_ID), location, vertSign, vertObs, carpet, carpetObs, access, accessObs, intercom, interHeight, intercomObs,
                bioClock, bioHeight, bioObs, unevenFloor, unevenHigh, unevenHeight, talude, taludeAngle, angle, hasProtect, protectType, protectDimen, hasVisible,
                hasTactile, protectObs, photo, circObs);
    }


    @Override
    public void radioListener(RadioGroup radio, int id) {
        int index = indexRadio(radio);

        if (radio == vertSignRadio) {
            if (index == 1)
                vertSignObsField.setVisibility(View.VISIBLE);
            else {
                vertSignObsValue.setText(null);
                vertSignObsField.setVisibility(View.GONE);
            }
        }
        if (radio == carpetRadio) {
            if (index == 1)
                carpetObsField.setVisibility(View.VISIBLE);
            else {
                carpetObsValue.setText(null);
                carpetObsField.setVisibility(View.GONE);
            }
        }
        if (radio == accessFloorRadio) {
            if (index == 0)
                accessFloorField.setVisibility(View.VISIBLE);
            else {
                accessFloorValue.setText(null);
                accessFloorField.setVisibility(View.GONE);
            }
        } else if (radio == intercomRadio) {
            if (index == 1)
                intercomHeightField.setVisibility(View.VISIBLE);
            else {
                intercomHeightValue.setText(null);
                intercomHeightField.setVisibility(View.GONE);
            }
        } else if (radio == bioClockRadio) {
            if (index == 1) {
                biometryHeightField.setVisibility(View.VISIBLE);
                biometryObsField.setVisibility(View.VISIBLE);
            } else {
                biometryHeightValue.setText(null);
                biometryObsValue.setText(null);
                biometryHeightField.setVisibility(View.GONE);
                biometryObsField.setVisibility(View.GONE);
            }
        } else if (radio == hasUnevenRadio) {
            if (index == 1) {
                unevenHeightHeader.setVisibility(View.VISIBLE);
                unevenHeightRadio.setVisibility(View.VISIBLE);
            } else {
//                Tirar todas as views de desnível
                unevenHeightHeader.setVisibility(View.GONE);
                unevenHeightRadio.clearCheck();
                unevenHeightRadio.setVisibility(View.GONE);
                unevenHeightValue.setText(null);
                unevenHeightField.setVisibility(View.GONE);
                hasTaludeHeader.setVisibility(View.GONE);
                taludeRadio.clearCheck();
                taludeRadio.setVisibility(View.GONE);
                taludeInclHeader.setVisibility(View.GONE);
                taludeInclRadio.clearCheck();
                taludeInclRadio.setVisibility(View.GONE);
                inclValue.setText(null);
                inclField.setVisibility(View.GONE);
                hasProtectHeader.setVisibility(View.GONE);
                hasProtectRadio.clearCheck();
                hasProtectRadio.setVisibility(View.GONE);
                removeChildFragments();
                protectTypeHeader.setVisibility(View.GONE);
                protectType1.setVisibility(View.GONE);
                protectType2.setVisibility(View.GONE);
                protectType3.setVisibility(View.GONE);
                protectTypeRadio.clearCheck();
                protectTypeRadio.setVisibility(View.GONE);
            }
        } else if (radio == unevenHeightRadio) {
            if (index == 1) {
                unevenHeightField.setVisibility(View.VISIBLE);
                hasTaludeHeader.setVisibility(View.VISIBLE);
                taludeRadio.setVisibility(View.VISIBLE);
            } else {
                unevenHeightValue.setText(null);
                unevenHeightField.setVisibility(View.GONE);
                hasTaludeHeader.setVisibility(View.GONE);
                taludeRadio.clearCheck();
                taludeRadio.setVisibility(View.GONE);
                hasProtectHeader.setVisibility(View.GONE);
                hasProtectRadio.clearCheck();
                hasProtectRadio.setVisibility(View.GONE);
                removeChildFragments();
                protectTypeHeader.setVisibility(View.GONE);
                protectType1.setVisibility(View.GONE);
                protectType2.setVisibility(View.GONE);
                protectType3.setVisibility(View.GONE);
                protectTypeRadio.clearCheck();
                protectTypeRadio.setVisibility(View.GONE);
            }
        } else if (radio == taludeRadio) {
            if (index == 1) {
                taludeInclHeader.setVisibility(View.VISIBLE);
                taludeInclRadio.setVisibility(View.VISIBLE);
                hasProtectHeader.setVisibility(View.GONE);
                hasProtectRadio.clearCheck();
                hasProtectRadio.setVisibility(View.GONE);
            } else if (index == 0) {
                taludeInclHeader.setVisibility(View.GONE);
                taludeInclRadio.clearCheck();
                taludeInclRadio.setVisibility(View.GONE);
                hasProtectHeader.setVisibility(View.VISIBLE);
                hasProtectRadio.setVisibility(View.VISIBLE);
            }

        } else if (radio == taludeInclRadio) {
            if (index == 1) {
                inclField.setVisibility(View.VISIBLE);
                hasProtectHeader.setVisibility(View.VISIBLE);
                hasProtectRadio.setVisibility(View.VISIBLE);
            } else {
                inclValue.setText(null);
                inclField.setVisibility(View.GONE);
                hasProtectHeader.setVisibility(View.GONE);
                hasProtectRadio.clearCheck();
                hasProtectRadio.setVisibility(View.GONE);
                removeChildFragments();
                protectTypeHeader.setVisibility(View.GONE);
                protectType1.setVisibility(View.GONE);
                protectType2.setVisibility(View.GONE);
                protectType3.setVisibility(View.GONE);
                protectTypeRadio.clearCheck();
                protectTypeRadio.setVisibility(View.GONE);
            }
        } else if (radio == hasProtectRadio) {
            if (index == 1) {
                protectTypeHeader.setVisibility(View.VISIBLE);
                protectType1.setVisibility(View.VISIBLE);
                protectType2.setVisibility(View.VISIBLE);
                protectType3.setVisibility(View.VISIBLE);
                protectTypeRadio.setVisibility(View.VISIBLE);
            } else {
                removeChildFragments();
                protectTypeHeader.setVisibility(View.GONE);
                protectType1.setVisibility(View.GONE);
                protectType2.setVisibility(View.GONE);
                protectType3.setVisibility(View.GONE);
                protectTypeRadio.clearCheck();
                protectTypeRadio.setVisibility(View.GONE);
            }
        } else {
            if (index > -1) {
                FallProtectFragment fragment = FallProtectFragment.newInstance(index, circBundle);
                getChildFragmentManager().beginTransaction().replace(R.id.fall_protect_frame, fragment).commit();
            } else
                removeChildFragments();
        }
    }
}