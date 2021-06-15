package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.WindowEntry;

import java.util.List;

@Dao
public interface WindowEntryDao {

    @Insert
    void insertWindow(WindowEntry windowEntry);

    @Query("SELECT * FROM WindowEntry WHERE schoolID == :schoolID AND roomID == :roomID")
    LiveData<List<WindowEntry>> selectWindowsFromRoom(int schoolID, int roomID);

    @Query("SELECT * FROM WindowEntry WHERE windowID == :windowID")
    LiveData<WindowEntry> selectSpecificWindow(int windowID);

    @Update
    void updateWindowEntry(WindowEntry windowEntry);

    @Query("DELETE FROM WindowEntry WHERE windowID == :windowID")
    void deleteWindow(int windowID);

    @Query("DELETE FROM WindowEntry WHERE schoolID == :schoolID AND roomID == :roomID")
    void deleteAllWindowsFromRoom(int schoolID, int roomID);
}
