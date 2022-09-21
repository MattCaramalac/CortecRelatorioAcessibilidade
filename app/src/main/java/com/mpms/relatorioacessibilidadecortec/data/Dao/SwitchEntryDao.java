package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.SwitchEntry;

import java.util.List;

@Dao
public interface SwitchEntryDao {

    @Insert
    void insertSwitch(SwitchEntry switchEntry);

    @Query("SELECT * FROM SwitchEntry WHERE roomID == :roomID ORDER BY switchID DESC")
    LiveData<List<SwitchEntry>> selectSwitchesFromRoom(int roomID);

    @Query("SELECT * FROM SwitchEntry WHERE roomID IN (:roomID)")
    LiveData<List<SwitchEntry>> getAllSwitches(List<Integer> roomID);

    @Query("SELECT * FROM SwitchEntry WHERE switchID == :switchID")
    LiveData<SwitchEntry> selectSpecificSwitch(int switchID);

    @Update
    void updateSwitch(SwitchEntry switchEntry);

    @Query("DELETE FROM SwitchEntry WHERE switchID == :switchID")
    void deleteSwitch(int switchID);

    @Query("DELETE FROM SwitchEntry WHERE roomID == :roomID")
    void deleteAllSwitchesFromRoom(int roomID);
}
