package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.RestroomMirrorEntry;

@Dao
public interface RestroomMirrorDao {

    @Insert
    void insertRestroomMirrorEntry(RestroomMirrorEntry restroom);

    @Query("SELECT * FROM RestroomMirrorEntry WHERE restroomID == :restroomID")
    LiveData<RestroomMirrorEntry> getOneRestroomMirrorEntry(int restroomID);

    @Update
    void updateRestroomMirrorEntry(RestroomMirrorEntry RestroomMirrorEntry);

    @Query("DELETE FROM RestroomMirrorEntry WHERE restroomID == :restroomID")
    void deleteOneRestroomMirrorEntry(int restroomID);

}
