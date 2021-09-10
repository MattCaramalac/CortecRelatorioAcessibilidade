package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.RestroomUpViewEntry;

@Dao
public interface RestroomUpViewDao {

    @Insert
    void insertRestroomUpViewEntry(RestroomUpViewEntry upViewEntry);

    @Query("SELECT * FROM RestroomUpViewEntry WHERE restroomID == :restroomID")
    LiveData<RestroomUpViewEntry> getOneRestroomUpViewEntry(int restroomID);

    @Update
    void updateRestroomUpViewEntry(RestroomUpViewEntry upViewEntry);

    @Query("DELETE FROM RestroomUpViewEntry WHERE restroomID == :restroomID")
    void deleteRestroomUpViewEntry(int restroomID);

}
