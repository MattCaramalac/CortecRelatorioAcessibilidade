package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.FlightStairsEntry;
import com.mpms.relatorioacessibilidadecortec.entities.StairsEntry;

import java.util.List;

@Dao
public interface FligthStairsDao {

    @Insert
    void insertStairsFlight(FlightStairsEntry flight);

    @Query("SELECT * FROM FlightStairsEntry ORDER BY flightID DESC")
    LiveData<List<FlightStairsEntry>> getAllStairsFlights();

    @Query("SELECT * FROM FlightStairsEntry WHERE flightID == :flightID")
    LiveData<FlightStairsEntry> getStairsFLightEntry(int flightID);

    @Query("SELECT * FROM FlightStairsEntry WHERE flightID == (SELECT MAX(flightID) from FlightStairsEntry)")
    LiveData<FlightStairsEntry> getLastStairsFlightEntry();

    @Query("DELETE FROM FlightStairsEntry WHERE flightID == :flightID")
    void deleteOneFlightOFStairs(int flightID);

    @Query("DELETE FROM FlightStairsEntry WHERE stairsID == :stairsID")
    void deleteAllFlightsFromStairs(int stairsID);

    @Update
    void updateFlightStairs(StairsEntry stairs);
}
