package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;

import java.util.Objects;

public class AddFreeSpaceDialog extends DialogFragment {

    TextInputLayout freeSpaceLocationField, freeSpaceWidthField, freeSpaceObsField;
    TextInputEditText freeSpaceLocationValue, freeSpaceWidthValue, freeSpaceObsValue;
    Button saveFreeSpace, cancelFreeSpace;
    Toolbar toolbar;

    static int schoolID, roomType, roomID;

    public static AddFreeSpaceDialog displayFreeSpaceDialog(FragmentManager fragmentManager, Bundle bundle) {
        AddFreeSpaceDialog freeSpaceDialog = new AddFreeSpaceDialog();
        freeSpaceDialog.show(fragmentManager, "FREE_SPACE_DIALOG");
        schoolID = bundle.getInt(RoomsRegisterFragment.SCHOOL_ID_VALUE);
        roomType = bundle.getInt(RoomsRegisterFragment.ROOM_TYPE);
        roomID = bundle.getInt(RoomsRegisterFragment.ROOM_ID_VALUE);
        return freeSpaceDialog;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_free_space_dialog, container, false);
        toolbar = view.findViewById(R.id.free_space_toolbar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle(R.string.dialog_add_free_space_header);

        freeSpaceLocationField = view.findViewById(R.id.free_space_placement_field);
        freeSpaceWidthField = view.findViewById(R.id.free_space_width_field);
        freeSpaceObsField = view.findViewById(R.id.free_space_obs_field);

        freeSpaceLocationValue = view.findViewById(R.id.free_space_placement_value);
        freeSpaceWidthValue = view.findViewById(R.id.free_space_width_value);
        freeSpaceObsValue = view.findViewById(R.id.free_space_obs_value);

        saveFreeSpace = view.findViewById(R.id.save_free_space);
        cancelFreeSpace = view.findViewById(R.id.cancel_free_space);

        saveFreeSpace.setOnClickListener(v -> {checkEmptySwitchFields();});

        cancelFreeSpace.setOnClickListener(v -> Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction().remove(this).commit());
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int length = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width,length);
        }
    }

    public boolean checkEmptySwitchFields() {
        clearEmptyFreeSpaceFields();
        int error = 0;
        if (TextUtils.isEmpty(freeSpaceLocationValue.getText())) {
            freeSpaceLocationField.setError(getString(R.string.blank_field_error));
            error++;
        }
        if (TextUtils.isEmpty(freeSpaceWidthValue.getText())) {
            freeSpaceWidthField.setError(getString(R.string.blank_field_error));
            error++;
        }

        return error == 0;
    }

    public void clearEmptyFreeSpaceFields() {
        freeSpaceLocationField.setErrorEnabled(false);
        freeSpaceWidthField.setErrorEnabled(false);

    }
}