package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.FreeSpaceEntry;

import java.util.List;

@Dao
public interface FreeSpaceEntryDao {

    @Insert
    void insertFreeSpace(FreeSpaceEntry freeSpace);

    @Query("SELECT * FROM FreeSpaceEntry WHERE schoolID == :schoolID AND roomID == :roomID")
    LiveData<List<FreeSpaceEntry>> selectFreeSpaceFromRoom(int schoolID, int roomID);

    @Query("SELECT * FROM FreeSpaceEntry WHERE freeSpaceID == :freeSpaceID")
    LiveData<FreeSpaceEntry> selectSpecificFreeSpace(int freeSpaceID);

    @Update
    void updateFreeSpace(FreeSpaceEntry freeSpace);

    @Query("DELETE FROM FreeSpaceEntry WHERE freeSpaceID == :freeSpaceID")
    void deleteFreeSpace(int freeSpaceID);

    @Query("DELETE FROM FreeSpaceEntry WHERE schoolID == :schoolID AND roomID == :roomID")
    void deleteAllFreeSpaceFromRoom(int schoolID, int roomID);
}
