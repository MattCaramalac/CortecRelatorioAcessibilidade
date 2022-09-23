package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsHandrailEntry;

import java.util.List;

@Dao
public interface RampStairsHandrailDao {

    @Insert
    void insertRampStairsHandrail(RampStairsHandrailEntry handrailEntry);

    @Query("SELECT * FROM RampStairsHandrailEntry WHERE flightID == :flightID ORDER BY handrailID DESC")
    LiveData<List<RampStairsHandrailEntry>> getRampStairsHandrails(int flightID);

    @Query("SELECT * FROM RampStairsHandrailEntry WHERE handrailID == :handrailID")
    LiveData<RampStairsHandrailEntry> getOneHandrail(int handrailID);

    @Query("SELECT * FROM RampStairsHandrailEntry WHERE flightID IN (:flightID)")
    LiveData<List<RampStairsHandrailEntry>> getAllHandrails(List<Integer> flightID);

    @Query("SELECT COUNT(flightID) FROM RampStairsHandrailEntry WHERE flightID == :flightID")
    LiveData<Integer> countRampStairsHandrails(int flightID);

    @Query("DELETE FROM RampStairsHandrailEntry WHERE handrailID == :handrailID")
    void deleteOneRampStairsHandrail(int handrailID);

    @Query("DELETE FROM RampStairsHandrailEntry WHERE flightID == :flightID")
    void deleteAllHandrailsFromOneRampStairs(int flightID);

    @Update
    void updateRampStairsHandrail(RampStairsHandrailEntry handrailEntry);
}
