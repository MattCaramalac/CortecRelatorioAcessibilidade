package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.RampStairsHandrailEntry;

import java.util.List;

@Dao
public interface RampStairsHandrailDao {

    @Insert
    void insertRampStairsHandrail(RampStairsHandrailEntry handrailEntry);

    @Query("SELECT * FROM RampStairsHandrailEntry WHERE flightID == :flightID ORDER BY handrailID DESC")
    LiveData<List<RampStairsHandrailEntry>> getAllRampStairsHandrails(int flightID);

    @Query("SELECT * FROM RampStairsHandrailEntry WHERE handrailID == :handrailID")
    LiveData<RampStairsHandrailEntry> getRampStairsHandrail(int handrailID);

    @Query("SELECT COUNT(flightID) FROM RampStairsHandrailEntry WHERE flightID == :flightID")
    LiveData<Integer> countRampStairsHandrails(int flightID);

    @Query("DELETE FROM RampStairsHandrailEntry WHERE handrailID == :handrailID")
    void deleteOneRampStairsHandrail(int handrailID);

    @Query("DELETE FROM RampStairsHandrailEntry WHERE flightID == :flightID")
    void deleteAllHandrailsFromOneRampStairs(int flightID);

    @Update
    void updateRampStairsHandrail(RampStairsHandrailEntry handrailEntry);
}
