package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.SoleStepEntry;

import java.util.List;

@Dao
public interface SoleStepDao {

    @Insert
    void insertSoleStep(SoleStepEntry slop);

    @Query("SELECT * FROM SoleStepEntry WHERE circID == :circID")
    LiveData<List<SoleStepEntry>> getAllSoleSteps(int circID);

    @Query("SELECT * FROM SoleStepEntry WHERE stepID == :stepID")
    LiveData<SoleStepEntry> getOneSoleStep(int stepID);

    @Update
    void updateSoleStep (SoleStepEntry soleStep);

    @Query("DELETE FROM SoleStepEntry WHERE stepID == :stepID")
    void deleteSoleStep(int stepID);

    @Query("DELETE FROM SoleStepEntry WHERE circID == :circID")
    void deleteAllSoleSteps(int circID);
}
