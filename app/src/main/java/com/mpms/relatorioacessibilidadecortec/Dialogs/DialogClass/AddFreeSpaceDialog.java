package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.entities.FreeSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;

public class AddFreeSpaceDialog extends DialogFragment {

    TextInputLayout freeSpaceLocationField, freeSpaceWidthField, freeSpaceObsField;
    TextInputEditText freeSpaceLocationValue, freeSpaceWidthValue, freeSpaceObsValue;
    Button saveFreeSpace, cancelFreeSpace;
    Toolbar toolbar;

    String freeSpaceLocation, freeSpaceObs;
    Double freeSpaceWidth;

    static Bundle roomBundle;

    public static AddFreeSpaceDialog displayFreeSpaceDialog(FragmentManager fragmentManager, Bundle bundle) {
        AddFreeSpaceDialog freeSpaceDialog = new AddFreeSpaceDialog();
        freeSpaceDialog.show(fragmentManager, "FREE_SPACE_DIALOG");
        roomBundle = bundle;
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

        instantiateFreeSpaceViews(view);
        allowFreeSpaceObsScroll();

        saveFreeSpace.setOnClickListener(v -> {
            if (checkEmptyFreeSpaceFields()) {
                FreeSpaceEntry newFreeSpace = newFreeSpace(roomBundle);
                ViewModelEntry.insertFreeSpaceEntry(newFreeSpace);
                clearFreeSpaceFields();
            }
        });

        cancelFreeSpace.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
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

    private void instantiateFreeSpaceViews(View view) {
        freeSpaceLocationField = view.findViewById(R.id.free_space_placement_field);
        freeSpaceWidthField = view.findViewById(R.id.free_space_width_field);
        freeSpaceObsField = view.findViewById(R.id.free_space_obs_field);

        freeSpaceLocationValue = view.findViewById(R.id.free_space_placement_value);
        freeSpaceWidthValue = view.findViewById(R.id.free_space_width_value);
        freeSpaceObsValue = view.findViewById(R.id.free_space_obs_value);

        saveFreeSpace = view.findViewById(R.id.save_free_space);
        cancelFreeSpace = view.findViewById(R.id.cancel_free_space);
    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowFreeSpaceObsScroll() {
        freeSpaceObsValue.setOnTouchListener(this::scrollingField);
    }

    public boolean checkEmptyFreeSpaceFields() {
        clearErrorFreeSpaceFields();
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

    public void clearErrorFreeSpaceFields() {
        freeSpaceLocationField.setErrorEnabled(false);
        freeSpaceWidthField.setErrorEnabled(false);

    }

    public FreeSpaceEntry newFreeSpace(Bundle bundle) {
        freeSpaceLocation = Objects.requireNonNull(freeSpaceLocationValue.getText()).toString();
        freeSpaceWidth = Double.parseDouble(Objects.requireNonNull(freeSpaceWidthValue.getText()).toString());
        if (!TextUtils.isEmpty(freeSpaceObsValue.getText()))
            freeSpaceObs = Objects.requireNonNull(freeSpaceObsValue.getText()).toString();
        else
            freeSpaceObs = null;

        return new FreeSpaceEntry(bundle.getInt(InspectionActivity.SCHOOL_ID_VALUE), bundle.getInt(RoomsRegisterFragment.ROOM_ID_VALUE),
                freeSpaceLocation, freeSpaceWidth, freeSpaceObs);
    }

    public void clearFreeSpaceFields() {
        freeSpaceLocationValue.setText(null);
        freeSpaceWidthValue.setText(null);
        freeSpaceObsValue.setText(null);
    }
}