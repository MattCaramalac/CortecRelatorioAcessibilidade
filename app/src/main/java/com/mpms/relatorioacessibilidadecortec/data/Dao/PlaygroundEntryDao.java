package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.PlaygroundEntry;

import java.util.List;

@Dao
public interface PlaygroundEntryDao {

    @Insert
    void insertPlayground(PlaygroundEntry play);

    @Query("SELECT * FROM PlaygroundEntry WHERE blockID == :blockID ORDER BY playgroundID DESC")
    LiveData<List<PlaygroundEntry>> getAllPlaygroundsPerBlock(int blockID);

    @Query("SELECT * FROM PlaygroundEntry WHERE playgroundID == :playgroundID")
    LiveData<PlaygroundEntry> getOnePlayground(int playgroundID);

    @Update
    void updatePlayground(PlaygroundEntry play);

    @Query("DELETE FROM PlaygroundEntry WHERE playgroundID == :playgroundID")
    void deleteOnePlayground(int playgroundID);

    @Query("DELETE FROM PlaygroundEntry WHERE blockID == :blockID")
    void deleteAllPlaygroundsFromBlock(int blockID);
}
