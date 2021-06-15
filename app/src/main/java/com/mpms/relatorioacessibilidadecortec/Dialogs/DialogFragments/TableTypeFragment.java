package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelDialog;

import java.util.Objects;


public class TableTypeFragment extends Fragment {

    RadioGroup tableType;

    ViewModelDialog modelDialog;

    Bundle tableBundle = new Bundle();

    public static final String TABLE_TYPE = "TABLE_TYPE";

    public TableTypeFragment() {
        // Required empty public constructor
    }

    public static TableTypeFragment newInstance() {
        return new TableTypeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_table_type, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tableType = view.findViewById(R.id.table_type_radio);

        modelDialog = new ViewModelProvider(requireActivity()).get(ViewModelDialog.class);

        modelDialog.getSaveTableAttempt().observe(getViewLifecycleOwner(), saveAttempt -> {
            if (Objects.equals(modelDialog.getSaveTableAttempt().getValue(), 1)) {
              if (checkEmptyRadioTable()) {
                  tableBundle.putInt(TABLE_TYPE, getCheckedTableRadio(tableType));
                  modelDialog.setTableInfo(tableBundle);
                  clearRadioTable();
              } else {
                  Toast.makeText(getContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
              }
            }
            modelDialog.setSaveDoorAttempt(0);
        });
    }

    public int getCheckedTableRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public boolean checkEmptyRadioTable(){
        int error= 0;
        if (getCheckedTableRadio(tableType) == -1)
            error++;
        return error == 0;
    }

    public void clearRadioTable(){
        tableType.clearCheck();
    }
}