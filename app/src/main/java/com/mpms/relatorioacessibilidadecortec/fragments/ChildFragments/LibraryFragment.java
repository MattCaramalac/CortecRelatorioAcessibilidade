package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

public class LibraryFragment extends Fragment {

    RadioGroup distShelvesRadio, pcrManeuverRadio, accessPcRadio;
    TextView distShelvesError, pcrError, accessPcError;

    ViewModelEntry modelEntry;

    public static final String DISTANCE_SHELVES = "DISTANCE_SHELVES";
    public static final String MANEUVER_PCR = "MANEUVER_PCR";
    public static final String COMPUTER_ACCESSIBLE = "COMPUTER_ACCESSIBLE";

    public LibraryFragment() {
        // Required empty public constructor
    }

    public static LibraryFragment newInstance() {
        return new LibraryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        libraryInstantiateView(view);

        getParentFragmentManager().setFragmentResultListener(RoomsRegisterFragment.LOAD_FRAG_DATA, this, (key, bundle) -> {
            if (bundle.getInt(RoomsRegisterFragment.ROOM_ID) > 0) {
                modelEntry.getRoomEntry(bundle.getInt(RoomsRegisterFragment.ROOM_ID)).observe(getViewLifecycleOwner(), this::loadLibraryData);
            }
        });

        getParentFragmentManager().setFragmentResultListener(InspectionActivity.GATHER_CHILD_DATA, this, (key, bundle) -> {
            if (!bundle.getBoolean(InspectionActivity.ADD_ITEM_REQUEST)) {
                bundle.putBoolean(RoomsRegisterFragment.CHILD_DATA_COMPLETE, checkEmptyLibraryFields());
            }
            gatherLibData(bundle);
            getParentFragmentManager().setFragmentResult(InspectionActivity.CHILD_DATA_LISTENER, bundle);
        });

        getParentFragmentManager().setFragmentResultListener(InspectionActivity.CLEAR_CHILD_DATA, this, (key, bundle) -> clearLibraryRadio());

    }

    private void libraryInstantiateView(View view) {
//        RadioGroups
        distShelvesRadio = view.findViewById(R.id.distance_shelves_radio);
        pcrManeuverRadio = view.findViewById(R.id.PCR_maneuver_radio);
        accessPcRadio = view.findViewById(R.id.computer_accessibility_radio);
//        TextView
        distShelvesError = view.findViewById(R.id.distance_shelves_error);
        pcrError = view.findViewById(R.id.PCR_maneuver_error);
        accessPcError = view.findViewById(R.id.computer_accessibility_error);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private void loadLibraryData(RoomEntry room) {
        if (room.getLibDistShelvesOK() != null && room.getLibDistShelvesOK() > -1)
            distShelvesRadio.check(distShelvesRadio.getChildAt(room.getLibDistShelvesOK()).getId());
        if (room.getLibPcrManeuversOK() != null && room.getLibPcrManeuversOK() > -1)
            pcrManeuverRadio.check(pcrManeuverRadio.getChildAt(room.getLibPcrManeuversOK()).getId());
        if (room.getLibAccessPcOK() != null && room.getLibAccessPcOK() > -1)
            accessPcRadio.check(accessPcRadio.getChildAt(room.getLibAccessPcOK()).getId());
    }

    private boolean checkEmptyLibraryFields() {
        clearLibEmptyFieldsErrors();
        int error = 0;
        if (distShelvesRadio.getCheckedRadioButtonId() == -1) {
            distShelvesError.setVisibility(View.VISIBLE);
            error++;
        }
        if (pcrManeuverRadio.getCheckedRadioButtonId() == -1) {
            pcrError.setVisibility(View.VISIBLE);
            error++;
        }
        if (accessPcRadio.getCheckedRadioButtonId() == -1) {
            accessPcError.setVisibility(View.VISIBLE);
            error++;
        }

        return error == 0;
    }

    private void clearLibEmptyFieldsErrors() {
        distShelvesError.setVisibility(View.GONE);
        pcrError.setVisibility(View.GONE);
        accessPcError.setVisibility(View.GONE);
    }

    private int getCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private void gatherLibData(Bundle bundle) {
        bundle.putInt(DISTANCE_SHELVES, getCheckedRadio(distShelvesRadio));
        bundle.putInt(MANEUVER_PCR, getCheckedRadio(pcrManeuverRadio));
        bundle.putInt(COMPUTER_ACCESSIBLE, getCheckedRadio(accessPcRadio));
    }

    private void clearLibraryRadio() {
        distShelvesRadio.clearCheck();
        pcrManeuverRadio.clearCheck();
        accessPcRadio.clearCheck();
    }
}

