package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.SlopeEntry;

import java.util.List;

@Dao
public interface SlopeDao {

    @Insert
    void insertSlope(SlopeEntry slop);

//    TODO - Fazer captação de todos os Slopes pro relatório

    @Query("SELECT * FROM SlopeEntry WHERE circID == :circID")
    LiveData<List<SlopeEntry>> getAllCircSlopes(int circID);

    @Query("SELECT * FROM SlopeEntry WHERE roomID == :roomID")
    LiveData<List<SlopeEntry>> getAllRoomSlopes(int roomID);

    @Query("SELECT * FROM SlopeEntry WHERE slopeID == :slopeID")
    LiveData<SlopeEntry> getOneSlope(int slopeID);

    @Update
    void updateSlope (SlopeEntry slope);

    @Query("DELETE FROM SlopeEntry WHERE slopeID == :slopeID")
    void deleteSlope(int slopeID);

    @Query("DELETE FROM SlopeEntry WHERE circID == :circID")
    void deleteAllSlopes(int circID);
}
