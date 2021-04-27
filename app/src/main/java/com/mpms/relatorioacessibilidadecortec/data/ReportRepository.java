package com.mpms.relatorioacessibilidadecortec.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.mpms.relatorioacessibilidadecortec.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.data.ReportDatabase;

import java.util.List;

public class ReportRepository {
    private SchoolEntryDao schoolEntryDao;
    private LiveData<List<SchoolEntry>> allEntries;
    private LiveData<SchoolEntry> getEntry;

    public ReportRepository(Application application) {
        ReportDatabase db = ReportDatabase.getDatabase(application);
        schoolEntryDao = db.schoolEntryDao();

        allEntries = schoolEntryDao.getAllEntries();
    }

    public LiveData<List<SchoolEntry>> getAllEntries() {
        return schoolEntryDao.getAllEntries();
    }

    public LiveData<SchoolEntry> getEntry(int cadID) { return schoolEntryDao.getEntry(cadID); }

    public LiveData<SchoolEntry> getLastEntry() { return schoolEntryDao.getLastEntry(); }

    public void insert(SchoolEntry schoolEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> schoolEntryDao.insertEntry(schoolEntry));
    }

    public void update(SchoolEntry schoolEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> schoolEntryDao.update(schoolEntry));
    }

    public void deleteOne(SchoolEntry schoolEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> schoolEntryDao.deleteOne(schoolEntry));
    }

    public void deleteAll() {
        ReportDatabase.dbWriteExecutor.execute(() -> schoolEntryDao.deleteAll());
    }
}
