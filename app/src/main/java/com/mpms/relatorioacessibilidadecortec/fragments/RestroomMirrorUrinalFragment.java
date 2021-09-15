package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.RestroomMirrorChildFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.RestroomUrinalChildFragment;

public class RestroomMirrorUrinalFragment extends Fragment {

    public static final String OPENED_MIRROR = "OPENED_MIRROR";

    RadioGroup mirrorRadio, urinalRadio;
    Button returnSinkTwo, save;
    Bundle restroomDataBundle;
    FragmentManager manager;

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
        manager = getChildFragmentManager();
        return inflater.inflate(R.layout.fragment_restroom_mirror_urinal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mirrorRadio = view.findViewById(R.id.restroom_mirror_radio);
        urinalRadio = view.findViewById(R.id.restroom_urinal_radio);

        returnSinkTwo = view.findViewById(R.id.return_sink_two);
        save = view.findViewById(R.id.save_mirror_urinal);

        radioListener();

        save.setOnClickListener( v-> {
            //Inserir Gravação de Dados
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            RestroomFragment restroom = RestroomFragment.newInstance();
            restroom.setArguments(restroomDataBundle);
            fragmentTransaction.replace(R.id.show_fragment_selected, restroom).addToBackStack(null).commit();
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
}