package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;

import java.util.List;

@Dao
public interface DoorEntryDao {

    @Insert
    void insertDoor(DoorEntry door);

    @Query("SELECT * FROM DoorEntry WHERE roomID == :roomID ORDER BY doorID DESC")
    LiveData<List<DoorEntry>> getDoorsFromRoom(int roomID);

    @Query("SELECT * FROM DoorEntry WHERE roomID IN (:roomID)")
    LiveData<List<DoorEntry>> getAllDoors(List<Integer> roomID);

    @Query("SELECT * FROM DoorEntry WHERE doorID == :doorID")
    LiveData<DoorEntry> getSpecificDoor(int doorID);

    @Query("SELECT * FROM DoorEntry WHERE restID == :restID")
    LiveData<DoorEntry> getRestDoor(int restID);

    @Query("SELECT * FROM DoorEntry WHERE restID IN (:restID)")
    LiveData<List<DoorEntry>> getAllRestDoors(List<Integer> restID);

    @Query("SELECT * FROM DoorEntry WHERE boxID IN (:boxID)")
    LiveData<List<DoorEntry>> getAllBoxDoors(List<Integer> boxID);

    @Query("SELECT * FROM DoorEntry WHERE boxID == :boxID")
    LiveData<DoorEntry> getAccBoxDoor(int boxID);

    @Query("SELECT * FROM DoorEntry WHERE doorID == (SELECT MAX(doorID) from DoorEntry)")
    LiveData<DoorEntry> getLastDoorEntry();

    @Update
    void updateDoor (DoorEntry door);

    @Query("DELETE FROM DoorEntry WHERE doorID == :doorID")
    void deleteDoor(int doorID);

    @Query("DELETE FROM DoorEntry WHERE restID == :restID")
    void deleteRestDoor(int restID);

    @Query("DELETE FROM DoorEntry WHERE roomID == :roomID")
    void deleteAllDoorsFromRoom(int roomID);
}
