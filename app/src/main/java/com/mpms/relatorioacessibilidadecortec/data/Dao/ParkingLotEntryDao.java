package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotEntry;

import java.util.List;

@Dao
public interface ParkingLotEntryDao {

    @Insert
    void insertParkingLot(ParkingLotEntry parkingLotEntry);

    @Query("SELECT * FROM ParkingLotEntry WHERE blockID == :blockID ORDER BY parkingLotID DESC")
    LiveData<List<ParkingLotEntry>> selectEveryParkingLot(int blockID);

    @Query("SELECT * FROM ParkingLotEntry WHERE parkingLotID == :parkingLotID")
    LiveData<ParkingLotEntry> selectOneParkingLot(int parkingLotID);

    @Query("SELECT * FROM ParkingLotEntry WHERE parkingLotID == (SELECT MAX(parkingLotID) from ParkingLotEntry)")
    LiveData<ParkingLotEntry> selectLastInsertedParkingLot();

    @Update
    void updateParkingLot(ParkingLotEntry parkingLotEntry);

    @Query("DELETE FROM ParkingLotEntry WHERE parkingLotID == :parkingLotID")
    void deleteOneParkingLot(int parkingLotID);

    @Query("DELETE FROM ParkingLotEntry WHERE blockID == :blockID")
    void deleteAllParkingLots(int blockID);
}
