package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.FallProtectionEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.FallProtectParcel;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.FallProtectChildFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

public class FallProtectFragment extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

    TextInputLayout protectLocalField, unevenHeightField, inclField, protectObsField, protectPhotoField;
    TextInputEditText protectLocalValue, unevenHeightValue, inclValue, protectObsValue, protectPhotoValue;
    RadioGroup unevenHeightRadio, taludeRadio, taludeInclRadio, hasProtectRadio, protectTypeRadio;
    TextView unevenHeightError, hasTaludeHeader, hasTaludeError, taludeInclHeader, taludeInclError, hasProtectHeader,
            hasProtectError, protectTypeHeader, protectTypeError;
    ImageButton protectType1, protectType2, protectType3;
    MaterialButton cancelProtect, saveProtect;
    FrameLayout protectFrame;

    Bundle fallBundle;
    ViewModelEntry modelEntry;


    public FallProtectFragment() {
        // Required empty public constructor
    }


    public static FallProtectFragment newInstance() {
        return new FallProtectFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            fallBundle = new Bundle(this.getArguments());
        else
            fallBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fall_protect, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateFallViews(view);

        if (fallBundle.getInt(PROTECT_ID) > 0)
            modelEntry.getOneFallProtection(fallBundle.getInt(PROTECT_ID)).observe(getViewLifecycleOwner(), this::loadProtectData);

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER, getViewLifecycleOwner(), (key, bundle) -> {
            if (noEmptyFields() && bundle.getBoolean(CHILD_DATA_COMPLETE))
                saveUpdateProtect(bundle);
            else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });

        saveProtect.setOnClickListener(v -> {
            if (indexRadio(protectTypeRadio) != -1)
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA, fallBundle);
            else if (noEmptyFields())
                saveUpdateProtect(fallBundle);
            else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });

        cancelProtect.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void instantiateFallViews(View view) {
// TextInputLayout
        protectLocalField = view.findViewById(R.id.protect_location_field);
        unevenHeightField = view.findViewById(R.id.protect_uneven_height_field);
        inclField = view.findViewById(R.id.protect_talude_incl_field);
        protectObsField = view.findViewById(R.id.protect_photo_field);
        protectPhotoField = view.findViewById(R.id.protect_obs_field);
//        TextInputEditText
        protectLocalValue = view.findViewById(R.id.protect_location_value);
        unevenHeightValue = view.findViewById(R.id.protect_uneven_height_value);
        inclValue = view.findViewById(R.id.protect_talude_incl_value);
        protectObsValue = view.findViewById(R.id.protect_photo_value);
        protectPhotoValue = view.findViewById(R.id.protect_obs_value);
//        RadioGroup
        unevenHeightRadio = view.findViewById(R.id.protect_uneven_height_radio);
        taludeRadio = view.findViewById(R.id.protect_uneven_talude_radio);
        taludeInclRadio = view.findViewById(R.id.protect_talude_incl_radio);
        hasProtectRadio = view.findViewById(R.id.protect_fall_protect_radio);
        protectTypeRadio = view.findViewById(R.id.protect_type_radio);
//        TextView
        unevenHeightError = view.findViewById(R.id.protect_uneven_height_error);
        hasTaludeHeader = view.findViewById(R.id.protect_uneven_talude_header);
        hasTaludeError = view.findViewById(R.id.protect_uneven_talude_error);
        taludeInclHeader = view.findViewById(R.id.protect_talude_incl_header);
        taludeInclError = view.findViewById(R.id.protect_talude_incl_error);
        hasProtectHeader = view.findViewById(R.id.protect_fall_protect_header);
        hasProtectError = view.findViewById(R.id.protect_fall_protect_error);
        protectTypeError = view.findViewById(R.id.protect_type_error);
        protectTypeHeader = view.findViewById(R.id.protect_type_header);
        //        ImageButton
        protectType1 = view.findViewById(R.id.protect_type_1);
        protectType2 = view.findViewById(R.id.protect_type_2);
        protectType3 = view.findViewById(R.id.protect_type_3);
        //        MaterialButton
        cancelProtect = view.findViewById(R.id.cancel_protection);
        saveProtect = view.findViewById(R.id.save_protection);
        //        FrameLayout
        protectFrame = view.findViewById(R.id.fall_protect_frame);
//        Listener
        unevenHeightRadio.setOnCheckedChangeListener(this::radioListener);
        taludeRadio.setOnCheckedChangeListener(this::radioListener);
        taludeInclRadio.setOnCheckedChangeListener(this::radioListener);
        hasProtectRadio.setOnCheckedChangeListener(this::radioListener);
        protectTypeRadio.setOnCheckedChangeListener(this::radioListener);
        allowObsScroll(protectObsValue);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private void saveUpdateProtect(Bundle bundle) {
        FallProtectionEntry entry = createFallProtect(bundle);
        if (bundle.getInt(PROTECT_ID) > 0) {
            entry.setProtectID(bundle.getInt(PROTECT_ID));
            modelEntry.updateFallProtection(entry);
            Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        } else if (bundle.getInt(PROTECT_ID) == 0) {
            modelEntry.insertFallProtection(entry);
            Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
//            TODO - Criar método para limpar todos os campos em vez de só fechar o cadastro
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        } else {
            bundle.putInt(PROTECT_ID, 0);
            Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadProtectData(FallProtectionEntry entry) {

        if (entry.getProtectLocal() != null)
            protectLocalValue.setText(entry.getProtectLocal());
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
        if (entry.getProtectObs() != null)
            protectObsValue.setText(entry.getProtectObs());
        if (entry.getProtectPhoto() != null)
            protectPhotoValue.setText(entry.getProtectPhoto());
    }

    private FallProtectionEntry createFallProtect(Bundle bundle) {
        String protectLocal, protectObs = null, photo = null, obs = null;
        Double unevenHeight = null, angle = null, protectDimen = null;
        Integer talude = null, taludeAngle = null, hasProtect = null, protectType = null, hasTactile = null, hasVisible = null;
        int unevenHigh;

        protectLocal = String.valueOf(protectLocalValue.getText());
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

        if (!TextUtils.isEmpty(protectPhotoValue.getText()))
            photo = String.valueOf(protectPhotoValue.getText());
        if (!TextUtils.isEmpty(protectObsValue.getText()))
            obs = String.valueOf(protectObsValue.getText());

        return new FallProtectionEntry(bundle.getInt(CIRC_ID), protectLocal, unevenHigh, unevenHeight, talude, taludeAngle, angle, hasProtect, protectType, protectDimen,
                hasVisible, hasTactile, protectObs, photo, obs);
    }

    private boolean noEmptyFields() {
        clearErrors();

        int i = 0;

        if (TextUtils.isEmpty(protectLocalValue.getText())) {
            i++;
            protectLocalField.setError(getString(R.string.req_field_error));
        }
        if (indexRadio(unevenHeightRadio) == -1) {
            i++;
            unevenHeightError.setVisibility(View.VISIBLE);
        } else if (indexRadio(unevenHeightRadio) == 1) {
            if (TextUtils.isEmpty(unevenHeightValue.getText())) {
                i++;
                unevenHeightField.setError(getString(R.string.req_field_error));
            } else if (Double.parseDouble(String.valueOf(unevenHeightValue.getText())) < 0.18) {
                i++;
                unevenHeightField.setError("A altura informada deve ser superior à 0.18 metros");
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

        return i == 0;
    }

    private void clearErrors() {
        unevenHeightField.setErrorEnabled(false);
        inclField.setErrorEnabled(false);

        unevenHeightError.setVisibility(View.GONE);
        hasTaludeError.setVisibility(View.GONE);
        taludeInclError.setVisibility(View.GONE);
        hasProtectError.setVisibility(View.GONE);
        protectTypeError.setVisibility(View.GONE);
    }


    @Override
    public void radioListener(RadioGroup radio, int id) {
        int index = indexRadio(radio);

        if (radio == unevenHeightRadio) {
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
                FallProtectChildFragment fragment = FallProtectChildFragment.newInstance(index, fallBundle);
                getChildFragmentManager().beginTransaction().replace(R.id.fall_protect_frame, fragment).commit();
            } else
                removeChildFragments();
        }
    }

    private void removeChildFragments() {
        Fragment fragment = getChildFragmentManager().findFragmentById(R.id.fall_protect_frame);
        if (fragment != null)
            getChildFragmentManager().beginTransaction().remove(fragment).commit();
    }
}