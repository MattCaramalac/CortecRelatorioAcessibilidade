package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.TableEntry;

import java.util.List;

@Dao
public interface TableEntryDao {

    @Insert
    void insertSwitch(TableEntry table);

    @Query("SELECT * FROM TableEntry WHERE schoolID == :schoolID AND roomID == :roomID")
    LiveData<List<TableEntry>> selectTablesFromRoom(int schoolID, int roomID);

    @Query("SELECT * FROM TableEntry WHERE tableID == :tableID")
    LiveData<TableEntry> selectSpecificTable(int tableID);

    @Update
    void updateTable(TableEntry table);

    @Query("DELETE FROM TableEntry WHERE tableID == :tableID")
    void deleteTable(int tableID);

    @Query("DELETE FROM TableEntry WHERE schoolID == :schoolID AND roomID == :roomID")
    void deleteAllTablesFromRoom(int schoolID, int roomID);
}
