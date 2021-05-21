package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotEntry;

import java.util.List;

@Dao
public interface ParkingLotEntryDao {

    @Insert
    void insertParkingLot(ParkingLotEntry parkingLotEntry);

    @Query("SELECT * FROM ParkingLotEntry WHERE schoolEntryID == :schoolEntryID ORDER BY parkingLotID DESC")
    LiveData<List<ParkingLotEntry>> selectEveryParkingLot(int schoolEntryID);

    @Query("SELECT * FROM ParkingLotEntry WHERE parkingLotID == :parkingLotID")
    LiveData<ParkingLotEntry> selectOneParkingLot(int parkingLotID);

    @Query("SELECT * FROM ParkingLotEntry WHERE parkingLotID == (SELECT MAX(parkingLotID) from ParkingLotEntry)")
    LiveData<ParkingLotEntry> selectLastInsertedParkingLot();

    @Update
    void updateParkingLot(ParkingLotEntry parkingLotEntry);

    @Query("DELETE FROM ParkingLotEntry WHERE parkingLotID == :parkingLotID")
    void deleteOneParkingLot(int parkingLotID);

    @Query("DELETE FROM ParkingLotEntry WHERE schoolEntryID == :schoolID")
    void deleteAllParkingLots(int schoolID);
}
