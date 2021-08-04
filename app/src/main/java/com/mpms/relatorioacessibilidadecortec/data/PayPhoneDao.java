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

    @Query("SELECT * FROM PayPhoneEntry WHERE externalAccessID == :externalAccessID ORDER BY payPhoneID DESC")
    LiveData<List<PayPhoneEntry>> selectAllPayPhones(int externalAccessID);

    @Query("SELECT * FROM PayPhoneEntry WHERE payPhoneID == :payPhoneID")
    LiveData<List<PayPhoneEntry>> selectPayPhoneEntry(int payPhoneID);

    @Update
    void updatePayPhone (PayPhoneEntry payPhone);

    @Query("DELETE FROM PayPhoneEntry WHERE payPhoneID == :payPhoneID")
    void deletePayPhoneEntry(int payPhoneID);

    @Query("DELETE FROM PayPhoneEntry WHERE externalAccessID == :externalAccessID")
    void deleteAllPayPhones(int externalAccessID);
}
