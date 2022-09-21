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

    @Query("SELECT * FROM ParkingLotEntry WHERE blockID == :blockID ORDER BY parkingID DESC")
    LiveData<List<ParkingLotEntry>> getParkingLotFromBlock(int blockID);

    @Query("SELECT * FROM ParkingLotEntry WHERE blockID == :blockID AND sidewalkID == :sideID ORDER BY parkingID DESC")
    LiveData<List<ParkingLotEntry>> getParkingLotFromSide(int blockID, int sideID);

    @Query("SELECT * FROM ParkingLotEntry WHERE parkingID == :parkingLotID")
    LiveData<ParkingLotEntry> selectOneParkingLot(int parkingLotID);

    @Query("SELECT * FROM ParkingLotEntry WHERE parkingID == (SELECT MAX(parkingID) from ParkingLotEntry)")
    LiveData<ParkingLotEntry> selectLastInsertedParkingLot();

    @Query("SELECT * FROM ParkingLotEntry WHERE blockID IN (:blockID)")
    LiveData<List<ParkingLotEntry>> getAllParkingLots(List<Integer> blockID);

    @Update
    void updateParkingLot(ParkingLotEntry parkingLotEntry);

    @Query("DELETE FROM ParkingLotEntry WHERE parkingID == :parkingLotID")
    void deleteOneParkingLot(int parkingLotID);

    @Query("DELETE FROM ParkingLotEntry WHERE blockID == :blockID")
    void deleteAllParkingLots(int blockID);
}
