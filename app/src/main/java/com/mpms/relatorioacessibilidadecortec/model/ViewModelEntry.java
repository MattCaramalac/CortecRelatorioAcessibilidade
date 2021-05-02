package com.mpms.relatorioacessibilidadecortec.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mpms.relatorioacessibilidadecortec.data.ReportRepository;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.entities.WaterFountainEntry;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ViewModelEntry extends AndroidViewModel {

    public static ReportRepository repository;
    public final LiveData<List<SchoolEntry>> allEntries;

    public ViewModelEntry(@NonNull Application application) {
        super(application);
        repository = new ReportRepository(application);
        allEntries = repository.getAllSchoolEntries();
    }

    //Quando vou retornar o valor, preciso colocar a variável com o valor, NÃO o método.
    public LiveData<List<SchoolEntry>> getAllEntries() { return allEntries; }
    public LiveData<SchoolEntry> getEntry(int cadID) { return repository.getSchoolEntry(cadID); }
    public LiveData<SchoolEntry> getLastEntry() {return repository.getLastSchoolEntry(); }
    public static void insert(SchoolEntry schoolEntry) { repository.insertSchoolEntry(schoolEntry);  }
    public static void update(SchoolEntry schoolEntry) { repository.updateSchoolEntry(schoolEntry); }
    public static void deleteOne(SchoolEntry schoolEntry) { repository.deleteOneSchoolEntry(schoolEntry); }
    public static void deleteAll() { repository.deleteAllEntries(); }

    public LiveData<List<WaterFountainEntry>> getAllFountainsInSchool(int schoolEntryID) {
        return repository.getAllFountainsInSchool(schoolEntryID); }
    public LiveData<WaterFountainEntry> getOneWaterFountain(int waterFountainID) {
        return repository.getOneWaterFountain(waterFountainID); }
    public static void insertWaterFountain(WaterFountainEntry waterFountainEntry) { repository.insertWaterFountain(waterFountainEntry); }
    public static void updateWaterFountain(WaterFountainEntry waterFountainEntry) { repository.updateWaterFountain(waterFountainEntry); }
    public static void deleteOneWaterFountain(int waterFountainID) { repository.deleteOneWaterFountain(waterFountainID); }
    public static void deleteAllFountainsFromSchool(int schoolID) { repository.deleteAllFountainsFromSchool(schoolID); }
}
