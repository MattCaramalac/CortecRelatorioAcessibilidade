package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.ExpandImageDialog;
import com.mpms.relatorioacessibilidadecortec.R;

public class RestroomSink2Fragment extends Fragment {

    Button saveSinkTwo, returnSinkOne;
    ImageButton sinkThree, sinkFour, sinkFive;
    Bundle restroomDataBundle;

    public RestroomSink2Fragment() {
        // Required empty public constructor
    }

    public static RestroomSink2Fragment newInstance() {
        return new RestroomSink2Fragment();
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
        return inflater.inflate(R.layout.fragment_restroom_sink2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        saveSinkTwo = view.findViewById(R.id.save_sink_two);
        returnSinkOne = view.findViewById(R.id.return_sink_one);
        sinkThree = view.findViewById(R.id.sink_image_three);
        sinkFour = view.findViewById(R.id.sink_image_four);
        sinkFive = view.findViewById(R.id.sink_image_five);

        Glide.with(this).load(R.drawable.sink_3).fitCenter().into(sinkThree);
        Glide.with(this).load(R.drawable.sink_4).fitCenter().into(sinkFour);
        Glide.with(this).load(R.drawable.sink_5).fitCenter().into(sinkFive);

        sinkThree.setOnClickListener(v -> {
            restroomDataBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.sink_3);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), restroomDataBundle);
        });

        sinkFour.setOnClickListener(v -> {
            restroomDataBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.sink_4);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), restroomDataBundle);
        });

        sinkFive.setOnClickListener(v -> {
            restroomDataBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.sink_5);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), restroomDataBundle);
        });

        saveSinkTwo.setOnClickListener( v-> {
            //Inserir Gravação de Dados
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            RestroomMirrorUrinalFragment mirror = RestroomMirrorUrinalFragment.newInstance();
            mirror.setArguments(restroomDataBundle);
            fragmentTransaction.replace(R.id.show_fragment_selected, mirror).addToBackStack(null).commit();
        });

        returnSinkOne.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }
}