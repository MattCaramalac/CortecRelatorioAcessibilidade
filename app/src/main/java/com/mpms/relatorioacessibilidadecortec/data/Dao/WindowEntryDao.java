package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.common.util.concurrent.ListenableFuture;
import com.mpms.relatorioacessibilidadecortec.data.entities.WindowEntry;

import java.util.List;

@Dao
public interface WindowEntryDao {

    @Insert
    void insertWindow(WindowEntry windowEntry);

    @Query("SELECT * FROM WindowEntry WHERE roomID == :roomID ORDER BY windowID DESC")
    LiveData<List<WindowEntry>> getWindowsFromRoom(int roomID);

    @Query("SELECT * FROM WindowEntry WHERE roomID IN (:roomID)")
    LiveData<List<WindowEntry>> getAllRoomsWindows(List<Integer> roomID);

    @Query("SELECT * FROM WindowEntry WHERE circID == :circID ORDER BY windowID DESC")
    LiveData<List<WindowEntry>> getWindowsFromCirc(int circID);

    @Query("SELECT * FROM WindowEntry WHERE circID IN (:circID)")
    LiveData<List<WindowEntry>> getAllCircWindows(List<Integer> circID);

    @Query("SELECT * FROM WindowEntry WHERE windowID == :windowID")
    LiveData<WindowEntry> selectSpecificWindow(int windowID);

    @Update
    void updateWindowEntry(WindowEntry windowEntry);

    @Query("DELETE FROM WindowEntry WHERE windowID == :windowID")
    void deleteWindow(int windowID);

    @Query("DELETE FROM WindowEntry WHERE roomID == :roomID")
    void deleteAllWindowsFromRoom(int roomID);

    //    Listenable
    @Query("SELECT * FROM WindowEntry WHERE roomID IN (:roomID)")
    ListenableFuture<List<WindowEntry>> getListAllRoomsWindows(List<Integer> roomID);

    @Query("SELECT * FROM WindowEntry WHERE circID IN (:circID)")
    ListenableFuture<List<WindowEntry>> getListAllCircWindows(List<Integer> circID);
}
