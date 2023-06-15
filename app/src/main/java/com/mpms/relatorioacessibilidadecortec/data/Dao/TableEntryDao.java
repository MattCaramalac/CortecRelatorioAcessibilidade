package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.common.util.concurrent.ListenableFuture;
import com.mpms.relatorioacessibilidadecortec.data.entities.TableEntry;

import java.util.List;

@Dao
public interface TableEntryDao {

    @Insert
    void insertTable(TableEntry table);

    @Query("SELECT * FROM TableEntry WHERE roomID == :roomID ORDER BY tableID DESC")
    LiveData<List<TableEntry>> getTablesFromRoom(int roomID);

    @Query("SELECT * FROM TableEntry WHERE roomID IN (:roomID)")
    LiveData<List<TableEntry>> getAllRoomsTables(List<Integer> roomID);

    @Query("SELECT * FROM TableEntry WHERE circID == :circID ORDER BY tableID DESC")
    LiveData<List<TableEntry>> getTablesFromCirc(int circID);

    @Query("SELECT * FROM TableEntry WHERE circID IN (:circID)")
    LiveData<List<TableEntry>> getAllCircTables(List<Integer> circID);

    @Query("SELECT * FROM TableEntry WHERE tableID == :tableID")
    LiveData<TableEntry> getSpecificTable(int tableID);

    @Update
    void updateTable(TableEntry table);

    @Query("DELETE FROM TableEntry WHERE tableID == :tableID")
    void deleteTable(int tableID);

    @Query("DELETE FROM TableEntry WHERE roomID == :roomID")
    void deleteAllTablesFromRoom(int roomID);

    //    Listenable
    @Query("SELECT * FROM TableEntry WHERE roomID IN (:roomID)")
    ListenableFuture<List<TableEntry>> getListAllTablesRooms(List<Integer> roomID);

    @Query("SELECT * FROM TableEntry WHERE circID IN (:circID)")
    ListenableFuture<List<TableEntry>> getListAllTablesCirc(List<Integer> circID);
}
