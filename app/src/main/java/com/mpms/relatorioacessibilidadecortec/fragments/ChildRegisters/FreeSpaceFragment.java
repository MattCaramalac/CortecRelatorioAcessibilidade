package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.FreeSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

public class FreeSpaceFragment extends Fragment {

    public static final String FREE_SPACE_ID = "FREE_SPACE_ID";

    TextInputLayout fSpaceLocaleField, fSpaceWidthField, fSpaceObsField;
    TextInputEditText fSpaceLocaleValue, fSpaceWidthValue, fSpaceObsValue;
    MaterialButton saveFreeSp, cancelFreeSp;

    ViewModelEntry modelEntry;

    Bundle fSpaceBundle = new Bundle();

    public FreeSpaceFragment() {
        // Required empty public constructor
    }

    public static FreeSpaceFragment newInstance() {
        return new FreeSpaceFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fSpaceBundle.putInt(RoomsRegisterFragment.ROOM_ID, this.getArguments().getInt(RoomsRegisterFragment.ROOM_ID));
            fSpaceBundle.putInt(FREE_SPACE_ID, this.getArguments().getInt(FREE_SPACE_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_free_space, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateFreeSpViews(view);

        if (fSpaceBundle.getInt(FREE_SPACE_ID) > 0)
            modelEntry.selectSpecificFreeSpace(fSpaceBundle.getInt(FREE_SPACE_ID)).observe(getViewLifecycleOwner(), this::loadFreeSpData);

        saveFreeSp.setOnClickListener(v -> {
            if (fSpaceNoEmptyFields()) {
                FreeSpaceEntry newFreeSp = newFreeSpEntry(fSpaceBundle);
                if (fSpaceBundle.getInt(FREE_SPACE_ID) > 0) {
                    newFreeSp.setFreeSpaceID(fSpaceBundle.getInt(FREE_SPACE_ID));
                    ViewModelEntry.updateFreeSpace(newFreeSp);
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStackImmediate();
                } else if (fSpaceBundle.getInt(FREE_SPACE_ID) == 0) {
                    ViewModelEntry.insertFreeSpaceEntry(newFreeSp);
                    clearFreeSpFields();
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                } else {
                    fSpaceBundle.putInt(FREE_SPACE_ID, 0);
                    Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelFreeSp.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void instantiateFreeSpViews(View view) {
//        TextInputLayout
        fSpaceLocaleField = view.findViewById(R.id.free_space_placement_field);
        fSpaceWidthField = view.findViewById(R.id.free_space_width_field);
        fSpaceObsField = view.findViewById(R.id.free_space_obs_field);
//        TextInputEditText
        fSpaceLocaleValue = view.findViewById(R.id.free_space_placement_value);
        fSpaceWidthValue = view.findViewById(R.id.free_space_width_value);
        fSpaceObsValue = view.findViewById(R.id.free_space_obs_value);
//        MaterialButton
        saveFreeSp = view.findViewById(R.id.save_free_space);
        cancelFreeSp = view.findViewById(R.id.cancel_free_space);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private void loadFreeSpData(FreeSpaceEntry fSpace) {
        fSpaceLocaleValue.setText(fSpace.getFreeSpaceLocation());
        fSpaceWidthValue.setText(String.valueOf(fSpace.getFreeSpaceWidth()));
        if (fSpace.getFreeSpaceObs() != null)
            fSpaceObsValue.setText(fSpace.getFreeSpaceObs());
    }

    private void clearFreeSpEmptyFieldErrors() {
        fSpaceLocaleField.setErrorEnabled(false);
        fSpaceWidthField.setErrorEnabled(false);
    }

    private void clearFreeSpFields() {
        fSpaceLocaleValue.setText(null);
        fSpaceWidthValue.setText(null);
        fSpaceObsValue.setText(null);
    }

    private FreeSpaceEntry newFreeSpEntry(Bundle bundle) {
        String fSpLocale, fSpObs = null;
        double fSpWidth;

        fSpLocale = String.valueOf(fSpaceLocaleValue.getText());
        fSpWidth = Double.parseDouble(String.valueOf(fSpaceWidthValue.getText()));
        if (!TextUtils.isEmpty(fSpaceObsValue.getText()))
            fSpObs = String.valueOf(fSpaceObsValue.getText());

        return new FreeSpaceEntry(bundle.getInt(RoomsRegisterFragment.ROOM_ID), fSpLocale, fSpWidth, fSpObs);
    }

    private boolean fSpaceNoEmptyFields() {
        clearFreeSpEmptyFieldErrors();
        int i = 0;
        if (TextUtils.isEmpty(fSpaceLocaleValue.getText())) {
            i++;
            fSpaceLocaleField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(fSpaceWidthValue.getText())) {
            i++;
            fSpaceWidthField.setError(getString(R.string.blank_field_error));
        }

        return i == 0;
    }
}