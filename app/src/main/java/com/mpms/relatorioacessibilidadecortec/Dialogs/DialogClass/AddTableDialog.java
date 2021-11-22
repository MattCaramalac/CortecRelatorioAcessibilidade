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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogFragments.TableTypeFragment;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.SchoolRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.entities.TableEntry;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomRegisterListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomsRegisterFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelDialog;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.Objects;


public class AddTableDialog extends DialogFragment {

    Button saveTable, cancelTable;
    TextInputLayout highestBorderField, lowestBorderField, widthField, frontalApproxField, tableObsField;
    TextInputEditText highestBorderValue, lowestBorderValue, widthValue, frontalApproxValue, tableObsValue;
    Toolbar toolbar;

    Integer tableType;
    Double highestBorder, lowestBorder, widthTable, frontalApprox;
    String tableObs;

    static Bundle roomBundle;

    ViewModelDialog modelDialog;

    public static AddTableDialog addTableDialog(FragmentManager fragmentManager, Bundle bundle) {
        AddTableDialog tableDialog = new AddTableDialog();
        tableDialog.show(fragmentManager, "TABLE_DIALOG");
        roomBundle = bundle;
        return tableDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_table_dialog, container, false);
        toolbar = view.findViewById(R.id.table_toolbar);
        modelDialog = new ViewModelProvider(requireActivity()).get(ViewModelDialog.class);

        if (roomBundle.getInt(RoomRegisterListFragment.ROOM_TYPE) == 11) {
            getChildFragmentManager().beginTransaction().replace(R.id.table_type_child_fragment, new TableTypeFragment()).commit();
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar.setTitle("Adicionar Mesa");

        instantiateTableViews(view);
        allowTableObsScroll();

        modelDialog.getTableInfo().observe(getViewLifecycleOwner(), tableBundle -> {
            if (tableBundle!= null) {
                roomBundle.putInt(TableTypeFragment.TABLE_TYPE,tableBundle.getInt(TableTypeFragment.TABLE_TYPE));
                TableEntry newTabEntry = newTableEntry(roomBundle);
                ViewModelEntry.insertTablesEntry(newTabEntry);
                Toast.makeText(getContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                clearTableFields();
                modelDialog.setTableInfo(null);
            }
        });

        saveTable.setOnClickListener(v -> {
            if(checkEmptyTableFields()) {
                if (roomBundle.getInt(RoomRegisterListFragment.ROOM_TYPE) == 11) {
                    modelDialog.setSaveTableAttempt(1);
                } else {
                    TableEntry newTabEntry = newTableEntry(roomBundle);
                    ViewModelEntry.insertTablesEntry(newTabEntry);
                    clearTableFields();
                }
            } else {
                Toast.makeText(getContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            }
        });

        cancelTable.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .beginTransaction().remove(this).commit());
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int length = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width,length);
        }
    }

    private void instantiateTableViews(View view) {
        highestBorderField = view.findViewById(R.id.table_superior_border_height_field);
        lowestBorderField = view.findViewById(R.id.table_inferior_border_height_field);
        widthField = view.findViewById(R.id.table_width_field);
        frontalApproxField = view.findViewById(R.id.table_frontal_approx_field);
        tableObsField = view.findViewById(R.id.table_obs_field);

        highestBorderValue = view.findViewById(R.id.table_superior_border_height_value);
        lowestBorderValue = view.findViewById(R.id.table_inferior_border_height_value);
        widthValue = view.findViewById(R.id.table_width_value);
        frontalApproxValue = view.findViewById(R.id.table_frontal_approx_value);
        tableObsValue = view.findViewById(R.id.table_obs_value);

        saveTable = view.findViewById(R.id.save_table);
        cancelTable = view.findViewById(R.id.cancel_table);
    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowTableObsScroll() {
        tableObsValue.setOnTouchListener(this::scrollingField);
    }

    public boolean checkEmptyTableFields() {
        clearErrorsTableDialog();
        int error = 0;
        if (TextUtils.isEmpty(highestBorderValue.getText())) {
            highestBorderField.setError(getString(R.string.blank_field_error));
            error++;
        }
        if (TextUtils.isEmpty(lowestBorderValue.getText())) {
            lowestBorderField.setError(getString(R.string.blank_field_error));
            error++;
        }
        if (TextUtils.isEmpty(widthValue.getText())) {
            widthField.setError(getString(R.string.blank_field_error));
            error++;
        }
        if (TextUtils.isEmpty(frontalApproxValue.getText())) {
            frontalApproxField.setError(getString(R.string.blank_field_error));
            error++;
        }
        return error == 0;
    }

    public void clearErrorsTableDialog() {
        highestBorderField.setErrorEnabled(false);
        lowestBorderField.setErrorEnabled(false);
        widthField.setErrorEnabled(false);
        frontalApproxField.setErrorEnabled(false);

    }

    public TableEntry newTableEntry(Bundle bundle) {
        int isClassroom = 0;
        tableType = null;
        highestBorder = null;
        lowestBorder = null;
        widthTable = null;
        frontalApprox = null;
        tableObs = null;
        if (bundle.getInt(RoomRegisterListFragment.ROOM_TYPE) == 11) {
            tableType = bundle.getInt(TableTypeFragment.TABLE_TYPE);
            isClassroom = 1;
        }
        highestBorder = Double.parseDouble(Objects.requireNonNull(highestBorderValue.getText()).toString());
        lowestBorder = Double.parseDouble(Objects.requireNonNull(lowestBorderValue.getText()).toString());
        widthTable = Double.parseDouble(Objects.requireNonNull(widthValue.getText()).toString());
        frontalApprox = Double.parseDouble(Objects.requireNonNull(frontalApproxValue.getText()).toString());
        if (!TextUtils.isEmpty(tableObsValue.getText()))
            tableObs = Objects.requireNonNull(tableObsValue.getText()).toString();

        return new TableEntry(bundle.getInt(SchoolRegisterActivity.SCHOOL_ID),bundle.getInt(RoomsRegisterFragment.ROOM_ID_VALUE),
                isClassroom, tableType,lowestBorder, highestBorder, widthTable, frontalApprox, tableObs);
    }

    public void clearTableFields() {
        highestBorderValue.setText(null);
        lowestBorderValue.setText(null);
        widthValue.setText(null);
        frontalApproxValue.setText(null);
        tableObsValue.setText(null);
    }
}