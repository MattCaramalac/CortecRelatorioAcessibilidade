package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.PoolStairsEntry;

import java.util.List;

@Dao
public interface PoolStairsDao {

    @Insert
    void insertPoolStairs(PoolStairsEntry stairs);

    @Query("SELECT * FROM PoolStairsEntry WHERE poolID == :poolID ORDER BY poolStairsID DESC")
    LiveData<List<PoolStairsEntry>> getPoolStairs(int poolID);

    @Query("SELECT * FROM PoolStairsEntry WHERE poolID IN (:poolList)")
    LiveData<List<PoolStairsEntry>> getEveryPoolStairs(List<Integer> poolList);

    @Query("SELECT * FROM PoolStairsEntry WHERE poolStairsID == :StairsID")
    LiveData<PoolStairsEntry> getOnePoolStairs(int StairsID);

    @Update
    void updatePoolStairs(PoolStairsEntry Stairs);

    @Query("DELETE FROM PoolStairsEntry WHERE poolStairsID == :stairsID")
    void deletePoolStairs(int stairsID);

    @Query("DELETE FROM PoolStairsEntry WHERE poolID == :poolID")
    void deleteAllPoolStairs(int poolID);
}
