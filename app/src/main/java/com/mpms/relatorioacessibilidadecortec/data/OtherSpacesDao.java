package com.mpms.relatorioacessibilidadecortec.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.entities.OtherSpaces;

import java.util.List;

@Dao
public interface OtherSpacesDao {

    @Insert
    void insertOtherSpace(OtherSpaces otherSpaces);

    @Query("SELECT * FROM OtherSpaces WHERE blockID == :blockID ORDER BY otherSpacesID DESC")
    LiveData<List<OtherSpaces>> selectAllSpaces(int blockID);

    @Query("SELECT * FROM OtherSpaces WHERE otherSpacesID == :otherID")
    LiveData<OtherSpaces> selectOneSpace(int otherID);

    @Update
    void updateOtherSpace(OtherSpaces otherSpaces);

    @Query("DELETE FROM OtherSpaces WHERE otherSpacesID == :otherID")
    void deleteOneSpace(int otherID);

    @Query("DELETE FROM OtherSpaces WHERE blockID == :blockID")
    void deleteAllSpaces(int blockID);
}
