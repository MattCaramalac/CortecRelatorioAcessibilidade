package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.CounterEntry;

import java.util.List;

@Dao
public interface CounterEntryDao {

    @Insert
    void insertCounter(CounterEntry counter);

    @Query("SELECT * FROM CounterEntry WHERE roomID == :roomID")
    LiveData<List<CounterEntry>> getCountersFromRoom(int roomID);

    @Query("SELECT * FROM CounterEntry WHERE counterID == :counterID")
    LiveData<CounterEntry> getSpecificCounter(int counterID);

    @Update
    void updateCounter (CounterEntry counter);

    @Query("DELETE FROM CounterEntry WHERE counterID == :counterID")
    void deleteCounter(int counterID);

    @Query("DELETE FROM CounterEntry WHERE counterID == :counterID")
    void deleteAllCounterFromRoom(int counterID);
}
