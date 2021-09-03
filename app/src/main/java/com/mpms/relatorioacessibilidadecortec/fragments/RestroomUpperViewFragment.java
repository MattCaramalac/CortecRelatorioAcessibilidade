package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mpms.relatorioacessibilidadecortec.R;

public class RestroomUpperViewFragment extends Fragment {

    ImageButton upperViewImgButton;
    ImageView resizedImg;

    public RestroomUpperViewFragment() {
        // Required empty public constructor
    }

    public static RestroomUpperViewFragment newInstance() {
        RestroomUpperViewFragment fragment = new RestroomUpperViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restroom_upper_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        upperViewImgButton = view.findViewById(R.id.rest_upper_view_image);
        resizedImg = view.findViewById(R.id.expanded_image_view);

        Glide.with(this).load(R.drawable.upperview).centerCrop().into(upperViewImgButton);

        upperViewImgButton.setOnClickListener(this::zoomImage);
    }

    private void zoomImage(View thumbView) {
        resizedImg.setVisibility(View.VISIBLE);
        Glide.with(this).load(R.drawable.upperview).fitCenter().into(resizedImg);
    }
}