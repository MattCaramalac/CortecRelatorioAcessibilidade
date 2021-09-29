package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;


public class WaterFountainFragment extends Fragment {

    private ViewModelFragments modelFragments;

    private int chosenFountain = -1;

    private Bundle bundle;

    public static int schoolID;

    WaterFountainEntry newFountain;

    RadioGroup typeWaterFountain;
    TextView typeWaterFountainError;
    Button cancelWaterFountain, saveWaterFountain;

    public WaterFountainFragment() {
        // Required empty public constructor
    }

    public static WaterFountainFragment newInstance() {
        return new WaterFountainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle schoolBundle = this.getArguments();
        if (schoolBundle != null)
            schoolID = schoolBundle.getInt(InspectionActivity.SCHOOL_ID_VALUE);
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_water_fountain, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateFountainViews(view);

        typeWaterFountain.setOnCheckedChangeListener(this::typeFountainListener);

        modelFragments.getFountainBundle().observe(getViewLifecycleOwner(), bundle -> {
            if (bundle != null) {
                newFountain = createFountain(bundle, chosenFountain);
                ViewModelEntry.insertWaterFountain(newFountain);
                Toast.makeText(getContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                modelFragments.setFountainBundle(null);
                typeWaterFountain.clearCheck();
            }
        });

        saveWaterFountain.setOnClickListener(v -> {
            if (verifyWaterFountainErrors())
                modelFragments.setSaveAttemptFountain(1);
        });

        cancelWaterFountain.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .beginTransaction().remove(this).commit());
    }

    private void instantiateFountainViews(View view) {
        typeWaterFountain = view.findViewById(R.id.fountain_type_radio);

        typeWaterFountainError = view.findViewById(R.id.water_fountain_type_error);

        cancelWaterFountain = view.findViewById(R.id.cancel_waterfountain);
        saveWaterFountain = view.findViewById(R.id.save_waterfountain);
    }

    public void typeFountainListener(RadioGroup group, int checkedID) {
        RadioButton radioButton = group.findViewById(checkedID);
        int index = group.indexOfChild(radioButton);
        chosenFountain = index;

        switch (index) {
            case 0:
                getChildFragmentManager().beginTransaction().replace(R.id.water_fountain_info, new WaterFountainSpoutFragment()).commit();
                break;
            case 1:
                getChildFragmentManager().beginTransaction().replace(R.id.water_fountain_info, new WaterFountainOtherFragment()).commit();
                break;
            default:
                break;

        }
    }

    public boolean verifyWaterFountainErrors() {
        clearWaterFountainErrors();
        int errors = 0;
        if (typeWaterFountain.getCheckedRadioButtonId() == -1) {
            typeWaterFountainError.setVisibility(View.VISIBLE);
            errors++;
        }
        return errors == 0;
    }

    public void clearWaterFountainErrors(){
        typeWaterFountainError.setVisibility(View.GONE);
    }

    public WaterFountainEntry createFountain(Bundle bundle, int chosenFountain) {
        WaterFountainEntry newFountain = null;
        if (chosenFountain == 0) {
            newFountain = new WaterFountainEntry(schoolID, chosenFountain, null, null, null, null,
                    bundle.getInt(WaterFountainSpoutFragment.ALLOW_FRONTAL), bundle.getDouble(WaterFountainSpoutFragment.HIGHEST_SPOUT),
                    bundle.getDouble(WaterFountainSpoutFragment.LOWEST_SPOUT), bundle.getDouble(WaterFountainSpoutFragment.FREE_SPACE_SPOUT),
                    bundle.getString(WaterFountainSpoutFragment.SPOUT_FOUNTAIN_OBS));
        } else if (chosenFountain == 1) {
            newFountain = new WaterFountainEntry(schoolID, chosenFountain, bundle.getInt(WaterFountainOtherFragment.ALLOW_LATERAL),
                    bundle.getDouble(WaterFountainOtherFragment.FAUCET_HEIGHT), bundle.getInt(WaterFountainOtherFragment.HAS_CUP_HOLDER),
                    bundle.getDouble(WaterFountainOtherFragment.CUP_HOLDER_HEIGHT),null, null,
                    null, null, bundle.getString(WaterFountainOtherFragment.OTHER_FOUNTAIN_OBS));
        }
        return newFountain;
    }

}
