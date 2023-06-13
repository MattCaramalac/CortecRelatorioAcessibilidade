package com.mpms.relatorioacessibilidadecortec.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.common.util.concurrent.ListenableFuture;
import com.mpms.relatorioacessibilidadecortec.data.database.ReportDatabase;
import com.mpms.relatorioacessibilidadecortec.data.database.ReportRepository;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlackboardEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlockSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.CirculationEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.CounterEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorLockEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.EquipmentEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExtAccessSocialOne;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExtAccessSocialThree;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExtAccessSocialTwo;
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
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolEntryOne;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolEntryTwo;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolEquipEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolRampEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsFlightEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsHandrailEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsRailingEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestAccessEntranceUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestAccessUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestAccessUpdateTwo;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxAccOneUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxAccTwoUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxFirstUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxSinkUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxToilUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxUpViewUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestColFirstUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestSinkUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestToiletUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestUpViewUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestUrinalUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolRegisterOne;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolRegisterThree;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolRegisterTwo;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntryOne;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntryTwo;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkSlopeEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SingleStepEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SlopeEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SwitchEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.TableEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WindowEntry;

import java.util.List;

public class ViewModelEntry extends AndroidViewModel {

    public static ReportRepository repository;
    public LiveData<List<ExternalAccess>> allExtAccSchool;
    public LiveData<List<WaterFountainEntry>> allFountains;
    public LiveData<List<RestroomEntry>> allRestSchool;
    public LiveData<List<ParkingLotEntry>> allParkingLots;
    public LiveData<List<SidewalkEntry>> allSidewalks;
    public LiveData<List<SidewalkSlopeEntry>> allSidewalkSlopes;
    public LiveData<List<RampStairsEntry>> allStairsRampsSchool;
    public LiveData<List<RampStairsFlightEntry>> allFlights;
    public LiveData<List<RampStairsHandrailEntry>> allHandrails;
    public LiveData<List<RampStairsRailingEntry>> allRails;
    public LiveData<List<ParkingLotPCDEntry>> allPcdLots;
    public LiveData<List<ParkingLotElderlyEntry>> allElderLots;
    public LiveData<List<RoomEntry>> allRooms;
    public LiveData<List<BlockSpaceEntry>> allBlockSpaces;
    public LiveData<List<PayPhoneEntry>> allPayPhonesExtAccess;
    public LiveData<List<PayPhoneEntry>> allPayPhonesSidewalk;
    public LiveData<List<GateObsEntry>> allGateObs;
    public LiveData<List<PlaygroundEntry>> allPlaygrounds;
    public LiveData<List<DoorEntry>> allDoors;
    public LiveData<List<SwitchEntry>> allSwitches;
    public LiveData<List<WindowEntry>> allWindows;
    public LiveData<List<TableEntry>> allTables;
    public LiveData<List<FreeSpaceEntry>> allFreeSpaces;
    public LiveData<List<FreeSpaceEntry>> allRestFreeSpaces;
    public LiveData<List<BlackboardEntry>> allBlackboards;
    public LiveData<List<CounterEntry>> allCounters;
    public LiveData<List<DoorLockEntry>> allDoorLocks;
    public LiveData<List<DoorLockEntry>> allDoorLocksGates;
    public LiveData<List<RestBoxEntry>> allBoxes;
    public LiveData<List<EquipmentEntry>> allEquips;
    public LiveData<List<CirculationEntry>> allCirculations;
    public LiveData<List<SingleStepEntry>> allSteps;
    public LiveData<List<SlopeEntry>> allSlopes;
    public LiveData<List<FallProtectionEntry>> allFallProtect;
    public LiveData<List<PoolEntry>> allPools;
    public LiveData<List<PoolBenchEntry>> allPoolBenches;
    public LiveData<List<PoolRampEntry>> allPoolRamps;
    public LiveData<List<PoolStairsEntry>> allPoolStairs;
    public LiveData<List<PoolEquipEntry>> allPoolEquip;
    public final LiveData<List<SchoolEntry>> allEntries;

    public LiveData<ExternalAccess> oneAccess;

    public ViewModelEntry(@NonNull Application application) {
        super(application);
        repository = new ReportRepository(application);
        allEntries = repository.getAllSchoolEntries();
    }

    public static void insertSchool(SchoolEntry schoolEntry) {
        repository.insertSchoolEntry(schoolEntry);
    }

    public static void updateSchoolRegOne(SchoolRegisterOne... regOne) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateSchoolRegOne(regOne));
    }

    public static void updateSchoolRegTwo(SchoolRegisterTwo... regTwo) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateSchoolRegTwo(regTwo));
    }

    public static void updateSchoolRegThree(SchoolRegisterThree... regThree) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateSchoolRegThree(regThree));
    }

    public static void deleteOneSchoolEntry(SchoolEntry schoolEntry) {
        repository.deleteOneSchoolEntry(schoolEntry);
    }

    public static void delSchoolWithID(int schID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.delSchoolWithID(schID));
    }

    public static void deleteAllSchool() {
        repository.deleteAllEntries();
    }

    public static void insertWaterFountain(WaterFountainEntry waterFountainEntry) {
        repository.insertWaterFountain(waterFountainEntry);
    }

    public static void updateWaterFountain(WaterFountainEntry waterFountainEntry) {
        repository.updateWaterFountain(waterFountainEntry);
    }

    public static void deleteOneWaterFountain(int waterFountainID) {
        repository.deleteOneWaterFountain(waterFountainID);
    }

    public static void deleteAllFountainsFromSchool(int schoolID) {
        repository.deleteAllFountainsFromSchool(schoolID);
    }

    public static void insertExternalAccess(ExternalAccess externalAccess) {
        repository.insertExternalAccess(externalAccess);
    }

    public static void updateExternalAccess(ExternalAccess externalAccess) {
        repository.updateExternalAccess(externalAccess);
    }

    public static void updateExtAccessRegOne(ExtAccessSocialOne... regOne) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateExtAccessRegOne(regOne));
    }

    public static void updateExtAccessRegTwo(ExtAccessSocialTwo... regTwo) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateExtAccessRegTwo(regTwo));
    }

    public static void updateExtAccessRegThree(ExtAccessSocialThree... regThree) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateExtAccessRegThree(regThree));
    }

    public static void deleteOneExternalAccess(int externalAccessID) {
        repository.deleteOneExternalAccess(externalAccessID);
    }

    public static void deleteAllExternalAccessesFromBlock(int blockID) {
        repository.deleteAllExternalAccesses(blockID);
    }

    public static void insertParkingLot(ParkingLotEntry parkingLotEntry) {
        repository.insertParkingLot(parkingLotEntry);
    }

    public static void updateParkingLot(ParkingLotEntry parkingLotEntry) {
        repository.updateParkingLot(parkingLotEntry);
    }

    public static void deleteOneParkingLot(int parkingLotID) {
        repository.deleteOneParkingLot(parkingLotID);
    }

    public static void deleteAllParkingLots(int schoolID) {
        repository.deleteAllParkingLots(schoolID);
    }

    public static void insertElderlyParkingLot(ParkingLotElderlyEntry elderlyEntry) {
        repository.insertElderlyParkingLot(elderlyEntry);
    }

    public static void updateElderlyParkingLot(ParkingLotElderlyEntry parkingLotElderlyEntry) {
        repository.updateElderlyParkingLot(parkingLotElderlyEntry);
    }

    public static void deleteElderlyParkingLot(int parkingLotID) {
            repository.deleteElderlyParkingLot(parkingLotID);
    }

    public static void insertPcdParkingLot(ParkingLotPCDEntry pcdEntry) {
        repository.insertPcdParkingLot(pcdEntry);
    }

    public static void updatePcdParkingLot(ParkingLotPCDEntry pcdEntry) {
        repository.updatePcdParkingLot(pcdEntry);
    }

    public static void deletePdmrParkingLot(int parkingLotID) {
            repository.deletePcdParkingLot(parkingLotID);
    }

    //Quando vou retornar o valor, preciso colocar a variável com o valor, NÃO o método.
    public LiveData<List<SchoolEntry>> getAllEntries() {
        return allEntries;
    }

    public LiveData<SchoolEntry> getEntry(int cadID) {
        return repository.getSchoolEntry(cadID);
    }

    public LiveData<SchoolEntry> getLastEntry() {
        return repository.getLastSchoolEntry();
    }

    public LiveData<List<WaterFountainEntry>> getAllBlockWaterFountain(int blockID) {
        allFountains = repository.getAllBlockWaterFountains(blockID);
        return allFountains;
    }

    public static LiveData<List<WaterFountainEntry>> getAllWaterFountains(List<Integer> blockID) {
        return repository.getAllWaterFountains(blockID);
    }

    public LiveData<List<WaterFountainEntry>> getRoomWaterFountains(int roomID) {
        allFountains = repository.getRoomWaterFountains(roomID);
        return allFountains;
    }

    public static LiveData<List<WaterFountainEntry>> getAllRoomWaterFountains(List<Integer> roomID) {
        return repository.getAllRoomWaterFountains(roomID);
    }

    public LiveData<WaterFountainEntry> getOneWaterFountain(int waterFountainID) {
        return repository.getOneWaterFountain(waterFountainID);
    }

    public LiveData<List<WaterFountainEntry>> getCircWaterFountains(int circID) {
        allFountains = repository.getCircWaterFountains(circID);
        return allFountains;
    }

    public static LiveData<List<WaterFountainEntry>> getAllCircWaterFountains(List<Integer> circID) {
        return repository.getAllCircWaterFountains(circID);
    }

    public LiveData<List<ExternalAccess>> getAllExternalAccessesInOneBlock(int blockID) {
        allExtAccSchool = repository.getAllExternalAccessesInSchool(blockID);
        return allExtAccSchool;
    }

    public static LiveData<List<ExternalAccess>> getAllExtAccess(List<Integer> blockID) {
        return repository.getAllExtAccess(blockID);
    }

    public LiveData<ExternalAccess> getOneExternalAccess(int externalAccessID) {
        oneAccess = repository.getOneExternalAccess(externalAccessID);
        return oneAccess;
    }

    public LiveData<ExternalAccess> getLastExternalAccess() {
        return repository.getLastExternalAccess();
    }

    public LiveData<List<ParkingLotEntry>> getParkingLotsFromBlocks(int schoolEntryID) {
        allParkingLots = repository.getParkingLotFromBlock(schoolEntryID);
        return allParkingLots;
    }

    public LiveData<List<ParkingLotEntry>> getParkingLotFromSide(int blockID, int sideID) {
        return repository.getParkingLotFromSide(blockID, sideID);
    }

    public static LiveData<List<ParkingLotEntry>> getAllParkingLots(List<Integer> blockID) {
        return repository.getAllParkingLots(blockID);
    }

    public LiveData<ParkingLotEntry> getOneParkingLot(int parkingLotID) {
        return repository.selectOneParkingLot(parkingLotID);
    }

    public LiveData<ParkingLotEntry> getLastInsertedParkingLot() {
        return repository.selectLastInsertedParkingLot();
    }

    public LiveData<List<ParkingLotElderlyEntry>> getElderVacanciesPark(int parkingLotID) {
        allElderLots = repository.getElderVacanciesPark(parkingLotID);
        return allElderLots;
    }

    public static LiveData<List<ParkingLotElderlyEntry>> getAllElderVacancies(List<Integer> parkingLotID) {
        return repository.getAllElderVacancies(parkingLotID);
    }

    public LiveData<List<ParkingLotPCDEntry>> getPcdVacanciesPark(int parkingLotID) {
        allPcdLots = repository.getPcdVacanciesPark(parkingLotID);
        return allPcdLots;
    }

    public static LiveData<List<ParkingLotPCDEntry>> getAllPcdVacancies(List<Integer> parkingLotID) {
        return repository.getAllPcdVacancies(parkingLotID);
    }

    public LiveData<ParkingLotElderlyEntry> getOneElderVacancy(int parkingElderlyID) {
        return repository.getOneElderVacancy(parkingElderlyID);
    }

    public LiveData<ParkingLotPCDEntry> getOnePcdVacancy(int parkingPcdID) {
        return repository.getOnePcdVacancy(parkingPcdID);
    }

    public static void insertRoomEntry(RoomEntry roomEntry) {
       repository.insertRoomEntry(roomEntry);
    }

    public LiveData<List<RoomEntry>> getAllRoomsInBlock(int schoolID, int roomType) {
        allRooms = repository.getAllRoomsInSchool(schoolID, roomType);
        return  allRooms;
    }

    public static LiveData<List<RoomEntry>> getAllRoomsInSchool(List<Integer> blockID) {
        return  repository.getAllRoomsInSchool(blockID);
    }

    public LiveData<List<RoomEntry>> getAllRoomsInSchool(int blockID) {
        return  repository.getAllRoomsInSchool(blockID);
    }

    public LiveData<RoomEntry> getRoomEntry(int roomID) {
        return repository.getRoomEntry(roomID);
    }

    public LiveData<RoomEntry> getLastRoomEntry() {
        return repository.getLastRoomEntry();
    }

    public static void updateRoom(RoomEntry roomEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateRoom(roomEntry));
    }

    public static void deleteRoom(int roomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteRoom(roomID));
    }

    public static void insertDoorEntry(DoorEntry doorEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertDoorEntry(doorEntry));
    }

    public  LiveData<List<DoorEntry>> getDoorsFromRoom(int roomID) {
        allDoors = repository.getDoorsFromRoom(roomID);
        return allDoors;
    }

    public static LiveData<List<DoorEntry>> getAllDoors(List<Integer> roomID) {
        return repository.getAllDoors(roomID);
    }

    public static LiveData<List<DoorEntry>> getAllRestDoors(List<Integer> restID) {
        return repository.getAllRestDoors(restID);
    }

    public LiveData<List<DoorEntry>> getDoorsFromCirc(int circID) {
        allDoors = repository.getCircDoor(circID);
        return allDoors;
    }

    public static LiveData<List<DoorEntry>> getAllCircDoors(List<Integer> circID) {
        return repository.getAllCircDoors(circID);
    }
    public static LiveData<List<DoorEntry>> getAllBoxDoors(List<Integer> boxID) {
        return repository.getAllBoxDoors(boxID);
    }

    public LiveData<DoorEntry> getSpecificDoor(int doorId) {
        return repository.getSpecificDoor(doorId);
    }

    public LiveData<DoorEntry> getRestDoor(int restID) {
        return repository.getRestDoor(restID);
    }

    public LiveData<DoorEntry> getAccBoxDoor(int boxID) {
        return repository.getAccBoxDoor(boxID);
    }

    public LiveData<DoorEntry> getLastDoorEntry() {
        return repository.getLastDoorEntry();
    }

    public static void updateDoor(DoorEntry doorEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateDoor(doorEntry));
    }

    public static void deleteDoor(int doorID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteDoor(doorID));
    }

    public void deleteRestDoor(int restID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteRestDoor(restID));
    }

    public static void deleteAllDoorsFromRoom(int schoolID, int roomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllDoorsFromRoom(roomID));
    }

    public static void insertFreeSpaceEntry(FreeSpaceEntry freeSpace) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertFreeSpace(freeSpace));
    }

    public LiveData<List<FreeSpaceEntry>> getFreeSpaceFromRoom(int roomID) {
        allFreeSpaces = repository.getFreeSpaceFromRoom(roomID);
        return allFreeSpaces;
    }

    public LiveData<List<FreeSpaceEntry>> getFreeSpaceFromRest(int restID) {
        allRestFreeSpaces = repository.getFreeSpaceFromRest(restID);
        return allRestFreeSpaces;
    }

    public static LiveData<List<FreeSpaceEntry>> getAllFreeSpaces(List<Integer> roomID) {
        return repository.getAllFreeSpaces(roomID);
    }

    public static LiveData<List<FreeSpaceEntry>> getAllRestFreeSpaces(List<Integer> restID) {
        return repository.getAllRestFreeSpaces(restID);
    }

    public LiveData<List<FreeSpaceEntry>> getFreeSpaceFromCirc(int circID) {
        allFreeSpaces =  repository.getFreeSpaceFromCirc(circID);
        return allFreeSpaces;
    }

    public static LiveData<List<FreeSpaceEntry>> getAllCircFreeSpaces(List<Integer> circID) {
        return repository.getAllCircFreeSpaces(circID);
    }

    public LiveData<FreeSpaceEntry> getSpecificFreeSpace(int freeSpaceID) {
        return repository.getSpecificFreeSpace(freeSpaceID);
    }

    public static void updateFreeSpace(FreeSpaceEntry freeSpace) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateFreeSpace(freeSpace));
    }

    public static void deleteFreeSpace(int freeSpaceID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteFreeSpace(freeSpaceID));
    }

    public static void deleteAllFreeSpaceFromRoom(int roomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllFreeSpaceFromRoom(roomID));
    }

    public static void insertWindowEntry(WindowEntry windowEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertWindow(windowEntry));
    }

    public LiveData<List<WindowEntry>> getWindowsFromRoom(int roomID) {
        allWindows = repository.getWindowsFromRoom(roomID);
        return allWindows;
    }

    public static LiveData<List<WindowEntry>> getAllRoomsWindows(List<Integer> roomID) {
        return repository.getAllRoomsWindows(roomID);
    }

    public LiveData<List<WindowEntry>> getWindowsFromCirc(int circID) {
        allWindows = repository.getWindowsFromCirc(circID);
        return allWindows;
    }

    public static LiveData<List<WindowEntry>> getAllCircWindows(List<Integer> circID) {
        return repository.getAllCircWindows(circID);
    }

    public LiveData<WindowEntry> selectSpecificWindow(int windowID) {
        return repository.selectSpecificWindow(windowID);
    }

    public static void updateWindowEntry(WindowEntry windowEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateWindowEntry(windowEntry));
    }

    public static void deleteWindow(int windowID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteWindow(windowID));
    }

    public static void deleteAllWindowsFromRoom(int roomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllWindowsFromRoom(roomID));
    }

    public static void insertSwitchEntry(SwitchEntry switchEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertSwitch(switchEntry));
    }

    public LiveData<List<SwitchEntry>> getSwitchesFromRoom(int roomID) {
        allSwitches = repository.getSwitchesFromRoom(roomID);
        return allSwitches;
    }

    public static LiveData<List<SwitchEntry>> getAllRoomsSwitches(List<Integer> roomID) {
        return repository.getAllRoomsSwitches(roomID);
    }

    public LiveData<List<SwitchEntry>> getSwitchesFromCirc(int circID) {
        allSwitches = repository.getSwitchesFromCirc(circID);
        return allSwitches;
    }

    public static LiveData<List<SwitchEntry>> getAllCircSwitches(List<Integer> circID) {
        return repository.getAllCircSwitches(circID);
    }

    public LiveData<SwitchEntry> getSpecificSwitch(int switchID) {
        return repository.selectSpecificSwitch(switchID);
    }

    public static void updateSwitch(SwitchEntry switchEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateSwitch(switchEntry));
    }

    public static void deleteSwitch(int switchID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteSwitch(switchID));
    }

    public static void deleteAllSwitchesFromRoom(int roomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllSwitchesFromRoom(roomID));
    }

    public static void insertTablesEntry(TableEntry tableEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertTable(tableEntry));
    }

    public LiveData<List<TableEntry>> getTablesFromRoom(int roomID) {
        allTables = repository.getTablesFromRoom(roomID);
        return allTables;
    }

    public static LiveData<List<TableEntry>> getAllRoomsTables(List<Integer> roomID) {
        return repository.getAllRoomsTables(roomID);
    }

    public LiveData<List<TableEntry>> getTablesFromCirc(int circID) {
        allTables = repository.getTablesFromCirc(circID);
        return allTables;
    }

    public static LiveData<List<TableEntry>> getAllCircTables(List<Integer> circID) {
        return repository.getAllCircTables(circID);
    }

    public LiveData<TableEntry> getSpecificTable(int tableID) {
        return repository.getSpecificTable(tableID);
    }

    public static void updateTable(TableEntry table) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateTable(table));
    }

    public static void deleteTable(int tableID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteTable(tableID));
    }

    public static void deleteAllTablesFromRoom(int roomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllTablesFromRoom(roomID));
    }

    public static void insertGateObs (GateObsEntry gateObs) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertGateObs(gateObs));
    }

    public LiveData<List<GateObsEntry>> getAllGateObsEntries(int externalAccessID) {
        allGateObs = repository.selectAllGateObsEntries(externalAccessID);
        return allGateObs;
    }

    public LiveData<GateObsEntry> getOneGateObsEntry(int gateObsID) {
        return repository.selectGateObsEntry(gateObsID);
    }

    public static LiveData<List<GateObsEntry>> getAllGateObs(List<Integer> gateObsID) {
        return repository.getAllGates(gateObsID);
    }

    public static void updateGateObs (GateObsEntry gateObs) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateGateObs(gateObs));
    }

    public static void deleteGateObsEntry(int gateObsID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteGateObsEntry(gateObsID));
    }

    public static void deleteAllGateObsEntries(int externalAccessID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllGateObsEntries(externalAccessID));
    }

    public static void insertPayPhone (PayPhoneEntry payPhone) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertPayPhone(payPhone));
    }

    public LiveData<List<PayPhoneEntry>> getPayPhonesExtAccess(int externalAccessID) {
        allPayPhonesExtAccess = repository.getPayPhonesExtAccess(externalAccessID);
        return allPayPhonesExtAccess;
    }

    public LiveData<List<PayPhoneEntry>> getPayPhonesSidewalk(int sidewalkID) {
        allPayPhonesSidewalk = repository.getPhonesSidewalk(sidewalkID);
        return allPayPhonesSidewalk;
    }

    public void deleteAllPayPhonesSidewalk(int sidewalkID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllPayPhonesSidewalk(sidewalkID));
    }

    public LiveData<PayPhoneEntry> getPayPhoneEntry(int payPhoneID) {
        return repository.getPayPhoneEntry(payPhoneID);
    }

    public static LiveData<List<PayPhoneEntry>> getAllPhonesExtAccess(List<Integer> externalAccessID) {
        return repository.getAllPhonesExtAccess(externalAccessID);
    }

    public static LiveData<List<PayPhoneEntry>> getAllPhonesSidewalk(List<Integer> sidewalkID) {
        return repository.getAllPhonesSidewalk(sidewalkID);
    }

    public static void updatePayPhone (PayPhoneEntry payPhone) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updatePayPhone(payPhone));
    }

    public static void deletePayPhoneEntry(int payPhoneID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deletePayPhoneEntry(payPhoneID));
    }

    public static void deleteAllPayPhones(int externalAccessID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllPayPhones(externalAccessID));
    }

    public static void insertCounter(CounterEntry counter) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertCounter(counter));
    }

    public LiveData<List<CounterEntry>> getCountersFromRoom(int roomID) {
        allCounters = repository.getCountersFromRoom(roomID);
        return allCounters;
    }

    public static LiveData<List<CounterEntry>> getAllCounters(List<Integer> roomID) {
        return repository.getAllCounters(roomID);
    }

    public LiveData<List<CounterEntry>> getCountersFromCirc(int circID) {
        allCounters = repository.getCountersFromCirc(circID);
        return allCounters;
    }

    public static LiveData<List<CounterEntry>> getAllCircCounters(List<Integer> circID) {
        return repository.getAllCircCounters(circID);
    }

    public LiveData<CounterEntry> getSpecificCounter(int counterID) {
        return repository.getSpecificCounter(counterID);
    }

    public static void updateCounter (CounterEntry counter) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateCounter(counter));
    }

    public static void deleteCounter(int counterID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteCounter(counterID));
    }

    public static void deleteAllCounterFromRoom(int counterID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllCounterFromRoom(counterID));
    }

    public static void insertEquip(EquipmentEntry equip) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertEquip(equip));
    }

    public LiveData<List<EquipmentEntry>> getEquipmentFromRoom(int roomID) {
        allEquips = repository.getEquipmentFromRoom(roomID);
        return allEquips;
    }

    public static LiveData<List<EquipmentEntry>> getAllEquipments(List<Integer> roomID) {
        return repository.getAllEquipments(roomID);
    }

    public LiveData<List<EquipmentEntry>> getEquipmentFromCirc(int circID) {
        allEquips = repository.getEquipmentFromCirc(circID);
        return allEquips;
    }

    public static LiveData<List<EquipmentEntry>> getAllCircEquipments(List<Integer> circID) {
        return repository.getAllCircEquipments(circID);
    }

    public LiveData<EquipmentEntry> getSpecificEquipment(int equipID) {
        return repository.getSpecificEquipment(equipID);
    }

    public static void updateEquipment (EquipmentEntry equip) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateEquipment(equip));
    }

    public static void deleteEquipment(int equipID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteEquipment(equipID));
    }

    public void deleteAllEquipsFromRoom(int roomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllEquipsFromRoom(roomID));
    }

    public static void insertRampStairs(RampStairsEntry ramp) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertRampStairs(ramp));
    }

    public LiveData<List<RampStairsEntry>> getStairsRampFromExtAccess(int ambientID, int rampOrStairs) {
        allStairsRampsSchool = repository.getStairsRampFromExtAccess(ambientID, rampOrStairs);
        return allStairsRampsSchool;
    }

    public LiveData<List<RampStairsEntry>> getStairsRampFromParking(int ambientID, int rampOrStairs) {
        allStairsRampsSchool = repository.getStairsRampFromParking(ambientID, rampOrStairs);
        return allStairsRampsSchool;
    }


    public LiveData<List<RampStairsEntry>> getStairsRampFromRoom(int ambientID, int rampOrStairs){
        allStairsRampsSchool = repository.getStairsRampFromRoom(ambientID, rampOrStairs);
        return allStairsRampsSchool;
    }

    public LiveData<List<RampStairsEntry>> getStairsRampFromCirculation(int ambientID, int rampOrStairs) {
        allStairsRampsSchool = repository.getStairsRampFromCirculation(ambientID, rampOrStairs);
        return allStairsRampsSchool;
    }

    public LiveData<RampStairsEntry> getRampStairsEntry(int rampStairsID) {
        return repository.getRampStairsEntry(rampStairsID);
    }

    public static LiveData<List<RampStairsEntry>> getAllRampStRoom(List<Integer> roomID) {
        return repository.getAllRampStRoom(roomID);
    }

    public static LiveData<List<RampStairsEntry>> getAllRampStExt(List<Integer> extID) {
        return repository.getAllRampStExt(extID);
    }

    public static LiveData<List<RampStairsEntry>> getAllRampStCirc(List<Integer> circID) {
        return repository.getAllRampStCirc(circID);
    }

    public static LiveData<List<RampStairsEntry>> getAllRampStPark(List<Integer> parkID) {
        return repository.getAllRampStPark(parkID);
    }

    public LiveData<RampStairsEntry> getLastRampStairsEntry() {
        return repository.getLastRampStairsEntry();
    }

    public static void deleteOneRampStairs(int rampStairsID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteOneRampStairs(rampStairsID));
    }

    public static void updateRampStairs(RampStairsEntry rampStairs) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateRampStairs(rampStairs));
    }

    public static void insertRampsStairsFlight(RampStairsFlightEntry flight) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertRampsStairsFlight(flight));
    }

    public LiveData<List<RampStairsFlightEntry>> getRampStairsFlights(int rampStairsID) {
        allFlights = repository.getRampStairsFlights(rampStairsID);
        return allFlights;
    }

    public static LiveData<List<RampStairsFlightEntry>> getAllFlights(List<Integer> rampStairsID) {
        return repository.getAllFlights(rampStairsID);
    }

    public LiveData<RampStairsFlightEntry> getRampStairsFlightEntry(int flightID) {
        return repository.getRampStairsFlightEntry(flightID);
    }

    public LiveData<RampStairsFlightEntry> getLastRampStairsFlightEntry() {
        return repository.getLastRampStairsFlightEntry();
    }

    public static void deleteOneFlight(int flightID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteOneFlight(flightID));
    }

    public static void deleteAllFlightsFromOneRampsStairs(int rampStairsID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllFlightsFromOneRampsStairs(rampStairsID));
    }

    public static void updateFlightRampStairs(RampStairsFlightEntry flight) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateFlightRampStairs(flight));
    }

    public static void insertRestroomEntry(RestroomEntry restroom) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertRestroomEntry(restroom));
    }

    public LiveData<List<RestroomEntry>> getAllRestEntriesInBlock(int schoolID) {
        allRestSchool = repository.getAllRestEntriesInBlock(schoolID);
        return allRestSchool;
    }

    public static LiveData<List<RestroomEntry>> getAllRestEntries(List<Integer> blockID) {
        return repository.getAllRestEntries(blockID);
    }

    public LiveData<RestroomEntry> getOneRestroomEntry(int restroomID) {
        return repository.getOneRestroomEntry(restroomID);
    }

    public LiveData<RestroomEntry> getRestFirstData(int restID) {
        return repository.getRestFirstData(restID);
    }

    public LiveData<RestroomEntry> getRestUpViewData(int restID) {
        return repository.getRestUpViewData(restID);
    }

    public LiveData<RestroomEntry> getRestColDoorData(int restID) {
        return repository.getRestColDoorData(restID);
    }

    public LiveData<RestroomEntry> getRestToiletData(int restID) {
        return repository.getRestToiletData(restID);
    }

    public LiveData<RestroomEntry> getRestAccessData(int restID) {
        return repository.getRestAccessData(restID);
    }

    public LiveData<RestroomEntry> getRestAccessDataTwo(int restID) {
        return repository.getRestAccessDataTwo(restID);
    }

    public LiveData<RestroomEntry> getRestSinkData(int restID) {
        return repository.getRestSinkData(restID);
    }

    public LiveData<RestroomEntry> getRestCounterSinkData(int restID) {
        return repository.getRestCounterSinkData(restID);
    }

    public LiveData<RestroomEntry> getRestUrinalData(int restID) {
        return repository.getRestUrinalData(restID);
    }

    public LiveData<RestroomEntry> getLastRestroomEntry() {
        return repository.getLastRestroomEntry();
    }

    public static void updateRestroomData(RestColFirstUpdate... restColFirstUpdates) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateRestroomData(restColFirstUpdates));
    }

    public static void updateAccessRestData(RestAccessEntranceUpdate... restAccessUpdates) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateAccessRestData(restAccessUpdates));
    }

    public static void updateRestUpViewData(RestUpViewUpdate... upViewUpdates) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateRestUpViewData(upViewUpdates));
    }

    public static void updateRestToiletData(RestToiletUpdate... barUpdates) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateRestToiletData(barUpdates));
    }

    public static void updateRestAccessData(RestAccessUpdate... accUpdates) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateRestAccessData(accUpdates));
    }

    public static void updateRestAccessDataTwo(RestAccessUpdateTwo... accUpdatesTwo) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateRestAccessDataTwo(accUpdatesTwo));
    }

    public static void updateRestSinkData(RestSinkUpdate... sinkUpdates) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateRestSinkData(sinkUpdates));
    }

    public static void updateRestUrinalData(RestUrinalUpdate... urUpdates) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateRestUrinalData(urUpdates));
    }

    public static void deleteOneRestroomEntry(int restroomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteOneRestroomEntry(restroomID));
    }

    public static void insertSidewalkEntry(SidewalkEntry sidewalkEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertSidewalkEntry(sidewalkEntry));
    }

    public LiveData<List<SidewalkEntry>> getAllSidewalks(int schoolID) {
        allSidewalks = repository.getAllSidewalks(schoolID);
        return allSidewalks;
    }

    public static LiveData<List<SidewalkEntry>> getAllSidewalks(List<Integer> blockID) {
        return repository.getAllSidewalks(blockID);
    }

    public LiveData<SidewalkEntry> getSidewalkEntry(int sidewalkID) {
        return repository.getSidewalkEntry(sidewalkID);
    }

    public LiveData<SidewalkEntry> getLastSidewalkEntry() {
        return repository.getLastSidewalkEntry();
    }

    public static void updateSidewalk(SidewalkEntry sidewalkEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateSidewalk(sidewalkEntry));
    }

    public static void updateSidewalkOne(SidewalkEntryOne... sideOne) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateSidewalkOne(sideOne));
    }

    public static void updateSidewalkTwo(SidewalkEntryTwo... sideTwo) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateSidewalkTwo(sideTwo));
    }

    public static void deleteSidewalk(int sidewalkID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteSidewalk(sidewalkID));
    }

    public static void deleteAllSidewalksFromSchool(int schoolID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllSidewalksFromSchool(schoolID));
    }

    public static void insertSidewalkSlopeEntry(SidewalkSlopeEntry sidewalkSlopeEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertSidewalkSlopeEntry(sidewalkSlopeEntry));
    }

    public LiveData<List<SidewalkSlopeEntry>> getSideSlopes(int sidewalkID) {
        allSidewalkSlopes = repository.getSideSlopes(sidewalkID);
        return allSidewalkSlopes;
    }

    public static LiveData<List<SidewalkSlopeEntry>> getAllSideSlopes(List<Integer> sidewalkID) {
        return repository.getAllSideSlopes(sidewalkID);
    }

    public LiveData<SidewalkSlopeEntry> getSidewalkSlopeEntry(int sidewalkSlopeID) {
        return repository.getSidewalkSlopeEntry(sidewalkSlopeID);
    }


    public static void updateSidewalkSlope(SidewalkSlopeEntry sidewalkSlopeEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateSidewalkSlope(sidewalkSlopeEntry));
    }

    public static void deleteSidewalkSlope(int sidewalkSlopeID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteSidewalkSlope(sidewalkSlopeID));
    }

    public static void deleteAllSidewalkSlopesFromSidewalk(int sidewalkID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllSidewalkSlopesFromSidewalk(sidewalkID));
    }

    public static void insertRampStairsRailing(RampStairsRailingEntry railingEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertRampStairsRailing(railingEntry));
    }

    public LiveData<List<RampStairsRailingEntry>> getRampStairsRailings(int flightID){
        allRails = repository.getRampStairsRailings(flightID);
        return allRails;
    }

    public LiveData<RampStairsRailingEntry> getOneRailing(int railingID){
        return repository.getOneRailing(railingID);
    }

    public static LiveData<List<RampStairsRailingEntry>> getAllRailings(List<Integer> flightID) {
        return repository.getAllRailings(flightID);
    }

    public static void deleteOneRampStairsRailing(int railingID){
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteOneRampStairsRailing(railingID));
    }

    public static void deleteAllRailingsFromOneRampStairs(int flightID){
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllRailingsFromOneRampStairs(flightID));
    }

    public static void updateRampStairsRailing(RampStairsRailingEntry railingEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateRampStairsRailing(railingEntry));
    }

    public static void insertRampStairsHandrail(RampStairsHandrailEntry handrailEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertRampStairsHandrail(handrailEntry));
    }

    public LiveData<List<RampStairsHandrailEntry>> getRampStairsHandrails(int flightID){
        allHandrails = repository.getRampStairsHandrails(flightID);
        return allHandrails;
    }

    public LiveData<RampStairsHandrailEntry> getOneHandrail(int handrailID){
        return repository.getOneHandrail(handrailID);
    }

    public static LiveData<List<RampStairsHandrailEntry>> getAllHandrails(List<Integer> flightID) {
        return repository.getAllHandrails(flightID);
    }

    public static void deleteOneRampStairsHandrail(int handrailID){
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteOneRampStairsHandrail(handrailID));
    }

    public static void deleteAllHandrailsFromOneRampStairs(int flightID){
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllHandrailsFromOneRampStairs(flightID));
    }

    public static void updateRampStairsHandrail(RampStairsHandrailEntry handrailEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateRampStairsHandrail(handrailEntry));
    }

    public LiveData<Integer> countRampStairsRailings(int flightID) {
        return repository.countRampStairsRailings(flightID);
    }

    public LiveData<Integer> countRampStairsHandrails(int flightID) {
        return repository.countRampStairsHandrails(flightID);
    }

    public static void insertBlockSpace(BlockSpaceEntry blockSpace) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertBlockSpace(blockSpace));
    }

    public LiveData<List<BlockSpaceEntry>> getBlockSpaceFromSchool(int schoolID) {
        allBlockSpaces = repository.getBlockSpaceFromSchool(schoolID);
        return allBlockSpaces;
    }

    public LiveData<List<BlockSpaceEntry>> getAllBlocksSchool(int schoolID) {
        return repository.getAllBlocksSchool(schoolID);
    }

    public LiveData<List<Integer>> getAllBlockIds(int schoolID) {
        return repository.getAllBlockIds(schoolID);
    }

    public LiveData<BlockSpaceEntry> getSpecificBlockSpace(int blockSpaceID) {
        return repository.getSpecificBlockSpace(blockSpaceID);
    }

    public LiveData<BlockSpaceEntry> getLastBlockSpace(int schoolID, int blockSpaceType) {
        return repository.getLastBlockSpace(schoolID, blockSpaceType);
    }

    public LiveData<BlockSpaceEntry> getAreaFromSchool(int schoolID, int areaType) {
        return repository.getAreaFromSchool(schoolID, areaType);
    }

    public static void updateBlockSpace (BlockSpaceEntry blockSpace) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateBlockSpace(blockSpace));
    }

    public static void deleteBlockSpace(int blockSpaceID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteBlockSpace(blockSpaceID));
    }

    public static void deleteAllBlockSpacesSchool(int schoolID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllBlockSpacesSchool(schoolID));
    }

    public static void insertPlayground(PlaygroundEntry play) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertPlayground(play));
    }

    public LiveData<List<PlaygroundEntry>> getAllPlaygroundsPerBlock(int blockID) {
        allPlaygrounds =repository.getAllPlaygroundsPerBlock(blockID);
        return allPlaygrounds;
    }

    public static LiveData<List<PlaygroundEntry>> getAllPlaygrounds(List<Integer> blockID) {
        return repository.getAllPlaygrounds(blockID);
    }

    public LiveData<PlaygroundEntry> getOnePlayground(int playgroundID) {
        return repository.getOnePlayground(playgroundID);
    }

    public static void updatePlayground(PlaygroundEntry play) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updatePlayground(play));
    }

    public static void deleteOnePlayground(int playgroundID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteOnePlayground(playgroundID));
    }

    public static void deleteAllPlaygroundsFromBlock(int blockID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllPlaygroundsFromBlock(blockID));
    }

    public static void insertBlackboard(BlackboardEntry blackboard) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertBlackboard(blackboard));
    }

    public LiveData<List<BlackboardEntry>> getAllBlackboardsFromRoom(int roomID) {
        allBlackboards = repository.getAllBlackboardsFromRoom(roomID);
        return allBlackboards;
    }

    public static LiveData<List<BlackboardEntry>> getAllBlackboards(List<Integer> roomID) {
        return repository.getAllBlackboards(roomID);
    }

    public LiveData<List<BlackboardEntry>> getAllBlackboardsFromCirc(int circID) {
        allBlackboards = repository.getAllBlackboardsFromCirc(circID);
        return allBlackboards;
    }

    public static LiveData<List<BlackboardEntry>> getAllCircBlackboards(List<Integer> circID) {
        return repository.getAllCircBlackboards(circID);
    }

    public LiveData<BlackboardEntry> getOneBlackboard(int blackboardID) {
        return repository.getOneBlackboard(blackboardID);
    }

    public static void updateBlackboard (BlackboardEntry blackboard) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateBlackboard(blackboard));
    }

    public static void deleteBlackboard(int blackboardID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteBlackboard(blackboardID));
    }

    public static void deleteAllBlackboardsFromRoom(int roomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllBlackboardsFromRoom(roomID));
    }

    public static void insertDoorLock(DoorLockEntry doorLock) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertDoorLock(doorLock));
    }

    public LiveData<List<DoorLockEntry>> getDoorLocksFromDoor(int doorID) {
        allDoorLocks = repository.getDoorLocksFromDoor(doorID);
        return allDoorLocks;
    }

    public LiveData<List<DoorLockEntry>> getDoorLocksFromGates(int extID) {
        allDoorLocksGates = repository.getDoorLocksFromGates(extID);
        return allDoorLocksGates;
    }

    public static LiveData<List<DoorLockEntry>> getAllLocksFromDoor(List<Integer> doorID) {
        return repository.getAllLocksFromDoor(doorID);
    }

    public static LiveData<List<DoorLockEntry>> getAllLocksFromGates(List<Integer> extID) {
        return repository.getAllLocksFromGates(extID);
    }

    public LiveData<DoorLockEntry> getOneDoorLock(int lockID) {
        return repository.getOneDoorLock(lockID);
    }

    public LiveData<DoorLockEntry> getLastDoorLockEntry() {
        return repository.getLastDoorLockEntry();
    }

    public static void updateDoorLock(DoorLockEntry doorLock) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateDoorLock(doorLock));
    }

    public static void deleteDoorLock(int lockID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteDoorLock(lockID));
    }

    public static void deleteAllDoorLocksFromDoor(int doorID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllDoorLocksFromDoor(doorID));
    }

    public static void insertRestBox(RestBoxEntry restBox) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertRestBox(restBox));
    }

    public LiveData<List<RestBoxEntry>> getBoxesInRestroom(int restID) {
        allBoxes = repository.getBoxesInRestroom(restID);
        return allBoxes;
    }

    public static LiveData<List<RestBoxEntry>> getAllBoxesAllRestrooms(List<Integer> restID) {
        return repository.getAllBoxesInRestroom(restID);
    }

    public LiveData<RestBoxEntry> getCommonBoxData(int boxID) {
        return repository.getCommonBoxData(boxID);
    }

    public LiveData<RestBoxEntry> getBoxUpViewData(int boxID) {
        return repository.getBoxUpViewData(boxID);
    }

    public LiveData<RestBoxEntry> getBoxToiletData(int boxID) {
        return repository.getBoxToiletData(boxID);
    }

    public LiveData<RestBoxEntry> getBoxAccessData(int boxID) {
        return repository.getBoxAccessData(boxID);
    }

    public LiveData<RestBoxEntry> getBoxAccessDataTwo(int boxID) {
        return repository.getBoxAccessDataTwo(boxID);
    }

    public LiveData<RestBoxEntry> getBoxSinkData(int boxID) {
        return repository.getBoxSinkData(boxID);
    }

    public LiveData<RestBoxEntry> getOneBoxEntry(int boxID) {
        return repository.getOneBoxEntry(boxID);
    }

    public LiveData<RestBoxEntry> getLastBoxEntry() {
        return repository.getLastBoxEntry();
    }

    public static void deleteOneBox(int boxID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteOneBox(boxID));
    }

    public static void updateBoxFirstData(RestBoxFirstUpdate... comBoxUpdate) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateBoxFirstData(comBoxUpdate));
    }

    public void updateBoxUpView(RestBoxUpViewUpdate... upViewUpdate) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateBoxUpView(upViewUpdate));
    }

    public static void updateBoxToilet(RestBoxToilUpdate... toilUpdate) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateBoxToilet(toilUpdate));
    }

    public void updateBoxAccOne(RestBoxAccOneUpdate... accOneUpdate) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateBoxAccOne(accOneUpdate));
    }

    public static void updateBoxAccTwo(RestBoxAccTwoUpdate... accTwoUpdate) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateBoxAccTwo(accTwoUpdate));
    }

    public static void updateBoxSink(RestBoxSinkUpdate... sinkUpdate) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateBoxSink(sinkUpdate));
    }

    public static void insertCirculation(CirculationEntry circ) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertCirculation(circ));
    }

    public LiveData<List<CirculationEntry>> getAllCirculations(int schoolID) {
        allCirculations = repository.getAllCirculations(schoolID);
        return allCirculations;
    }


    public static LiveData<List<CirculationEntry>> getAllCircList(int schoolID) {
        return repository.getAllCirculations(schoolID);
    }

    public static LiveData<List<CirculationEntry>> getAllCircWithID(List<Integer> circID) {
        return repository.getAllCircWithID(circID);
    }

    public LiveData<CirculationEntry> getOneCirculation(int circID) {
        return repository.getOneCirculation(circID);
    }

    public LiveData<CirculationEntry> getLastCirculation() {
        return repository.getLastCirculation();
    }

    public LiveData<List<Integer>> getAllCircIds(int schoolID) {
        return repository.getAllCircIds(schoolID);
    }

    public static void updateCirculation (CirculationEntry circ) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateCirculation(circ));
    }

    public static void deleteCirculation(int circID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteCirculation(circID));
    }

    public static void deleteAllCirculations(int schoolID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllCirculations(schoolID));
    }

    public static void insertSoleStep(SingleStepEntry slop) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertSoleStep(slop));
    }

    public LiveData<List<SingleStepEntry>> getAllCircSingleSteps(int circID) {
        allSteps = repository.getAllCircSingleSteps(circID);
        return allSteps;
    }

    public static LiveData<List<SingleStepEntry>> getAllSingleStepsCirc(List<Integer> circID) {
        return repository.getAllSingleStepsCirc(circID);
    }

    public LiveData<List<SingleStepEntry>> getAllRoomSingleSteps(int roomID) {
        allSteps = repository.getAllRoomSingleSteps(roomID);
        return allSteps;
    }

    public LiveData<SingleStepEntry> getOneSoleStep(int stepID) {
        return repository.getOneSoleStep(stepID);
    }

    public static void updateSoleStep (SingleStepEntry soleStep) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateSoleStep(soleStep));
    }

    public static void deleteSoleStep(int stepID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteSoleStep(stepID));
    }

    public static void deleteAllSoleSteps(int circID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllSoleSteps(circID));
    }

    public void insertSlope(SlopeEntry slope) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertSlope(slope));
    }

    public LiveData<List<SlopeEntry>> getAllCircSlopes(int circID) {
        allSlopes = repository.getAllCircSlopes(circID);
        return allSlopes;
    }

    public static LiveData<List<SlopeEntry>> getCircAllSlopes(List<Integer> circID) {
        return repository.getCircAllSlopes(circID);
    }

    public LiveData<List<SlopeEntry>> getAllRoomSlopes(int roomID) {
        allSlopes = repository.getAllRoomSlopes(roomID);
        return allSlopes;
    }

    public LiveData<SlopeEntry> getOneSlope(int slopeID) {
        return repository.getOneSlope(slopeID);
    }

    public void updateSlope (SlopeEntry slope) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateSlope(slope));
    }

    public void deleteSlope(int slopeID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteSlope(slopeID));
    }

    public void deleteAllSlopes(int circID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteSlope(circID));
    }

    public void insertFallProtection(FallProtectionEntry protect) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertFallProtection(protect));
    }

    public LiveData<List<FallProtectionEntry>> getFallProtectFromCirc(int circID) {
        allFallProtect = repository.getFallProtectFromCirc(circID);
        return allFallProtect;
    }

    public static LiveData<List<FallProtectionEntry>> getAllFallProtection(List<Integer> circID) {
        return repository.getAllFallProtection(circID);
    }

    public LiveData<FallProtectionEntry> getOneFallProtection(int protectID) {
        return repository.getOneFallProtection(protectID);
    }

    public LiveData<FallProtectionEntry> getLastFallProtection() {
        return repository.getLastFallProtection();
    }

    public void updateFallProtection (FallProtectionEntry protect) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateFallProtection(protect));
    }


    public static void deleteFallProtection(int protectID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteFallProtection(protectID));
    }

    public void deleteAllFallProtectCirc(int circID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllFallProtectCirc(circID));
    }

    public void insertPool(PoolEntry pool) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertPool(pool));
    }

    public LiveData<List<PoolEntry>> getAllPoolsPerBlock(int blockID) {
        allPools = repository.getAllPoolsPerBlock(blockID);
        return allPools;
    }

    public static LiveData<List<PoolEntry>> getAllPools(List<Integer> blockID) {
        return repository.getAllPools(blockID);
    }

    public LiveData<PoolEntry> getPool(int poolID) {
        return repository.getPool(poolID);
    }

    public LiveData<PoolEntry> getLastPoolEntry() {
        return repository.getLastPoolEntry();
    }

    public static void updatePool(PoolEntry pool) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updatePool(pool));
    }

    public void updatePoolOne(PoolEntryOne... poolOne) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updatePoolOne(poolOne));
    }

    public void updatePoolTwo(PoolEntryTwo... poolTwo) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updatePoolTwo(poolTwo));
    }

    public static void deletePool(int poolID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deletePool(poolID));
    }

    public static void deleteAllPools(int blockID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllPools(blockID));
    }

    public void insertPoolBench(PoolBenchEntry bench) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertPoolBench(bench));
    }

    public LiveData<List<PoolBenchEntry>> getPoolBenches(int poolID) {
        allPoolBenches = repository.getPoolBenches(poolID);
        return allPoolBenches;
    }

    public static LiveData<List<PoolBenchEntry>> getAllPoolBenches(List<Integer> poolList) {
        return repository.getAllPoolBenches(poolList);
    }

    public LiveData<PoolBenchEntry> getOnePoolBench(int poolID) {
        return repository.getOnePoolBench(poolID);
    }

    public void updatePoolBench(PoolBenchEntry bench) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updatePoolBench(bench));
    }

    public static void deletePoolBench(int benchID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deletePoolBench(benchID));
    }

    public void deleteAllPoolBenches(int poolID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllPoolBenches(poolID));
    }

    public void insertPoolRamp(PoolRampEntry ramp) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertPoolRamp(ramp));
    }

    public LiveData<List<PoolRampEntry>> getPoolRamps(int poolID) {
        allPoolRamps = repository.getPoolRamps(poolID);
        return allPoolRamps;
    }

    public static LiveData<List<PoolRampEntry>> getEveryPoolRamps(List<Integer> poolList) {
        return repository.getEveryPoolRamps(poolList);
    }

    public LiveData<PoolRampEntry> getOnePoolRamp(int rampID) {
        return repository.getOnePoolRamp(rampID);
    }

    public void updatePoolRamp(PoolRampEntry ramp) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updatePoolRamp(ramp));
    }

    public static void deletePoolRamp(int rampID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deletePoolRamp(rampID));
    }

    public void deleteAllPoolRamps(int poolID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllPoolRamps(poolID));
    }

    public void insertPoolStairs(PoolStairsEntry Stairs) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertPoolStairs(Stairs));
    }

    public LiveData<List<PoolStairsEntry>> getPoolStairs(int poolID) {
        allPoolStairs = repository.getPoolStairs(poolID);
        return allPoolStairs;
    }

    public static LiveData<List<PoolStairsEntry>> getEveryPoolStairs(List<Integer> poolList) {
        return repository.getEveryPoolStairs(poolList);
    }

    public LiveData<PoolStairsEntry> getOnePoolStairs(int StairsID) {
        return repository.getOnePoolStairs(StairsID);
    }

    public void updatePoolStairs(PoolStairsEntry stairs) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updatePoolStairs(stairs));
    }

    public static void deletePoolStairs(int stairsID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deletePoolStairs(stairsID));
    }

    public void deleteAllPoolStairs(int poolID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllPoolStairs(poolID));
    }

    public void insertPoolEquip(PoolEquipEntry equip) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertPoolEquip(equip));
    }

    public LiveData<List<PoolEquipEntry>> getPoolEquips(int poolID) {
        allPoolEquip = repository.getPoolEquips(poolID);
        return allPoolEquip;
    }

    public static LiveData<List<PoolEquipEntry>> getEveryPoolEquips(List<Integer> poolList) {
        return repository.getEveryPoolEquips(poolList);
    }

    public LiveData<PoolEquipEntry> getOnePoolEquip(int EquipID) {
        return repository.getOnePoolEquip(EquipID);
    }

    public void updatePoolEquip(PoolEquipEntry equip) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updatePoolEquip(equip));
    }

    public static void deletePoolEquip(int equipID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deletePoolEquip(equipID));
    }

    public void deleteAllPoolEquips(int poolID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllPoolEquips(poolID));
    }

    //    Listenable
    public ListenableFuture<List<BlockSpaceEntry>> getListAllBlocks(int schoolID) {
        return repository.getListAllBlocks(schoolID);
    }

    public ListenableFuture<List<RoomEntry>> getListAllRoomsSchool(List<Integer> blockID) {
        return repository.getListAllRoomsSchool(blockID);
    }

    public ListenableFuture<List<ExternalAccess>> getListAllExtAccess(List<Integer> blockID) {
        return repository.getListAllExtAccess(blockID);
    }

    public ListenableFuture<List<ParkingLotEntry>> getListAllParkingLots(List<Integer> blockID) {
        return repository.getListAllParkingLots(blockID);
    }

    public ListenableFuture<List<PlaygroundEntry>> getListAllPlaygrounds(List<Integer> blockID) {
        return repository.getListAllPlaygrounds(blockID);
    }

    public ListenableFuture<List<RestroomEntry>> getListAllRestEntries(List<Integer> blockID) {
        return repository.getListAllRestEntries(blockID);
    }

    public ListenableFuture<List<SidewalkEntry>> getListAllSidewalks(List<Integer> blockID) {
        return repository.getListAllSidewalks(blockID);
    }

    public ListenableFuture<List<WaterFountainEntry>> getListAllFountains(List<Integer> blockID) {
        return repository.getListAllFountains(blockID);
    }

    public ListenableFuture<List<PoolEntry>> getListAllPools(List<Integer> blockID) {
        return repository.getListAllPools(blockID);
    }

    public ListenableFuture<List<BlackboardEntry>> getListAllBlackboards(List<Integer> roomID) {
        return repository.getListAllBlackboards(roomID);
    }

    public ListenableFuture<List<CounterEntry>> getListAllCounters(List<Integer> roomID) {
        return repository.getListAllCounters(roomID);
    }

    public ListenableFuture<List<DoorEntry>> getListAllDoors(List<Integer> roomID) {
        return repository.getListAllDoors(roomID);
    }

    public ListenableFuture<List<EquipmentEntry>> getListAllEquipments(List<Integer> roomID) {
        return repository.getListAllEquipments(roomID);
    }

    public ListenableFuture<List<FreeSpaceEntry>> getListAllFreeSpaces(List<Integer> roomID) {
        return repository.getListAllFreeSpaces(roomID);
    }

    public ListenableFuture<List<RampStairsEntry>> getListAllRampStRoom(List<Integer> roomID) {
        return repository.getListAllRampStRoom(roomID);
    }

    public ListenableFuture<List<SwitchEntry>> getListAllRoomsSwitches(List<Integer> roomID) {
        return repository.getListAllRoomsSwitches(roomID);
    }

    public ListenableFuture<List<TableEntry>> getListAllRoomsTables(List<Integer> roomID) {
        return repository.getListAllRoomsTables(roomID);
    }

    public ListenableFuture<List<WindowEntry>> getListAllRoomsWindows(List<Integer> roomID) {
        return repository.getListAllRoomsWindows(roomID);
    }

    public ListenableFuture<List<WaterFountainEntry>> getListAllRoomWaterFountains(List<Integer> roomID) {
        return repository.getListAllRoomWaterFountains(roomID);
    }

    public ListenableFuture<List<SingleStepEntry>> getListRoomAllSteps(List<Integer> roomID) {
        return repository.getListRoomAllSteps(roomID);
    }

    public ListenableFuture<List<SlopeEntry>> getListRoomAllSlopes(List<Integer> roomID) {
        return repository.getListRoomAllSlopes(roomID);
    }

    public ListenableFuture<List<DoorLockEntry>> getListAllLocksFromDoor(List<Integer> doorID) {
        return repository.getListAllLocksFromDoor(doorID);
    }

    //    Test
    public ListenableFuture<List<CirculationEntry>> getListAllCirculation(int schoolID) {
        return repository.getListAllCirculation(schoolID);
    }

    public ListenableFuture<List<RampStairsFlightEntry>> getListAllFlights(List<Integer> rampStairsID) {
        return repository.getListAllFlights(rampStairsID);
    }

    public ListenableFuture<List<RampStairsHandrailEntry>> getListAllHandrails(List<Integer> flightID) {
        return repository.getListAllHandrails(flightID);
    }

    public ListenableFuture<List<RampStairsRailingEntry>> getListAllRailings(List<Integer> flightID) {
        return repository.getListAllRailings(flightID);
    }

    public ListenableFuture<List<DoorLockEntry>> getListAllLocksFromGates(List<Integer> extID) {
        return repository.getListAllLocksFromGates(extID);
    }

    public ListenableFuture<List<GateObsEntry>> getListAllObs(List<Integer> extID) {
        return repository.getListAllObs(extID);
    }

    public ListenableFuture<List<PayPhoneEntry>> getListAllPhonesExtAccess(List<Integer> externalAccessID) {
        return repository.getListAllPhonesExtAccess(externalAccessID);
    }

    public ListenableFuture<List<RampStairsEntry>> getListAllRampStExt(List<Integer> extID) {
        return repository.getListAllRampStExt(extID);
    }

    public ListenableFuture<List<ParkingLotElderlyEntry>> getListAllElderVacancies(List<Integer> parkingLotID) {
        return repository.getListAllElderVacancies(parkingLotID);
    }

    public ListenableFuture<List<ParkingLotPCDEntry>> getListAllPcdVacancies(List<Integer> parkingLotID) {
        return repository.getListAllPcdVacancies(parkingLotID);
    }

    public ListenableFuture<List<DoorEntry>> getListAllRestDoors(List<Integer> restID) {
        return repository.getListAllRestDoors(restID);
    }

    public ListenableFuture<List<FreeSpaceEntry>> getListAllRestFreeSpaces(List<Integer> restID) {
        return repository.getListAllRestFreeSpaces(restID);
    }

    public ListenableFuture<List<RestBoxEntry>> getListAllBoxes(List<Integer> restID) {
        return repository.getListAllBoxes(restID);
    }

    public ListenableFuture<List<DoorEntry>> getListAllBoxDoors(List<Integer> boxID) {
        return repository.getListAllBoxDoors(boxID);
    }

    public ListenableFuture<List<SidewalkSlopeEntry>> getListAllSideSlopes(List<Integer> sidewalkID) {
        return repository.getListAllSideSlopes(sidewalkID);
    }

    public ListenableFuture<List<PayPhoneEntry>> getListAllPhonesSidewalk(List<Integer> sidewalkID) {
        return repository.getListAllPhonesSidewalk(sidewalkID);
    }

    public ListenableFuture<List<PoolBenchEntry>> getListAllPoolBenches(List<Integer> poolList) {
        return repository.getListAllPoolBenches(poolList);
    }

    public ListenableFuture<List<PoolEquipEntry>> getListAllPoolEquips(List<Integer> poolList) {
        return repository.getListAllPoolEquips(poolList);
    }

    public ListenableFuture<List<PoolRampEntry>> getListAllPoolRamps(List<Integer> poolList) {
        return repository.getListAllPoolRamps(poolList);
    }

    public ListenableFuture<List<PoolStairsEntry>> getListAllPoolStairs(List<Integer> poolList) {
        return repository.getListAllPoolStairs(poolList);
    }

    public ListenableFuture<List<DoorEntry>> getListAllCircDoors(List<Integer> circID) {
        return repository.getListAllCircDoors(circID);
    }

    public ListenableFuture<List<SwitchEntry>> getListAllCircSwitches(List<Integer> circID) {
        return repository.getListAllCircSwitches(circID);
    }

    public ListenableFuture<List<WindowEntry>> getListAllCircWindows(List<Integer> circID) {
        return repository.getListAllCircWindows(circID);
    }

    public ListenableFuture<List<TableEntry>> getListAllCircTables(List<Integer> circID) {
        return repository.getListAllCircTables(circID);
    }

    public ListenableFuture<List<BlackboardEntry>> getListAllCircBlackboards(List<Integer> circID) {
        return repository.getListAllCircBlackboards(circID);
    }

    public ListenableFuture<List<FreeSpaceEntry>> getListAllCircFreeSpaces(List<Integer> circID) {
        return repository.getListAllCircFreeSpaces(circID);
    }

    public ListenableFuture<List<SingleStepEntry>> getListAllStepsCirc(List<Integer> circID) {
        return repository.getListAllStepsCirc(circID);
    }

    public ListenableFuture<List<SlopeEntry>> getListCircAllSlopes(List<Integer> circID) {
        return repository.getListCircAllSlopes(circID);
    }

    public ListenableFuture<List<WaterFountainEntry>> getListAllCircWaterFountains(List<Integer> circID) {
        return repository.getListAllCircWaterFountains(circID);
    }

    public ListenableFuture<List<EquipmentEntry>> getListAllCircEquipments(List<Integer> circID) {
        return repository.getListAllCircEquipments(circID);
    }

    public ListenableFuture<List<CounterEntry>> getListAllCircCounters(List<Integer> circID) {
        return repository.getListAllCircCounters(circID);
    }

    public ListenableFuture<List<FallProtectionEntry>> getListAllFallProtection(List<Integer> circID) {
        return repository.getListAllFallProtection(circID);
    }

    public ListenableFuture<List<RampStairsEntry>> getListAllRampStCirc(List<Integer> circID) {
        return repository.getListAllRampStCirc(circID);
    }

    public ListenableFuture<List<Integer>> getListAllCircID(int schoolID) {
        return repository.getListAllCircID(schoolID);
    }

    public ListenableFuture<List<Integer>> getListAllBlocksID(int schoolID) {
        return repository.getListAllBlocksID(schoolID);
    }

    public ListenableFuture<List<Integer>> getListAllRoomsID(List<Integer> blockID) {
        return repository.getListAllRoomsID(blockID);
    }

    public ListenableFuture<List<Integer>> getListAllDoorsID(List<Integer> roomID) {
        return repository.getListAllDoorsID(roomID);
    }

    public ListenableFuture<List<Integer>> getListAllRestDoorsID(List<Integer> restID) {
        return repository.getListAllRestDoorsID(restID);
    }

    public ListenableFuture<List<Integer>> getListAllBoxDoorsID(List<Integer> boxID) {
        return repository.getListAllBoxDoorsID(boxID);
    }

    public ListenableFuture<List<Integer>> getListAllCircDoorsID(List<Integer> circID) {
        return repository.getListAllCircDoorsID(circID);
    }

    public ListenableFuture<List<Integer>> getListAllRampStIDRoom(List<Integer> roomID) {
        return repository.getListAllRampStIDRoom(roomID);
    }

    public ListenableFuture<List<Integer>> getListAllRampStIDExt(List<Integer> extID) {
        return repository.getListAllRampStIDExt(extID);
    }

    public ListenableFuture<List<Integer>> getListAllRampStIDCirc(List<Integer> circID) {
        return repository.getListAllRampStIDCirc(circID);
    }

    public ListenableFuture<List<Integer>> getListAllFlightsID(List<Integer> rampStairsID) {
        return repository.getListAllFlightsID(rampStairsID);
    }

    public ListenableFuture<List<Integer>> getListAllExtAccessID(List<Integer> blockID) {
        return repository.getListAllExtAccessID(blockID);
    }

    public ListenableFuture<List<Integer>> getListAllSidewalksID(List<Integer> blockID) {
        return repository.getListAllSidewalksID(blockID);
    }

    public ListenableFuture<List<Integer>> getListAllParkingLotID(List<Integer> blockID) {
        return repository.getListAllParkingLotID(blockID);
    }

    public ListenableFuture<List<RampStairsEntry>> getListAllRampStPark(List<Integer> parkID) {
        return repository.getListAllRampStPark(parkID);
    }

    public ListenableFuture<List<Integer>> getListAllRampStParkID(List<Integer> parkID) {
        return repository.getListAllRampStParkID(parkID);
    }

    public ListenableFuture<List<Integer>> getListAllRestID(List<Integer> blockID) {
        return repository.getListAllRestID(blockID);
    }

    public ListenableFuture<List<Integer>> getListAllBoxesID(List<Integer> restID) {
        return repository.getListAllBoxesID(restID);
    }

    public ListenableFuture<List<Integer>> getListAllPoolsID(List<Integer> blockID) {
        return repository.getListAllPoolsID(blockID);
    }

    public ListenableFuture<SchoolEntry> getListenableSchool(int cadID) {
        return repository.getListenableSchool(cadID);
    }

}
