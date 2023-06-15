package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.common.util.concurrent.ListenableFuture;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorLockEntry;

import java.util.List;

@Dao
public interface DoorLockDao {

    @Insert
    void insertDoorLock(DoorLockEntry doorLock);

    @Query("SELECT * FROM DoorLockEntry WHERE doorID == :doorID ORDER BY lockID DESC")
    LiveData<List<DoorLockEntry>> getDoorLocksFromDoor(int doorID);

    @Query("SELECT * FROM DoorLockEntry WHERE extAccessID == :extID ORDER BY lockID DESC")
    LiveData<List<DoorLockEntry>> getDoorLocksFromGates(int extID);

    @Query("SELECT * FROM DoorLockEntry WHERE doorID IN (:doorID)")
    LiveData<List<DoorLockEntry>> getAllLocksFromDoor(List<Integer> doorID);

    @Query("SELECT * FROM DoorLockEntry WHERE extAccessID IN (:extID)")
    LiveData<List<DoorLockEntry>> getAllLocksFromGates(List<Integer> extID);

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

    //    Listenable
    @Query("SELECT * FROM DoorLockEntry WHERE doorID IN (:doorID)")
    ListenableFuture<List<DoorLockEntry>> getListAllLocksFromDoor(List<Integer> doorID);

    @Query("SELECT * FROM DoorLockEntry WHERE extAccessID IN (:extID)")
    ListenableFuture<List<DoorLockEntry>> getListAllLocksFromGates(List<Integer> extID);
}

