package com.mpms.relatorioacessibilidadecortec.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mpms.relatorioacessibilidadecortec.data.ReportDatabase;
import com.mpms.relatorioacessibilidadecortec.data.ReportRepository;
import com.mpms.relatorioacessibilidadecortec.entities.CounterEntry;
import com.mpms.relatorioacessibilidadecortec.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.entities.FlightsRampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.entities.FreeSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.entities.GateObsEntry;
import com.mpms.relatorioacessibilidadecortec.entities.OtherSpaces;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotElderlyEntry;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotPDMREntry;
import com.mpms.relatorioacessibilidadecortec.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RampStairsEntry;
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
    public final LiveData<List<SchoolEntry>> allEntries;
    public final LiveData<List<RoomEntry>> allRooms;

    public ViewModelEntry(@NonNull Application application) {
        super(application);
        repository = new ReportRepository(application);
        allEntries = repository.getAllSchoolEntries();
        allRooms = repository.getAllRooms();
    }

    public static void insert(SchoolEntry schoolEntry) {
        repository.insertSchoolEntry(schoolEntry);
    }

    public static void update(SchoolEntry schoolEntry) {
        repository.updateSchoolEntry(schoolEntry);
    }

    public static void deleteOne(SchoolEntry schoolEntry) {
        repository.deleteOneSchoolEntry(schoolEntry);
    }

    public static void deleteAll() {
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

    public static void insertPdmrParkingLot(ParkingLotPDMREntry pdmrEntry) {
        repository.insertPdmrParkingLot(pdmrEntry);
    }

    public static void updatePdmrParkingLot(ParkingLotPDMREntry pdmrEntry) {
        repository.updatePdmrParkingLot(pdmrEntry);
    }

    public static void deletePdmrParkingLot(int parkingLotID) {
            repository.deletePdmrParkingLot(parkingLotID);
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
        return repository.getAllFountainsInSchool(schoolEntryID);
    }

    public LiveData<WaterFountainEntry> getOneWaterFountain(int waterFountainID) {
        return repository.getOneWaterFountain(waterFountainID);
    }

    public LiveData<List<ExternalAccess>> getAllExternalAccessesInSchool(int schoolEntryID) {
        return repository.getAllExternalAccessesInSchool(schoolEntryID);
    }

    public LiveData<ExternalAccess> getOneExternalAccess(int externalAccessID) {
        return repository.getOneExternalAccess(externalAccessID);
    }

    public LiveData<ExternalAccess> getLastExternalAccess() {
        return repository.getLastExternalAccess();
    }

    public LiveData<List<OtherSpaces>> selectAllSpaces(int schoolEntryID) {
        return repository.selectAllSpaces(schoolEntryID);
    }

    public LiveData<OtherSpaces> selectOneSpace(int otherID) {
        return repository.selectOneSpace(otherID);
    }

    public LiveData<List<ParkingLotEntry>> selectEveryParkingLot(int schoolEntryID) {
        return repository.selectEveryParkingLot(schoolEntryID);
    }

    public LiveData<ParkingLotEntry> selectOneParkingLot(int parkingLotID) {
        return repository.selectOneParkingLot(parkingLotID);
    }

    public LiveData<ParkingLotEntry> selectLastInsertedParkingLot() {
        return repository.selectLastInsertedParkingLot();
    }

    public LiveData<ParkingLotElderlyEntry> selectElderlyParkingLot(int parkingLotID) {
        return repository.selectElderlyParkingLot(parkingLotID);
    }

    public LiveData<ParkingLotPDMREntry> selectPdmrParkingLot(int parkingLotID) {
        return repository.selectPdmrParkingLot(parkingLotID);
    }

    public static void insertRoomEntry(RoomEntry roomEntry) {
       repository.insertRoomEntry(roomEntry);
    }

    public LiveData<List<RoomEntry>> getAllRooms() {
        return  repository.getAllRooms();
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

    public LiveData<FlightsRampStairsEntry> getRampStairsFLightEntry(int flightID) {
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
        return repository.getAllSchoolRestroomEntries(schoolID);
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

    public LiveData<List<SidewalkEntry>> getAllSidewalks() {
        return repository.getAllSidewalks();
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
        return repository.getAllSidewalkSlopes(sidewalkID);
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

    public void insertStairsStepEntry(StairsStepEntry stepEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertStairsStepEntry(stepEntry));
    }

    public LiveData<List<StairsStepEntry>> getAllStairsStepPerFlight(int flightID) {
        return repository.getAllStairsStepPerFlight(flightID);
    }

    public LiveData<StairsStepEntry> getOneStairsStepEntry(int stairsStepID) {
        return repository.getOneStairsStepEntry(stairsStepID);
    }

    public void updateStairsStepEntry(StairsStepEntry stepEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateStairsStepEntry(stepEntry));
    }

    public void deleteOneStairsStepEntry(int stairsStepID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteOneStairsStepEntry(stairsStepID));
    }

    public void deleteAllStairsStepPerFlight(int flightID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllStairsStepPerFlight(flightID));
    }

    public void insertStairsMirrorEntry(StairsMirrorEntry mirrorEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.insertStairsMirrorEntry(mirrorEntry));
    }

    public LiveData<List<StairsMirrorEntry>> getAllStairsMirrorPerFlight(int flightID) {
        return repository.getAllStairsMirrorPerFlight(flightID);
    }

    public LiveData<StairsMirrorEntry> getOneStairsMirrorEntry(int stairsMirrorID) {
        return repository.getOneStairsMirrorEntry(stairsMirrorID);
    }

    public void updateStairsMirrorEntry(StairsMirrorEntry mirrorEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.updateStairsMirrorEntry(mirrorEntry));
    }

    public void deleteOneStairsMirrorEntry(int stairsMirrorID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteOneStairsMirrorEntry(stairsMirrorID));
    }

    public void deleteAllStairsMirrorPerFlight(int flightID) {
        ReportDatabase.dbWriteExecutor.execute(() -> repository.deleteAllStairsMirrorPerFlight(flightID));
    }

}
