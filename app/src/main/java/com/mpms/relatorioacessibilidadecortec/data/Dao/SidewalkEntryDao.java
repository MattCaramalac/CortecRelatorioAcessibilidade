package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntryOne;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntryTwo;

import java.util.List;

@Dao
public interface SidewalkEntryDao {

    @Insert
    void insertSidewalkEntry(SidewalkEntry SidewalkEntry);

    @Query("SELECT * FROM SidewalkEntry WHERE blockID == :blockID ORDER BY sidewalkID DESC")
    LiveData<List<SidewalkEntry>> getAllSidewalks(int blockID);

    @Query("SELECT * FROM SidewalkEntry WHERE blockID IN (:blockID)")
    LiveData<List<SidewalkEntry>> getAllSidewalks(List<Integer> blockID);

    @Query("SELECT * FROM SidewalkEntry WHERE sidewalkID == :sidewalkID")
    LiveData<SidewalkEntry> getSidewalkEntry(int sidewalkID);

    @Query("SELECT * FROM SidewalkEntry WHERE sidewalkID == (SELECT MAX(sidewalkID) from SidewalkEntry)")
    LiveData<SidewalkEntry> getLastSidewalkEntry();

    @Update
    void updateSidewalk(SidewalkEntry SidewalkEntry);

    @Update(entity = SidewalkEntry.class)
    void updateSidewalkOne(SidewalkEntryOne... sideOne);

    @Update(entity = SidewalkEntry.class)
    void updateSidewalkTwo(SidewalkEntryTwo... sideTwo);

    @Query("DELETE FROM SidewalkEntry WHERE sidewalkID == :sidewalkID")
    void deleteSidewalk(int sidewalkID);

    @Query("DELETE FROM SidewalkEntry WHERE blockID == :blockID")
    void deleteAllSidewalksFromSchool(int blockID);
}
