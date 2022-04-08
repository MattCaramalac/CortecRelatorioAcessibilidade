package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.ExtAccessSocialOne;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExtAccessSocialThree;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExtAccessSocialTwo;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;

import java.util.List;

@Dao
public interface ExternalAccessDao {

    @Insert
    void insertExternalAccess(ExternalAccess externalAccess);

    @Query("SELECT * FROM ExternalAccess WHERE blockID == :blockID ORDER BY externalAccessID DESC")
    LiveData<List<ExternalAccess>> getAllSchoolExternalAccesses(int blockID);

    @Query("SELECT * FROM ExternalAccess WHERE externalAccessID == :externalAccessID")
    LiveData<ExternalAccess> getOneExternalAccess(int externalAccessID);

    @Query("SELECT * FROM ExternalAccess WHERE externalAccessID == (SELECT MAX(externalAccessID) from ExternalAccess)")
    LiveData<ExternalAccess> getLastExternalAccess();

    @Update
    void updateExternalAccess(ExternalAccess externalAccess);

    @Update(entity = ExternalAccess.class)
    void updateExtAccessRegOne(ExtAccessSocialOne... regOne);

    @Update(entity = ExternalAccess.class)
    void updateExtAccessRegTwo(ExtAccessSocialTwo... regTwo);

    @Update(entity = ExternalAccess.class)
    void updateExtAccessRegThree(ExtAccessSocialThree... regThree);

    @Query("DELETE FROM ExternalAccess WHERE externalAccessID == :externalAccessID")
    void deleteOneExternalAccess(int externalAccessID);

    @Query("DELETE FROM ExternalAccess WHERE blockID == :blockID")
    void deleteAllExternalAccessesFromSchool(int blockID);
}
