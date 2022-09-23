package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;

import java.util.List;

@Dao
public interface RampStairsEntryDao {

    @Insert
    void insertRampStairs(RampStairsEntry entry);

    @Query("SELECT * FROM RampStairsEntry WHERE extAccessID == :ambientID AND rampStairsIdentifier == :rampOrStairs  ORDER BY rampStairsID DESC")
    LiveData<List<RampStairsEntry>> getStairsRampFromExtAccess(int ambientID, int rampOrStairs);

    @Query("SELECT * FROM RampStairsEntry WHERE sidewalkID == :ambientID AND rampStairsIdentifier == :rampOrStairs  ORDER BY rampStairsID DESC")
    LiveData<List<RampStairsEntry>> getStairsRampFromSidewalk(int ambientID, int rampOrStairs);

    @Query("SELECT * FROM RampStairsEntry WHERE parkingID == :ambientID AND rampStairsIdentifier == :rampOrStairs  ORDER BY rampStairsID DESC")
    LiveData<List<RampStairsEntry>> getStairsRampFromParking(int ambientID, int rampOrStairs);

    @Query("SELECT * FROM RampStairsEntry WHERE roomID == :ambientID AND rampStairsIdentifier == :rampOrStairs  ORDER BY rampStairsID DESC")
    LiveData<List<RampStairsEntry>> getStairsRampFromRoom(int ambientID, int rampOrStairs);

    @Query("SELECT * FROM RampStairsEntry WHERE rampStairsID == :rampStairsID")
    LiveData<RampStairsEntry> getRampStairsEntry(int rampStairsID);

    @Query("SELECT * FROM RampStairsEntry WHERE roomID IN (:roomID)")
    LiveData<List<RampStairsEntry>> getAllRampStRoom(List<Integer> roomID);

    @Query("SELECT * FROM RampStairsEntry WHERE extAccessID IN (:extID)")
    LiveData<List<RampStairsEntry>> getAllRampStExt(List<Integer> extID);

    @Query("SELECT * FROM RampStairsEntry WHERE sidewalkID IN (:sideID)")
    LiveData<List<RampStairsEntry>> getAllRampStSide(List<Integer> sideID);

    @Query("SELECT * FROM RampStairsEntry WHERE parkingID IN (:parkID)")
    LiveData<List<RampStairsEntry>> getAllRampStPark(List<Integer> parkID);

    @Query("SELECT * FROM RampStairsEntry WHERE rampStairsID == (SELECT MAX(rampStairsID) from RampStairsEntry)")
    LiveData<RampStairsEntry> getLastRampStairsEntry();

    @Query("DELETE FROM RampStairsEntry WHERE rampStairsID == :rampStairsID")
    void deleteOneRampStairs(int rampStairsID);

    @Update
    void updateRampStairs(RampStairsEntry entry);
}
