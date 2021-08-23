package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.RampStairsEntry;

import java.util.List;

@Dao
public interface RampStairsEntryDao {

    @Insert
    void insertRampStairs(RampStairsEntry ramp);

    @Query("SELECT * FROM RampStairsEntry WHERE schoolID == :schoolID ORDER BY rampStairsID DESC")
    LiveData<List<RampStairsEntry>> getAllRampStairsFromSchool(int schoolID);

    @Query("SELECT * FROM RampStairsEntry WHERE rampStairsID == :rampStairsID")
    LiveData<RampStairsEntry> getRampStairsEntry(int rampStairsID);

    @Query("SELECT * FROM RampStairsEntry WHERE rampStairsID == (SELECT MAX(rampStairsID) from RampStairsEntry)")
    LiveData<RampStairsEntry> getLastRampStairsEntry();

    @Query("DELETE FROM RampStairsEntry WHERE rampStairsID == :rampStairsID")
    void deleteOneRampStairs(int rampStairsID);

    @Query("DELETE FROM RampStairsEntry WHERE schoolID == :schoolID")
    void deleteAllRampStairsFromSchool(int schoolID);

    @Update
    void updateRampStairs(RampStairsEntry ramp);
}
