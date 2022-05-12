package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsFlightEntry;

import java.util.List;

@Dao
public interface FlightRampStairsDao {

    @Insert
    void insertRampsStairsFlight(RampStairsFlightEntry flight);

    @Query("SELECT * FROM RampStairsFlightEntry WHERE rampStairsID == :rampStairsID ORDER BY flightID DESC")
    LiveData<List<RampStairsFlightEntry>> getAllRampStairsFlights(int rampStairsID);

    @Query("SELECT * FROM RampStairsFlightEntry WHERE flightID == :flightID")
    LiveData<RampStairsFlightEntry> getRampStairsFlightEntry(int flightID);

    @Query("SELECT * FROM RampStairsFlightEntry WHERE flightID == (SELECT MAX(flightID) from RampStairsFlightEntry)")
    LiveData<RampStairsFlightEntry> getLastRampStairsFlightEntry();

    @Query("DELETE FROM RampStairsFlightEntry WHERE flightID == :flightID")
    void deleteOneFlight(int flightID);

    @Query("DELETE FROM RampStairsFlightEntry WHERE rampStairsID == :rampStairsID")
    void deleteAllFlightsFromOneRampsStairs(int rampStairsID);

    @Update
    void updateFlightRampStairs(RampStairsFlightEntry flight);
}
