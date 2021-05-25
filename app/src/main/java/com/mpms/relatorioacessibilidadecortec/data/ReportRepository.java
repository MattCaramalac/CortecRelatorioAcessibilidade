package com.mpms.relatorioacessibilidadecortec.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.mpms.relatorioacessibilidadecortec.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.entities.OtherSpaces;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotElderlyEntry;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotPDMREntry;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.entities.WaterFountainEntry;

import java.util.List;

public class ReportRepository {

    private ReportDatabase db;

    private SchoolEntryDao schoolEntryDao;
    private LiveData<List<SchoolEntry>> allSchoolEntries;
    private LiveData<SchoolEntry> getEntry;

    private WaterFountainDao waterFountainDao;
    private LiveData<List<WaterFountainEntry>> allFountainsInSchool;
    private LiveData<WaterFountainEntry> waterFountain;

    private ExternalAccessDao externalAccessDao;

    private OtherSpacesDao otherSpacesDao;

    private ParkingLotEntryDao parkingLotEntryDao;

    private ParkingLotElderlyDao parkingLotElderlyDao;

    private ParkingLotPdmrDao parkingLotPdmrDao;

    public ReportRepository(Application application) {
        db = ReportDatabase.getDatabase(application);
        schoolEntryDao = db.schoolEntryDao();
        waterFountainDao = db.waterFountainDao();
        externalAccessDao = db.externalAccessDao();
        otherSpacesDao = db.otherSpacesDao();
        parkingLotEntryDao = db.parkingLotEntryDao();
        parkingLotPdmrDao = db.parkingLotPdmrDao();
        parkingLotElderlyDao = db.parkingLotElderlyDao();

        allSchoolEntries = schoolEntryDao.getAllEntries();
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
        {
            ReportDatabase.dbWriteExecutor.execute(() -> parkingLotPdmrDao.deleteOnePdmrParkingLot(parkingLotID));
        }
    }

    public void deleteAllEntries() {
        ReportDatabase.dbWriteExecutor.execute(() -> schoolEntryDao.deleteAll());
    }
}
