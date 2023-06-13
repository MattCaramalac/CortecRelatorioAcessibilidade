package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.common.util.concurrent.ListenableFuture;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlackboardEntry;

import java.util.List;

@Dao
public interface BlackboardEntryDao {

    @Insert
    void insertBlackboard(BlackboardEntry blackboard);

    @Query("SELECT * FROM BlackboardEntry WHERE roomID == :roomID ORDER BY boardID DESC")
    LiveData<List<BlackboardEntry>> getAllBlackboardsFromRoom(int roomID);

    @Query("SELECT * FROM BlackboardEntry WHERE roomID IN (:roomID)")
    LiveData<List<BlackboardEntry>> getAllBlackboards(List<Integer> roomID);

    @Query("SELECT * FROM BlackboardEntry WHERE circID == :circID ORDER BY boardID DESC")
    LiveData<List<BlackboardEntry>> getAllBlackboardsFromCirc(int circID);

    @Query("SELECT * FROM BlackboardEntry WHERE roomID IN (:circID)")
    LiveData<List<BlackboardEntry>> getAllCircBlackboards(List<Integer> circID);

    @Query("SELECT * FROM BlackboardEntry WHERE boardID == :blackboardID")
    LiveData<BlackboardEntry> getOneBlackboard(int blackboardID);

    @Update
    void updateBlackboard(BlackboardEntry blackboard);

    @Query("DELETE FROM BlackboardEntry WHERE boardID == :blackboardID")
    void deleteBlackboard(int blackboardID);

    @Query("DELETE FROM blackboardEntry WHERE roomID == :roomID")
    void deleteAllBlackboardsFromRoom(int roomID);

    //Listenable
    @Query("SELECT * FROM BlackboardEntry WHERE roomID IN (:roomID)")
    ListenableFuture<List<BlackboardEntry>> getListAllBlackboards(List<Integer> roomID);

    @Query("SELECT * FROM BlackboardEntry WHERE roomID IN (:circID)")
    ListenableFuture<List<BlackboardEntry>> getListAllCircBlackboards(List<Integer> circID);
}
