package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.common.util.concurrent.ListenableFuture;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolRampEntry;

import java.util.List;

@Dao
public interface PoolRampDao {

    @Insert
    void insertPoolRamp(PoolRampEntry ramp);

    @Query("SELECT * FROM PoolRampEntry WHERE poolID == :poolID ORDER BY poolRampID DESC")
    LiveData<List<PoolRampEntry>> getPoolRamps(int poolID);

    @Query("SELECT * FROM PoolRampEntry WHERE poolID IN (:poolList)")
    LiveData<List<PoolRampEntry>> getEveryPoolRamps(List<Integer> poolList);

    @Query("SELECT * FROM PoolRampEntry WHERE poolRampID == :rampID")
    LiveData<PoolRampEntry> getOnePoolRamp(int rampID);

    @Update
    void updatePoolRamp(PoolRampEntry ramp);

    @Query("DELETE FROM PoolRampEntry WHERE PoolRampID == :rampID")
    void deletePoolRamp(int rampID);

    @Query("DELETE FROM PoolRampEntry WHERE poolID == :poolID")
    void deleteAllPoolRamps(int poolID);

    //    Listenable
    @Query("SELECT * FROM PoolRampEntry WHERE poolID IN (:poolList)")
    ListenableFuture<List<PoolRampEntry>> getListAllPoolRamps(List<Integer> poolList);
}
