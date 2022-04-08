package com.mpms.relatorioacessibilidadecortec.data.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.mpms.relatorioacessibilidadecortec.data.Dao.AdmEquipDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.BlackboardEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.BlockSpaceDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.CounterEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.DoorEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.DoorLockDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.ExternalAccessDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.FlightRampStairsDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.FreeSpaceEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.GateObsDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.OtherSpacesDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.ParkingLotElderlyDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.ParkingLotEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.ParkingLotPcdDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.PayPhoneDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.PlaygroundEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RampInclinationDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RampStairsEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RampStairsHandrailDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RampStairsRailingDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RestroomEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RestroomMirrorDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RestroomSinkDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RestroomSupportBarDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RestroomUpViewDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RestroomUrinalDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RoomEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.SchoolEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.SidewalkEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.SidewalkSlopeDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.StairsMirrorDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.StairsStepDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.SwitchEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.TableEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.WaterFountainDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.WindowEntryDao;
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
import com.mpms.relatorioacessibilidadecortec.data.entities.FlightsRampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.FreeSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.GateObsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.OtherSpaces;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotElderlyEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotPCDEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PlaygroundEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampInclinationEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsHandrailEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsRailingEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomDoorUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntryUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomMirrorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomSinkEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomSinkOne;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomSinkTwo;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomSupportBarEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomUpViewEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomUrinalEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolRegisterOne;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolRegisterThree;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolRegisterTwo;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkSlopeEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.StairsMirrorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.StairsStepEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SwitchEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.TableEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WindowEntry;

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
    private final BlockSpaceDao blockSpaceDao;
    private final AdmEquipDao admEquipDao;
    private final PlaygroundEntryDao playgroundEntryDao;
    private final BlackboardEntryDao blackboardEntryDao;
    private final DoorLockDao doorLockDao;

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
        blockSpaceDao = db.blockSpaceDao();
        admEquipDao = db.admEquipDao();
        playgroundEntryDao = db.playgroundEntryDao();
        blackboardEntryDao = db.blackboardEntryDao();
        doorLockDao = db.doorLockDao();

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

    public void insertBlockSpace(BlockSpaceEntry blockSpace) {
        ReportDatabase.dbWriteExecutor.execute(() -> blockSpaceDao.insertBlockSpace(blockSpace));
    }

    public LiveData<List<BlockSpaceEntry>> getBlockSpaceFromSchool(int schoolID) {
        return blockSpaceDao.getBlockSpaceFromSchool(schoolID);
    }

    public LiveData<BlockSpaceEntry> getSpecificBlockSpace(int blockSpaceID) {
        return blockSpaceDao.getSpecificBlockSpace(blockSpaceID);
    }

    public LiveData<BlockSpaceEntry> getLastBlockSpace(int schoolID, int blockSpaceType) {
        return blockSpaceDao.getLastBlockSpace(schoolID, blockSpaceType);
    }

    public void updateBlockSpace (BlockSpaceEntry blockSpace) {
        ReportDatabase.dbWriteExecutor.execute(() -> blockSpaceDao.updateBlockSpace(blockSpace));
    }

    public void deleteBlockSpace(int blockSpaceID) {
        ReportDatabase.dbWriteExecutor.execute(() -> blockSpaceDao.deleteBlockSpace(blockSpaceID));
    }

    public void deleteAllBlockSpacesSchool(int schoolID) {
        ReportDatabase.dbWriteExecutor.execute(() -> blockSpaceDao.deleteAllBlockSpacesSchool(schoolID));
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

    public LiveData<List<ExternalAccess>> getAllExternalAccessesInSchool(int blockID) {
        return externalAccessDao.getAllSchoolExternalAccesses(blockID);
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

    public void updateExtAccessRegOne(ExtAccessSocialOne... regOne) {
        ReportDatabase.dbWriteExecutor.execute(() -> externalAccessDao.updateExtAccessRegOne(regOne));
    }

    public void updateExtAccessRegTwo(ExtAccessSocialTwo... regTwo) {
        ReportDatabase.dbWriteExecutor.execute(() -> externalAccessDao.updateExtAccessRegTwo(regTwo));
    }

    public void updateExtAccessRegThree(ExtAccessSocialThree... regThree) {
        ReportDatabase.dbWriteExecutor.execute(() -> externalAccessDao.updateExtAccessRegThree(regThree));
    }

    public void deleteOneExternalAccess(int externalAccessID) {
        ReportDatabase.dbWriteExecutor.execute(() -> externalAccessDao.deleteOneExternalAccess(externalAccessID));
    }

    public void deleteAllExternalAccesses(int blockID) {
        ReportDatabase.dbWriteExecutor.execute(() -> externalAccessDao.deleteAllExternalAccessesFromSchool(blockID));
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

    public LiveData<List<RoomEntry>> getAllRoomsInSchool(int schoolID, int roomType) {
        return  roomEntryDao.getAllRoomsInSchool(schoolID, roomType);
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

    public  LiveData<List<DoorEntry>> getDoorsFromRoom(int roomID) {
        return doorEntryDao.getDoorsFromRoom(roomID);
    }

    public LiveData<DoorEntry> getSpecificDoor(int doorId) {
        return doorEntryDao.getSpecificDoor(doorId);
    }

    public LiveData<DoorEntry> getLastDoorEntry() {
        return doorEntryDao.getLastDoorEntry();
    }

    public void updateDoor(DoorEntry doorEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> doorEntryDao.updateDoor(doorEntry));
    }

    public void deleteDoor(int doorID) {
        ReportDatabase.dbWriteExecutor.execute(() -> doorEntryDao.deleteDoor(doorID));
    }

    public void deleteAllDoorsFromRoom(int roomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> doorEntryDao.deleteAllDoorsFromRoom(roomID));
    }

    public void insertFreeSpace(FreeSpaceEntry freeSpace) {
        ReportDatabase.dbWriteExecutor.execute(() -> freeSpaceEntryDao.insertFreeSpace(freeSpace));
    }

    public LiveData<List<FreeSpaceEntry>> selectFreeSpaceFromRoom(int roomID) {
        return freeSpaceEntryDao.selectFreeSpaceFromRoom(roomID);
    }

    public LiveData<FreeSpaceEntry> selectSpecificFreeSpace(int freeSpaceID) {
        return freeSpaceEntryDao.selectSpecificFreeSpace(freeSpaceID);
    }

    public void updateFreeSpace(FreeSpaceEntry freeSpace) {
        ReportDatabase.dbWriteExecutor.execute(() -> freeSpaceEntryDao.updateFreeSpace(freeSpace));
    }

    public void deleteFreeSpace(int freeSpaceID) {
        ReportDatabase.dbWriteExecutor.execute(() -> freeSpaceEntryDao.deleteFreeSpace(freeSpaceID));
    }

    public void deleteAllFreeSpaceFromRoom(int roomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> freeSpaceEntryDao.deleteAllFreeSpaceFromRoom(roomID));
    }

    public void insertSwitch(SwitchEntry switchEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> switchEntryDao.insertSwitch(switchEntry));
    }

    public LiveData<List<SwitchEntry>> selectSwitchesFromRoom(int roomID) {
        return switchEntryDao.selectSwitchesFromRoom(roomID);
    }

    public LiveData<SwitchEntry> selectSpecificSwitch(int switchID) {
        return switchEntryDao.selectSpecificSwitch(switchID);
    }

    public void updateSwitch(SwitchEntry switchEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> switchEntryDao.updateSwitch(switchEntry));
    }

    public void deleteSwitch(int switchID) {
        ReportDatabase.dbWriteExecutor.execute(() -> switchEntryDao.deleteSwitch(switchID));
    }

    public void deleteAllSwitchesFromRoom(int roomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> switchEntryDao.deleteAllSwitchesFromRoom(roomID));
    }

    public void insertWindow(WindowEntry windowEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> windowEntryDao.insertWindow(windowEntry));
    }

    public LiveData<List<WindowEntry>> selectWindowsFromRoom(int roomID) {
        return windowEntryDao.selectWindowsFromRoom(roomID);
    }

    public LiveData<WindowEntry> selectSpecificWindow(int windowID) {
        return windowEntryDao.selectSpecificWindow(windowID);
    }

    public void updateWindowEntry(WindowEntry windowEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> windowEntryDao.updateWindowEntry(windowEntry));
    }

    public void deleteWindow(int windowID) {
        ReportDatabase.dbWriteExecutor.execute(() -> windowEntryDao.deleteWindow(windowID));
    }

    public void deleteAllWindowsFromRoom(int roomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> windowEntryDao.deleteAllWindowsFromRoom(roomID));
    }

    public void insertTable(TableEntry table) {
        ReportDatabase.dbWriteExecutor.execute(() -> tableEntryDao.insertTable(table));
    }

    public LiveData<List<TableEntry>> selectTablesFromRoom(int roomID) {
        return tableEntryDao.selectTablesFromRoom(roomID);
    }

    public LiveData<TableEntry> selectSpecificTable(int tableID) {
        return tableEntryDao.selectSpecificTable(tableID);
    }

    public void updateTable(TableEntry table) {
        ReportDatabase.dbWriteExecutor.execute(() -> tableEntryDao.updateTable(table));
    }

    public void deleteTable(int tableID) {
        ReportDatabase.dbWriteExecutor.execute(() -> tableEntryDao.deleteTable(tableID));
    }

    public void deleteAllTablesFromRoom(int roomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> tableEntryDao.deleteAllTablesFromRoom(roomID));
    }

    public void insertGateObs (GateObsEntry gateObs) {
        ReportDatabase.dbWriteExecutor.execute(() -> gateObsDao.insertGateObs(gateObs));
    }

    public LiveData<List<GateObsEntry>> selectAllGateObsEntries(int externalAccessID) {
        return gateObsDao.selectAllGateObsEntries(externalAccessID);
    }

    public LiveData<GateObsEntry> selectGateObsEntry(int gateObsID) {
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
        return payPhoneDao.selectAllPhonesExtAccess(SchoolEntryID);
    }

    public LiveData<List<PayPhoneEntry>> selectAllPhonesSidewalk(int sidewalkID) {
        return payPhoneDao.selectAllPhonesSidewalk(sidewalkID);
    }

    public void deleteAllPayPhonesSidewalk(int sidewalkID) {
        ReportDatabase.dbWriteExecutor.execute(() -> payPhoneDao.deleteAllPayPhonesSidewalk(sidewalkID));
    }


    public LiveData<PayPhoneEntry> selectPayPhoneEntry(int payPhoneID) {
        return payPhoneDao.selectPayPhoneEntry(payPhoneID);
    }

    public void updatePayPhone (PayPhoneEntry payPhone) {
        ReportDatabase.dbWriteExecutor.execute(() -> payPhoneDao.updatePayPhone(payPhone));
    }

    public void deletePayPhoneEntry(int payPhoneID) {
        ReportDatabase.dbWriteExecutor.execute(() -> payPhoneDao.deletePayPhoneEntry(payPhoneID));
    }

   public void deleteAllPayPhones(int externalAccessID) {
       ReportDatabase.dbWriteExecutor.execute(() -> payPhoneDao.deleteAllPayPhonesExtAccess(externalAccessID));
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

    public void insertAdmEquip(AdmEquipEntry admEquip) {
        ReportDatabase.dbWriteExecutor.execute(() -> admEquipDao.insertAdmEquip(admEquip));
    }

    public LiveData<List<AdmEquipEntry>> getAllAdmEquipsPerBlock(int blockID) {
        return admEquipDao.getAllAdmEquipsPerBlock(blockID);
    }

    public LiveData<AdmEquipEntry> getOneAdmEquip(int admEquipID) {
        return admEquipDao.getOneAdmEquip(admEquipID);
    }

    public void updateAdmEquip(AdmEquipEntry admEquip) {
        ReportDatabase.dbWriteExecutor.execute(() -> admEquipDao.updateAdmEquip(admEquip));
    }

    public void deleteOneAdmEquip(int admEquipID) {
        ReportDatabase.dbWriteExecutor.execute(() -> admEquipDao.deleteOneAdmEquip(admEquipID));
    }

    public void deleteAllAdmEquipsFromBlock(int blockID) {
        ReportDatabase.dbWriteExecutor.execute(() -> admEquipDao.deleteAllAdmEquipsFromBlock(blockID));
    }

    public void insertPlayground(PlaygroundEntry play) {
        ReportDatabase.dbWriteExecutor.execute(() -> playgroundEntryDao.insertPlayground(play));
    }

    public LiveData<List<PlaygroundEntry>> getAllPlaygroundsPerBlock(int blockID) {
        return playgroundEntryDao.getAllPlaygroundsPerBlock(blockID);
    }

    public LiveData<PlaygroundEntry> getOnePlayground(int playgroundID) {
        return playgroundEntryDao.getOnePlayground(playgroundID);
    }

    public void updatePlayground(PlaygroundEntry play) {
        ReportDatabase.dbWriteExecutor.execute(() -> playgroundEntryDao.updatePlayground(play));
    }

    public void deleteOnePlayground(int playgroundID) {
        ReportDatabase.dbWriteExecutor.execute(() -> playgroundEntryDao.deleteOnePlayground(playgroundID));
    }

    public void deleteAllPlaygroundsFromBlock(int blockID) {
        ReportDatabase.dbWriteExecutor.execute(() -> playgroundEntryDao.deleteAllPlaygroundsFromBlock(blockID));
    }

    public void deleteAllEntries() {
        ReportDatabase.dbWriteExecutor.execute(schoolEntryDao::deleteAll);
    }

    public void insertBlackboard(BlackboardEntry blackboard) {
        ReportDatabase.dbWriteExecutor.execute(() -> blackboardEntryDao.insertBlackboard(blackboard));
    }

    public LiveData<List<BlackboardEntry>> getAllBlackboardsFromRoom(int roomID) {
        return blackboardEntryDao.getAllBlackboardsFromRoom(roomID);
    }

    public LiveData<BlackboardEntry> getOneBlackboard(int blackboardID) {
        return blackboardEntryDao.getOneBlackboard(blackboardID);
    }

    public void updateBlackboard (BlackboardEntry blackboard) {
        ReportDatabase.dbWriteExecutor.execute(() -> blackboardEntryDao.updateBlackboard(blackboard));
    }

    public void deleteBlackboard(int blackboardID) {
        ReportDatabase.dbWriteExecutor.execute(() -> blackboardEntryDao.deleteBlackboard(blackboardID));
    }

    public void deleteAllBlackboardsFromRoom(int roomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> blackboardEntryDao.deleteAllBlackboardsFromRoom(roomID));
    }

    public void insertDoorLock(DoorLockEntry doorLock) {
        ReportDatabase.dbWriteExecutor.execute(() -> doorLockDao.insertDoorLock(doorLock));
    }

    public LiveData<List<DoorLockEntry>> getDoorLocksFromDoor(int doorID) {
        return doorLockDao.getDoorLocksFromDoor(doorID);
    }

    public LiveData<List<DoorLockEntry>> getDoorLocksFromGates(int extID) {
        return doorLockDao.getDoorLocksFromGates(extID);
    }

    public LiveData<DoorLockEntry> getOneDoorLock(int lockID) {
        return doorLockDao.getOneDoorLock(lockID);
    }

    public LiveData<DoorLockEntry> getLastDoorLockEntry() {
        return doorLockDao.getLastDoorLockEntry();
    }

    public void updateDoorLock(DoorLockEntry doorLock) {
        ReportDatabase.dbWriteExecutor.execute(() -> doorLockDao.updateDoorLock(doorLock));
    }

    public void deleteDoorLock(int lockID) {
        ReportDatabase.dbWriteExecutor.execute(() -> doorLockDao.deleteDoorLock(lockID));
    }

    public void deleteAllDoorLocksFromDoor(int doorID) {
        ReportDatabase.dbWriteExecutor.execute(() -> doorLockDao.deleteAllDoorLocksFromDoor(doorID));
    }
}
