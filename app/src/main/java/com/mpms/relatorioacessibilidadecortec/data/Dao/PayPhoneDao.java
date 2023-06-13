package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.common.util.concurrent.ListenableFuture;
import com.mpms.relatorioacessibilidadecortec.data.entities.PayPhoneEntry;

import java.util.List;

@Dao
public interface PayPhoneDao {

    @Insert
    void insertPayPhone(PayPhoneEntry payPhone);

    @Query("SELECT * FROM PayPhoneEntry WHERE extAccessID == :externalAccessID ORDER BY payPhoneID DESC")
    LiveData<List<PayPhoneEntry>> getPhonesExtAccess(int externalAccessID);

    @Query("SELECT * FROM PayPhoneEntry WHERE sidewalkID == :sidewalkID ORDER BY payPhoneID DESC")
    LiveData<List<PayPhoneEntry>> getPhonesSidewalk(int sidewalkID);

    @Query("SELECT * FROM PayPhoneEntry WHERE payPhoneID == :payPhoneID")
    LiveData<PayPhoneEntry> getPayPhoneEntry(int payPhoneID);

    @Query("SELECT * FROM PayPhoneEntry WHERE extAccessID IN (:externalAccessID)")
    LiveData<List<PayPhoneEntry>> getAllPhonesExtAccess(List<Integer> externalAccessID);

    @Query("SELECT * FROM PayPhoneEntry WHERE sidewalkID IN (:sidewalkID)")
    LiveData<List<PayPhoneEntry>> getAllPhonesSidewalk(List<Integer> sidewalkID);

    @Update
    void updatePayPhone(PayPhoneEntry payPhone);

    @Query("DELETE FROM PayPhoneEntry WHERE payPhoneID == :payPhoneID")
    void deletePayPhoneEntry(int payPhoneID);

    @Query("DELETE FROM PayPhoneEntry WHERE extAccessID == :externalAccessID")
    void deleteAllPayPhonesExtAccess(int externalAccessID);

    @Query("DELETE FROM PayPhoneEntry WHERE sidewalkID == :sidewalkID")
    void deleteAllPayPhonesSidewalk(int sidewalkID);

    //    Listenable
    @Query("SELECT * FROM PayPhoneEntry WHERE extAccessID IN (:externalAccessID)")
    ListenableFuture<List<PayPhoneEntry>> getListAllPhonesExtAccess(List<Integer> externalAccessID);

    @Query("SELECT * FROM PayPhoneEntry WHERE sidewalkID IN (:sidewalkID)")
    ListenableFuture<List<PayPhoneEntry>> getListAllPhonesSidewalk(List<Integer> sidewalkID);
}
