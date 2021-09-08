package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.SwitchEntry;

import java.util.List;

@Dao
public interface SwitchEntryDao {

    @Insert
    void insertSwitch(SwitchEntry switchEntry);

    @Query("SELECT * FROM SwitchEntry WHERE schoolID == :schoolID AND roomID == :roomID")
    LiveData<List<SwitchEntry>> selectSwitchesFromRoom(int schoolID, int roomID);

    @Query("SELECT * FROM SwitchEntry WHERE switchID == :switchID")
    LiveData<SwitchEntry> selectSpecificSwitch(int switchID);

    @Update
    void updateSwitch(SwitchEntry switchEntry);

    @Query("DELETE FROM SwitchEntry WHERE switchID == :switchID")
    void deleteSwitch(int switchID);

    @Query("DELETE FROM SwitchEntry WHERE schoolID == :schoolID AND roomID == :roomID")
    void deleteAllSwitchesFromRoom(int schoolID, int roomID);
}
