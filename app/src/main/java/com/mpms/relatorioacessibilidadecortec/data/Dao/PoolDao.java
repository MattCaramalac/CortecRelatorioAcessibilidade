package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.PoolEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolEntryOne;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolEntryTwo;

import java.util.List;

@Dao
public interface PoolDao {

    @Insert
    void insertPool(PoolEntry pool);

    @Query("SELECT * FROM PoolEntry WHERE blockID == :blockID ORDER BY poolID DESC")
    LiveData<List<PoolEntry>> getAllPoolsPerBlock(int blockID);

    @Query("SELECT * FROM PoolEntry WHERE blockID IN (:blockID)")
    LiveData<List<PoolEntry>> getAllPools(List<Integer> blockID);

    @Query("SELECT * FROM PoolEntry WHERE poolID == :poolID")
    LiveData<PoolEntry> getPool(int poolID);

    @Query("SELECT * FROM PoolEntry WHERE poolID == (SELECT MAX(poolID) from POOLENTRY)")
    LiveData<PoolEntry> getLastPoolEntry();

    @Update
    void updatePool(PoolEntry pool);

    @Update(entity = PoolEntry.class)
    void updatePoolOne(PoolEntryOne... poolOne);

    @Update(entity = PoolEntry.class)
    void updatePoolTwo(PoolEntryTwo... poolTwo);

    @Query("DELETE FROM PoolEntry WHERE poolID == :poolID")
    void deletePool(int poolID);

    @Query("DELETE FROM PoolEntry WHERE blockID == :blockID")
    void deleteAllPools(int blockID);
}
