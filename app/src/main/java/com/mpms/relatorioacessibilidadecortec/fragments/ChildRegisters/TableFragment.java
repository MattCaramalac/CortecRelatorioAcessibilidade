package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
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
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

public class TableFragment extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface {

    TextInputLayout supHeightField, infHeightField, tableWidthField, frontalApproxField, obsField, freeWidthField, tableDescField, photoField;
    TextInputEditText supHeightValue, infHeightValue, tableWidthValue, frontalApproxValue, obsValue, freeWidthValue, tableDescValue, photoValue;
    TextView tableTypeHeader, tableTypeError, tableSizeError;
    RadioGroup tableTypeRadio, tableSizeRadio;
    MaterialButton saveTable, cancelTable;

    Bundle tableBundle;

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
        if (getArguments() != null)
            tableBundle = new Bundle(this.getArguments());
        else
            tableBundle = new Bundle();
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
            modelEntry.getSpecificTable(tableBundle.getInt(TABLE_ID)).observe(getViewLifecycleOwner(), table -> loadTableData(table, tableBundle));

        saveTable.setOnClickListener(v -> {
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
            } else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        });

        cancelTable.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void instantiateTableViews(Bundle bundle, View view) {
//        TextInputLayout
        supHeightField = view.findViewById(R.id.table_superior_border_height_field);
        infHeightField = view.findViewById(R.id.table_inferior_border_height_field);
        tableWidthField = view.findViewById(R.id.table_width_field);
        frontalApproxField = view.findViewById(R.id.table_frontal_approx_field);
        obsField = view.findViewById(R.id.table_obs_field);
        freeWidthField = view.findViewById(R.id.table_fs_width_field);
        tableDescField = view.findViewById(R.id.table_desc_field);
        photoField = view.findViewById(R.id.table_photo_field);
//        TextInputEditText
        supHeightValue = view.findViewById(R.id.table_superior_border_height_value);
        infHeightValue = view.findViewById(R.id.table_inferior_border_height_value);
        tableWidthValue = view.findViewById(R.id.table_width_value);
        frontalApproxValue = view.findViewById(R.id.table_frontal_approx_value);
        obsValue = view.findViewById(R.id.table_obs_value);
        freeWidthValue = view.findViewById(R.id.table_fs_width_value);
        tableDescValue = view.findViewById(R.id.table_desc_value);
        photoValue = view.findViewById(R.id.table_photo_value);
//        TextView
        tableTypeHeader = view.findViewById(R.id.table_type_header_text);
        tableTypeError = view.findViewById(R.id.table_type_error);
        tableSizeError = view.findViewById(R.id.table_size_error);
//        RadioGroup
        tableTypeRadio = view.findViewById(R.id.table_type_radio);
        tableSizeRadio = view.findViewById(R.id.table_size_radio);
//        MaterialButton
        saveTable = view.findViewById(R.id.save_table);
        cancelTable = view.findViewById(R.id.cancel_table);
//        ViewModel
        modelEntry = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ViewModelEntry.class);
//        TableType Enabler
        classroomTableView(bundle);
        allowObsScroll(obsValue);
    }

    private void classroomTableView(Bundle bundle) {
        if (bundle.getInt(ROOM_TYPE) == 6) {
            tableTypeHeader.setVisibility(View.VISIBLE);
            tableTypeRadio.setVisibility(View.VISIBLE);
        }
    }

    private void loadTableData(TableEntry tableEntry, Bundle bundle) {
        if (bundle.getInt(ROOM_TYPE) == 6)
            checkRadioGroup(tableTypeRadio, tableEntry.getTableType());
        if (tableEntry.getTableDesc() != null)
            tableDescValue.setText(tableEntry.getTableDesc());
        checkRadioGroup(tableSizeRadio, tableEntry.getTableSize());
        supHeightValue.setText(String.valueOf(tableEntry.getSuperiorBorderHeight()));
        infHeightValue.setText(String.valueOf(tableEntry.getInferiorBorderHeight()));
        tableWidthValue.setText(String.valueOf(tableEntry.getTableWidth()));
        frontalApproxValue.setText(String.valueOf(tableEntry.getTableFrontalApprox()));
        freeWidthValue.setText(String.valueOf(tableEntry.getTableFreeWidth()));
        if (tableEntry.getTableObs() != null)
            obsValue.setText(tableEntry.getTableObs());
        if (tableEntry.getTablePhoto() != null)
            photoValue.setText(tableEntry.getTablePhoto());
    }

    private boolean tableNoEmptyFields(Bundle bundle) {
        clearTableEmptyFieldsErrors();
        int i = 0;
        if (bundle.getInt(ROOM_TYPE) == 6 && indexRadio(tableTypeRadio) == -1) {
            i++;
            tableTypeError.setVisibility(View.VISIBLE);
        }
        if (indexRadio(tableSizeRadio) == -1) {
            i++;
            tableSizeError.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(supHeightValue.getText())) {
            i++;
            supHeightField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(infHeightValue.getText())) {
            i++;
            infHeightField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(tableWidthValue.getText())) {
            i++;
            tableWidthField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(frontalApproxValue.getText())) {
            i++;
            frontalApproxField.setError(getString(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(freeWidthValue.getText())) {
            i++;
            freeWidthField.setError(getString(R.string.req_field_error));
        }

        return i == 0;
    }

    private void clearTableEmptyFieldsErrors() {
        tableTypeError.setVisibility(View.GONE);
        tableSizeError.setVisibility(View.GONE);
        supHeightField.setErrorEnabled(false);
        infHeightField.setErrorEnabled(false);
        tableWidthField.setErrorEnabled(false);
        frontalApproxField.setErrorEnabled(false);
        freeWidthField.setErrorEnabled(false);
    }

    private TableEntry newTableEntry(Bundle bundle) {
        Integer tableType = null, room = null, circ = null;
        int tableSize;
        double supHeight, infHeight, tableWidth, frontAprox, freeWidth;
        String tableObs = null, tableDesc = null, photo = null;

        if (bundle.getInt(AMBIENT_ID) > 0)
            room = bundle.getInt(AMBIENT_ID);
        else if (bundle.getInt(CIRC_ID) > 0)
            circ = bundle.getInt(CIRC_ID);

        if (bundle.getInt(ROOM_TYPE) == 6) {
            tableType = indexRadio(tableTypeRadio);
        }
        tableSize = indexRadio(tableSizeRadio);
        if (!TextUtils.isEmpty(tableDescValue.getText()))
            tableDesc = String.valueOf(tableDescValue.getText());
        supHeight = Double.parseDouble(String.valueOf(supHeightValue.getText()));
        infHeight = Double.parseDouble(String.valueOf(infHeightValue.getText()));
        tableWidth = Double.parseDouble(String.valueOf(tableWidthValue.getText()));
        frontAprox = Double.parseDouble(String.valueOf(frontalApproxValue.getText()));
        freeWidth = Double.parseDouble(String.valueOf(freeWidthValue.getText()));
        if (!TextUtils.isEmpty(obsValue.getText()))
            tableObs = String.valueOf(obsValue.getText());
        if (!TextUtils.isEmpty(photoValue.getText()))
            photo = String.valueOf(photoValue.getText());

        return new TableEntry(room, circ, bundle.getInt(ROOM_TYPE), tableType, supHeight, infHeight, tableWidth, frontAprox, tableObs, freeWidth, tableDesc, tableSize, photo);
    }

    private void clearTableFields() {
        tableDescValue.setText(null);
        tableTypeRadio.clearCheck();
        tableSizeRadio.clearCheck();
        supHeightValue.setText(null);
        infHeightValue.setText(null);
        tableWidthValue.setText(null);
        frontalApproxValue.setText(null);
        freeWidthValue.setText(null);
        obsValue.setText(null);
        photoValue.setText(null);
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
    }
}