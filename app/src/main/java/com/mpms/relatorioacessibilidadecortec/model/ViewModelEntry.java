package com.mpms.relatorioacessibilidadecortec.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mpms.relatorioacessibilidadecortec.data.ReportDatabase;
import com.mpms.relatorioacessibilidadecortec.data.ReportRepository;
import com.mpms.relatorioacessibilidadecortec.entities.BlockSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.entities.CounterEntry;
import com.mpms.relatorioacessibilidadecortec.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.entities.FlightsRampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.entities.FreeSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.entities.GateObsEntry;
import com.mpms.relatorioacessibilidadecortec.entities.OtherSpaces;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotElderlyEntry;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotPCDEntry;
import com.mpms.relatorioacessibilidadecortec.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RampInclinationEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RampStairsHandrailEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RampStairsRailingEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomDoorUpdate;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomMirrorEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomSinkEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomSinkOne;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomSinkTwo;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomSupportBarEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomUpViewEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomEntryUpdate;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomUrinalEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolRegisterOne;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolRegisterThree;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolRegisterTwo;
import com.mpms.relatorioacessibilidadecortec.entities.SidewalkEntry;
import com.mpms.relatorioacessibilidadecortec.entities.SidewalkSlopeEntry;
import com.mpms.relatorioacessibilidadecortec.entities.StairsMirrorEntry;
import com.mpms.relatorioacessibilidadecortec.entities.StairsStepEntry;
import com.mpms.relatorioacessibilidadecortec.entities.SwitchEntry;
import com.mpms.relatorioacessibilidadecortec.entities.TableEntry;
import com.mpms.relatorioacessibilidadecortec.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.entities.WindowEntry;

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
    public LiveData<List<RampStairsEntry>> allRampsSchool;
    public LiveData<List<RampStairsEntry>> allStairsSchool;
    public LiveData<List<ParkingLotPCDEntry>> allPcdLots;
    public LiveData<List<ParkingLotElderlyEntry>> allElderLots;
    public LiveData<List<RoomEntry>> allRooms;
    public LiveData<List<BlockSpaceEntry>> allBlockSpaces;
    public final LiveData<List<SchoolEntry>> allEntries;

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

    public static void deleteAllExternalAccesses(int schoolID) {
        repository.deleteAllExternalAccesses(schoolID);
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

    public LiveData<List<ExternalAccess>> getAllExternalAccessesInSchool(int schoolEntryID) {
        allExtAccSchool = repository.getAllExternalAccessesInSchool(schoolEntryID);
        return allExtAccSchool;
    }

    public LiveData<ExternalAccess> getOneExternalAccess(int externalAccessID) {
        return repository.getOneExternalAccess(externalAccessID);
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

    public LiveData<List<ParkingLotEntry>> getAllParkingLots(int schoolEntryID) {
        allParkingLots = repository.selectEveryParkingLot(schoolEntryID);
        return allParkingLots;
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

    public LiveData<List<RoomEntry>> getAllRoomsInSchool(int schoolID, int roomType) {
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

    public static void deleteRoom(RoomEntry roomEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteRoom(roomEntry));
    }

    public static void insertDoorEntry(DoorEntry doorEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertDoorEntry(doorEntry));
    }

    public  LiveData<List<DoorEntry>> getDoorsFromRoom(int schoolID, int roomID) {
        return repository.getDoorsFromRoom(schoolID, roomID);
    }

    public LiveData<DoorEntry> getSpecificDoor(int doorId) {
        return repository.getSpecificDoor(doorId);
    }

    public static void updateDoor(DoorEntry doorEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateDoor(doorEntry));
    }

    public static void deleteDoor(int doorID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteDoor(doorID));
    }

    public static void deleteAllDoorsFromRoom(int schoolID, int roomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllDoorsFromRoom(schoolID, roomID));
    }

    public static void insertFreeSpaceEntry(FreeSpaceEntry freeSpace) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertFreeSpace(freeSpace));
    }

    public static void insertWindowEntry(WindowEntry windowEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertWindow(windowEntry));
    }

    public static void insertSwitchEntry(SwitchEntry switchEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertSwitch(switchEntry));
    }

    public static void insertTablesEntry(TableEntry tableEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertTable(tableEntry));
    }

    public static void insertGateObs (GateObsEntry gateObs) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertGateObs(gateObs));
    }

    public LiveData<List<GateObsEntry>> selectAllGateObsEntries(int externalAccessID) {
        return repository.selectAllGateObsEntries(externalAccessID);
    }

    public LiveData<List<GateObsEntry>> selectGateObsEntry(int gateObsID) {
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

    public LiveData<List<PayPhoneEntry>> selectAllPayPhones(int externalAccessID) {
        return repository.selectAllPayPhones(externalAccessID);
    }

    public LiveData<List<PayPhoneEntry>> selectPayPhoneEntry(int payPhoneID) {
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
        return repository.getCountersFromRoom(roomID);
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

    public LiveData<List<RampStairsEntry>> getAllRampStairsFromSchool(int schoolID) {
        return repository.getAllRampStairsFromSchool(schoolID);
    }

    public LiveData<List<RampStairsEntry>> getAllRampsFromSchool(int schoolID, int rampIdentifier) {
        allRampsSchool = repository.getAllRampsFromSchool(schoolID, rampIdentifier);
        return allRampsSchool;
    }

    public LiveData<List<RampStairsEntry>> getAllStairsFromSchool(int schoolID, int stairsIdentifier) {
        allStairsSchool = repository.getAllStairsFromSchool(schoolID,stairsIdentifier);
        return allStairsSchool;
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

    public static void insertRampsStairsFlight(FlightsRampStairsEntry flight) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertRampsStairsFlight(flight));
    }

    public LiveData<List<FlightsRampStairsEntry>> getAllRampStairsFlights(int rampStairsID) {
        return repository.getAllRampStairsFlights(rampStairsID);
    }

    public LiveData<FlightsRampStairsEntry> getRampStairsFlightEntry(int flightID) {
        return repository.getRampStairsFlightEntry(flightID);
    }

    public LiveData<FlightsRampStairsEntry> getLastRampStairsFlightEntry() {
        return repository.getLastRampStairsFlightEntry();
    }

    public static void deleteOneFlight(int flightID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteOneFlight(flightID));
    }

    public static void deleteAllFlightsFromOneRampsStairs(int rampStairsID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllFlightsFromOneRampsStairs(rampStairsID));
    }

    public static void updateFlightRampStairs(FlightsRampStairsEntry flight) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateFlightRampStairs(flight));
    }

    public static void insertRestroomEntry(RestroomEntry restroom) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertRestroomEntry(restroom));
    }

    public LiveData<List<RestroomEntry>> getAllSchoolRestroomEntries(int schoolID) {
        allRestSchool = repository.getAllSchoolRestroomEntries(schoolID);
        return allRestSchool;
    }

    public LiveData<RestroomEntry> getOneRestroomEntry(int restroomID) {
        return repository.getOneRestroomEntry(restroomID);
    }

    public LiveData<RestroomEntry> getLastRestroomEntry() {
        return repository.getLastRestroomEntry();
    }

    public static void updateRestroomEntry(RestroomEntry restroom) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateRestroomEntry(restroom));
    }

    public static void updateRestroomData(RestroomEntryUpdate... restroomEntryUpdates) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateRestroomData(restroomEntryUpdates));
    }

    public static void updateRestroomDoorData(RestroomDoorUpdate... doorUpdates) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateRestroomDoorData(doorUpdates));
    }

    public static void deleteOneRestroomEntry(int restroomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteOneRestroomEntry(restroomID));
    }

    public static void deleteAllRestroomEntriesFromSchool(int schoolID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllRestroomEntriesFromSchool(schoolID));
    }

    public static void insertRestroomMirrorEntry(RestroomMirrorEntry restroom) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertRestroomMirrorEntry(restroom));
    }

    public LiveData<RestroomMirrorEntry> getOneRestroomMirrorEntry(int restroomID) {
        return repository.getOneRestroomMirrorEntry(restroomID);
    }

    public static void updateRestroomMirrorEntry(RestroomMirrorEntry mirrorEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateRestroomMirrorEntry(mirrorEntry));
    }

    public static void deleteOneRestroomMirrorEntry(int restroomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteOneRestroomMirrorEntry(restroomID));
    }

    public static void insertRestroomSinkEntry(RestroomSinkEntry sinkEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertRestroomSinkEntry(sinkEntry));
    }

    public LiveData<RestroomSinkEntry> getOneRestroomSinkEntry(int sinkID) {
        return repository.getOneRestroomSinkEntry(sinkID);
    }

    public LiveData<RestroomSinkEntry> getLastRestroomSinkEntry() {
        return repository.getLastRestroomSinkEntry();
    }

    public LiveData<RestroomSinkEntry> getRestroomSinkByRestroom(int restroomID) {
        return repository.getRestroomSinkByRestroom(restroomID);
    }

    public static void updateRestroomSinkEntry(RestroomSinkEntry sinkEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateRestroomSinkEntry(sinkEntry));
    }

    public static void updateSinkEntryOne(RestroomSinkOne... sinkOne) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateSinkEntryOne(sinkOne));
    }

    public static void updateSinkEntryTwo(RestroomSinkTwo... sinkTwo) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateSinkEntryTwo(sinkTwo));
    }

    public static void deleteOneRestroomSinkEntry(int restroomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteOneRestroomSinkEntry(restroomID));
    }

    public static void insertRestroomSupportBarEntry(RestroomSupportBarEntry supportBar) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertRestroomSupportBarEntry(supportBar));
    }

    public LiveData<RestroomSupportBarEntry> getOneRestroomSupportBarEntry(int restroomID) {
        return repository.getOneRestroomSupportBarEntry(restroomID);
    }

    public LiveData<RestroomSupportBarEntry> getLastRestroomSupportBarEntry() {
        return repository.getLastRestroomSupportBarEntry();
    }

    public LiveData<RestroomSupportBarEntry> getRestSupportBarByRestroom(int restroomID) {
        return repository.getRestSupportBarByRestroom(restroomID);
    }

    public static void updateRestroomSupportBarEntry(RestroomSupportBarEntry supportBar) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateRestroomSupportBarEntry(supportBar));
    }

    public static void deleteOneRestroomSupportBarEntry(int restroomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteOneRestroomSupportBarEntry(restroomID));
    }

    public static void insertRestroomUpViewEntry(RestroomUpViewEntry upViewEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertRestroomUpViewEntry(upViewEntry));
    }

    public LiveData<RestroomUpViewEntry> getOneRestroomUpViewEntry(int upViewID) {
        return repository.getOneRestroomUpViewEntry(upViewID);
    }

    public LiveData<RestroomUpViewEntry> getRestroomUpViewByRestroom(int restroomID) {
        return repository.getRestroomUpViewByRestroom(restroomID);
    }

    public LiveData<RestroomUpViewEntry> getLastRestroomUpViewEntry() {
        return repository.getLastRestroomUpViewEntry();
    }

    public static void updateRestroomUpViewEntry(RestroomUpViewEntry upViewEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateRestroomUpViewEntry(upViewEntry));
    }

    public static void deleteRestroomUpViewEntry(int restroomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteRestroomUpViewEntry(restroomID));
    }

    public static void insertRestroomUrinalEntry(RestroomUrinalEntry urinalEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertRestroomUrinalEntry(urinalEntry));
    }

    public LiveData<RestroomUrinalEntry> getOneRestroomUrinalEntry(int restroomID) {
        return repository.getOneRestroomUrinalEntry(restroomID);
    }

    public static void updateRestroomUrinalEntry(RestroomUrinalEntry urinalEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateRestroomUrinalEntry(urinalEntry));
    }

    public static void deleteOneRestroomUrinalEntry(int restroomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteOneRestroomUrinalEntry(restroomID));
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

    public static void insertStairsStepEntry(StairsStepEntry stepEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertStairsStepEntry(stepEntry));
    }

    public LiveData<List<StairsStepEntry>> getAllStairsStepPerFlight(int flightID) {
        return repository.getAllStairsStepPerFlight(flightID);
    }

    public LiveData<StairsStepEntry> getOneStairsStepEntry(int stairsStepID) {
        return repository.getOneStairsStepEntry(stairsStepID);
    }

    public static void updateStairsStepEntry(StairsStepEntry stepEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateStairsStepEntry(stepEntry));
    }

    public static void deleteOneStairsStepEntry(int stairsStepID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteOneStairsStepEntry(stairsStepID));
    }

    public static void deleteAllStairsStepPerFlight(int flightID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllStairsStepPerFlight(flightID));
    }

    public static void insertStairsMirrorEntry(StairsMirrorEntry mirrorEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertStairsMirrorEntry(mirrorEntry));
    }

    public LiveData<List<StairsMirrorEntry>> getAllStairsMirrorPerFlight(int flightID) {
        return repository.getAllStairsMirrorPerFlight(flightID);
    }

    public LiveData<StairsMirrorEntry> getOneStairsMirrorEntry(int stairsMirrorID) {
        return repository.getOneStairsMirrorEntry(stairsMirrorID);
    }

    public static void updateStairsMirrorEntry(StairsMirrorEntry mirrorEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateStairsMirrorEntry(mirrorEntry));
    }

    public static void deleteOneStairsMirrorEntry(int stairsMirrorID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteOneStairsMirrorEntry(stairsMirrorID));
    }

    public static void deleteAllStairsMirrorPerFlight(int flightID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllStairsMirrorPerFlight(flightID));
    }

    public static void insertRampInclinationEntry(RampInclinationEntry rampEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertRampInclinationEntry(rampEntry));
    }

    public LiveData<List<RampInclinationEntry>> getAllRampInclinationsPerFlight(int flightID) {
        return repository.getAllRampInclinationsPerFlight(flightID);
    }

    public LiveData<RampInclinationEntry> getOneRampInclinationEntry(int rampInclinationID) {
        return repository.getOneRampInclinationEntry(rampInclinationID);
    }

    public static void updateRampInclinationEntry(RampInclinationEntry rampEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateRampInclinationEntry(rampEntry));
    }

    public static void deleteOneRampInclinationEntry(int rampInclinationID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteOneRampInclinationEntry(rampInclinationID));
    }

    public static void deleteAllRampInclinationsPerFlight(int flightID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllRampInclinationsPerFlight(flightID));
    }

    public static void insertRampStairsRailing(RampStairsRailingEntry railingEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertRampStairsRailing(railingEntry));
    }

    public LiveData<List<RampStairsRailingEntry>> getAllRampStairsRailings(int flightID){
        return repository.getAllRampStairsRailings(flightID);
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
        return repository.getAllRampStairsHandrails(flightID);
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

    public LiveData<Integer> countStairSteps(int flightID) {
        return repository.countStairSteps(flightID);
    }

    public LiveData<Integer> countStairsMirror(int flightID) {
        return repository.countStairsMirror(flightID);
    }

    public LiveData<Integer> countRampInclination(int flightID) {
        return repository.countRampInclination(flightID);
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

    public LiveData<BlockSpaceEntry> getLastBlockSpace(int blockSpaceType) {
        return repository.getSpecificBlockSpace(blockSpaceType);
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

}
