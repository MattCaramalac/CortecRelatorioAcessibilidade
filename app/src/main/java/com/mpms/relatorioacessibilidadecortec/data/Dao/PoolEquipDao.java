package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.common.util.concurrent.ListenableFuture;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolEquipEntry;

import java.util.List;

@Dao
public interface PoolEquipDao {

    @Insert
    void insertPoolEquip(PoolEquipEntry equip);

    @Query("SELECT * FROM PoolEquipEntry WHERE poolID == :poolID ORDER BY poolEquipID DESC")
    LiveData<List<PoolEquipEntry>> getPoolEquips(int poolID);

    @Query("SELECT * FROM PoolEquipEntry WHERE poolID IN (:poolList)")
    LiveData<List<PoolEquipEntry>> getEveryPoolEquips(List<Integer> poolList);

    @Query("SELECT * FROM PoolEquipEntry WHERE poolEquipID == :EquipID")
    LiveData<PoolEquipEntry> getOnePoolEquip(int EquipID);

    @Update
    void updatePoolEquip(PoolEquipEntry Equip);

    @Query("DELETE FROM PoolEquipEntry WHERE poolEquipID == :equipID")
    void deletePoolEquip(int equipID);

    @Query("DELETE FROM PoolEquipEntry WHERE poolID == :poolID")
    void deleteAllPoolEquips(int poolID);

    //    Listenable
    @Query("SELECT * FROM PoolEquipEntry WHERE poolID IN (:poolList)")
    ListenableFuture<List<PoolEquipEntry>> getListAllPoolEquips(List<Integer> poolList);
}
