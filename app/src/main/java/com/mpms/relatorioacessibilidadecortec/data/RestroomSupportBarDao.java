package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.RestroomSupportBarEntry;

@Dao
public interface RestroomSupportBarDao {

    @Insert
    void insertRestroomSupportBarEntry(RestroomSupportBarEntry supportBar);

    @Query("SELECT * FROM RestroomSupportBarEntry WHERE restroomID == :restroomID")
    LiveData<RestroomSupportBarEntry> getOneRestroomSupportBarEntry(int restroomID);

    @Update
    void updateRestroomSupportBarEntry(RestroomSupportBarEntry supportBar);

    @Query("DELETE FROM RestroomSupportBarEntry WHERE restroomID == :restroomID")
    void deleteOneRestroomSupportBarEntry(int restroomID);
}
