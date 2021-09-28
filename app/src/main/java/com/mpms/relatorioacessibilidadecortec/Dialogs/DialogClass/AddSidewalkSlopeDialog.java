package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.SidewalkSlopeEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelDialog;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import static com.mpms.relatorioacessibilidadecortec.fragments.SidewalkFragment.SIDEWALK_ID;

public class AddSidewalkSlopeDialog extends DialogFragment {

    private Toolbar toolbar;

    TextInputLayout slopeLocationField, slopeWidthField, frontalInclinationField, leftInclinationField, rightInclinationField,
            slopeFreeSpaceField, slopeObsField;
    TextInputEditText slopeLocationValue, slopeWidthValue, frontalInclinationValue, leftInclinationValue, rightInclinationValue,
            slopeFreeSpaceValue, slopeObsValue;
    RadioGroup slopeHasTactileFloorRadio;
    TextView slopeHasTactileFloorError;
    Button cancelSlope, saveSlope;

    String slopeLocation, slopeObs;
    Integer slopeHasTactileFloor;
    Double slopeWidth, frontalInclination, leftInclination, rightInclination, freeSpace;

    static Bundle sidewalkBundle = new Bundle();
    ViewModelDialog modelDialog;
    int slopeCounter = 0;

    public static AddSidewalkSlopeDialog sidewalkSlope(FragmentManager fragmentManager, Bundle bundle) {
        AddSidewalkSlopeDialog sidewalkSlope = new AddSidewalkSlopeDialog();
        sidewalkSlope.show(fragmentManager, "SLOPE_DIALOG");
        sidewalkBundle = bundle;
        return sidewalkSlope;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        modelDialog = new ViewModelProvider(requireActivity()).get(ViewModelDialog.class);
        View view = inflater.inflate(R.layout.fragment_add_sidewalk_slope_dialog, container, false);
        toolbar = view.findViewById(R.id.sidewalk_slope_toolbar);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle(R.string.dialog_add_sidewalk_slope);

        instantiateSidewalkSlopeViews(view);
        allowSidewalkSlopeObsScroll();

        saveSlope.setOnClickListener( v -> {
            if (checkEmptySidewalkSlopeFields()) {
                SidewalkSlopeEntry newSlope = newSlope(sidewalkBundle);
                ViewModelEntry.insertSidewalkSlopeEntry(newSlope);
                clearSidewalkSlopeFields();
                slopeCounter++;
            }
        });

        cancelSlope.setOnClickListener( v-> requireActivity().getSupportFragmentManager()
                .beginTransaction().remove(this).commit());

    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int length = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, length);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        modelDialog.setSidewalkSlopeCounter(slopeCounter);
    }

    private void instantiateSidewalkSlopeViews(View v) {
//        TextInputLayout
        slopeLocationField = v.findViewById(R.id.slope_identification_field);
        slopeWidthField = v.findViewById(R.id.slope_width_field);
        frontalInclinationField = v.findViewById(R.id.slope_frontal_inclination_field);
        leftInclinationField = v.findViewById(R.id.left_brim_inclination_field);
        rightInclinationField = v.findViewById(R.id.right_brim_inclination_field);
        slopeFreeSpaceField = v.findViewById(R.id.slope_free_space_field);
        slopeObsField = v.findViewById(R.id.slope_obs_field);
//        TextInputEditText
        slopeLocationValue = v.findViewById(R.id.slope_identification_value);
        slopeWidthValue = v.findViewById(R.id.slope_width_value);
        frontalInclinationValue = v.findViewById(R.id.slope_frontal_inclination_value);
        leftInclinationValue = v.findViewById(R.id.left_brim_inclination_value);
        rightInclinationValue = v.findViewById(R.id.right_brim_inclination_value);
        slopeFreeSpaceValue = v.findViewById(R.id.slope_free_space_value);
        slopeObsValue = v.findViewById(R.id.slope_obs_value);
//        RadioGroup
        slopeHasTactileFloorRadio = v.findViewById(R.id.slope_has_tactile_floor_radio);
//        TextView
        slopeHasTactileFloorError = v.findViewById(R.id.slope_has_tactile_floor_error);
//        Button
        cancelSlope = v.findViewById(R.id.cancel_sidewalk_slope);
        saveSlope = v.findViewById(R.id.save_sidewalk_slope);
    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowSidewalkSlopeObsScroll() {
        slopeObsValue.setOnTouchListener(this::scrollingField);
    }

    private int getSidewalkSlopeRadioCheck(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private boolean checkEmptySidewalkSlopeFields() {
        clearSidewalkSlopeEmptyFieldsErrors();
        int i = 0;
        if (TextUtils.isEmpty(slopeLocationValue.getText())){
            i++;
            slopeLocationField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(slopeWidthValue.getText())){
            i++;
            slopeWidthField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(frontalInclinationValue.getText())){
            i++;
            frontalInclinationField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(leftInclinationValue.getText())){
            i++;
            leftInclinationField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(rightInclinationValue.getText())){
            i++;
            rightInclinationField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(slopeFreeSpaceValue.getText())){
            i++;
            slopeFreeSpaceField.setError(getString(R.string.blank_field_error));
        }
        if (getSidewalkSlopeRadioCheck(slopeHasTactileFloorRadio) == -1) {
            i++;
            slopeHasTactileFloorError.setVisibility(View.VISIBLE);
        }
        return i == 0;
    }

    private void clearSidewalkSlopeEmptyFieldsErrors() {
        slopeLocationField.setErrorEnabled(false);
        slopeWidthField.setErrorEnabled(false);
        frontalInclinationField.setErrorEnabled(false);
        leftInclinationField.setErrorEnabled(false);
        rightInclinationField.setErrorEnabled(false);
        slopeFreeSpaceField.setErrorEnabled(false);
        slopeHasTactileFloorError.setVisibility(View.GONE);
    }

    private void clearSidewalkSlopeFields() {
        slopeLocationValue.setText(null);
        slopeWidthValue.setText(null);
        frontalInclinationValue.setText(null);
        leftInclinationValue.setText(null);
        rightInclinationValue.setText(null);
        slopeFreeSpaceValue.setText(null);
        slopeObsValue.setText(null);
        slopeHasTactileFloorRadio.clearCheck();
    }

    private SidewalkSlopeEntry newSlope(Bundle bundle) {
        slopeLocation = String.valueOf(slopeLocationValue.getText());
        slopeObs = String.valueOf(slopeObsValue.getText());
        slopeHasTactileFloor = getSidewalkSlopeRadioCheck(slopeHasTactileFloorRadio);
        slopeWidth = Double.valueOf(String.valueOf(slopeWidthValue.getText()));
        frontalInclination = Double.valueOf(String.valueOf(frontalInclinationValue.getText()));
        leftInclination = Double.valueOf(String.valueOf(leftInclinationValue.getText()));
        rightInclination = Double.valueOf(String.valueOf(rightInclinationValue.getText()));
        freeSpace = Double.valueOf(String.valueOf(slopeFreeSpaceValue.getText()));

        return new SidewalkSlopeEntry(bundle.getInt(SIDEWALK_ID),slopeLocation, slopeWidth, frontalInclination,
                leftInclination, rightInclination, slopeHasTactileFloor, freeSpace, slopeObs);
    }
}