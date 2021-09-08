package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.ExpandImageDialog;
import com.mpms.relatorioacessibilidadecortec.R;

public class RestroomSupportBarFragment extends Fragment {

    Bundle restroomData;

    ImageButton supportBar;


    public RestroomSupportBarFragment() {
        // Required empty public constructor
    }

    public static RestroomSupportBarFragment newInstance() {
        return new RestroomSupportBarFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restroomData = this.getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restroom_support_bar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        supportBar = view.findViewById(R.id.rest_support_bar_image);

        Glide.with(this).load(R.drawable.supporthandle2).centerCrop().into(supportBar);

        supportBar.setOnClickListener( v -> {
            restroomData.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.supporthandle2);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), restroomData);
        });
    }
}