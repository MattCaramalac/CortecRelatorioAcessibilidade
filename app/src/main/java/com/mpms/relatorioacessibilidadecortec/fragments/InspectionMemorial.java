package com.mpms.relatorioacessibilidadecortec.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.MainActivity;
import com.mpms.relatorioacessibilidadecortec.commService.DataSender;
import com.mpms.relatorioacessibilidadecortec.commService.JSONCreator;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.HeaderNames;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.json.JSONException;
import org.json.JSONObject;


public class InspectionMemorial extends Fragment implements TagInterface {

    OnFragmentInteractionListener listener;

    TextInputLayout dropdownMenuLocations;
    AutoCompleteTextView listItemsMemorial;
    ArrayAdapter<String> adapterLocations;
    int error = 0;


    MaterialButton saveAndClose;

    ViewModelEntry modelEntry;

    Bundle fragInspection;

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
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        saveAndClose = view.findViewById(R.id.saveAndQuit);

        saveAndClose.setOnClickListener(v -> {
            modelEntry.getEntry(fragInspection.getInt(SCHOOL_ID)).observe(getViewLifecycleOwner(), entry -> {
                JSONCreator creator = new JSONCreator();
                JSONObject postData = new JSONObject();

                creator.createJson(postData, entry);

                if (creator.error == 0) {
                    Log.i("POST", postData.toString());
                    new DataSender().execute(postData.toString());
                } else
                    Toast.makeText(getContext(), "Houve um Problema. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
            });

            Intent intent = new Intent(requireActivity(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new ClassCastException(context.toString());
        }

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

        listItemsMemorial.setOnItemClickListener((parent, view, position, id) -> {
//            chosenOption = position;
            listener.onDropdownChoice(position);
        });

        super.onResume();
    }

    public interface OnFragmentInteractionListener {
        void onDropdownChoice(int choice);
    }

    public JSONObject createJson() {
        JSONObject postData = new JSONObject();
        modelEntry.getEntry(fragInspection.getInt(SCHOOL_ID)).observe(getViewLifecycleOwner(), entry -> {
            try {
                postData.put("schoolNameCaps", entry.getSchoolName().toUpperCase());
                postData.put("cityNameCaps", entry.getNameCity().toUpperCase());
                postData.put("schoolName", entry.getSchoolName());
                postData.put("schoolAddress", entry.getSchoolAddress());
                postData.put("schoolNumber", entry.getAddressNumber());
                postData.put("schoolNeighbour", entry.getAddressNeighborhood());
                postData.put("schoolDistrict", entry.getNameDistrict());
                postData.put("cityName", entry.getNameCity());
                postData.put("visitDate", entry.getInitialDateInspection());
                postData.put("responsibleVisit", entry.getNameResponsibleVisit());
                postData.put("youngAge", String.valueOf(entry.getYoungestStudentAge()));
                postData.put("ageClassYoung", "(teste) anos");
                postData.put("oldestAge", String.valueOf(entry.getOldestStudentAge()));
                postData.put("ageClassOldest", "(teste) anos");
                postData.put("schoolServices", "(teste) EJA");
                postData.put("workingHours", "(teste) matutino");
                postData.put("numberStudents", String.valueOf(entry.getNumberStudents()));
                postData.put("numberDisabled", String.valueOf(entry.getNumberStudentsPCD()));
                postData.put("necessityDesc", entry.getWorkersPCDDescription());
                postData.put("numberWorkers", String.valueOf(entry.getNumberWorkers()));
                postData.put("librasWorkers", String.valueOf(entry.getNumberWorkersLibras()));
            } catch (JSONException e) {
                e.printStackTrace();
                error = 1;
            }
        });
        error = 0;
        return postData;
    }
}