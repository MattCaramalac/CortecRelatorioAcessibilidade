package com.mpms.relatorioacessibilidadecortec.fragments.ParentFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.MainActivity;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.model.InspectionViewModel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.HeaderNames;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

public class InspectionMemorial extends Fragment implements TagInterface {

    OnFragmentInteractionListener listener;
    TextInputLayout dropdownMenuLocations;
    TextView registerHeader;
    AutoCompleteTextView listItemsMemorial;
    ArrayAdapter<String> adapterLocations;
    MaterialButton saveAndClose;

    ViewModelEntry modelEntry;
    InspectionViewModel dataView;

    Bundle fragInspection;

    SchoolEntry school;

    public InspectionMemorial() {
        // Required empty public constructor
    }

    public static InspectionMemorial newInstance() {
        return new InspectionMemorial();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            fragInspection = new Bundle(this.getArguments());
        else
            fragInspection = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_inspection_memorial, container, false);
        dropdownMenuLocations = rootView.findViewById(R.id.menu_options_memorial);
        listItemsMemorial = rootView.findViewById(R.id.list_item_memorial);
        registerHeader = rootView.findViewById(R.id.register_header);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instanceMemorial(view);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new ClassCastException(context.toString());
        }

        getParentFragmentManager().setFragmentResultListener(HEADER_TEXT, this, (key, bundle) -> {
            dataView.setHeaderTitle(setHeaderText(bundle));
            registerHeader.setText(setHeaderText(bundle));
        });
    }

    @Override
    public void onResume() {
        if (fragInspection.getBoolean(EXT_AREA_REG))
            adapterLocations = new ArrayAdapter<>(getContext(), R.layout.dropdown_list_memorial, HeaderNames.externalAreaOptions);
        else if (fragInspection.getBoolean(SUP_AREA_REG))
            adapterLocations = new ArrayAdapter<>(getContext(), R.layout.dropdown_list_memorial, HeaderNames.supportAreaOptions);
        else
            adapterLocations = new ArrayAdapter<>(getContext(), R.layout.dropdown_list_memorial, HeaderNames.blockOptions);
        listItemsMemorial.setAdapter(adapterLocations);
        listItemsMemorial.setOnItemClickListener((parent, view, position, id) -> listener.onDropdownChoice(position));

        dataView.getVisible().observe(getViewLifecycleOwner(), isVisible -> {
            if (!isVisible) {
                dropdownMenuLocations.setVisibility(View.GONE);
                saveAndClose.setVisibility(View.GONE);
            } else {
                dropdownMenuLocations.setVisibility(View.VISIBLE);
                saveAndClose.setVisibility(View.VISIBLE);
            }
        });

        dataView.getHeaderTitle().observe(getViewLifecycleOwner(), header -> {
            if (header != null && header.length() > 0)
                registerHeader.setText(header);
        });


        super.onResume();
    }

    private String setHeaderText(Bundle bundle) {
        int choice = bundle.getInt(CHOICE);
        if (bundle.getBoolean(CIRCULATION))
            return getString(R.string.header_circulation_register);
        else if (bundle.getBoolean(EXT_AREA_REG)) {
            switch (choice) {
                case 0:
                    return getString(R.string.label_external_access_header);
                case 1:
                    return getString(R.string.label_sidewalk_header);
                case 2:
                    return getString(R.string.ext_park_reg_header);
                case 3:
                    return getString(R.string.header_other_spaces);
            }
        } else if (bundle.getBoolean(SUP_AREA_REG)) {
            switch (choice) {
                case 0:
                    return getString(R.string.fountain_reg_header);
                case 1:
                    return getString(R.string.int_park_reg_header);
                case 2:
                    return getString(R.string.header_other_spaces);
                case 3:
                    return getString(R.string.header_playground_register);
                case 4:
                    return getString(R.string.restroom_reg_header);
                case 5:
                    return "Cadastro de Piscinas";
            }
        } else {
            switch (choice) {
                case 0:
                case 10:
                    return getString(R.string.restroom_reg_header);
                case 1:
                    return getString(R.string.fountain_reg_header);
                default:
                    return roomHeader(choice);
            }
        }
        return null;
    }

    private String roomHeader(int choice) {
        switch (choice) {
            case 2:
                return getString(R.string.header_library_register);
            case 3:
                return getString(R.string.header_coordination_register);
            case 4:
                return getString(R.string.header_directory_register);
            case 5:
                return getString(R.string.header_cafeteria_register);
            case 6:
                return getString(R.string.header_classroom_register);
            case 7:
                return getString(R.string.header_tech_room_register);
            case 8:
                return getString(R.string.header_resource_room_register);
            case 9:
                return getString(R.string.header_teacher_room_register);
            case 11:
                return getString(R.string.header_secretary_register);
            case 12:
                return getString(R.string.header_other_spaces);
            default:
                return "";
        }
    }

    private void instanceMemorial(View view) {
//        MaterialButton
        saveAndClose = view.findViewById(R.id.saveAndQuit);
        saveAndClose.setOnClickListener(v -> {
            fragInspection = null;
            Intent intent = new Intent(requireActivity(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        dataView = new ViewModelProvider(requireActivity()).get(InspectionViewModel.class);
    }

    public interface OnFragmentInteractionListener {
        void onDropdownChoice(int choice);
    }

}