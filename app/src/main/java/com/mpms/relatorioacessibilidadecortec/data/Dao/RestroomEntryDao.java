package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.RestAccessEntranceUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestAccessUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestAccessUpdateTwo;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestColFirstUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestSinkUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestToiletUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestUpViewUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestUrinalUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;

import java.util.List;

@Dao
public interface RestroomEntryDao {

    @Insert
    void insertRestroomEntry(RestroomEntry restroom);

    @Query("SELECT restroomID, restType, blockID, restGender, restLocation FROM RestroomEntry WHERE blockID == :blockID ORDER BY restroomID DESC")
    LiveData<List<RestroomEntry>> getAllRestEntriesInBlock(int blockID);

    @Query("SELECT * FROM RestroomEntry WHERE blockID IN (:blockID)")
    LiveData<List<RestroomEntry>> getAllRestEntries(List<Integer> blockID);

    @Query("SELECT restroomID, blockID, restType, restGender, restLocation, collectiveHasDoor, entranceWidth, entranceDoorSill, entranceDoorSillObs, " +
            "accessRoute, accessRouteObs, intRestroom, intRestObs, antiDriftFloor, antiDriftFloorObs, upViewLength, upViewWidth," +
            "restDrain, restDrainObs, restSwitch, switchHeight, hasWindow, winQnt, switchObs, winComType1, winComHeight1, winComType2, winComHeight2, winComType3, " +
            "winComHeight3 FROM RestroomEntry WHERE restroomID = :restID")
    LiveData<RestroomEntry> getRestFirstData(int restID);

    @Query("SELECT restroomID, blockID, restType, collectiveHasDoor, entranceWidth, entranceDoorSill, entranceDoorSillObs FROM RestroomEntry WHERE restroomID = :restID")
    LiveData<RestroomEntry> getRestColDoorData(int restID);

    @Query("SELECT restroomID, blockID, restType, upViewLength, upViewWidth, upViewMeasureA, upViewMeasureB, upViewMeasureC, upViewMeasureD, upViewMeasureD, upViewObs" +
            " FROM RestroomEntry WHERE restroomID = :restID")
    LiveData<RestroomEntry> getRestUpViewData(int restID);

    @Query("SELECT restroomID, blockID, restType, toType, toHeightNoSeat, toHasSeat, toHeightSeat, toHasSoculo, frSoculo, latSoculo, socCorners, toHasFrontBar, frBarA, frBarB, frBarC, frBarSect, " +
            "frBarDist, toHasWall, hasHorBar, horBarD, horBarE, horBarF, horBarDistG, horBarSect, horBarDist, hasVertBar, vertBarH, vertBarI, vertBarJ, vertBarSect, vertBarDist, hasSideBar, " +
            "sideBarD, sideBarE, sideBarDistG, sideBarSect, hasArtBar, artBarH, artBarI, artBarJ, artBarSect, toActDesc, toActHeight, toActObs, hasPapHolder, papHolderType, papEmbDist, " +
            "papEmbHeight, papSupAlign, papSupHeight, papHoldObs, hasDouche, douchePressHeight, doucheActHeight, doucheObs, toiletObs FROM RestroomEntry WHERE restroomID = :restID")
    LiveData<RestroomEntry> getRestToiletData(int restID);

    @Query("SELECT restroomID, blockID, restType, hasHanger, hangerHeight, hangerObs, hasObjHold, objHoldCorrect, objHoldHeight, objHoldObs, hasSoapHold, soapHoldHeight, soapHoldObs," +
            "hasTowelHold, towelHoldHeight, towelHoldObs FROM RestroomEntry WHERE restroomID = :restID")
    LiveData<RestroomEntry> getRestAccessData(int restID);

    @Query("SELECT restroomID, blockID, restType, hasEmergencyButton, emergencyHeight, emergencyObs, hasWaterValve, waterValveType, waterValveHeight, waterValveObs, hasWindow, winQnt, winComType1, " +
            "winComHeight1, winComType2,winComHeight2, winComType3, winComHeight3, hasWallMirror, wallMirrorLow, wallMirrorHigh, wallMirrorObs FROM RestroomEntry WHERE restroomID = :restID")
    LiveData<RestroomEntry> getRestAccessDataTwo(int restID);

    @Query("SELECT restroomID,  blockID, restType, hasSink, sinkType, approxMeasureA, approxMeasureB, approxMeasureC, approxMeasureD, approxMeasureE, hasSinkBar, hasLeftFrontHorBar, leftFrontHorMeasureA, leftFrontHorMeasureB, " +
            "leftFrontHorMeasureC, leftFrontHorMeasureD, leftFrontHorDiam, leftFrontHorDist, leftFrontHorObs, hasRightSideVertBar, rightSideVertMeasureA, rightSideVertMeasureB, rightSideVertMeasureC, " +
            "rightSideVertMeasureD, rightSideVertMeasureE, rightSideVertDiam, rightSideVertDist, rightSideVertObs, sinkHasMirror, sinkMirrorLow, sinkMirrorHigh, sinkObs, hasLowerColSink " +
            "FROM RestroomEntry WHERE restroomID = :restID")
    LiveData<RestroomEntry> getRestSinkData(int restID);

    @Query("SELECT restroomID,  blockID, restType, approxMeasureB, approxMeasureC, hasSinkBar, " +
            "sinkObs, hasLowerSink " +
            "FROM RestroomEntry WHERE restroomID = :restID")
    LiveData<RestroomEntry> getRestCounterSinkData(int restID);

    @Query("SELECT restroomID, blockID, hasUrinal, restType, hasAccessUrinal, urinalType, urMeasureA, urMeasureB, urMeasureC, urMeasureD, urMeasureE, urMeasureF, urMeasureG, " +
            "urMeasureH, urMeasureI, urMeasureJ, urMeasureK, urMeasureL, urMeasureM, urObs FROM RestroomEntry WHERE restroomID = :restID")
    LiveData<RestroomEntry> getRestUrinalData(int restID);


    @Query("SELECT * FROM RestroomEntry WHERE restroomID == :restroomID")
    LiveData<RestroomEntry> getOneRestroomEntry(int restroomID);

    @Query("SELECT * FROM RestroomEntry WHERE restroomID == (SELECT MAX(restroomID) from RestroomEntry)")
    LiveData<RestroomEntry> getLastRestroomEntry();

    @Update
    void updateRestroomEntry(RestroomEntry RestroomEntry);

    @Update(entity = RestroomEntry.class)
    void updateRestroomData(RestColFirstUpdate... restColFirstUpdates);

    @Update(entity = RestroomEntry.class)
    void updateAccessRestData(RestAccessEntranceUpdate... restAccessUpdates);

    @Update(entity = RestroomEntry.class)
    void updateRestUpViewData(RestUpViewUpdate... upViewUpdates);

    @Update(entity = RestroomEntry.class)
    void updateRestToiletData(RestToiletUpdate... barUpdates);

    @Update(entity = RestroomEntry.class)
    void updateRestAccessData(RestAccessUpdate... accUpdates);

    @Update(entity = RestroomEntry.class)
    void updateRestAccessDataTwo(RestAccessUpdateTwo... accUpdatesTwo);

    @Update(entity = RestroomEntry.class)
    void updateRestSinkData(RestSinkUpdate... sinkUpdates);

    @Update(entity = RestroomEntry.class)
    void updateRestUrinalData(RestUrinalUpdate... urUpdates);

    @Query("DELETE FROM RestroomEntry WHERE restroomID == :restroomID")
    void deleteOneRestroomEntry(int restroomID);
}
