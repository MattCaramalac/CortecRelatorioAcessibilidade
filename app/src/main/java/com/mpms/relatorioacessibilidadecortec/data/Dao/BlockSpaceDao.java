package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.BlockSpaceEntry;

import java.util.List;

@Dao
public interface BlockSpaceDao {

    @Insert
    void insertBlockSpace(BlockSpaceEntry blockSpace);

    @Query("SELECT * FROM BlockSpaceEntry WHERE schoolID == :schoolID AND blockSpaceType = 0 ORDER BY blockSpaceType ASC")
    LiveData<List<BlockSpaceEntry>> getBlockSpaceFromSchool(int schoolID);

    @Query("SELECT * FROM BlockSpaceEntry WHERE schoolID == :schoolID ORDER BY blockSpaceType ASC")
    LiveData<List<BlockSpaceEntry>> getAllBlocksSchool(int schoolID);

    @Query("SELECT blockSpaceID FROM BlockSpaceEntry WHERE schoolID == :schoolID ORDER BY blockSpaceType ASC")
    LiveData<List<Integer>> getAllIds(int schoolID);

    @Query("SELECT * FROM BlockSpaceEntry WHERE blockSpaceID == :blockSpaceID")
    LiveData<BlockSpaceEntry> getSpecificBlockSpace(int blockSpaceID);

    @Query("SELECT * FROM BlockSpaceEntry WHERE schoolID == :schoolID AND blockSpaceType == :areaType")
    LiveData<BlockSpaceEntry> getAreaFromSchool(int schoolID, int areaType);

    @Query("SELECT * FROM BlockSpaceEntry WHERE schoolID == :schoolID " +
            " AND blockSpaceNumber == (SELECT MAX(blockSpaceNumber) from BlockSpaceEntry WHERE blockSpaceType == :blockSpaceType)")
    LiveData<BlockSpaceEntry> getLastBlockSpace(int schoolID, int blockSpaceType);

    @Update
    void updateBlockSpace (BlockSpaceEntry blockSpace);

    @Query("DELETE FROM BlockSpaceEntry WHERE blockSpaceID == :blockSpaceID")
    void deleteBlockSpace(int blockSpaceID);

    @Query("DELETE FROM BlockSpaceEntry WHERE schoolID == :schoolID")
    void deleteAllBlockSpacesSchool(int schoolID);


}
