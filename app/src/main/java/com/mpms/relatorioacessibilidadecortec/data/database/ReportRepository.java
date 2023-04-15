package com.mpms.relatorioacessibilidadecortec.data.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.mpms.relatorioacessibilidadecortec.data.Dao.BlackboardEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.BlockSpaceDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.CirculationDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.CounterEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.DoorEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.DoorLockDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.EquipEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.ExternalAccessDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.FlightRampStairsDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.FreeSpaceEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.GateObsDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.ParkingLotElderlyDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.ParkingLotEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.ParkingLotPcdDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.PayPhoneDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.PlaygroundEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RampStairsEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RampStairsHandrailDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RampStairsRailingDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RestBoxDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RestroomEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RoomEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.SchoolEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.SidewalkEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.SidewalkSlopeDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.SlopeDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.SoleStepDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.SwitchEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.TableEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.WaterFountainDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.WindowEntryDao;
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
import com.mpms.relatorioacessibilidadecortec.data.entities.FreeSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.GateObsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotElderlyEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotPCDEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PlaygroundEntry;
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

public class ReportRepository {

    private ReportDatabase db;

    private final SchoolEntryDao schoolEntryDao;
    private final WaterFountainDao waterFountainDao;
    private final ExternalAccessDao externalAccessDao;
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
    private final SidewalkEntryDao sidewalkEntryDao;
    private final SidewalkSlopeDao sidewalkSlopeDao;
    private final RampStairsHandrailDao handrailDao;
    private final RampStairsRailingDao railingDao;
    private final BlockSpaceDao blockSpaceDao;
    private final PlaygroundEntryDao playgroundEntryDao;
    private final BlackboardEntryDao blackboardEntryDao;
    private final DoorLockDao doorLockDao;
    private final RestBoxDao restBoxDao;
    private final EquipEntryDao equipEntryDao;
    private final CirculationDao circulationDao;
    private final SoleStepDao stepDao;
    private final SlopeDao slopeDao;

    public ReportRepository(Application application) {
        db = ReportDatabase.getDatabase(application);
        schoolEntryDao = db.schoolEntryDao();
        waterFountainDao = db.waterFountainDao();
        externalAccessDao = db.externalAccessDao();
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
        sidewalkEntryDao = db.sidewalkEntryDao();
        sidewalkSlopeDao = db.sidewalkSlopeDao();
        handrailDao = db.rampStairsHandrailDao();
        railingDao = db.rampStairsRailingDao();
        blockSpaceDao = db.blockSpaceDao();
        playgroundEntryDao = db.playgroundEntryDao();
        blackboardEntryDao = db.blackboardEntryDao();
        doorLockDao = db.doorLockDao();
        restBoxDao = db.restBoxDao();
        equipEntryDao = db.equipEntryDao();
        circulationDao = db.circulationDao();
        stepDao = db.soleStepDao();
        slopeDao = db.slopeDao();
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
        ReportDatabase.dbWriteExecutor.execute(() -> schoolEntryDao.deleteOneSchoolEntry(schoolEntry));
    }

    public void delSchoolWithID(int schID) {
        ReportDatabase.dbWriteExecutor.execute(() -> schoolEntryDao.deleteSchoolWithID(schID));
    }

    public void insertBlockSpace(BlockSpaceEntry blockSpace) {
        ReportDatabase.dbWriteExecutor.execute(() -> blockSpaceDao.insertBlockSpace(blockSpace));
    }

    public LiveData<List<BlockSpaceEntry>> getBlockSpaceFromSchool(int schoolID) {
        return blockSpaceDao.getBlockSpaceFromSchool(schoolID);
    }

    public LiveData<List<BlockSpaceEntry>> getAllBlocksSchool(int schoolID) {
        return blockSpaceDao.getAllBlocksSchool(schoolID);
    }

    public LiveData<List<Integer>> getAllBlockIds(int schoolID) {
        return blockSpaceDao.getAllIds(schoolID);
    }

    public LiveData<BlockSpaceEntry> getSpecificBlockSpace(int blockSpaceID) {
        return blockSpaceDao.getSpecificBlockSpace(blockSpaceID);
    }

    public LiveData<BlockSpaceEntry> getLastBlockSpace(int schoolID, int blockSpaceType) {
        return blockSpaceDao.getLastBlockSpace(schoolID, blockSpaceType);
    }

    public LiveData<BlockSpaceEntry> getAreaFromSchool(int schoolID, int areaType) {
        return blockSpaceDao.getAreaFromSchool(schoolID, areaType);
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

    public LiveData<List<WaterFountainEntry>> getAllBlockWaterFountains(int blockID) {
        return waterFountainDao.getAllBlockWaterFountains(blockID);
    }

    public LiveData<List<WaterFountainEntry>> getAllWaterFountains(List<Integer> blockID) {
        return waterFountainDao.getAllWaterFountains(blockID);
    }

    public LiveData<List<WaterFountainEntry>> getRoomWaterFountains(int roomID) {
        return waterFountainDao.getRoomWaterFountains(roomID);
    }

    public LiveData<List<WaterFountainEntry>> getAllRoomWaterFountains(List<Integer> roomID) {
        return waterFountainDao.getAllRoomWaterFountains(roomID);
    }

    public LiveData<WaterFountainEntry> getOneWaterFountain(int waterFountainID) {
        return waterFountainDao.getOneWaterFountain(waterFountainID);
    }

    public LiveData<List<WaterFountainEntry>> getCircWaterFountains(int circID) {
        return waterFountainDao.getCircWaterFountains(circID);
    }

    public LiveData<List<WaterFountainEntry>> getAllCircWaterFountains(List<Integer> circID) {
        return waterFountainDao.getAllCircWaterFountains(circID);
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

    public LiveData<List<ExternalAccess>> getAllExtAccess(List<Integer> blockID) {
        return externalAccessDao.getAllExtAccess(blockID);
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

    public void insertParkingLot(ParkingLotEntry parkingLotEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> parkingLotEntryDao.insertParkingLot(parkingLotEntry));
    }

    public LiveData<List<ParkingLotEntry>> getParkingLotFromBlock(int blockID) {
        return parkingLotEntryDao.getParkingLotFromBlock(blockID);
    }

    public LiveData<List<ParkingLotEntry>> getParkingLotFromSide(int blockID, int sideID) {
        return parkingLotEntryDao.getParkingLotFromSide(blockID, sideID);
    }

    public LiveData<List<ParkingLotEntry>> getAllParkingLots(List<Integer> blockID) {
        return parkingLotEntryDao.getAllParkingLots(blockID);
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

    public LiveData<List<ParkingLotElderlyEntry>> getElderVacanciesPark(int parkingLotID) {
        return parkingLotElderlyDao.getElderVacanciesPark(parkingLotID);
    }

    public LiveData<List<ParkingLotElderlyEntry>> getAllElderVacancies(List<Integer> parkingLotID) {
        return parkingLotElderlyDao.getAllElderVacancies(parkingLotID);
    }

    public LiveData<ParkingLotElderlyEntry> getOneElderVacancy(int parkingElderlyID) {
        return parkingLotElderlyDao.getOneElderVacancy(parkingElderlyID);
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

    public LiveData<List<ParkingLotPCDEntry>> getPcdVacanciesPark(int parkingLotID) {
        return parkingLotPcdDao.getPcdVacanciesPark(parkingLotID);
    }

    public LiveData<List<ParkingLotPCDEntry>> getAllPcdVacancies(List<Integer> parkingLotID) {
        return parkingLotPcdDao.getAllPcdVacancies(parkingLotID);
    }

    public LiveData<ParkingLotPCDEntry> getOnePcdVacancy(int parkingPcdID) {
        return parkingLotPcdDao.getOnePcdVacancy(parkingPcdID);
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
        return  roomEntryDao.getAllRoomsInBlock(schoolID, roomType);
    }

    public LiveData<List<RoomEntry>> getAllRoomsInSchool(List<Integer> blockID) {
        return  roomEntryDao.getAllRoomsInSchool(blockID);
    }

    public LiveData<List<RoomEntry>> getAllRoomsInSchool(int blockID) {
        return  roomEntryDao.getAllRoomsInSchool(blockID);
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

    public void deleteRoom(int roomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> roomEntryDao.deleteOneRoom(roomID));
    }

    public void insertDoorEntry(DoorEntry doorEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> doorEntryDao.insertDoor(doorEntry));
    }

    public  LiveData<List<DoorEntry>> getDoorsFromRoom(int roomID) {
        return doorEntryDao.getDoorsFromRoom(roomID);
    }

    public LiveData<List<DoorEntry>> getAllDoors(List<Integer> roomID) {
        return doorEntryDao.getAllDoors(roomID);
    }

    public LiveData<List<DoorEntry>> getAllRestDoors(List<Integer> restID) {
        return doorEntryDao.getAllRestDoors(restID);
    }

    public LiveData<List<DoorEntry>> getCircDoor(int circID) {
        return doorEntryDao.getCircDoor(circID);
    }

    public LiveData<List<DoorEntry>> getAllCircDoors(List<Integer> circID) {
        return doorEntryDao.getAllCircDoors(circID);
    }

    public LiveData<List<DoorEntry>> getAllBoxDoors(List<Integer> boxID) {
        return doorEntryDao.getAllBoxDoors(boxID);
    }

    public LiveData<DoorEntry> getSpecificDoor(int doorId) {
        return doorEntryDao.getSpecificDoor(doorId);
    }

    public LiveData<DoorEntry> getRestDoor(int restID) {
        return doorEntryDao.getRestDoor(restID);
    }

    public LiveData<DoorEntry> getAccBoxDoor(int boxID) {
        return doorEntryDao.getAccBoxDoor(boxID);
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

    public void deleteRestDoor(int restID) {
        ReportDatabase.dbWriteExecutor.execute(() -> doorEntryDao.deleteRestDoor(restID));
    }

    public void deleteAllDoorsFromRoom(int roomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> doorEntryDao.deleteAllDoorsFromRoom(roomID));
    }

    public void insertFreeSpace(FreeSpaceEntry freeSpace) {
        ReportDatabase.dbWriteExecutor.execute(() -> freeSpaceEntryDao.insertFreeSpace(freeSpace));
    }

    public LiveData<List<FreeSpaceEntry>> getFreeSpaceFromRoom(int roomID) {
        return freeSpaceEntryDao.selectFreeSpaceFromRoom(roomID);
    }

    public LiveData<List<FreeSpaceEntry>> getFreeSpaceFromRest(int restID) {
        return freeSpaceEntryDao.selectFreeSpaceFromRest(restID);
    }

    public LiveData<List<FreeSpaceEntry>> getAllFreeSpaces(List<Integer> roomID) {
        return freeSpaceEntryDao.getAllFreeSpaces(roomID);
    }

    public LiveData<List<FreeSpaceEntry>> getAllRestFreeSpaces(List<Integer> restID) {
        return freeSpaceEntryDao.getAllRestFreeSpaces(restID);
    }

    public LiveData<List<FreeSpaceEntry>> getFreeSpaceFromCirc(int circID) {
        return freeSpaceEntryDao.getFreeSpaceFromCirc(circID);
    }

    public LiveData<List<FreeSpaceEntry>> getAllCircFreeSpaces(List<Integer> circID) {
        return freeSpaceEntryDao.getAllCircFreeSpaces(circID);
    }

    public LiveData<FreeSpaceEntry> getSpecificFreeSpace(int freeSpaceID) {
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

    public LiveData<List<SwitchEntry>> getSwitchesFromRoom(int roomID) {
        return switchEntryDao.getSwitchesFromRoom(roomID);
    }

    public LiveData<List<SwitchEntry>> getAllRoomsSwitches(List<Integer> roomID) {
        return switchEntryDao.getAllRoomsSwitches(roomID);
    }

    public LiveData<List<SwitchEntry>> getSwitchesFromCirc(int circID) {
        return switchEntryDao.getSwitchesFromCirc(circID);
    }

    public LiveData<List<SwitchEntry>> getAllCircSwitches(List<Integer> circID) {
        return switchEntryDao.getAllCircSwitches(circID);
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

    public LiveData<List<WindowEntry>> getWindowsFromRoom(int roomID) {
        return windowEntryDao.getWindowsFromRoom(roomID);
    }

    public LiveData<List<WindowEntry>> getAllRoomsWindows(List<Integer> roomID) {
        return windowEntryDao.getAllRoomsWindows(roomID);
    }

    public LiveData<List<WindowEntry>> getWindowsFromCirc(int circID) {
        return windowEntryDao.getWindowsFromCirc(circID);
    }

    public LiveData<List<WindowEntry>> getAllCircWindows(List<Integer> circID) {
        return windowEntryDao.getAllCircWindows(circID);
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

    public LiveData<List<TableEntry>> getTablesFromRoom(int roomID) {
        return tableEntryDao.getTablesFromRoom(roomID);
    }

    public LiveData<List<TableEntry>> getAllRoomsTables(List<Integer> roomID) {
        return tableEntryDao.getAllRoomsTables(roomID);
    }

    public LiveData<List<TableEntry>> getTablesFromCirc(int circID) {
        return tableEntryDao.getTablesFromCirc(circID);
    }

    public LiveData<List<TableEntry>> getAllCircTables(List<Integer> circID) {
        return tableEntryDao.getAllCircTables(circID);
    }

    public LiveData<TableEntry> getSpecificTable(int tableID) {
        return tableEntryDao.getSpecificTable(tableID);
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

    public LiveData<List<GateObsEntry>> getAllGates(List<Integer> gateObsID) {
        return gateObsDao.getAllGates(gateObsID);
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

    public LiveData<List<PayPhoneEntry>> getPayPhonesExtAccess(int SchoolEntryID) {
        return payPhoneDao.getPhonesExtAccess(SchoolEntryID);
    }

    public LiveData<List<PayPhoneEntry>> getPhonesSidewalk(int sidewalkID) {
        return payPhoneDao.getPhonesSidewalk(sidewalkID);
    }

    public LiveData<PayPhoneEntry> getPayPhoneEntry(int payPhoneID) {
        return payPhoneDao.getPayPhoneEntry(payPhoneID);
    }

    public LiveData<List<PayPhoneEntry>> getAllPhonesExtAccess(List<Integer> externalAccessID) {
        return payPhoneDao.getAllPhonesExtAccess(externalAccessID);
    }

    public LiveData<List<PayPhoneEntry>> getAllPhonesSidewalk(List<Integer> sidewalkID) {
       return payPhoneDao.getAllPhonesSidewalk(sidewalkID);
    }

    public void deleteAllPayPhonesSidewalk(int sidewalkID) {
        ReportDatabase.dbWriteExecutor.execute(() -> payPhoneDao.deleteAllPayPhonesSidewalk(sidewalkID));
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

    public LiveData<List<CounterEntry>> getAllCounters(List<Integer> roomID) {
        return counterEntryDao.getAllCounters(roomID);
    }

    public LiveData<List<CounterEntry>> getCountersFromCirc(int circID) {
        return counterEntryDao.getCountersFromCirc(circID);
    }

    public LiveData<List<CounterEntry>> getAllCircCounters(List<Integer> circID) {
        return counterEntryDao.getAllCircCounters(circID);
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

    public void insertEquip(EquipmentEntry equip) {
        ReportDatabase.dbWriteExecutor.execute(() -> equipEntryDao.insertEquip(equip));
    }

    public LiveData<List<EquipmentEntry>> getEquipmentFromRoom(int roomID) {
        return equipEntryDao.getEquipmentFromRoom(roomID);
    }

    public LiveData<List<EquipmentEntry>> getAllEquipments(List<Integer> roomID) {
        return equipEntryDao.getAllEquipments(roomID);
    }

    public LiveData<List<EquipmentEntry>> getEquipmentFromCirc(int circID) {
        return equipEntryDao.getEquipmentFromCirc(circID);
    }

    public LiveData<List<EquipmentEntry>> getAllCircEquipments(List<Integer> circID) {
        return equipEntryDao.getAllCircEquipments(circID);
    }

    public LiveData<EquipmentEntry> getSpecificEquipment(int equipID) {
        return equipEntryDao.getSpecificEquipment(equipID);
    }

    public void updateEquipment (EquipmentEntry equip) {
        ReportDatabase.dbWriteExecutor.execute(() -> equipEntryDao.updateEquipment(equip));
    }

    public void deleteEquipment(int equipID) {
        ReportDatabase.dbWriteExecutor.execute(() -> equipEntryDao.deleteEquipment(equipID));
    }

    public void deleteAllEquipsFromRoom(int roomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> equipEntryDao.deleteAllEquipsFromRoom(roomID));
    }

    public void insertRampStairs(RampStairsEntry ramp) {
        ReportDatabase.dbWriteExecutor.execute(() -> rampStairsEntryDao.insertRampStairs(ramp));
    }

    public LiveData<List<RampStairsEntry>> getStairsRampFromExtAccess(int ambientID, int rampOrStairs) {
        return rampStairsEntryDao.getStairsRampFromExtAccess(ambientID, rampOrStairs);
    }

    public LiveData<List<RampStairsEntry>> getStairsRampFromParking(int ambientID, int rampOrStairs) {
        return rampStairsEntryDao.getStairsRampFromParking(ambientID, rampOrStairs);
    }


    public LiveData<List<RampStairsEntry>> getStairsRampFromRoom(int ambientID, int rampOrStairs){
        return rampStairsEntryDao.getStairsRampFromRoom(ambientID, rampOrStairs);
    }

    public LiveData<List<RampStairsEntry>> getStairsRampFromCirculation(int ambientID, int rampOrStairs) {
        return rampStairsEntryDao.getStairsRampFromCirculation(ambientID, rampOrStairs);
    }

    public LiveData<RampStairsEntry> getRampStairsEntry(int rampStairsID) {
        return rampStairsEntryDao.getRampStairsEntry(rampStairsID);
    }

    public LiveData<List<RampStairsEntry>> getAllRampStRoom(List<Integer> roomID) {
        return rampStairsEntryDao.getAllRampStRoom(roomID);
    }

    public LiveData<List<RampStairsEntry>> getAllRampStExt(List<Integer> extID) {
        return rampStairsEntryDao.getAllRampStExt(extID);
    }

    public LiveData<List<RampStairsEntry>> getAllRampStCirc(List<Integer> circID) {
        return rampStairsEntryDao.getAllRampStCirc(circID);
    }

    public LiveData<List<RampStairsEntry>> getAllRampStPark(List<Integer> parkID) {
        return rampStairsEntryDao.getAllRampStPark(parkID);
    }

    public LiveData<RampStairsEntry> getLastRampStairsEntry() {
        return rampStairsEntryDao.getLastRampStairsEntry();
    }

    public void deleteOneRampStairs(int rampStairsID) {
        ReportDatabase.dbWriteExecutor.execute(() -> rampStairsEntryDao.deleteOneRampStairs(rampStairsID));
    }

    public void updateRampStairs(RampStairsEntry rampStairs) {
        ReportDatabase.dbWriteExecutor.execute(() -> rampStairsEntryDao.updateRampStairs(rampStairs));
    }

    public void insertRampsStairsFlight(RampStairsFlightEntry flight) {
        ReportDatabase.dbWriteExecutor.execute(() -> flightsRampStairDao.insertRampsStairsFlight(flight));
    }

    public LiveData<List<RampStairsFlightEntry>> getRampStairsFlights(int rampStairsID) {
        return flightsRampStairDao.getRampStairsFlights(rampStairsID);
    }

    public LiveData<List<RampStairsFlightEntry>> getAllFlights(List<Integer> rampStairsID) {
        return flightsRampStairDao.getAllFlights(rampStairsID);
    }

    public LiveData<RampStairsFlightEntry> getRampStairsFlightEntry(int flightID) {
        return flightsRampStairDao.getRampStairsFlightEntry(flightID);
    }

    public LiveData<RampStairsFlightEntry> getLastRampStairsFlightEntry() {
        return flightsRampStairDao.getLastRampStairsFlightEntry();
    }

    public void deleteOneFlight(int flightID) {
        ReportDatabase.dbWriteExecutor.execute(() -> flightsRampStairDao.deleteOneFlight(flightID));
    }

    public void deleteAllFlightsFromOneRampsStairs(int rampStairsID) {
        ReportDatabase.dbWriteExecutor.execute(() -> flightsRampStairDao.deleteAllFlightsFromOneRampsStairs(rampStairsID));
    }

    public void updateFlightRampStairs(RampStairsFlightEntry flight) {
        ReportDatabase.dbWriteExecutor.execute(() -> flightsRampStairDao.updateFlightRampStairs(flight));
    }

    public void insertRestroomEntry(RestroomEntry restroom) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomEntryDao.insertRestroomEntry(restroom));
    }

    public LiveData<List<RestroomEntry>> getAllRestEntriesInBlock(int schoolID) {
        return restroomEntryDao.getAllRestEntriesInBlock(schoolID);
    }

    public LiveData<List<RestroomEntry>> getAllRestEntries(List<Integer> blockID) {
        return restroomEntryDao.getAllRestEntries(blockID);
    }

    public LiveData<RestroomEntry> getOneRestroomEntry(int restroomID) {
        return restroomEntryDao.getOneRestroomEntry(restroomID);
    }

    public LiveData<RestroomEntry> getRestFirstData(int restID) {
        return restroomEntryDao.getRestFirstData(restID);
    }

    public LiveData<RestroomEntry> getRestColDoorData(int restID) {
        return restroomEntryDao.getRestColDoorData(restID);
    }

    public LiveData<RestroomEntry> getRestUpViewData(int restID) {
        return restroomEntryDao.getRestUpViewData(restID);
    }

    public LiveData<RestroomEntry> getRestToiletData(int restID) {
        return restroomEntryDao.getRestToiletData(restID);
    }

    public LiveData<RestroomEntry> getRestAccessData(int restID) {
        return restroomEntryDao.getRestAccessData(restID);
    }

    public LiveData<RestroomEntry> getRestAccessDataTwo(int restID) {
        return restroomEntryDao.getRestAccessDataTwo(restID);
    }

    public LiveData<RestroomEntry> getRestSinkData(int restID) {
        return restroomEntryDao.getRestSinkData(restID);
    }

    public LiveData<RestroomEntry> getRestCounterSinkData(int restID) {
        return restroomEntryDao.getRestCounterSinkData(restID);
    }

    public LiveData<RestroomEntry> getRestUrinalData(int restID) {
        return restroomEntryDao.getRestUrinalData(restID);
    }

    public LiveData<RestroomEntry> getLastRestroomEntry() {
        return restroomEntryDao.getLastRestroomEntry();
    }

    public void updateRestroomEntry(RestroomEntry restroom) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomEntryDao.updateRestroomEntry(restroom));
    }

    public void updateRestroomData(RestColFirstUpdate... restColFirstUpdates) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomEntryDao.updateRestroomData(restColFirstUpdates));
    }

    public void updateAccessRestData(RestAccessEntranceUpdate... restAccessUpdates) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomEntryDao.updateAccessRestData(restAccessUpdates));
    }

    public void updateRestUpViewData(RestUpViewUpdate... upViewUpdates) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomEntryDao.updateRestUpViewData(upViewUpdates));
    }

    public void updateRestToiletData(RestToiletUpdate... barUpdates) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomEntryDao.updateRestToiletData(barUpdates));
    }

    public void updateRestAccessData(RestAccessUpdate... accUpdates) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomEntryDao.updateRestAccessData(accUpdates));
    }

    public void updateRestAccessDataTwo(RestAccessUpdateTwo... accUpdatesTwo) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomEntryDao.updateRestAccessDataTwo(accUpdatesTwo));
    }

   public void updateRestSinkData(RestSinkUpdate... sinkUpdates) {
       ReportDatabase.dbWriteExecutor.execute(() -> restroomEntryDao.updateRestSinkData(sinkUpdates));
   }

    public void updateRestUrinalData(RestUrinalUpdate... urUpdates) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomEntryDao.updateRestUrinalData(urUpdates));
    }

    public void deleteOneRestroomEntry(int restroomID) {
        ReportDatabase.dbWriteExecutor.execute(() -> restroomEntryDao.deleteOneRestroomEntry(restroomID));
    }

    public void insertSidewalkEntry(SidewalkEntry sidewalkEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> sidewalkEntryDao.insertSidewalkEntry(sidewalkEntry));
    }

    public LiveData<List<SidewalkEntry>> getAllSidewalks(int schoolID) {
        return sidewalkEntryDao.getAllSidewalks(schoolID);
    }

    public LiveData<List<SidewalkEntry>> getAllSidewalks(List<Integer> blockID) {
        return sidewalkEntryDao.getAllSidewalks(blockID);
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

    public void updateSidewalkOne(SidewalkEntryOne... sideOne) {
        ReportDatabase.dbWriteExecutor.execute(() -> sidewalkEntryDao.updateSidewalkOne(sideOne));
    }

    public void updateSidewalkTwo(SidewalkEntryTwo... sideTwo) {
        ReportDatabase.dbWriteExecutor.execute(() -> sidewalkEntryDao.updateSidewalkTwo(sideTwo));
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

    public LiveData<List<SidewalkSlopeEntry>> getSideSlopes(int sidewalkID) {
        return sidewalkSlopeDao.getSideSlopes(sidewalkID);
    }

    public LiveData<List<SidewalkSlopeEntry>> getAllSideSlopes(List<Integer> sidewalkID) {
     return sidewalkSlopeDao.getAllSideSlopes(sidewalkID);
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

    public void insertRampStairsRailing(RampStairsRailingEntry railingEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> railingDao.insertRampStairsRailing(railingEntry));
    }

    public LiveData<List<RampStairsRailingEntry>> getRampStairsRailings(int flightID){
        return railingDao.getRampStairsRailings(flightID);
    }

    public LiveData<RampStairsRailingEntry> getOneRailing(int railingID){
        return railingDao.getOneRailing(railingID);
    }

    public LiveData<List<RampStairsRailingEntry>> getAllRailings(List<Integer> flightID) {
        return railingDao.getAllRailings(flightID);
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

    public LiveData<List<RampStairsHandrailEntry>> getRampStairsHandrails(int flightID){
        return handrailDao.getRampStairsHandrails(flightID);
    }

    public LiveData<RampStairsHandrailEntry> getOneHandrail(int handrailID){
        return handrailDao.getOneHandrail(handrailID);
    }

    public LiveData<List<RampStairsHandrailEntry>> getAllHandrails(List<Integer> flightID) {
        return handrailDao.getAllHandrails(flightID);
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

    public void insertPlayground(PlaygroundEntry play) {
        ReportDatabase.dbWriteExecutor.execute(() -> playgroundEntryDao.insertPlayground(play));
    }

    public LiveData<List<PlaygroundEntry>> getAllPlaygroundsPerBlock(int blockID) {
        return playgroundEntryDao.getAllPlaygroundsPerBlock(blockID);
    }

    public LiveData<List<PlaygroundEntry>> getAllPlaygrounds(List<Integer> blockID) {
        return playgroundEntryDao.getAllPlaygrounds(blockID);
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

    public LiveData<List<BlackboardEntry>> getAllBlackboards(List<Integer> roomID) {
        return blackboardEntryDao.getAllBlackboards(roomID);
    }

    public LiveData<List<BlackboardEntry>> getAllBlackboardsFromCirc(int circID) {
        return blackboardEntryDao.getAllBlackboardsFromCirc(circID);
    }

    public LiveData<List<BlackboardEntry>> getAllCircBlackboards(List<Integer> circID) {
        return blackboardEntryDao.getAllCircBlackboards(circID);
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

    public LiveData<List<DoorLockEntry>> getAllLocksFromDoor(List<Integer> doorID) {
        return doorLockDao.getAllLocksFromDoor(doorID);
    }

    public LiveData<List<DoorLockEntry>> getAllLocksFromGates(List<Integer> extID) {
        return doorLockDao.getAllLocksFromGates(extID);
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

    public void insertRestBox(RestBoxEntry restBox) {
        ReportDatabase.dbWriteExecutor.execute(() -> restBoxDao.insertRestBox(restBox));
    }

    public LiveData<List<RestBoxEntry>> getBoxesInRestroom(int restID) {
        return restBoxDao.getBoxesInRestroom(restID);
    }

    public LiveData<List<RestBoxEntry>> getAllBoxesInRestroom(List<Integer> restID) {
        return restBoxDao.getAllBoxesInRestroom(restID);
    }

    public LiveData<RestBoxEntry> getCommonBoxData(int boxID) {
        return restBoxDao.getCommonBoxData(boxID);
    }

    public LiveData<RestBoxEntry> getBoxUpViewData(int boxID) {
        return restBoxDao.getBoxUpViewData(boxID);
    }

    public LiveData<RestBoxEntry> getBoxToiletData(int boxID) {
        return restBoxDao.getBoxToiletData(boxID);
    }

    public LiveData<RestBoxEntry> getBoxAccessData(int boxID) {
        return restBoxDao.getBoxAccessData(boxID);
    }

    public LiveData<RestBoxEntry> getBoxAccessDataTwo(int boxID) {
        return restBoxDao.getBoxAccessDataTwo(boxID);
    }

    public LiveData<RestBoxEntry> getBoxSinkData(int boxID) {
        return restBoxDao.getBoxSinkData(boxID);
    }

    public LiveData<RestBoxEntry> getOneBoxEntry(int boxID) {
        return restBoxDao.getOneBoxEntry(boxID);
    }

    public LiveData<RestBoxEntry> getLastBoxEntry() {
        return restBoxDao.getLastBoxEntry();
    }

    public void deleteOneBox(int boxID) {
        ReportDatabase.dbWriteExecutor.execute(() -> restBoxDao.deleteOneBox(boxID));
    }

    public void updateBoxFirstData(RestBoxFirstUpdate... comBoxUpdate) {
        ReportDatabase.dbWriteExecutor.execute(() -> restBoxDao.updateBoxFirstData(comBoxUpdate));
    }

    public void updateBoxUpView(RestBoxUpViewUpdate... upViewUpdate) {
        ReportDatabase.dbWriteExecutor.execute(() -> restBoxDao.updateBoxUpView(upViewUpdate));
    }

    public void updateBoxToilet(RestBoxToilUpdate... toilUpdate) {
        ReportDatabase.dbWriteExecutor.execute(() -> restBoxDao.updateBoxToilet(toilUpdate));
    }

    public void updateBoxAccOne(RestBoxAccOneUpdate... accOneUpdate) {
        ReportDatabase.dbWriteExecutor.execute(() -> restBoxDao.updateBoxAccOne(accOneUpdate));
    }

    public void updateBoxAccTwo(RestBoxAccTwoUpdate... accTwoUpdate) {
        ReportDatabase.dbWriteExecutor.execute(() -> restBoxDao.updateBoxAccTwo(accTwoUpdate));
    }

    public void updateBoxSink(RestBoxSinkUpdate... sinkUpdate) {
        ReportDatabase.dbWriteExecutor.execute(() -> restBoxDao.updateBoxSink(sinkUpdate));
    }

    public void insertCirculation(CirculationEntry circ) {
        ReportDatabase.dbWriteExecutor.execute(() -> circulationDao.insertCirculation(circ));
    }

    public LiveData<List<CirculationEntry>> getAllCirculations(int schoolID) {
        return circulationDao.getAllCirculations(schoolID);
    }

    public LiveData<CirculationEntry> getOneCirculation(int circID) {
        return circulationDao.getOneCirculation(circID);
    }

    public LiveData<CirculationEntry> getLastCirculation() {
        return circulationDao.getLastCirculation();
    }

    public void updateCirculation (CirculationEntry circ) {
        ReportDatabase.dbWriteExecutor.execute(() -> circulationDao.updateCirculation(circ));
    }

    public void deleteCirculation(int circID) {
        ReportDatabase.dbWriteExecutor.execute(() -> circulationDao.deleteCirculation(circID));
    }

    public void deleteAllCirculations(int schoolID) {
        ReportDatabase.dbWriteExecutor.execute(() -> circulationDao.deleteAllCirculations(schoolID));
    }

    public void insertSoleStep(SingleStepEntry slop) {
        ReportDatabase.dbWriteExecutor.execute(() -> stepDao.insertSoleStep(slop));
    }

    public LiveData<List<SingleStepEntry>> getAllCircSingleSteps(int circID) {
        return stepDao.getAllCircSingleSteps(circID);
    }

    public LiveData<List<SingleStepEntry>> getAllRoomSingleSteps(int roomID) {
        return stepDao.getAllRoomSingleSteps(roomID);
    }

    public LiveData<SingleStepEntry> getOneSoleStep(int stepID) {
        return stepDao.getOneSoleStep(stepID);
    }

    public void updateSoleStep (SingleStepEntry soleStep) {
        ReportDatabase.dbWriteExecutor.execute(() -> stepDao.updateSoleStep(soleStep));
    }

    public void deleteSoleStep(int stepID) {
        ReportDatabase.dbWriteExecutor.execute(() -> stepDao.deleteSoleStep(stepID));
    }

    public void deleteAllSoleSteps(int circID) {
        ReportDatabase.dbWriteExecutor.execute(() -> stepDao.deleteAllSoleSteps(circID));
    }

    public void insertSlope(SlopeEntry slope) {
        ReportDatabase.dbWriteExecutor.execute(() -> slopeDao.insertSlope(slope));
    }

    public LiveData<List<SlopeEntry>> getAllCircSlopes(int circID) {
        return slopeDao.getAllCircSlopes(circID);
    }

    public LiveData<List<SlopeEntry>> getAllRoomSlopes(int roomID) {
        return slopeDao.getAllRoomSlopes(roomID);
    }

    public LiveData<SlopeEntry> getOneSlope(int slopeID) {
        return slopeDao.getOneSlope(slopeID);
    }

    public void updateSlope (SlopeEntry slope) {
        ReportDatabase.dbWriteExecutor.execute(() -> slopeDao.updateSlope(slope));
    }

    public void deleteSlope(int slopeID) {
        ReportDatabase.dbWriteExecutor.execute(() -> slopeDao.deleteSlope(slopeID));
    }

    public void deleteAllSlopes(int circID) {
        ReportDatabase.dbWriteExecutor.execute(() -> slopeDao.deleteSlope(circID));
    }
}
