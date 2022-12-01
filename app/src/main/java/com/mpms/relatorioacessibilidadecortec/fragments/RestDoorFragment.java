package com.mpms.relatorioacessibilidadecortec.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestDoorUpdate;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SillInclinationFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SillSlopeFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.SillStepFragment;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import java.util.ArrayList;


public class RestDoorFragment extends Fragment implements TagInterface {

    TextInputLayout widthField, pictObsField, opDirObsField, coatHeightField, coatObsField, vertSignObsField, sillObsField, tactSignObsField,
            horHandleHeightField, horHandleLengthField, horHandleDiamField, horHandleObsField;
    TextInputEditText widthValue, pictObsValue, opDirObsValue, coatHeightValue, coatObsValue, vertSignObsValue, sillObsValue, tactSignObsValue,
            horHandleHeightValue, horHandleLengthValue, horHandleDiamValue, horHandleObsValue;
    RadioGroup pictRadio, opDirRadio, coatRadio, vertSignRadio, tactSignRadio, horHandleRadio;
    MultiLineRadioGroup doorSillRadio;
    TextView pictError, opDirError, coatError, vertSignError, sillError, tactSignError, horHandleError;
    FrameLayout sillFrag;
    Button returnEntry, saveEntry;


    ArrayList<TextInputEditText> obsDoorValues = new ArrayList<>();
    Bundle restDoorBundle = new Bundle();

    ViewModelEntry modelEntry;
    ViewModelFragments modelFragments;
    FragmentManager manager;


    public RestDoorFragment() {
        // Required empty public constructor
    }

    public static RestDoorFragment newInstance() {
        return new RestDoorFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            restDoorBundle = new Bundle(this.getArguments());
        else
            restDoorBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restroom_door, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateRestDoorViews(view);
        enableDoorObsScrollingFields();

        if (restDoorBundle.getInt(REST_ID) > 0) {
            modelEntry.getRestDoorData(restDoorBundle.getInt(REST_ID))
                    .observe(getViewLifecycleOwner(), this::loadRestDoorData);
        }

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER, this, (key, bundle) -> {
            if (checkEmptyRestDoorDataFields() && bundle.getBoolean(CHILD_DATA_COMPLETE)) {
                updateRestData(bundle);
            }
        });


    }

    private void instantiateRestDoorViews(View view) {
//        TextInputLayout
        widthField = view.findViewById(R.id.restroom_door_width_field);
        pictObsField = view.findViewById(R.id.door_sia_obs_field);
        opDirObsField = view.findViewById(R.id.door_opening_direction_obs_field);
        coatHeightField = view.findViewById(R.id.door_coat_height_field);
        coatObsField = view.findViewById(R.id.door_coat_obs_field);
        vertSignObsField = view.findViewById(R.id.door_vertical_sign_obs_field);
        sillObsField = view.findViewById(R.id.rest_door_sill_obs_field);
        tactSignObsField = view.findViewById(R.id.rest_door_tactile_obs_field);
        horHandleHeightField = view.findViewById(R.id.door_horizontal_handle_height_field);
        horHandleLengthField = view.findViewById(R.id.door_horizontal_handle_length_field);
        horHandleDiamField = view.findViewById(R.id.door_horizontal_handle_diam_field);
        horHandleObsField = view.findViewById(R.id.door_horizontal_handle_obs_field);
//        TextInputEditText
        widthValue = view.findViewById(R.id.restroom_door_width_value);
        pictObsValue = view.findViewById(R.id.door_sia_obs_value);
        opDirObsValue = view.findViewById(R.id.door_opening_direction_obs_value);
        coatHeightValue = view.findViewById(R.id.door_coat_height_value);
        coatObsValue = view.findViewById(R.id.door_coat_obs_value);
        vertSignObsValue = view.findViewById(R.id.door_vertical_sign_obs_value);
        sillObsValue = view.findViewById(R.id.rest_door_sill_obs_value);
        tactSignObsValue = view.findViewById(R.id.rest_door_tactile_obs_value);
        horHandleHeightValue = view.findViewById(R.id.door_horizontal_handle_height_value);
        horHandleLengthValue = view.findViewById(R.id.door_horizontal_handle_length_value);
        horHandleDiamValue = view.findViewById(R.id.door_horizontal_handle_diam_value);
        horHandleObsValue = view.findViewById(R.id.door_horizontal_handle_obs_value);
//        RadioGroup
        pictRadio = view.findViewById(R.id.door_sia_radio);
        opDirRadio = view.findViewById(R.id.door_opening_direction_radio);
        coatRadio = view.findViewById(R.id.door_coating_radio);
        vertSignRadio = view.findViewById(R.id.door_vertical_sign_radio);
        tactSignRadio = view.findViewById(R.id.rest_door_tactile_radio);
        horHandleRadio = view.findViewById(R.id.door_horizontal_handle_radio);
//        MultiLine RadioGroup
        doorSillRadio = view.findViewById(R.id.rest_door_sill_radio);
//        TextView
        pictError = view.findViewById(R.id.door_sia_error);
        opDirError = view.findViewById(R.id.door_opening_direction_error);
        coatError = view.findViewById(R.id.door_coating_error);
        vertSignError = view.findViewById(R.id.door_vertical_sign_error);
        sillError = view.findViewById(R.id.rest_door_sill_error);
        tactSignError = view.findViewById(R.id.rest_door_tactile_error);
        horHandleError = view.findViewById(R.id.door_horizontal_handle_error);
//        Child Fragment
        sillFrag = view.findViewById(R.id.door_sill_fragment);
//        MaterialButton
        saveEntry = view.findViewById(R.id.save_restroom);
        returnEntry = view.findViewById(R.id.return_restroom);
//        ViewModel & Managers
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
        manager = getChildFragmentManager();
//        Listeners
        saveEntry.setOnClickListener(v -> saveClickMethod(restDoorBundle));
        returnEntry.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
        horHandleRadio.setOnCheckedChangeListener(this::handleCheckListener);
        coatRadio.setOnCheckedChangeListener(this::handleCheckListener);
        doorSillRadio.setOnCheckedChangeListener((MultiLineRadioGroup.OnCheckedChangeListener)
                (v, r) -> doorMultiRadioListener(doorSillRadio));
    }

    private void doorMultiRadioListener(MultiLineRadioGroup multi) {
        int index = multi.getCheckedRadioButtonIndex();
        switch (index) {
            case 1:
                getChildFragmentManager().beginTransaction().replace(R.id.door_sill_fragment, new SillInclinationFragment()).commit();
                break;
            case 2:
                getChildFragmentManager().beginTransaction().replace(R.id.door_sill_fragment, new SillStepFragment()).commit();
                break;
            case 3:
                getChildFragmentManager().beginTransaction().replace(R.id.door_sill_fragment, new SillSlopeFragment()).commit();
                break;
            default:
                removeSillFragments();
                break;
        }
    }

    private void removeSillFragments() {
        Fragment fragment = getChildFragmentManager().findFragmentById(R.id.door_sill_fragment);
        if (fragment != null)
            getChildFragmentManager().beginTransaction().remove(fragment).commit();

    }

    private void saveClickMethod(Bundle bundle) {
        bundle.putBoolean(ADD_ITEM_REQUEST, false);
        if (doorSillRadio.getCheckedRadioButtonIndex() > 0) {
            getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA, bundle);
        } else {
            if (checkEmptyRestDoorDataFields()) {
                updateRestData(bundle);
            } else {
                Toast.makeText(getContext(), "Um ou mais campos não foram preenchidos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateRestData(Bundle bundle) {
        RestDoorUpdate doorUpdate = restDoorData(bundle);
        ViewModelEntry.updateRestDoorData(doorUpdate);
        RestUpViewFragment upperView = RestUpViewFragment.newInstance();
        upperView.setArguments(bundle);
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, upperView).addToBackStack(null).commit();
    }

    public RestDoorUpdate restDoorData(Bundle bundle) {

        int hasPic, opDir, hasCoat, hasVertSign, doorSill, hasTactSign, hasHorHandle;
        Integer slopeQnt = null;
        double doorWidth;
        Double coatHeight = null, incHeight = null, stepHeight = null, slopeAngle1 = null, slopeAngle2 = null, slopeAngle3 = null,
                slopeAngle4 = null, slopeWidth = null, slopeHeight = null, handleHeight = null, handleLength = null, handleDiam = null;
        String picObs, opDirObs, coatObs, vSignObs, sillObs, tSignObs, horHandleObs;

        doorWidth = Double.parseDouble(String.valueOf(widthValue.getText()));
        hasPic = getRestroomDoorCheckedRadio(pictRadio);
        picObs = String.valueOf(pictObsValue.getText());

        opDir = getRestroomDoorCheckedRadio(opDirRadio);
        opDirObs = String.valueOf(opDirObsValue.getText());

        hasCoat = getRestroomDoorCheckedRadio(coatRadio);
        if (hasCoat == 1)
            coatHeight = Double.parseDouble(String.valueOf(coatHeightValue.getText()));
        coatObs = String.valueOf(coatObsValue.getText());

        hasVertSign = getRestroomDoorCheckedRadio(vertSignRadio);
        vSignObs = String.valueOf(vertSignObsValue.getText());

        doorSill = doorSillRadio.getCheckedRadioButtonIndex();
        if (doorSill == 1) {
            incHeight = bundle.getDouble(HEIGHT_INCLINED_SILL);
        } else if (doorSill == 2) {
            stepHeight = bundle.getDouble(STEP_HEIGHT);
        } else if (doorSill == 3) {
            slopeWidth = bundle.getDouble(SLOPE_WIDTH);
            slopeHeight = bundle.getDouble(SLOPE_HEIGHT);
            slopeQnt = bundle.getInt(SLOPE_QNT);
            switch (slopeQnt) {
                case 4:
                    slopeAngle4 = bundle.getDouble(SLOPE_ANGLE_4);
                case 3:
                    slopeAngle3 = bundle.getDouble(SLOPE_ANGLE_3);
                case 2:
                    slopeAngle2 = bundle.getDouble(SLOPE_ANGLE_2);
                case 1:
                    slopeAngle1 = bundle.getDouble(SLOPE_ANGLE_1);
                    break;
                default:
                    break;
            }
        }
        sillObs = String.valueOf(sillObsValue.getText());

        hasTactSign = getRestroomDoorCheckedRadio(tactSignRadio);
        tSignObs = String.valueOf(tactSignObsValue.getText());

        hasHorHandle = getRestroomDoorCheckedRadio(horHandleRadio);
        if (hasHorHandle == 1) {
            handleHeight = Double.parseDouble(String.valueOf(horHandleHeightValue.getText()));
            handleLength = Double.parseDouble(String.valueOf(horHandleLengthValue.getText()));
            handleDiam = Double.parseDouble(String.valueOf(horHandleDiamValue.getText()));
        }
        horHandleObs = String.valueOf(horHandleObsValue.getText());

        return new RestDoorUpdate(bundle.getInt(REST_ID), doorWidth, hasPic, picObs, opDir, opDirObs, hasCoat, coatHeight, coatObs,
                hasVertSign, vSignObs, doorSill, incHeight, stepHeight, slopeQnt, slopeAngle1, slopeAngle2, slopeAngle3, slopeAngle4, slopeWidth,
                slopeHeight, sillObs, hasTactSign, tSignObs, hasHorHandle, handleHeight, handleLength, handleDiam, horHandleObs);
    }

    public int getRestroomDoorCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public void handleCheckListener(RadioGroup radio, int checkedID) {
        int index = getRestroomDoorCheckedRadio(radio);

        if (radio == horHandleRadio) {
            if (index == 1) {
                horHandleLengthField.setVisibility(View.VISIBLE);
                horHandleHeightField.setVisibility(View.VISIBLE);
                horHandleDiamField.setVisibility(View.VISIBLE);
            } else {
                horHandleLengthValue.setText(null);
                horHandleHeightValue.setText(null);
                horHandleDiamValue.setText(null);
                horHandleLengthField.setVisibility(View.GONE);
                horHandleHeightField.setVisibility(View.GONE);
                horHandleDiamField.setVisibility(View.GONE);
            }
        } else if (radio == coatRadio) {
            if (index == 1)
                coatHeightField.setVisibility(View.VISIBLE);
            else {
                coatHeightValue.setText(null);
                coatHeightField.setVisibility(View.GONE);
            }
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
        obsDoorValues.add(pictObsValue);
        obsDoorValues.add(opDirObsValue);
        obsDoorValues.add(coatObsValue);
        obsDoorValues.add(vertSignObsValue);
        obsDoorValues.add(sillObsValue);
        obsDoorValues.add(tactSignObsValue);
        obsDoorValues.add(horHandleObsValue);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void enableDoorObsScrollingFields() {
        addDoorFieldsToArrays();
        for (TextInputEditText obsScroll : obsDoorValues) {
            obsScroll.setOnTouchListener(this::scrollingDoorField);
        }
    }

    public void loadRestDoorData(RestroomEntry restroomEntry) {
        if (restroomEntry.getDoorWidth() != null)
            widthValue.setText(String.valueOf(restroomEntry.getDoorWidth()));

        if (restroomEntry.getHasPict() != null)
            pictRadio.check(pictRadio.getChildAt(restroomEntry.getHasPict()).getId());
        pictObsValue.setText(restroomEntry.getPictObs());

        if (restroomEntry.getOpDir() != null)
            opDirRadio.check(opDirRadio.getChildAt(restroomEntry.getOpDir()).getId());
        opDirObsValue.setText(restroomEntry.getOpDirObs());

        if (restroomEntry.getHasCoat() != null) {
            coatRadio.check(coatRadio.getChildAt(restroomEntry.getHasCoat()).getId());
            if (restroomEntry.getHasCoat() == 1)
                coatHeightValue.setText(String.valueOf(restroomEntry.getCoatHeight()));
        }
        coatObsValue.setText(restroomEntry.getCoatObs());

        if (restroomEntry.getHasVertSign() != null)
            vertSignRadio.check(vertSignRadio.getChildAt(restroomEntry.getHasVertSign()).getId());
        vertSignObsValue.setText(restroomEntry.getVertSignObs());

        if (restroomEntry.getSillType() != null && restroomEntry.getSillType() > -1) {
            doorSillRadio.checkAt(restroomEntry.getSillType());
            if (restroomEntry.getSillType() > 0)
                getChildFragmentManager().setFragmentResult(LOAD_CHILD_DATA, restDoorBundle);
        }
        sillObsValue.setText(restroomEntry.getSillTypeObs());

        if (restroomEntry.getHasTactSign() != null)
            tactSignRadio.check(tactSignRadio.getChildAt(restroomEntry.getHasTactSign()).getId());
        tactSignObsValue.setText(restroomEntry.getTactSignObs());

        if (restroomEntry.getHasHorHandle() != null) {
            horHandleRadio.check(horHandleRadio.getChildAt(restroomEntry.getHasHorHandle()).getId());
            if (restroomEntry.getHasHorHandle() == 1) {
                horHandleHeightField.setVisibility(View.VISIBLE);
                horHandleLengthField.setVisibility(View.VISIBLE);
                horHandleDiamField.setVisibility(View.VISIBLE);
                horHandleHeightValue.setText(String.valueOf(restroomEntry.getHandleHeight()));
                horHandleLengthValue.setText(String.valueOf(restroomEntry.getHandleLength()));
                horHandleDiamValue.setText(String.valueOf(restroomEntry.getHandleDiam()));
            }
        }
        horHandleObsValue.setText(restroomEntry.getHandleObs());
    }

    public boolean checkEmptyRestDoorDataFields() {
        clearRestDoorErrors();
        int error = 0;

        if (TextUtils.isEmpty(widthValue.getText())) {
            error++;
            widthField.setError(getString(R.string.req_field_error));
        }
        if (getRestroomDoorCheckedRadio(pictRadio) == -1) {
            error++;
            pictError.setVisibility(View.VISIBLE);
        }
        if (getRestroomDoorCheckedRadio(opDirRadio) == -1) {
            error++;
            opDirError.setVisibility(View.VISIBLE);
        }
        if (getRestroomDoorCheckedRadio(coatRadio) == -1) {
            error++;
            coatError.setVisibility(View.VISIBLE);
        }
        if (getRestroomDoorCheckedRadio(vertSignRadio) == -1) {
            error++;
            vertSignError.setVisibility(View.VISIBLE);
        }
        if (doorSillRadio.getCheckedRadioButtonIndex() == -1) {
            error++;
            sillError.setVisibility(View.VISIBLE);
        }
        if (getRestroomDoorCheckedRadio(tactSignRadio) == -1) {
            error++;
            tactSignError.setVisibility(View.VISIBLE);
        }
        if (getRestroomDoorCheckedRadio(horHandleRadio) == -1) {
            error++;
            horHandleError.setVisibility(View.VISIBLE);
        }
        if (getRestroomDoorCheckedRadio(horHandleRadio) == 1) {
            if (TextUtils.isEmpty(horHandleHeightValue.getText())) {
                error++;
                horHandleHeightField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(horHandleLengthValue.getText())) {
                error++;
                horHandleLengthField.setError(getString(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(horHandleDiamValue.getText())) {
                error++;
                horHandleDiamField.setError(getString(R.string.req_field_error));
            }
        }
        return error == 0;
    }

    public void clearRestDoorErrors() {
        widthField.setErrorEnabled(false);
        pictError.setVisibility(View.GONE);
        opDirError.setVisibility(View.GONE);
        coatError.setVisibility(View.GONE);
        vertSignError.setVisibility(View.GONE);
        sillError.setVisibility(View.GONE);
        tactSignError.setVisibility(View.GONE);
        horHandleError.setVisibility(View.GONE);
        horHandleHeightField.setErrorEnabled(false);
        horHandleLengthField.setErrorEnabled(false);
        horHandleDiamField.setErrorEnabled(false);
    }

    public void openChildFragment(RadioGroup radio, int checkedID) {
        int index = getRestroomDoorCheckedRadio(radio);
        switch (index) {
            case 1:
                getChildFragmentManager().beginTransaction().replace(R.id.door_sill_fragment, new SillInclinationFragment()).commit();
                break;
            case 2:
                getChildFragmentManager().beginTransaction().replace(R.id.door_sill_fragment, new SillStepFragment()).commit();
                break;
            case 3:
                getChildFragmentManager().beginTransaction().replace(R.id.door_sill_fragment, new SillSlopeFragment()).commit();
                break;
            default:
                removeSillTypeFragments();
                break;
        }
    }

    public void removeSillTypeFragments() {
        Fragment fragment = manager.findFragmentById(R.id.door_sill_fragment);
        if (fragment != null)
            manager.beginTransaction().remove(fragment).commit();
    }

}