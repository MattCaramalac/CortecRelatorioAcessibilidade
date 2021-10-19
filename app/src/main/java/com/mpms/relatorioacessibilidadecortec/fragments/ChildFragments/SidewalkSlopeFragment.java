package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
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

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.ArrayList;

public class SidewalkSlopeFragment extends Fragment {

    TextInputLayout slopeLocaleField, slopeWidthField, longMeasureOneField, longMeasureTwoField, longMeasureThreeField,
            longMeasureFourField, longMeasureFiveField, longMeasureSixField, leftMeasureOneField, leftMeasureTwoField,
            leftMeasureThreeField, leftMeasureFourField, leftMeasureFiveField, leftMeasureSixField, rightMeasureOneField,
            rightMeasureTwoField, rightMeasureThreeField, rightMeasureFourField, rightMeasureFiveField,
            rightMeasureSixField, obsField;
    TextInputEditText slopeLocaleValue, slopeWidthValue, longMeasureOneValue, longMeasureTwoValue, longMeasureThreeValue,
            longMeasureFourValue, longMeasureFiveValue, longMeasureSixValue, leftMeasureOneValue, leftMeasureTwoValue,
            leftMeasureThreeValue, leftMeasureFourValue, leftMeasureFiveValue, leftMeasureSixValue, rightMeasureOneValue,
            rightMeasureTwoValue, rightMeasureThreeValue, rightMeasureFourValue, rightMeasureFiveValue,
            rightMeasureSixValue, obsValue;
    MaterialButton longButton, leftButton, rightButton, cancelSlope, saveSlope;
    ImageButton deleteLong, deleteLeft, deleteRight;
    RadioGroup hasTactileFloor;
    TextView tactileFloorError;

    Bundle slopeBundle = new Bundle();

    ViewModelEntry modelEntry;

    ArrayList<TextInputLayout> longFields = new ArrayList<>();
    ArrayList<TextInputLayout> leftFields = new ArrayList<>();
    ArrayList<TextInputLayout> rightFields = new ArrayList<>();

    int longCounter = 0;
    int leftCounter = 0;
    int rightCounter = 0;

    public SidewalkSlopeFragment() {
        // Required empty public constructor
    }

    public static SidewalkSlopeFragment newInstance() {
        return new SidewalkSlopeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            slopeBundle.putInt(SidewalkSlopeListFragment.SIDEWALK_SLOPE_ID,
                    this.getArguments().getInt(SidewalkSlopeListFragment.SIDEWALK_SLOPE_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sidewalk_slope, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSlopeViews(view);

        longButton.setOnClickListener(v -> {
            if (longCounter < 0) {
                longCounter = 0;
                Toast.makeText(getContext(), "Ocorreu um erro. Por Favor, tente novamente!", Toast.LENGTH_SHORT).show();
            } else if (longCounter < 6) {
                if (longCounter == 0)
                    deleteLong.setVisibility(View.VISIBLE);
                longFields.get(longCounter).setVisibility(View.VISIBLE);
                longCounter++;
            } else
                Toast.makeText(getContext(), "O limite de medições foi atingido!", Toast.LENGTH_SHORT).show();
        });

        leftButton.setOnClickListener(v -> {
            if (leftCounter < 0) {
                leftCounter = 0;
                Toast.makeText(getContext(), "Ocorreu um erro. Por Favor, tente novamente!", Toast.LENGTH_SHORT).show();
            } else if (leftCounter < 6) {
                if (leftCounter == 0) {
                    deleteLeft.setVisibility(View.VISIBLE);
                }
                leftFields.get(leftCounter).setVisibility(View.VISIBLE);
                leftCounter++;
            } else {
                Toast.makeText(getContext(), "O limite de medições foi atingido!", Toast.LENGTH_SHORT).show();
            }
        });

        rightButton.setOnClickListener(v -> {
            if (rightCounter < 0) {
                rightCounter = 0;
                Toast.makeText(getContext(), "Ocorreu um erro. Por Favor, tente novamente!", Toast.LENGTH_SHORT).show();
            } else if (rightCounter < 6) {
                if (rightCounter == 0) {
                    deleteRight.setVisibility(View.VISIBLE);
                }
                rightFields.get(rightCounter).setVisibility(View.VISIBLE);
                rightCounter++;
            } else {
                Toast.makeText(getContext(), "O limite de medições foi atingido!", Toast.LENGTH_SHORT).show();
            }
        });

        deleteLong.setOnClickListener(v -> {
            if (longCounter > 0) {
                longFields.get(longCounter-1).getEditText().setText(null);
                longFields.get(longCounter-1).setVisibility(View.GONE);
                longCounter--;
                if (longCounter == 0)
                    deleteLong.setVisibility(View.GONE);
            }
        });

        deleteLeft.setOnClickListener(v -> {
            if (leftCounter > 0) {
                leftFields.get(leftCounter-1).getEditText().setText(null);
                leftFields.get(leftCounter-1).setVisibility(View.GONE);
                leftCounter--;
                if (leftCounter == 0)
                    deleteLeft.setVisibility(View.GONE);
            }
        });

        deleteRight.setOnClickListener(v -> {
            if (rightCounter > 0) {
                rightFields.get(rightCounter-1).getEditText().setText(null);
                rightFields.get(rightCounter-1).setVisibility(View.GONE);
                rightCounter--;
                if (rightCounter == 0)
                    deleteRight.setVisibility(View.GONE);
            }
        });

        cancelSlope.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        saveSlope.setOnClickListener(v -> {

        });
    }

    private void instantiateSlopeViews(View view) {
//        TextInputLayout
        slopeLocaleField = view.findViewById(R.id.slope_locale_field);
        slopeWidthField = view.findViewById(R.id.slope_width_field);
        longMeasureOneField = view.findViewById(R.id.slope_longitudinal_1_field);
        longMeasureTwoField = view.findViewById(R.id.slope_longitudinal_2_field);
        longMeasureThreeField = view.findViewById(R.id.slope_longitudinal_3_field);
        longMeasureFourField = view.findViewById(R.id.slope_longitudinal_4_field);
        longMeasureFiveField = view.findViewById(R.id.slope_longitudinal_5_field);
        longMeasureSixField = view.findViewById(R.id.slope_longitudinal_6_field);
        leftMeasureOneField = view.findViewById(R.id.left_wing_1_field);
        leftMeasureTwoField = view.findViewById(R.id.left_wing_2_field);
        leftMeasureThreeField = view.findViewById(R.id.left_wing_3_field);
        leftMeasureFourField = view.findViewById(R.id.left_wing_4_field);
        leftMeasureFiveField = view.findViewById(R.id.left_wing_5_field);
        leftMeasureSixField = view.findViewById(R.id.left_wing_6_field);
        rightMeasureOneField = view.findViewById(R.id.right_wing_1_field);
        rightMeasureTwoField = view.findViewById(R.id.right_wing_2_field);
        rightMeasureThreeField = view.findViewById(R.id.right_wing_3_field);
        rightMeasureFourField = view.findViewById(R.id.right_wing_4_field);
        rightMeasureFiveField = view.findViewById(R.id.right_wing_5_field);
        rightMeasureSixField = view.findViewById(R.id.right_wing_6_field);
        obsField = view.findViewById(R.id.slope_obs_field);
//        TextInputEditText
        slopeLocaleValue = view.findViewById(R.id.slope_locale_value);
        slopeWidthValue = view.findViewById(R.id.slope_width_value);
        longMeasureOneValue = view.findViewById(R.id.slope_longitudinal_1_value);
        longMeasureTwoValue = view.findViewById(R.id.slope_longitudinal_2_value);
        longMeasureThreeValue = view.findViewById(R.id.slope_longitudinal_3_value);
        longMeasureFourValue = view.findViewById(R.id.slope_longitudinal_4_value);
        longMeasureFiveValue = view.findViewById(R.id.slope_longitudinal_5_value);
        longMeasureSixValue = view.findViewById(R.id.slope_longitudinal_6_value);
        leftMeasureOneValue = view.findViewById(R.id.left_wing_1_value);
        leftMeasureTwoValue = view.findViewById(R.id.left_wing_2_value);
        leftMeasureThreeValue = view.findViewById(R.id.left_wing_3_value);
        leftMeasureFourValue = view.findViewById(R.id.left_wing_4_value);
        leftMeasureFiveValue = view.findViewById(R.id.left_wing_5_value);
        leftMeasureSixValue = view.findViewById(R.id.left_wing_6_value);
        rightMeasureOneValue = view.findViewById(R.id.right_wing_1_value);
        rightMeasureTwoValue = view.findViewById(R.id.right_wing_2_value);
        rightMeasureThreeValue = view.findViewById(R.id.right_wing_3_value);
        rightMeasureFourValue = view.findViewById(R.id.right_wing_4_value);
        rightMeasureFiveValue = view.findViewById(R.id.right_wing_5_value);
        rightMeasureSixValue = view.findViewById(R.id.right_wing_6_value);
        obsValue = view.findViewById(R.id.slope_obs_value);
//        MaterialButtons
        longButton = view.findViewById(R.id.add_longitudinal_measurement);
        leftButton = view.findViewById(R.id.add_left_wing_measurement);
        rightButton = view.findViewById(R.id.add_right_wing_measurement);
        cancelSlope = view.findViewById(R.id.cancel_sidewalk_slope);
        saveSlope = view.findViewById(R.id.save_sidewalk_slope);
//        ImageButton
        deleteLong = view.findViewById(R.id.delete_long_measurement);
        deleteLeft = view.findViewById(R.id.delete_left_measurement);
        deleteRight = view.findViewById(R.id.delete_right_measurement);
//        RadioGroup
        hasTactileFloor = view.findViewById(R.id.slope_has_tactile_floor_radio);
//        TextView
        tactileFloorError = view.findViewById(R.id.slope_has_tactile_floor_error);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());

        addLayoutsToArrays();
    }

    private void addLayoutsToArrays() {
        longFields.add(longMeasureOneField);
        longFields.add(longMeasureTwoField);
        longFields.add(longMeasureThreeField);
        longFields.add(longMeasureFourField);
        longFields.add(longMeasureFiveField);
        longFields.add(longMeasureSixField);

        leftFields.add(leftMeasureOneField);
        leftFields.add(leftMeasureTwoField);
        leftFields.add(leftMeasureThreeField);
        leftFields.add(leftMeasureFourField);
        leftFields.add(leftMeasureFiveField);
        leftFields.add(leftMeasureSixField);

        rightFields.add(rightMeasureOneField);
        rightFields.add(rightMeasureTwoField);
        rightFields.add(rightMeasureThreeField);
        rightFields.add(rightMeasureFourField);
        rightFields.add(rightMeasureFiveField);
        rightFields.add(rightMeasureSixField);
    }

}