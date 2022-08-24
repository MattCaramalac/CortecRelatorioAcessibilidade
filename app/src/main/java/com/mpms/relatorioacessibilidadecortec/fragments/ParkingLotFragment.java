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
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.ParkLotElderListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.ParkLotPcdListFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

public class ParkingLotFragment extends Fragment implements TagInterface {

    RadioGroup accessFloorRadio, hasPcdVacancy, hasElderlyVacancy, parkHasStairs, parkHasRamps, parkHasAccessRoute;
    TextInputLayout parkAccessFloorObsField, parkAccessRouteObsField;
    TextInputEditText parkAccessFloorObsValue, parkAccessRouteObsValue;
    Button addParkStairs, addParkRamps, cancelParkingLotRegister, saveParkingLotRegister;
    TextView parkingAccessError, pcdVacancyError, elderlyVacancyError, parkHasStairsError, parkHasRampsError, parkAccessRouteError;
    Bundle parkingBundle;

    ViewModelEntry modelEntry;

    int pcdVacancy = 0, elderVacancy = 0;
    boolean saveAttempt = false, rampStairsReg = false;

    public ParkingLotFragment() {
        // Required empty public constructor
    }

    public static ParkingLotFragment newInstance() {
        return new ParkingLotFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            parkingBundle = new Bundle(this.getArguments());
        else
            parkingBundle = new Bundle();

        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (parkingBundle.getBoolean(RECENT_ENTRY))
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
        return inflater.inflate(R.layout.fragment_parking_lot, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateParkingViews(view);

        if (parkingBundle.getInt(PARKING_ID) > 0) {
            modelEntry.getOneParkingLot(parkingBundle.getInt(PARKING_ID))
                    .observe(getViewLifecycleOwner(), this::loadParkingLotData);
        }

        modelEntry.getLastInsertedParkingLot().observe(getViewLifecycleOwner(), lastLot -> {
            if (parkingBundle.getBoolean(RECENT_ENTRY)) {
                parkingBundle.putInt(PARKING_ID, lastLot.getParkingID());
                if (saveAttempt) {
                    saveAttempt = false;
                    clearFields();
                    openParkingLotTypeFragment();
                } else if (rampStairsReg) {
                    rampStairsReg = false;
                    clearFields();
                    openRampStairsListFragment();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        parkingBundle.putBoolean(FROM_PARKING, false);
        saveAttempt = false;
        rampStairsReg = false;
    }

    private void cancelClick() {
        if (parkingBundle.getBoolean(RECENT_ENTRY)) {
            CancelEntryDialog dialog = CancelEntryDialog.newInstance();
            dialog.setListener(() -> {
                ViewModelEntry.deleteOneParkingLot(parkingBundle.getInt(PARKING_ID));
                parkingBundle = null;
                requireActivity().getSupportFragmentManager().popBackStack(PARKING_LIST, 0);
            });
            FragmentManager manager = requireActivity().getSupportFragmentManager();
            dialog.show(manager, "MOSTRA");
        } else
            requireActivity().getSupportFragmentManager().popBackStack(PARKING_LIST, 0);
    }

    private void instantiateParkingViews(View view) {
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Buttons
        addParkStairs = view.findViewById(R.id.add_parking_stairs_button);
        addParkRamps = view.findViewById(R.id.add_parking_ramps_button);
        cancelParkingLotRegister = view.findViewById(R.id.cancel_parking_lot);
        saveParkingLotRegister = view.findViewById(R.id.save_parking_lot);
//        RadioGroup
        parkHasStairs = view.findViewById(R.id.parking_has_stairs_radio);
        parkHasRamps = view.findViewById(R.id.parking_has_ramps_radio);
        accessFloorRadio = view.findViewById(R.id.parking_lot_access_floor_radio);
        hasPcdVacancy = view.findViewById(R.id.parking_lot_PCD_vacancy_radio);
        hasElderlyVacancy = view.findViewById(R.id.parking_lot_elderly_vacancy_radio);
        parkHasAccessRoute = view.findViewById(R.id.parking_lot_access_route_radio);
//        TextView
        parkingAccessError = view.findViewById(R.id.parking_lot_access_floor_error);
        pcdVacancyError = view.findViewById(R.id.PCD_vacancy_error);
        elderlyVacancyError = view.findViewById(R.id.elderly_vacancy_error);
        parkHasStairsError = view.findViewById(R.id.parking_has_stairs_error);
        parkHasRampsError = view.findViewById(R.id.parking_has_ramps_error);
        parkAccessRouteError = view.findViewById(R.id.parking_lot_access_route_error);
//        TextInputLayout
        parkAccessFloorObsField = view.findViewById(R.id.parking_lot_floor_field);
        parkAccessRouteObsField = view.findViewById(R.id.parking_lot_route_field);
//        TextInputEditText
        parkAccessFloorObsValue = view.findViewById(R.id.parking_lot_floor_value);
        parkAccessRouteObsValue = view.findViewById(R.id.parking_lot_route_value);
//
        parkHasStairs.setOnCheckedChangeListener(this::parkRadioListener);
        parkHasRamps.setOnCheckedChangeListener(this::parkRadioListener);
        accessFloorRadio.setOnCheckedChangeListener(this::parkRadioListener);
        parkHasAccessRoute.setOnCheckedChangeListener(this::parkRadioListener);
        saveParkingLotRegister.setOnClickListener(this::buttonClick);
        addParkStairs.setOnClickListener(this::buttonClick);
        addParkRamps.setOnClickListener(this::buttonClick);
        cancelParkingLotRegister.setOnClickListener(v -> cancelClick());
    }

    private void loadParkingLotData(ParkingLotEntry parkingEntry) {
        if (parkingEntry.getHasPCDVacancy() != null)
            hasPcdVacancy.check(hasPcdVacancy.getChildAt(parkingEntry.getHasPCDVacancy()).getId());
        if (parkingEntry.getHasElderVacancy() != null)
            hasElderlyVacancy.check(hasElderlyVacancy.getChildAt(parkingEntry.getHasElderVacancy()).getId());
        if (parkingEntry.getParkingHasStairs() != null)
            parkHasStairs.check(parkHasStairs.getChildAt(parkingEntry.getParkingHasStairs()).getId());
        if (parkingEntry.getParkingHasRamps() != null)
            parkHasRamps.check(parkHasRamps.getChildAt(parkingEntry.getParkingHasRamps()).getId());
        if (parkingEntry.getParkAccessFloor() != null)
            accessFloorRadio.check(accessFloorRadio.getChildAt(parkingEntry.getParkAccessFloor()).getId());
        if (parkingEntry.getParkFloorObs() != null)
            parkAccessFloorObsValue.setText(parkingEntry.getParkFloorObs());
        if (parkingEntry.getParkAccessRoute() != null)
            parkHasAccessRoute.check(parkHasAccessRoute.getChildAt(parkingEntry.getParkAccessRoute()).getId());
        if (parkingEntry.getParkAccessRouteObs() != null)
            parkAccessRouteObsValue.setText(parkingEntry.getParkAccessRouteObs());
    }

    private void openParkingLotTypeFragment() {
        Fragment fragment;
        if (parkingBundle.getBoolean(HAS_PCD)) {
            fragment = ParkLotPcdListFragment.newInstance();
            fragment.setArguments(parkingBundle);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.show_fragment_selected, fragment).addToBackStack(PCD_LIST).commit();
        } else if (parkingBundle.getBoolean(HAS_ELDERLY)) {
            fragment = ParkLotElderListFragment.newInstance();
            fragment.setArguments(parkingBundle);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.show_fragment_selected, fragment).addToBackStack(ELDER_LIST).commit();
        } else {
            if (parkingBundle.getInt(PARKING_ID) > 0)
                Toast.makeText(getContext(), R.string.register_updated_message, Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getContext(), R.string.register_created_message, Toast.LENGTH_SHORT).show();
            requireActivity().getSupportFragmentManager().popBackStack(PARKING_LIST, 0);
        }
    }

    private void openRampStairsListFragment() {
        RampStairsListFragment listFragment = RampStairsListFragment.newInstance();
        listFragment.setArguments(parkingBundle);
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.show_fragment_selected, listFragment).addToBackStack(null).commit();
    }

    private boolean getBooleanFromRadio(int i) {
        return i == 1;
    }

    private void buttonClick(View view) {
        if (view != saveParkingLotRegister) {
            parkingBundle.putInt(AMBIENT_TYPE, 3);
            parkingBundle.putBoolean(FROM_PARKING, true);
            rampStairsReg = true;
            if (view == addParkStairs)
                parkingBundle.putInt(RAMP_OR_STAIRS, 1);
            else if (view == addParkRamps)
                parkingBundle.putInt(RAMP_OR_STAIRS, 2);

            if (parkingBundle.getInt(PARKING_ID) == 0) {
                ParkingLotEntry newEntry = newParkingLotEntry(parkingBundle);
                ViewModelEntry.insertParkingLot(newEntry);
                parkingBundle.putBoolean(RECENT_ENTRY, true);
            }
            openRampStairsListFragment();
        } else {
            if (checkEmptyFields()) {
                ParkingLotEntry newEntry = newParkingLotEntry(parkingBundle);
                if (parkingBundle.getInt(PARKING_ID) > 0) {
                    if (pcdVacancy < 0 || elderVacancy < 0)
                        Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                    else {
                        if (pcdVacancy != 0 || elderVacancy != 0) {
                            parkingBundle.putBoolean(HAS_PCD, getBooleanFromRadio(getCheckedRadio(hasPcdVacancy)));
                            parkingBundle.putBoolean(HAS_ELDERLY, getBooleanFromRadio(getCheckedRadio(hasElderlyVacancy)));
                        }
                        newEntry.setParkingID(parkingBundle.getInt(PARKING_ID));
                        ViewModelEntry.updateParkingLot(newEntry);
                        clearFields();
                        openParkingLotTypeFragment();
                    }
                } else if (parkingBundle.getInt(PARKING_ID) == 0) {
                    if (pcdVacancy < 0 || elderVacancy < 0)
                        Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                    else {
                        if (pcdVacancy != 0 || elderVacancy != 0) {
                            parkingBundle.putBoolean(HAS_PCD, getBooleanFromRadio(getCheckedRadio(hasPcdVacancy)));
                            parkingBundle.putBoolean(HAS_ELDERLY, getBooleanFromRadio(getCheckedRadio(hasElderlyVacancy)));
                            saveAttempt = true;
                        } else
                            Toast.makeText(getContext(), "Cadastro efetuado com sucesso", Toast.LENGTH_SHORT).show();
                        ViewModelEntry.insertParkingLot(newEntry);
                        parkingBundle.putBoolean(RECENT_ENTRY, true);
                        clearFields();
                    }
                } else {
                    Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStack(PARKING_LIST, 0);
                }
            }
        }
    }

    private void parkRadioListener(RadioGroup radio, int check) {
        int index = getCheckedRadio(radio);

        if (radio == parkHasStairs) {
            if (index == 1)
                addParkStairs.setVisibility(View.VISIBLE);
            else
                addParkStairs.setVisibility(View.GONE);
        } else if (radio == parkHasRamps) {
            if (index == 1)
                addParkRamps.setVisibility(View.VISIBLE);
            else
                addParkRamps.setVisibility(View.GONE);
        } else if (radio == accessFloorRadio) {
            if (index == 0)
                parkAccessFloorObsField.setVisibility(View.VISIBLE);
            else {
                parkAccessFloorObsValue.setText(null);
                parkAccessFloorObsField.setVisibility(View.GONE);
            }
        } else if (radio == parkHasAccessRoute) {
            if (index == 0)
                parkAccessRouteObsField.setVisibility(View.VISIBLE);
            else {
                parkAccessRouteObsValue.setText(null);
                parkAccessRouteObsField.setVisibility(View.GONE);
            }
        }
    }

    public boolean checkEmptyFields() {
        clearErrorsParkingLot();
        int error = 0;
        if (hasPcdVacancy.getCheckedRadioButtonId() == -1) {
            pcdVacancyError.setVisibility(View.VISIBLE);
            error++;
        } else
            pcdVacancy = getCheckedRadio(hasPcdVacancy);

        if (hasElderlyVacancy.getCheckedRadioButtonId() == -1) {
            elderlyVacancyError.setVisibility(View.VISIBLE);
            error++;
        } else
            elderVacancy = getCheckedRadio(hasElderlyVacancy);

        if (parkHasStairs.getCheckedRadioButtonId() == -1) {
            parkHasStairsError.setVisibility(View.VISIBLE);
            error++;
        }
        if (parkHasRamps.getCheckedRadioButtonId() == -1) {
            parkHasRampsError.setVisibility(View.VISIBLE);
            error++;
        }
        if (accessFloorRadio.getCheckedRadioButtonId() == -1) {
            parkingAccessError.setVisibility(View.VISIBLE);
            error++;
        } else if (getCheckedRadio(accessFloorRadio) == 0) {
            if (TextUtils.isEmpty(parkAccessFloorObsValue.getText())) {
                parkAccessFloorObsField.setError(getString(R.string.unexpected_error));
                error++;
            }
        }
        if (parkHasAccessRoute.getCheckedRadioButtonId() == -1) {
            parkAccessRouteError.setVisibility(View.VISIBLE);
            error++;
        } else if (getCheckedRadio(parkHasAccessRoute) == 0) {
            if (TextUtils.isEmpty(parkAccessRouteObsValue.getText())) {
                parkAccessRouteObsField.setError(getString(R.string.unexpected_error));
                error++;
            }
        }
        return error == 0;
    }

    public void clearErrorsParkingLot() {
        parkHasStairsError.setVisibility(View.GONE);
        parkHasRampsError.setVisibility(View.GONE);
        pcdVacancyError.setVisibility(View.GONE);
        elderlyVacancyError.setVisibility(View.GONE);
        parkingAccessError.setVisibility(View.GONE);
        parkAccessRouteError.setVisibility(View.GONE);
        parkAccessFloorObsField.setErrorEnabled(false);
        parkAccessRouteObsField.setErrorEnabled(false);
    }

    private int getCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public void clearFields() {
        accessFloorRadio.clearCheck();
        parkHasAccessRoute.clearCheck();
        parkHasRamps.clearCheck();
        parkHasStairs.clearCheck();
        parkAccessFloorObsValue.setText(null);
        hasPcdVacancy.clearCheck();
        hasElderlyVacancy.clearCheck();
    }

    public ParkingLotEntry newParkingLotEntry(Bundle bundle) {
        int typeParking;
        if (bundle.getBoolean(EXT_AREA_REG))
            typeParking = 1;
        else if (bundle.getBoolean(SUP_AREA_REG))
            typeParking = 2;
        else
            typeParking = 3;
        String accessFloorObs = null, accessRouteObs = null;
        Integer sideID = null;

        if (bundle.getInt(AMBIENT_ID) > 0)
            sideID = bundle.getInt(AMBIENT_ID);
        if (!TextUtils.isEmpty(parkAccessFloorObsValue.getText()))
            accessFloorObs = String.valueOf(parkAccessFloorObsValue.getText());
        if (!TextUtils.isEmpty(parkAccessRouteObsValue.getText()))
            accessRouteObs = String.valueOf(parkAccessRouteObsValue.getText());

        return new ParkingLotEntry(bundle.getInt(BLOCK_ID), sideID, typeParking, getCheckedRadio(accessFloorRadio),
                accessFloorObs, getCheckedRadio(hasPcdVacancy), getCheckedRadio(hasElderlyVacancy), getCheckedRadio(parkHasAccessRoute),
                accessRouteObs, getCheckedRadio(parkHasStairs), getCheckedRadio(parkHasRamps));
    }

}