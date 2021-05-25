package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotElderlyEntry;

@Dao
public interface ParkingLotElderlyDao {

    @Insert
    void insertElderlyParkingLot(ParkingLotElderlyEntry elderlyEntry);

    @Query("SELECT * FROM ParkingLotElderlyEntry WHERE parkingLotID == :parkingLotID")
    LiveData<ParkingLotElderlyEntry> selectElderlyParkingLot(int parkingLotID);

    @Update
    void updateElderlyParkingLot(ParkingLotElderlyEntry parkingLotElderlyEntry);

    @Query("DELETE FROM ParkingLotElderlyEntry WHERE parkingLotID == :parkingLotID")
    void deleteOneElderlyParkingLot(int parkingLotID);
}
