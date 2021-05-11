package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.mpms.relatorioacessibilidadecortec.R;


public class ParkingLotPdmrFragment extends Fragment {

    public ParkingLotPdmrFragment() {
        // Required empty public constructor
    }

    public static ParkingLotPdmrFragment newInstance() {
        return new ParkingLotPdmrFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parking_lot_pdmr, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ConstraintLayout layout = view.findViewById(R.id.parking_lot_PDMR_constraint_layout);
        RadioButton hasVacancy = view.findViewById(R.id.PDMR_vacancy_radio_button_yes);
        RadioButton hasNotVacancy = view.findViewById(R.id.PDMR_vacancy_radio_button_no);
        TextView fragHeader = view.findViewById(R.id.frag_parking_lot_PDMR_header);

        for (int i = 0; i < layout.getChildCount(); i++) {
            View layoutView = layout.getChildAt(i);

            if (layoutView.getId() != hasVacancy.getId() && layoutView.getId() != hasNotVacancy.getId() && layoutView.getId() != fragHeader.getId()) {
                layoutView.setEnabled(false);
            }
        }
    }
}