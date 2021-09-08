package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.entities.WindowEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class AddWindowDialog extends DialogFragment {

    TextInputLayout windowPlaceField, windowHeightField, windowObsField;
    TextInputEditText windowPlaceValue, windowHeightValue, windowObsValue;
    Button saveWindow, cancelWindow;
    Toolbar toolbar;

    String windowLocation, windowObs;
    Double windowSillHeight;

    static Bundle roomBundle;

    public static AddWindowDialog displayWindowDialog(FragmentManager fragmentManager, Bundle bundle) {
        AddWindowDialog windowDialog = new AddWindowDialog();
        windowDialog.show(fragmentManager, "WINDOW_DIALOG");
        roomBundle = bundle;
        return windowDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_window, container, false);
        toolbar = view.findViewById(R.id.window_toolbar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle(R.string.dialog_add_window_header);

        windowPlaceField = view.findViewById(R.id.window_placement_field);
        windowHeightField = view.findViewById(R.id.window_height_field);
        windowObsField = view.findViewById(R.id.window_obs_field);

        windowPlaceValue = view.findViewById(R.id.window_placement_value);
        windowHeightValue = view.findViewById(R.id.window_height_value);
        windowObsValue = view.findViewById(R.id.window_obs_value);

        saveWindow = view.findViewById(R.id.save_window);
        cancelWindow = view.findViewById(R.id.cancel_window);

        saveWindow.setOnClickListener(v -> {
            if(checkEmptyWindowFields()) {
                WindowEntry newWindow = newWindowEntry(roomBundle);
                ViewModelEntry.insertWindowEntry(newWindow);
                clearWindowsFields();
            }
        });

        cancelWindow.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
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

    public boolean checkEmptyWindowFields() {
        clearErrorsWindowFields();
        int error = 0;
        if (TextUtils.isEmpty(windowPlaceValue.getText())) {
            windowPlaceField.setError(getString(R.string.blank_field_error));
            error++;
        }
        if (TextUtils.isEmpty(windowHeightValue.getText())) {
            windowHeightField.setError(getString(R.string.blank_field_error));
            error++;
        }

        return error == 0;
    }

    public void clearErrorsWindowFields() {
        windowPlaceField.setErrorEnabled(false);
        windowHeightField.setErrorEnabled(false);

    }

    public WindowEntry newWindowEntry(Bundle bundle) {
        windowLocation = Objects.requireNonNull(windowPlaceValue.getText()).toString();
        windowSillHeight = Double.parseDouble(Objects.requireNonNull(windowHeightValue.getText()).toString());
        if (!TextUtils.isEmpty(windowObsValue.getText()))
            windowObs = Objects.requireNonNull(windowObsValue.getText()).toString();
        else
            windowObs = null;

        return new WindowEntry(bundle.getInt(RoomsRegisterFragment.SCHOOL_ID_VALUE),bundle.getInt(RoomsRegisterFragment.ROOM_ID_VALUE),
                windowLocation,windowSillHeight,windowObs);
    }

    public void clearWindowsFields() {
        windowPlaceValue.setText(null);
        windowHeightValue.setText(null);
        windowObsValue.setText(null);
    }
}