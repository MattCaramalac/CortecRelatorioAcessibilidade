package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.FallProtectionEntry;

import java.util.List;

@Dao
public interface FallProtectDao {

    @Insert
    void insertFallProtection(FallProtectionEntry protect);

    @Query("SELECT * FROM FallProtectionEntry WHERE circID == :circID ORDER BY protectID DESC")
    LiveData<List<FallProtectionEntry>> getFallProtectFromCirc(int circID);

    @Query("SELECT * FROM FallProtectionEntry WHERE circID IN (:circID)")
    LiveData<List<FallProtectionEntry>> getAllFallProtection(List<Integer> circID);

    @Query("SELECT * FROM FallProtectionEntry WHERE protectID == :protectID")
    LiveData<FallProtectionEntry> getOneFallProtection(int protectID);

    @Query("SELECT * FROM FallProtectionEntry WHERE protectID == (SELECT MAX(protectID) from FallProtectionEntry)")
    LiveData<FallProtectionEntry> getLastFallProtection();

    @Update
    void updateFallProtection (FallProtectionEntry protect);

    @Query("DELETE FROM FallProtectionEntry WHERE protectID == :protectID")
    void deleteFallProtection(int protectID);

    @Query("DELETE FROM FallProtectionEntry WHERE circID == :circID")
    void deleteAllFallProtectCirc(int circID);
}
