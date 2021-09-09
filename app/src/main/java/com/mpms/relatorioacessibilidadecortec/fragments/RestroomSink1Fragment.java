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

import com.bumptech.glide.Glide;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.ExpandImageDialog;
import com.mpms.relatorioacessibilidadecortec.R;

public class RestroomSink1Fragment extends Fragment {

    ImageButton sinkOne, sinkTwo;
    Button returnSupBar, saveSink;

    Bundle restroomDataBundle;


    public RestroomSink1Fragment() {
        // Required empty public constructor
    }

    public static RestroomSink1Fragment newInstance() {
        return new RestroomSink1Fragment();
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
        return inflater.inflate(R.layout.fragment_restroom_sink1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sinkOne = view.findViewById(R.id.sink_image_one);
        sinkTwo = view.findViewById(R.id.sink_image_two);

        returnSupBar = view.findViewById(R.id.return_sup_bar);
        saveSink = view.findViewById(R.id.save_sink_one);

        Glide.with(this).load(R.drawable.sink_1).fitCenter().into(sinkOne);
        Glide.with(this).load(R.drawable.sink_2).fitCenter().into(sinkTwo);

        sinkOne.setOnClickListener(v -> {
            restroomDataBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.sink_1);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), restroomDataBundle);
        });

        sinkTwo.setOnClickListener(v -> {
            restroomDataBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.sink_2);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), restroomDataBundle);
        });
    }
}