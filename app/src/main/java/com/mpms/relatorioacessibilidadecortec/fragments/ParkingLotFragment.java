package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.activities.SchoolRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

public class ParkingLotFragment extends Fragment {

    RadioGroup parkingLotType;
    TextInputLayout totalVacancyField, parkingLotFloorTypeField;
    TextInputEditText totalVacancyValue, parkingLotFloorTypeValue;
    Button cancelParkingLotRegister, saveParkingLotRegister;
    TextView parkingLotTypeError;
    Bundle parkingBundle = new Bundle();

    ViewModelEntry modelEntry;

    int saveAttempt = 0;

    public ParkingLotFragment() {
        // Required empty public constructor
    }

    public static ParkingLotFragment newInstance() {
        return new ParkingLotFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            parkingBundle.putInt(SchoolRegisterActivity.SCHOOL_ID, this.getArguments().getInt(SchoolRegisterActivity.SCHOOL_ID));
            parkingBundle.putInt(ParkingLotListFragment.PARKING_ID, this.getArguments().getInt(ParkingLotListFragment.PARKING_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parking_lot, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateParkingViews(view);

        if (parkingBundle.getInt(ParkingLotListFragment.PARKING_ID) > 0) {
            modelEntry.getOneParkingLot(parkingBundle.getInt(ParkingLotListFragment.PARKING_ID))
                    .observe(getViewLifecycleOwner(), this::gatherParkingLotData);
        }

        modelEntry.getLastInsertedParkingLot().observe(getViewLifecycleOwner(), lastLot -> {
            if (saveAttempt == 1) {
                parkingBundle.putInt(ParkingLotListFragment.PARKING_ID, lastLot.getParkingLotID());
                saveAttempt = 0;
                clearFields();
                openPdmrFragment();
            }
        });

        cancelParkingLotRegister.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .popBackStack(InspectionActivity.PARKING_LIST, 0));

        saveParkingLotRegister.setOnClickListener(v -> {
            if (checkEmptyFields()) {
                ParkingLotEntry newEntry = newParkingLotEntry();
                if (parkingBundle.getInt(ParkingLotListFragment.PARKING_ID) > 0) {
                    newEntry.setParkingLotID(parkingBundle.getInt(ParkingLotListFragment.PARKING_ID));
                    ViewModelEntry.updateParkingLot(newEntry);
                    openPdmrFragment();
                } else {
                    ViewModelEntry.insertParkingLot(newEntry);
                    saveAttempt = 1;
                }
            }
        });
    }

    private void instantiateParkingViews(View view) {
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Buttons
        cancelParkingLotRegister = view.findViewById(R.id.cancel_parking_lot);
        saveParkingLotRegister = view.findViewById(R.id.save_parking_lot);
//        RadioGroup
        parkingLotType = view.findViewById(R.id.parking_lot_type_radio);
//        TextView
        parkingLotTypeError = view.findViewById(R.id.parking_lot_type_error);
//        TextInputLayout
        totalVacancyField = view.findViewById(R.id.parking_lot_vacancy_field);
        parkingLotFloorTypeField = view.findViewById(R.id.parking_lot_floor_field);
//        TextInputEditText
        totalVacancyValue = view.findViewById(R.id.parking_lot_vacancy_value);
        parkingLotFloorTypeValue = view.findViewById(R.id.parking_lot_floor_value);
    }

    private void gatherParkingLotData(ParkingLotEntry parkingEntry) {
        parkingLotType.check(parkingLotType.getChildAt(parkingEntry.getTypeParkingLot()).getId());
        totalVacancyValue.setText(String.valueOf(parkingEntry.getTotalParkingVacancy()));
        parkingLotFloorTypeValue.setText(parkingEntry.getParkingLotFloorType());
    }

    private void openPdmrFragment() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ParkingLotPdmrFragment parkingLotPdmrFragment = ParkingLotPdmrFragment.newInstance();
        parkingLotPdmrFragment.setArguments(parkingBundle);
        fragmentTransaction.replace(R.id.show_fragment_selected, parkingLotPdmrFragment).addToBackStack(null).commit();
    }

    public boolean checkEmptyFields() {
        clearErrorsParkingLot();
        int error = 0;
        if (parkingLotType.getCheckedRadioButtonId() == -1) {
            parkingLotTypeError.setVisibility(View.VISIBLE);
            error++;
        }
        if (TextUtils.isEmpty(totalVacancyValue.getText())) {
            totalVacancyField.setError(getString(R.string.blank_field_error));
            error++;
        }
        if (TextUtils.isEmpty(parkingLotFloorTypeValue.getText())) {
            parkingLotFloorTypeField.setError(getString(R.string.blank_field_error));
            error++;
        }
        return error == 0;
    }

    public void clearErrorsParkingLot() {
        parkingLotTypeError.setVisibility(View.GONE);
        totalVacancyField.setErrorEnabled(false);
        parkingLotFloorTypeField.setErrorEnabled(false);

    }

    public int getCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public void clearFields() {
        parkingLotType.clearCheck();
        totalVacancyValue.setText(null);
        parkingLotFloorTypeValue.setText(null);
    }

    public ParkingLotEntry newParkingLotEntry() {
        return new ParkingLotEntry(parkingBundle.getInt(SchoolRegisterActivity.SCHOOL_ID),
                getCheckedRadio(parkingLotType),
                Integer.valueOf(String.valueOf(totalVacancyValue.getText())),
                String.valueOf(parkingLotFloorTypeValue.getText()));
    }

}