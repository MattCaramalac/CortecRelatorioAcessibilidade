package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.TableEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomRegisterListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

public class TableFragment extends Fragment {

    public static final String TABLE_ID = "TABLE_ID";

    TextInputLayout supHeightField, infHeightField, tableWidthField, frontalAproxField, obsField;
    TextInputEditText supHeightValue, infHeightValue, tableWidthValue, frontalAproxValue, obsValue;
    TextView tableRegisterHeader, tableTypeHeader, tableTypeError;
    RadioGroup tableTypeRadio;
    MaterialButton saveTable, cancelTable;

    Bundle tableBundle = new Bundle();

    ViewModelEntry modelEntry;

    public TableFragment() {
        // Required empty public constructor
    }

    public static TableFragment newInstance() {
        return new TableFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tableBundle.putInt(RoomsRegisterFragment.ROOM_ID, this.getArguments().getInt(RoomsRegisterFragment.ROOM_ID));
            tableBundle.putInt(RoomRegisterListFragment.ROOM_TYPE, this.getArguments().getInt(RoomRegisterListFragment.ROOM_TYPE));
            tableBundle.putInt(TABLE_ID, this.getArguments().getInt(TABLE_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_table, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateTableViews(tableBundle, view);

        if (tableBundle.getInt(TABLE_ID) > 0)
            modelEntry.selectSpecificTable(tableBundle.getInt(TABLE_ID)).observe(getViewLifecycleOwner(), table -> loadTableData(table, tableBundle));

        saveTable.setOnClickListener(v-> {
            if (tableNoEmptyFields(tableBundle)) {
                TableEntry newTable = newTableEntry(tableBundle);
                if (tableBundle.getInt(TABLE_ID) > 0) {
                    newTable.setTableID(tableBundle.getInt(TABLE_ID));
                    ViewModelEntry.updateTable(newTable);
                    Toast.makeText(getContext(), getString(R.string.register_updated_message), Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStackImmediate();
                } else if (tableBundle.getInt(TABLE_ID) == 0) {
                    ViewModelEntry.insertTablesEntry(newTable);
                    clearTableFields();
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                } else {
                    tableBundle.putInt(TABLE_ID, 0);
                    Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                }
            }

        });

        cancelTable.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void instantiateTableViews(Bundle bundle, View view) {
//        TextInputLayout
        supHeightField = view.findViewById(R.id.table_superior_border_height_field);
        infHeightField = view.findViewById(R.id.table_inferior_border_height_field);
        tableWidthField = view.findViewById(R.id.table_width_field);
        frontalAproxField = view.findViewById(R.id.table_frontal_approx_field);
        obsField = view.findViewById(R.id.table_obs_field);
//        TextInputEditText
        supHeightValue = view.findViewById(R.id.table_superior_border_height_value);
        infHeightValue = view.findViewById(R.id.table_inferior_border_height_value);
        tableWidthValue = view.findViewById(R.id.table_width_value);
        frontalAproxValue = view.findViewById(R.id.table_frontal_approx_value);
        obsValue = view.findViewById(R.id.table_obs_value);
//        TextView
        tableRegisterHeader = view.findViewById(R.id.table_register_header);
        tableRegisterHeader.setText(getString(R.string.header_table_register));
        tableTypeHeader = view.findViewById(R.id.table_type_header_text);
        tableTypeError = view.findViewById(R.id.table_type_error);
//        RadioGroup
        tableTypeRadio = view.findViewById(R.id.table_type_radio);
//        MaterialButton
        saveTable = view.findViewById(R.id.save_table);
        cancelTable = view.findViewById(R.id.cancel_table);
//        ViewModel
        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);
//        TableType Enabler
        classroomTableView(bundle);
        allowSwitchScrollFields();
    }

    private void classroomTableView(Bundle bundle) {
        if (bundle.getInt(RoomRegisterListFragment.ROOM_TYPE) == 11) {
            tableRegisterHeader.setVisibility(View.VISIBLE);
            tableTypeRadio.setVisibility(View.VISIBLE);
        }
    }

    private void loadTableData(TableEntry tableEntry, Bundle bundle) {
        if (bundle.getInt(RoomRegisterListFragment.ROOM_TYPE) == 11)
            tableTypeRadio.check(tableTypeRadio.getChildAt(tableEntry.getTableType()).getId());
        supHeightValue.setText(String.valueOf(tableEntry.getSuperiorBorderHeight()));
        infHeightValue.setText(String.valueOf(tableEntry.getInferiorBorderHeight()));
        tableWidthValue.setText(String.valueOf(tableEntry.getTableWidth()));
        frontalAproxValue.setText(String.valueOf(tableEntry.getTableWidth()));
        if (tableEntry.getTableObs() != null)
            obsValue.setText(tableEntry.getTableObs());
    }

    private int getRadioCheckedButton(RadioGroup radio) {
        return radio.indexOfChild(radio.getChildAt(radio.getCheckedRadioButtonId()));
    }

    private boolean tableNoEmptyFields(Bundle bundle) {
        clearTableEmptyFieldsErrors();
        int i = 0;
        if (bundle.getInt(RoomRegisterListFragment.ROOM_TYPE) == 11) {
            if (getRadioCheckedButton(tableTypeRadio) == -1) {
                i++;
                tableTypeError.setVisibility(View.VISIBLE);
            }
        }
        if (TextUtils.isEmpty(supHeightValue.getText())) {
            i++;
            supHeightField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(infHeightValue.getText())) {
            i++;
            infHeightField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(tableWidthValue.getText())) {
            i++;
            tableWidthField.setError(getString(R.string.blank_field_error));
        }
        if (TextUtils.isEmpty(frontalAproxValue.getText())) {
            i++;
            frontalAproxField.setError(getString(R.string.blank_field_error));
        }

        return i == 0;
    }

    private void clearTableEmptyFieldsErrors() {
        tableTypeError.setVisibility(View.GONE);
        supHeightField.setErrorEnabled(false);
        infHeightField.setErrorEnabled(false);
        tableWidthField.setErrorEnabled(false);
        frontalAproxField.setErrorEnabled(false);
    }

    private TableEntry newTableEntry(Bundle bundle) {
        Integer tableType = null;
        double supHeight, infHeight, tableWidth, frontAprox;
        String tableObs = null;

        if (bundle.getInt(RoomRegisterListFragment.ROOM_TYPE) == 11) {
            tableType = getRadioCheckedButton(tableTypeRadio);
        }
        supHeight = Double.parseDouble(String.valueOf(supHeightValue.getText()));
        infHeight = Double.parseDouble(String.valueOf(infHeightValue.getText()));
        tableWidth = Double.parseDouble(String.valueOf(tableWidthValue.getText()));
        frontAprox = Double.parseDouble(String.valueOf(frontalAproxValue.getText()));
        if (!TextUtils.isEmpty(obsValue.getText()))
            tableObs = String.valueOf(obsValue.getText());

        return new TableEntry(bundle.getInt(RoomsRegisterFragment.ROOM_ID), bundle.getInt(RoomRegisterListFragment.ROOM_TYPE), tableType, supHeight, infHeight,
                tableWidth, frontAprox, tableObs);
    }

    private void clearTableFields() {
        tableTypeRadio.clearCheck();
        supHeightValue.setText(null);
        infHeightValue.setText(null);
        tableWidthValue.setText(null);
        frontalAproxValue.setText(null);
        obsValue.setText(null);
    }



    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowSwitchScrollFields() {
        obsValue.setOnTouchListener(this::scrollingField);
    }
}