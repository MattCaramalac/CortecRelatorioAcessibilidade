package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.FlightRampEntry;

import java.util.List;

@Dao
public interface FlightRampDao {

    @Insert
    void insertRampFlight(FlightRampEntry flight);

    @Query("SELECT * FROM FlightRampEntry WHERE rampID == :rampID ORDER BY flightRampID DESC")
    LiveData<List<FlightRampEntry>> getAllRampFlights(int rampID);

    @Query("SELECT * FROM FlightRampEntry WHERE flightRampID == :flightRampID")
    LiveData<FlightRampEntry> getRampFLightEntry(int flightRampID);

    @Query("SELECT * FROM FlightRampEntry WHERE flightRampID == (SELECT MAX(flightRampID) from FlightRampEntry)")
    LiveData<FlightRampEntry> getLastRampFlightEntry();

    @Query("DELETE FROM FlightRampEntry WHERE flightRampID == :flightRampID")
    void deleteOneFlightOfRamp(int flightRampID);

    @Query("DELETE FROM FlightRampEntry WHERE rampID == :rampID")
    void deleteAllFlightsFromRamp(int rampID);

    @Update
    void updateFlightRamp(FlightRampEntry rampEntry);
}
