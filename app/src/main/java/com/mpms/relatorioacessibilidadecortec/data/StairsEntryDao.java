package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.StairsEntry;

import java.util.List;

@Dao
public interface StairsEntryDao {

    @Insert
    void insertStairs(StairsEntry stairs);

    @Query("SELECT * FROM StairsEntry ORDER BY StairsID DESC")
    LiveData<List<StairsEntry>> getAllStairs();

    @Query("SELECT * FROM StairsEntry WHERE stairsID == :stairsID")
    LiveData<StairsEntry> getStairsEntry(int stairsID);

    @Query("SELECT * FROM StairsEntry WHERE stairsID == (SELECT MAX(stairsID) from StairsEntry)")
    LiveData<StairsEntry> getLastStairsEntry();

    @Query("DELETE FROM StairsEntry WHERE stairsID == :stairsID")
    void deleteOneStairs(int stairsID);

    @Query("DELETE FROM StairsEntry WHERE schoolID == :schoolID")
    void deleteAllStairsFromSchool(int schoolID);

    @Update
    void updateStairs(StairsEntry stairs);
}
