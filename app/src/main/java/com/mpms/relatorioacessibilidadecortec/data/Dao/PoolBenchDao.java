package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.PoolBenchEntry;

import java.util.List;

@Dao
public interface PoolBenchDao {

    @Insert
    void insertPoolBench(PoolBenchEntry bench);

    @Query("SELECT * FROM PoolBenchEntry WHERE poolID == :poolID ORDER BY poolBenchID DESC")
    LiveData<List<PoolBenchEntry>> getPoolBenches(int poolID);

    @Query("SELECT * FROM PoolBenchEntry WHERE poolID IN (:poolList)")
    LiveData<List<PoolBenchEntry>> getAllPoolBenches(List<Integer> poolList);

    @Query("SELECT * FROM PoolBenchEntry WHERE poolBenchID == :benchID")
    LiveData<PoolBenchEntry> getOnePoolBench(int benchID);

    @Update
    void updatePoolBench(PoolBenchEntry pool);

    @Query("DELETE FROM PoolBenchEntry WHERE poolBenchID == :benchID")
    void deletePoolBench(int benchID);

    @Query("DELETE FROM PoolBenchEntry WHERE poolID == :poolID")
    void deleteAllPoolBenches(int poolID);
}
