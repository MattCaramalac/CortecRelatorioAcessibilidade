package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.RampEntry;

import java.util.List;

@Dao
public interface RampEntryDao {

    @Insert
    void insertRamp(RampEntry ramp);

    @Query("SELECT * FROM RampEntry WHERE schoolID == :schoolID ORDER BY rampID DESC")
    LiveData<List<RampEntry>> getAllRampsFromSchool(int schoolID);

    @Query("SELECT * FROM RampEntry WHERE rampID == :rampID")
    LiveData<RampEntry> getRampEntry(int rampID);

    @Query("SELECT * FROM RampEntry WHERE rampID == (SELECT MAX(rampID) from RampEntry)")
    LiveData<RampEntry> getLastRampEntry();

    @Query("DELETE FROM RampEntry WHERE rampID == :rampID")
    void deleteOneRamp(int rampID);

    @Query("DELETE FROM RampEntry WHERE schoolID == :schoolID")
    void deleteAllRampsFromSchool(int schoolID);

    @Update
    void updateRamp(RampEntry ramp);
}
