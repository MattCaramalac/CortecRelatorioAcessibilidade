package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.WindowEntry;

import java.util.List;

@Dao
public interface WindowEntryDao {

    @Insert
    void insertWindow(WindowEntry windowEntry);

    @Query("SELECT * FROM WindowEntry WHERE roomID == :roomID ORDER BY windowID DESC")
    LiveData<List<WindowEntry>> selectWindowsFromRoom(int roomID);

    @Query("SELECT * FROM WindowEntry WHERE roomID IN (:roomID)")
    LiveData<List<WindowEntry>> getAllWindows(List<Integer> roomID);

    @Query("SELECT * FROM WindowEntry WHERE windowID == :windowID")
    LiveData<WindowEntry> selectSpecificWindow(int windowID);

    @Update
    void updateWindowEntry(WindowEntry windowEntry);

    @Query("DELETE FROM WindowEntry WHERE windowID == :windowID")
    void deleteWindow(int windowID);

    @Query("DELETE FROM WindowEntry WHERE roomID == :roomID")
    void deleteAllWindowsFromRoom(int roomID);
}
