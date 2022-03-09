package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotElderlyEntry;

import java.util.List;

@Dao
public interface ParkingLotElderlyDao {

    @Insert
    void insertElderlyParkingLot(ParkingLotElderlyEntry elderlyEntry);

    @Query("SELECT * FROM ParkingLotElderlyEntry WHERE parkingLotID == :parkingLotID")
    LiveData<List<ParkingLotElderlyEntry>> selectAllElderlyParkingLot(int parkingLotID);

    @Query("SELECT * FROM ParkingLotElderlyEntry WHERE parkingElderlyID == :parkingElderlyID")
    LiveData<ParkingLotElderlyEntry> selectOneElderlyParkingLot(int parkingElderlyID);

    @Update
    void updateElderlyParkingLot(ParkingLotElderlyEntry parkingLotElderlyEntry);

    @Query("DELETE FROM ParkingLotElderlyEntry WHERE parkingLotID == :parkingLotID")
    void deleteOneElderlyParkingLot(int parkingLotID);
}
