package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.SidewalkSlopeEntry;

import java.util.List;

@Dao
public interface SidewalkSlopeDao {

    @Insert
    void insertSidewalkSlopeEntry(SidewalkSlopeEntry sidewalkSlopeEntry);

    @Query("SELECT * FROM SidewalkSlopeEntry ORDER BY sidewalkSlopeID DESC")
    LiveData<List<SidewalkSlopeEntry>> getAllSidewalkSlopes();

    @Query("SELECT * FROM SidewalkSlopeEntry WHERE sidewalkSlopeID == :sidewalkSlopeID")
    LiveData<SidewalkSlopeEntry> getSidewalkSlopeEntry(int sidewalkSlopeID);

    @Update
    void updateSidewalkSlope(SidewalkSlopeEntry sidewalkSlopeEntry);

    @Query("DELETE FROM SidewalkSlopeEntry WHERE sidewalkSlopeID == :sidewalkSlopeID")
    void deleteSidewalkSlope(int sidewalkSlopeID);

    @Query("DELETE FROM SidewalkSlopeEntry WHERE sidewalkID == :sidewalkID")
    void deleteAllSidewalkSlopesFromSidewalk(int sidewalkID);
}
