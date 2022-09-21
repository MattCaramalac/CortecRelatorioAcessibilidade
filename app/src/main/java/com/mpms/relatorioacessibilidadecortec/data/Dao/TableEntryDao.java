package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.TableEntry;

import java.util.List;

@Dao
public interface TableEntryDao {

    @Insert
    void insertTable(TableEntry table);

    @Query("SELECT * FROM TableEntry WHERE roomID == :roomID ORDER BY tableID DESC")
    LiveData<List<TableEntry>> selectTablesFromRoom(int roomID);

    @Query("SELECT * FROM TableEntry WHERE roomID IN (:roomID)")
    LiveData<List<TableEntry>> getAllTables(List<Integer> roomID);

    @Query("SELECT * FROM TableEntry WHERE tableID == :tableID")
    LiveData<TableEntry> selectSpecificTable(int tableID);

    @Update
    void updateTable(TableEntry table);

    @Query("DELETE FROM TableEntry WHERE tableID == :tableID")
    void deleteTable(int tableID);

    @Query("DELETE FROM TableEntry WHERE roomID == :roomID")
    void deleteAllTablesFromRoom(int roomID);
}
