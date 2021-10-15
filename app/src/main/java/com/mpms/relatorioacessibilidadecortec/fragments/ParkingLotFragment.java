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
import com.mpms.relatorioacessibilidadecortec.activities.SchoolRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class ParkingLotFragment extends Fragment {

    public static int schoolID, parkingLotID;
    RadioGroup parkingLotType;
    TextInputLayout totalVacancyField, parkingLotFloorTypeField;
    TextInputEditText totalVacancyValue, parkingLotFloorTypeValue;
    Button cancelParkingLotRegister, saveParkingLotRegister;
//    ViewModelFragments modelFragments;
    TextView parkingLotTypeError;
    Bundle registerID = new Bundle();

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
        Bundle schoolBundle = this.getArguments();
        if (schoolBundle != null)
            schoolID = schoolBundle.getInt(SchoolRegisterActivity.SCHOOL_ID);
//        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
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

        ViewModelEntry recentEntry = new ViewModelEntry(requireActivity().getApplication());

        cancelParkingLotRegister = view.findViewById(R.id.cancel_parking_lot);
        saveParkingLotRegister = view.findViewById(R.id.save_parking_lot);

        parkingLotType = view.findViewById(R.id.parking_lot_type_radio);

        parkingLotTypeError = view.findViewById(R.id.parking_lot_type_error);

        totalVacancyField = view.findViewById(R.id.parking_lot_vacancy_field);
        parkingLotFloorTypeField = view.findViewById(R.id.parking_lot_floor_field);

        totalVacancyValue = view.findViewById(R.id.parking_lot_vacancy_value);
        parkingLotFloorTypeValue = view.findViewById(R.id.parking_lot_floor_value);

        recentEntry.getAllParkingLots(schoolID).observe(getViewLifecycleOwner(), parkingLotEntries -> {
            if (saveAttempt == 1) {
                int size = parkingLotEntries.size();
                ParkingLotEntry lastEntry = parkingLotEntries.get(size - 1);
                parkingLotID = lastEntry.getParkingLotID();
                registerID.putInt("SCHOOL_ID", schoolID);
                registerID.putInt("PARKING_LOT_ID", parkingLotID);
                saveAttempt = 0;
                clearFields();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ParkingLotPdmrFragment parkingLotPdmrFragment = ParkingLotPdmrFragment.newInstance();
                parkingLotPdmrFragment.setArguments(registerID);
                fragmentTransaction.replace(R.id.show_fragment_selected, parkingLotPdmrFragment).addToBackStack(null).commit();
            }

        });

        cancelParkingLotRegister.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .beginTransaction().remove(this).commit());

        saveParkingLotRegister.setOnClickListener(v -> {
            if (checkEmptyFields()) {
                ParkingLotEntry newEntry = newParkingLotEntry();
                ViewModelEntry.insertParkingLot(newEntry);
                saveAttempt = 1;
            }

        });


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
        return new ParkingLotEntry(schoolID,
                getCheckedRadio(parkingLotType),
                Integer.parseInt(Objects.requireNonNull(totalVacancyValue.getText()).toString()),
                Objects.requireNonNull(parkingLotFloorTypeValue.getText()).toString());
    }

}