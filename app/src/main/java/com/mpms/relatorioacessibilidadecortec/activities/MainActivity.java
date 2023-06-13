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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
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
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
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
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.report.TextUpdate;
import com.mpms.relatorioacessibilidadecortec.util.ListClickListener;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;
import com.whygraphics.multilineradiogroup.BuildConfig;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements OnEntryClickListener, OnPopupClickListener, TagInterface {

    private ViewModelEntry modelEntry;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ActionMode actionMode;
    private FloatingActionButton fab;
    Context context;

    int delClick = 0;

    public static int endRegister = 0;

    static int blockQnt = 0;
    static boolean hasHelpSpace = false;
    static int intParkQnt = 0;
    static int extParkQnt = 0;
    static int blockCounter = 0;
    static SchoolEntry school;

    static File filePath;
    Uri fileUri;
    private static String[] address;

    Handler handler;

    int schoolID;

    static TextUpdate upText;
    static HashMap<String, String> tData;
    static ActivityResultLauncher<String> fillCreatedDocxFile;
    static CircularProgressIndicator circBar;

    List<BlockSpaceEntry> blockList = new ArrayList<>();

    ListeningExecutorService service;

    List<ListenableFuture<?>> blockDataQueries = new ArrayList<>();

    ListenableFuture<List<Integer>> blockIDListener;
    ListenableFuture<List<Integer>> roomIDListener;
    ListenableFuture<List<Integer>> roomDoorIDList;
    ListenableFuture<List<Integer>> roomRampStIDList;
    ListenableFuture<List<Integer>> roomFlightIDList;
    ListenableFuture<List<Integer>> extIDListener;
    ListenableFuture<List<Integer>> extRampStIDList;
    ListenableFuture<List<Integer>> extFlightIDList;
    ListenableFuture<List<Integer>> sideIDList;
    ListenableFuture<List<Integer>> parkIDList;
    ListenableFuture<List<Integer>> parkRampStIDList;
    ListenableFuture<List<Integer>> parkFlightIDList;
    ListenableFuture<List<Integer>> restIDList;
    ListenableFuture<List<Integer>> boxIDList;
    ListenableFuture<List<Integer>> poolIDList;
    ListenableFuture<List<Integer>> circIDListener;
    ListenableFuture<List<Integer>> circDoorIDList;
    ListenableFuture<List<Integer>> circRampStIDList;
    ListenableFuture<List<Integer>> circFlightIDList;

    ListenableFuture<SchoolEntry> schoolListener;
    ListenableFuture<List<BlockSpaceEntry>> blockListener;

    //    Room-related ListenableFutures
    ListenableFuture<List<RoomEntry>> blockRoomList;
    ListenableFuture<List<DoorEntry>> roomDoorListener;
    ListenableFuture<List<DoorLockEntry>> roomDoorLockListener;
    ListenableFuture<List<SwitchEntry>> roomSwitchListener;
    ListenableFuture<List<WindowEntry>> roomWindowListener;
    ListenableFuture<List<TableEntry>> roomTableListener;
    ListenableFuture<List<BlackboardEntry>> roomBoardListener;
    ListenableFuture<List<FreeSpaceEntry>> roomFreeSpListener;
    ListenableFuture<List<RampStairsEntry>> roomRampStairsListener;
    ListenableFuture<List<RampStairsFlightEntry>> roomRampStFlightListener;
    ListenableFuture<List<RampStairsRailingEntry>> roomRampStRailListener;
    ListenableFuture<List<RampStairsHandrailEntry>> roomRampStHandListener;
    ListenableFuture<List<SingleStepEntry>> roomStepListener;
    ListenableFuture<List<SlopeEntry>> roomSlopeListener;
    ListenableFuture<List<WaterFountainEntry>> roomFountainListener;
    ListenableFuture<List<EquipmentEntry>> roomEquipListener;
    ListenableFuture<List<CounterEntry>> roomCounterListener;

    ListenableFuture<List<ExternalAccess>> extAccessList;
    ListenableFuture<List<DoorLockEntry>> extGateLockListener;
    ListenableFuture<List<GateObsEntry>> gateObstacleListener;
    ListenableFuture<List<PayPhoneEntry>> extPhoneListener;
    ListenableFuture<List<RampStairsEntry>> extRampStairsListener;
    ListenableFuture<List<RampStairsFlightEntry>> extRampStFlightListener;
    ListenableFuture<List<RampStairsRailingEntry>> extRampStRailListener;
    ListenableFuture<List<RampStairsHandrailEntry>> extRampStHandListener;

    ListenableFuture<List<ParkingLotEntry>> parkingList;
    ListenableFuture<List<RampStairsEntry>> parkRampStairsListener;
    ListenableFuture<List<RampStairsFlightEntry>> parkRampStFlightListener;
    ListenableFuture<List<RampStairsRailingEntry>> parkRampStRailListener;
    ListenableFuture<List<RampStairsHandrailEntry>> parkRampStHandListener;
    ListenableFuture<List<ParkingLotElderlyEntry>> parkElderListener;
    ListenableFuture<List<ParkingLotPCDEntry>> parkPCDListener;

    ListenableFuture<List<PlaygroundEntry>> playListener;

    ListenableFuture<List<RestroomEntry>> restroomListener;
    ListenableFuture<List<DoorEntry>> restDoorList;
    ListenableFuture<List<FreeSpaceEntry>> restFreeSpaceList;
    ListenableFuture<List<RestBoxEntry>> restBoxList;
    ListenableFuture<List<DoorEntry>> restBoxDoorList;

    ListenableFuture<List<SidewalkEntry>> sidewalkListener;
    ListenableFuture<List<SidewalkSlopeEntry>> sideSlopeList;
    ListenableFuture<List<PayPhoneEntry>> sidePhoneList;

    ListenableFuture<List<WaterFountainEntry>> blockFountList;

    ListenableFuture<List<PoolEntry>> poolList;
    ListenableFuture<List<PoolRampEntry>> poolRampListener;
    ListenableFuture<List<PoolStairsEntry>> poolStairsListener;
    ListenableFuture<List<PoolBenchEntry>> poolBenchListener;
    ListenableFuture<List<PoolEquipEntry>> poolEquipListener;

    ListenableFuture<List<CirculationEntry>> circListener;

    ListenableFuture<List<DoorEntry>> circDoorListener;
    ListenableFuture<List<DoorLockEntry>> circDoorLockListener;
    ListenableFuture<List<SwitchEntry>> circSwitchListener;
    ListenableFuture<List<WindowEntry>> circWindowListener;
    ListenableFuture<List<TableEntry>> circTableListener;
    ListenableFuture<List<BlackboardEntry>> circBoardListener;
    ListenableFuture<List<FreeSpaceEntry>> circFreeSpListener;
    ListenableFuture<List<SingleStepEntry>> circStepListener;
    ListenableFuture<List<SlopeEntry>> circSlopeListener;
    ListenableFuture<List<WaterFountainEntry>> circFountainListener;
    ListenableFuture<List<EquipmentEntry>> circEquipListener;
    ListenableFuture<List<CounterEntry>> circCounterListener;
    ListenableFuture<List<FallProtectionEntry>> circProtectListener;
    ListenableFuture<List<RampStairsEntry>> circRampStListener;
    ListenableFuture<List<RampStairsFlightEntry>> circRampStFlightListener;
    ListenableFuture<List<RampStairsHandrailEntry>> circRampStHandListener;
    ListenableFuture<List<RampStairsRailingEntry>> circRampStRailListener;

    AsyncCallable<SchoolEntry> asyncSchool = () -> modelEntry.getListenableSchool(schoolID);

    AsyncFunction<SchoolEntry, List<BlockSpaceEntry>> asyncFunBlockList = new AsyncFunction<SchoolEntry, List<BlockSpaceEntry>>() {
        @Override
        public ListenableFuture<List<BlockSpaceEntry>> apply(SchoolEntry input) throws Exception {
            ListenableFuture<List<BlockSpaceEntry>> blocks = modelEntry.getListAllBlocks(input.getCadID());
            blockList = blocks.get();
            for (int k = blockCounter; k < blockList.size(); k++) {
                if (blockList.get(k).getBlockSpaceType() == 0)
                    blockQnt++;
                else if (blockList.get(k).getBlockSpaceType() == 2)
                    hasHelpSpace = true;
            }
            blockCounter = blockList.size();
            return blocks;
        }
    };

    AsyncFunction<Integer, List<Integer>> asyncFunBlockID = new AsyncFunction<Integer, List<Integer>>() {
        @Override
        public ListenableFuture<List<Integer>> apply(Integer input) throws Exception {
            return modelEntry.getListAllBlocksID(schoolID);
        }
    };

    AsyncCallable<List<BlockSpaceEntry>> asyncBlocks = new AsyncCallable<List<BlockSpaceEntry>>() {
        @Override
        public ListenableFuture<List<BlockSpaceEntry>> call() throws Exception {
            ListenableFuture<List<BlockSpaceEntry>> blocks = modelEntry.getListAllBlocks(schoolID);
            blockList = blocks.get();
            for (int k = blockCounter; k < blockList.size(); k++) {
                if (blockList.get(k).getBlockSpaceType() == 0)
                    blockQnt++;
                else if (blockList.get(k).getBlockSpaceType() == 2)
                    hasHelpSpace = true;
            }
            blockCounter = blockList.size();
            return blocks;
        }
    };

    AsyncCallable<List<Integer>> asyncBlockID = new AsyncCallable<List<Integer>>() {
        @Override
        public ListenableFuture<List<Integer>> call() throws Exception {
            return modelEntry.getListAllBlocksID(schoolID);
        }
    };

//    Room-related Registers

    AsyncFunction<List<Integer>, List<Integer>> asyncRoomID = new AsyncFunction<List<Integer>, List<Integer>>() {
        @Override
        public ListenableFuture<List<Integer>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllRoomsID(input);
        }
    };

    AsyncFunction<List<Integer>, List<RoomEntry>> asyncFunRooms = new AsyncFunction<List<Integer>, List<RoomEntry>>() {
        @Override
        public ListenableFuture<List<RoomEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllRoomsSchool(input);
        }
    };

    AsyncFunction<List<Integer>, List<DoorEntry>> asyncFunRoomDoor = new AsyncFunction<List<Integer>, List<DoorEntry>>() {
        @Override
        public ListenableFuture<List<DoorEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllDoors(input);
        }
    };

    AsyncFunction<List<Integer>, List<Integer>> asyncRoomDoorID = new AsyncFunction<List<Integer>, List<Integer>>() {
        @Override
        public ListenableFuture<List<Integer>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllDoorsID(input);
        }
    };

    AsyncFunction<List<Integer>, List<DoorLockEntry>> asyncFunDoorLock = new AsyncFunction<List<Integer>, List<DoorLockEntry>>() {
        @Override
        public ListenableFuture<List<DoorLockEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllLocksFromDoor(input);
        }
    };

    AsyncFunction<List<Integer>, List<BlackboardEntry>> asyncFunRoomBoard = new AsyncFunction<List<Integer>, List<BlackboardEntry>>() {
        @Override
        public ListenableFuture<List<BlackboardEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllBlackboards(input);
        }
    };

    AsyncFunction<List<Integer>, List<CounterEntry>> asyncFunRoomCounter = new AsyncFunction<List<Integer>, List<CounterEntry>>() {
        @Override
        public ListenableFuture<List<CounterEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllCounters(input);
        }
    };

    AsyncFunction<List<Integer>, List<EquipmentEntry>> asyncFunRoomEquip = new AsyncFunction<List<Integer>, List<EquipmentEntry>>() {
        @Override
        public ListenableFuture<List<EquipmentEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllEquipments(input);
        }
    };

    AsyncFunction<List<Integer>, List<FreeSpaceEntry>> asyncFunRoomFrSp = new AsyncFunction<List<Integer>, List<FreeSpaceEntry>>() {
        @Override
        public ListenableFuture<List<FreeSpaceEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllFreeSpaces(input);
        }
    };

    AsyncFunction<List<Integer>, List<Integer>> asyncRoomRampStID = new AsyncFunction<List<Integer>, List<Integer>>() {
        @Override
        public ListenableFuture<List<Integer>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllRampStIDRoom(input);
        }
    };

    AsyncFunction<List<Integer>, List<RampStairsEntry>> asyncFunRoomRampSt = new AsyncFunction<List<Integer>, List<RampStairsEntry>>() {
        @Override
        public ListenableFuture<List<RampStairsEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllRampStRoom(input);
        }
    };

    AsyncFunction<List<Integer>, List<SingleStepEntry>> asyncFunRoomStep = new AsyncFunction<List<Integer>, List<SingleStepEntry>>() {
        @Override
        public ListenableFuture<List<SingleStepEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListRoomAllSteps(input);
        }
    };

    AsyncFunction<List<Integer>, List<SlopeEntry>> asyncFunRoomSlope = new AsyncFunction<List<Integer>, List<SlopeEntry>>() {
        @Override
        public ListenableFuture<List<SlopeEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListRoomAllSlopes(input);
        }
    };

    AsyncFunction<List<Integer>, List<SwitchEntry>> asyncFunRoomSwitch = new AsyncFunction<List<Integer>, List<SwitchEntry>>() {
        @Override
        public ListenableFuture<List<SwitchEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllRoomsSwitches(input);
        }
    };

    AsyncFunction<List<Integer>, List<TableEntry>> asyncFunRoomTable = new AsyncFunction<List<Integer>, List<TableEntry>>() {
        @Override
        public ListenableFuture<List<TableEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllRoomsTables(input);
        }
    };

    AsyncFunction<List<Integer>, List<WindowEntry>> asyncFunRoomWindow = new AsyncFunction<List<Integer>, List<WindowEntry>>() {
        @Override
        public ListenableFuture<List<WindowEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllRoomsWindows(input);
        }
    };

    AsyncFunction<List<Integer>, List<WaterFountainEntry>> asyncFunRoomFount = new AsyncFunction<List<Integer>, List<WaterFountainEntry>>() {
        @Override
        public ListenableFuture<List<WaterFountainEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllRoomWaterFountains(input);
        }
    };

// General Ramp/Stairs Functions

    AsyncFunction<List<Integer>, List<Integer>> asyncRampStFlightID = new AsyncFunction<List<Integer>, List<Integer>>() {
        @Override
        public ListenableFuture<List<Integer>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllFlightsID(input);
        }
    };

    AsyncFunction<List<Integer>, List<RampStairsFlightEntry>> asyncFunRampStFlight = new AsyncFunction<List<Integer>, List<RampStairsFlightEntry>>() {
        @Override
        public ListenableFuture<List<RampStairsFlightEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllFlights(input);
        }
    };

    AsyncFunction<List<Integer>, List<RampStairsHandrailEntry>> asyncFunRampStHand = new AsyncFunction<List<Integer>, List<RampStairsHandrailEntry>>() {
        @Override
        public ListenableFuture<List<RampStairsHandrailEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllHandrails(input);
        }
    };

    AsyncFunction<List<Integer>, List<RampStairsRailingEntry>> asyncFunRampStRail = new AsyncFunction<List<Integer>, List<RampStairsRailingEntry>>() {
        @Override
        public ListenableFuture<List<RampStairsRailingEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllRailings(input);
        }
    };

//    External Areas

    AsyncFunction<List<Integer>, List<ExternalAccess>> asyncFunExt = new AsyncFunction<List<Integer>, List<ExternalAccess>>() {
        @Override
        public ListenableFuture<List<ExternalAccess>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllExtAccess(input);
        }
    };

    AsyncFunction<List<Integer>, List<Integer>> asyncExtID = new AsyncFunction<List<Integer>, List<Integer>>() {
        @Override
        public ListenableFuture<List<Integer>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllExtAccessID(input);
        }
    };

    AsyncFunction<List<Integer>, List<DoorLockEntry>> asyncFunGateLock = new AsyncFunction<List<Integer>, List<DoorLockEntry>>() {
        @Override
        public ListenableFuture<List<DoorLockEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllLocksFromGates(input);
        }
    };

    AsyncFunction<List<Integer>, List<GateObsEntry>> asyncFunGateObs = new AsyncFunction<List<Integer>, List<GateObsEntry>>() {
        @Override
        public ListenableFuture<List<GateObsEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllObs(input);
        }
    };

    AsyncFunction<List<Integer>, List<PayPhoneEntry>> asyncFunGatePhone = new AsyncFunction<List<Integer>, List<PayPhoneEntry>>() {
        @Override
        public ListenableFuture<List<PayPhoneEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllPhonesExtAccess(input);
        }
    };

    AsyncFunction<List<Integer>, List<RampStairsEntry>> asyncFunGateRampSt = new AsyncFunction<List<Integer>, List<RampStairsEntry>>() {
        @Override
        public ListenableFuture<List<RampStairsEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllRampStExt(input);
        }
    };

//    Sidewalk

    AsyncFunction<List<Integer>, List<SidewalkEntry>> asyncFunSidewalk = new AsyncFunction<List<Integer>, List<SidewalkEntry>>() {
        @Override
        public ListenableFuture<List<SidewalkEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllSidewalks(input);
        }
    };

    AsyncFunction<List<Integer>, List<Integer>> asyncSideID = new AsyncFunction<List<Integer>, List<Integer>>() {
        @Override
        public ListenableFuture<List<Integer>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllSidewalksID(input);
        }
    };

    AsyncFunction<List<Integer>, List<SidewalkSlopeEntry>> asyncFunSideSlope = new AsyncFunction<List<Integer>, List<SidewalkSlopeEntry>>() {
        @Override
        public ListenableFuture<List<SidewalkSlopeEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllSideSlopes(input);
        }
    };

    AsyncFunction<List<Integer>, List<PayPhoneEntry>> asyncFunSidePhone = new AsyncFunction<List<Integer>, List<PayPhoneEntry>>() {
        @Override
        public ListenableFuture<List<PayPhoneEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllPhonesSidewalk(input);
        }
    };

//    Parking Lots

    AsyncFunction<List<Integer>, List<ParkingLotEntry>> asyncFunParking = new AsyncFunction<List<Integer>, List<ParkingLotEntry>>() {
        @Override
        public ListenableFuture<List<ParkingLotEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllParkingLots(input);
        }
    };

    AsyncFunction<List<Integer>, List<Integer>> asyncParkingID = new AsyncFunction<List<Integer>, List<Integer>>() {
        @Override
        public ListenableFuture<List<Integer>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllParkingLotID(input);
        }
    };

    AsyncFunction<List<Integer>, List<RampStairsEntry>> asyncFunParkRampSt = new AsyncFunction<List<Integer>, List<RampStairsEntry>>() {
        @Override
        public ListenableFuture<List<RampStairsEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllRampStPark(input);
        }
    };

    AsyncFunction<List<Integer>, List<Integer>> asyncParkRampStID = new AsyncFunction<List<Integer>, List<Integer>>() {
        @Override
        public ListenableFuture<List<Integer>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllRampStParkID(input);
        }
    };

    AsyncFunction<List<Integer>, List<ParkingLotElderlyEntry>> asyncFunParkElder = new AsyncFunction<List<Integer>, List<ParkingLotElderlyEntry>>() {
        @Override
        public ListenableFuture<List<ParkingLotElderlyEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllElderVacancies(input);
        }
    };

    AsyncFunction<List<Integer>, List<ParkingLotPCDEntry>> asyncFunParkPCD = new AsyncFunction<List<Integer>, List<ParkingLotPCDEntry>>() {
        @Override
        public ListenableFuture<List<ParkingLotPCDEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllPcdVacancies(input);
        }
    };

//    Playground

    AsyncFunction<List<Integer>, List<PlaygroundEntry>> asyncFunPlay = new AsyncFunction<List<Integer>, List<PlaygroundEntry>>() {
        @Override
        public ListenableFuture<List<PlaygroundEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllPlaygrounds(input);
        }
    };

//    Restrooms

    AsyncFunction<List<Integer>, List<RestroomEntry>> asyncFunRest = new AsyncFunction<List<Integer>, List<RestroomEntry>>() {
        @Override
        public ListenableFuture<List<RestroomEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllRestEntries(input);
        }
    };

    AsyncFunction<List<Integer>, List<Integer>> asyncRestID = new AsyncFunction<List<Integer>, List<Integer>>() {
        @Override
        public ListenableFuture<List<Integer>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllRestID(input);
        }
    };

    AsyncFunction<List<Integer>, List<DoorEntry>> asyncFunRestDoor = new AsyncFunction<List<Integer>, List<DoorEntry>>() {
        @Override
        public ListenableFuture<List<DoorEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllRestDoors(input);
        }
    };

    AsyncFunction<List<Integer>, List<FreeSpaceEntry>> asyncFunRestFrSp = new AsyncFunction<List<Integer>, List<FreeSpaceEntry>>() {
        @Override
        public ListenableFuture<List<FreeSpaceEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllRestFreeSpaces(input);
        }
    };

    AsyncFunction<List<Integer>, List<RestBoxEntry>> asyncFunRestBox = new AsyncFunction<List<Integer>, List<RestBoxEntry>>() {
        @Override
        public ListenableFuture<List<RestBoxEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllBoxes(input);
        }
    };

    AsyncFunction<List<Integer>, List<Integer>> asyncBoxID = new AsyncFunction<List<Integer>, List<Integer>>() {
        @Override
        public ListenableFuture<List<Integer>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllBoxesID(input);
        }
    };

    AsyncFunction<List<Integer>, List<DoorEntry>> asyncFunBoxDoor = new AsyncFunction<List<Integer>, List<DoorEntry>>() {
        @Override
        public ListenableFuture<List<DoorEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllBoxDoors(input);
        }
    };

    //    Water Fountain

    AsyncFunction<List<Integer>, List<WaterFountainEntry>> asyncFunFountain = new AsyncFunction<List<Integer>, List<WaterFountainEntry>>() {
        @Override
        public ListenableFuture<List<WaterFountainEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllFountains(input);
        }
    };

//    Pools

    AsyncFunction<List<Integer>, List<PoolEntry>> asyncFunPool = new AsyncFunction<List<Integer>, List<PoolEntry>>() {
        @Override
        public ListenableFuture<List<PoolEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllPools(input);
        }
    };

    AsyncFunction<List<Integer>, List<Integer>> asyncPoolID = new AsyncFunction<List<Integer>, List<Integer>>() {
        @Override
        public ListenableFuture<List<Integer>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllPoolsID(input);
        }
    };

    AsyncFunction<List<Integer>, List<PoolBenchEntry>> asyncFunPoolBench = new AsyncFunction<List<Integer>, List<PoolBenchEntry>>() {
        @Override
        public ListenableFuture<List<PoolBenchEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllPoolBenches(input);
        }
    };

    AsyncFunction<List<Integer>, List<PoolRampEntry>> asyncFunPoolRamp = new AsyncFunction<List<Integer>, List<PoolRampEntry>>() {
        @Override
        public ListenableFuture<List<PoolRampEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllPoolRamps(input);
        }
    };

    AsyncFunction<List<Integer>, List<PoolStairsEntry>> asyncFunPoolStairs = new AsyncFunction<List<Integer>, List<PoolStairsEntry>>() {
        @Override
        public ListenableFuture<List<PoolStairsEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllPoolStairs(input);
        }
    };

    AsyncFunction<List<Integer>, List<PoolEquipEntry>> asyncFunPoolEquip = new AsyncFunction<List<Integer>, List<PoolEquipEntry>>() {
        @Override
        public ListenableFuture<List<PoolEquipEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllPoolEquips(input);
        }
    };

//    Circulations

    AsyncFunction<List<Integer>, List<CirculationEntry>> asyncFunCirc = new AsyncFunction<List<Integer>, List<CirculationEntry>>() {
        @Override
        public ListenableFuture<List<CirculationEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllCirculation(schoolID);
        }
    };

    AsyncFunction<List<Integer>, List<Integer>> asyncCircID = new AsyncFunction<List<Integer>, List<Integer>>() {
        @Override
        public ListenableFuture<List<Integer>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllCircID(schoolID);
        }
    };

    AsyncFunction<List<Integer>, List<DoorEntry>> asyncFunCircDoor = new AsyncFunction<List<Integer>, List<DoorEntry>>() {
        @Override
        public ListenableFuture<List<DoorEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllCircDoors(input);
        }
    };

    AsyncFunction<List<Integer>, List<Integer>> asyncCircDoorID = new AsyncFunction<List<Integer>, List<Integer>>() {
        @Override
        public ListenableFuture<List<Integer>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllCircDoorsID(input);
        }
    };

    AsyncFunction<List<Integer>, List<DoorLockEntry>> asyncFunCircDoorLock = new AsyncFunction<List<Integer>, List<DoorLockEntry>>() {
        @Override
        public ListenableFuture<List<DoorLockEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllLocksFromDoor(input);
        }
    };

    AsyncFunction<List<Integer>, List<BlackboardEntry>> asyncFunCircBoard = new AsyncFunction<List<Integer>, List<BlackboardEntry>>() {
        @Override
        public ListenableFuture<List<BlackboardEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllCircBlackboards(input);
        }
    };

    AsyncFunction<List<Integer>, List<CounterEntry>> asyncFunCircCounter = new AsyncFunction<List<Integer>, List<CounterEntry>>() {
        @Override
        public ListenableFuture<List<CounterEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllCircCounters(input);
        }
    };

    AsyncFunction<List<Integer>, List<EquipmentEntry>> asyncFunCircEquip = new AsyncFunction<List<Integer>, List<EquipmentEntry>>() {
        @Override
        public ListenableFuture<List<EquipmentEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllCircEquipments(input);
        }
    };

    AsyncFunction<List<Integer>, List<FreeSpaceEntry>> asyncFunCircFrSp = new AsyncFunction<List<Integer>, List<FreeSpaceEntry>>() {
        @Override
        public ListenableFuture<List<FreeSpaceEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllCircFreeSpaces(input);
        }
    };

    AsyncFunction<List<Integer>, List<Integer>> asyncCircRampStID = new AsyncFunction<List<Integer>, List<Integer>>() {
        @Override
        public ListenableFuture<List<Integer>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllRampStIDCirc(input);
        }
    };

    AsyncFunction<List<Integer>, List<RampStairsEntry>> asyncFunCircRampSt = new AsyncFunction<List<Integer>, List<RampStairsEntry>>() {
        @Override
        public ListenableFuture<List<RampStairsEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllRampStCirc(input);
        }
    };

    AsyncFunction<List<Integer>, List<SingleStepEntry>> asyncFunCircStep = new AsyncFunction<List<Integer>, List<SingleStepEntry>>() {
        @Override
        public ListenableFuture<List<SingleStepEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllStepsCirc(input);
        }
    };

    AsyncFunction<List<Integer>, List<SlopeEntry>> asyncFunCircSlope = new AsyncFunction<List<Integer>, List<SlopeEntry>>() {
        @Override
        public ListenableFuture<List<SlopeEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListCircAllSlopes(input);
        }
    };

    AsyncFunction<List<Integer>, List<SwitchEntry>> asyncFunCircSwitch = new AsyncFunction<List<Integer>, List<SwitchEntry>>() {
        @Override
        public ListenableFuture<List<SwitchEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllCircSwitches(input);
        }
    };

    AsyncFunction<List<Integer>, List<TableEntry>> asyncFunCircTable = new AsyncFunction<List<Integer>, List<TableEntry>>() {
        @Override
        public ListenableFuture<List<TableEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllCircTables(input);
        }
    };

    AsyncFunction<List<Integer>, List<WindowEntry>> asyncFunCircWindow = new AsyncFunction<List<Integer>, List<WindowEntry>>() {
        @Override
        public ListenableFuture<List<WindowEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllCircWindows(input);
        }
    };

    AsyncFunction<List<Integer>, List<WaterFountainEntry>> asyncFunCircFount = new AsyncFunction<List<Integer>, List<WaterFountainEntry>>() {
        @Override
        public ListenableFuture<List<WaterFountainEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllCircWaterFountains(input);
        }
    };

    AsyncFunction<List<Integer>, List<FallProtectionEntry>> asyncFunCircFallProtect = new AsyncFunction<List<Integer>, List<FallProtectionEntry>>() {
        @Override
        public ListenableFuture<List<FallProtectionEntry>> apply(List<Integer> input) throws Exception {
            return modelEntry.getListAllFallProtection(input);
        }
    };

//    Future Callbacks

    FutureCallback<SchoolEntry> schoolDataFuture = new FutureCallback<SchoolEntry>() {
        @Override
        public void onSuccess(SchoolEntry result) {
            school = result;

            blockListener = Futures.transformAsync(schoolListener, asyncFunBlockList, service);
            Futures.addCallback(blockListener, blockDataFuture, service);

            ListenableFuture<List<Object>> successQueries = Futures.successfulAsList(blockDataQueries);
            Futures.addCallback(successQueries, jCreateFuture, service);
        }

        @Override
        public void onFailure(Throwable t) {
            Log.e("Callback SchoolData", t.toString(), t);
            school = null;
            blockDataQueries.clear();
            intParkQnt = 0;
            extParkQnt = 0;
            schoolID = 0;
            runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Lista Cancelada", Toast.LENGTH_SHORT).show());
            runOnUiThread(() -> showProgress(false));
        }
    };

    FutureCallback<List<BlockSpaceEntry>> blockDataFuture = new FutureCallback<List<BlockSpaceEntry>>() {
        @Override
        public void onSuccess(List<BlockSpaceEntry> result) {

            blockIDListener = Futures.submitAsync(asyncBlockID, service);

            roomIDListener = Futures.transformAsync(blockIDListener, asyncRoomID, service);
            roomDoorIDList = Futures.transformAsync(roomIDListener, asyncRoomDoorID, service);
            roomRampStIDList = Futures.transformAsync(roomIDListener, asyncRoomRampStID, service);
            roomFlightIDList = Futures.transformAsync(roomRampStIDList, asyncRampStFlightID, service);

            blockRoomList = Futures.transformAsync(blockIDListener, asyncFunRooms, service);
            roomDoorListener = Futures.transformAsync(roomIDListener, asyncFunRoomDoor, service);
            roomDoorLockListener = Futures.transformAsync(roomDoorIDList, asyncFunDoorLock, service);
            roomSwitchListener = Futures.transformAsync(roomIDListener, asyncFunRoomSwitch, service);
            roomWindowListener = Futures.transformAsync(roomIDListener, asyncFunRoomWindow, service);
            roomTableListener = Futures.transformAsync(roomIDListener, asyncFunRoomTable, service);
            roomBoardListener = Futures.transformAsync(roomIDListener, asyncFunRoomBoard, service);
            roomFreeSpListener = Futures.transformAsync(roomIDListener, asyncFunRoomFrSp, service);
            roomRampStairsListener = Futures.transformAsync(roomIDListener, asyncFunRoomRampSt, service);
            roomRampStFlightListener = Futures.transformAsync(roomRampStIDList, asyncFunRampStFlight, service);
            roomRampStRailListener = Futures.transformAsync(roomFlightIDList, asyncFunRampStRail, service);
            roomRampStHandListener = Futures.transformAsync(roomFlightIDList, asyncFunRampStHand, service);
            roomStepListener = Futures.transformAsync(roomIDListener, asyncFunRoomStep, service);
            roomSlopeListener = Futures.transformAsync(roomIDListener, asyncFunRoomSlope, service);
            roomFountainListener = Futures.transformAsync(roomIDListener, asyncFunRoomFount, service);
            roomEquipListener = Futures.transformAsync(roomIDListener, asyncFunRoomEquip, service);
            roomCounterListener = Futures.transformAsync(roomIDListener, asyncFunRoomCounter, service);

            extIDListener = Futures.transformAsync(blockIDListener, asyncExtID, service);
            extRampStIDList = Futures.transformAsync(extIDListener, asyncRoomRampStID, service);
            extFlightIDList = Futures.transformAsync(extRampStIDList, asyncRampStFlightID, service);

            extAccessList = Futures.transformAsync(extIDListener, asyncFunExt, service);
            extGateLockListener = Futures.transformAsync(extIDListener, asyncFunGateLock, service);
            gateObstacleListener = Futures.transformAsync(extIDListener, asyncFunGateObs, service);
            extPhoneListener = Futures.transformAsync(extIDListener, asyncFunGatePhone, service);
            extRampStairsListener = Futures.transformAsync(extIDListener, asyncFunGateRampSt, service);
            extRampStFlightListener = Futures.transformAsync(extRampStIDList, asyncFunRampStFlight, service);
            extRampStRailListener = Futures.transformAsync(extFlightIDList, asyncFunRampStRail, service);
            extRampStHandListener = Futures.transformAsync(extFlightIDList, asyncFunRampStHand, service);

            sideIDList = Futures.transformAsync(blockIDListener, asyncSideID, service);

            sidewalkListener = Futures.transformAsync(blockIDListener, asyncFunSidewalk, service);
            sideSlopeList = Futures.transformAsync(sideIDList, asyncFunSideSlope, service);
            sidePhoneList = Futures.transformAsync(sideIDList, asyncFunSidePhone, service);

            parkIDList = Futures.transformAsync(blockIDListener, asyncParkingID, service);
            parkRampStIDList = Futures.transformAsync(parkIDList, asyncParkRampStID, service);
            parkFlightIDList = Futures.transformAsync(parkRampStIDList, asyncRampStFlightID, service);

            parkingList = Futures.transformAsync(parkIDList, asyncFunParking, service);
            parkRampStairsListener = Futures.transformAsync(parkIDList, asyncFunParkRampSt, service);
            parkRampStFlightListener = Futures.transformAsync(parkIDList, asyncFunRampStFlight, service);
            parkRampStRailListener = Futures.transformAsync(parkIDList, asyncFunRampStRail, service);
            parkRampStHandListener = Futures.transformAsync(parkIDList, asyncFunRampStHand, service);
            parkElderListener = Futures.transformAsync(parkIDList, asyncFunParkElder, service);
            parkPCDListener = Futures.transformAsync(parkIDList, asyncFunParkPCD, service);

            playListener = Futures.transformAsync(blockIDListener, asyncFunPlay, service);

            restIDList = Futures.transformAsync(blockIDListener, asyncRestID, service);
            boxIDList = Futures.transformAsync(restIDList, asyncBoxID, service);

            restroomListener = Futures.transformAsync(blockIDListener, asyncFunRest, service);
            restDoorList = Futures.transformAsync(restIDList, asyncFunRestDoor, service);
            restFreeSpaceList = Futures.transformAsync(restIDList, asyncFunRestFrSp, service);
            restBoxList = Futures.transformAsync(restIDList, asyncFunRestBox, service);
            restBoxDoorList = Futures.transformAsync(boxIDList, asyncFunBoxDoor, service);

            blockFountList = Futures.transformAsync(blockIDListener, asyncFunFountain, service);

            poolIDList = Futures.transformAsync(blockIDListener, asyncPoolID, service);

            poolList = Futures.transformAsync(blockIDListener, asyncFunPool, service);
            poolRampListener = Futures.transformAsync(poolIDList, asyncFunPoolRamp, service);
            poolStairsListener = Futures.transformAsync(poolIDList, asyncFunPoolStairs, service);
            poolBenchListener = Futures.transformAsync(poolIDList, asyncFunPoolBench, service);
            poolEquipListener = Futures.transformAsync(poolIDList, asyncFunPoolEquip, service);

            circIDListener = Futures.transformAsync(blockIDListener, asyncCircID, service);
            circDoorIDList = Futures.transformAsync(circIDListener, asyncCircDoorID, service);
            circRampStIDList = Futures.transformAsync(circIDListener, asyncCircRampStID, service);
            circFlightIDList = Futures.transformAsync(circRampStIDList, asyncRampStFlightID, service);

            circListener = Futures.transformAsync(blockIDListener, asyncFunCirc, service);
            circDoorListener = Futures.transformAsync(circIDListener, asyncFunCircDoor, service);
            circDoorLockListener = Futures.transformAsync(circDoorIDList, asyncFunCircDoorLock, service);
            circSwitchListener = Futures.transformAsync(circIDListener, asyncFunCircSwitch, service);
            circWindowListener = Futures.transformAsync(circIDListener, asyncFunCircWindow, service);
            circTableListener = Futures.transformAsync(circIDListener, asyncFunCircTable, service);
            circBoardListener = Futures.transformAsync(circIDListener, asyncFunCircBoard, service);
            circFreeSpListener = Futures.transformAsync(circIDListener, asyncFunCircFrSp, service);
            circStepListener = Futures.transformAsync(circIDListener, asyncFunCircStep, service);
            circSlopeListener = Futures.transformAsync(circIDListener, asyncFunCircSlope, service);
            circFountainListener = Futures.transformAsync(circIDListener, asyncFunCircFount, service);
            circEquipListener = Futures.transformAsync(circIDListener, asyncFunCircEquip, service);
            circCounterListener = Futures.transformAsync(circIDListener, asyncFunCircCounter, service);
            circProtectListener = Futures.transformAsync(circIDListener, asyncFunCircFallProtect, service);
            circRampStListener = Futures.transformAsync(circIDListener, asyncFunCircRampSt, service);
            circRampStFlightListener = Futures.transformAsync(circRampStIDList, asyncFunRampStFlight, service);
            circRampStRailListener = Futures.transformAsync(circFlightIDList, asyncFunRampStRail, service);
            circRampStHandListener = Futures.transformAsync(circFlightIDList, asyncFunRampStHand, service);

            blockDataQueries.add(blockRoomList);
            blockDataQueries.add(roomDoorListener);
            blockDataQueries.add(roomDoorLockListener);
            blockDataQueries.add(roomSwitchListener);
            blockDataQueries.add(roomWindowListener);
            blockDataQueries.add(roomTableListener);
            blockDataQueries.add(roomBoardListener);
            blockDataQueries.add(roomFreeSpListener);
            blockDataQueries.add(roomRampStairsListener);
            blockDataQueries.add(roomRampStFlightListener);
            blockDataQueries.add(roomRampStRailListener);
            blockDataQueries.add(roomRampStHandListener);
            blockDataQueries.add(roomStepListener);
            blockDataQueries.add(roomSlopeListener);
            blockDataQueries.add(roomFountainListener);
            blockDataQueries.add(roomEquipListener);
            blockDataQueries.add(roomCounterListener);

            blockDataQueries.add(extAccessList);
            blockDataQueries.add(extGateLockListener);
            blockDataQueries.add(gateObstacleListener);
            blockDataQueries.add(extPhoneListener);
            blockDataQueries.add(extRampStairsListener);
            blockDataQueries.add(extRampStFlightListener);
            blockDataQueries.add(extRampStRailListener);
            blockDataQueries.add(extRampStHandListener);

            blockDataQueries.add(parkingList);
            blockDataQueries.add(parkRampStairsListener);
            blockDataQueries.add(parkRampStFlightListener);
            blockDataQueries.add(parkRampStRailListener);
            blockDataQueries.add(parkRampStHandListener);
            blockDataQueries.add(parkElderListener);
            blockDataQueries.add(parkPCDListener);

            blockDataQueries.add(playListener);

            blockDataQueries.add(restroomListener);
            blockDataQueries.add(restDoorList);
            blockDataQueries.add(restFreeSpaceList);
            blockDataQueries.add(restBoxList);
            blockDataQueries.add(restBoxDoorList);

            blockDataQueries.add(sidewalkListener);
            blockDataQueries.add(sideSlopeList);
            blockDataQueries.add(sidePhoneList);

            blockDataQueries.add(poolList);
            blockDataQueries.add(poolRampListener);
            blockDataQueries.add(poolStairsListener);
            blockDataQueries.add(poolBenchListener);
            blockDataQueries.add(poolEquipListener);

            blockDataQueries.add(blockFountList);

            blockDataQueries.add(circListener);
            blockDataQueries.add(circDoorListener);
            blockDataQueries.add(circDoorLockListener);
            blockDataQueries.add(circSwitchListener);
            blockDataQueries.add(circWindowListener);
            blockDataQueries.add(circTableListener);
            blockDataQueries.add(circBoardListener);
            blockDataQueries.add(circFreeSpListener);
            blockDataQueries.add(circStepListener);
            blockDataQueries.add(circSlopeListener);
            blockDataQueries.add(circFountainListener);
            blockDataQueries.add(circEquipListener);
            blockDataQueries.add(circCounterListener);
            blockDataQueries.add(circProtectListener);
            blockDataQueries.add(circRampStListener);
            blockDataQueries.add(circRampStFlightListener);
            blockDataQueries.add(circRampStHandListener);
            blockDataQueries.add(circRampStRailListener);
        }

        @Override
        public void onFailure(Throwable t) {
            Log.e("Callback blockListener", t.toString(), t);
            blockDataQueries.clear();
            intParkQnt = 0;
            extParkQnt = 0;
            schoolID = 0;
            runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Lista Cancelada", Toast.LENGTH_SHORT).show());
            runOnUiThread(() -> showProgress(false));
        }
    };

    FutureCallback<List<Object>> jCreateFuture = new FutureCallback<List<Object>>() {
        @Override
        public void onSuccess(List<Object> result) {
            try {
                JsonCreation jCreate = new JsonCreation(school, blockList, blockQnt, extAccessList.get(), extGateLockListener.get(), gateObstacleListener.get(),
                        extPhoneListener.get(), extRampStairsListener.get(), extRampStFlightListener.get(), extRampStRailListener.get(), extRampStHandListener.get(),
                        sidewalkListener.get(), sidePhoneList.get(), sideSlopeList.get(), hasHelpSpace, playListener.get(), poolList.get(), poolRampListener.get(),
                        poolStairsListener.get(), poolBenchListener.get(), poolEquipListener.get(), extParkQnt, intParkQnt, parkingList.get(), parkRampStairsListener.get(),
                        parkRampStFlightListener.get(), parkRampStRailListener.get(), parkRampStHandListener.get(), parkElderListener.get(), parkPCDListener.get(),
                        blockFountList.get(), blockRoomList.get(), roomBoardListener.get(), roomCounterListener.get(), roomDoorListener.get(), roomDoorLockListener.get(),
                        restFreeSpaceList.get(), roomSwitchListener.get(), roomTableListener.get(), roomWindowListener.get(), roomFountainListener.get(),
                        roomEquipListener.get(), roomStepListener.get(), roomSlopeListener.get(), roomRampStairsListener.get(), roomRampStFlightListener.get(),
                        roomRampStRailListener.get(), roomRampStHandListener.get(), restroomListener.get(), restDoorList.get(), restFreeSpaceList.get(), restBoxList.get(),
                        restBoxDoorList.get(), circListener.get(), circDoorListener.get(), circDoorLockListener.get(), circSwitchListener.get(), circWindowListener.get(),
                        circTableListener.get(), circBoardListener.get(), circFreeSpListener.get(), circStepListener.get(), circSlopeListener.get(), circFountainListener.get(),
                        circEquipListener.get(), circCounterListener.get(), circProtectListener.get(), circRampStListener.get(), circRampStFlightListener.get(),
                        circRampStRailListener.get(), circRampStHandListener.get(), null, null, null, null);
                tData = jCreate.createJson();
                address = new String[]{jCreate.getSchool().getEmailAddress()};
                List<String> blockList = jCreate.ambListCreator();
                upText.setJsonCreation(jCreate, blockList);
                upText.newFileName();
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                    ListenableFuture<?> future = service.submit(() -> {
                        handler.post(() -> upText.fillDocFields(tData, fileUri, getApplicationContext()));
                    });

                    Futures.addCallback(future, sendReport, service);
                } else {
                    filePath = upText.filePath;
                    fillCreatedDocxFile.launch(upText.fName);
                }

            } catch (ExecutionException | InterruptedException e) {
                Log.e("Callback jCreate", e.toString(), e);
                e.printStackTrace();
                blockDataQueries.clear();
                intParkQnt = 0;
                extParkQnt = 0;
                schoolID = 0;
                runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Lista Cancelada", Toast.LENGTH_SHORT).show());
                runOnUiThread(() -> showProgress(false));
            }
        }

        @Override
        public void onFailure(Throwable t) {
            Log.e("Callback successList", t.toString(), t);
            blockDataQueries.clear();
            intParkQnt = 0;
            extParkQnt = 0;
            schoolID = 0;
            runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Lista Cancelada", Toast.LENGTH_SHORT).show());
            runOnUiThread(() -> showProgress(false));
        }
    };

    FutureCallback<Object> sendReport = new FutureCallback<Object>() {
        @Override
        public void onSuccess(Object result) {
            sendEmailIntent(fileUri, address, context);
        }

        @Override
        public void onFailure(Throwable t) {
            Log.e("Callback Report", t.toString(), t);
            showProgress(false);
            Toast.makeText(getApplicationContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instantiateViews();

        modelEntry.getAllEntries().observe(this, schoolEntries -> {

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

        fillCreatedDocxFile = registerForActivityResult(new CreateDocumentDaex(), result -> {
            fileUri = result;
            ListenableFuture<?> future = service.submit(() -> {
                handler.post(() -> upText.fillDocFields(tData, fileUri, getApplicationContext()));
            });

            Futures.addCallback(future, sendReport, service);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

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
        context = recyclerView.getContext();

        fab = findViewById(R.id.fab_register);

        modelEntry = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this.getApplication()).create(ViewModelEntry.class);

        service = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
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
        SchoolEntry schoolEntry = modelEntry.getAllEntries().getValue().get(position);
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
        SchoolEntry schoolEntry = modelEntry.getAllEntries().getValue().get(position);
        schoolID = schoolEntry.getCadID();

        if (menuItem.getItemId() == R.id.edit_school) {
            Intent updateIntent = new Intent(MainActivity.this, SchoolRegisterActivity.class);
            updateIntent.putExtra(SCHOOL_ID, schoolID);
            if (actionMode != null)
                actionMode.finish();
            startActivity(updateIntent);
        } else if (menuItem.getItemId() == R.id.generate_report) {
            runOnUiThread(() -> showProgress(true));

            schoolListener = Futures.submitAsync(asyncSchool, service);
            Futures.addCallback(schoolListener, schoolDataFuture, service);
        }
    }

    public void sendEmailIntent(Uri uri, String[] address, Context context) {
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
        endRegister = 1;
        runOnUiThread(() -> showProgress(false));
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


}