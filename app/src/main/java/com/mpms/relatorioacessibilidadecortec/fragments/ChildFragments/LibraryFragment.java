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
import com.mpms.relatorioacessibilidadecortec.data.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.LibParcel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.parceler.Parcels;

public class LibraryFragment extends Fragment implements TagInterface, RadioGroupInterface {

    RadioGroup distShelvesRadio, libCorridorRadio, pcrManeuverRadio, hasComputerRadio, accessPcRadio;
    TextView distShelvesError, libCorridorError, pcrManeuverHeader, pcrError, hasPCError, accessPcHeader, accessPcError;

    ViewModelEntry modelEntry;

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

        getParentFragmentManager().setFragmentResultListener(LOAD_CHILD_DATA, this, (key, bundle) -> {
            if (bundle.getInt(AMBIENT_ID) > 0) {
                modelEntry.getRoomEntry(bundle.getInt(AMBIENT_ID)).observe(getViewLifecycleOwner(), this::loadLibraryData);
            }
        });

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA, this, (key, bundle) -> {
            if (!bundle.getBoolean(ADD_ITEM_REQUEST)) {
                bundle.putBoolean(CHILD_DATA_COMPLETE, checkEmptyLibraryFields());
            }
            gatherLibData(bundle);
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
        });

        getParentFragmentManager().setFragmentResultListener(CLEAR_CHILD_DATA, this, (key, bundle) -> clearLibraryRadio());

    }

    private void libraryInstantiateView(View view) {
//        RadioGroups
        distShelvesRadio = view.findViewById(R.id.distance_shelves_radio);
        pcrManeuverRadio = view.findViewById(R.id.PCR_maneuver_radio);
        accessPcRadio = view.findViewById(R.id.computer_accessibility_radio);
        libCorridorRadio = view.findViewById(R.id.lib_corridor_length_radio);
        hasComputerRadio = view.findViewById(R.id.lib_has_computers_radio);

//        TextView
        distShelvesError = view.findViewById(R.id.distance_shelves_error);
        pcrError = view.findViewById(R.id.PCR_maneuver_error);
        accessPcError = view.findViewById(R.id.computer_accessibility_error);
        pcrManeuverHeader = view.findViewById(R.id.header_PCR_maneuver);
        accessPcHeader = view.findViewById(R.id.label_computer_accessibility);
        libCorridorError = view.findViewById(R.id.lib_corridor_length_error);
        hasPCError = view.findViewById(R.id.lib_has_computers_error);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listener
        libCorridorRadio.setOnCheckedChangeListener(this::radioListener);
        hasComputerRadio.setOnCheckedChangeListener(this::radioListener);

    }

    private void loadLibraryData(RoomEntry room) {
        if (room.getLibDistShelvesOK() != null && room.getLibDistShelvesOK() > -1)
            checkRadioGroup(distShelvesRadio, room.getLibDistShelvesOK());
        if (room.getLibHasLongCorridors() != null && room.getLibHasLongCorridors() > -1) {
            checkRadioGroup(libCorridorRadio, room.getLibHasLongCorridors());
            if (room.getLibHasLongCorridors() == 1) {
                if (room.getLibHasManeuverArea() != null && room.getLibHasManeuverArea() > -1)
                    checkRadioGroup(pcrManeuverRadio, room.getLibHasManeuverArea());
            }
        }
        if (room.getLibHasPC() != null && room.getLibHasPC() > -1) {
            checkRadioGroup(hasComputerRadio, room.getLibHasPC());
            if (room.getLibHasPC() == 1) {
                if (room.getLibHasAccessPC() != null && room.getLibHasAccessPC() > -1)
                    checkRadioGroup(accessPcRadio, room.getLibHasAccessPC());
            }
        }
    }

    private boolean checkEmptyLibraryFields() {
        clearLibEmptyFieldsErrors();
        int error = 0;
        if (indexCheckRadio(distShelvesRadio) == -1) {
            distShelvesError.setVisibility(View.VISIBLE);
            error++;
        }
        if (indexCheckRadio(libCorridorRadio) == -1) {
            error++;
            libCorridorError.setVisibility(View.VISIBLE);
        } else if (indexCheckRadio(libCorridorRadio) == 1) {
            if (indexCheckRadio(pcrManeuverRadio) == -1) {
                pcrError.setVisibility(View.VISIBLE);
                error++;
            }
        }
        if (indexCheckRadio(hasComputerRadio) == -1) {
            error++;
            hasPCError.setVisibility(View.VISIBLE);
        } else if (indexCheckRadio(hasComputerRadio) == 1) {
            if (indexCheckRadio(accessPcRadio) == -1) {
                accessPcError.setVisibility(View.VISIBLE);
                error++;
            }
        }

        return error == 0;
    }

    private void clearLibEmptyFieldsErrors() {
        distShelvesError.setVisibility(View.GONE);
        libCorridorError.setVisibility(View.GONE);
        pcrError.setVisibility(View.GONE);
        hasPCError.setVisibility(View.GONE);
        accessPcError.setVisibility(View.GONE);
    }

    private void gatherLibData(Bundle bundle) {
        int hasDistShelves, hasLongCorridor, hasPC;
        Integer hasManeuver = null, hasAccessPC = null;

        hasDistShelves = indexCheckRadio(distShelvesRadio);
        hasLongCorridor = indexCheckRadio(libCorridorRadio);
        if (hasLongCorridor == 1)
            hasManeuver = indexCheckRadio(pcrManeuverRadio);
        hasPC = indexCheckRadio(hasComputerRadio);
        if (hasPC == 1)
            hasAccessPC = indexCheckRadio(accessPcRadio);

        LibParcel parcel = new LibParcel(hasDistShelves, hasLongCorridor, hasManeuver, hasPC, hasAccessPC);
        bundle.putParcelable(CHILD_PARCEL, Parcels.wrap(parcel));
    }

    private void clearLibraryRadio() {
        distShelvesRadio.clearCheck();
        libCorridorRadio.clearCheck();
        pcrManeuverRadio.clearCheck();
        pcrManeuverHeader.setVisibility(View.GONE);
        pcrManeuverRadio.setVisibility(View.GONE);
        hasComputerRadio.clearCheck();
        accessPcHeader.setVisibility(View.GONE);
        accessPcRadio.clearCheck();
        accessPcRadio.setVisibility(View.GONE);
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
        int index = indexCheckRadio(radio);

        if (radio == libCorridorRadio) {
            if (index == 1) {
                pcrManeuverHeader.setVisibility(View.VISIBLE);
                pcrManeuverRadio.setVisibility(View.VISIBLE);
            } else {
                pcrManeuverRadio.clearCheck();
                pcrManeuverHeader.setVisibility(View.GONE);
                pcrManeuverRadio.setVisibility(View.GONE);
                pcrError.setVisibility(View.GONE);
            }
        } else {
            if (index == 1) {
                accessPcHeader.setVisibility(View.VISIBLE);
                accessPcRadio.setVisibility(View.VISIBLE);
            } else {
                accessPcRadio.clearCheck();
                accessPcHeader.setVisibility(View.GONE);
                accessPcRadio.setVisibility(View.GONE);
                accessPcError.setVisibility(View.GONE);
            }
        }
    }
}

