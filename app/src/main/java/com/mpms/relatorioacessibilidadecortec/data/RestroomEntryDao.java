package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.RestroomDoorUpdate;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomUpdate;

import java.util.List;

@Dao
public interface RestroomEntryDao {

    @Insert
    void insertRestroomEntry(RestroomEntry restroom);

    @Query("SELECT * FROM RestroomEntry WHERE schoolID == :schoolID ORDER BY restroomID DESC")
    LiveData<List<RestroomEntry>> getAllSchoolRestroomEntries(int schoolID);

    @Query("SELECT * FROM RestroomEntry WHERE restroomID == :restroomID")
    LiveData<RestroomEntry> getOneRestroomEntry(int restroomID);

    @Query("SELECT * FROM RestroomEntry WHERE restroomID == (SELECT MAX(restroomID) from RestroomEntry)")
    LiveData<RestroomEntry> getLastRestroomEntry();

    @Update
    void updateRestroomEntry(RestroomEntry RestroomEntry);

    @Update(entity = RestroomEntry.class)
    void updateRestroomData(RestroomUpdate...restroomUpdates);

    @Update(entity = RestroomEntry.class)
    void updateRestroomDoorData(RestroomDoorUpdate... doorUpdates);

    @Query("DELETE FROM RestroomEntry WHERE restroomID == :restroomID")
    void deleteOneRestroomEntry(int restroomID);

    @Query("DELETE FROM RestroomEntry WHERE schoolID == :schoolID")
    void deleteAllRestroomEntriesFromSchool(int schoolID);
}
