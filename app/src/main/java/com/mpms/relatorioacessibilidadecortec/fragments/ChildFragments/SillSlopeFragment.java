package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.data.entities.PlaygroundEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.DoorFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.PlaygroundFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.ArrayList;


public class SillSlopeFragment extends Fragment {

    public static final String SLOPE_QNT = "SLOPE_QNT";
    public static final String SLOPE_ANGLE_1 = "SLOPE_ANGLE_1";
    public static final String SLOPE_ANGLE_2 = "SLOPE_ANGLE_2";
    public static final String SLOPE_ANGLE_3 = "SLOPE_ANGLE_3";
    public static final String SLOPE_ANGLE_4 = "SLOPE_ANGLE_4";
    public static final String SLOPE_WIDTH = "SLOPE_WIDTH";

    TextInputLayout slopeAngleField1, slopeAngleField2, slopeAngleField3, slopeAngleField4, sillSlopeWidthField;
    TextInputEditText slopeAngleValue1, slopeAngleValue2, slopeAngleValue3, slopeAngleValue4, sillSlopeWidthValue;
    MaterialButton addAngle;
    ImageButton delAngle;
    TextView angleError;

    ViewModelEntry modelEntry;

    Bundle sillSlopeBundle = new Bundle();

    ArrayList<String> childData = new ArrayList<>();
    ArrayList<TextInputLayout> slopeAngleArray = new ArrayList<>();

    int measureQnt = 1;

    public SillSlopeFragment() {
        // Required empty public constructor
    }


    public static SillSlopeFragment newInstance() {
        return new SillSlopeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sill_slope, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSillSlopeViews(view);

        addAngle.setOnClickListener(v -> {
            if (measureQnt < 1) {
                measureQnt = 1;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (measureQnt < 4) {
                if (measureQnt == 1)
                    delAngle.setVisibility(View.VISIBLE);
                slopeAngleArray.get(measureQnt).setVisibility(View.VISIBLE);
                measureQnt++;
            } else
                Toast.makeText(getContext(), "O limite de medições foi atingido!", Toast.LENGTH_SHORT).show();
        });

        delAngle.setOnClickListener(v -> {
            if (measureQnt > 1) {
                slopeAngleArray.get(measureQnt - 1).getEditText().setText(null);
                slopeAngleArray.get(measureQnt - 1).setVisibility(View.GONE);
                measureQnt--;
                if (measureQnt == 1)
                    delAngle.setVisibility(View.GONE);
            }
        });

        getParentFragmentManager().setFragmentResultListener(InspectionActivity.LOAD_CHILD_DATA, this, (key, bundle) -> {
            if (bundle.getInt(DoorFragment.DOOR_ID) > 0) {
                modelEntry.getSpecificDoor(bundle.getInt(DoorFragment.DOOR_ID)).observe(getViewLifecycleOwner(), this::loadSlopeDoorData);
            } else if (bundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID) > 0) {
                modelEntry.getOneExternalAccess(bundle.getInt(ExternalAccessFragment.EXT_ACCESS_ID))
                        .observe(getViewLifecycleOwner(), this::gatherSlopeExtAccData);
            } else if (bundle.getInt(PlaygroundFragment.PLAY_ID) > 0) {
                modelEntry.getOnePlayground(bundle.getInt(PlaygroundFragment.PLAY_ID))
                        .observe(getViewLifecycleOwner(), this::gatherSlopePlayData);
            }
        });

        getParentFragmentManager().setFragmentResultListener(InspectionActivity.GATHER_CHILD_DATA, this, (key, bundle) -> {
            checkSlopeNoEmptyFields(bundle);
            getParentFragmentManager().setFragmentResult(InspectionActivity.CHILD_DATA_LISTENER, bundle);
        });
    }

    private void instantiateSillSlopeViews(View view) {
//        TextInputLayout
        slopeAngleField1 = view.findViewById(R.id.slope_measure_1_field);
        slopeAngleField2 = view.findViewById(R.id.slope_measure_2_field);
        slopeAngleField3 = view.findViewById(R.id.slope_measure_3_field);
        slopeAngleField4 = view.findViewById(R.id.slope_measure_4_field);
        sillSlopeWidthField = view.findViewById(R.id.sill_slope_width_field);
//        TextInputEditText
        slopeAngleValue1 = view.findViewById(R.id.slope_measure_1_value);
        slopeAngleValue2 = view.findViewById(R.id.slope_measure_2_value);
        slopeAngleValue3 = view.findViewById(R.id.slope_measure_3_value);
        slopeAngleValue4 = view.findViewById(R.id.slope_measure_4_value);
        sillSlopeWidthValue = view.findViewById(R.id.sill_slope_width_value);
//        MaterialButton
        addAngle = view.findViewById(R.id.add_slope_measure_button);
//        ImageButton
        delAngle = view.findViewById(R.id.delete_slope_measure);
//        TextView
        angleError = view.findViewById(R.id.slope_measure_error);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());

        addFieldsToArray();
    }

    private void addFieldsToArray() {
        slopeAngleArray.add(slopeAngleField1);
        slopeAngleArray.add(slopeAngleField2);
        slopeAngleArray.add(slopeAngleField3);
        slopeAngleArray.add(slopeAngleField4);
    }

    private boolean checkSlopeNoEmptyFields(Bundle bundle) {
        clearEmptyFieldErrors();
        int error = 0;
        if (TextUtils.isEmpty(sillSlopeWidthValue.getText())) {
            sillSlopeWidthField.setError(getString(R.string.blank_field_error));
            error++;
        } else
            bundle.putDouble(SLOPE_WIDTH, Double.parseDouble(String.valueOf(sillSlopeWidthValue.getText())));

        bundle.putInt(SLOPE_QNT, measureQnt);
        switch (measureQnt) {
            case 4:
                if (TextUtils.isEmpty(slopeAngleValue4.getText())) {
                    angleError.setVisibility(View.VISIBLE);
                    error++;
                } else
                    bundle.putDouble(SLOPE_ANGLE_4, Double.parseDouble(String.valueOf(slopeAngleValue4.getText())));
            case 3:
                if (TextUtils.isEmpty(slopeAngleValue3.getText())) {
                    angleError.setVisibility(View.VISIBLE);
                    error++;
                } else
                    bundle.putDouble(SLOPE_ANGLE_3, Double.parseDouble(String.valueOf(slopeAngleValue3.getText())));
            case 2:
                if (TextUtils.isEmpty(slopeAngleValue2.getText())) {
                    angleError.setVisibility(View.VISIBLE);
                    error++;
                } else
                    bundle.putDouble(SLOPE_ANGLE_2, Double.parseDouble(String.valueOf(slopeAngleValue2.getText())));
            case 1:
                if (TextUtils.isEmpty(slopeAngleValue1.getText())) {
                    angleError.setVisibility(View.VISIBLE);
                    error++;
                } else
                    bundle.putDouble(SLOPE_ANGLE_1, Double.parseDouble(String.valueOf(slopeAngleValue1.getText())));
                break;
            default:
                break;
        }
        if (!bundle.getBoolean(InspectionActivity.ADD_ITEM_REQUEST)) {
            bundle.putBoolean(RoomsRegisterFragment.CHILD_DATA_COMPLETE, error == 0);
        }
        return error == 0;
    }

    private void clearEmptyFieldErrors() {
        angleError.setVisibility(View.GONE);
        sillSlopeWidthField.setErrorEnabled(false);
    }

    //TODO - Corrigir o carregamento de dados para pegar todos os 4 campos de inclinação
    private void gatherSlopeExtAccData(ExternalAccess access) {
        if (access.getSillSlopeWidth() != null)
            sillSlopeWidthValue.setText(String.valueOf(access.getSillSlopeWidth()));
        if (access.getSillSlopeAngle() != null)
            slopeAngleValue1.setText(String.valueOf(access.getSillSlopeAngle()));
    }

    private void gatherSlopePlayData(PlaygroundEntry playEntry) {
        if (playEntry.getSlopeSillWidth() != null)
            sillSlopeWidthValue.setText(String.valueOf(playEntry.getSlopeSillWidth()));
        if (playEntry.getSlopeSillAngle() != null)
            slopeAngleValue1.setText(String.valueOf(playEntry.getSlopeSillAngle()));
    }

    private void loadSlopeDoorData(DoorEntry doorEntry) {
        if (doorEntry.getSillSlopeWidth() != null)
            sillSlopeWidthValue.setText(String.valueOf(doorEntry.getSillSlopeWidth()));
        switch (doorEntry.getSillSlopeQnt()) {
            case 4:
                slopeAngleField4.setVisibility(View.VISIBLE);
                if (doorEntry.getSillSlopeAngle4() != null)
                    slopeAngleValue4.setText(String.valueOf(doorEntry.getSillSlopeAngle4()));
            case 3:
                slopeAngleField3.setVisibility(View.VISIBLE);
                if (doorEntry.getSillSlopeAngle3() != null)
                    slopeAngleValue3.setText(String.valueOf(doorEntry.getSillSlopeAngle3()));
            case 2:
                slopeAngleField2.setVisibility(View.VISIBLE);
                if (doorEntry.getSillSlopeAngle2() != null)
                    slopeAngleValue2.setText(String.valueOf(doorEntry.getSillSlopeAngle2()));
            case 1:
                slopeAngleField1.setVisibility(View.VISIBLE);
                if (doorEntry.getSillSlopeAngle1() != null)
                    slopeAngleValue1.setText(String.valueOf(doorEntry.getSillSlopeAngle1()));
                break;
            default:
                break;
        }




    }
}