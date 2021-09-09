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

public class RestroomSupportBarFragment extends Fragment {

    Bundle restroomDataBundle;

    ImageButton supportBar;

    Button saveSupBar, returnUpView;


    public RestroomSupportBarFragment() {
        // Required empty public constructor
    }


    public static RestroomSupportBarFragment newInstance() {
        return new RestroomSupportBarFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restroomDataBundle = this.getArguments();
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

        saveSupBar = view.findViewById(R.id.save_sup_bar);
        returnUpView = view.findViewById(R.id.return_up_view);

        Glide.with(this).load(R.drawable.supporthandle2).centerCrop().into(supportBar);

        supportBar.setOnClickListener( v -> {
            restroomDataBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.supporthandle2);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), restroomDataBundle);
        });

        saveSupBar.setOnClickListener( v -> {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
           RestroomSink1Fragment sinkOneFragment = RestroomSink1Fragment.newInstance();
            sinkOneFragment.setArguments(restroomDataBundle);
            fragmentTransaction.replace(R.id.show_fragment_selected, sinkOneFragment).addToBackStack(null).commit();
        });

        returnUpView.setOnClickListener( v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }
}