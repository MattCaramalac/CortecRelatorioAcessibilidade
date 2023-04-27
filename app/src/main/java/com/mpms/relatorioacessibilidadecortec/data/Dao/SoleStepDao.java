package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.SingleStepEntry;

import java.util.List;

@Dao
public interface SoleStepDao {

    @Insert
    void insertSoleStep(SingleStepEntry slop);

    @Query("SELECT * FROM SingleStepEntry WHERE circID == :circID ORDER BY stepID DESC")
    LiveData<List<SingleStepEntry>> getAllCircSingleSteps(int circID);

    @Query("SELECT * FROM SingleStepEntry WHERE circID IN (:circID)")
    LiveData<List<SingleStepEntry>> getAllSingleStepsCirc(List<Integer> circID);

    @Query("SELECT * FROM SingleStepEntry WHERE roomID == :roomID")
    LiveData<List<SingleStepEntry>> getAllRoomSingleSteps(int roomID);

    @Query("SELECT * FROM SingleStepEntry WHERE stepID == :stepID")
    LiveData<SingleStepEntry> getOneSoleStep(int stepID);

    @Update
    void updateSoleStep (SingleStepEntry soleStep);

    @Query("DELETE FROM SingleStepEntry WHERE stepID == :stepID")
    void deleteSoleStep(int stepID);

    @Query("DELETE FROM SingleStepEntry WHERE circID == :circID")
    void deleteAllSoleSteps(int circID);
}
