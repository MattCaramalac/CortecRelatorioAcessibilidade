package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.CirculationEntry;

import java.util.List;

@Dao
public interface CirculationDao {

    @Insert
    void insertCirculation(CirculationEntry circ);

    @Query("SELECT * FROM CirculationEntry WHERE schoolID == :schoolID ORDER BY circID DESC")
    LiveData<List<CirculationEntry>> getAllCirculations(int schoolID);

    @Query("SELECT * FROM CirculationEntry WHERE circID == :circID")
    LiveData<CirculationEntry> getOneCirculation(int circID);

    @Query("SELECT * FROM CirculationEntry WHERE circID == (SELECT MAX(circID) from CirculationEntry)")
    LiveData<CirculationEntry> getLastCirculation();

    @Update
    void updateCirculation (CirculationEntry circ);

    @Query("DELETE FROM CirculationEntry WHERE circID == :circID")
    void deleteCirculation(int circID);

    @Query("DELETE FROM CirculationEntry WHERE schoolID == :schoolID")
    void deleteAllCirculations(int schoolID);
}
