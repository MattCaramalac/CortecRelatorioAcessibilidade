package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.mpms.relatorioacessibilidadecortec.R;


public class ParkingLotPdmrFragment extends Fragment {

    ConstraintLayout layout;
    TextView fragHeader, hasVacancyHeader;
    RadioGroup hasVacancy, verticalSign, horizontalSign, safetyZone, siaPdmr;
    Button cancelParkingLotPdmr, proceedParkingLotPdmr;

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

        layout = view.findViewById(R.id.parking_lot_PDMR_constraint_layout);

        fragHeader = view.findViewById(R.id.frag_parking_lot_PDMR_header);
        hasVacancyHeader = view.findViewById(R.id.parking_lot_PDMR_vacancy_header);

        hasVacancy = view.findViewById(R.id.parking_lot_PDMR_vacancy_radio);
        verticalSign = view.findViewById(R.id.vertical_sign_PDMR_radio);
        horizontalSign = view.findViewById(R.id.horizontal_sign_PDMR_radio);
        safetyZone = view.findViewById(R.id.security_zone_PDMR_radio);
        siaPdmr = view.findViewById(R.id.PDMR_SIA_radio);

        cancelParkingLotPdmr = view.findViewById(R.id.cancel_parking_lot_pdmr);
        proceedParkingLotPdmr = view.findViewById(R.id.save_parking_lot_pdmr);

        disableEverything(layout);

        hasVacancy.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.PDMR_vacancy_radio_button_yes:
                    enableEverything(layout);
                    break;
                default:
                    disableEverything(layout);
                    break;
            }

        });


    }

    public void disableEverything(ConstraintLayout layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            View layoutView = layout.getChildAt(i);

            if (layoutView.getId() == verticalSign.getId())
                disableRadioGroup(verticalSign);
            else if (layoutView.getId() == horizontalSign.getId())
                disableRadioGroup(horizontalSign);
            else if (layoutView.getId() == safetyZone.getId())
                disableRadioGroup(safetyZone);
            else if (layoutView.getId() == siaPdmr.getId())
                disableRadioGroup(siaPdmr);
            else if (layoutView.getId() != fragHeader.getId() && layoutView.getId() != hasVacancyHeader.getId() &&
                    layoutView.getId() != cancelParkingLotPdmr.getId() && layoutView.getId() != proceedParkingLotPdmr.getId()) {
                layoutView.setEnabled(false);
            }
        }
    }

    public void enableEverything(ConstraintLayout layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            View layoutView = layout.getChildAt(i);

            if (layoutView.getId() == verticalSign.getId())
                enableRadioGroup(verticalSign);
            else if (layoutView.getId() == horizontalSign.getId())
                enableRadioGroup(horizontalSign);
            else if (layoutView.getId() == safetyZone.getId())
                enableRadioGroup(safetyZone);
            else if (layoutView.getId() == siaPdmr.getId())
                enableRadioGroup(siaPdmr);
            else if (layoutView.getId() != fragHeader.getId() && layoutView.getId() != hasVacancyHeader.getId() &&
                    layoutView.getId() != cancelParkingLotPdmr.getId() && layoutView.getId() != proceedParkingLotPdmr.getId()) {
                layoutView.setEnabled(true);
            }
        }
    }

    public void disableRadioGroup(RadioGroup radioGroup) {
        for (int j = 0; j < radioGroup.getChildCount(); j++) {
            radioGroup.getChildAt(j).setEnabled(false);
        }
    }

    public void enableRadioGroup(RadioGroup radioGroup) {
        for (int j = 0; j < radioGroup.getChildCount(); j++) {
            radioGroup.getChildAt(j).setEnabled(true);
        }
    }

    public void radioGroupListener(RadioGroup radioGroup) {

    }
}