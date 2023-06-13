package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.common.util.concurrent.ListenableFuture;
import com.mpms.relatorioacessibilidadecortec.data.entities.EquipmentEntry;

import java.util.List;

@Dao
public interface EquipEntryDao {

    @Insert
    void insertEquip(EquipmentEntry equip);

    @Query("SELECT * FROM EquipmentEntry WHERE roomID == :roomID ORDER BY equipID DESC")
    LiveData<List<EquipmentEntry>> getEquipmentFromRoom(int roomID);

    @Query("SELECT * FROM EquipmentEntry WHERE roomID IN (:roomID)")
    LiveData<List<EquipmentEntry>> getAllEquipments(List<Integer> roomID);

    @Query("SELECT * FROM EquipmentEntry WHERE circID == :circID ORDER BY equipID DESC")
    LiveData<List<EquipmentEntry>> getEquipmentFromCirc(int circID);

    @Query("SELECT * FROM EquipmentEntry WHERE circID IN (:circID)")
    LiveData<List<EquipmentEntry>> getAllCircEquipments(List<Integer> circID);

    @Query("SELECT * FROM EquipmentEntry WHERE equipID == :equipID")
    LiveData<EquipmentEntry> getSpecificEquipment(int equipID);

    @Update
    void updateEquipment(EquipmentEntry equip);

    @Query("DELETE FROM EquipmentEntry WHERE equipID == :equipID")
    void deleteEquipment(int equipID);

    @Query("DELETE FROM EquipmentEntry WHERE roomID == :roomID")
    void deleteAllEquipsFromRoom(int roomID);

    //    Test
    @Query("SELECT * FROM EquipmentEntry WHERE roomID IN (:roomID)")
    ListenableFuture<List<EquipmentEntry>> getListAllEquipments(List<Integer> roomID);

    @Query("SELECT * FROM EquipmentEntry WHERE circID IN (:circID)")
    ListenableFuture<List<EquipmentEntry>> getListAllCircEquipments(List<Integer> circID);
}
