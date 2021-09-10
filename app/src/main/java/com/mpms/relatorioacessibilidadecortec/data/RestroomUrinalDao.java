package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.RestroomUrinalEntry;

@Dao
public interface RestroomUrinalDao {

    @Insert
    void insertRestroomUrinalEntry(RestroomUrinalEntry urinalEntry);

    @Query("SELECT * FROM RestroomUrinalEntry WHERE restroomID == :restroomID")
    LiveData<RestroomUrinalEntry> getOneRestroomUrinalEntry(int restroomID);

    @Update
    void updateRestroomUrinalEntry(RestroomUrinalEntry urinalEntry);

    @Query("DELETE FROM RestroomUrinalEntry WHERE restroomID == :restroomID")
    void deleteOneRestroomUrinalEntry(int restroomID);

}
