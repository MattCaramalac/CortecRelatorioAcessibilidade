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

    @Query("SELECT * FROM ParkingLotPCDEntry WHERE parkID == :parkingLotID")
    LiveData<List<ParkingLotPCDEntry>> getPcdVacanciesPark(int parkingLotID);

    @Query("SELECT * FROM ParkingLotPCDEntry WHERE parkID IN (:parkingLotID)")
    LiveData<List<ParkingLotPCDEntry>> getAllPcdVacancies(List<Integer> parkingLotID);

    @Query("SELECT * FROM ParkingLotPCDEntry WHERE parkPcdID == :parkingPcdID")
    LiveData<ParkingLotPCDEntry> getOnePcdVacancy(int parkingPcdID);

    @Update
    void updatePcdParkingLot(ParkingLotPCDEntry parkingLotPCDEntry);

    @Query("DELETE FROM ParkingLotPCDEntry WHERE parkPcdID == :parkingLotID")
    void deleteOnePcdParkingLot(int parkingLotID);

}
