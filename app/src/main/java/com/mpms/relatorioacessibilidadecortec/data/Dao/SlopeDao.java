package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.common.util.concurrent.ListenableFuture;
import com.mpms.relatorioacessibilidadecortec.data.entities.SlopeEntry;

import java.util.List;

@Dao
public interface SlopeDao {

    @Insert
    void insertSlope(SlopeEntry slop);

    @Query("SELECT * FROM SlopeEntry WHERE circID == :circID ORDER BY slopeID DESC")
    LiveData<List<SlopeEntry>> getAllCircSlopes(int circID);

    @Query("SELECT * FROM SlopeEntry WHERE circID IN (:circID)")
    LiveData<List<SlopeEntry>> getCircAllSlopes(List<Integer> circID);

    @Query("SELECT * FROM SlopeEntry WHERE roomID == :roomID")
    LiveData<List<SlopeEntry>> getAllRoomSlopes(int roomID);

    @Query("SELECT * FROM SlopeEntry WHERE roomID IN (:roomID)")
    LiveData<List<SlopeEntry>> getAllRoomSlopes(List<Integer> roomID);

    @Query("SELECT * FROM SlopeEntry WHERE slopeID == :slopeID")
    LiveData<SlopeEntry> getOneSlope(int slopeID);

    @Update
    void updateSlope(SlopeEntry slope);

    @Query("DELETE FROM SlopeEntry WHERE slopeID == :slopeID")
    void deleteSlope(int slopeID);

    @Query("DELETE FROM SlopeEntry WHERE circID == :circID")
    void deleteAllSlopes(int circID);

    //    Listenable
    @Query("SELECT * FROM SlopeEntry WHERE roomID IN (:roomID)")
    ListenableFuture<List<SlopeEntry>> getListAllRoomSlopes(List<Integer> roomID);

    @Query("SELECT * FROM SlopeEntry WHERE circID IN (:circID)")
    ListenableFuture<List<SlopeEntry>> getListCircAllSlopes(List<Integer> circID);
}
