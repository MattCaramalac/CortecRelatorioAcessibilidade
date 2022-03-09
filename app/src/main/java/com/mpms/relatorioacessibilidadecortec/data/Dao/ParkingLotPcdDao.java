package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotPCDEntry;

import java.util.List;

@Dao
public interface ParkingLotPcdDao {

    @Insert
    void insertPcdParkingLot(ParkingLotPCDEntry parkingLotPCDEntry);

    @Query("SELECT * FROM ParkingLotPCDEntry WHERE parkingLotID == :parkingLotID")
    LiveData<List<ParkingLotPCDEntry>> selectAllPcdParkingLot(int parkingLotID);

    @Query("SELECT * FROM ParkingLotPCDEntry WHERE parkingPcdID == :parkingPcdID")
    LiveData<ParkingLotPCDEntry> selectOnePcdParkingLot(int parkingPcdID);

    @Update
    void updatePcdParkingLot(ParkingLotPCDEntry parkingLotPCDEntry);

    @Query("DELETE FROM ParkingLotPCDEntry WHERE parkingLotID == :parkingLotID")
    void deleteOnePcdParkingLot(int parkingLotID);

}
