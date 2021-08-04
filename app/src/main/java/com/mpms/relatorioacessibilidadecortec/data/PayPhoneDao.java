package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.PayPhoneEntry;

import java.util.List;

@Dao
public interface PayPhoneDao {

    @Insert
    void insertPayPhone (PayPhoneEntry payPhone);

    @Query("SELECT * FROM PayPhoneEntry WHERE SchoolEntryID == :SchoolEntryID ORDER BY payPhoneID DESC")
    LiveData<List<PayPhoneEntry>> selectAllPayPhones(int SchoolEntryID);

    @Query("SELECT * FROM PayPhoneEntry WHERE payPhoneID == :payPhoneID")
    LiveData<List<PayPhoneEntry>> selectPayPhoneEntry(int payPhoneID);

    @Update
    void updatePayPhone (PayPhoneEntry payPhone);

    @Query("DELETE FROM PayPhoneEntry WHERE SchoolEntryID == :SchoolEntryID AND payPhoneID == :payPhoneID")
    void deletePayPhoneEntry(int SchoolEntryID, int payPhoneID);

    @Query("DELETE FROM PayPhoneEntry WHERE SchoolEntryID == :SchoolEntryID")
    void deleteAllPayPhones(int SchoolEntryID);
}
