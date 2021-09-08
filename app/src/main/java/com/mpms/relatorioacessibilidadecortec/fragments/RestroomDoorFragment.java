package com.mpms.relatorioacessibilidadecortec.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomDoorUpdate;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

import java.util.ArrayList;
import java.util.Objects;


public class RestroomDoorFragment extends Fragment {

    public static final String GATHER_DOOR_DATA = "GATHER_DOOR_DATA";

    TextInputLayout doorWidthField, doorSIAObsField, doorOpenDirObsField, doorVertSignObsField, doorSillObsField,
            doorTactSignField, doorInternalCoatObsField, doorHorHandleHeightField, doorHorHandleLengthField, doorHorHandleObsField;
    TextInputEditText doorWidthValue, doorSIAObsValue, doorOpenDirObsValue, doorVertSignObsValue, doorSillObsValue,
            doorTactSignObsValue, doorInternalCoatObsValue, doorHorHandleHeightValue, doorHorHandleLengthValue, doorHorHandleObsValue;
    RadioGroup doorHasSIARadio, doorOpenExtDirRadio, doorVertSignRadio, doorSillRadio, doorTactSignRadio,
            doorInternalCoatRadio, doorHorHandleRadio;
    TextView doorSIAError, doorOpenDirError, doorVertSignError, doorSillError, doorTactSignError, doorInternalCoatError,
            doorHorHandleError;
    Button returnEntry, saveEntry;

    Integer doorHasSIA, doorOpenExtDir, doorHasVertSign, doorSillType, doorHasTactSign, doorHasInternalCoat, doorHasHorHandle;
    Double doorWidth, doorHorHandleLength, doorHorHandleHeight;
    String doorSIAObs, doorOpenDirObs, doorVertSignObs, doorSillObs, doorTactSignObs, doorInternalCoatObs, doorHorHandleObs;

    ArrayList<TextInputEditText> obsDoorValues = new ArrayList<>();
    Bundle restroomDoorBundle = new Bundle();
    ViewModelEntry modelEntry;
    ViewModelFragments modelFragments;


    public RestroomDoorFragment() {
        // Required empty public constructor
    }

    public static RestroomDoorFragment newInstance() {
        return new RestroomDoorFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_restroom_door, container, false);
        restroomDoorBundle = this.getArguments();
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        doorWidthField = view.findViewById(R.id.restroom_door_width_field);
        doorSIAObsField = view.findViewById(R.id.door_sia_obs_field);
        doorOpenDirObsField = view.findViewById(R.id.door_opening_direction_obs_field);
        doorVertSignObsField = view.findViewById(R.id.door_vertical_sign_obs_field);
        doorSillObsField = view.findViewById(R.id.restroom_door_sill_obs_field);
        doorTactSignField = view.findViewById(R.id.door_tactile_sign_obs_field);
        doorInternalCoatObsField = view.findViewById(R.id.door_internal_coating_obs_field);
        doorHorHandleHeightField = view.findViewById(R.id.door_horizontal_handle_height_field);
        doorHorHandleLengthField = view.findViewById(R.id.door_horizontal_handle_length_field);
        doorHorHandleObsField = view.findViewById(R.id.door_horizontal_handle_obs_field);

        doorWidthValue = view.findViewById(R.id.restroom_door_width_value);
        doorSIAObsValue = view.findViewById(R.id.door_sia_obs_value);
        doorOpenDirObsValue = view.findViewById(R.id.door_opening_direction_obs_value);
        doorVertSignObsValue = view.findViewById(R.id.door_vertical_sign_obs_value);
        doorSillObsValue = view.findViewById(R.id.restroom_door_sill_obs_value);
        doorTactSignObsValue = view.findViewById(R.id.door_tactile_sign_obs_value);
        doorInternalCoatObsValue = view.findViewById(R.id.door_internal_coating_obs_value);
        doorHorHandleHeightValue = view.findViewById(R.id.door_horizontal_handle_height_value);
        doorHorHandleLengthValue = view.findViewById(R.id.door_horizontal_handle_length_value);
        doorHorHandleObsValue = view.findViewById(R.id.door_horizontal_handle_obs_value);

        doorHasSIARadio = view.findViewById(R.id.door_sia_radio);
        doorOpenExtDirRadio = view.findViewById(R.id.door_opening_direction_radio);
        doorVertSignRadio = view.findViewById(R.id.door_vertical_sign_radio);
        doorSillRadio = view.findViewById(R.id.restroom_door_sill_type_radio);
        doorTactSignRadio = view.findViewById(R.id.door_tactile_sign_radio);
        doorInternalCoatRadio = view.findViewById(R.id.door_internal_coating_radio);
        doorHorHandleRadio = view.findViewById(R.id.door_horizontal_handle_radio);

        doorSIAError = view.findViewById(R.id.door_sia_error);
        doorOpenDirError = view.findViewById(R.id.door_opening_direction_error);
        doorVertSignError = view.findViewById(R.id.door_vertical_sign_error);
        doorSillError = view.findViewById(R.id.restroom_door_sill_type_error);
        doorTactSignError = view.findViewById(R.id.door_tactile_sign_error);
        doorInternalCoatError = view.findViewById(R.id.door_internal_coating_error);
        doorHorHandleError = view.findViewById(R.id.door_horizontal_handle_error);

        saveEntry = view.findViewById(R.id.save_restroom);
        returnEntry = view.findViewById(R.id.return_restroom);

        enableDoorObsScrollingFields();

        doorHorHandleRadio.setOnCheckedChangeListener(this::activateSwitchHandle);

        if (restroomDoorBundle.getBoolean(GATHER_DOOR_DATA)) {
            modelEntry.getOneRestroomEntry(restroomDoorBundle.getInt(RestroomFragment.RESTROOM_ID)).observe(getViewLifecycleOwner(), this::gatherRestroomDoorData);
        }

        saveEntry.setOnClickListener(v -> {
            if(checkEmptyRestDoorDataFields()) {
                RestroomDoorUpdate doorUpdate = restDoorDataUpdate(restroomDoorBundle);
                ViewModelEntry.updateRestroomDoorData(doorUpdate);
                restroomDoorBundle.putBoolean(GATHER_DOOR_DATA, true);
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RestroomUpperViewFragment upperView = RestroomUpperViewFragment.newInstance();
                upperView.setArguments(restroomDoorBundle);
                fragmentTransaction.replace(R.id.show_fragment_selected, upperView).addToBackStack(null).commit();

            }
        });

        returnEntry.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    public RestroomDoorUpdate restDoorDataUpdate(Bundle bundle) {

        //Ignorando dados do Fragmento Filho por enquanto

        doorWidth = Double.valueOf(String.valueOf(doorWidthValue.getText()));

        doorHasSIA = getRestroomDoorCheckedRadio(doorHasSIARadio);
        doorSIAObs = String.valueOf(doorSIAObsValue.getText());

        doorOpenExtDir = getRestroomDoorCheckedRadio(doorOpenExtDirRadio);
        doorOpenDirObs = String.valueOf(doorOpenDirObsValue.getText());

        doorHasVertSign = getRestroomDoorCheckedRadio(doorOpenExtDirRadio);
        doorVertSignObs = String.valueOf(doorVertSignObsValue.getText());

        doorSillType = getRestroomDoorCheckedRadio(doorSillRadio);
        doorSillObs = String.valueOf(doorSillObsValue.getText());

        doorHasTactSign = getRestroomDoorCheckedRadio(doorTactSignRadio);
        doorTactSignObs = String.valueOf(doorTactSignObsValue.getText());

        doorHasInternalCoat = getRestroomDoorCheckedRadio(doorInternalCoatRadio);
        doorInternalCoatObs = String.valueOf(doorInternalCoatObsValue.getText());

        doorHasHorHandle = getRestroomDoorCheckedRadio(doorHorHandleRadio);
        doorHorHandleObs = String.valueOf(doorHorHandleObsValue.getText());

        if (doorHasHorHandle == 1) {
            doorHorHandleLength = Double.valueOf(String.valueOf(doorHorHandleLengthValue.getText()));
            doorHorHandleHeight = Double.valueOf(String.valueOf(doorHorHandleHeightValue.getText()));
        }

        return new RestroomDoorUpdate(bundle.getInt(RestroomFragment.RESTROOM_ID),doorWidth,doorHasSIA,doorSIAObs,
                doorOpenExtDir, doorOpenDirObs, doorHasVertSign, doorVertSignObs,doorSillType,null,
                null, null, null, doorSillObs,doorHasTactSign, doorTactSignObs,
                doorHasInternalCoat,doorInternalCoatObs,doorHasHorHandle,doorHorHandleHeight, doorHorHandleLength, doorHorHandleObs);

    }

    public int getRestroomDoorCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public void activateSwitchHandle(RadioGroup radio, int checkedID) {
        int checkIndex = getRestroomDoorCheckedRadio(radio);
        if (checkIndex == 1) {
            doorHorHandleHeightField.setEnabled(true);
            doorHorHandleLengthField.setEnabled(true);
        } else {
            doorHorHandleHeightValue.setText(null);
            doorHorHandleLengthValue.setText(null);
            doorHorHandleHeightField.setEnabled(false);
            doorHorHandleLengthField.setEnabled(false);
        }
    }

    public boolean scrollingDoorField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    public void addDoorFieldsToArrays() {
        obsDoorValues.add(doorWidthValue);
        obsDoorValues.add(doorSIAObsValue);
        obsDoorValues.add(doorOpenDirObsValue);
        obsDoorValues.add(doorVertSignObsValue);
        obsDoorValues.add(doorSillObsValue);
        obsDoorValues.add(doorTactSignObsValue);
        obsDoorValues.add(doorInternalCoatObsValue);
        obsDoorValues.add(doorHorHandleHeightValue);
        obsDoorValues.add(doorHorHandleLengthValue);
        obsDoorValues.add(doorHorHandleObsValue);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void enableDoorObsScrollingFields() {
        addDoorFieldsToArrays();
        for (TextInputEditText obsScroll : obsDoorValues) {
            obsScroll.setOnTouchListener(this::scrollingDoorField);
        }
    }

    public void gatherRestroomDoorData(RestroomEntry restroomEntry) {
        doorWidthValue.setText(String.valueOf(restroomEntry.getDoorWidth()));

        doorHasSIARadio.check(doorHasSIARadio.getChildAt(restroomEntry.getDoorSIA()).getId());
        doorSIAObsValue.setText(restroomEntry.getDoorSIAObs());

        doorOpenExtDirRadio.check(doorOpenExtDirRadio.getChildAt(restroomEntry.getDoorExtOp()).getId());
        doorOpenDirObsValue.setText(restroomEntry.getDoorExtOpObs());

        doorVertSignRadio.check(doorVertSignRadio.getChildAt(restroomEntry.getDoorVertSign()).getId());
        doorVertSignObsValue.setText(restroomEntry.getDoorVertSignObs());

        doorSillRadio.check(doorSillRadio.getChildAt(restroomEntry.getDoorSillType()).getId());

        doorSillObsValue.setText(restroomEntry.getDoorSillTypeObs());

        doorTactSignRadio.check(doorTactSignRadio.getChildAt(restroomEntry.getDoorTactileSign()).getId());
        doorTactSignObsValue.setText(restroomEntry.getDoorTactileSignObs());

        doorInternalCoatRadio.check(doorInternalCoatRadio.getChildAt(restroomEntry.getDoorIntCoating()).getId());
        doorInternalCoatObsValue.setText(restroomEntry.getDoorIntCoatingObs());

        doorHorHandleRadio.check(doorHorHandleRadio.getChildAt(restroomEntry.getDoorHorizontalHandle()).getId());
        if (restroomEntry.getDoorHorizontalHandle() == 1) {
            doorHorHandleHeightValue.setText(String.valueOf(restroomEntry.getHandleHeight()));
            doorHorHandleLengthValue.setText(String.valueOf(restroomEntry.getHandleLength()));
        }
        doorHorHandleObsValue.setText(restroomEntry.getHandleObs());
    }

    public boolean checkEmptyRestDoorDataFields() {
        clearRestDoorErrors();
        int error = 0;

        if (TextUtils.isEmpty(doorWidthValue.getText())) {
            error++;
            doorWidthField.setError(getString(R.string.blank_field_error));
        }

        if (getRestroomDoorCheckedRadio(doorHasSIARadio) == -1) {
            error++;
            doorSIAError.setVisibility(View.VISIBLE);
        }
        if (getRestroomDoorCheckedRadio(doorOpenExtDirRadio) == -1) {
            error++;
            doorOpenDirError.setVisibility(View.VISIBLE);
        }
        if (getRestroomDoorCheckedRadio(doorVertSignRadio) == -1) {
            error++;
            doorVertSignError.setVisibility(View.VISIBLE);
        }
        if (getRestroomDoorCheckedRadio(doorSillRadio) == -1) {
            error++;
            doorSillError.setVisibility(View.VISIBLE);
        }
        if (getRestroomDoorCheckedRadio(doorTactSignRadio) == -1) {
            error++;
            doorTactSignError.setVisibility(View.VISIBLE);
        }
        if(getRestroomDoorCheckedRadio(doorInternalCoatRadio) == -1) {
            error++;
            doorInternalCoatError.setVisibility(View.VISIBLE);
        }
        if(getRestroomDoorCheckedRadio(doorHorHandleRadio) == -1) {
            error++;
            doorHorHandleError.setVisibility(View.VISIBLE);
        }
        if (getRestroomDoorCheckedRadio(doorHorHandleRadio) == 1) {
            if (TextUtils.isEmpty(doorHorHandleHeightValue.getText())) {
                error++;
                doorHorHandleHeightField.setError(getString(R.string.blank_field_error));
            }
            if (TextUtils.isEmpty(doorHorHandleLengthValue.getText())) {
                error++;
                doorHorHandleLengthField.setError(getString(R.string.blank_field_error));
            }
        }
        return error == 0;
    }

    public void clearRestDoorErrors() {
        doorWidthField.setErrorEnabled(false);
        doorSIAError.setVisibility(View.GONE);
        doorOpenDirError.setVisibility(View.GONE);
        doorVertSignError.setVisibility(View.GONE);
        doorSillError.setVisibility(View.GONE);
        doorTactSignError.setVisibility(View.GONE);
        doorInternalCoatError.setVisibility(View.GONE);
        doorHorHandleError.setVisibility(View.GONE);
        doorHorHandleHeightField.setErrorEnabled(false);
        doorHorHandleLengthField.setErrorEnabled(false);

    }

}