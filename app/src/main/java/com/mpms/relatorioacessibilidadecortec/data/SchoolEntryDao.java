package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.SchoolEntry;

import java.util.List;

@Dao
public interface SchoolEntryDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertEntry(SchoolEntry schoolEntry);

    @Query("SELECT * FROM SchoolEntry ORDER BY cadID DESC")
    LiveData<List<SchoolEntry>> getAllEntries();

    @Query("SELECT * FROM SchoolEntry WHERE SchoolEntry.cadID == :cadID")
    LiveData<SchoolEntry> getEntry(int cadID);

    @Update
    void update(SchoolEntry schoolEntry);

    @Delete
    void deleteOne(SchoolEntry schoolEntry);

    @Query("DELETE FROM SchoolEntry")
    void deleteAll();
}
