package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomUpViewEntry;

@Dao
public interface RestroomUpViewDao {

    @Insert
    void insertRestroomUpViewEntry(RestroomUpViewEntry upViewEntry);

    @Query("SELECT * FROM RestroomUpViewEntry WHERE upViewID == :upViewID")
    LiveData<RestroomUpViewEntry> getOneRestroomUpViewEntry(int upViewID);

    @Query("SELECT * FROM RestroomUpViewEntry WHERE restroomID == :restroomID")
    LiveData<RestroomUpViewEntry> getRestroomUpViewByRestroom(int restroomID);

    @Query("SELECT * FROM RestroomUpViewEntry WHERE restroomID == (SELECT MAX(restroomID) FROM RestroomUpViewEntry)")
    LiveData<RestroomUpViewEntry> getLastRestroomUpViewEntry();

    @Update
    void updateRestroomUpViewEntry(RestroomUpViewEntry upViewEntry);

    @Query("DELETE FROM RestroomUpViewEntry WHERE restroomID == :restroomID")
    void deleteRestroomUpViewEntry(int restroomID);

}
