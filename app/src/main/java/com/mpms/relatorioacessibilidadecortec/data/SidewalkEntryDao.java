package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.SidewalkEntry;

import java.util.List;

@Dao
public interface SidewalkEntryDao {

    @Insert
    void insertSidewalkEntry(SidewalkEntry SidewalkEntry);

    @Query("SELECT * FROM SidewalkEntry ORDER BY schoolEntryID DESC")
    LiveData<List<SidewalkEntry>> getAllSidewalks();

    @Query("SELECT * FROM SidewalkEntry WHERE sidewalkID == :sidewalkID")
    LiveData<SidewalkEntry> getSidewalkEntry(int sidewalkID);

    @Query("SELECT * FROM SidewalkEntry WHERE sidewalkID == (SELECT MAX(sidewalkID) from SidewalkEntry)")
    LiveData<SidewalkEntry> getLastSidewalkEntry();

    @Update
    void updateSidewalk(SidewalkEntry SidewalkEntry);

    @Query("DELETE FROM SidewalkEntry WHERE sidewalkID == :sidewalkID")
    void deleteSidewalk(int sidewalkID);

    @Query("DELETE FROM SidewalkEntry WHERE schoolEntryID == :schoolID")
    void deleteAllSidewalksFromSchool(int schoolID);
}
