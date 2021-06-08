package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.DoorEntry;

import java.util.List;

@Dao
public interface DoorEntryDao {

    @Insert
    void insertDoor(DoorEntry door);

    @Query("SELECT * FROM DoorEntry WHERE schoolID == :schoolID AND roomID == :roomID")
    LiveData<List<DoorEntry>> selectDoorsFromRoom(int schoolID, int roomID);

    @Query("SELECT * FROM DoorEntry WHERE doorID == :doorID")
    LiveData<DoorEntry> selectSpecificDoor(int doorID);

    @Update
    void updateDoor (DoorEntry door);

    @Query("DELETE FROM DoorEntry WHERE doorID == :doorID")
    void deleteDoor(int doorID);

    @Query("DELETE FROM DoorEntry WHERE schoolID == :schoolID AND roomID == :roomID")
    void deleteAllDoorsFromRoom(int schoolID, int roomID);
}
