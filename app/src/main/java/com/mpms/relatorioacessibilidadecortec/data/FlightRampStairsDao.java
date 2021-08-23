package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.FlightsRampStairsEntry;

import java.util.List;

@Dao
public interface FlightRampStairsDao {

    @Insert
    void insertRampsStairsFlight(FlightsRampStairsEntry flight);

    @Query("SELECT * FROM FlightsRampStairsEntry WHERE rampStairsID == :rampStairsID ORDER BY flightID DESC")
    LiveData<List<FlightsRampStairsEntry>> getAllRampStairsFlights(int rampStairsID);

    @Query("SELECT * FROM FlightsRampStairsEntry WHERE flightID == :flightID")
    LiveData<FlightsRampStairsEntry> getRampStairsFlightEntry(int flightID);

    @Query("SELECT * FROM FlightsRampStairsEntry WHERE flightID == (SELECT MAX(flightID) from FlightsRampStairsEntry)")
    LiveData<FlightsRampStairsEntry> getLastRampStairsFlightEntry();

    @Query("DELETE FROM FlightsRampStairsEntry WHERE flightID == :flightID")
    void deleteOneFlight(int flightID);

    @Query("DELETE FROM FlightsRampStairsEntry WHERE rampStairsID == :rampStairsID")
    void deleteAllFlightsFromOneRampsStairs(int rampStairsID);

    @Update
    void updateFlightRampStairs(FlightsRampStairsEntry flight);
}
