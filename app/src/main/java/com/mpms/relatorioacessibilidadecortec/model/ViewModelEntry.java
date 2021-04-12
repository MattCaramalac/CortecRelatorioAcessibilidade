package com.mpms.relatorioacessibilidadecortec.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mpms.relatorioacessibilidadecortec.data.ReportRepository;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolEntry;

import java.util.List;

public class ViewModelEntry extends AndroidViewModel {

    public static ReportRepository repository;
    public final LiveData<List<SchoolEntry>> allEntries;

    public ViewModelEntry(@NonNull Application application) {
        super(application);
        repository = new ReportRepository(application);
        allEntries = repository.getAllEntries();
    }

    //Quando vou retornar o valor, preciso colocar a variável com o valor, NÃO o método.
    public LiveData<List<SchoolEntry>> getAllEntries() { return allEntries; }
    public LiveData<SchoolEntry> getEntry(int cadID) { return repository.getEntry(cadID); }

    public static void insert(SchoolEntry schoolEntry) { repository.insert(schoolEntry); }
    public static void deleteOne(SchoolEntry schoolEntry) { repository.deleteOne(schoolEntry); }
    public static void deleteAll() { repository.deleteAll(); }
}
