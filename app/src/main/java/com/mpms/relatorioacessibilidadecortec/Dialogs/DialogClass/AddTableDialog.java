package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogFragments.TableTypeFragment;
import com.mpms.relatorioacessibilidadecortec.R;

import java.util.Objects;


public class AddTableDialog extends DialogFragment {

    Button saveTable, cancelTable;
    TextInputLayout highestBorderField, lowestBorderField, widthField, frontalApproxField, tableObsField;
    TextInputEditText highestBorderValue, lowestBorderValue, widthValue, frontalApproxValue, tableObsValue;
    Toolbar toolbar;

    private static final String SCHOOL_ID_VALUE = "SCHOOL_ID_VALUE";
    private static final String ROOM_TYPE = "ROOM_TYPE";

    static int schoolID, roomType;

    public static AddTableDialog addTableDialog(FragmentManager fragmentManager, Bundle bundle) {
        AddTableDialog tableDialog = new AddTableDialog();
        tableDialog.show(fragmentManager, "TABLE_DIALOG");
        schoolID = bundle.getInt(SCHOOL_ID_VALUE);
        roomType = bundle.getInt(ROOM_TYPE);
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

        if (roomType == 11) {
            getChildFragmentManager().beginTransaction().replace(R.id.table_type_child_fragment, new TableTypeFragment()).commit();
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar.setTitle("Adicionar Mesa");

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

        saveTable.setOnClickListener(v -> checkEmptyTableFields());

        cancelTable.setOnClickListener(v -> Objects.requireNonNull(getActivity()).getSupportFragmentManager()
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
}