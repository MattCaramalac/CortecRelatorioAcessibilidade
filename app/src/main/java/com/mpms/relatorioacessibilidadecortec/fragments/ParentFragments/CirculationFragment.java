package com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CirculationFragment extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

    TextInputLayout circLocField, vertSignObsField, carpetObsField, accessFloorField, intercomHeightField, intercomObsField, biometryHeightField, biometryObsField,
            photoField, obsField;
    TextInputEditText circLocValue, vertSignObsValue, carpetObsValue, accessFloorValue, intercomHeightValue, intercomObsValue, biometryHeightValue, biometryObsValue,
            photoValue, obsValue;
    RadioGroup vertSignRadio, carpetRadio, accessFloorRadio, intercomRadio, bioClockRadio;
    TextView vertSignError, carpetError, accessError, intercomError, bioClockError;
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
            circBundle = new Bundle(getArguments());
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

        continueCirc.setOnClickListener(v -> {
            if (noEmptyFields())
                saveUpdateCirculation(circBundle);
            else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });

        cancelCirc.setOnClickListener(v -> cancelClick());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!circBundle.getBoolean(RECENT_ENTRY)) {
            modelEntry.getLastCirculation().observe(getViewLifecycleOwner(), entry -> {
                if (circBundle.getBoolean(RECENT_ENTRY))
                    callNextFragment(entry);
            });
        }
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

        photoValue = view.findViewById(R.id.circ_photo_value);
        obsValue = view.findViewById(R.id.circ_obs_value);
//        RadioGroup
        vertSignRadio = view.findViewById(R.id.circ_has_visual_sign_radio);
        carpetRadio = view.findViewById(R.id.circ_has_carpet_radio);
        accessFloorRadio = view.findViewById(R.id.circ_accessible_floor_radio);
        intercomRadio = view.findViewById(R.id.circ_has_intercom_radio);
        bioClockRadio = view.findViewById(R.id.circ_has_biometry_radio);

//        TextView
        vertSignError = view.findViewById(R.id.circ_visual_sign_error);
        carpetError = view.findViewById(R.id.circ_carpet_error);
        accessError = view.findViewById(R.id.circ_accessible_floor_error);
        intercomError = view.findViewById(R.id.circ_has_intercom_error);
        bioClockError = view.findViewById(R.id.circ_has_biometry_error);

//        MaterialButton
        cancelCirc = view.findViewById(R.id.cancel_circ);
        continueCirc = view.findViewById(R.id.continue_circ);

//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listeners
        accessFloorRadio.setOnCheckedChangeListener(this::radioListener);
        intercomRadio.setOnCheckedChangeListener(this::radioListener);
        bioClockRadio.setOnCheckedChangeListener(this::radioListener);

//        Utility
        editTextFields();
        allowObsScroll(eText);
    }

    private void saveUpdateCirculation(Bundle bundle) {
        CirculationEntry entry = createCirculation(bundle);
        if (bundle.getInt(CIRC_ID) > 0) {
            entry.setCircID(bundle.getInt(CIRC_ID));
            ViewModelEntry.updateCirculation(entry);
            callNextFragment(bundle);
        } else if (bundle.getInt(CIRC_ID) == 0) {
            bundle.putBoolean(RECENT_ENTRY, true);
            ViewModelEntry.insertCirculation(entry);
        } else {
            circBundle.putInt(CIRC_ID, 0);
            Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
        }
    }

    private void callNextFragment(CirculationEntry entry) {
        circBundle.putInt(CIRC_ID, entry.getCircID());
        CirculationTwoFragment fragment = new CirculationTwoFragment();
        fragment.setArguments(circBundle);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, fragment).addToBackStack(null).commit();
    }

    private void callNextFragment(Bundle bundle) {
        CirculationTwoFragment fragment = new CirculationTwoFragment();
        fragment.setArguments(bundle);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.show_fragment_selected, fragment).addToBackStack(null).commit();
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

        vertSignError.setVisibility(View.GONE);
        carpetError.setVisibility(View.GONE);
        accessError.setVisibility(View.GONE);
        intercomError.setVisibility(View.GONE);
        bioClockError.setVisibility(View.GONE);
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
        String location, vertObs = null, carpetObs = null, accessObs = null, intercomObs = null, bioObs = null,  photo = null, circObs = null;
        Double interHeight = null, bioHeight = null;
        int vertSign, carpet, access, intercom, bioClock, unevenFloor;


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


        if (!TextUtils.isEmpty(photoValue.getText()))
            photo = String.valueOf(photoValue.getText());
        if (!TextUtils.isEmpty(obsValue.getText()))
            circObs = String.valueOf(obsValue.getText());

        return new CirculationEntry(bundle.getInt(SCHOOL_ID), location, vertSign, vertObs, carpet, carpetObs, access, accessObs, intercom, interHeight, intercomObs,
                bioClock, bioHeight, bioObs, photo, circObs);
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
        }
    }
}