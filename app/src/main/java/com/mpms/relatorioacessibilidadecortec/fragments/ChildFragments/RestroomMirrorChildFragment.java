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


public class RestroomMirrorChildFragment extends Fragment {

    ImageButton mirror;
    Bundle imgBundleMirror = new Bundle();

    public RestroomMirrorChildFragment() {
        // Required empty public constructor
    }

    public static RestroomMirrorChildFragment newInstance() {
        return new RestroomMirrorChildFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restroom_mirror_child, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mirror = view.findViewById(R.id.mirror_image);

        Glide.with(this).load(R.drawable.mirror).fitCenter().into(mirror);

        mirror.setOnClickListener(v -> {
            imgBundleMirror.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.mirror);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundleMirror);
        });
    }
}