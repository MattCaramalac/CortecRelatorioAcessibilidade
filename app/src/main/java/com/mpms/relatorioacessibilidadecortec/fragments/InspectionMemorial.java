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
import com.mpms.relatorioacessibilidadecortec.data.entities.BlackboardEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.CounterEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorLockEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.data.entities.FreeSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.GateObsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PlaygroundEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SwitchEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.TableEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WindowEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.HeaderNames;
import com.mpms.relatorioacessibilidadecortec.util.IdListObservable;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;


public class InspectionMemorial extends Fragment implements TagInterface {

    OnFragmentInteractionListener listener;

    TextInputLayout dropdownMenuLocations;
    AutoCompleteTextView listItemsMemorial;
    ArrayAdapter<String> adapterLocations;

    SchoolEntry school;

    IdListObservable bID = new IdListObservable();
    IdListObservable roomID = new IdListObservable();
    IdListObservable extID = new IdListObservable();
    IdListObservable parkID = new IdListObservable();

    List<RoomEntry> roomList = new ArrayList<>();
    List<ExternalAccess> extList = new ArrayList<>();
    List<ParkingLotEntry> parkList = new ArrayList<>();
    List<PlaygroundEntry> playList = new ArrayList<>();
    List<RestroomEntry> restList = new ArrayList<>();
    List<SidewalkEntry> sideList = new ArrayList<>();
    List<WaterFountainEntry> fountList = new ArrayList<>();
    //    Ramp & Stairs Lists
    List<RampStairsEntry> roomStRaList = new ArrayList<>();
    List<RampStairsEntry> sideStRaList = new ArrayList<>();
    List<RampStairsEntry> extStRaList = new ArrayList<>();
    List<RampStairsEntry> parkStRaList = new ArrayList<>();
    //Door-related entities
    List<BlackboardEntry> boardList = new ArrayList<>();
    List<CounterEntry> counterList = new ArrayList<>();
    List<DoorEntry> doorList = new ArrayList<>();
    List<FreeSpaceEntry> freeList = new ArrayList<>();
    List<SwitchEntry> switchList = new ArrayList<>();
    List<TableEntry> tableList = new ArrayList<>();
    List<WindowEntry> windowList = new ArrayList<>();
    //    Gate & Door Entities
    List<DoorLockEntry> doorLockList = new ArrayList<>();
    List<DoorLockEntry> gateLockList = new ArrayList<>();
    //    Gate Entities
    List<GateObsEntry> gateList = new ArrayList<>();
    //    Gate & Sidewalk Entities
    List<PayPhoneEntry> extPhoneList = new ArrayList<>();
    List<PayPhoneEntry> sidePhoneList = new ArrayList<>();

    Observer blockIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllRoomsInSchool(idList).observe(getViewLifecycleOwner(), rList -> {
            roomList = rList;
            List<Integer> rIdList = new ArrayList<>();
            for (RoomEntry r : rList)
                rIdList.add(r.getRoomID());
            roomID.setIdList(rIdList);
        });
        ViewModelEntry.getAllExtAccess(idList).observe(getViewLifecycleOwner(), exList -> {
            extList = exList;
            List<Integer> exIdList = new ArrayList<>();
            for (ExternalAccess e : exList)
                exIdList.add(e.getExternalAccessID());
            extID.setIdList(exIdList);
        });
        ViewModelEntry.getAllParkingLots(idList).observe(getViewLifecycleOwner(), pList -> {
            parkList = pList;
            List<Integer> pIdList = new ArrayList<>();
            for (ParkingLotEntry p : pList)
                pIdList.add(p.getParkingID());
            parkID.setIdList(pIdList);
        });
        ViewModelEntry.getAllPlaygrounds(idList).observe(getViewLifecycleOwner(), pgList -> playList = pgList);
        ViewModelEntry.getAllRestEntries(idList).observe(getViewLifecycleOwner(), reList -> restList = reList);
        ViewModelEntry.getAllSidewalks(idList).observe(getViewLifecycleOwner(), sList -> sideList = sList);
        ViewModelEntry.getAllWaterFountains(idList).observe(getViewLifecycleOwner(), wList -> fountList = wList);
    };

    Observer roomIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllBlackboards(idList).observe(getViewLifecycleOwner(), bList -> boardList = bList);
        ViewModelEntry.getAllCounters(idList).observe(getViewLifecycleOwner(), cList -> counterList = cList);
        ViewModelEntry.getAllDoors(idList).observe(getViewLifecycleOwner(), dList -> doorList = dList); //TODO - Fazer um observador para as travas de porta
        ViewModelEntry.getAllFreeSpaces(idList).observe(getViewLifecycleOwner(), fList -> freeList = fList);
        ViewModelEntry.getAllRampStRoom(idList).observe(getViewLifecycleOwner(), rsList -> roomStRaList = rsList);
        ViewModelEntry.getAllSwitches(idList).observe(getViewLifecycleOwner(), sList -> switchList = sList);
        ViewModelEntry.getAllTables(idList).observe(getViewLifecycleOwner(), tList -> tableList = tList);
        ViewModelEntry.getAllWindows(idList).observe(getViewLifecycleOwner(), wList -> windowList = wList);
    };

    Observer extAccessIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllLocksFromGates(idList).observe(getViewLifecycleOwner(), dLoList -> gateLockList = dLoList);
        ViewModelEntry.getAllGateObs(idList).observe(getViewLifecycleOwner(), gList -> gateList = gList);
        ViewModelEntry.getAllPhonesExtAccess(idList).observe(getViewLifecycleOwner(), phList -> extPhoneList = phList);
        ViewModelEntry.getAllRampStExt(idList).observe(getViewLifecycleOwner(), rampList -> extStRaList = rampList); // TODO - Fazer um observador para os componentes da escada
    };

    Observer parkIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllLocksFromGates(idList).observe(getViewLifecycleOwner(), dLoList -> gateLockList = dLoList);
        ViewModelEntry.getAllGateObs(idList).observe(getViewLifecycleOwner(), gList -> gateList = gList);
        ViewModelEntry.getAllPhonesExtAccess(idList).observe(getViewLifecycleOwner(), phList -> extPhoneList = phList);
        ViewModelEntry.getAllRampStExt(idList).observe(getViewLifecycleOwner(), rampList -> extStRaList = rampList); // TODO - Fazer um observador para os componentes da escada
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

        bID.addObserver(blockIdList);
        roomID.addObserver(roomIdList);
        extID.addObserver(extAccessIdList);
//        parkID.addObserver();


        saveAndClose.setOnClickListener(v -> {
            JSONCreator creator = new JSONCreator();
            creator.createJsonInstance(school, roomList, extList, parkList, playList, roomStRaList, restList, sideList, fountList, boardList, counterList,
                    doorList, freeList, switchList, tableList, windowList);

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
}