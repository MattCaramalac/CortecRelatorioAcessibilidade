package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;


public class AddStairsMirrorDialog extends DialogFragment {

    TextInputLayout stairsMirrorHeightField;
    TextInputEditText stairsMirrorHeightValue;
    Button saveStairs, cancelStairs;
    Toolbar toolbar;

    FragmentManager manager;

    ViewModelEntry modelEntry;

    static Bundle stairsMirrorBundle = new Bundle();

    int mirrorMeasurements = 1;

    public static AddStairsMirrorDialog displayMirrorDialog(FragmentManager manager, Bundle bundle) {
        AddStairsMirrorDialog mirrorDialog = new AddStairsMirrorDialog();
        mirrorDialog.show(manager, "STAIRS_MIRROR_DIALOG");
        stairsMirrorBundle = bundle;
        return mirrorDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_stairs_mirror_dialog, container, false);
        toolbar = view.findViewById(R.id.stairs_mirror_toolbar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle("Cadastrar Espelhos do Lance");

        stairsMirrorHeightField = view.findViewById(R.id.stairs_mirror_size_field);
        stairsMirrorHeightValue = view.findViewById(R.id.stairs_mirror_size_value);

        saveStairs = view.findViewById(R.id.save_stairs_mirror);
        cancelStairs = view.findViewById(R.id.cancel_stairs_mirror);

        manager = getChildFragmentManager();

        modelEntry = new ViewModelEntry(requireActivity().getApplication());

        saveStairs.setOnClickListener(v -> {
            if(checkStairsMirrorEmptyField()) {

            }
        });

        cancelStairs.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .beginTransaction().remove(this).commit());
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int length = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width,length);
        }
    }

    private boolean checkStairsMirrorEmptyField() {
        clearStairsMirrorEmptyFieldError();
        int i = 0;
        if (TextUtils.isEmpty(stairsMirrorHeightValue.getText())){
            i++;
            stairsMirrorHeightField.setError(getString(R.string.blank_field_error));
        }
        return i == 0;
    }

    private void clearStairsMirrorEmptyFieldError() {
        stairsMirrorHeightField.setErrorEnabled(false);
    }

    private void clearStairsMirrorFields() {
        stairsMirrorHeightValue.setText(null);
    }
}