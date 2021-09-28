package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.RampStairsRailingEntry;

import java.util.List;

@Dao
public interface RampStairsRailingDao {

    @Insert
    void insertRampStairsRailing(RampStairsRailingEntry railingEntry);

    @Query("SELECT * FROM RampStairsRailingEntry WHERE flightID == :flightID ORDER BY railingID DESC")
    LiveData<List<RampStairsRailingEntry>> getAllRampStairsRailings(int flightID);

    @Query("SELECT * FROM RampStairsRailingEntry WHERE railingID == :railingID")
    LiveData<RampStairsRailingEntry> getRampStairsRailing(int railingID);

    @Query("SELECT COUNT(flightID) FROM RampStairsRailingEntry WHERE flightID == :flightID")
    LiveData<Integer> countRampStairsRailings(int flightID);

    @Query("DELETE FROM RampStairsRailingEntry WHERE railingID == :railingID")
    void deleteOneRampStairsRailing(int railingID);

    @Query("DELETE FROM RampStairsRailingEntry WHERE flightID == :flightID")
    void deleteAllRailingsFromOneRampStairs(int flightID);

    @Update
    void updateRampStairsRailing(RampStairsRailingEntry railingEntry);
}
