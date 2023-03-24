package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.CancelEntryDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExtAccessSocialOne;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.data.parcels.VehicleExtAccParcel;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.ExtAccessSocialFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.ExtAccessVehicleFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.parceler.Parcels;

public class ExternalAccessFragment extends Fragment implements TagInterface, ScrollEditText {

    RadioGroup entranceTypeRadio, accessFloorRadio;
    TextInputLayout entranceLocationField, accessFloorObsField;
    TextInputEditText entranceLocationValue, accessFloorObsValue;
    Button saveExternalAccess, cancelExternalAccess;
    TextView accessTypeError, accessFloorError;
    Fragment accessType;

    Bundle extBundle;

    private ViewModelEntry modelEntry;

    public ExternalAccessFragment() {
        // Required empty public constructor
    }

    public static ExternalAccessFragment newInstance() {
        return new ExternalAccessFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            extBundle = new Bundle(this.getArguments());
        else
            extBundle = new Bundle();

        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (extBundle.getBoolean(RECENT_ENTRY))
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

        return inflater.inflate(R.layout.fragment_external_access, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateExternalAccessViews(view);

        getParentFragmentManager().setFragmentResult(MEMORIAL, extBundle);

        if (extBundle.getInt(AMBIENT_ID) > 0)
            modelEntry.getOneExternalAccess(extBundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), this::loadExtAccessInfo);

        getChildFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA, this, (key, bundle) -> {
            if (bundle.getBoolean(CHILD_DATA_COMPLETE)) {
                if (checkEmptyFields()) {
                    ExternalAccess extAccess = newExtAccess(bundle);
                    if (bundle.getInt(AMBIENT_ID) > 0) {
                        extAccess.setExternalAccessID(bundle.getInt(AMBIENT_ID));
                        ViewModelEntry.updateExternalAccess(extAccess);
                        Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                        requireActivity().getSupportFragmentManager().popBackStack(EXTERNAL_LIST, 0);
                    } else if (bundle.getInt(AMBIENT_ID) == 0) {
                        ViewModelEntry.insertExternalAccess(extAccess);
                        Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                        clearExtAccessFields();
                    } else {
                        extBundle.putInt(AMBIENT_ID, 0);
                        Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        saveExternalAccess.setOnClickListener(v -> {
            if (getRadioCheckIndex(entranceTypeRadio) == 1) {
                getChildFragmentManager().setFragmentResult(PARENT_SAVE_ATTEMPT, extBundle);
            } else {
                if (checkEmptyFields()) {
                    if (extBundle.getInt(AMBIENT_ID) >= 0) {
                        if (extBundle.getInt(AMBIENT_ID) > 0) {
                            ViewModelEntry.updateExtAccessRegOne(upExtAccessOne(extBundle));
                            callSocialAccessFrag(extBundle);
                        } else {
                            extBundle.putBoolean(RECENT_ENTRY, true);
                            ViewModelEntry.insertExternalAccess(newExtAccess(extBundle));
                        }
                    } else {
                        extBundle.putInt(AMBIENT_ID, 0);
                        Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!extBundle.getBoolean(RECENT_ENTRY)) {
            modelEntry.getLastExternalAccess().observe(getViewLifecycleOwner(), access -> {
                if (extBundle.getBoolean(RECENT_ENTRY))
                    callSocialAccessFrag(access);
            });
        } else {
            modelEntry.getLastExternalAccess().removeObserver(access -> {
                if (extBundle.getBoolean(RECENT_ENTRY))
                    callSocialAccessFrag(access);
            });
        }
    }

    private void cancelClick() {
        if (extBundle.getBoolean(RECENT_ENTRY)) {
            CancelEntryDialog dialog = CancelEntryDialog.newInstance();
            dialog.setListener(() -> {
                ViewModelEntry.deleteOneExternalAccess(extBundle.getInt(AMBIENT_ID));
                extBundle = null;
                requireActivity().getSupportFragmentManager().popBackStack(EXTERNAL_LIST, 0);
            });
            FragmentManager manager = requireActivity().getSupportFragmentManager();
            dialog.show(manager, "MOSTRA");
        } else
            requireActivity().getSupportFragmentManager().popBackStack(EXTERNAL_LIST, 0);
    }

    private void callSocialAccessFrag(ExternalAccess access) {
        int aID = access.getExternalAccessID();
        extBundle.putInt(AMBIENT_ID, aID);
        ExtAccessSocialFragment fragment = new ExtAccessSocialFragment();
        fragment.setArguments(extBundle);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, fragment)
                .addToBackStack(null).commit();
    }

    private void callSocialAccessFrag(Bundle bundle) {
        ExtAccessSocialFragment fragment = new ExtAccessSocialFragment();
        fragment.setArguments(bundle);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, fragment)
                .addToBackStack(null).commit();
    }

    private void instantiateExternalAccessViews(View view) {
//        TextInputLayout
        entranceLocationField = view.findViewById(R.id.entrance_location_field);
        accessFloorObsField = view.findViewById(R.id.ext_accessible_floor_obs_field);
//        TextInputEditText
        entranceLocationValue = view.findViewById(R.id.entrance_location_value);
        accessFloorObsValue = view.findViewById(R.id.ext_accessible_floor_obs_value);
//        RadioGroup
        entranceTypeRadio = view.findViewById(R.id.external_access_type_radio);
        accessFloorRadio = view.findViewById(R.id.ext_accessible_floor_radio);
//        TextView
        accessTypeError = view.findViewById(R.id.external_access_type_error);
        accessFloorError = view.findViewById(R.id.ext_accessible_floor_error);
//        MaterialButton
        saveExternalAccess = view.findViewById(R.id.save_ext_access);
        cancelExternalAccess = view.findViewById(R.id.cancel_ext_access);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());

        entranceTypeRadio.setOnCheckedChangeListener(this::radioGroupActivation);
        accessFloorRadio.setOnCheckedChangeListener(this::radioGroupActivation);
        cancelExternalAccess.setOnClickListener(v -> cancelClick());

        allowObsScroll(accessFloorObsValue);
    }

    private void loadExtAccessInfo(ExternalAccess extAccess) {
        entranceLocationValue.setText(extAccess.getAccessLocation());
        entranceTypeRadio.check(entranceTypeRadio.getChildAt(extAccess.getEntranceType()).getId());
        accessFloorRadio.check(accessFloorRadio.getChildAt(extAccess.getFloorIsAccessible()).getId());
        if (extAccess.getAccessibleFloorObs() != null)
            accessFloorObsValue.setText(extAccess.getAccessibleFloorObs());

        getChildFragmentManager().setFragmentResult(InspectionActivity.LOAD_CHILD_DATA, extBundle);
    }

    private int getRadioCheckIndex(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public void radioGroupActivation(RadioGroup radioGroup, int checkedID) {
        int index = radioGroup.indexOfChild(radioGroup.findViewById(checkedID));
        if (radioGroup == entranceTypeRadio) {
            if (index == 1) {
                saveExternalAccess.setText(getString(R.string.label_button_save));
                accessType = new ExtAccessVehicleFragment();
                accessType.setArguments(extBundle);
                getChildFragmentManager().beginTransaction().replace(R.id.external_access_layout, accessType).commit();
            } else {
                removeVehicleFragments();
                saveExternalAccess.setText(getString(R.string.label_button_proceed_register));
            }
        } else if (radioGroup == accessFloorRadio) {
            if (index == 0) {
                accessFloorObsField.setVisibility(View.VISIBLE);
            } else {
                accessFloorObsValue.setText(null);
                accessFloorObsField.setVisibility(View.GONE);
            }
        }

    }

    private void removeVehicleFragments() {
        Fragment fragment = getChildFragmentManager().findFragmentById(R.id.external_access_layout);
        if (fragment != null)
            getChildFragmentManager().beginTransaction().remove(fragment).commit();
    }

    private boolean checkEmptyFields() {
        clearExternalAccessErrors();
        int i = 0;
        if (TextUtils.isEmpty(entranceLocationValue.getText())) {
            entranceLocationField.setError(getText(R.string.req_field_error));
            i++;
        }
        if (entranceTypeRadio.getCheckedRadioButtonId() == -1) {
            accessTypeError.setVisibility(View.VISIBLE);
            i++;
        }
        if (getRadioCheckIndex(accessFloorRadio) == -1) {
            accessFloorError.setVisibility(View.VISIBLE);
            i++;
        } else if (getRadioCheckIndex(accessFloorRadio) == 0) {
            if (TextUtils.isEmpty(accessFloorObsValue.getText())) {
                accessFloorObsField.setError(getString(R.string.req_field_error));
                i++;
            }
        }
        return i == 0;
    }

    private void clearExternalAccessErrors() {
        entranceLocationField.setErrorEnabled(false);
        accessTypeError.setVisibility(View.GONE);
        accessFloorError.setVisibility(View.GONE);
        accessFloorObsField.setErrorEnabled(false);
    }

    private void clearExtAccessFields() {
        entranceLocationValue.setText(null);
        entranceTypeRadio.clearCheck();
        accessFloorRadio.clearCheck();
        accessFloorObsValue.setText(null);
        accessFloorObsField.setVisibility(View.GONE);
        getChildFragmentManager().beginTransaction().remove(accessType).commit();
        accessType = null;
    }

    private ExternalAccess newExtAccess(Bundle bundle) {
        String location, accessFloorObs = null, accessObs = null, photos = null;
        int accessType, accessFloor;
        Integer hasSound = null;

        location = String.valueOf(entranceLocationValue.getText());
        accessType = getRadioCheckIndex(entranceTypeRadio);
        if (accessType == 1) {
            VehicleExtAccParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));
            hasSound = parcel.getHasSoundSignal();
            accessObs = parcel.getSoundObs();
            photos = parcel.getPhotos();
        }
        accessFloor = getRadioCheckIndex(accessFloorRadio);
        if (accessFloor == 0)
            accessFloorObs = String.valueOf(accessFloorObsValue.getText());

        return new ExternalAccess(bundle.getInt(BLOCK_ID), location, accessType, accessFloor, accessFloorObs, null, null,
                null, null, null, null, null, null, null, null,
                null,null, null, null, null, null, null,
                null, null,null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, hasSound, accessObs, null, photos);
    }

    private ExtAccessSocialOne upExtAccessOne(Bundle bundle) {
        String location, accessFloorObs = null;
        int accessType, accessFloor;

        location = String.valueOf(entranceLocationValue.getText());
        accessType = getRadioCheckIndex(entranceTypeRadio);
        accessFloor = getRadioCheckIndex(accessFloorRadio);
        if (accessFloor == 0)
            accessFloorObs = String.valueOf(accessFloorObsValue.getText());

        return new ExtAccessSocialOne(bundle.getInt(AMBIENT_ID), bundle.getInt(BLOCK_ID), location, accessType, accessFloor, accessFloorObs);
    }
}
