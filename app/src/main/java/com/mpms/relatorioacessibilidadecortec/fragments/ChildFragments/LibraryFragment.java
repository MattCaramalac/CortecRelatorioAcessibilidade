package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

import java.util.Objects;

public class LibraryFragment extends Fragment {

    RadioGroup distanceShelvesAcceptable, maneuverPcrAcceptable, computersAccessible;

    ViewModelFragments modelFragments;

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

        modelFragments.getSaveAttemptRoom().observe(getViewLifecycleOwner(), roomAttempt -> {
            if (Objects.equals(modelFragments.getSaveAttemptRoom().getValue(), 1)) {
                if (checkEmptyLibraryFields()) {
                   Bundle libraryBundle = new Bundle();
                   libraryBundle.putInt(DISTANCE_SHELVES, getCheckedRadio(distanceShelvesAcceptable));
                   libraryBundle.putInt(MANEUVER_PCR, getCheckedRadio(maneuverPcrAcceptable));
                   libraryBundle.putInt(COMPUTER_ACCESSIBLE, getCheckedRadio(computersAccessible));
                   modelFragments.setRoomBundle(libraryBundle);
                   clearLibraryRadio();
                } else
                    Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();

                modelFragments.setSaveAttemptRooms(0);
            }

        });
    }

    public void libraryInstantiateView(View view) {
//        RadioGroups
        distanceShelvesAcceptable = view.findViewById(R.id.distance_shelves_radio);
        maneuverPcrAcceptable = view.findViewById(R.id.PCR_maneuver_radio);
        computersAccessible = view.findViewById(R.id.computer_accessibility_radio);
//        ViewModel
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);

    }

    public boolean checkEmptyLibraryFields() {
        int error = 0;
        if (distanceShelvesAcceptable.getCheckedRadioButtonId() == -1) {
            error++;
        }
        if (maneuverPcrAcceptable.getCheckedRadioButtonId() == -1) {
            error++;
        }
        if (computersAccessible.getCheckedRadioButtonId() == -1) {
            error++;
        }

        return error == 0;
    }

    public int getCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public void clearLibraryRadio() {
        distanceShelvesAcceptable.clearCheck();
        maneuverPcrAcceptable.clearCheck();
        computersAccessible.clearCheck();
    }
}

