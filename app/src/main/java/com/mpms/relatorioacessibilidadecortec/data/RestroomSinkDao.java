package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.RestroomSinkEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomSinkOne;
import com.mpms.relatorioacessibilidadecortec.entities.RestroomSinkTwo;

@Dao
public interface RestroomSinkDao {

    @Insert
    void insertRestroomSinkEntry(RestroomSinkEntry sinkEntry);

    @Query("SELECT * FROM RestroomSinkEntry WHERE restroomID == :restroomID")
    LiveData<RestroomSinkEntry> getOneRestroomSinkEntry(int restroomID);

    @Update
    void updateRestroomSinkEntry(RestroomSinkEntry sinkEntry);

    @Update(entity = RestroomSinkEntry.class)
    void updateSinkEntryOne(RestroomSinkOne... sinkOne);

    @Update(entity = RestroomSinkEntry.class)
    void updateSinkEntryTwo(RestroomSinkTwo... sinkTwo);

    @Query("DELETE FROM RestroomSinkEntry WHERE restroomID == :restroomID")
    void deleteOneRestroomSinkEntry(int restroomID);

}
