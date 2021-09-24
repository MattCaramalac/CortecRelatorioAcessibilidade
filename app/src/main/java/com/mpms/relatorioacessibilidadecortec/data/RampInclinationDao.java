package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.RampInclinationEntry;

import java.util.List;

@Dao
public interface RampInclinationDao {

    @Insert
    void insertRampInclinationEntry(RampInclinationEntry rampEntry);

    @Query("SELECT * FROM RampInclinationEntry WHERE flightID == :flightID")
    LiveData<List<RampInclinationEntry>> getAllRampInclinationsPerFlight(int flightID);

    @Query("SELECT * FROM RampInclinationEntry WHERE rampInclinationID == :rampInclinationID")
    LiveData<RampInclinationEntry> getOneRampInclinationEntry(int rampInclinationID);

    @Update
    void updateRampInclinationEntry(RampInclinationEntry RampInclinationEntry);

    @Query("DELETE FROM RampInclinationEntry WHERE rampInclinationID == :rampInclinationID")
    void deleteOneRampInclinationEntry(int rampInclinationID);

    @Query("DELETE FROM RampInclinationEntry WHERE flightID == :flightID")
    void deleteAllRampInclinationsPerFlight(int flightID);
}
