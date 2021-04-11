package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mpms.relatorioacessibilidadecortec.entities.SchoolEntry;

import java.util.List;

@Dao
public interface SchoolEntryDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertEntry(SchoolEntry schoolEntry);

    @Query("SELECT * FROM SchoolEntry ORDER BY cadID DESC")
    LiveData<List<SchoolEntry>> getAllEntries();
}
