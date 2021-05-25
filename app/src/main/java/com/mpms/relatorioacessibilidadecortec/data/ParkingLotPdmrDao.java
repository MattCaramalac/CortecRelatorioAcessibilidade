package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotPDMREntry;

import java.util.List;

@Dao
public interface ParkingLotPdmrDao {

    @Insert
    void insertPdmrParkingLot(ParkingLotPDMREntry parkingLotPDMREntry);

    @Query("SELECT * FROM ParkingLotPDMREntry WHERE parkingLotID == :parkingLotID")
    LiveData<ParkingLotPDMREntry> selectPdmrParkingLot(int parkingLotID);

    @Update
    void updatePdmrParkingLot(ParkingLotPDMREntry parkingLotPDMREntry);

    @Query("DELETE FROM ParkingLotPDMREntry WHERE parkingLotID == :parkingLotID")
    void deleteOnePdmrParkingLot(int parkingLotID);

}
