package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.AdmEquipEntry;

import java.util.List;

@Dao
public interface AdmEquipDao {

    @Insert
    void insertAdmEquip(AdmEquipEntry AdmEquip);

    @Query("SELECT * FROM AdmEquipEntry WHERE blockID == :blockID ORDER BY admEquipID DESC")
    LiveData<List<AdmEquipEntry>> getAllAdmEquipsPerBlock(int blockID);

    @Query("SELECT * FROM AdmEquipEntry WHERE AdmEquipID == :AdmEquipID")
    LiveData<AdmEquipEntry> getOneAdmEquip(int AdmEquipID);

    @Update
    void updateAdmEquip(AdmEquipEntry AdmEquip);

    @Query("DELETE FROM AdmEquipEntry WHERE AdmEquipID == :AdmEquipID")
    void deleteOneAdmEquip(int AdmEquipID);

    @Query("DELETE FROM AdmEquipEntry WHERE blockID == :blockID")
    void deleteAllAdmEquipsFromBlock(int blockID);
}
