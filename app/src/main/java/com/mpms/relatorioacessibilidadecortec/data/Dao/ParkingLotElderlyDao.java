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

    @Query("SELECT * FROM ParkingLotElderlyEntry WHERE parkID == :parkingLotID")
    LiveData<List<ParkingLotElderlyEntry>> getElderVacanciesPark(int parkingLotID);

    @Query("SELECT * FROM ParkingLotElderlyEntry WHERE parkID IN (:parkingLotID)")
    LiveData<List<ParkingLotElderlyEntry>> getAllElderVacancies(List<Integer> parkingLotID);

    @Query("SELECT * FROM ParkingLotElderlyEntry WHERE parkElderID == :parkingElderlyID")
    LiveData<ParkingLotElderlyEntry> getOneElderVacancy(int parkingElderlyID);

    @Update
    void updateElderlyParkingLot(ParkingLotElderlyEntry parkingLotElderlyEntry);

    @Query("DELETE FROM ParkingLotElderlyEntry WHERE parkElderID == :parkElderID")
    void deleteOneElderlyParkingLot(int parkElderID);
}
