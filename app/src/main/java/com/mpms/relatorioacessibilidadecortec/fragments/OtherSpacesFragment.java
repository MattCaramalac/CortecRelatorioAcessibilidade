package com.mpms.relatorioacessibilidadecortec.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;

import java.util.Objects;

public class OtherSpacesFragment extends Fragment {

    TextInputEditText otherSpacesDescriptionValue;
    TextInputLayout otherSpacesDescriptionField;
    Button saveOtherSpaces, cancelOtherSpaces;
    RadioGroup isAccessible;

    public OtherSpacesFragment() {
        // Required empty public constructor
    }

    public static OtherSpacesFragment newInstance() {
        return new OtherSpacesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_other_spaces, container, false);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        otherSpacesDescriptionValue = view.findViewById(R.id.other_spaces_description_value);

        otherSpacesDescriptionField = view.findViewById(R.id.other_spaces_description_field);

        saveOtherSpaces = view.findViewById(R.id.save_other_spaces_button);

        cancelOtherSpaces = view.findViewById(R.id.cancel_other_spaces_button);

        isAccessible = view.findViewById(R.id.accordance_with_accessibility_radio);

        otherSpacesDescriptionValue.setOnTouchListener((v, event) -> {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
                v.getParent().requestDisallowInterceptTouchEvent(false);
            }
            return false;
        });

        cancelOtherSpaces.setOnClickListener(v -> Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction().remove(this).commit());

    }
}