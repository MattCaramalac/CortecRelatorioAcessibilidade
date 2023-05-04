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
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.CancelEntryDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.ParkLotElderListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.ParkLotPcdListFragment;
import com.mpms.relatorioacessibilidadecortec.model.InspectionViewModel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.ArrayList;

//TODO - Corrigir cadastro de estacionamento

public class ParkingLotFragment extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

    RadioGroup accessFloorRadio, hasPcdVacancy, hasElderlyVacancy, parkHasStairs, parkHasRamps, parkHasAccessRoute;
    TextInputLayout extParkLocalField, parkAccessFloorObsField, parkAccessRouteObsField, parkObsField, photoField;
    TextInputEditText extParkLocalValue, parkAccessFloorObsValue, parkAccessRouteObsValue, parkObsValue, photoValue;
    Button addParkStairs, addParkRamps, cancelParkingLotRegister, saveParkingLotRegister;
    TextView parkingAccessError, pcdVacancyError, elderlyVacancyError, parkHasStairsError, parkHasRampsError, parkAccessRouteError;
    Bundle parkingBundle;

    ArrayList<TextInputEditText> eText = new ArrayList<>();

    ViewModelEntry modelEntry;
    InspectionViewModel dataView;

    int pcdVacancy = 0, elderVacancy = 0, recentPark = 0;
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

        dataView.setVisible(false);

        if (parkingBundle.getInt(PARKING_ID) > 0)
            modelEntry.getOneParkingLot(parkingBundle.getInt(PARKING_ID)).observe(getViewLifecycleOwner(), this::loadParkingLotData);


        modelEntry.getLastInsertedParkingLot().observe(getViewLifecycleOwner(), lastLot -> {
            if (recentPark == 1 && saveAttempt) {
                recentPark = 0;
                parkingBundle.putInt(PARKING_ID, lastLot.getParkingID());
                saveAttempt = false;
                openParkingLotTypeFragment();
            } else if (recentPark == 1 && rampStairsReg) {
                recentPark = 0;
                parkingBundle.putInt(PARKING_ID, lastLot.getParkingID());
                rampStairsReg = false;
                openRampStairsListFragment();
            }
        });

        if (parkingBundle.getInt(PARKING_ID) > 0 && modelEntry.getLastInsertedParkingLot().hasActiveObservers())
            modelEntry.getLastInsertedParkingLot().removeObservers(getViewLifecycleOwner());

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
        dataView = new ViewModelProvider(requireActivity()).get(InspectionViewModel.class);
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
        photoField = view.findViewById(R.id.park_lot_photo_field);
//        TextView
        parkingAccessError = view.findViewById(R.id.parking_lot_access_floor_error);
        pcdVacancyError = view.findViewById(R.id.PCD_vacancy_error);
        elderlyVacancyError = view.findViewById(R.id.elderly_vacancy_error);
        parkHasStairsError = view.findViewById(R.id.parking_has_stairs_error);
        parkHasRampsError = view.findViewById(R.id.parking_has_ramps_error);
        parkAccessRouteError = view.findViewById(R.id.parking_lot_access_route_error);
        photoValue = view.findViewById(R.id.park_lot_photo_value);
//        TextInputLayout
        extParkLocalField = view.findViewById(R.id.ext_park_location_field);
        parkAccessFloorObsField = view.findViewById(R.id.parking_lot_floor_field);
        parkAccessRouteObsField = view.findViewById(R.id.parking_lot_route_field);
        parkObsField = view.findViewById(R.id.parking_lot_obs_field);
//        TextInputEditText
        extParkLocalValue = view.findViewById(R.id.ext_park_location_value);
        parkAccessFloorObsValue = view.findViewById(R.id.parking_lot_floor_value);
        parkAccessRouteObsValue = view.findViewById(R.id.parking_lot_route_value);
        parkObsValue = view.findViewById(R.id.parking_lot_obs_value);
//
        if (parkingBundle.getBoolean(EXT_AREA_REG))
            extParkLocalField.setVisibility(View.VISIBLE);
        parkHasStairs.setOnCheckedChangeListener(this::radioListener);
        parkHasRamps.setOnCheckedChangeListener(this::radioListener);
        accessFloorRadio.setOnCheckedChangeListener(this::radioListener);
        parkHasAccessRoute.setOnCheckedChangeListener(this::radioListener);
        saveParkingLotRegister.setOnClickListener(this::buttonClick);
        addParkStairs.setOnClickListener(this::buttonClick);
        addParkRamps.setOnClickListener(this::buttonClick);
        cancelParkingLotRegister.setOnClickListener(v -> cancelClick());

        eText.add(parkAccessFloorObsValue);
        eText.add(parkAccessRouteObsValue);
        eText.add(parkAccessFloorObsValue);
        allowObsScroll(eText);
    }

    private void loadParkingLotData(ParkingLotEntry parkingEntry) {
        if (parkingEntry.getExtParkLocation() != null)
            extParkLocalValue.setText(parkingEntry.getExtParkLocation());
        if (parkingEntry.getHasPCDVacancy() != null && parkingEntry.getHasPCDVacancy() > -1)
            checkRadioGroup(hasPcdVacancy, parkingEntry.getHasPCDVacancy());
        if (parkingEntry.getHasElderVacancy() != null && parkingEntry.getHasElderVacancy() > -1)
            checkRadioGroup(hasElderlyVacancy, parkingEntry.getHasElderVacancy());
        if (parkingEntry.getParkingHasStairs() != null && parkingEntry.getParkingHasStairs() > -1)
            checkRadioGroup(parkHasStairs, parkingEntry.getParkingHasStairs());
        if (parkingEntry.getParkingHasRamps() != null && parkingEntry.getParkingHasRamps() > -1)
            checkRadioGroup(parkHasRamps, parkingEntry.getParkingHasRamps());
        if (parkingEntry.getParkAccessFloor() != null && parkingEntry.getParkAccessFloor() > -1)
            checkRadioGroup(accessFloorRadio, parkingEntry.getParkAccessFloor());
        if (parkingEntry.getParkFloorObs() != null)
            parkAccessFloorObsValue.setText(parkingEntry.getParkFloorObs());
        if (parkingEntry.getParkAccessRoute() != null && parkingEntry.getParkAccessRoute() > -1)
            checkRadioGroup(parkHasAccessRoute, parkingEntry.getParkAccessRoute());
        if (parkingEntry.getParkAccessRouteObs() != null)
            parkAccessRouteObsValue.setText(parkingEntry.getParkAccessRouteObs());
        if (parkingEntry.getParkingObs() != null)
            parkObsValue.setText(parkingEntry.getParkingObs());
        if (parkingEntry.getParkPhotos() != null)
            photoValue.setText(parkingEntry.getParkPhotos());
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
                .replace(R.id.show_fragment_selected, listFragment).addToBackStack(OTHER_OBJ_LIST).commit();
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
                if (newEntry != null) {
                    ViewModelEntry.insertParkingLot(newEntry);
                    parkingBundle.putBoolean(RECENT_ENTRY, true);
                    recentPark++;
                }
            } else if (parkingBundle.getInt(PARKING_ID) > 0) {
                ParkingLotEntry newEntry = newParkingLotEntry(parkingBundle);
                if (newEntry != null) {
                    newEntry.setParkingID(parkingBundle.getInt(PARKING_ID));
                    ViewModelEntry.updateParkingLot(newEntry);
                    parkingBundle.putBoolean(RECENT_ENTRY, true);
                    recentPark++;
                    openRampStairsListFragment();
                }
            } else {
                parkingBundle.putInt(PARKING_ID, 0);
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            }
        } else {
            if (checkEmptyFields()) {
                ParkingLotEntry newEntry = newParkingLotEntry(parkingBundle);
                if (newEntry != null) {
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
                            }
                            ViewModelEntry.insertParkingLot(newEntry);
                            parkingBundle.putBoolean(RECENT_ENTRY, true);
                            recentPark++;
                        }
                    }
                } else {
                    Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStack(PARKING_LIST, 0);
                }
            } else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkEmptyFields() {
        clearErrorsParkingLot();
        int error = 0;
        if (parkingBundle.getBoolean(EXT_AREA_REG)) {
            if (TextUtils.isEmpty(extParkLocalValue.getText())) {
                error++;
                extParkLocalField.setError(getString(R.string.req_field_error));
            }
        }
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
                parkAccessFloorObsField.setError(getString(R.string.req_field_error));
                error++;
            }
        }
        if (parkHasAccessRoute.getCheckedRadioButtonId() == -1) {
            parkAccessRouteError.setVisibility(View.VISIBLE);
            error++;
        } else if (getCheckedRadio(parkHasAccessRoute) == 0) {
            if (TextUtils.isEmpty(parkAccessRouteObsValue.getText())) {
                parkAccessRouteObsField.setError(getString(R.string.req_field_error));
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
        extParkLocalField.setErrorEnabled(false);
    }

    private int getCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

//    public void clearFields() {
//        accessFloorRadio.clearCheck();
//        parkHasAccessRoute.clearCheck();
//        parkHasRamps.clearCheck();
//        parkHasStairs.clearCheck();
//        parkAccessFloorObsValue.setText(null);
//        parkAccessRouteObsValue.setText(null);
//        extParkLocalValue.setText(null);
//        hasPcdVacancy.clearCheck();
//        hasElderlyVacancy.clearCheck();
//    }

    public ParkingLotEntry newParkingLotEntry(Bundle bundle) {
        String accessFloorObs = null, accessRouteObs = null, extLocal = null, parkObs = null, photo = null;
        Integer sideID = null, accessFloor = null, pcdVacancy = null, elderVacancy = null, accessRoute = null, hasStairs = null, hasRamps = null;
        int typeParking;

        if (bundle.getBoolean(EXT_AREA_REG)) {
            typeParking = 1;
            if (!TextUtils.isEmpty(extParkLocalValue.getText()))
                extLocal = String.valueOf(extParkLocalValue.getText());
        } else
            typeParking = 2;


        if (bundle.getInt(AMBIENT_ID) > 0)
            sideID = bundle.getInt(AMBIENT_ID);
        if (!TextUtils.isEmpty(parkAccessFloorObsValue.getText()))
            accessFloorObs = String.valueOf(parkAccessFloorObsValue.getText());
        if (!TextUtils.isEmpty(parkAccessRouteObsValue.getText()))
            accessRouteObs = String.valueOf(parkAccessRouteObsValue.getText());
        if (!TextUtils.isEmpty(parkObsValue.getText()))
            parkObs = String.valueOf(parkObsValue.getText());
        if (!TextUtils.isEmpty(photoValue.getText()))
            photo = String.valueOf(photoValue.getText());

        if (getCheckedRadio(accessFloorRadio) > -1)
            accessFloor = getCheckedRadio(accessFloorRadio);
        if (getCheckedRadio(hasPcdVacancy) > -1)
            pcdVacancy = getCheckedRadio(hasPcdVacancy);
        if (getCheckedRadio(hasElderlyVacancy) > -1)
            elderVacancy = getCheckedRadio(hasElderlyVacancy);
        if (getCheckedRadio(parkHasAccessRoute) > -1)
            accessRoute = getCheckedRadio(parkHasAccessRoute);
        if (getCheckedRadio(parkHasStairs) > -1)
            hasStairs = getCheckedRadio(parkHasStairs);
        if (getCheckedRadio(parkHasRamps) > -1)
            hasRamps = getCheckedRadio(parkHasRamps);

        return new ParkingLotEntry(bundle.getInt(BLOCK_ID), sideID, typeParking, accessFloor, accessFloorObs, pcdVacancy, elderVacancy, accessRoute,
                accessRouteObs, hasStairs, hasRamps, extLocal, parkObs, photo);

    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
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
}