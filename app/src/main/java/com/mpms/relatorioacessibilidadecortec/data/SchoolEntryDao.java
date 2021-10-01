package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolRegisterOne;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolRegisterThree;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolRegisterTwo;

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
    void deleteOne(SchoolEntry schoolEntry);

    @Query("DELETE FROM SchoolEntry")
    void deleteAll();
}
