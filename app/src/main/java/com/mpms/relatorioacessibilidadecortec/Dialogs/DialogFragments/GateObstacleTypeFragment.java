package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelDialog;

import java.util.Objects;

public class GateObstacleTypeFragment extends Fragment {

    public static final String GATE_OBS_TYPE = "GATE_OBS_TYPE";

    RadioGroup gateObsType;
    TextView gateObsTypeError;

    FragmentManager manager;

    ViewModelDialog modelDialog;

    public GateObstacleTypeFragment() {
        // Required empty public constructor
    }

    public static GateObstacleTypeFragment newInstance() {
        return new GateObstacleTypeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gate_obstacle_type, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gateObsType = view.findViewById(R.id.gate_obstacle_type_radio);
        gateObsTypeError = view.findViewById(R.id.gate_obstacle_type_error);

        modelDialog = new ViewModelProvider(requireActivity()).get(ViewModelDialog.class);

        modelDialog.getSaveGateObsAttemptOne().observe(getViewLifecycleOwner(), partOne -> {
            if (Objects.equals(modelDialog.getSaveGateObsAttemptOne().getValue(), 1)) {
                if(checkEmptyGateTypeRadio()) {
                   modelDialog.setSaveGateObsAttemptTwo(1);
                }
                modelDialog.setSaveGateObsAttemptOne(0);
            }
        });

        modelDialog.getTempGateObsInfo().observe(getViewLifecycleOwner(), tempBundle -> {
            if (tempBundle != null) {
                tempBundle.putInt(GATE_OBS_TYPE, getCheckedRadio(gateObsType));
                modelDialog.setGateObsInfo(tempBundle);
//                Teve que ser colocado aqui dentro, caso contrário entra em loop infinito
//                Por quê isto acontece? Gostaria de saber também
                modelDialog.setTempGateObsInfo(null);
            }
        });

        gateObsType.setOnCheckedChangeListener((group, checkedID) -> {
            int index = getCheckedRadio(group);
            switch (index) {
                case 0:
                    getChildFragmentManager().beginTransaction().replace(R.id.gate_obs_type_info_child_fragment, new GateObsBarrierType()).commit();
                    break;
                case 1:
                    getChildFragmentManager().beginTransaction().replace(R.id.gate_obs_type_info_child_fragment, new GateObsDoorType()).commit();
                    break;
            }
        });
    }

    public int getCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public boolean checkEmptyGateTypeRadio() {
        clearErrorGateObsType();
        int empty = 1;
        if (getCheckedRadio(gateObsType) == -1) {
            empty--;
            gateObsTypeError.setVisibility(View.VISIBLE);
        }
        return empty == 1;
    }

    public void clearErrorGateObsType() {
        gateObsTypeError.setVisibility(View.GONE);
    }

    public void clearRadioGateObsType() {
        gateObsType.clearCheck();
    }

    public void removeGateObsFragments() {
        try {
            GateObsBarrierType barrier = (GateObsBarrierType) manager.findFragmentById(R.id.gate_obs_type_info_child_fragment);
            if (barrier != null)
                manager.beginTransaction().remove(barrier).commit();
        } catch (Exception e) {
            try {
                GateObsDoorType door = (GateObsDoorType) manager.findFragmentById(R.id.gate_obs_type_info_child_fragment);
                if (door != null)
                    manager.beginTransaction().remove(door).commit();
            } catch (Exception f) {
                Toast.makeText(getContext(), "Algo deu errado, tente novamente", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


