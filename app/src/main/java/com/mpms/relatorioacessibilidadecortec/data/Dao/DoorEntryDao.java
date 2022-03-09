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

    @Query("SELECT * FROM DoorEntry WHERE blockID == :blockID AND roomID == :roomID")
    LiveData<List<DoorEntry>> getDoorsFromRoom(int blockID, int roomID);

    @Query("SELECT * FROM DoorEntry WHERE doorID == :doorID")
    LiveData<DoorEntry> getSpecificDoor(int doorID);

    @Update
    void updateDoor (DoorEntry door);

    @Query("DELETE FROM DoorEntry WHERE doorID == :doorID")
    void deleteDoor(int doorID);

    @Query("DELETE FROM DoorEntry WHERE blockID == :blockID AND roomID == :roomID")
    void deleteAllDoorsFromRoom(int blockID, int roomID);
}
