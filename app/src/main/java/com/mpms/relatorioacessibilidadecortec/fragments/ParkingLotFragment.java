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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.BlockRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

public class ParkingLotFragment extends Fragment {

    public static final String HAS_PCD = "HAS_PCD";
    public static final String HAS_ELDERLY = "HAS_ELDERLY";
    public static final String PCD_LIST = "PCD_LIST";
    public static final String ELDER_LIST = "ELDER_LIST";

    RadioGroup parkingLotType, hasPcdVacancy, hasElderlyVacancy;
    TextInputLayout parkingLotFloorTypeField;
    TextInputEditText parkingLotFloorTypeValue;
    Button cancelParkingLotRegister, saveParkingLotRegister;
    TextView parkingLotTypeError, pcdVacancyError, elderlyVacancyError;
    Bundle parkingBundle = new Bundle();

    ViewModelEntry modelEntry;

    int pcdVacancy = 0, elderVacancy = 0;
    boolean saveAttempt = false;

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
            parkingBundle.putInt(BlockRegisterActivity.BLOCK_ID, this.getArguments().getInt(BlockRegisterActivity.BLOCK_ID));
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
            if (saveAttempt) {
                parkingBundle.putInt(ParkingLotListFragment.PARKING_ID, lastLot.getParkingLotID());
                saveAttempt = false;
                clearFields();
                openParkingLotTypeFragment();
            }
        });

        cancelParkingLotRegister.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .popBackStack(InspectionActivity.PARKING_LIST, 0));

        saveParkingLotRegister.setOnClickListener(v -> saveClick());
    }

    private void instantiateParkingViews(View view) {
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Buttons
        cancelParkingLotRegister = view.findViewById(R.id.cancel_parking_lot);
        saveParkingLotRegister = view.findViewById(R.id.save_parking_lot);
//        RadioGroup
        parkingLotType = view.findViewById(R.id.parking_lot_type_radio);
        hasPcdVacancy = view.findViewById(R.id.parking_lot_PCD_vacancy_radio);
        hasElderlyVacancy = view.findViewById(R.id.parking_lot_elderly_vacancy_radio);
//        TextView
        parkingLotTypeError = view.findViewById(R.id.parking_lot_type_error);
        pcdVacancyError = view.findViewById(R.id.PCD_vacancy_error);
        elderlyVacancyError = view.findViewById(R.id.elderly_vacancy_error);
//        TextInputLayout
        parkingLotFloorTypeField = view.findViewById(R.id.parking_lot_floor_field);
//        TextInputEditText
        parkingLotFloorTypeValue = view.findViewById(R.id.parking_lot_floor_value);
    }

    private void gatherParkingLotData(ParkingLotEntry parkingEntry) {
        parkingLotType.check(parkingLotType.getChildAt(parkingEntry.getTypeParkingLot()).getId());
        parkingLotFloorTypeValue.setText(parkingEntry.getParkingLotFloorType());
        hasPcdVacancy.check(hasPcdVacancy.getChildAt(parkingEntry.getHasPCDVacancy()).getId());
        hasElderlyVacancy.check(hasElderlyVacancy.getChildAt(parkingEntry.getHasElderVacancy()).getId());
    }

    private void openParkingLotTypeFragment() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (parkingBundle.getBoolean(HAS_PCD)) {
            ParkLotPcdListFragment pcdListFragment = ParkLotPcdListFragment.newInstance();
            pcdListFragment.setArguments(parkingBundle);
            fragmentTransaction.replace(R.id.show_fragment_selected, pcdListFragment).addToBackStack(PCD_LIST).commit();
        } else if (parkingBundle.getBoolean(HAS_ELDERLY)) {
            ParkLotElderListFragment elderListFragment = ParkLotElderListFragment.newInstance();
            elderListFragment.setArguments(parkingBundle);
            fragmentTransaction.replace(R.id.show_fragment_selected, elderListFragment).addToBackStack(ELDER_LIST).commit();
        }
    }

    private boolean getBooleanFromRadio(int i) {
        return i == 1;
    }

    private void saveClick() {
        if (checkEmptyFields()) {
            ParkingLotEntry newEntry = newParkingLotEntry(parkingBundle);
            if (parkingBundle.getInt(ParkingLotListFragment.PARKING_ID) > 0) {
                if (pcdVacancy < 0 || elderVacancy < 0)
                    Toast.makeText(getContext(), "Algo deu errado. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
                else {
                    if (pcdVacancy != 0 || elderVacancy != 0) {
                        parkingBundle.putBoolean(HAS_PCD, getBooleanFromRadio(getCheckedRadio(hasPcdVacancy)));
                        parkingBundle.putBoolean(HAS_ELDERLY, getBooleanFromRadio(getCheckedRadio(hasElderlyVacancy)));
                    }
                    newEntry.setParkingLotID(parkingBundle.getInt(ParkingLotListFragment.PARKING_ID));
                    ViewModelEntry.updateParkingLot(newEntry);
                    clearFields();
                    openParkingLotTypeFragment();
                }
            } else if (parkingBundle.getInt(ParkingLotListFragment.PARKING_ID) == 0) {
                if (pcdVacancy < 0 || elderVacancy < 0)
                    Toast.makeText(getContext(), "Algo deu errado. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
                else {
                    if (pcdVacancy != 0 || elderVacancy != 0) {
                        parkingBundle.putBoolean(HAS_PCD, getBooleanFromRadio(getCheckedRadio(hasPcdVacancy)));
                        parkingBundle.putBoolean(HAS_ELDERLY, getBooleanFromRadio(getCheckedRadio(hasElderlyVacancy)));
                        saveAttempt = true;
                    } else
                        Toast.makeText(getContext(), "Cadastro efetuado com sucesso", Toast.LENGTH_SHORT).show();
                    ViewModelEntry.insertParkingLot(newEntry);
                    clearFields();
                }
            } else {
                Toast.makeText(getContext(), "Algo deu errado. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
                requireActivity().getSupportFragmentManager().popBackStack(InspectionActivity.PARKING_LIST, 0);
            }
        }
    }

    public boolean checkEmptyFields() {
        clearErrorsParkingLot();
        int error = 0;
        if (parkingLotType.getCheckedRadioButtonId() == -1) {
            parkingLotTypeError.setVisibility(View.VISIBLE);
            error++;
        }
        if (TextUtils.isEmpty(parkingLotFloorTypeValue.getText())) {
            parkingLotFloorTypeField.setError(getString(R.string.blank_field_error));
            error++;
        }
        if (hasPcdVacancy.getCheckedRadioButtonId() == -1) {
            pcdVacancyError.setVisibility(View.VISIBLE);
            error++;
        }
        if (hasElderlyVacancy.getCheckedRadioButtonId() == -1) {
            elderlyVacancyError.setVisibility(View.VISIBLE);
            error++;
        }
        return error == 0;
    }

    public void clearErrorsParkingLot() {
        parkingLotTypeError.setVisibility(View.GONE);
        parkingLotFloorTypeField.setErrorEnabled(false);
        pcdVacancyError.setVisibility(View.GONE);
        elderlyVacancyError.setVisibility(View.GONE);

    }

    public int getCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public void clearFields() {
        parkingLotType.clearCheck();
        parkingLotFloorTypeValue.setText(null);
        hasPcdVacancy.clearCheck();
        hasElderlyVacancy.clearCheck();
    }

    public ParkingLotEntry newParkingLotEntry(Bundle bundle) {
        String floorType = String.valueOf(parkingLotFloorTypeValue.getText());
        pcdVacancy = getCheckedRadio(hasPcdVacancy);
        elderVacancy = getCheckedRadio(hasElderlyVacancy);
        return new ParkingLotEntry(bundle.getInt(BlockRegisterActivity.BLOCK_ID), getCheckedRadio(parkingLotType),
                floorType, pcdVacancy, elderVacancy);
    }

}