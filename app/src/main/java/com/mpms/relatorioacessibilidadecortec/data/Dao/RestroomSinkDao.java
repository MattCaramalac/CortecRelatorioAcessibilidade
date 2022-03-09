package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomSinkEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomSinkOne;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomSinkTwo;

@Dao
public interface RestroomSinkDao {

    @Insert
    void insertRestroomSinkEntry(RestroomSinkEntry sinkEntry);

    @Query("SELECT * FROM RestroomSinkEntry WHERE sinkID == :sinkID")
    LiveData<RestroomSinkEntry> getOneRestroomSinkEntry(int sinkID);

    @Query("SELECT * FROM RestroomSinkEntry WHERE restroomID == :restroomID")
    LiveData<RestroomSinkEntry> getRestroomSinkByRestroom(int restroomID);

    @Query("SELECT * FROM RestroomSinkEntry WHERE sinkID == (SELECT MAX (sinkID) FROM RestroomSinkEntry)")
    LiveData<RestroomSinkEntry> getLastRestroomSinkEntry();

    @Update
    void updateRestroomSinkEntry(RestroomSinkEntry sinkEntry);

    @Update(entity = RestroomSinkEntry.class)
    void updateSinkEntryOne(RestroomSinkOne... sinkOne);

    @Update(entity = RestroomSinkEntry.class)
    void updateSinkEntryTwo(RestroomSinkTwo... sinkTwo);

    @Query("DELETE FROM RestroomSinkEntry WHERE restroomID == :restroomID")
    void deleteOneRestroomSinkEntry(int restroomID);

}
