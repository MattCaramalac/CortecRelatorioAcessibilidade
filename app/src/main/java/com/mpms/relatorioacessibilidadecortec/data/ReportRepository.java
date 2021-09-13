package com.mpms.relatorioacessibilidadecortec.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

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
import com.mpms.relatorioacessibilidadecortec.entities.SwitchEntry;
import com.mpms.relatorioacessibilidadecortec.entities.TableEntry;
import com.mpms.relatorioacessibilidadecortec.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.entities.WindowEntry;

import java.util.List;

public class ReportRepository {

    private ReportDatabase db;

    private final SchoolEntryDao schoolEntryDao;
    private final WaterFountainDao waterFountainDao;
    private final ExternalAccessDao externalAccessDao;
    private final OtherSpacesDao otherSpacesDao;
    private final ParkingLotEntryDao parkingLotEntryDao;
    private final ParkingLotElderlyDao parkingLotElderlyDao;
    private final ParkingLotPdmrDao parkingLotPdmrDao;
    private final RoomEntryDao roomEntryDao;
    private final DoorEntryDao doorEntryDao;
    private final FreeSpaceEntryDao freeSpaceEntryDao;
    private final SwitchEntryDao switchEntryDao;
    private final WindowEntryDao windowEntryDao;
    private final TableEntryDao tableEntryDao;
    private final GateObsDao gateObsDao;
    private final PayPhoneDao payPhoneDao;
    private final CounterEntryDao counterEntryDao;
    private final RampStairsEntryDao rampStairsEntryDao;
    private final FlightRampStairsDao flightsRampStairDao;
    private final RestroomEntryDao restroomEntryDao;
    private final RestroomMirrorDao restroomMirrorDao;
    private final RestroomSinkDao restroomSinkDao;
    private final RestroomSupportBarDao restroomSupportBarDao;
    private final RestroomUpViewDao restroomUpViewDao;
    private final RestroomUrinalDao restroomUrinalDao;

    public ReportRepository(Application application) {
        db = ReportDatabase.getDatabase(application);
        schoolEntryDao = db.schoolEntryDao();
        waterFountainDao = db.waterFountainDao();
        externalAccessDao = db.externalAccessDao();
        otherSpacesDao = db.otherSpacesDao();
        parkingLotEntryDao = db.parkingLotEntryDao();
        parkingLotPdmrDao = db.parkingLotPdmrDao();
        parkingLotElderlyDao = db.parkingLotElderlyDao();
        roomEntryDao = db.roomEntryDao();
        doorEntryDao = db.doorEntryDao();
        freeSpaceEntryDao = db.freeSpaceEntryDao();
        switchEntryDao = db.switchEntryDao();
        windowEntryDao = db.windowEntryDao();
        tableEntryDao = db.tableEntryDao();
        payPhoneDao = db.payPhoneDao();
        gateObsDao = db.gateObsDao();
        counterEntryDao = db.counterEntryDao();
        rampStairsEntryDao = db.rampStairsDao();
        flightsRampStairDao = db.flightRampStairsDao();
        restroomEntryDao = db.restroomEntryDao();
        restroomMirrorDao = db.restroomMirrorDao();
        restroomSinkDao = db.restroomSinkDao();
        restroomSupportBarDao = db.restroomSupportBarDao();
        restroomUpViewDao = db.restroomUpViewDao();
        restroomUrinalDao = db.restroomUrinalDao();

    }

    public LiveData<List<SchoolEntry>> getAllSchoolEntries() {
        return schoolEntryDao.getAllEntries();
    }

    public LiveData<SchoolEntry> getSchoolEntry(int cadID) {
        return schoolEntryDao.getEntry(cadID);
    }

    public LiveData<SchoolEntry> getLastSchoolEntry() {
        return schoolEntryDao.getLastEntry();
    }

    public void insertSchoolEntry(SchoolEntry schoolEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> schoolEntryDao.insertEntry(schoolEntry));
    }

    public void updateSchoolEntry(SchoolEntry schoolEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> schoolEntryDao.update(schoolEntry));
    }

    public void deleteOneSchoolEntry(SchoolEntry schoolEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> schoolEntryDao.deleteOne(schoolEntry));
    }

    public LiveData<List<WaterFountainEntry>> getAllFountainsInSchool(int schoolEntryID) {
        return waterFountainDao.getAllSchoolWaterFountains(schoolEntryID);
    }

    public LiveData<WaterFountainEntry> getOneWaterFountain(int waterFountainID) {
        return waterFountainDao.getOneWaterFountain(waterFountainID);
    }

    public void insertWaterFountain(WaterFountainEntry waterFountainEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> waterFountainDao.insertWaterFountain(waterFountainEntry));
    }

    public void updateWaterFountain(WaterFountainEntry waterFountainEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> waterFountainDao.updateWaterFountain(waterFountainEntry));
    }

    public void deleteOneWaterFountain(int waterFountainID) {
        ReportDatabase.dbWriteExecutor.execute(() -> waterFountainDao.deleteOneWaterFountain(waterFountainID));
    }

    public void deleteAllFountainsFromSchool(int schoolID) {
        ReportDatabase.dbWriteExecutor.execute(() -> waterFountainDao.deleteAllFountainsFromSchool(schoolID));
    }

    public LiveData<List<ExternalAccess>> getAllExternalAccessesInSchool(int schoolEntryID) {
        return externalAccessDao.getAllSchoolExternalAccesses(schoolEntryID);
    }

    public LiveData<ExternalAccess> getOneExternalAccess(int externalAccessID) {
        return externalAccessDao.getOneExternalAccess(externalAccessID);
    }

    public LiveData<ExternalAccess> getLastExternalAccess() {
        return externalAccessDao.getLastExternalAccess();
    }

    public void insertExternalAccess(ExternalAccess externalAccess) {
        ReportDatabase.dbWriteExecutor.execute(() -> externalAccessDao.insertExternalAccess(externalAccess));
    }

    public void updateExternalAccess(ExternalAccess externalAccess) {
        ReportDatabase.dbWriteExecutor.execute(() -> externalAccessDao.updateExternalAccess(externalAccess));
    }

    public void deleteOneExternalAccess(int externalAccessID) {
        ReportDatabase.dbWriteExecutor.execute(() -> externalAccessDao.deleteOneExternalAccess(externalAccessID));
    }

    public void deleteAllExternalAccesses(int schoolID) {
        ReportDatabase.dbWriteExecutor.execute(() -> externalAccessDao.deleteAllExternalAccessesFromSchool(schoolID));
    }

    public void insertOtherSpace(OtherSpaces otherSpaces){
        ReportDatabase.dbWriteExecutor.execute(() -> otherSpacesDao.insertOtherSpace(otherSpaces));
    }

    public LiveData<List<OtherSpaces>> selectAllSpaces(int schoolEntryID) {
        return otherSpacesDao.selectAllSpaces(schoolEntryID);
    }

    public LiveData<OtherSpaces> selectOneSpace(int otherID) {
        return otherSpacesDao.selectOneSpace(otherID);
    }

    public void updateOtherSpace(OtherSpaces otherSpaces) {
        ReportDatabase.dbWriteExecutor.execute(() -> otherSpacesDao.updateOtherSpace(otherSpaces));
    }

    public void deleteOneSpace(int otherID) {
        ReportDatabase.dbWriteExecutor.execute(() -> otherSpacesDao.deleteOneSpace(otherID));
    }

    public void deleteAllSpaces(int schoolID) {
        ReportDatabase.dbWriteExecutor.execute(() -> otherSpacesDao.deleteAllSpaces(schoolID));
    }

    public void insertParkingLot(ParkingLotEntry parkingLotEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> parkingLotEntryDao.insertParkingLot(parkingLotEntry));
    }

    public LiveData<List<ParkingLotEntry>> selectEveryParkingLot(int schoolEntryID) {
        return parkingLotEntryDao.selectEveryParkingLot(schoolEntryID);
    }

    public LiveData<ParkingLotEntry> selectOneParkingLot(int parkingLotID) {
        return parkingLotEntryDao.selectOneParkingLot(parkingLotID);
    }

    public LiveData<ParkingLotEntry> selectLastInsertedParkingLot() {
        return parkingLotEntryDao.selectLastInsertedParkingLot();
    }

    public void updateParkingLot(ParkingLotEntry parkingLotEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> parkingLotEntryDao.updateParkingLot(parkingLotEntry));
    }

    public void deleteOneParkingLot(int parkingLotID) {
        ReportDatabase.dbWriteExecutor.execute(() -> parkingLotEntryDao.deleteOneParkingLot(parkingLotID));
    }

    public void deleteAllParkingLots(int schoolID) {
        ReportDatabase.dbWriteExecutor.execute(() -> parkingLotEntryDao.deleteAllParkingLots(schoolID));
    }

    public void insertElderlyParkingLot(ParkingLotElderlyEntry elderlyEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> parkingLotElderlyDao.insertElderlyParkingLot(elderlyEntry));
    }

    public LiveData<ParkingLotElderlyEntry> selectElderlyParkingLot(int parkingLotID) {
        return parkingLotElderlyDao.selectElderlyParkingLot(parkingLotID);
    }

    public void updateElderlyParkingLot(ParkingLotElderlyEntry parkingLotElderlyEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> parkingLotElderlyDao.updateElderlyParkingLot(parkingLotElderlyEntry));
    }

    public void deleteElderlyParkingLot(int parkingLotID) {
        {
            ReportDatabase.dbWriteExecutor.execute(() -> parkingLotElderlyDao.deleteOneElderlyParkingLot(parkingLotID));
        }
    }

    public void insertPdmrParkingLot(ParkingLotPDMREntry pdmrEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> parkingLotPdmrDao.insertPdmrParkingLot(pdmrEntry));
    }

    public LiveData<ParkingLotPDMREntry> selectPdmrParkingLot(int parkingLotID) {
        return parkingLotPdmrDao.selectPdmrParkingLot(parkingLotID);
    }

    public void updatePdmrParkingLot(ParkingLotPDMREntry pdmrEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> parkingLotPdmrDao.updatePdmrParkingLot(pdmrEntry));
    }

    public void deletePdmrParkingLot(int parkingLotID) {
            ReportDatabase.dbWriteExecutor.execute(() -> parkingLotPdmrDao.deleteOnePdmrParkingLot(parkingLotID));
    }

    public void insertRoomEntry(RoomEntry roomEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> roomEntryDao.insertRoomEntry(roomEntry));
    }

    public LiveData<List<RoomEntry>> getAllRooms() {
        return  roomEntryDao.getAllRooms();
    }

    public LiveData<RoomEntry> getRoomEntry(int roomID) {
        return roomEntryDao.getRoomEntry(roomID);
    }

    public LiveData<RoomEntry> getLastRoomEntry() {
        return roomEntryDao.getLastRoomEntry();
    }

    public void updateRoom(RoomEntry roomEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> roomEntryDao.updateRoom(roomEntry));
    }

    public void deleteRoom(RoomEntry roomEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> roomEntryDao.deleteRoom(roomEntry));
    }

    public void insertDoorEntry(DoorEntry doorEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> doorEntryDao.insertDoor(doorEntry));
    }

    public  LiveData<List<DoorEntry>> getDoorsFromRoom(int schoolID, int roomID) {
        return doorEntryDao.getDoorsFromRoom(schoolID, roomID);
    }

    public LiveData<DoorEntry> getSpecificDoor(int doorId) {
        return doorEntryDao.getSpecificDoor(doorId);
    }

    public void updateDoor(DoorEntry doorEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> doorEntryDao.updateDoor(doorEntry));
    }

    public void deleteDoor(int doorID) {
        ReportDatabase.dbWriteExecutor.execute(() -> doorEntryDao.deleteDoor(doorID));
    }

    public void deleteAllDoorsFromRoom(int schoolID, int roomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> doorEntryDao.deleteAllDoorsFromRoom(schoolID, roomID));
    }

    public void insertFreeSpace(FreeSpaceEntry freeSpace) {
        ReportDatabase.dbWriteExecutor.execute(() -> freeSpaceEntryDao.insertFreeSpace(freeSpace));
    }

    public void insertSwitch(SwitchEntry switchEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> switchEntryDao.insertSwitch(switchEntry));
    }

    public void insertWindow(WindowEntry windowEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> windowEntryDao.insertWindow(windowEntry));
    }

    public void insertTable(TableEntry table) {
        ReportDatabase.dbWriteExecutor.execute(() -> tableEntryDao.insertTable(table));
    }

    public void insertGateObs (GateObsEntry gateObs) {
        ReportDatabase.dbWriteExecutor.execute(() -> gateObsDao.insertGateObs(gateObs));
    }

    public LiveData<List<GateObsEntry>> selectAllGateObsEntries(int externalAccessID) {
        return gateObsDao.selectAllGateObsEntries(externalAccessID);
    }

    public LiveData<List<GateObsEntry>> selectGateObsEntry(int gateObsID) {
        return gateObsDao.selectGateObsEntry(gateObsID);
    }

   public void updateGateObs (GateObsEntry gateObs) {
       ReportDatabase.dbWriteExecutor.execute(() -> gateObsDao.updateGateObs(gateObs));
   }

    public void deleteGateObsEntry(int gateObsID) {
        ReportDatabase.dbWriteExecutor.execute(() -> gateObsDao.deleteGateObsEntry(gateObsID));
    }

   public void deleteAllGateObsEntries(int externalAccessID) {
       ReportDatabase.dbWriteExecutor.execute(() -> gateObsDao.deleteAllGateObsEntries(externalAccessID));
   }

    public void insertPayPhone (PayPhoneEntry payPhone) {
        ReportDatabase.dbWriteExecutor.execute(() -> payPhoneDao.insertPayPhone(payPhone));
    }

    public LiveData<List<PayPhoneEntry>> selectAllPayPhones(int SchoolEntryID) {
        return payPhoneDao.selectAllPayPhones(SchoolEntryID);
    }

    public LiveData<List<PayPhoneEntry>> selectPayPhoneEntry(int payPhoneID) {
        return payPhoneDao.selectPayPhoneEntry(payPhoneID);
    }

    public void updatePayPhone (PayPhoneEntry payPhone) {
        ReportDatabase.dbWriteExecutor.execute(() -> payPhoneDao.updatePayPhone(payPhone));
    }

    public void deletePayPhoneEntry(int payPhoneID) {
        ReportDatabase.dbWriteExecutor.execute(() -> payPhoneDao.deletePayPhoneEntry(payPhoneID));
    }

   public void deleteAllPayPhones(int externalAccessID) {
       ReportDatabase.dbWriteExecutor.execute(() -> payPhoneDao.deleteAllPayPhones(externalAccessID));
   }

    public void insertCounter(CounterEntry counter) {
        ReportDatabase.dbWriteExecutor.execute(() -> counterEntryDao.insertCounter(counter));
    }

   public LiveData<List<CounterEntry>> getCountersFromRoom(int roomID) {
       return counterEntryDao.getCountersFromRoom(roomID);
   }

    public LiveData<CounterEntry> getSpecificCounter(int counterID) {
        return counterEntryDao.getSpecificCounter(counterID);
    }

    public void updateCounter (CounterEntry counter) {
        ReportDatabase.dbWriteExecutor.execute(() -> counterEntryDao.updateCounter(counter));
    }

    public void deleteCounter(int counterID) {
        ReportDatabase.dbWriteExecutor.execute(() -> counterEntryDao.deleteCounter(counterID));
    }

    public void deleteAllCounterFromRoom(int counterID) {
        ReportDatabase.dbWriteExecutor.execute(() -> counterEntryDao.deleteAllCounterFromRoom(counterID));
    }

    public void insertRampStairs(RampStairsEntry ramp) {
        ReportDatabase.dbWriteExecutor.execute(() -> rampStairsEntryDao.insertRampStairs(ramp));
    }

    public LiveData<List<RampStairsEntry>> getAllRampStairsFromSchool(int schoolID) {
        return rampStairsEntryDao.getAllRampStairsFromSchool(schoolID);
    }

    public LiveData<RampStairsEntry> getRampStairsEntry(int rampStairsID) {
        return rampStairsEntryDao.getRampStairsEntry(rampStairsID);
    }

    public LiveData<RampStairsEntry> getLastRampStairsEntry() {
        return rampStairsEntryDao.getLastRampStairsEntry();
    }

    public void deleteOneRampStairs(int rampStairsID) {
        ReportDatabase.dbWriteExecutor.execute(() -> rampStairsEntryDao.deleteOneRampStairs(rampStairsID));
    }

    public void deleteAllRampStairsFromSchool(int schoolID) {
        ReportDatabase.dbWriteExecutor.execute(() -> rampStairsEntryDao.deleteAllRampStairsFromSchool(schoolID));
    }

    public void updateRampStairs(RampStairsEntry rampStairs) {
        ReportDatabase.dbWriteExecutor.execute(() -> rampStairsEntryDao.updateRampStairs(rampStairs));
    }

    public void insertRampsStairsFlight(FlightsRampStairsEntry flight) {
        ReportDatabase.dbWriteExecutor.execute(() -> flightsRampStairDao.insertRampsStairsFlight(flight));
    }

    public LiveData<List<FlightsRampStairsEntry>> getAllRampStairsFlights(int rampStairsID) {
        return flightsRampStairDao.getAllRampStairsFlights(rampStairsID);
    }

    public LiveData<FlightsRampStairsEntry> getRampStairsFlightEntry(int flightID) {
        return flightsRampStairDao.getRampStairsFlightEntry(flightID);
    }

    public LiveData<FlightsRampStairsEntry> getLastRampStairsFlightEntry() {
        return flightsRampStairDao.getLastRampStairsFlightEntry();
    }

    public void deleteOneFlight(int flightID) {
        ReportDatabase.dbWriteExecutor.execute(() -> flightsRampStairDao.deleteOneFlight(flightID));
    }

    public void deleteAllFlightsFromOneRampsStairs(int rampStairsID) {
        ReportDatabase.dbWriteExecutor.execute(() -> flightsRampStairDao.deleteAllFlightsFromOneRampsStairs(rampStairsID));
    }

    public void updateFlightRampStairs(FlightsRampStairsEntry flight) {
        ReportDatabase.dbWriteExecutor.execute(() -> flightsRampStairDao.updateFlightRampStairs(flight));
    }

    public void insertRestroomEntry(RestroomEntry restroom) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomEntryDao.insertRestroomEntry(restroom));
    }

    public LiveData<List<RestroomEntry>> getAllSchoolRestroomEntries(int schoolID) {
        return restroomEntryDao.getAllSchoolRestroomEntries(schoolID);
    }

    public LiveData<RestroomEntry> getOneRestroomEntry(int restroomID) {
        return restroomEntryDao.getOneRestroomEntry(restroomID);
    }

    public LiveData<RestroomEntry> getLastRestroomEntry() {
        return restroomEntryDao.getLastRestroomEntry();
    }

    public void updateRestroomEntry(RestroomEntry restroom) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomEntryDao.updateRestroomEntry(restroom));
    }

    public void updateRestroomData(RestroomEntryUpdate... restroomEntryUpdates) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomEntryDao.updateRestroomData(restroomEntryUpdates));
    }

    public void updateRestroomDoorData(RestroomDoorUpdate... doorUpdates) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomEntryDao.updateRestroomDoorData(doorUpdates));
    }

    public void deleteOneRestroomEntry(int restroomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomEntryDao.deleteOneRestroomEntry(restroomID));
    }

    public void deleteAllRestroomEntriesFromSchool(int schoolID) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomEntryDao.deleteAllRestroomEntriesFromSchool(schoolID));
    }

    public void insertRestroomMirrorEntry(RestroomMirrorEntry restroom) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomMirrorDao.insertRestroomMirrorEntry(restroom));
    }

    public LiveData<RestroomMirrorEntry> getOneRestroomMirrorEntry(int restroomID) {
        return restroomMirrorDao.getOneRestroomMirrorEntry(restroomID);
    }

    public void updateRestroomMirrorEntry(RestroomMirrorEntry mirrorEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomMirrorDao.updateRestroomMirrorEntry(mirrorEntry));
    }

    public void deleteOneRestroomMirrorEntry(int restroomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomMirrorDao.deleteOneRestroomMirrorEntry(restroomID));
    }

    public void insertRestroomSinkEntry(RestroomSinkEntry sinkEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomSinkDao.insertRestroomSinkEntry(sinkEntry));
    }

    public LiveData<RestroomSinkEntry> getOneRestroomSinkEntry(int restroomID) {
        return restroomSinkDao.getOneRestroomSinkEntry(restroomID);
    }

    public void updateRestroomSinkEntry(RestroomSinkEntry sinkEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomSinkDao.updateRestroomSinkEntry(sinkEntry));
    }

    public void updateSinkEntryOne(RestroomSinkOne... sinkOne) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomSinkDao.updateSinkEntryOne(sinkOne));
    }

    public void updateSinkEntryTwo(RestroomSinkTwo... sinkTwo) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomSinkDao.updateSinkEntryTwo(sinkTwo));
    }

    public void deleteOneRestroomSinkEntry(int restroomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomSinkDao.deleteOneRestroomSinkEntry(restroomID));
    }

    public void insertRestroomSupportBarEntry(RestroomSupportBarEntry supportBar) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomSupportBarDao.insertRestroomSupportBarEntry(supportBar));
    }

    public LiveData<RestroomSupportBarEntry> getOneRestroomSupportBarEntry(int restroomID) {
        return restroomSupportBarDao.getOneRestroomSupportBarEntry(restroomID);
    }

    public void updateRestroomSupportBarEntry(RestroomSupportBarEntry supportBar) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomSupportBarDao.updateRestroomSupportBarEntry(supportBar));
    }

    public void deleteOneRestroomSupportBarEntry(int restroomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomSupportBarDao.deleteOneRestroomSupportBarEntry(restroomID));
    }

    public void insertRestroomUpViewEntry(RestroomUpViewEntry upViewEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomUpViewDao.insertRestroomUpViewEntry(upViewEntry));
    }

    public LiveData<RestroomUpViewEntry> getOneRestroomUpViewEntry(int restroomID) {
        return restroomUpViewDao.getOneRestroomUpViewEntry(restroomID);
    }

    public LiveData<RestroomUpViewEntry> getLastRestroomUpViewEntry() {
        return restroomUpViewDao.getLastRestroomUpViewEntry();
    }

    public void updateRestroomUpViewEntry(RestroomUpViewEntry upViewEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomUpViewDao.updateRestroomUpViewEntry(upViewEntry));
    }

    public void deleteRestroomUpViewEntry(int restroomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomUpViewDao.deleteRestroomUpViewEntry(restroomID));
    }

    public void insertRestroomUrinalEntry(RestroomUrinalEntry urinalEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomUrinalDao.insertRestroomUrinalEntry(urinalEntry));
    }

    public LiveData<RestroomUrinalEntry> getOneRestroomUrinalEntry(int restroomID) {
        return restroomUrinalDao.getOneRestroomUrinalEntry(restroomID);
    }

    public void updateRestroomUrinalEntry(RestroomUrinalEntry urinalEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomUrinalDao.updateRestroomUrinalEntry(urinalEntry));
    }

    public void deleteOneRestroomUrinalEntry(int restroomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomUrinalDao.deleteOneRestroomUrinalEntry(restroomID));
    }

    public void deleteAllEntries() {
        ReportDatabase.dbWriteExecutor.execute(schoolEntryDao::deleteAll);
    }
}
