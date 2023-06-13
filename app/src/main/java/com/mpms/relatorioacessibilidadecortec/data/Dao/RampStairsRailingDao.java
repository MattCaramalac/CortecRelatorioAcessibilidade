package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.common.util.concurrent.ListenableFuture;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsRailingEntry;

import java.util.List;

@Dao
public interface RampStairsRailingDao {

    @Insert
    void insertRampStairsRailing(RampStairsRailingEntry railingEntry);

    @Query("SELECT * FROM RampStairsRailingEntry WHERE flightID == :flightID ORDER BY railingID DESC")
    LiveData<List<RampStairsRailingEntry>> getRampStairsRailings(int flightID);

    @Query("SELECT * FROM RampStairsRailingEntry WHERE railingID == :railingID")
    LiveData<RampStairsRailingEntry> getOneRailing(int railingID);

    @Query("SELECT * FROM RampStairsRailingEntry WHERE flightID IN (:flightID)")
    LiveData<List<RampStairsRailingEntry>> getAllRailings(List<Integer> flightID);

    @Query("SELECT COUNT(flightID) FROM RampStairsRailingEntry WHERE flightID == :flightID")
    LiveData<Integer> countRampStairsRailings(int flightID);

    @Query("DELETE FROM RampStairsRailingEntry WHERE railingID == :railingID")
    void deleteOneRampStairsRailing(int railingID);

    @Query("DELETE FROM RampStairsRailingEntry WHERE flightID == :flightID")
    void deleteAllRailingsFromOneRampStairs(int flightID);

    @Update
    void updateRampStairsRailing(RampStairsRailingEntry railingEntry);

    //    Listenable
    @Query("SELECT * FROM RampStairsRailingEntry WHERE flightID IN (:flightID)")
    ListenableFuture<List<RampStairsRailingEntry>> getListAllRailings(List<Integer> flightID);
}
