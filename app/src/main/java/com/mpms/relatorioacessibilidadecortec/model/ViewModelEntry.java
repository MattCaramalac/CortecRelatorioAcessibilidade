package com.mpms.relatorioacessibilidadecortec.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mpms.relatorioacessibilidadecortec.data.database.ReportDatabase;
import com.mpms.relatorioacessibilidadecortec.data.database.ReportRepository;
import com.mpms.relatorioacessibilidadecortec.data.entities.AdmEquipEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlackboardEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlockSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.CounterEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorLockEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExtAccessSocialOne;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExtAccessSocialThree;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExtAccessSocialTwo;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.data.entities.FreeSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.GateObsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.OtherSpaces;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotElderlyEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotPCDEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PlaygroundEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsFlightEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsHandrailEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsRailingEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestAccessUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestDoorUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestEntryUpdate;
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
import com.mpms.relatorioacessibilidadecortec.data.entities.SwitchEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.TableEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WindowEntry;

import java.util.List;

public class ViewModelEntry extends AndroidViewModel {

    public static ReportRepository repository;
    public LiveData<List<ExternalAccess>> allExtAccSchool;
    public LiveData<List<WaterFountainEntry>> allFountainsInSchool;
    public LiveData<List<RestroomEntry>> allRestSchool;
    public LiveData<List<OtherSpaces>> allOtherSpaces;
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
    public LiveData<List<AdmEquipEntry>> allAdmEquip;
    public LiveData<List<PlaygroundEntry>> allPlaygrounds;
    public LiveData<List<DoorEntry>> allDoors;
    public LiveData<List<SwitchEntry>> allSwitches;
    public LiveData<List<WindowEntry>> allWindows;
    public LiveData<List<TableEntry>> allTables;
    public LiveData<List<FreeSpaceEntry>> allFreeSpaces;
    public LiveData<List<BlackboardEntry>> allBlackboards;
    public LiveData<List<CounterEntry>> allCounters;
    public LiveData<List<DoorLockEntry>> allDoorLocks;
    public LiveData<List<DoorLockEntry>> allDoorLocksGates;
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

    public static void deleteOneSchool(SchoolEntry schoolEntry) {
        repository.deleteOneSchoolEntry(schoolEntry);
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

    public static void insertOtherSpace(OtherSpaces otherSpaces) {
        repository.insertOtherSpace(otherSpaces);
    }

    public static void updateOtherSpace(OtherSpaces otherSpaces) {
        repository.updateOtherSpace(otherSpaces);
    }

    public static void deleteOneSpace(int otherID) {
        repository.deleteOneSpace(otherID);
    }

    public static void deleteAllSpaces(int schoolID) {
        repository.deleteAllSpaces(schoolID);
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

    public LiveData<List<WaterFountainEntry>> getAllFountainsInSchool(int schoolEntryID) {
        allFountainsInSchool = repository.getAllFountainsInSchool(schoolEntryID);
        return allFountainsInSchool;
    }

    public LiveData<WaterFountainEntry> getOneWaterFountain(int waterFountainID) {
        return repository.getOneWaterFountain(waterFountainID);
    }

    public LiveData<List<ExternalAccess>> getAllExternalAccessesInOneBlock(int blockID) {
        allExtAccSchool = repository.getAllExternalAccessesInSchool(blockID);
        return allExtAccSchool;
    }

    public LiveData<ExternalAccess> getOneExternalAccess(int externalAccessID) {
        oneAccess = repository.getOneExternalAccess(externalAccessID);
        return oneAccess;
    }

    public LiveData<ExternalAccess> getLastExternalAccess() {
        return repository.getLastExternalAccess();
    }

    public LiveData<List<OtherSpaces>> getAllOtherSpaces(int schoolEntryID) {
        allOtherSpaces = repository.selectAllSpaces(schoolEntryID);
        return allOtherSpaces;
    }

    public LiveData<OtherSpaces> getOneOtherSpace(int otherID) {
        return repository.selectOneSpace(otherID);
    }

    public LiveData<List<ParkingLotEntry>> getParkingLotsFromBlocks(int schoolEntryID) {
        allParkingLots = repository.getParkingLotFromBlock(schoolEntryID);
        return allParkingLots;
    }

    public LiveData<List<ParkingLotEntry>> getParkingLotFromSide(int blockID, int sideID) {
        return repository.getParkingLotFromSide(blockID, sideID);
    }

    public LiveData<ParkingLotEntry> getOneParkingLot(int parkingLotID) {
        return repository.selectOneParkingLot(parkingLotID);
    }

    public LiveData<ParkingLotEntry> getLastInsertedParkingLot() {
        return repository.selectLastInsertedParkingLot();
    }

    public LiveData<List<ParkingLotElderlyEntry>> getAllElderlyParkingLot(int parkingLotID) {
        allElderLots = repository.selectAllElderlyParkingLot(parkingLotID);
        return allElderLots;
    }

    public LiveData<List<ParkingLotPCDEntry>> getAllPcdParkingLot(int parkingLotID) {
        allPcdLots = repository.selectAllPcdParkingLot(parkingLotID);
        return allPcdLots;
    }

    public LiveData<ParkingLotElderlyEntry> getOneElderlyParkingLot(int parkingElderlyID) {
        return repository.selectOneElderlyParkingLot(parkingElderlyID);
    }

    public LiveData<ParkingLotPCDEntry> getOnePcdParkingLot(int parkingPcdID) {
        return repository.selectOnePcdParkingLot(parkingPcdID);
    }

    public static void insertRoomEntry(RoomEntry roomEntry) {
       repository.insertRoomEntry(roomEntry);
    }

    public LiveData<List<RoomEntry>> getAllRoomsInBlock(int schoolID, int roomType) {
        allRooms = repository.getAllRoomsInSchool(schoolID, roomType);
        return  allRooms;
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

    public LiveData<DoorEntry> getSpecificDoor(int doorId) {
        return repository.getSpecificDoor(doorId);
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

    public static void deleteAllDoorsFromRoom(int schoolID, int roomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllDoorsFromRoom(roomID));
    }

    public static void insertFreeSpaceEntry(FreeSpaceEntry freeSpace) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertFreeSpace(freeSpace));
    }

    public LiveData<List<FreeSpaceEntry>> selectFreeSpaceFromRoom(int roomID) {
        allFreeSpaces = repository.selectFreeSpaceFromRoom(roomID);
        return allFreeSpaces;
    }

    public LiveData<FreeSpaceEntry> selectSpecificFreeSpace(int freeSpaceID) {
        return repository.selectSpecificFreeSpace(freeSpaceID);
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

    public LiveData<List<WindowEntry>> selectWindowsFromRoom(int roomID) {
        allWindows = repository.selectWindowsFromRoom(roomID);
        return allWindows;
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
        allSwitches = repository.selectSwitchesFromRoom(roomID);
        return allSwitches;
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

    public LiveData<List<TableEntry>> selectTablesFromRoom(int roomID) {
        allTables = repository.selectTablesFromRoom(roomID);
        return allTables;
    }

    public LiveData<TableEntry> selectSpecificTable(int tableID) {
        return repository.selectSpecificTable(tableID);
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

    public LiveData<List<PayPhoneEntry>> getAllPayPhonesExtAccess(int externalAccessID) {
        allPayPhonesExtAccess = repository.selectAllPayPhones(externalAccessID);
        return allPayPhonesExtAccess;
    }

    public LiveData<List<PayPhoneEntry>> getAllPayPhonesSidewalk(int sidewalkID) {
        allPayPhonesSidewalk = repository.selectAllPhonesSidewalk(sidewalkID);
        return allPayPhonesSidewalk;
    }

    public void deleteAllPayPhonesSidewalk(int sidewalkID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllPayPhonesSidewalk(sidewalkID));
    }

    public LiveData<PayPhoneEntry> getPayPhoneEntry(int payPhoneID) {
        return repository.selectPayPhoneEntry(payPhoneID);
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

    public static void insertRampStairs(RampStairsEntry ramp) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertRampStairs(ramp));
    }

    public LiveData<List<RampStairsEntry>> getStairsRampFromExtAccess(int blockID, int ambientID, int rampOrStairs) {
        allStairsRampsSchool = repository.getStairsRampFromExtAccess(blockID, ambientID, rampOrStairs);
        return allStairsRampsSchool;
    }

    public LiveData<List<RampStairsEntry>> getStairsRampFromSidewalk(int blockID, int ambientID, int rampOrStairs) {
        allStairsRampsSchool = repository.getStairsRampFromSidewalk(blockID, ambientID, rampOrStairs);
        return allStairsRampsSchool;
    }

    public LiveData<List<RampStairsEntry>> getStairsRampFromParking(int blockID, int ambientID, int rampOrStairs) {
        allStairsRampsSchool = repository.getStairsRampFromParking(blockID, ambientID, rampOrStairs);
        return allStairsRampsSchool;
    }


    public LiveData<List<RampStairsEntry>> getStairsRampFromRoom(int blockID, int ambientID, int rampOrStairs){
        allStairsRampsSchool = repository.getStairsRampFromRoom(blockID, ambientID, rampOrStairs);
        return allStairsRampsSchool;
    }

    public LiveData<RampStairsEntry> getRampStairsEntry(int rampStairsID) {
        return repository.getRampStairsEntry(rampStairsID);
    }

    public LiveData<RampStairsEntry> getLastRampStairsEntry() {
        return repository.getLastRampStairsEntry();
    }

    public static void deleteOneRampStairs(int rampStairsID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteOneRampStairs(rampStairsID));
    }

    public static void deleteAllRampStairsFromSchool(int schoolID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllRampStairsFromSchool(schoolID));
    }

    public static void updateRampStairs(RampStairsEntry rampStairs) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateRampStairs(rampStairs));
    }

    public static void insertRampsStairsFlight(RampStairsFlightEntry flight) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertRampsStairsFlight(flight));
    }

    public LiveData<List<RampStairsFlightEntry>> getAllRampStairsFlights(int rampStairsID) {
        allFlights = repository.getAllRampStairsFlights(rampStairsID);
        return allFlights;
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

    public LiveData<RestroomEntry> getOneRestroomEntry(int restroomID) {
        return repository.getOneRestroomEntry(restroomID);
    }

    public LiveData<RestroomEntry> getRestFirstData(int restID) {
        return repository.getRestFirstData(restID);
    }

    public LiveData<RestroomEntry> getRestDoorData(int restID) {
        return repository.getRestDoorData(restID);
    }

    public LiveData<RestroomEntry> getRestUpViewData(int restID) {
        return repository.getRestUpViewData(restID);
    }

    public LiveData<RestroomEntry> getRestToiletData(int restID) {
        return repository.getRestToiletData(restID);
    }

    public LiveData<RestroomEntry> getRestAccessData(int restID) {
        return repository.getRestAccessData(restID);
    }

    public LiveData<RestroomEntry> getRestSinkData(int restID) {
        return repository.getRestSinkData(restID);
    }

    public LiveData<RestroomEntry> getRestUrinalData(int restID) {
        return repository.getRestUrinalData(restID);
    }

    public LiveData<RestroomEntry> getLastRestroomEntry() {
        return repository.getLastRestroomEntry();
    }

    public static void updateRestroomEntry(RestroomEntry restroom) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateRestroomEntry(restroom));
    }

    public static void updateRestroomData(RestEntryUpdate... restEntryUpdates) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateRestroomData(restEntryUpdates));
    }

    public static void updateRestDoorData(RestDoorUpdate... doorUpdates) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateRestDoorData(doorUpdates));
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

    public LiveData<List<SidewalkSlopeEntry>> getAllSidewalkSlopes(int sidewalkID) {
        allSidewalkSlopes = repository.getAllSidewalkSlopes(sidewalkID);
        return allSidewalkSlopes;
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

    public LiveData<List<RampStairsRailingEntry>> getAllRampStairsRailings(int flightID){
        allRails = repository.getAllRampStairsRailings(flightID);
        return allRails;
    }

    public LiveData<RampStairsRailingEntry> getRampStairsRailing(int railingID){
        return repository.getRampStairsRailing(railingID);
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

    public LiveData<List<RampStairsHandrailEntry>> getAllRampStairsHandrails(int flightID){
        allHandrails = repository.getAllRampStairsHandrails(flightID);
        return allHandrails;
    }

    public LiveData<RampStairsHandrailEntry> getRampStairsHandrail(int handrailID){
        return repository.getRampStairsHandrail(handrailID);
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

    public static void insertAdmEquip(AdmEquipEntry admEquip) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertAdmEquip(admEquip));
    }

    public LiveData<List<AdmEquipEntry>> getAllAdmEquipsPerBlock(int blockID) {
        allAdmEquip = repository.getAllAdmEquipsPerBlock(blockID);
        return allAdmEquip;
    }

    public LiveData<AdmEquipEntry> getOneAdmEquip(int admEquipID) {
        return repository.getOneAdmEquip(admEquipID);
    }

    public static void updateAdmEquip(AdmEquipEntry admEquip) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateAdmEquip(admEquip));
    }

    public static void deleteOneAdmEquip(int admEquipID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteOneAdmEquip(admEquipID));
    }

    public static void deleteAllAdmEquipsFromBlock(int blockID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllAdmEquipsFromBlock(blockID));
    }

    public static void insertPlayground(PlaygroundEntry play) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertPlayground(play));
    }

    public LiveData<List<PlaygroundEntry>> getAllPlaygroundsPerBlock(int blockID) {
        allPlaygrounds =repository.getAllPlaygroundsPerBlock(blockID);
        return allPlaygrounds;
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

}
