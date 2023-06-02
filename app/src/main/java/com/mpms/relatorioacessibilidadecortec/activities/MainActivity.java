package com.mpms.relatorioacessibilidadecortec.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.DocumentsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.mpms.relatorioacessibilidadecortec.BuildConfig;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.adapter.OnEntryClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.OnPopupClickListener;
import com.mpms.relatorioacessibilidadecortec.adapter.RecyclerViewAdapter;
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
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolBenchEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolEquipEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolRampEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolStairsEntry;
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
import com.mpms.relatorioacessibilidadecortec.model.InspectionViewModel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.report.TextUpdate;
import com.mpms.relatorioacessibilidadecortec.util.BoolObservable;
import com.mpms.relatorioacessibilidadecortec.util.IdListObservable;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.apache.poi.openxml4j.exceptions.OpenXML4JRuntimeException;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Observer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity implements OnEntryClickListener, OnPopupClickListener, TagInterface {

    private ViewModelEntry viewModelEntry;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ActionMode actionMode;
    private FloatingActionButton fab;

    int delClick = 0;

    public static int endRegister = 0;

//    ---------------------

    static int blockQnt = 0;
    static boolean hasHelpSpace = false;
    static int intParkQnt = 0;
    static int extParkQnt = 0;
    static int blockCounter = 0;

    static File filePath;
    Uri fileUri;
    //    private static Context context;
    private static String[] address;
    Future<?> check;

    ExecutorService service;
    Handler handler;

    InspectionViewModel dataView;

    static TextUpdate upText;
    static HashMap<String, String> tData;
    static ActivityResultLauncher<String> fillCreatedDocxFile;
    static CircularProgressIndicator circBar;

    static BoolObservable endReport = new BoolObservable();

    Observer repObserver = (o, arg) -> {
        BoolObservable bEnd = (BoolObservable) o;
        boolean hasFinished = bEnd.getFinished();

        if (hasFinished) {
            if (!upText.error && Build.VERSION.SDK_INT > Build.VERSION_CODES.Q)
                sendEmailIntent(fileUri, address, this);
            else if (!upText.error)
                sendEmailIntent(Uri.parse("placeholder"), address, this);
            else {
                showProgress(false);
                Toast.makeText(getApplicationContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            }
        }
    };

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
    IdListObservable poolID = new IdListObservable();

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
    //    PoolData
    List<PoolEntry> pList = new ArrayList<>();
    List<PoolRampEntry> poolRampList = new ArrayList<>();
    List<PoolStairsEntry> poolStairsList = new ArrayList<>();
    List<PoolBenchEntry> poolBenchList = new ArrayList<>();
    List<PoolEquipEntry> poolEquipList = new ArrayList<>();

    Observer circIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllCircWithID(idList).observe(this, cList -> circList = cList);
        ViewModelEntry.getAllCircSwitches(idList).observe(this, switchList -> circSwitchList = switchList);
        ViewModelEntry.getAllCircTables(idList).observe(this, tableList -> circTableList = tableList);
        ViewModelEntry.getAllCircWindows(idList).observe(this, winList -> circWindowList = winList);
        ViewModelEntry.getAllCircBlackboards(idList).observe(this, boardList -> circBoardList = boardList);
        ViewModelEntry.getAllCircFreeSpaces(idList).observe(this, freeList -> circFreeSpList = freeList);
        ViewModelEntry.getAllSingleStepsCirc(idList).observe(this, stepList -> circStepList = stepList);
        ViewModelEntry.getCircAllSlopes(idList).observe(this, slopeList -> circSlopeList = slopeList);
        ViewModelEntry.getAllCircWaterFountains(idList).observe(this, fountList -> circFountainList = fountList);
        ViewModelEntry.getAllCircEquipments(idList).observe(this, equipList -> circEquipList = equipList);
        ViewModelEntry.getAllCircCounters(idList).observe(this, counterList -> circCounterList = counterList);
        ViewModelEntry.getAllFallProtection(idList).observe(this, protectList -> circProtectList = protectList);

        ViewModelEntry.getAllCircDoors(idList).observe(this, doorList -> {
            circDoorList = doorList;
            List<Integer> dIdList = new ArrayList<>();
            for (DoorEntry d : doorList)
                dIdList.add(d.getDoorID());
            circDoorID.setIdList(dIdList);

            ViewModelEntry.getAllLocksFromDoor(dIdList).observe(this, lockList -> circLockList = lockList);
        });

        ViewModelEntry.getAllRampStCirc(idList).observe(this, rampList -> {
            circRampStairsList = rampList;
            List<Integer> rIdList = new ArrayList<>();
            for (RampStairsEntry r : rampList)
                rIdList.add(r.getRampStairsID());
            flCircID.setIdList(rIdList);
        });

    };

    Observer flCircIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllFlights(idList).observe(this, cFlightList -> {
            circFlightList = cFlightList;
            List<Integer> flightListID = new ArrayList<>();
            for (RampStairsFlightEntry fl : cFlightList)
                flightListID.add(fl.getFlightID());

            ViewModelEntry.getAllHandrails(flightListID).observe(this, circHand -> circHandList = circHand);
            ViewModelEntry.getAllRailings(flightListID).observe(this, circRail -> circRailList = circRail);
        });
    };

    Observer blockIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllRoomsInSchool(idList).observe(this, rList -> {
            roomList = rList;
            List<Integer> rIdList = new ArrayList<>();
            for (RoomEntry r : rList)
                rIdList.add(r.getRoomID());
            roomID.setIdList(rIdList);
        });
        ViewModelEntry.getAllExtAccess(idList).observe(this, exList -> {
            extList = exList;
            List<Integer> exIdList = new ArrayList<>();
            for (ExternalAccess e : exList)
                exIdList.add(e.getExternalAccessID());
            extID.setIdList(exIdList);
        });
        ViewModelEntry.getAllParkingLots(idList).observe(this, pList -> {
            parkList = pList;
            List<Integer> pIdList = new ArrayList<>();
            for (ParkingLotEntry p : pList)
                pIdList.add(p.getParkingID());
            parkID.setIdList(pIdList);
        });
        ViewModelEntry.getAllPlaygrounds(idList).observe(this, pgList -> playList = pgList);
        ViewModelEntry.getAllRestEntries(idList).observe(this, reList -> {
            restList = reList;
            List<Integer> restIdList = new ArrayList<>();
            for (RestroomEntry r : reList)
                restIdList.add(r.getRestroomID());
            restID.setIdList(restIdList);
        });
        ViewModelEntry.getAllSidewalks(idList).observe(this, sList -> {
            sideList = sList;
            List<Integer> sIDList = new ArrayList<>();
            for (SidewalkEntry s : sList)
                sIDList.add(s.getSidewalkID());
            sideID.setIdList(sIDList);
        });
        ViewModelEntry.getAllWaterFountains(idList).observe(this, wList -> fountList = wList);
        ViewModelEntry.getAllPools(idList).observe(this, poolList -> {
            pList = poolList;
            List<Integer> pIDList = new ArrayList<>();
            for (PoolEntry p : pList)
                pIDList.add(p.getPoolID());
            poolID.setIdList(pIDList);
        });
    };

    Observer poolIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllPoolBenches(idList).observe(this, benchList -> poolBenchList = benchList);
        ViewModelEntry.getEveryPoolRamps(idList).observe(this, rampList -> poolRampList = rampList);
        ViewModelEntry.getEveryPoolStairs(idList).observe(this, stairsList -> poolStairsList = stairsList);
        ViewModelEntry.getEveryPoolEquips(idList).observe(this, equipList -> poolEquipList = equipList);

    };

    Observer roomIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllBlackboards(idList).observe(this, bList -> boardList = bList);
        ViewModelEntry.getAllCounters(idList).observe(this, cList -> counterList = cList);
        ViewModelEntry.getAllDoors(idList).observe(this, dList -> {
            doorList = dList;
            List<Integer> dIdList = new ArrayList<>();
            for (DoorEntry d : dList)
                dIdList.add(d.getDoorID());
            doorID.setIdList(dIdList);
        });
        ViewModelEntry.getAllFreeSpaces(idList).observe(this, fList -> freeList = fList);
        ViewModelEntry.getAllRampStRoom(idList).observe(this, rsList -> {
            roomStRaList = rsList;
            List<Integer> stRaList = new ArrayList<>();
            for (RampStairsEntry st : rsList)
                stRaList.add(st.getRampStairsID());
            flRoomID.setIdList(stRaList);
        });
        ViewModelEntry.getAllRoomsSwitches(idList).observe(this, sList -> switchList = sList);
        ViewModelEntry.getAllRoomsTables(idList).observe(this, tList -> tableList = tList);
        ViewModelEntry.getAllRoomsWindows(idList).observe(this, wList -> windowList = wList);
        ViewModelEntry.getAllEquipments(idList).observe(this, eList -> equipList = eList);
        ViewModelEntry.getAllRoomWaterFountains(idList).observe(this, waterList -> roomFountList = waterList);
    };

    Observer extAccessIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllLocksFromGates(idList).observe(this, dLoList -> gateLockList = dLoList);
        ViewModelEntry.getAllGateObs(idList).observe(this, gList -> gateObsList = gList);
        ViewModelEntry.getAllPhonesExtAccess(idList).observe(this, phList -> extPhoneList = phList);
        ViewModelEntry.getAllRampStExt(idList).observe(this, rampList -> {
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

        ViewModelEntry.getAllRampStPark(idList).observe(this, rampList -> {
            parkStRaList = rampList;
            List<Integer> stRaList = new ArrayList<>();
            for (RampStairsEntry st : rampList)
                stRaList.add(st.getRampStairsID());
            flParkID.setIdList(stRaList);
        });
        ViewModelEntry.getAllElderVacancies(idList).observe(this, elder -> elderList = elder);
        ViewModelEntry.getAllPcdVacancies(idList).observe(this, pcdVacancy -> pcdList = pcdVacancy);
    };

    Observer sideIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllPhonesSidewalk(idList).observe(this, phList -> sidePhoneList = phList);


        ViewModelEntry.getAllSideSlopes(idList).observe(this, slList -> slopeList = slList);
    };

    Observer doorIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllLocksFromDoor(idList).observe(this, dLoList -> doorLockList = dLoList);
    };

    Observer flRoomIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllFlights(idList).observe(this, rFlightList -> {
            roomFlightList = rFlightList;
            List<Integer> flIDList = new ArrayList<>();
            for (RampStairsFlightEntry fl : rFlightList)
                flIDList.add(fl.getFlightID());

            ViewModelEntry.getAllHandrails(flIDList).observe(this, flHand -> roomHandList = flHand);
            ViewModelEntry.getAllRailings(flIDList).observe(this, flRail -> roomRailList = flRail);
        });
    };

    Observer flExtIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllFlights(idList).observe(this, eFlightList -> {
            extFlightList = eFlightList;
            List<Integer> flIDList = new ArrayList<>();
            for (RampStairsFlightEntry fl : eFlightList)
                flIDList.add(fl.getFlightID());

            ViewModelEntry.getAllHandrails(flIDList).observe(this, flHand -> extHandList = flHand);
            ViewModelEntry.getAllRailings(flIDList).observe(this, flRail -> extRailList = flRail);
        });
    };

    Observer flParkIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllFlights(idList).observe(this, pFlightList -> {
            parkFlightList = pFlightList;
            List<Integer> flIDList = new ArrayList<>();
            for (RampStairsFlightEntry fl : pFlightList)
                flIDList.add(fl.getFlightID());

            ViewModelEntry.getAllHandrails(flIDList).observe(this, flHand -> parkHandList = flHand);
            ViewModelEntry.getAllRailings(flIDList).observe(this, flRail -> parkRailList = flRail);
        });
    };

    Observer restroomIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllRestDoors(idList).observe(this, rDoorList -> restDoorList = rDoorList);
        ViewModelEntry.getAllRestFreeSpaces(idList).observe(this, rFree -> restFrSpaceList = rFree);
        ViewModelEntry.getAllBoxesAllRestrooms(idList).observe(this, bList -> {
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

        ViewModelEntry.getAllBoxDoors(idList).observe(this, bDoorList -> boxDoorList = bDoorList);
    };

//    --------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instantiateViews();

        viewModelEntry.getAllEntries().observe(this, schoolEntries -> {

            recyclerViewAdapter = new RecyclerViewAdapter(schoolEntries, MainActivity.this, this, this);
            recyclerView.setAdapter(recyclerViewAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(MainActivity.this, R.drawable.abc_list_divider_material)));
            recyclerView.addItemDecoration(dividerItemDecoration);

            recyclerViewAdapter.setListener(new ListClickListener() {
                @Override
                public void onItemClick(int position) {
                    if (actionMode == null)
                        OnEntryClick(position);
                    else
                        enableActionMode(schoolEntries);
                }

                @Override
                public void onItemLongClick(int position) {
                    enableActionMode(schoolEntries);
                }
            });
        });


        fab.setOnClickListener(v -> {
            if (actionMode != null)
                actionMode.finish();
            startActivity(new Intent(MainActivity.this, SchoolRegisterActivity.class));
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        fillCreatedDocxFile = registerForActivityResult(new CreateDocumentDaex(), result -> {

            Future<?> future = service.submit(() -> {
                try {
                    fileUri = result;
                    handler.post(() -> {
                        boolean finish = upText.docFiller(tData, result, getApplicationContext());
                        endReport.setFinished(finish);
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

//            try {
//                check = (Future<?>) future.get();
//            } catch (ExecutionException | InterruptedException e) {
//                e.printStackTrace();
//            }
            while (!future.isDone()) {
                try {
                    check = (Future<?>) future.get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    @Override
    protected void onRestart() {
        if (endRegister == 1) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        } else
            endRegister = 0;

        super.onRestart();
    }

    private void instantiateViews() {

        recyclerView = findViewById(R.id.recyclerViewEntries);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab = findViewById(R.id.fab_register);

        viewModelEntry = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this.getApplication()).create(ViewModelEntry.class);

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
        poolID.addObserver(poolIdList);
        endReport.addObserver(repObserver);

        service = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
        circBar = findViewById(R.id.progress_indicator);

        upText = new TextUpdate();
    }

    private <T> void enableActionMode(List<T> entries) {
        if (actionMode == null) {
            AppCompatActivity activity = this;
            actionMode = activity.startSupportActionMode(new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    mode.getMenuInflater().inflate(R.menu.menu_delete, menu);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return true;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    if (item.getItemId() == R.id.delete_button) {
                        delClick = 1;
                        recyclerViewAdapter.deleteItemList();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    if (delClick == 0)
                        recyclerViewAdapter.cancelSelection(recyclerView, entries, recyclerViewAdapter);
                    recyclerViewAdapter.selectedItems.clear();
                    recyclerViewAdapter.notifyDataSetChanged();
                    delClick = 0;
                    actionMode = null;
                }
            });
        }

        final int size = recyclerViewAdapter.selectedItems.size();
        if (size == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(size + "");
            actionMode.invalidate();
        }
    }

    @Override
    public void OnEntryClick(int position) {
        SchoolEntry schoolEntry = viewModelEntry.getAllEntries().getValue().get(position);
        Intent updateIntent;
        if (schoolEntry.getUpdateRegister() == 0) {
            updateIntent = new Intent(MainActivity.this, SchoolRegisterActivity.class);
        } else if (schoolEntry.getUpdateRegister() == 1) {
            updateIntent = new Intent(MainActivity.this, SchoolAreasRegisterActivity.class);
        } else {
            schoolEntry.setUpdateRegister(0);
            Toast.makeText(getApplicationContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            return;
        }
        updateIntent.putExtra(SCHOOL_ID, schoolEntry.getCadID());
        if (actionMode != null)
            actionMode.finish();
        startActivity(updateIntent);
    }


    @Override
    public void onPopupClickOption(int position, MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.edit_school) {
            SchoolEntry schoolEntry = viewModelEntry.getAllEntries().getValue().get(position);
            Intent updateIntent = new Intent(MainActivity.this, SchoolRegisterActivity.class);
            updateIntent.putExtra(SCHOOL_ID, schoolEntry.getCadID());
            if (actionMode != null)
                actionMode.finish();
            startActivity(updateIntent);
        } else if (menuItem.getItemId() == R.id.generate_report) {
            SchoolEntry schoolEntry = viewModelEntry.getAllEntries().getValue().get(position);
            int schoolID = schoolEntry.getCadID();

            viewModelEntry.getAllBlocksSchool(schoolID).observe(this, blocks -> {
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

            ViewModelEntry.getAllParkingLots(blockListId).observe(this, parking -> {
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

            viewModelEntry.getAllBlockIds(schoolID).observe(this, bList -> bID.setIdList(bList));

            viewModelEntry.getAllCircIds(schoolID).observe(this, cList -> circID.setIdList(cList));

            JsonCreation jCreate = new JsonCreation(schoolEntry, blockList, roomList, extList, parkList, playList, restList, sideList, fountList, roomStRaList, sideStRaList,
                    extStRaList, parkStRaList, boardList, counterList, doorList, freeList, switchList, tableList, windowList, doorLockList,
                    gateLockList, gateObsList, extPhoneList, sidePhoneList, slopeList, roomFlightList, sideFlightList, extFlightList, parkFlightList,
                    roomRailList, sideRailList, extRailList, parkRailList, roomHandList, sideHandList, extHandList, parkHandList, blockQnt, hasHelpSpace, extParkQnt, intParkQnt,
                    elderList, pcdList, roomFountList, restDoorList, restFrSpaceList, boxList, boxDoorList, equipList, circList, circDoorList, circLockList, circSwitchList,
                    circWindowList, circTableList, circBoardList, circFreeSpList, circStepList, circSlopeList, circFountainList, circEquipList,
                    circCounterList, circProtectList, circRampStairsList, circFlightList, circRailList, circHandList, pList, poolRampList, poolStairsList, poolBenchList, poolEquipList);

            tData = jCreate.createJson();

            MainActivity.callFunction(tData, jCreate, getApplicationContext());
        }
    }

    public static void sendEmailIntent(Uri uri, String[] address, Context context) {
        Intent sender = new Intent(Intent.ACTION_SEND);
        sender.putExtra(Intent.EXTRA_SUBJECT, "Relatório DOCX");
        sender.putExtra(Intent.EXTRA_EMAIL, address);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
            Uri fileUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", new File(upText.fileName));
            sender.putExtra(Intent.EXTRA_STREAM, fileUri);
        } else
            sender.putExtra(Intent.EXTRA_STREAM, uri);
        sender.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        sender.setType("message/rfc822");
        InspectionActivity.endRegister = 1;
        showProgress(false);
        context.startActivity(Intent.createChooser(sender, "Escolha o App desejado"));
    }

    public class CreateDocumentDaex extends ActivityResultContracts.CreateDocument {

        @RequiresApi(api = Build.VERSION_CODES.O)
        @NonNull
        @NotNull
        @Override
        public Intent createIntent(@NonNull @NotNull Context context, @NonNull @NotNull String input) {
            return super.createIntent(context, input)
                    .setType("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
                    .addCategory(Intent.CATEGORY_OPENABLE)
                    .putExtra(DocumentsContract.EXTRA_INITIAL_URI, filePath)
                    .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                    .putExtra(Intent.EXTRA_STREAM, fileUri);

        }
    }

    public static void showProgress(boolean show) {
        circBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    public static void callFunction(HashMap<String, String> tData, JsonCreation jCreate, Context context) {
        address = new String[]{jCreate.getSchool().getEmailAddress()};
        MainActivity.tData = tData;
        List<String> blockList = jCreate.ambListCreator();
        upText.setJsonCreation(jCreate, blockList);
        upText.newFileName();
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
            try {
                boolean finish = upText.docFiller(tData, Uri.parse("placeholder"), context.getApplicationContext());
                endReport.setFinished(finish);
            } catch (OpenXML4JRuntimeException e) {
                endRegister = 0;
                e.printStackTrace();
            }
        } else {
            filePath = upText.path;
            fillCreatedDocxFile.launch(upText.fName);
        }
    }
}