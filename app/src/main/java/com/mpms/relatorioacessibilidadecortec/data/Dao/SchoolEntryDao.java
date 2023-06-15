package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.common.util.concurrent.ListenableFuture;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolRegisterOne;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolRegisterThree;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolRegisterTwo;

import java.util.List;

@Dao
public interface SchoolEntryDao {

    @Insert
    void insertEntry(SchoolEntry schoolEntry);

    @Query("SELECT * FROM SchoolEntry ORDER BY cadID DESC")
    LiveData<List<SchoolEntry>> getAllEntries();

    @Query("SELECT * FROM SchoolEntry WHERE SchoolEntry.cadID == :cadID")
    LiveData<SchoolEntry> getEntry(int cadID);

    @Query("SELECT * FROM SchoolEntry WHERE cadID == (SELECT MAX(cadID) from SchoolEntry)")
    LiveData<SchoolEntry> getLastEntry();

    @Update
    void update(SchoolEntry schoolEntry);

    @Update(entity = SchoolEntry.class)
    void updateSchoolRegOne(SchoolRegisterOne... regOne);

    @Update(entity = SchoolEntry.class)
    void updateSchoolRegTwo(SchoolRegisterTwo... regTwo);

    @Update(entity = SchoolEntry.class)
    void updateSchoolRegThree(SchoolRegisterThree... regThree);

    @Delete
    void deleteOneSchoolEntry(SchoolEntry schoolEntry);

    @Query("DELETE FROM SchoolEntry WHERE cadID == :schID")
    void deleteSchoolWithID(int schID);

    @Query("DELETE FROM SchoolEntry")
    void deleteAll();

    @Query("UPDATE SchoolEntry SET reportSent = 1 WHERE cadID == :schoolID")
    void updateReportSent(int schoolID);

    //    Listenable
    @Query("SELECT * FROM SchoolEntry WHERE cadID == :cadID")
    ListenableFuture<SchoolEntry> getListenableSchool(int cadID);


}
