package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.GateObsEntry;

import java.util.List;

@Dao
public interface GateObsDao {

    @Insert
    void insertGateObs (GateObsEntry gateObs);

    @Query("SELECT * FROM GateObsEntry WHERE externalAccessID == :externalAccessID ORDER BY gateObsID DESC")
    LiveData<List<GateObsEntry>> selectAllGateObsEntries(int externalAccessID);

    @Query("SELECT * FROM GateObsEntry WHERE gateObsID == :gateObsID")
    LiveData<GateObsEntry> selectGateObsEntry(int gateObsID);

    @Update
    void updateGateObs (GateObsEntry gateObs);

    @Query("DELETE FROM GateObsEntry WHERE gateObsID == :gateObsID")
    void deleteGateObsEntry(int gateObsID);

    @Query("DELETE FROM GateObsEntry WHERE externalAccessID == :externalAccessID")
    void deleteAllGateObsEntries(int externalAccessID);
}
