package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.WaterFountainEntry;

import java.util.List;

@Dao
public interface WaterFountainDao {

    @Insert
    void insertWaterFountain(WaterFountainEntry waterFountain);

    @Query("SELECT * FROM WaterFountainEntry WHERE schoolEntryID == :schoolID ORDER BY waterFountainID DESC")
    LiveData<List<WaterFountainEntry>> getAllSchoolWaterFountains(int schoolID);

    @Query("SELECT * FROM WaterFountainEntry WHERE waterFountainID == :waterFountain")
    LiveData<WaterFountainEntry> getOneWaterFountain(int waterFountain);

    @Update
    void updateWaterFountain(WaterFountainEntry waterFountain);

    @Query("DELETE FROM WaterFountainEntry WHERE waterFountainID == :waterFountainID")
    void deleteOneWaterFountain(int waterFountainID);

    @Query("DELETE FROM WaterFountainEntry WHERE schoolEntryID == :schoolEntryID")
    void deleteAllFountainsFromSchool(int schoolEntryID);


}
