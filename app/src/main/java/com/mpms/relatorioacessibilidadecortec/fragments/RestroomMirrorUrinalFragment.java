package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.ExpandImageDialog;
import com.mpms.relatorioacessibilidadecortec.R;

public class RestroomMirrorUrinalFragment extends Fragment {

    public static final String OPENED_MIRROR = "OPENED_MIRROR";

    ImageButton mirror, urinalOne, urinalTwo;
    Button returnSinkTwo, save;
    Bundle restroomDataBundle;

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
        return inflater.inflate(R.layout.fragment_restroom_mirror_urinal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mirror = view.findViewById(R.id.mirror_image);
        urinalOne = view.findViewById(R.id.urinal_image_one);
        urinalTwo = view.findViewById(R.id.urinal_image_two);

        returnSinkTwo = view.findViewById(R.id.return_sink_two);
        save = view.findViewById(R.id.save_mirror_urinal);

        Glide.with(this).load(R.drawable.mirror).fitCenter().into(mirror);
        Glide.with(this).load(R.drawable.urinal_1).fitCenter().into(urinalOne);
        Glide.with(this).load(R.drawable.urinal_2).fitCenter().into(urinalTwo);

        urinalOne.setOnClickListener(v -> {
            restroomDataBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.urinal_1);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), restroomDataBundle);
        });

        urinalTwo.setOnClickListener(v -> {
            restroomDataBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.urinal_2);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), restroomDataBundle);
        });

        mirror.setOnClickListener(v -> {
            restroomDataBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.mirror);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), restroomDataBundle);
        });

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
}