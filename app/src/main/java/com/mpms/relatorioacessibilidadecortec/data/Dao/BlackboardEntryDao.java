package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.BlackboardEntry;

import java.util.List;

@Dao
public interface BlackboardEntryDao {

    @Insert
    void insertBlackboard(BlackboardEntry blackboard);

    @Query("SELECT * FROM BlackboardEntry WHERE roomID == :roomID ORDER BY boardID DESC")
    LiveData<List<BlackboardEntry>> getAllBlackboardsFromRoom(int roomID);

    @Query("SELECT * FROM BlackboardEntry WHERE boardID == :blackboardID")
    LiveData<BlackboardEntry> getOneBlackboard(int blackboardID);

    @Update
    void updateBlackboard (BlackboardEntry blackboard);

    @Query("DELETE FROM BlackboardEntry WHERE boardID == :blackboardID")
    void deleteBlackboard(int blackboardID);

    @Query("DELETE FROM blackboardEntry WHERE roomID == :roomID")
    void deleteAllBlackboardsFromRoom(int roomID);
}
