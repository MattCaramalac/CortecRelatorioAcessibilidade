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

    @Query("SELECT * FROM TableEntry WHERE blockID == :blockID AND roomID == :roomID")
    LiveData<List<TableEntry>> selectTablesFromRoom(int blockID, int roomID);

    @Query("SELECT * FROM TableEntry WHERE tableID == :tableID")
    LiveData<TableEntry> selectSpecificTable(int tableID);

    @Update
    void updateTable(TableEntry table);

    @Query("DELETE FROM TableEntry WHERE tableID == :tableID")
    void deleteTable(int tableID);

    @Query("DELETE FROM TableEntry WHERE blockID == :blockID AND roomID == :roomID")
    void deleteAllTablesFromRoom(int blockID, int roomID);
}
