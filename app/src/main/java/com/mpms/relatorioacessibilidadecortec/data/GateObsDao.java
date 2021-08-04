package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.GateObsEntry;

import java.util.List;

@Dao
public interface GateObsDao {

    @Insert
    void insertGateObs (GateObsEntry gateObs);

    @Query("SELECT * FROM GateObsEntry WHERE SchoolEntryID == :SchoolEntryID ORDER BY gateObsID DESC")
    LiveData<List<GateObsEntry>> selectAllGateObsEntries(int SchoolEntryID);

    @Query("SELECT * FROM GateObsEntry WHERE gateObsID == :gateObsID")
    LiveData<List<GateObsEntry>> selectGateObsEntry(int gateObsID);

    @Update
    void updateGateObs (GateObsEntry gateObs);

    @Query("DELETE FROM GateObsEntry WHERE SchoolEntryID == :SchoolEntryID AND gateObsID == :gateObsID")
    void deleteGateObsEntry(int SchoolEntryID, int gateObsID);

    @Query("DELETE FROM GateObsEntry WHERE SchoolEntryID == :SchoolEntryID")
    void deleteAllGateObsEntries(int SchoolEntryID);
}
