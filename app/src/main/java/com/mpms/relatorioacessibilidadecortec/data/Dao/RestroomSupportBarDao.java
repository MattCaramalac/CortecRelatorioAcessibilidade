package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomSupportBarEntry;

@Dao
public interface RestroomSupportBarDao {

    @Insert
    void insertRestroomSupportBarEntry(RestroomSupportBarEntry supportBar);

    @Query("SELECT * FROM RestroomSupportBarEntry WHERE supBarID == :supBarID")
    LiveData<RestroomSupportBarEntry> getOneRestroomSupportBarEntry(int supBarID);

    @Query("SELECT * FROM RestroomSupportBarEntry WHERE restroomID == :restroomID")
    LiveData<RestroomSupportBarEntry> getRestSupportBarByRestroom(int restroomID);

    @Query("SELECT * FROM RestroomSupportBarEntry WHERE supBarID == (SELECT MAX(supBarID) FROM RestroomSupportBarEntry)")
    LiveData<RestroomSupportBarEntry> getLastRestroomSupportBarEntry();

    @Update
    void updateRestroomSupportBarEntry(RestroomSupportBarEntry supportBar);

    @Query("DELETE FROM RestroomSupportBarEntry WHERE restroomID == :restroomID")
    void deleteOneRestroomSupportBarEntry(int restroomID);
}
