package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mpms.relatorioacessibilidadecortec.R;


public class ExpandImageDialog extends DialogFragment {

    public static final String IMAGE_ID = "IMAGE_ID";

    ImageView expandedImage;
    ImageButton closeDialog;

    Toolbar toolbar;
    static Bundle imgID;

    public static ExpandImageDialog expandImage(FragmentManager manager, Bundle bundle) {
        ExpandImageDialog expandImgDialog = new ExpandImageDialog();
        imgID = bundle;
        expandImgDialog.show(manager, "EXPAND_IMG_DIALOG");
        return expandImgDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rest_up_view_dialog, container, false);
        toolbar = view.findViewById(R.id.expand_image_toolbar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle("Imagem Expandida");

        expandedImage = view.findViewById(R.id.dialog_expanded_img);
        closeDialog = view.findViewById(R.id.closeDialog);

        Glide.with(this).load(imgID.getInt(IMAGE_ID)).fitCenter().into(expandedImage);

        closeDialog.setOnClickListener( v-> requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit());

    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.WRAP_CONTENT;
            int length = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width,length);
        }
    }
}