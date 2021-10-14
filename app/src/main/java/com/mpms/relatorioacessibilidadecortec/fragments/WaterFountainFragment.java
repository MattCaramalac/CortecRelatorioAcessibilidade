package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
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
import androidx.lifecycle.ViewModelProvider;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.activities.SchoolRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;


public class WaterFountainFragment extends Fragment {

    public static final String FOUNTAIN_ID = "FOUNTAIN_ID";

    ViewModelFragments modelFragments;
    ViewModelEntry modelEntry;

    Bundle fountainBundle = new Bundle();

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
        if (this.getArguments() != null) {
            fountainBundle.putInt(SchoolRegisterActivity.SCHOOL_ID, this.getArguments().getInt(SchoolRegisterActivity.SCHOOL_ID));
            fountainBundle.putInt(FOUNTAIN_ID, this.getArguments().getInt(FOUNTAIN_ID));
        }
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

        if (fountainBundle.getInt(FOUNTAIN_ID) > 0) {
            modelEntry.getOneWaterFountain(fountainBundle.getInt(FOUNTAIN_ID)).observe(getViewLifecycleOwner(), this::gatherFountainInfo);
        }

        modelFragments.getFountainBundle().observe(getViewLifecycleOwner(), bundle -> {
            if (bundle != null) {
                bundle.putInt(SchoolRegisterActivity.SCHOOL_ID, fountainBundle.getInt(SchoolRegisterActivity.SCHOOL_ID));
                WaterFountainEntry newFountain = createFountain(bundle);
                if (fountainBundle.getInt(FOUNTAIN_ID) > 0) {
                    newFountain.setWaterFountainID(fountainBundle.getInt(FOUNTAIN_ID));
                    ViewModelEntry.updateWaterFountain(newFountain);
                    Toast.makeText(getContext(), "Cadastro atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                    modelFragments.setFountainFragData(null);
                    modelFragments.setFountainBundle(null);
                    typeWaterFountain.clearCheck();
                    FragmentManager manager = requireActivity().getSupportFragmentManager();
                    manager.popBackStack(InspectionActivity.WATER_LIST, 0);
                } else {
                    ViewModelEntry.insertWaterFountain(newFountain);
                    Toast.makeText(getContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                    modelFragments.setFountainBundle(null);
                    typeWaterFountain.clearCheck();
                }
            }
        });

        saveWaterFountain.setOnClickListener(v -> {
            if (verifyWaterFountainErrors())
                modelFragments.setSaveAttemptFountain(1);
        });

        cancelWaterFountain.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .popBackStack(InspectionActivity.WATER_LIST, 0));
    }

    private void instantiateFountainViews(View view) {
        typeWaterFountain = view.findViewById(R.id.fountain_type_radio);

        typeWaterFountainError = view.findViewById(R.id.water_fountain_type_error);

        cancelWaterFountain = view.findViewById(R.id.cancel_waterfountain);
        saveWaterFountain = view.findViewById(R.id.save_waterfountain);

        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
        modelEntry = new ViewModelEntry(requireActivity().getApplication());

    }

    public void typeFountainListener(RadioGroup group, int checkedID) {
        int index = getCheckedFountainType(group);

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

    private void gatherFountainInfo(WaterFountainEntry waterFountain) {
        Bundle fountainFrag = new Bundle();
        typeWaterFountain.check(typeWaterFountain.getChildAt(waterFountain.getTypeWaterFountain()).getId());
        if (waterFountain.getTypeWaterFountain() == 0) {
            fountainFrag.putInt(WaterFountainSpoutFragment.ALLOW_FRONTAL, waterFountain.getSpoutAllowFrontalApproximation());
            fountainFrag.putDouble(WaterFountainSpoutFragment.HIGHEST_SPOUT, waterFountain.getHighestSpoutHeight());
            fountainFrag.putDouble(WaterFountainSpoutFragment.LOWEST_SPOUT, waterFountain.getLowestSpoutHeight());
            fountainFrag.putDouble(WaterFountainSpoutFragment.FREE_SPACE_SPOUT, waterFountain.getFreeSpaceLowestSpout());
            fountainFrag.putString(WaterFountainSpoutFragment.SPOUT_FOUNTAIN_OBS, waterFountain.getFountainObs());
        } else {
            fountainFrag.putInt(WaterFountainOtherFragment.ALLOW_LATERAL, waterFountain.getOtherAllowSideApproximation());
            fountainFrag.putDouble(WaterFountainOtherFragment.FAUCET_HEIGHT, waterFountain.getOtherFaucetHeight());
            fountainFrag.putInt(WaterFountainOtherFragment.HAS_CUP_HOLDER, waterFountain.getOtherHasCupHolder());
            if (waterFountain.getOtherHasCupHolder() == 1)
                fountainFrag.putDouble(WaterFountainOtherFragment.CUP_HOLDER_HEIGHT, waterFountain.getOtherCupHolderHeight());
            fountainFrag.putString(WaterFountainOtherFragment.OTHER_FOUNTAIN_OBS, waterFountain.getFountainObs());
        }
        modelFragments.setFountainFragData(fountainFrag);
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

    private int getCheckedFountainType(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public void clearWaterFountainErrors(){
        typeWaterFountainError.setVisibility(View.GONE);
    }

    public WaterFountainEntry createFountain(Bundle bundle) {
        int choice = getCheckedFountainType(typeWaterFountain);
        if (choice == 0) {
            return new WaterFountainEntry(bundle.getInt(SchoolRegisterActivity.SCHOOL_ID), choice, null,
                    null, null, null, bundle.getInt(WaterFountainSpoutFragment.ALLOW_FRONTAL),
                    bundle.getDouble(WaterFountainSpoutFragment.HIGHEST_SPOUT), bundle.getDouble(WaterFountainSpoutFragment.LOWEST_SPOUT),
                    bundle.getDouble(WaterFountainSpoutFragment.FREE_SPACE_SPOUT), bundle.getString(WaterFountainSpoutFragment.SPOUT_FOUNTAIN_OBS));
        } else {
            return new WaterFountainEntry(bundle.getInt(SchoolRegisterActivity.SCHOOL_ID), choice,
                    bundle.getInt(WaterFountainOtherFragment.ALLOW_LATERAL), bundle.getDouble(WaterFountainOtherFragment.FAUCET_HEIGHT),
                    bundle.getInt(WaterFountainOtherFragment.HAS_CUP_HOLDER), bundle.getDouble(WaterFountainOtherFragment.CUP_HOLDER_HEIGHT),
                    null, null, null, null,
                    bundle.getString(WaterFountainOtherFragment.OTHER_FOUNTAIN_OBS));
        }
    }

}
