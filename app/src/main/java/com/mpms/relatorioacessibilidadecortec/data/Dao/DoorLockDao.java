package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.DoorLockEntry;

import java.util.List;

@Dao
public interface DoorLockDao {

    @Insert
    void insertDoorLock(DoorLockEntry doorLock);

    @Query("SELECT * FROM DoorLockEntry WHERE doorID == :doorID ORDER BY lockID DESC")
    LiveData<List<DoorLockEntry>> getDoorLocksFromDoor(int doorID);

    @Query("SELECT * FROM DoorLockEntry WHERE lockID == :lockID")
    LiveData<DoorLockEntry> getOneDoorLock(int lockID);

    @Query("SELECT * FROM DoorLockEntry WHERE lockID == (SELECT MAX(lockID) from DoorLockEntry)")
    LiveData<DoorLockEntry> getLastDoorLockEntry();

    @Update
    void updateDoorLock(DoorLockEntry doorLock);

    @Query("DELETE FROM DoorLockEntry WHERE lockID == :lockID")
    void deleteDoorLock(int lockID);

    @Query("DELETE FROM DoorLockEntry WHERE doorID == :doorID")
    void deleteAllDoorLocksFromDoor(int doorID);
}

