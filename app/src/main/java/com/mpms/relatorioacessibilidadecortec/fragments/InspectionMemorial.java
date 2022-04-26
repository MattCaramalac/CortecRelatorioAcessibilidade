package com.mpms.relatorioacessibilidadecortec.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.BlockRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.activities.MainActivity;
import com.mpms.relatorioacessibilidadecortec.activities.SchoolAreasRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.activities.SchoolRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.util.HeaderNames;


public class InspectionMemorial extends Fragment {

    OnFragmentInteractionListener listener;

    TextInputLayout dropdownMenuLocations;
    AutoCompleteTextView listItemsMemorial;
    ArrayAdapter<String> adapterLocations;

    MaterialButton saveAndClose;

    Bundle fragInspection = new Bundle();

    private static final int NO_CHOICE = -1;
    private int chosenOption = NO_CHOICE;

    public InspectionMemorial() {
        // Required empty public constructor
    }

    public static InspectionMemorial newInstance() {
        return new InspectionMemorial();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            fragInspection.putInt(SchoolRegisterActivity.SCHOOL_ID, this.getArguments().getInt(SchoolRegisterActivity.SCHOOL_ID));
            fragInspection.putInt(BlockRegisterActivity.BLOCK_ID, this.getArguments().getInt(BlockRegisterActivity.BLOCK_ID));
            fragInspection.putBoolean(SchoolAreasRegisterActivity.SUP_AREA_REG, this.getArguments().getBoolean(SchoolAreasRegisterActivity.SUP_AREA_REG, false));
            fragInspection.putBoolean(SchoolAreasRegisterActivity.EXT_AREA_REG, this.getArguments().getBoolean(SchoolAreasRegisterActivity.EXT_AREA_REG, false));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_inspection_memorial, container, false);
        dropdownMenuLocations = rootView.findViewById(R.id.menu_options_memorial);
        listItemsMemorial = rootView.findViewById(R.id.list_item_memorial);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        saveAndClose = view.findViewById(R.id.saveAndQuit);

        saveAndClose.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |  Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        }
        else {
            throw new ClassCastException(context.toString());
        }

    }

    @Override
    public void onResume() {
        if (fragInspection.getBoolean(SchoolAreasRegisterActivity.EXT_AREA_REG))
            adapterLocations = new ArrayAdapter<>(getContext(), R.layout.dropdown_list_memorial, HeaderNames.externalAreaOptions);
        else if (fragInspection.getBoolean(SchoolAreasRegisterActivity.SUP_AREA_REG))
            adapterLocations = new ArrayAdapter<>(getContext(), R.layout.dropdown_list_memorial, HeaderNames.supportAreaOptions);
        else
            adapterLocations = new ArrayAdapter<>(getContext(), R.layout.dropdown_list_memorial, HeaderNames.blockOptions);
        listItemsMemorial.setAdapter(adapterLocations);

        listItemsMemorial.setOnItemClickListener((parent, view, position, id) -> {
            chosenOption = position;
            listener.onDropdownChoice(position);
        });

        super.onResume();
    }

    public interface OnFragmentInteractionListener {
        void onDropdownChoice (int choice);
    }
}