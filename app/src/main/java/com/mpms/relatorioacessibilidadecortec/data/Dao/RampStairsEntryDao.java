package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.common.util.concurrent.ListenableFuture;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;

import java.util.List;

@Dao
public interface RampStairsEntryDao {

    @Insert
    void insertRampStairs(RampStairsEntry entry);

    @Query("SELECT * FROM RampStairsEntry WHERE extAccessID == :ambientID AND rampStairsIdentifier == :rampOrStairs  ORDER BY rampStairsID DESC")
    LiveData<List<RampStairsEntry>> getStairsRampFromExtAccess(int ambientID, int rampOrStairs);

    @Query("SELECT * FROM RampStairsEntry WHERE parkingID == :ambientID AND rampStairsIdentifier == :rampOrStairs  ORDER BY rampStairsID DESC")
    LiveData<List<RampStairsEntry>> getStairsRampFromParking(int ambientID, int rampOrStairs);

    @Query("SELECT * FROM RampStairsEntry WHERE roomID == :ambientID AND rampStairsIdentifier == :rampOrStairs  ORDER BY rampStairsID DESC")
    LiveData<List<RampStairsEntry>> getStairsRampFromRoom(int ambientID, int rampOrStairs);

    @Query("SELECT * FROM RampStairsEntry WHERE circID == :ambientID AND rampStairsIdentifier == :rampOrStairs  ORDER BY rampStairsID DESC")
    LiveData<List<RampStairsEntry>> getStairsRampFromCirculation(int ambientID, int rampOrStairs);

    @Query("SELECT * FROM RampStairsEntry WHERE rampStairsID == :rampStairsID")
    LiveData<RampStairsEntry> getRampStairsEntry(int rampStairsID);

    @Query("SELECT * FROM RampStairsEntry WHERE roomID IN (:roomID)")
    LiveData<List<RampStairsEntry>> getAllRampStRoom(List<Integer> roomID);

    @Query("SELECT * FROM RampStairsEntry WHERE extAccessID IN (:extID)")
    LiveData<List<RampStairsEntry>> getAllRampStExt(List<Integer> extID);

    @Query("SELECT * FROM RampStairsEntry WHERE parkingID IN (:parkID)")
    LiveData<List<RampStairsEntry>> getAllRampStPark(List<Integer> parkID);

    @Query("SELECT * FROM RampStairsEntry WHERE circID IN (:circID)")
    LiveData<List<RampStairsEntry>> getAllRampStCirc(List<Integer> circID);

    @Query("SELECT * FROM RampStairsEntry WHERE rampStairsID == (SELECT MAX(rampStairsID) from RampStairsEntry)")
    LiveData<RampStairsEntry> getLastRampStairsEntry();

    @Query("DELETE FROM RampStairsEntry WHERE rampStairsID == :rampStairsID")
    void deleteOneRampStairs(int rampStairsID);

    @Update
    void updateRampStairs(RampStairsEntry entry);

    //    Listenable
    @Query("SELECT * FROM RampStairsEntry WHERE roomID IN (:roomID)")
    ListenableFuture<List<RampStairsEntry>> getListAllRampStRoom(List<Integer> roomID);

    @Query("SELECT * FROM RampStairsEntry WHERE extAccessID IN (:extID)")
    ListenableFuture<List<RampStairsEntry>> getListAllRampStExt(List<Integer> extID);

    @Query("SELECT * FROM RampStairsEntry WHERE circID IN (:circID)")
    ListenableFuture<List<RampStairsEntry>> getListAllRampStCirc(List<Integer> circID);

    @Query("SELECT rampStairsID FROM RampStairsEntry WHERE roomID IN (:roomID)")
    ListenableFuture<List<Integer>> getListAllRampStIDRoom(List<Integer> roomID);

    @Query("SELECT rampStairsID FROM RampStairsEntry WHERE extAccessID IN (:extID)")
    ListenableFuture<List<Integer>> getListAllRampStIDExt(List<Integer> extID);

    @Query("SELECT rampStairsID FROM RampStairsEntry WHERE circID IN (:circID)")
    ListenableFuture<List<Integer>> getListAllRampStIDCirc(List<Integer> circID);

    @Query("SELECT * FROM RampStairsEntry WHERE parkingID IN (:parkID)")
    ListenableFuture<List<RampStairsEntry>> getListAllRampStPark(List<Integer> parkID);

    @Query("SELECT rampStairsID FROM RampStairsEntry WHERE parkingID IN (:parkID)")
    ListenableFuture<List<Integer>> getListAllRampStParkID(List<Integer> parkID);
}
