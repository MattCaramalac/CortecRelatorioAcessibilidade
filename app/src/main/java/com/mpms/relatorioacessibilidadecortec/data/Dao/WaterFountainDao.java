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

    @Query("SELECT * FROM WaterFountainEntry WHERE blockID == :blockID AND roomID IS NULL ORDER BY waterFountainID DESC")
    LiveData<List<WaterFountainEntry>> getAllBlockWaterFountains(int blockID);

    @Query("SELECT * FROM WaterFountainEntry WHERE blockID IN (:blockID) AND roomID IS NULL")
    LiveData<List<WaterFountainEntry>> getAllWaterFountains(List<Integer> blockID);

    @Query("SELECT * FROM WaterFountainEntry WHERE roomID == :roomID ORDER BY waterFountainID DESC")
    LiveData<List<WaterFountainEntry>> getRoomWaterFountains(int roomID);

    @Query("SELECT * FROM WaterFountainEntry WHERE roomID IN (:roomID)")
    LiveData<List<WaterFountainEntry>> getAllRoomWaterFountains(List<Integer> roomID);

    @Query("SELECT * FROM WaterFountainEntry WHERE waterFountainID == :waterFountain")
    LiveData<WaterFountainEntry> getOneWaterFountain(int waterFountain);

    @Query("SELECT * FROM WaterFountainEntry WHERE circID == :circID ORDER BY waterFountainID DESC")
    LiveData<List<WaterFountainEntry>> getCircWaterFountains(int circID);

    @Query("SELECT * FROM WaterFountainEntry WHERE circID IN (:circID) AND roomID IS NULL")
    LiveData<List<WaterFountainEntry>> getAllCircWaterFountains(List<Integer> circID);

    @Update
    void updateWaterFountain(WaterFountainEntry waterFountain);

    @Query("DELETE FROM WaterFountainEntry WHERE waterFountainID == :waterFountainID")
    void deleteOneWaterFountain(int waterFountainID);

    @Query("DELETE FROM WaterFountainEntry WHERE blockID == :blockID")
    void deleteAllFountainsFromSchool(int blockID);


}
