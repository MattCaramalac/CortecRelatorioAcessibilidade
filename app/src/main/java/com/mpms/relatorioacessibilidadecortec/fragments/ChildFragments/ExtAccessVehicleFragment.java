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
import com.mpms.relatorioacessibilidadecortec.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.ArrayList;

public class ExtAccessVehicleFragment extends Fragment {

    RadioGroup hasSoundSignRadio;
    TextView soundSingError;

    ViewModelEntry modelEntry;

    ArrayList<String> carList = new ArrayList<>();

    int extAccessID = 0;

    public ExtAccessVehicleFragment() {
        // Required empty public constructor
    }

    public static ExtAccessVehicleFragment newInstance() {
        return new ExtAccessVehicleFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            extAccessID = this.getArguments().getInt(ExternalAccessFragment.EXT_ACCESS_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ext_access_vehicle, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        instantiateVehicleViews(view);

        getParentFragmentManager().setFragmentResultListener(ExternalAccessFragment.EXT_ACCESS_SAVE_ATTEMPT, this, (key,bundle) -> {
            carList = bundle.getStringArrayList(ExternalAccessFragment.EXT_ARRAY);
            soundSingError.setVisibility(View.GONE);
            if(getRadioCheckIndex(hasSoundSignRadio) == -1){
                carList.set(18, "false");
                soundSingError.setVisibility(View.VISIBLE);
            }
            else {
                carList.set(17, String.valueOf(getRadioCheckIndex(hasSoundSignRadio)));
                carList.set(18, null);
            }
            bundle.putStringArrayList(ExternalAccessFragment.CHILD_FRAG_DATA, carList);
            getParentFragmentManager().setFragmentResult(ExternalAccessFragment.FRAG_DATA, bundle);
        });

        if (extAccessID > 0)
            modelEntry.getOneExternalAccess(extAccessID).observe(getViewLifecycleOwner(), this::loadVehicleData);
    }

    private void instantiateVehicleViews(View view) {
//        RadioGroup
        hasSoundSignRadio = view.findViewById(R.id.has_sound_sign_radio);
//        TextView
        soundSingError = view.findViewById(R.id.has_sound_sign_error);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private int getRadioCheckIndex(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private void loadVehicleData(ExternalAccess access) {
        hasSoundSignRadio.check(hasSoundSignRadio.getChildAt(access.getGateHasSoundSign()).getId());
    }
}