package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.WaterFountainEntry;

import java.util.List;

@Dao
public interface WaterFountainDao {

    @Insert
    void insertWaterFountain(WaterFountainEntry waterFountain);

    @Query("SELECT * FROM WaterFountainEntry WHERE blockID == :blockID ORDER BY waterFountainID DESC")
    LiveData<List<WaterFountainEntry>> getAllSchoolWaterFountains(int blockID);

    @Query("SELECT * FROM WaterFountainEntry WHERE waterFountainID == :waterFountain")
    LiveData<WaterFountainEntry> getOneWaterFountain(int waterFountain);

    @Update
    void updateWaterFountain(WaterFountainEntry waterFountain);

    @Query("DELETE FROM WaterFountainEntry WHERE waterFountainID == :waterFountainID")
    void deleteOneWaterFountain(int waterFountainID);

    @Query("DELETE FROM WaterFountainEntry WHERE blockID == :blockID")
    void deleteAllFountainsFromSchool(int blockID);


}
