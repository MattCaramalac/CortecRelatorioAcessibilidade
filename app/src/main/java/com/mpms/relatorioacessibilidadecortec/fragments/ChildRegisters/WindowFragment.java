package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.WindowEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

public class WindowFragment extends Fragment implements TagInterface, ScrollEditText {

    TextInputLayout windowLocationField, windowHeightField, windowObsField;
    TextInputEditText windowLocationValue, windowHeightValue, windowObsValue;
    TextView windowHeader;
    MaterialButton saveWindow, cancelWindow;

    Bundle windowBundle;

    ViewModelEntry modelEntry;

    public WindowFragment() {
        // Required empty public constructor
    }

    public static WindowFragment newInstance() {
        return new WindowFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            windowBundle = new Bundle(this.getArguments());
        else
            windowBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_window, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateWindowView(view);

        if (windowBundle.getInt(WINDOW_ID) > 0)
            modelEntry.selectSpecificWindow(windowBundle.getInt(WINDOW_ID)).observe(getViewLifecycleOwner(), this::loadWindowData);

        saveWindow.setOnClickListener(v -> {
            if(windowFieldsNotEmpty()) {
                WindowEntry newWindow = newWindowEntry(windowBundle);
                if (windowBundle.getInt(WINDOW_ID) > 0) {
                    newWindow.setWindowID(windowBundle.getInt(WINDOW_ID));
                    ViewModelEntry.updateWindowEntry(newWindow);
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStackImmediate();
                } else if (windowBundle.getInt(WINDOW_ID) == 0) {
                    ViewModelEntry.insertWindowEntry(newWindow);
                    clearWindowFields();
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                } else {
                    windowBundle.putInt(WINDOW_ID, 0);
                    Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                }
            }
            else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();

        });

        cancelWindow.setOnClickListener( v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void instantiateWindowView(View view) {
//        TextInputLayout
        windowLocationField = view.findViewById(R.id.window_placement_field);
        windowHeightField = view.findViewById(R.id.window_height_field);
        windowObsField = view.findViewById(R.id.window_obs_field);
//        TextInputEditText
        windowLocationValue = view.findViewById(R.id.window_placement_value);
        windowHeightValue = view.findViewById(R.id.window_height_value);
        windowObsValue = view.findViewById(R.id.window_obs_value);
//        TextView
        windowHeader = view.findViewById(R.id.window_register_header);
        windowHeader.setText(getText(R.string.header_window_register));
//        MaterialButton
        saveWindow = view.findViewById(R.id.save_window);
        cancelWindow = view.findViewById(R.id.cancel_window);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());

        allowObsScroll(windowObsValue);
    }

    private void loadWindowData(WindowEntry window) {
        windowLocationValue.setText(window.getWindowLocation());
        windowHeightValue.setText(String.valueOf(window.getWindowCommandHeight()));
        if (window.getWindowObs() != null) {
            windowObsValue.setText(window.getWindowObs());
        }
    }

    private boolean windowFieldsNotEmpty() {
        clearEmptyFieldsError();
        int i = 0;
        if (TextUtils.isEmpty(windowLocationValue.getText())) {
            i++;
            windowLocationField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(windowHeightValue.getText())) {
            i++;
            windowHeightField.setError(getString(R.string.req_field_error));
        }
        return i == 0;
    }

    private void clearEmptyFieldsError(){
        windowLocationField.setErrorEnabled(false);
        windowHeightField.setErrorEnabled(false);
    }

    private void clearWindowFields() {
        windowLocationValue.setText(null);
        windowHeightValue.setText(null);
        windowObsValue.setText(null);
    }

    private WindowEntry newWindowEntry(Bundle bundle) {
        String winLocale, winObs = null;
        double winHeight;

        winLocale = String.valueOf(windowLocationValue.getText());
        winHeight = Double.parseDouble(String.valueOf(windowHeightValue.getText()));
        if (windowObsValue.getText() != null)
            winObs = String.valueOf(windowObsValue.getText());

        return new WindowEntry(bundle.getInt(AMBIENT_ID), winLocale, winHeight, winObs);
    }
}