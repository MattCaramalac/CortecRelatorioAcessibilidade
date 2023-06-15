package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.common.util.concurrent.ListenableFuture;
import com.mpms.relatorioacessibilidadecortec.data.entities.CounterEntry;

import java.util.List;

@Dao
public interface CounterEntryDao {

    @Insert
    void insertCounter(CounterEntry counter);

    @Query("SELECT * FROM CounterEntry WHERE roomID == :roomID ORDER BY counterID DESC")
    LiveData<List<CounterEntry>> getCountersFromRoom(int roomID);

    @Query("SELECT * FROM CounterEntry WHERE roomID IN (:roomID)")
    LiveData<List<CounterEntry>> getAllCounters(List<Integer> roomID);

    @Query("SELECT * FROM CounterEntry WHERE circID == :circID ORDER BY counterID DESC")
    LiveData<List<CounterEntry>> getCountersFromCirc(int circID);

    @Query("SELECT * FROM CounterEntry WHERE circID IN (:circID)")
    LiveData<List<CounterEntry>> getAllCircCounters(List<Integer> circID);

    @Query("SELECT * FROM CounterEntry WHERE counterID == :counterID")
    LiveData<CounterEntry> getSpecificCounter(int counterID);

    @Update
    void updateCounter(CounterEntry counter);

    @Query("DELETE FROM CounterEntry WHERE counterID == :counterID")
    void deleteCounter(int counterID);

    @Query("DELETE FROM CounterEntry WHERE roomID == :roomID")
    void deleteAllCounterFromRoom(int roomID);

    //    Listenable
    @Query("SELECT * FROM CounterEntry WHERE roomID IN (:roomID)")
    ListenableFuture<List<CounterEntry>> getListAllCounters(List<Integer> roomID);

    @Query("SELECT * FROM CounterEntry WHERE circID IN (:circID)")
    ListenableFuture<List<CounterEntry>> getListAllCircCounters(List<Integer> circID);
}
