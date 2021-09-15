package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.ExpandImageDialog;
import com.mpms.relatorioacessibilidadecortec.R;

public class RestroomUrinalChildFragment extends Fragment {

    ImageButton urinalOne, urinalTwo;
    Bundle imgBundleUrinal = new Bundle();

    public RestroomUrinalChildFragment() {
        // Required empty public constructor
    }

    public static RestroomUrinalChildFragment newInstance(String param1, String param2) {
        return new RestroomUrinalChildFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restroom_urinal_child, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        urinalOne = view.findViewById(R.id.urinal_image_one);
        urinalTwo = view.findViewById(R.id.urinal_image_two);

        Glide.with(this).load(R.drawable.urinal_1).fitCenter().into(urinalOne);
        Glide.with(this).load(R.drawable.urinal_2).fitCenter().into(urinalTwo);

        urinalOne.setOnClickListener(v -> {
            imgBundleUrinal.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.urinal_1);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundleUrinal);
        });

        urinalTwo.setOnClickListener(v -> {
            imgBundleUrinal.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.urinal_2);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundleUrinal);
        });
    }
}