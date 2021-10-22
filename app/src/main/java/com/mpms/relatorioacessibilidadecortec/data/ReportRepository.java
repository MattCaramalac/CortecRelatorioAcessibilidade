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
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotPCDEntry;
import com.mpms.relatorioacessibilidadecortec.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RampInclinationEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RampStairsHandrailEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RampStairsRailingEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomDoorUpdate;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomEntryUpdate;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomMirrorEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomSinkEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomSinkOne;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomSinkTwo;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomSupportBarEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomUpViewEntry;
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

public class ReportRepository {

    private ReportDatabase db;

    private final SchoolEntryDao schoolEntryDao;
    private final WaterFountainDao waterFountainDao;
    private final ExternalAccessDao externalAccessDao;
    private final OtherSpacesDao otherSpacesDao;
    private final ParkingLotEntryDao parkingLotEntryDao;
    private final ParkingLotElderlyDao parkingLotElderlyDao;
    private final ParkingLotPcdDao parkingLotPcdDao;
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
    private final SidewalkEntryDao sidewalkEntryDao;
    private final SidewalkSlopeDao sidewalkSlopeDao;
    private final StairsStepDao stairsStepDao;
    private final StairsMirrorDao stairsMirrorDao;
    private final RampInclinationDao rampInclinationDao;
    private final RampStairsHandrailDao handrailDao;
    private final RampStairsRailingDao railingDao;

    public ReportRepository(Application application) {
        db = ReportDatabase.getDatabase(application);
        schoolEntryDao = db.schoolEntryDao();
        waterFountainDao = db.waterFountainDao();
        externalAccessDao = db.externalAccessDao();
        otherSpacesDao = db.otherSpacesDao();
        parkingLotEntryDao = db.parkingLotEntryDao();
        parkingLotPcdDao = db.parkingLotPdmrDao();
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
        sidewalkEntryDao = db.sidewalkEntryDao();
        sidewalkSlopeDao = db.sidewalkSlopeDao();
        stairsStepDao = db.stairsStepDao();
        stairsMirrorDao = db.stairsMirrorDao();
        rampInclinationDao = db.rampInclinationDao();
        handrailDao = db.rampStairsHandrailDao();
        railingDao = db.rampStairsRailingDao();

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

    public void updateSchoolRegOne(SchoolRegisterOne... regOne) {
        ReportDatabase.dbWriteExecutor.execute(() -> schoolEntryDao.updateSchoolRegOne(regOne));
    }

    public void updateSchoolRegTwo(SchoolRegisterTwo... regTwo) {
        ReportDatabase.dbWriteExecutor.execute(() -> schoolEntryDao.updateSchoolRegTwo(regTwo));
    }

    public void updateSchoolRegThree(SchoolRegisterThree... regThree) {
        ReportDatabase.dbWriteExecutor.execute(() -> schoolEntryDao.updateSchoolRegThree(regThree));
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

    public LiveData<List<ParkingLotElderlyEntry>> selectAllElderlyParkingLot(int parkingLotID) {
        return parkingLotElderlyDao.selectAllElderlyParkingLot(parkingLotID);
    }

    public LiveData<ParkingLotElderlyEntry> selectOneElderlyParkingLot(int parkingElderlyID) {
        return parkingLotElderlyDao.selectOneElderlyParkingLot(parkingElderlyID);
    }

    public void updateElderlyParkingLot(ParkingLotElderlyEntry parkingLotElderlyEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> parkingLotElderlyDao.updateElderlyParkingLot(parkingLotElderlyEntry));
    }

    public void deleteElderlyParkingLot(int parkingLotID) {
        {
            ReportDatabase.dbWriteExecutor.execute(() -> parkingLotElderlyDao.deleteOneElderlyParkingLot(parkingLotID));
        }
    }

    public void insertPcdParkingLot(ParkingLotPCDEntry pdmrEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> parkingLotPcdDao.insertPcdParkingLot(pdmrEntry));
    }

    public LiveData<List<ParkingLotPCDEntry>> selectAllPcdParkingLot(int parkingLotID) {
        return parkingLotPcdDao.selectAllPcdParkingLot(parkingLotID);
    }

    public LiveData<ParkingLotPCDEntry> selectOnePcdParkingLot(int parkingPcdID) {
        return parkingLotPcdDao.selectOnePcdParkingLot(parkingPcdID);
    }

    public void updatePcdParkingLot(ParkingLotPCDEntry pdmrEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> parkingLotPcdDao.updatePcdParkingLot(pdmrEntry));
    }

    public void deletePcdParkingLot(int parkingLotID) {
            ReportDatabase.dbWriteExecutor.execute(() -> parkingLotPcdDao.deleteOnePcdParkingLot(parkingLotID));
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

    public LiveData<List<RampStairsEntry>> getAllRampsFromSchool(int schoolID, int rampIdentifier) {
        return rampStairsEntryDao.getAllRampsFromSchool(schoolID, rampIdentifier);
    }

    public LiveData<List<RampStairsEntry>> getAllStairsFromSchool(int schoolID, int stairsIdentifier) {
        return rampStairsEntryDao.getAllStairsFromSchool(schoolID,stairsIdentifier);
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

    public LiveData<RestroomUpViewEntry> getRestroomUpViewByRestroom(int restroomID) {
        return restroomUpViewDao.getRestroomUpViewByRestroom(restroomID);
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

    public LiveData<RestroomSinkEntry> getOneRestroomSinkEntry(int sinkID) {
        return restroomSinkDao.getOneRestroomSinkEntry(sinkID);
    }

    public LiveData<RestroomSinkEntry> getLastRestroomSinkEntry() {
        return restroomSinkDao.getLastRestroomSinkEntry();
    }

    public LiveData<RestroomSinkEntry> getRestroomSinkByRestroom(int restroomID) {
        return restroomSinkDao.getRestroomSinkByRestroom(restroomID);
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

    public LiveData<RestroomSupportBarEntry> getLastRestroomSupportBarEntry() {
        return restroomSupportBarDao.getLastRestroomSupportBarEntry();
    }

    public LiveData<RestroomSupportBarEntry> getRestSupportBarByRestroom(int restroomID) {
        return restroomSupportBarDao.getRestSupportBarByRestroom(restroomID);
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

    public LiveData<RestroomUpViewEntry> getOneRestroomUpViewEntry(int upViewID) {
        return restroomUpViewDao.getOneRestroomUpViewEntry(upViewID);
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

    public void insertSidewalkEntry(SidewalkEntry sidewalkEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> sidewalkEntryDao.insertSidewalkEntry(sidewalkEntry));
    }

    public LiveData<List<SidewalkEntry>> getAllSidewalks(int schoolID) {
        return sidewalkEntryDao.getAllSidewalks(schoolID);
    }

    public LiveData<SidewalkEntry> getSidewalkEntry(int sidewalkID) {
        return sidewalkEntryDao.getSidewalkEntry(sidewalkID);
    }

    public LiveData<SidewalkEntry> getLastSidewalkEntry() {
        return sidewalkEntryDao.getLastSidewalkEntry();
    }

    public void updateSidewalk(SidewalkEntry sidewalkEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> sidewalkEntryDao.updateSidewalk(sidewalkEntry));
    }

    public void deleteSidewalk(int sidewalkID) {
        ReportDatabase.dbWriteExecutor.execute(() -> sidewalkEntryDao.deleteSidewalk(sidewalkID));
    }

    public void deleteAllSidewalksFromSchool(int schoolID) {
        ReportDatabase.dbWriteExecutor.execute(() -> sidewalkEntryDao.deleteAllSidewalksFromSchool(schoolID));
    }

    public void insertSidewalkSlopeEntry(SidewalkSlopeEntry sidewalkSlopeEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> sidewalkSlopeDao.insertSidewalkSlopeEntry(sidewalkSlopeEntry));
    }

    public LiveData<List<SidewalkSlopeEntry>> getAllSidewalkSlopes(int sidewalkID) {
        return sidewalkSlopeDao.getAllSidewalkSlopes(sidewalkID);
    }

    public LiveData<SidewalkSlopeEntry> getSidewalkSlopeEntry(int sidewalkSlopeID) {
        return sidewalkSlopeDao.getSidewalkSlopeEntry(sidewalkSlopeID);
    }

   public void updateSidewalkSlope(SidewalkSlopeEntry sidewalkSlopeEntry) {
       ReportDatabase.dbWriteExecutor.execute(() -> sidewalkSlopeDao.updateSidewalkSlope(sidewalkSlopeEntry));
   }

    public void deleteSidewalkSlope(int sidewalkSlopeID) {
        ReportDatabase.dbWriteExecutor.execute(() -> sidewalkSlopeDao.deleteSidewalkSlope(sidewalkSlopeID));
    }

    public void deleteAllSidewalkSlopesFromSidewalk(int sidewalkID) {
        ReportDatabase.dbWriteExecutor.execute(() -> sidewalkSlopeDao.deleteAllSidewalkSlopesFromSidewalk(sidewalkID));
    }

    public void insertStairsStepEntry(StairsStepEntry stepEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> stairsStepDao.insertStairsStepEntry(stepEntry));
    }

    public LiveData<List<StairsStepEntry>> getAllStairsStepPerFlight(int flightID) {
        return stairsStepDao.getAllStairsStepPerFlight(flightID);
    }

    public LiveData<StairsStepEntry> getOneStairsStepEntry(int stairsStepID) {
        return stairsStepDao.getOneStairsStepEntry(stairsStepID);
    }

    public void updateStairsStepEntry(StairsStepEntry stepEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> stairsStepDao.updateStairsStepEntry(stepEntry));
    }

    public void deleteOneStairsStepEntry(int stairsStepID) {
        ReportDatabase.dbWriteExecutor.execute(() -> stairsStepDao.deleteOneStairsStepEntry(stairsStepID));
    }

    public void deleteAllStairsStepPerFlight(int flightID) {
        ReportDatabase.dbWriteExecutor.execute(() -> stairsStepDao.deleteAllStairsStepPerFlight(flightID));
    }

    public void insertStairsMirrorEntry(StairsMirrorEntry mirrorEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> stairsMirrorDao.insertStairsMirrorEntry(mirrorEntry));
    }

    public LiveData<List<StairsMirrorEntry>> getAllStairsMirrorPerFlight(int flightID) {
        return stairsMirrorDao.getAllStairsMirrorPerFlight(flightID);
    }

    public LiveData<StairsMirrorEntry> getOneStairsMirrorEntry(int stairsMirrorID) {
        return stairsMirrorDao.getOneStairsMirrorEntry(stairsMirrorID);
    }

    public void updateStairsMirrorEntry(StairsMirrorEntry mirrorEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> stairsMirrorDao.updateStairsMirrorEntry(mirrorEntry));
    }

    public void deleteOneStairsMirrorEntry(int stairsMirrorID) {
        ReportDatabase.dbWriteExecutor.execute(() -> stairsMirrorDao.deleteOneStairsMirrorEntry(stairsMirrorID));
    }

    public void deleteAllStairsMirrorPerFlight(int flightID) {
        ReportDatabase.dbWriteExecutor.execute(() -> stairsMirrorDao.deleteAllStairsMirrorPerFlight(flightID));
    }

    public void insertRampInclinationEntry(RampInclinationEntry rampEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> rampInclinationDao.insertRampInclinationEntry(rampEntry));
    }

    public LiveData<List<RampInclinationEntry>> getAllRampInclinationsPerFlight(int flightID) {
        return rampInclinationDao.getAllRampInclinationsPerFlight(flightID);
    }

    public LiveData<RampInclinationEntry> getOneRampInclinationEntry(int rampInclinationID) {
        return rampInclinationDao.getOneRampInclinationEntry(rampInclinationID);
    }

    public void updateRampInclinationEntry(RampInclinationEntry rampEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> rampInclinationDao.updateRampInclinationEntry(rampEntry));
    }

    public void deleteOneRampInclinationEntry(int rampInclinationID) {
        ReportDatabase.dbWriteExecutor.execute(() -> rampInclinationDao.deleteOneRampInclinationEntry(rampInclinationID));
    }

    public void deleteAllRampInclinationsPerFlight(int flightID) {
        ReportDatabase.dbWriteExecutor.execute(() -> rampInclinationDao.deleteAllRampInclinationsPerFlight(flightID));
    }

    public void insertRampStairsRailing(RampStairsRailingEntry railingEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> railingDao.insertRampStairsRailing(railingEntry));
    }

    public LiveData<List<RampStairsRailingEntry>> getAllRampStairsRailings(int flightID){
        return railingDao.getAllRampStairsRailings(flightID);
    }

    public LiveData<RampStairsRailingEntry> getRampStairsRailing(int railingID){
        return railingDao.getRampStairsRailing(railingID);
    }

    public void deleteOneRampStairsRailing(int railingID){
        ReportDatabase.dbWriteExecutor.execute(() -> railingDao.deleteOneRampStairsRailing(railingID));
    }

    public void deleteAllRailingsFromOneRampStairs(int flightID){
        ReportDatabase.dbWriteExecutor.execute(() -> railingDao.deleteAllRailingsFromOneRampStairs(flightID));
    }

    public void updateRampStairsRailing(RampStairsRailingEntry railingEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> railingDao.updateRampStairsRailing(railingEntry));
    }

    public LiveData<Integer> countRampStairsRailings(int flightID) {
        return railingDao.countRampStairsRailings(flightID);
    }

    public LiveData<Integer> countRampStairsHandrails(int flightID) {
        return handrailDao.countRampStairsHandrails(flightID);
    }

    public void insertRampStairsHandrail(RampStairsHandrailEntry handrailEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> handrailDao.insertRampStairsHandrail(handrailEntry));
    }

    public LiveData<List<RampStairsHandrailEntry>> getAllRampStairsHandrails(int flightID){
        return handrailDao.getAllRampStairsHandrails(flightID);
    }

    public LiveData<RampStairsHandrailEntry> getRampStairsHandrail(int handrailID){
        return handrailDao.getRampStairsHandrail(handrailID);
    }

    public void deleteOneRampStairsHandrail(int handrailID){
        ReportDatabase.dbWriteExecutor.execute(() -> handrailDao.deleteOneRampStairsHandrail(handrailID));
    }

    public void deleteAllHandrailsFromOneRampStairs(int flightID){
        ReportDatabase.dbWriteExecutor.execute(() -> handrailDao.deleteAllHandrailsFromOneRampStairs(flightID));
    }

    public void updateRampStairsHandrail(RampStairsHandrailEntry handrailEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> handrailDao.updateRampStairsHandrail(handrailEntry));
    }

    public LiveData<Integer> countStairSteps(int flightID) {
        return stairsStepDao.countStairSteps(flightID);
    }

    public LiveData<Integer> countStairsMirror(int flightID) {
        return stairsMirrorDao.countStairsMirror(flightID);
    }

    public LiveData<Integer> countRampInclination(int flightID) {
        return rampInclinationDao.countRampInclination(flightID);
    }

    public void deleteAllEntries() {
        ReportDatabase.dbWriteExecutor.execute(schoolEntryDao::deleteAll);
    }
}
