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

    @Query("SELECT * FROM RampStairsEntry WHERE blockID == :blockID AND extAccessID == :ambientID AND rampStairsIdentifier == :rampOrStairs  ORDER BY rampStairsID DESC")
    LiveData<List<RampStairsEntry>> getStairsRampFromExtAccess(int blockID, int ambientID, int rampOrStairs);

    @Query("SELECT * FROM RampStairsEntry WHERE blockID == :blockID AND sidewalkID == :ambientID AND rampStairsIdentifier == :rampOrStairs  ORDER BY rampStairsID DESC")
    LiveData<List<RampStairsEntry>> getStairsRampFromSidewalk(int blockID, int ambientID, int rampOrStairs);

    @Query("SELECT * FROM RampStairsEntry WHERE blockID == :blockID AND parkingID == :ambientID AND rampStairsIdentifier == :rampOrStairs  ORDER BY rampStairsID DESC")
    LiveData<List<RampStairsEntry>> getStairsRampFromParking(int blockID, int ambientID, int rampOrStairs);

    @Query("SELECT * FROM RampStairsEntry WHERE blockID == :blockID AND roomID == :ambientID AND rampStairsIdentifier == :rampOrStairs  ORDER BY rampStairsID DESC")
    LiveData<List<RampStairsEntry>> getStairsRampFromRoom(int blockID, int ambientID, int rampOrStairs);

    @Query("SELECT * FROM RampStairsEntry WHERE rampStairsID == :rampStairsID")
    LiveData<RampStairsEntry> getRampStairsEntry(int rampStairsID);

    @Query("SELECT * FROM RampStairsEntry WHERE rampStairsID IN (:rampStairsID)")
    LiveData<List<RampStairsEntry>> getAllRampStairsEntry(List<Integer> rampStairsID);

    @Query("SELECT * FROM RampStairsEntry WHERE rampStairsID == (SELECT MAX(rampStairsID) from RampStairsEntry)")
    LiveData<RampStairsEntry> getLastRampStairsEntry();

    @Query("DELETE FROM RampStairsEntry WHERE rampStairsID == :rampStairsID")
    void deleteOneRampStairs(int rampStairsID);

    @Query("DELETE FROM RampStairsEntry WHERE blockID == :blockID")
    void deleteAllRampStairsFromSchool(int blockID);

    @Update
    void updateRampStairs(RampStairsEntry entry);
}
