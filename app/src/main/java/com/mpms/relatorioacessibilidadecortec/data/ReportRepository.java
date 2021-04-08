package com.mpms.relatorioacessibilidadecortec.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.mpms.relatorioacessibilidadecortec.entities.SchoolEntry;

import java.util.List;

public class ReportRepository {
    private SchoolEntryDao schoolEntryDao;
    private LiveData<List<SchoolEntry>> allEntries;

    public ReportRepository(Application application) {
        ReportDatabase db = ReportDatabase.getDatabase(application);
        schoolEntryDao = db.schoolEntryDao();

        allEntries = schoolEntryDao.getAllEntries();
    }

    public LiveData<List<SchoolEntry>> getAllEntries() {
        return allEntries;
    }

    public void insert(SchoolEntry schoolEntry) {
        ReportDatabase.dbWriteExecutor.execute(() -> schoolEntryDao.insertEntry(schoolEntry));
    }
}
