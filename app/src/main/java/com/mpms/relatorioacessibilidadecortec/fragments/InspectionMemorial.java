package com.mpms.relatorioacessibilidadecortec.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.InspectionActivity;
import com.mpms.relatorioacessibilidadecortec.commService.JsonCreation;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlackboardEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlockSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.CirculationEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.CounterEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorLockEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.EquipmentEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.data.entities.FallProtectionEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.FreeSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.GateObsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotElderlyEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotPCDEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PlaygroundEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsFlightEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsHandrailEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsRailingEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkSlopeEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SingleStepEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SlopeEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SwitchEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.TableEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WindowEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.HeaderNames;
import com.mpms.relatorioacessibilidadecortec.util.IdListObservable;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;

public class InspectionMemorial extends Fragment implements TagInterface {

    static int blockQnt = 0;
    static boolean hasHelpSpace = false;
    static int intParkQnt = 0;
    static int extParkQnt = 0;
    static int blockCounter = 0;

    OnFragmentInteractionListener listener;
    TextInputLayout dropdownMenuLocations;
    TextView registerHeader;
    AutoCompleteTextView listItemsMemorial;
    ArrayAdapter<String> adapterLocations;
    HashMap<String, String> tData;
    MaterialButton saveAndClose;

    ViewModelEntry modelEntry;

    Bundle fragInspection;

    SchoolEntry school;

    List<Integer> blockListId = new ArrayList<>();

    IdListObservable circID = new IdListObservable();
    IdListObservable bID = new IdListObservable();
    IdListObservable roomID = new IdListObservable();
    IdListObservable extID = new IdListObservable();
    IdListObservable parkID = new IdListObservable();
    IdListObservable sideID = new IdListObservable();
    IdListObservable doorID = new IdListObservable();
    IdListObservable circDoorID = new IdListObservable();
    IdListObservable flCircID = new IdListObservable();
    IdListObservable flRoomID = new IdListObservable();
    IdListObservable flExtID = new IdListObservable();
    IdListObservable flParkID = new IdListObservable();
    IdListObservable restID = new IdListObservable();
    IdListObservable boxID = new IdListObservable();

    List<CirculationEntry> circList = new ArrayList<>();
    List<RoomEntry> roomList = new ArrayList<>();
    List<BlockSpaceEntry> blockList = new ArrayList<>();
    List<ExternalAccess> extList = new ArrayList<>();
    List<ParkingLotEntry> parkList = new ArrayList<>();
    List<ParkingLotElderlyEntry> elderList = new ArrayList<>();
    List<ParkingLotPCDEntry> pcdList = new ArrayList<>();
    List<PlaygroundEntry> playList = new ArrayList<>();
    List<RestroomEntry> restList = new ArrayList<>();
    List<SidewalkEntry> sideList = new ArrayList<>();
    List<WaterFountainEntry> fountList = new ArrayList<>();
    List<WaterFountainEntry> roomFountList = new ArrayList<>();
    //Room-related entities
    List<BlackboardEntry> boardList = new ArrayList<>();
    List<CounterEntry> counterList = new ArrayList<>();
    List<DoorEntry> doorList = new ArrayList<>();
    List<FreeSpaceEntry> freeList = new ArrayList<>();
    List<SwitchEntry> switchList = new ArrayList<>();
    List<TableEntry> tableList = new ArrayList<>();
    List<WindowEntry> windowList = new ArrayList<>();
    List<EquipmentEntry> equipList = new ArrayList<>();
    //    Gate & Door Entities
    List<DoorLockEntry> doorLockList = new ArrayList<>();
    List<DoorLockEntry> gateLockList = new ArrayList<>();
    List<DoorLockEntry> circLockList = new ArrayList<>();
    //    Gate Entities
    List<GateObsEntry> gateObsList = new ArrayList<>();
    //    Gate & Sidewalk Entities
    List<PayPhoneEntry> extPhoneList = new ArrayList<>();
    List<PayPhoneEntry> sidePhoneList = new ArrayList<>();
    //    Sidewalk Entities
    List<SidewalkSlopeEntry> slopeList = new ArrayList<>();
    //    Ramp & Stairs Lists
    List<RampStairsEntry> roomStRaList = new ArrayList<>();
    List<RampStairsEntry> sideStRaList = new ArrayList<>();
    List<RampStairsEntry> extStRaList = new ArrayList<>();
    List<RampStairsEntry> parkStRaList = new ArrayList<>();
    List<RampStairsEntry> circRampStairsList = new ArrayList<>();
    //    Stair/Ramp Flights
    List<RampStairsFlightEntry> roomFlightList = new ArrayList<>();
    List<RampStairsFlightEntry> sideFlightList = new ArrayList<>();
    List<RampStairsFlightEntry> extFlightList = new ArrayList<>();
    List<RampStairsFlightEntry> parkFlightList = new ArrayList<>();
    List<RampStairsFlightEntry> circFlightList = new ArrayList<>();
    //    RampStairs Components
    List<RampStairsRailingEntry> roomRailList = new ArrayList<>();
    List<RampStairsRailingEntry> sideRailList = new ArrayList<>();
    List<RampStairsRailingEntry> extRailList = new ArrayList<>();
    List<RampStairsRailingEntry> parkRailList = new ArrayList<>();
    List<RampStairsRailingEntry> circRailList = new ArrayList<>();
    List<RampStairsHandrailEntry> roomHandList = new ArrayList<>();
    List<RampStairsHandrailEntry> sideHandList = new ArrayList<>();
    List<RampStairsHandrailEntry> extHandList = new ArrayList<>();
    List<RampStairsHandrailEntry> parkHandList = new ArrayList<>();
    List<RampStairsHandrailEntry> circHandList = new ArrayList<>();
    //    Restrooom Data
    List<DoorEntry> restDoorList = new ArrayList<>();
    List<FreeSpaceEntry> restFrSpaceList = new ArrayList<>();
    List<RestBoxEntry> boxList = new ArrayList<>();
    List<DoorEntry> boxDoorList = new ArrayList<>();
//    Circulation Data
    List<DoorEntry> circDoorList = new ArrayList<>();
    List<SwitchEntry> circSwitchList = new ArrayList<>();
    List<WindowEntry> circWindowList = new ArrayList<>();
    List<TableEntry> circTableList = new ArrayList<>();
    List<BlackboardEntry> circBoardList = new ArrayList<>();
    List<FreeSpaceEntry> circFreeSpList = new ArrayList<>();
    List<SingleStepEntry> circStepList = new ArrayList<>();
    List<SlopeEntry> circSlopeList = new ArrayList<>();
    List<WaterFountainEntry> circFountainList = new ArrayList<>();
    List<EquipmentEntry> circEquipList = new ArrayList<>();
    List<CounterEntry> circCounterList = new ArrayList<>();
    List<FallProtectionEntry> circProtectList = new ArrayList<>();

    Observer circIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllCircWithID(idList).observe(getViewLifecycleOwner(), cList -> circList = cList);
        ViewModelEntry.getAllCircSwitches(idList).observe(getViewLifecycleOwner(), switchList -> circSwitchList = switchList);
        ViewModelEntry.getAllCircTables(idList).observe(getViewLifecycleOwner(), tableList -> circTableList = tableList);
        ViewModelEntry.getAllCircWindows(idList).observe(getViewLifecycleOwner(), winList -> circWindowList = winList);
        ViewModelEntry.getAllCircBlackboards(idList).observe(getViewLifecycleOwner(), boardList -> circBoardList = boardList);
        ViewModelEntry.getAllCircFreeSpaces(idList).observe(getViewLifecycleOwner(), freeList -> circFreeSpList = freeList);
        ViewModelEntry.getAllSingleStepsCirc(idList).observe(getViewLifecycleOwner(), stepList -> circStepList = stepList);
        ViewModelEntry.getCircAllSlopes(idList).observe(getViewLifecycleOwner(), slopeList -> circSlopeList = slopeList);
        ViewModelEntry.getAllCircWaterFountains(idList).observe(getViewLifecycleOwner(), fountList -> circFountainList = fountList);
        ViewModelEntry.getAllCircEquipments(idList).observe(getViewLifecycleOwner(), equipList -> circEquipList = equipList);
        ViewModelEntry.getAllCircCounters(idList).observe(getViewLifecycleOwner(), counterList -> circCounterList = counterList);
        ViewModelEntry.getAllFallProtection(idList).observe(getViewLifecycleOwner(), protectList -> circProtectList = protectList);

        ViewModelEntry.getAllCircDoors(idList).observe(getViewLifecycleOwner(), doorList -> {
            circDoorList = doorList;
            List<Integer> dIdList = new ArrayList<>();
            for (DoorEntry d : doorList)
                dIdList.add(d.getDoorID());
            circDoorID.setIdList(dIdList);

            ViewModelEntry.getAllLocksFromDoor(dIdList).observe(getViewLifecycleOwner(), lockList -> circLockList = lockList);
        });

        ViewModelEntry.getAllRampStCirc(idList).observe(getViewLifecycleOwner(), rampList -> {
            circRampStairsList = rampList;
            List<Integer> rIdList = new ArrayList<>();
            for (RampStairsEntry r: rampList)
                rIdList.add(r.getRampStairsID());
            flCircID.setIdList(rIdList);
        });

    };

    Observer flCircIdList = (o,arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllFlights(idList).observe(getViewLifecycleOwner(), cFlightList -> {
            circFlightList = cFlightList;
            List<Integer> flightListID = new ArrayList<>();
            for (RampStairsFlightEntry fl : cFlightList)
                flightListID.add(fl.getFlightID());

            ViewModelEntry.getAllHandrails(flightListID).observe(getViewLifecycleOwner(), circHand -> circHandList = circHand);
            ViewModelEntry.getAllRailings(flightListID).observe(getViewLifecycleOwner(), circRail -> circRailList = circRail);
        });
    };

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
        ViewModelEntry.getAllRestEntries(idList).observe(getViewLifecycleOwner(), reList -> {
            restList = reList;
            List<Integer> restIdList = new ArrayList<>();
            for (RestroomEntry r : reList)
                restIdList.add(r.getRestroomID());
            restID.setIdList(restIdList);
        });
        ViewModelEntry.getAllSidewalks(idList).observe(getViewLifecycleOwner(), sList -> {
            sideList = sList;
            List<Integer> sIDList = new ArrayList<>();
            for (SidewalkEntry s : sList)
                sIDList.add(s.getSidewalkID());
            sideID.setIdList(sIDList);
        });
        ViewModelEntry.getAllWaterFountains(idList).observe(getViewLifecycleOwner(), wList -> fountList = wList);
    };

    Observer roomIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllBlackboards(idList).observe(getViewLifecycleOwner(), bList -> boardList = bList);
        ViewModelEntry.getAllCounters(idList).observe(getViewLifecycleOwner(), cList -> counterList = cList);
        ViewModelEntry.getAllDoors(idList).observe(getViewLifecycleOwner(), dList -> {
            doorList = dList;
            List<Integer> dIdList = new ArrayList<>();
            for (DoorEntry d : dList)
                dIdList.add(d.getDoorID());
            doorID.setIdList(dIdList);
        });
        ViewModelEntry.getAllFreeSpaces(idList).observe(getViewLifecycleOwner(), fList -> freeList = fList);
        ViewModelEntry.getAllRampStRoom(idList).observe(getViewLifecycleOwner(), rsList -> {
            roomStRaList = rsList;
            List<Integer> stRaList = new ArrayList<>();
            for (RampStairsEntry st : rsList)
                stRaList.add(st.getRampStairsID());
            flRoomID.setIdList(stRaList);
        });
        ViewModelEntry.getAllRoomsSwitches(idList).observe(getViewLifecycleOwner(), sList -> switchList = sList);
        ViewModelEntry.getAllRoomsTables(idList).observe(getViewLifecycleOwner(), tList -> tableList = tList);
        ViewModelEntry.getAllRoomsWindows(idList).observe(getViewLifecycleOwner(), wList -> windowList = wList);
        ViewModelEntry.getAllEquipments(idList).observe(getViewLifecycleOwner(), eList -> equipList = eList);
        ViewModelEntry.getAllRoomWaterFountains(idList).observe(getViewLifecycleOwner(), waterList -> roomFountList = waterList);
    };

    Observer extAccessIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllLocksFromGates(idList).observe(getViewLifecycleOwner(), dLoList -> gateLockList = dLoList);
        ViewModelEntry.getAllGateObs(idList).observe(getViewLifecycleOwner(), gList -> gateObsList = gList);
        ViewModelEntry.getAllPhonesExtAccess(idList).observe(getViewLifecycleOwner(), phList -> extPhoneList = phList);
        ViewModelEntry.getAllRampStExt(idList).observe(getViewLifecycleOwner(), rampList -> {
            extStRaList = rampList;
            List<Integer> stRaList = new ArrayList<>();
            for (RampStairsEntry st : rampList)
                stRaList.add(st.getRampStairsID());
            flExtID.setIdList(stRaList);
        });
    };

    Observer parkIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllRampStPark(idList).observe(getViewLifecycleOwner(), rampList -> {
            parkStRaList = rampList;
            List<Integer> stRaList = new ArrayList<>();
            for (RampStairsEntry st : rampList)
                stRaList.add(st.getRampStairsID());
            flParkID.setIdList(stRaList);
        });
        ViewModelEntry.getAllElderVacancies(idList).observe(getViewLifecycleOwner(), elder -> elderList = elder);
        ViewModelEntry.getAllPcdVacancies(idList).observe(getViewLifecycleOwner(), pcdVacancy -> pcdList = pcdVacancy);
    };

    Observer sideIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllPhonesSidewalk(idList).observe(getViewLifecycleOwner(), phList -> sidePhoneList = phList);


        ViewModelEntry.getAllSideSlopes(idList).observe(getViewLifecycleOwner(), slList -> slopeList = slList);
    };

    Observer doorIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllLocksFromDoor(idList).observe(getViewLifecycleOwner(), dLoList -> doorLockList = dLoList);
    };

    Observer flRoomIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllFlights(idList).observe(getViewLifecycleOwner(), rFlightList -> {
            roomFlightList = rFlightList;
            List<Integer> flIDList = new ArrayList<>();
            for (RampStairsFlightEntry fl : rFlightList)
                flIDList.add(fl.getFlightID());

            ViewModelEntry.getAllHandrails(flIDList).observe(getViewLifecycleOwner(), flHand -> roomHandList = flHand);
            ViewModelEntry.getAllRailings(flIDList).observe(getViewLifecycleOwner(), flRail -> roomRailList = flRail);
        });
    };

    Observer flExtIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllFlights(idList).observe(getViewLifecycleOwner(), eFlightList -> {
            extFlightList = eFlightList;
            List<Integer> flIDList = new ArrayList<>();
            for (RampStairsFlightEntry fl : eFlightList)
                flIDList.add(fl.getFlightID());

            ViewModelEntry.getAllHandrails(flIDList).observe(getViewLifecycleOwner(), flHand -> extHandList = flHand);
            ViewModelEntry.getAllRailings(flIDList).observe(getViewLifecycleOwner(), flRail -> extRailList = flRail);
        });
    };

    Observer flParkIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllFlights(idList).observe(getViewLifecycleOwner(), pFlightList -> {
            parkFlightList = pFlightList;
            List<Integer> flIDList = new ArrayList<>();
            for (RampStairsFlightEntry fl : pFlightList)
                flIDList.add(fl.getFlightID());

            ViewModelEntry.getAllHandrails(flIDList).observe(getViewLifecycleOwner(), flHand -> parkHandList = flHand);
            ViewModelEntry.getAllRailings(flIDList).observe(getViewLifecycleOwner(), flRail -> parkRailList = flRail);
        });
    };

    Observer restroomIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllRestDoors(idList).observe(getViewLifecycleOwner(), rDoorList -> restDoorList = rDoorList);
        ViewModelEntry.getAllRestFreeSpaces(idList).observe(getViewLifecycleOwner(), rFree -> restFrSpaceList = rFree);
        ViewModelEntry.getAllBoxesAllRestrooms(idList).observe(getViewLifecycleOwner(), bList -> {
            boxList = bList;
            List<Integer> boxIDList = new ArrayList<>();
            for (RestBoxEntry box : bList)
                boxIDList.add(box.getBoxID());
            boxID.setIdList(boxIDList);
        });
    };

    Observer boxIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllBoxDoors(idList).observe(getViewLifecycleOwner(), bDoorList -> boxDoorList = bDoorList);
    };

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

        ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        getParentFragmentManager().setFragmentResultListener(MEMORIAL, this, (key,bundle) -> {
            if (!bundle.getBoolean(VISIBLE_MEMORIAL)) {
                dropdownMenuLocations.setVisibility(View.GONE);
                saveAndClose.setVisibility(View.GONE);
                if (bundle.getBoolean(CIRCULATION))
                    registerHeader.setText("Cadastro de Circulações");
            } else {
                dropdownMenuLocations.setVisibility(View.VISIBLE);
                saveAndClose.setVisibility(View.VISIBLE);
            }

        });

        modelEntry.getEntry(fragInspection.getInt(SCHOOL_ID)).observe(getViewLifecycleOwner(), entry -> school = entry);

        modelEntry.getAllBlocksSchool(fragInspection.getInt(SCHOOL_ID)).observe(getViewLifecycleOwner(), blocks -> {
            blockList = blocks;
            for (int k = blockCounter; k < blocks.size(); k++) {
                blockListId.add(blockList.get(k).getBlockSpaceID());
                if (blockList.get(k).getBlockSpaceType() == 0)
                    blockQnt++;
                else if (blockList.get(k).getBlockSpaceType() == 2)
                    hasHelpSpace = true;
            }
            blockCounter = blocks.size();
        });

        ViewModelEntry.getAllParkingLots(blockListId).observe(getViewLifecycleOwner(), parking -> {
            if (!parking.isEmpty()) {
                for (int k = 0; k < parking.size(); k++) {
                    ParkingLotEntry pLot = parking.get(k);
                    if (pLot.getTypeParkingLot() == 1)
                        extParkQnt++;
                    else
                        intParkQnt++;
                }
            }
        });

        modelEntry.getAllBlockIds(fragInspection.getInt(SCHOOL_ID)).observe(getViewLifecycleOwner(), bList -> bID.setIdList(bList));

        modelEntry.getAllCircIds(fragInspection.getInt(SCHOOL_ID)).observe(getViewLifecycleOwner(), cList -> circID.setIdList(cList));

        saveAndClose.setOnClickListener(v -> {

            JsonCreation jCreate = new JsonCreation(school, blockList, roomList, extList, parkList, playList, restList, sideList, fountList, roomStRaList, sideStRaList,
                    extStRaList, parkStRaList, boardList, counterList, doorList, freeList, switchList, tableList, windowList, doorLockList,
                    gateLockList, gateObsList, extPhoneList, sidePhoneList, slopeList, roomFlightList, sideFlightList, extFlightList, parkFlightList,
                    roomRailList, sideRailList, extRailList, parkRailList, roomHandList, sideHandList, extHandList, parkHandList, blockQnt, hasHelpSpace, extParkQnt, intParkQnt,
                    elderList, pcdList, roomFountList, restDoorList, restFrSpaceList, boxList, boxDoorList, equipList, circList, circDoorList, circLockList, circSwitchList,
                    circWindowList, circTableList, circBoardList, circFreeSpList, circStepList, circSlopeList, circFountainList, circEquipList,
                    circCounterList, circProtectList, circRampStairsList, circFlightList, circRailList, circHandList);

            tData = jCreate.createJson();

            InspectionActivity.callFunction(tData, jCreate, getActivity().getApplicationContext());


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

        getParentFragmentManager().setFragmentResultListener(HEADER_TEXT, this, (key, bundle) -> registerHeader.setText(setHeaderText(bundle)));
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

    private String setHeaderText(Bundle bundle) {
        int choice = bundle.getInt(CHOICE);
        if (bundle.getBoolean(EXT_AREA_REG)) {
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
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Observers
        bID.addObserver(blockIdList);
        circID.addObserver(circIdList);
        roomID.addObserver(roomIdList);
        extID.addObserver(extAccessIdList);
        parkID.addObserver(parkIdList);
        sideID.addObserver(sideIdList);
        doorID.addObserver(doorIdList);
        flRoomID.addObserver(flRoomIdList);
        flExtID.addObserver(flExtIdList);
        flParkID.addObserver(flParkIdList);
        flCircID.addObserver(flCircIdList);
        restID.addObserver(restroomIdList);
        boxID.addObserver(boxIdList);
    }

    public interface OnFragmentInteractionListener {
        void onDropdownChoice(int choice);
    }

}