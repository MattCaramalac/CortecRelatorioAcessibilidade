package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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

import java.util.ArrayList;

public class WindowFragment extends Fragment implements TagInterface, ScrollEditText {

    TextInputLayout winLocationField, comTypeField1, comTypeField2, comTypeField3, comHeightField1, comHeightField2, comHeightField3, windowObsField;
    TextInputEditText winLocationValue, comTypeValue1, comTypeValue2, comTypeValue3, comHeightValue1, comHeightValue2, comHeightValue3, windowObsValue;
    MaterialButton saveWindow, cancelWindow, addWindow;
    ImageButton delWindow;

    Bundle windowBundle;

    ViewModelEntry modelEntry;
    ArrayList<TextInputLayout> winTypeArray = new ArrayList<>();
    ArrayList<TextInputLayout> winHeightArray = new ArrayList<>();

    int windowQnt = 1;

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

    }

    private void instantiateWindowView(View view) {
//        TextInputLayout
        winLocationField = view.findViewById(R.id.win_location_field);
        comTypeField1 = view.findViewById(R.id.win_handle_id_1_field);
        comTypeField2 = view.findViewById(R.id.win_handle_id_2_field);
        comTypeField3 = view.findViewById(R.id.win_handle_id_3_field);
        comHeightField1 = view.findViewById(R.id.win_handle_height_field_1);
        comHeightField2 = view.findViewById(R.id.win_handle_height_field_2);
        comHeightField3 = view.findViewById(R.id.win_handle_height_field_3);
        windowObsField = view.findViewById(R.id.window_obs_field);
//        TextInputEditText
        winLocationValue = view.findViewById(R.id.win_location_value);
        comTypeValue1 = view.findViewById(R.id.win_handle_id_1_value);
        comTypeValue2 = view.findViewById(R.id.win_handle_id_2_value);
        comTypeValue3 = view.findViewById(R.id.win_handle_id_3_value);
        comHeightValue1 = view.findViewById(R.id.win_handle_height_value_1);
        comHeightValue2 = view.findViewById(R.id.win_handle_height_value_2);
        comHeightValue3 = view.findViewById(R.id.win_handle_height_value_3);
        windowObsValue = view.findViewById(R.id.window_obs_value);
//        MaterialButton
        saveWindow = view.findViewById(R.id.save_window);
        cancelWindow = view.findViewById(R.id.cancel_window);
        addWindow = view.findViewById(R.id.add_win_command_button);
//        ImageButton
        delWindow = view.findViewById(R.id.delete_win_command_button);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Listener
        saveWindow.setOnClickListener(this::buttonListener);
        cancelWindow.setOnClickListener(this::buttonListener);
        addWindow.setOnClickListener(this::buttonListener);
        delWindow.setOnClickListener(this::buttonListener);

        allowObsScroll(windowObsValue);
        viewsToArrays();
    }

    private void buttonListener(View view) {
        if (view == addWindow) {
            if (windowQnt < 1) {
                windowQnt = 1;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (windowQnt < 3) {
                if (windowQnt == 1)
                    delWindow.setVisibility(View.VISIBLE);
                winTypeArray.get(windowQnt).setVisibility(View.VISIBLE);
                winHeightArray.get(windowQnt).setVisibility(View.VISIBLE);
                windowQnt++;
            } else
                Toast.makeText(getContext(), getString(R.string.toast_max_measurements), Toast.LENGTH_SHORT).show();
        } else if (view == delWindow) {
            if (windowQnt > 1) {
                winTypeArray.get(windowQnt - 1).getEditText().setText(null);
                winTypeArray.get(windowQnt - 1).setVisibility(View.GONE);
                winHeightArray.get(windowQnt - 1).getEditText().setText(null);
                winHeightArray.get(windowQnt - 1).setVisibility(View.GONE);
                windowQnt--;
                if (windowQnt == 1)
                    delWindow.setVisibility(View.GONE);
            }
        } else if (view == saveWindow) {
            if (windowFieldsNotEmpty()) {
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
            } else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        } else
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
    }

    private void loadWindowData(WindowEntry window) {
        winLocationValue.setText(window.getWindowLocation());
        windowQnt = window.getWinQnt();
        switch (windowQnt) {
            case 3:
                comTypeField3.setVisibility(View.VISIBLE);
                comTypeValue3.setText(window.getComType3());
                comHeightField3.setVisibility(View.VISIBLE);
                comHeightValue3.setText(String.valueOf(window.getComHeight3()));
            case 2:
                comTypeField2.setVisibility(View.VISIBLE);
                comTypeValue2.setText(window.getComType2());
                comHeightField2.setVisibility(View.VISIBLE);
                comHeightValue2.setText(String.valueOf(window.getComHeight2()));
            case 1:
                comTypeField1.setVisibility(View.VISIBLE);
                comTypeValue1.setText(window.getComType1());
                comHeightField1.setVisibility(View.VISIBLE);
                comHeightValue1.setText(String.valueOf(window.getComHeight1()));
                break;
            default:
                break;
        }
        if (window.getWindowObs() != null) {
            windowObsValue.setText(window.getWindowObs());
        }
    }

    private boolean windowFieldsNotEmpty() {
        clearEmptyFieldsError();
        int i = 0;
        switch (windowQnt) {
            case 3:
                if (TextUtils.isEmpty(comHeightValue3.getText())) {
                    i++;
                    comHeightField3.setError(getString(R.string.req_field_error));
                    if (TextUtils.isEmpty(comTypeValue3.getText()))
                        comTypeField3.setError(getString(R.string.req_field_error));
                }
            case 2:
                if (TextUtils.isEmpty(comHeightValue2.getText())) {
                    i++;
                    comHeightField2.setError(getString(R.string.req_field_error));
                    if (TextUtils.isEmpty(comTypeValue2.getText()))
                        comTypeField2.setError(getString(R.string.req_field_error));
                }
            case 1:
                if (TextUtils.isEmpty(comHeightValue1.getText())) {
                    i++;
                    comHeightField1.setError(getString(R.string.req_field_error));
                    if (TextUtils.isEmpty(comTypeValue1.getText()))
                        comTypeField1.setError(getString(R.string.req_field_error));
                }
                break;
        }

        return i == 0;
    }

    private void clearEmptyFieldsError() {
        comHeightField3.setErrorEnabled(false);
        comTypeField3.setErrorEnabled(false);
        comHeightField2.setErrorEnabled(false);
        comTypeField2.setErrorEnabled(false);
        comHeightField1.setErrorEnabled(false);
        comTypeField1.setErrorEnabled(false);
    }

    private void clearWindowFields() {
        windowQnt = 1;
        clearEmptyFieldsError();
        winLocationValue.setText(null);
        comHeightValue3.setText(null);
        comTypeValue3.setText(null);
        comHeightField3.setVisibility(View.GONE);
        comTypeField3.setVisibility(View.GONE);
        comHeightValue2.setText(null);
        comTypeValue2.setText(null);
        comHeightField2.setVisibility(View.GONE);
        comTypeField2.setVisibility(View.GONE);
        comHeightValue1.setText(null);
        comTypeValue1.setText(null);
        windowObsValue.setText(null);
    }

    private WindowEntry newWindowEntry(Bundle bundle) {
        String winLocale, comType1 = null, comType2 = null, comType3 = null, winObs = null;
        Double winHeight1 = null, winHeight2 = null, winHeight3 = null;

        if (!TextUtils.isEmpty(winLocationValue.getText()))
            winLocale = String.valueOf(winLocationValue.getText());
        else
            winLocale = "Única";

        switch (windowQnt) {
            case 3:
                comType3 = String.valueOf(comTypeValue3.getText());
                winHeight3 = Double.parseDouble(String.valueOf(comHeightValue3.getText()));
            case 2:
                comType2 = String.valueOf(comTypeValue2.getText());
                winHeight2 = Double.parseDouble(String.valueOf(comHeightValue2.getText()));
            case 1:
                comType1 = String.valueOf(comTypeValue1.getText());
                winHeight1 = Double.parseDouble(String.valueOf(comHeightValue1.getText()));
                break;
            default:
                break;
        }

        if (windowObsValue.getText() != null)
            winObs = String.valueOf(windowObsValue.getText());

        return new WindowEntry(bundle.getInt(AMBIENT_ID), winLocale, windowQnt, comType1, winHeight1, comType2, winHeight2, comType3, winHeight3, winObs);
    }

    private void viewsToArrays() {
        winTypeArray.add(comTypeField1);
        winTypeArray.add(comTypeField2);
        winTypeArray.add(comTypeField3);

        winHeightArray.add(comHeightField1);
        winHeightArray.add(comHeightField2);
        winHeightArray.add(comHeightField3);
    }
}