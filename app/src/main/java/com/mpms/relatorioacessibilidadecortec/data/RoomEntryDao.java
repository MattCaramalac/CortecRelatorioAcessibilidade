package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.RoomEntry;

import java.util.List;

@Dao
public interface RoomEntryDao {

    @Insert
    void insertRoomEntry(RoomEntry roomEntry);

    @Query("SELECT * FROM RoomEntry WHERE blockID == :blockID AND roomType = :roomType ORDER BY roomID DESC")
    LiveData<List<RoomEntry>> getAllRoomsInSchool(int blockID, int roomType);

    @Query("SELECT * FROM RoomEntry WHERE blockID == :blockID ORDER BY roomID DESC")
    LiveData<List<RoomEntry>> getAllRoomsInSchool(int blockID);

    @Query("SELECT * FROM RoomEntry WHERE roomID == :roomID")
    LiveData<RoomEntry> getRoomEntry(int roomID);

    @Query("SELECT * FROM RoomEntry WHERE roomID == (SELECT MAX(roomID) from RoomEntry)")
    LiveData<RoomEntry> getLastRoomEntry();

    @Update
    void updateRoom(RoomEntry roomEntry);

    @Delete
    void deleteRoom(RoomEntry roomEntry);

}
