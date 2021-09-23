package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.StairsMirrorEntry;

import java.util.List;

@Dao
public interface StairsMirrorDao {

    @Insert
    void insertStairsMirrorEntry(StairsMirrorEntry mirrorEntry);

    @Query("SELECT * FROM StairsMirrorEntry WHERE flightID == :flightID")
    LiveData<List<StairsMirrorEntry>> getAllStairsMirrorPerFlight(int flightID);

    @Query("SELECT * FROM StairsMirrorEntry WHERE stairsMirrorID == :stairsMirrorID")
    LiveData<StairsMirrorEntry> getOneStairsMirrorEntry(int stairsMirrorID);

    @Update
    void updateStairsMirrorEntry(StairsMirrorEntry StairsMirrorEntry);

    @Query("DELETE FROM StairsMirrorEntry WHERE stairsMirrorID == :stairsMirrorID")
    void deleteOneStairsMirrorEntry(int stairsMirrorID);

    @Query("DELETE FROM StairsMirrorEntry WHERE flightID == :flightID")
    void deleteAllStairsMirrorPerFlight(int flightID);
}
