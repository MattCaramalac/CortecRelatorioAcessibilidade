package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.StairsStepEntry;

import java.util.List;

@Dao
public interface StairsStepDao {

    @Insert
    void insertStairsStepEntry(StairsStepEntry stepEntry);

    @Query("SELECT * FROM StairsStepEntry WHERE flightID == :flightID")
    LiveData<List<StairsStepEntry>> getAllStairsStepPerFlight(int flightID);

    @Query("SELECT * FROM StairsStepEntry WHERE stairsStepID == :stairsStepID")
    LiveData<StairsStepEntry> getOneStairsStepEntry(int stairsStepID);

    @Update
    void updateStairsStepEntry(StairsStepEntry stepEntry);

    @Query("DELETE FROM StairsStepEntry WHERE stairsStepID == :stairsStepID")
    void deleteOneStairsStepEntry(int stairsStepID);

    @Query("DELETE FROM StairsStepEntry WHERE flightID == :flightID")
    void deleteAllStairsStepPerFlight(int flightID);
}
