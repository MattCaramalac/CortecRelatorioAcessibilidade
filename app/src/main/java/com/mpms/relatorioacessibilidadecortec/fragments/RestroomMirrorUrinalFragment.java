package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomMirrorEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomUrinalEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.RestroomMirrorChildFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.RestroomUrinalChildFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

public class RestroomMirrorUrinalFragment extends Fragment {

    public static final String OPENED_MIRROR = "OPENED_MIRROR";
    public static final String HAS_BOTH = "HAS_BOTH";
    public static final String HAS_MIRROR = "HAS_MIRROR";
    public static final String HAS_URINAL = "HAS_URINAL";

    TextView mirrorError, urinalError;
    RadioGroup mirrorRadio, urinalRadio;
    Button returnSinkTwo, save;
    Bundle restroomDataBundle, checkChildFrag;
    FragmentManager manager;

    ViewModelFragments modelFragments;

    Integer hasMirrors, hasUrinal;

    public RestroomMirrorUrinalFragment() {
        // Required empty public constructor
    }

    public static RestroomMirrorUrinalFragment newInstance() {
        return new RestroomMirrorUrinalFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        restroomDataBundle = this.getArguments();
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
        manager = getChildFragmentManager();
        return inflater.inflate(R.layout.fragment_restroom_mirror_urinal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mirrorError = view.findViewById(R.id.mirror_error);
        urinalError = view.findViewById(R.id.urinal_error);

        mirrorRadio = view.findViewById(R.id.restroom_mirror_radio);
        urinalRadio = view.findViewById(R.id.restroom_urinal_radio);

        returnSinkTwo = view.findViewById(R.id.return_sink_two);
        save = view.findViewById(R.id.save_mirror_urinal);

        radioListener();

        modelFragments.getRestChildFragBundle().observe(getViewLifecycleOwner(), dataBundle -> {
            if (dataBundle != null) {
                dataBundle.putInt(RestroomFragment.RESTROOM_ID, restroomDataBundle.getInt(RestroomFragment.RESTROOM_ID));
                createEntryFinishFragment(dataBundle);
            }
        });

//        TODO - Fazer os erros aparecerem nos fragmentos filhos já abertos, assim como no fragmento pai
        save.setOnClickListener( v-> {
            if (checkEmptyMirrorUrinalFields())
                if (hasMirrors == 0 && hasUrinal == 0) {
                    createEntryFinishFragment(restroomDataBundle);
                } else
                    sendDataFragments();
        });

        returnSinkTwo.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    @Override
    public void onResume() {
        super.onResume();
        restroomDataBundle.putBoolean(OPENED_MIRROR, true);
    }

    public int getMirrorUrinalCheckedIndex(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public void radioListener() {
        mirrorRadio.setOnCheckedChangeListener((radio, checkedId) -> {
            int index = getMirrorUrinalCheckedIndex(radio);
            if (index == 1)
                getChildFragmentManager().beginTransaction().replace(R.id.restroom_mirror_fragment, new RestroomMirrorChildFragment()).commit();
            else
                removeChildFragments(R.id.restroom_mirror_fragment);
        });
        urinalRadio.setOnCheckedChangeListener((radio, checkedId) -> {
            int index = getMirrorUrinalCheckedIndex(radio);
            if (index == 1)
                getChildFragmentManager().beginTransaction().replace(R.id.restroom_urinal_fragment, new RestroomUrinalChildFragment()).commit();
            else
                removeChildFragments(R.id.restroom_urinal_fragment);
        });
    }

    public void removeChildFragments(int fragID) {
        Fragment fragment = manager.findFragmentById(fragID);
        if (fragment != null)
            manager.beginTransaction().remove(fragment).commit();
    }

    public boolean checkEmptyMirrorUrinalFields() {
        if (getMirrorUrinalCheckedIndex(mirrorRadio) == -1) {
            mirrorError.setVisibility(View.VISIBLE);
        } else {
            hasMirrors = getMirrorUrinalCheckedIndex(mirrorRadio);
        }
        if (getMirrorUrinalCheckedIndex(urinalRadio) == -1) {
            urinalError.setVisibility(View.VISIBLE);
        } else {
            hasUrinal = getMirrorUrinalCheckedIndex(urinalRadio);
        }

        return hasMirrors != null && hasUrinal != null;
    }

    public void sendDataFragments() {
        checkChildFrag = new Bundle();
        if (hasMirrors == 1 && hasUrinal == 1)
            checkChildFrag.putBoolean(HAS_BOTH, true);
        else if (hasMirrors == 1 && hasUrinal == 0)
            checkChildFrag.putBoolean(HAS_MIRROR, true);
        else if (hasMirrors == 0 && hasUrinal == 1)
            checkChildFrag.putBoolean(HAS_URINAL, true);
        modelFragments.setCheckMirUrFrags(checkChildFrag);
    }

    public void finishRestroomRegister() {
        int schoolID = restroomDataBundle.getInt(InspectionActivity.SCHOOL_ID_VALUE);
        restroomDataBundle = new Bundle();
        restroomDataBundle.putInt(InspectionActivity.SCHOOL_ID_VALUE, schoolID);
        modelFragments.setRestroomBundle(null);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        RestroomFragment restroom = RestroomFragment.newInstance();
        restroom.setArguments(restroomDataBundle);
        fragmentTransaction.replace(R.id.show_fragment_selected, restroom).addToBackStack(null).commit();
    }

    public RestroomMirrorEntry newMirror(Bundle bundle) {
        return new RestroomMirrorEntry(bundle.getInt(RestroomFragment.RESTROOM_ID), hasMirrors, bundle.getDouble(RestroomMirrorChildFragment.MIRROR_A),
                bundle.getDouble(RestroomMirrorChildFragment.MIRROR_B), bundle.getString(RestroomMirrorChildFragment.MIRROR_OBS));
    }

    public RestroomUrinalEntry newUrinal (Bundle bundle) {
        return new RestroomUrinalEntry(bundle.getInt(RestroomFragment.RESTROOM_ID), hasUrinal, bundle.getDouble(RestroomUrinalChildFragment.URINAL_MEASURE_A),
                bundle.getDouble(RestroomUrinalChildFragment.URINAL_MEASURE_B), bundle.getDouble(RestroomUrinalChildFragment.URINAL_MEASURE_C),
                bundle.getDouble(RestroomUrinalChildFragment.URINAL_MEASURE_D), bundle.getDouble(RestroomUrinalChildFragment.URINAL_MEASURE_E),
                bundle.getDouble(RestroomUrinalChildFragment.URINAL_MEASURE_F), bundle.getDouble(RestroomUrinalChildFragment.URINAL_MEASURE_G),
                bundle.getDouble(RestroomUrinalChildFragment.URINAL_MEASURE_H), bundle.getDouble(RestroomUrinalChildFragment.URINAL_MEASURE_I),
                bundle.getDouble(RestroomUrinalChildFragment.URINAL_MEASURE_J), bundle.getDouble(RestroomUrinalChildFragment.URINAL_MEASURE_K),
                bundle.getString(RestroomUrinalChildFragment.URINAL_OBS));
    }

    public void createEntryFinishFragment(Bundle bundle) {
        RestroomMirrorEntry newMirror = newMirror(bundle);
        ViewModelEntry.insertRestroomMirrorEntry(newMirror);
        RestroomUrinalEntry newUrinal = newUrinal(bundle);
        ViewModelEntry.insertRestroomUrinalEntry(newUrinal);
        finishRestroomRegister();
    }


}