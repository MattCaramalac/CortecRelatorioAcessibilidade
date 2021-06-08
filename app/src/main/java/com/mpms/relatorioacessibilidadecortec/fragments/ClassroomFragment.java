package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;

import java.util.Objects;

public class ClassroomFragment extends Fragment {

    public static final String BOARD_HEIGHT = "BOARD_HEIGHT";
    ViewModelFragments modelFragments;
    TextInputLayout boardHeightField;
    TextInputEditText boardHeightValue;

    public ClassroomFragment() {
        // Required empty public constructor
    }

    public static ClassroomFragment newInstance() {
        return new ClassroomFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_classroom, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        boardHeightField = view.findViewById(R.id.blackboard_height_field);
        boardHeightValue = view.findViewById(R.id.blackboard_height_value);

        modelFragments.getSaveAttemptRoom().observe(getViewLifecycleOwner(), saveAttempt -> {
            if (saveAttempt == 1) {
                if (checkEmptyClassFields()) {
                    Bundle classBundle = new Bundle();
                    classBundle.putDouble(BOARD_HEIGHT, Double.parseDouble(Objects.requireNonNull(boardHeightValue.getText()).toString()));
                    modelFragments.setRoomBundle(classBundle);
                    clearClassroomField();
                } else
                    Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();

                modelFragments.setSaveAttemptRooms(0);
            }
        });
    }

    public boolean checkEmptyClassFields() {
        clearFieldErrors();
        int error = 0;
        if (TextUtils.isEmpty(boardHeightValue.getText())) {
            boardHeightField.setError(getString(R.string.blank_field_error));
            error++;
        }
        return error == 0;
    }

    public void clearFieldErrors() {
        boardHeightField.setErrorEnabled(false);
    }

    public void clearClassroomField() {
        boardHeightValue.setText(null);
    }
}