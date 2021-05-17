package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

import java.util.Objects;

public class WaterFountainFragment extends Fragment {

    private ViewModelFragments modelFragments;

    public static int schoolID;

    RadioGroup typeWaterFountain;
    TextView typeWaterFountainError;
    Button cancelWaterfountain, saveWaterFountain;

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

        typeWaterFountain = view.findViewById(R.id.fountain_type_radio);

        typeWaterFountainError = view.findViewById(R.id.water_fountain_type_error);

        cancelWaterfountain = view.findViewById(R.id.cancel_waterfountain);
        saveWaterFountain = view.findViewById(R.id.save_waterfountain);

        typeWaterFountain.setOnCheckedChangeListener(this::typeFountainListener);

        modelFragments.getFountainBundle().observe(getViewLifecycleOwner(), bundle -> {
//            Colocar a recepção de dados aqui para poder salvar nas tabelas
            if (true) {

            } else {

            }
        });

        saveWaterFountain.setOnClickListener(v -> {
            modelFragments.saveAttemptTestWaterFountain(1);
            modelFragments.getSaveAttempt().observe(getViewLifecycleOwner(), saveAttempt -> {
                if (verifyWaterFountainErrors()) {
//                    WaterFountainEntry newWaterFountain = createWaterFountain();
//                    ViewModelEntry.insertWaterFountain(newWaterFountain);
                }
            });
        });

        cancelWaterfountain.setOnClickListener(v -> Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction().remove(this).commit());
    }

    public void typeFountainListener(RadioGroup group, int checkedID) {
        RadioButton radioButton = group.findViewById(checkedID);
        int index = group.indexOfChild(radioButton);

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


//    public WaterFountainEntry createWaterFountain() {
//        bun
//        return new WaterFountainEntry();
//    }

//    (@NonNull Integer schoolEntryID, @NonNull Integer typeWaterFountain, Integer otherAllowSideApproximation,
//    Double otherFaucetHeight, Integer otherHasCupHolder, Double otherCupHolderHeight,
//    Integer spoutAllowFrontalApproximation, Double highestSpoutHeight, Double lowestSpoutHeight,
//    Double freeSpaceLowestSpout) {

}
