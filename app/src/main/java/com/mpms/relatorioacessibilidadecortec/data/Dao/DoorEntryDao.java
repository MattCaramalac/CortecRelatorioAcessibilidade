package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.common.util.concurrent.ListenableFuture;
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

    @Query("SELECT * FROM DoorEntry WHERE restID == :restID ORDER BY doorID DESC")
    LiveData<DoorEntry> getRestDoor(int restID);

    @Query("SELECT * FROM DoorEntry WHERE restID IN (:restID)")
    LiveData<List<DoorEntry>> getAllRestDoors(List<Integer> restID);

    @Query("SELECT * FROM DoorEntry WHERE boxID IN (:boxID)")
    LiveData<List<DoorEntry>> getAllBoxDoors(List<Integer> boxID);

    @Query("SELECT * FROM DoorEntry WHERE boxID == :boxID")
    LiveData<DoorEntry> getAccBoxDoor(int boxID);

    @Query("SELECT * FROM DoorEntry WHERE doorID == (SELECT MAX(doorID) from DoorEntry)")
    LiveData<DoorEntry> getLastDoorEntry();

    @Query("SELECT * FROM DoorEntry WHERE circID == :circID ORDER BY doorID DESC")
    LiveData<List<DoorEntry>> getCircDoor(int circID);

    @Query("SELECT * FROM DoorEntry WHERE restID IN (:circID)")
    LiveData<List<DoorEntry>> getAllCircDoors(List<Integer> circID);

    @Update
    void updateDoor(DoorEntry door);

    @Query("DELETE FROM DoorEntry WHERE doorID == :doorID")
    void deleteDoor(int doorID);

    @Query("DELETE FROM DoorEntry WHERE restID == :restID")
    void deleteRestDoor(int restID);

    @Query("DELETE FROM DoorEntry WHERE roomID == :roomID")
    void deleteAllDoorsFromRoom(int roomID);

    //    Listenable
    @Query("SELECT * FROM DoorEntry WHERE roomID IN (:roomID)")
    ListenableFuture<List<DoorEntry>> getListAllDoors(List<Integer> roomID);

    @Query("SELECT * FROM DoorEntry WHERE restID IN (:restID)")
    ListenableFuture<List<DoorEntry>> getListAllRestDoors(List<Integer> restID);

    @Query("SELECT * FROM DoorEntry WHERE boxID IN (:boxID)")
    ListenableFuture<List<DoorEntry>> getListAllBoxDoors(List<Integer> boxID);

    @Query("SELECT * FROM DoorEntry WHERE restID IN (:circID)")
    ListenableFuture<List<DoorEntry>> getListAllCircDoors(List<Integer> circID);

    @Query("SELECT doorID FROM DoorEntry WHERE roomID IN (:roomID)")
    ListenableFuture<List<Integer>> getListAllDoorsID(List<Integer> roomID);

    @Query("SELECT doorID FROM DoorEntry WHERE restID IN (:restID)")
    ListenableFuture<List<Integer>> getListAllRestDoorsID(List<Integer> restID);

    @Query("SELECT doorID FROM DoorEntry WHERE boxID IN (:boxID)")
    ListenableFuture<List<Integer>> getListAllBoxDoorsID(List<Integer> boxID);

    @Query("SELECT doorID FROM DoorEntry WHERE restID IN (:circID)")
    ListenableFuture<List<Integer>> getListAllCircDoorsID(List<Integer> circID);
}
