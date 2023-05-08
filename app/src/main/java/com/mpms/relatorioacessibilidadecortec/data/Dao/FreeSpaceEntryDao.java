package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.FreeSpaceEntry;

import java.util.List;

@Dao
public interface FreeSpaceEntryDao {

    @Insert
    void insertFreeSpace(FreeSpaceEntry freeSpace);

    @Query("SELECT * FROM FreeSpaceEntry WHERE roomID == :roomID ORDER BY frSpaceID DESC")
    LiveData<List<FreeSpaceEntry>> getFreeSpaceFromRoom(int roomID);

    @Query("SELECT * FROM FreeSpaceEntry WHERE restID == :restID ORDER BY frSpaceID DESC")
    LiveData<List<FreeSpaceEntry>> getFreeSpaceFromRest(int restID);

    @Query("SELECT * FROM FreeSpaceEntry WHERE roomID IN (:roomID)")
    LiveData<List<FreeSpaceEntry>> getAllFreeSpaces(List<Integer> roomID);

    @Query("SELECT * FROM FreeSpaceEntry WHERE restID IN (:restID)")
    LiveData<List<FreeSpaceEntry>> getAllRestFreeSpaces(List<Integer> restID);

    @Query("SELECT * FROM FreeSpaceEntry WHERE circID == :circID ORDER BY frSpaceID DESC")
    LiveData<List<FreeSpaceEntry>> getFreeSpaceFromCirc(int circID);

    @Query("SELECT * FROM FreeSpaceEntry WHERE circID IN (:circID)")
    LiveData<List<FreeSpaceEntry>> getAllCircFreeSpaces(List<Integer> circID);

    @Query("SELECT * FROM FreeSpaceEntry WHERE frSpaceID == :freeSpaceID")
    LiveData<FreeSpaceEntry> getSpecificFreeSpace(int freeSpaceID);

    @Update
    void updateFreeSpace(FreeSpaceEntry freeSpace);

    @Query("DELETE FROM FreeSpaceEntry WHERE frSpaceID == :freeSpaceID")
    void deleteFreeSpace(int freeSpaceID);

    @Query("DELETE FROM FreeSpaceEntry WHERE roomID == :roomID")
    void deleteAllFreeSpaceFromRoom(int roomID);
}
