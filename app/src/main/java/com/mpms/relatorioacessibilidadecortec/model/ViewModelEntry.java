package com.mpms.relatorioacessibilidadecortec.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mpms.relatorioacessibilidadecortec.data.ReportDatabase;
import com.mpms.relatorioacessibilidadecortec.data.ReportRepository;
import com.mpms.relatorioacessibilidadecortec.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.entities.OtherSpaces;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.entities.WaterFountainEntry;

import java.util.List;

public class ViewModelEntry extends AndroidViewModel {

    public static ReportRepository repository;
    public final LiveData<List<SchoolEntry>> allEntries;

    public ViewModelEntry(@NonNull Application application) {
        super(application);
        repository = new ReportRepository(application);
        allEntries = repository.getAllSchoolEntries();
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

    public LiveData<List<WaterFountainEntry>> getAllFountainsInSchool(int schoolEntryID) {
        return repository.getAllFountainsInSchool(schoolEntryID);
    }

    public LiveData<WaterFountainEntry> getOneWaterFountain(int waterFountainID) {
        return repository.getOneWaterFountain(waterFountainID);
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


    public LiveData<List<ExternalAccess>> getAllExternalAccessesInSchool(int schoolEntryID) {
        return repository.getAllExternalAccessesInSchool(schoolEntryID);
    }

    public LiveData<ExternalAccess> getOneExternalAccess(int externalAccessID) {
        return repository.getOneExternalAccess(externalAccessID);
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

    public LiveData<List<OtherSpaces>> selectAllSpaces(int schoolEntryID) {
        return repository.selectAllSpaces(schoolEntryID);
    }

    public LiveData<OtherSpaces> selectOneSpace(int otherID) {
        return repository.selectOneSpace(otherID);
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

    public LiveData<List<ParkingLotEntry>> selectEveryParkingLot(int schoolEntryID) {
        return repository.selectEveryParkingLot(schoolEntryID);
    }

    public LiveData<ParkingLotEntry> selectOneParkingLot(int parkingLotID) {
        return repository.selectOneParkingLot(parkingLotID);
    }

    public LiveData<ParkingLotEntry> selectLastInsertedParkingLot() {
        return repository.selectLastInsertedParkingLot();
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


}
