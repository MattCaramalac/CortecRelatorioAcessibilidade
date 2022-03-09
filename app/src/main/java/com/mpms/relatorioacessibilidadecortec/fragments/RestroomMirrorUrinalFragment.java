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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.activities.SchoolRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomMirrorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomUrinalEntry;
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
    ViewModelEntry modelEntry;

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
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        manager = getChildFragmentManager();
        return inflater.inflate(R.layout.fragment_restroom_mirror_urinal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateMirrorUrinal(view);
        radioListener();

        modelEntry.getOneRestroomMirrorEntry(restroomDataBundle.getInt(RestroomFragment.RESTROOM_ID)).
                observe(getViewLifecycleOwner(), mirrorEntry ->  {
                    if (mirrorEntry != null) {
                        restroomDataBundle.putInt(RestroomMirrorChildFragment.MIRROR_ID, mirrorEntry.getMirrorID());
                        gatherInfoMirror(mirrorEntry);
                    }

                });

        modelEntry.getOneRestroomUrinalEntry(restroomDataBundle.getInt(RestroomFragment.RESTROOM_ID)).
                observe(getViewLifecycleOwner(), urinalEntry ->  {
                    if (urinalEntry != null) {
                        restroomDataBundle.putInt(RestroomMirrorChildFragment.MIRROR_ID, urinalEntry.getUrinalID());
                        gatherInfoUrinal(urinalEntry);
                    }

                });

        modelFragments.getRestChildFragBundle().observe(getViewLifecycleOwner(), dataBundle -> {
            if (dataBundle != null) {
                dataBundle.putInt(RestroomFragment.RESTROOM_ID, restroomDataBundle.getInt(RestroomFragment.RESTROOM_ID));
                createEntryFinishFragment(dataBundle);
                modelFragments.setRestChildFragBundle(null);
            }
        });

//        TODO - Fazer os erros aparecerem nos fragmentos filhos já abertos, assim como no fragmento pai
        save.setOnClickListener(v -> {
            if (checkEmptyMirrorUrinalFields())
                if (hasMirrors == 0 && hasUrinal == 0) {
                    createEntryFinishFragment(restroomDataBundle);
                } else
                    sendDataFragments();
        });

        returnSinkTwo.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void instantiateMirrorUrinal(View view) {
        mirrorError = view.findViewById(R.id.mirror_error);
        urinalError = view.findViewById(R.id.urinal_error);

        mirrorRadio = view.findViewById(R.id.restroom_mirror_radio);
        urinalRadio = view.findViewById(R.id.restroom_urinal_radio);

        returnSinkTwo = view.findViewById(R.id.return_sink_two);
        save = view.findViewById(R.id.save_mirror_urinal);
    }

    public void gatherInfoMirror(RestroomMirrorEntry mirrorEntry) {
        mirrorRadio.check(mirrorRadio.getChildAt(mirrorEntry.getRestroomHasMirror()).getId());
        restroomDataBundle.putInt(RestroomMirrorChildFragment.MIRROR_ID, mirrorEntry.getMirrorID());
        if (mirrorEntry.getRestroomHasMirror() == 1) {
            Bundle mirrorData = new Bundle();
            mirrorData.putDouble(RestroomMirrorChildFragment.MIRROR_A, mirrorEntry.getMirrorMeasureA());
            mirrorData.putDouble(RestroomMirrorChildFragment.MIRROR_B, mirrorEntry.getMirrorMeasureB());
            mirrorData.putString(RestroomMirrorChildFragment.MIRROR_OBS, mirrorEntry.getMirrorObs());
            mirrorData.putBoolean(RestroomMirrorChildFragment.FILL_MIRROR, true);
            modelFragments.setSendMirrorFragData(mirrorData);
        }
    }

    public void gatherInfoUrinal(RestroomUrinalEntry urinalEntry) {
        urinalRadio.check(urinalRadio.getChildAt(urinalEntry.getRestroomHasUrinal()).getId());
        restroomDataBundle.putInt(RestroomUrinalChildFragment.URINAL_ID, urinalEntry.getUrinalID());
        if (urinalEntry.getRestroomHasUrinal() == 1) {
            Bundle urinalData = new Bundle();
            urinalData.putDouble(RestroomUrinalChildFragment.URINAL_MEASURE_A, urinalEntry.getUrinalMeasureA());
            urinalData.putDouble(RestroomUrinalChildFragment.URINAL_MEASURE_B, urinalEntry.getUrinalMeasureB());
            urinalData.putDouble(RestroomUrinalChildFragment.URINAL_MEASURE_C, urinalEntry.getUrinalMeasureC());
            urinalData.putDouble(RestroomUrinalChildFragment.URINAL_MEASURE_D, urinalEntry.getUrinalMeasureD());
            urinalData.putDouble(RestroomUrinalChildFragment.URINAL_MEASURE_E, urinalEntry.getUrinalMeasureE());
            urinalData.putDouble(RestroomUrinalChildFragment.URINAL_MEASURE_F, urinalEntry.getUrinalMeasureF());
            urinalData.putDouble(RestroomUrinalChildFragment.URINAL_MEASURE_G, urinalEntry.getUrinalMeasureG());
            urinalData.putDouble(RestroomUrinalChildFragment.URINAL_MEASURE_H, urinalEntry.getUrinalMeasureH());
            urinalData.putDouble(RestroomUrinalChildFragment.URINAL_MEASURE_I, urinalEntry.getUrinalMeasureI());
            urinalData.putDouble(RestroomUrinalChildFragment.URINAL_MEASURE_J, urinalEntry.getUrinalMeasureJ());
            urinalData.putDouble(RestroomUrinalChildFragment.URINAL_MEASURE_K, urinalEntry.getUrinalMeasureK());
            urinalData.putString(RestroomUrinalChildFragment.URINAL_OBS, urinalEntry.getUrinalObs());
            urinalData.putBoolean(RestroomUrinalChildFragment.FILL_URINAL, true);
            modelFragments.setSendUrinalFragData(urinalData);
        }
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
        int schoolID = restroomDataBundle.getInt(SchoolRegisterActivity.SCHOOL_ID);
        restroomDataBundle = new Bundle();
        restroomDataBundle.putInt(SchoolRegisterActivity.SCHOOL_ID, schoolID);
        modelFragments.setRestroomBundle(null);
        modelFragments.setRestChildFragBundle(null);
        modelFragments.setCheckMirUrFrags(null);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentManager.popBackStack(InspectionActivity.REST_LIST, 0);
        RestroomFragment restroom = RestroomFragment.newInstance();
        restroom.setArguments(restroomDataBundle);
        Toast.makeText(getContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
        fragmentTransaction.replace(R.id.show_fragment_selected, restroom).addToBackStack(null).commit();
    }

    public RestroomMirrorEntry newMirror(Bundle bundle) {
        return new RestroomMirrorEntry(bundle.getInt(RestroomFragment.RESTROOM_ID), hasMirrors, bundle.getDouble(RestroomMirrorChildFragment.MIRROR_A),
                bundle.getDouble(RestroomMirrorChildFragment.MIRROR_B), bundle.getString(RestroomMirrorChildFragment.MIRROR_OBS));
    }

    public RestroomUrinalEntry newUrinal(Bundle bundle) {
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
        RestroomUrinalEntry newUrinal = newUrinal(bundle);
        if (restroomDataBundle.getInt(RestroomMirrorChildFragment.MIRROR_ID) > 0) {
            newMirror.setMirrorID(restroomDataBundle.getInt(RestroomMirrorChildFragment.MIRROR_ID));
            ViewModelEntry.updateRestroomMirrorEntry(newMirror);
        } else
            ViewModelEntry.insertRestroomMirrorEntry(newMirror);

        if (restroomDataBundle.getInt(RestroomUrinalChildFragment.URINAL_ID) > 0) {
            newUrinal.setUrinalID(restroomDataBundle.getInt(RestroomUrinalChildFragment.URINAL_ID));
            ViewModelEntry.updateRestroomUrinalEntry(newUrinal);
        } else
            ViewModelEntry.insertRestroomUrinalEntry(newUrinal);

        finishRestroomRegister();
    }


}