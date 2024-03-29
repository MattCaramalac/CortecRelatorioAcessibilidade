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
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

public class FreeSpaceFragment extends Fragment implements TagInterface, ScrollEditText {

    TextInputLayout fSpaceLocaleField, fSpaceWidthField, fSpaceObsField, obsWidthField, photoField;
    TextInputEditText fSpaceLocaleValue, fSpaceWidthValue, fSpaceObsValue, obsWidthValue, photoValue;
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
        if (getArguments() != null)
            fSpaceBundle = new Bundle(getArguments());
        else
            fSpaceBundle = new Bundle();
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
            modelEntry.getSpecificFreeSpace(fSpaceBundle.getInt(FREE_SPACE_ID)).observe(getViewLifecycleOwner(), this::loadFreeSpData);

        saveFreeSp.setOnClickListener(v -> {
            if (fSpaceNoEmptyFields()) {
                FreeSpaceEntry newFreeSp = newFreeSpEntry(fSpaceBundle);
                if (fSpaceBundle.getInt(FREE_SPACE_ID) > 0) {
                    newFreeSp.setFrSpaceID(fSpaceBundle.getInt(FREE_SPACE_ID));
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
            else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });

        cancelFreeSp.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void instantiateFreeSpViews(View view) {
//        TextInputLayout
        fSpaceLocaleField = view.findViewById(R.id.free_space_placement_field);
        fSpaceWidthField = view.findViewById(R.id.free_space_width_field);
        fSpaceObsField = view.findViewById(R.id.free_space_obs_field);
        obsWidthField = view.findViewById(R.id.obstacle_width_field);
        photoField = view.findViewById(R.id.free_space_photo_field);
//        TextInputEditText
        fSpaceLocaleValue = view.findViewById(R.id.free_space_placement_value);
        fSpaceWidthValue = view.findViewById(R.id.free_space_width_value);
        fSpaceObsValue = view.findViewById(R.id.free_space_obs_value);
        obsWidthValue = view.findViewById(R.id.obstacle_width_value);
        photoValue = view.findViewById(R.id.free_space_photo_value);
//        MaterialButton
        saveFreeSp = view.findViewById(R.id.save_free_space);
        cancelFreeSp = view.findViewById(R.id.cancel_free_space);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        allowObsScroll(fSpaceObsValue);
    }

    private void loadFreeSpData(FreeSpaceEntry fSpace) {
        if (fSpace.getFrSpaceLocation() != null)
            fSpaceLocaleValue.setText(fSpace.getFrSpaceLocation());
        if (fSpace.getObstacleWidth() != null)
            obsWidthValue.setText(String.valueOf(fSpace.getObstacleWidth()));
        if (fSpace.getFrSpaceWidth() != null)
            fSpaceWidthValue.setText(String.valueOf(fSpace.getFrSpaceWidth()));
        if (fSpace.getFrSpaceObs() != null)
            fSpaceObsValue.setText(fSpace.getFrSpaceObs());
        if (fSpace.getFrSpacePhoto() != null)
            photoValue.setText(fSpace.getFrSpacePhoto());
    }

    private void clearFreeSpEmptyFieldErrors() {
        fSpaceLocaleField.setErrorEnabled(false);
        obsWidthField.setErrorEnabled(false);
        fSpaceWidthField.setErrorEnabled(false);
    }

    private void clearFreeSpFields() {
        fSpaceLocaleValue.setText(null);
        fSpaceWidthValue.setText(null);
        obsWidthValue.setText(null);
        fSpaceObsValue.setText(null);
        photoValue.setText(null);
    }

    private FreeSpaceEntry newFreeSpEntry(Bundle bundle) {
        String fSpLocale, fSpObs = null, photo = null;
        double fSpWidth, obsWidth;
        Integer roomID = null, restID = null, circID = null;

        if (bundle.getInt(AMBIENT_ID) != 0)
            roomID = bundle.getInt(AMBIENT_ID);
        else if (bundle.getInt(REST_ID) != 0)
            restID = bundle.getInt(REST_ID);
        else if (bundle.getInt(CIRC_ID) != 0)
            circID = bundle.getInt(CIRC_ID);

        fSpLocale = String.valueOf(fSpaceLocaleValue.getText());
        fSpWidth = Double.parseDouble(String.valueOf(fSpaceWidthValue.getText()));
        obsWidth = Double.parseDouble(String.valueOf(obsWidthValue.getText()));
        if (!TextUtils.isEmpty(fSpaceObsValue.getText()))
            fSpObs = String.valueOf(fSpaceObsValue.getText());
        if (!TextUtils.isEmpty(photoValue.getText()))
            photo = String.valueOf(photoValue.getText());

        return new FreeSpaceEntry(roomID, restID, circID, fSpLocale, obsWidth, fSpWidth, fSpObs, photo);
    }

    private boolean fSpaceNoEmptyFields() {
        clearFreeSpEmptyFieldErrors();
        int i = 0;
        if (TextUtils.isEmpty(fSpaceLocaleValue.getText())) {
            i++;
            fSpaceLocaleField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(obsWidthValue.getText())) {
            i++;
            obsWidthField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(fSpaceWidthValue.getText())) {
            i++;
            fSpaceWidthField.setError(getString(R.string.req_field_error));
        }

        return i == 0;
    }
}