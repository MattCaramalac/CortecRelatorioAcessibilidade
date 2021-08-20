package com.mpms.relatorioacessibilidadecortec.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.mpms.relatorioacessibilidadecortec.entities.CounterEntry;
import com.mpms.relatorioacessibilidadecortec.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.entities.FlightRampEntry;
import com.mpms.relatorioacessibilidadecortec.entities.FlightStairsEntry;
import com.mpms.relatorioacessibilidadecortec.entities.FreeSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.entities.GateObsEntry;
import com.mpms.relatorioacessibilidadecortec.entities.OtherSpaces;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotElderlyEntry;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotPDMREntry;
import com.mpms.relatorioacessibilidadecortec.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RampEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.entities.StairsEntry;
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
    private final StairsEntryDao stairsEntryDao;
    private final FlightStairsDao flightStairsDao;
    private final RampEntryDao rampEntryDao;
    private final FlightRampDao flightRampDao;

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
        stairsEntryDao = db.stairsEntryDao();
        flightStairsDao = db.flightStairsDao();
        rampEntryDao = db.rampEntryDao();
        flightRampDao = db.flightRampDao();

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

    public void insertStairs(StairsEntry stairs) {
        ReportDatabase.dbWriteExecutor.execute(() -> stairsEntryDao.insertStairs(stairs));
    }

    public LiveData<List<StairsEntry>> getAllStairsFromSchool(int schoolID) {
        return stairsEntryDao.getAllStairs(schoolID);
    }

    public LiveData<StairsEntry> getStairsEntry(int stairsID) {
        return stairsEntryDao.getStairsEntry(stairsID);
    }

    public LiveData<StairsEntry> getLastStairsEntry() {
        return stairsEntryDao.getLastStairsEntry();
    }

    public void deleteOneStairs(int stairsID) {
        ReportDatabase.dbWriteExecutor.execute(() -> stairsEntryDao.deleteOneStairs(stairsID));
    }

    public void deleteAllStairsFromSchool(int schoolID) {
        ReportDatabase.dbWriteExecutor.execute(() -> stairsEntryDao.deleteAllStairsFromSchool(schoolID));
    }

    public void updateStairs(StairsEntry stairs) {
        ReportDatabase.dbWriteExecutor.execute(() -> stairsEntryDao.updateStairs(stairs));
    }

    public void insertStairsFlight(FlightStairsEntry flight) {
        ReportDatabase.dbWriteExecutor.execute(() -> flightStairsDao.insertStairsFlight(flight));
    }

    public LiveData<List<FlightStairsEntry>> getAllStairsFlights(int stairsID) {
        return flightStairsDao.getAllStairsFlights(stairsID);
    }

    public LiveData<FlightStairsEntry> getStairsFLightEntry(int flightID) {
        return flightStairsDao.getStairsFLightEntry(flightID);
    }

    public LiveData<FlightStairsEntry> getLastStairsFlightEntry() {
        return flightStairsDao.getLastStairsFlightEntry();
    }

    public void deleteOneFlightOFStairs(int flightID) {
        ReportDatabase.dbWriteExecutor.execute(() -> flightStairsDao.deleteOneFlightOFStairs(flightID));
    }

    public void deleteAllFlightsFromStairs(int stairsID) {
        ReportDatabase.dbWriteExecutor.execute(() -> flightStairsDao.deleteAllFlightsFromStairs(stairsID));
    }

    public void updateFlightStairs(FlightStairsEntry flightStairs) {
        ReportDatabase.dbWriteExecutor.execute(() -> flightStairsDao.updateFlightStairs(flightStairs));
    }

    public void insertRamp(RampEntry ramp) {
        ReportDatabase.dbWriteExecutor.execute(() -> rampEntryDao.insertRamp(ramp));
    }

    public LiveData<List<RampEntry>> getAllRampsFromSchool(int schoolID) {
        return rampEntryDao.getAllRampsFromSchool(schoolID);
    }

    public LiveData<RampEntry> getRampEntry(int rampID) {
        return rampEntryDao.getRampEntry(rampID);
    }

    public LiveData<RampEntry> getLastRampEntry() {
        return rampEntryDao.getLastRampEntry();
    }

    public void deleteOneRamp(int rampID) {
        ReportDatabase.dbWriteExecutor.execute(() -> rampEntryDao.deleteOneRamp(rampID));
    }

    public void deleteAllRampsFromSchool(int schoolID) {
        ReportDatabase.dbWriteExecutor.execute(() -> rampEntryDao.deleteAllRampsFromSchool(schoolID));
    }

    public void updateRamp(RampEntry ramp) {
        ReportDatabase.dbWriteExecutor.execute(() -> rampEntryDao.updateRamp(ramp));
    }

    public void insertRampFlight(FlightRampEntry flight) {
        ReportDatabase.dbWriteExecutor.execute(() -> flightRampDao.insertRampFlight(flight));
    }

    public LiveData<List<FlightRampEntry>> getAllRampFlights(int rampID) {
        return flightRampDao.getAllRampFlights(rampID);
    }

    public LiveData<FlightRampEntry> getRampFLightEntry(int flightRampID) {
        return flightRampDao.getRampFLightEntry(flightRampID);
    }

    public LiveData<FlightRampEntry> getLastRampFlightEntry() {
        return flightRampDao.getLastRampFlightEntry();
    }

    public void deleteOneFlightOfRamp(int flightRampID) {
        ReportDatabase.dbWriteExecutor.execute(() -> flightRampDao.deleteOneFlightOfRamp(flightRampID));
    }

    public void deleteAllFlightsFromRamp(int rampID) {
        ReportDatabase.dbWriteExecutor.execute(() -> flightRampDao.deleteAllFlightsFromRamp(rampID));
    }

    public void updateFlightRamp(FlightRampEntry rampEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> flightRampDao.updateFlightRamp(rampEntry));
    }

    public void deleteAllEntries() {
        ReportDatabase.dbWriteExecutor.execute(schoolEntryDao::deleteAll);
    }
}
