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
import com.mpms.relatorioacessibilidadecortec.data.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.HeaderNames;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class InspectionMemorial extends Fragment implements TagInterface {

    OnFragmentInteractionListener listener;

    TextInputLayout dropdownMenuLocations;
    AutoCompleteTextView listItemsMemorial;
    ArrayAdapter<String> adapterLocations;

    SchoolEntry school;

    BlockObservable bID = new BlockObservable();
    List<RoomEntry> roomList = new ArrayList<>();

    Observer idListObs = (o, arg) -> {
        BlockObservable blockList = (BlockObservable) o;
        ViewModelEntry.getAllRoomsInSchool(blockList.getIdList()).observe(getViewLifecycleOwner(), rList -> roomList = rList);
    };

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

        modelEntry.getEntry(fragInspection.getInt(SCHOOL_ID)).observe(getViewLifecycleOwner(), entry -> school = entry);

        modelEntry.getAllBlockIds(fragInspection.getInt(SCHOOL_ID)).observe(getViewLifecycleOwner(), bList -> bID.setIdList(bList));

        bID.addObserver(idListObs);


        saveAndClose.setOnClickListener(v -> {
            JSONCreator creator = new JSONCreator();
            creator.createJsonInstance(school, roomList);

            creator.createJson();

            if (creator.error == 0) {
                Log.i("POST", creator.jObject.toString());
                new DataSender().execute(creator.jObject.toString());
            } else
                Toast.makeText(getContext(), "Houve um Problema. Por favor, tente novamente", Toast.LENGTH_SHORT).show();

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

        listItemsMemorial.setOnItemClickListener((parent, view, position, id) -> listener.onDropdownChoice(position));

        super.onResume();
    }

    public interface OnFragmentInteractionListener {
        void onDropdownChoice(int choice);
    }


    public static class BlockObservable extends Observable {
        private List<Integer> idList;

        public List<Integer> getIdList() {
            return idList;
        }

        public void setIdList(List<Integer> idList) {
            this.idList = idList;
            setChanged();
            notifyObservers();
        }

    }
}