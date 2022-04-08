package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.PayPhoneEntry;

import java.util.List;

@Dao
public interface PayPhoneDao {

    @Insert
    void insertPayPhone (PayPhoneEntry payPhone);

    @Query("SELECT * FROM PayPhoneEntry WHERE extAccessID == :externalAccessID ORDER BY payPhoneID DESC")
    LiveData<List<PayPhoneEntry>> selectAllPhonesExtAccess(int externalAccessID);

    @Query("SELECT * FROM PayPhoneEntry WHERE sidewalkID == :sidewalkID ORDER BY payPhoneID DESC")
    LiveData<List<PayPhoneEntry>> selectAllPhonesSidewalk(int sidewalkID);

    @Query("SELECT * FROM PayPhoneEntry WHERE payPhoneID == :payPhoneID")
    LiveData<PayPhoneEntry> selectPayPhoneEntry(int payPhoneID);

    @Update
    void updatePayPhone (PayPhoneEntry payPhone);

    @Query("DELETE FROM PayPhoneEntry WHERE payPhoneID == :payPhoneID")
    void deletePayPhoneEntry(int payPhoneID);

    @Query("DELETE FROM PayPhoneEntry WHERE extAccessID == :externalAccessID")
    void deleteAllPayPhonesExtAccess(int externalAccessID);

    @Query("DELETE FROM PayPhoneEntry WHERE sidewalkID == :sidewalkID")
    void deleteAllPayPhonesSidewalk(int sidewalkID);
}
