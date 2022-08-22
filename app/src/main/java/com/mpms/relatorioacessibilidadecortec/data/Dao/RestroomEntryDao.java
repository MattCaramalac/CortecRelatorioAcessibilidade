package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mpms.relatorioacessibilidadecortec.data.entities.RestAccessUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestDoorUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestEntryUpdate;
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

    @Query("SELECT restroomID, blockID, restType, restLocation FROM RestroomEntry WHERE blockID == :blockID ORDER BY restroomID DESC")
    LiveData<List<RestroomEntry>> getAllRestEntriesInBlock(int blockID);

    @Query("SELECT restroomID, blockID, restType, restLocation, accessRoute, accessRouteObs, intRestroom, intRestObs, restDistance, restDistObs, exEntrance," +
            "exEntObs, antiDriftFloor, antiDriftFloorObs, restDrain, restDrainObs, restSwitch, switchHeight, switchObs FROM RestroomEntry " +
            "WHERE restroomID = :restID")
    LiveData<RestroomEntry> getRestFirstData(int restID);

    @Query("SELECT restroomID, blockID, doorWidth, hasPict, pictObs, opDir, opDirObs, hasCoat, coatHeight, coatObs, hasVertSign, vertSignObs, sillType, " +
            "sillIncHeight, sillStepHeight, sillSlopeQnt, sillSlopeAngle1, sillSlopeAngle2, sillSlopeAngle3, sillSlopeAngle4, sillSlopeWidth, sillTypeObs," +
            "hasTactSign, tactSignObs, hasHorHandle, handleHeight, handleLength, handleDiam, handleObs FROM RestroomEntry WHERE restroomID = :restID")
    LiveData<RestroomEntry> getRestDoorData(int restID);

    @Query("SELECT restroomID, blockID, upViewLength, upViewWidth, upViewMeasureA, upViewMeasureB, upViewMeasureC, upViewMeasureD, upViewMeasureD, upViewMeasureE " +
            "FROM RestroomEntry WHERE restroomID = :restID")
    LiveData<RestroomEntry> getRestUpViewData(int restID);

    @Query("SELECT restroomID, blockID, toType, toHeightNoSeat, toHasSeat, toHeightSeat, toHasSoculo, frSoculo, latSoculo, socCorners, toHasFrontBar, frBarA, frBarB, " +
            "frBarC, frBarSect,frBarDist, toHasWall, hasHorBar, horBarD, horBarE, horBarF, horBarDistG, horBarSect, horBarDist, hasVertBar, vertBarH, vertBarI, " +
            "vertBarJ, vertBarSect, vertBarDist, hasArtBar, artBarH, artBarI, artBarJ, artBarSect, toActDesc, toActHeight, toActObs, hasPapHolder, papHolderType," +
            "papEmbDist, papEmbHeight, papSupAlign, papSupHeight, papHoldObs, hasDouche, doucheHeight, doucheObs, toiletObs FROM RestroomEntry WHERE restroomID = :restID")
    LiveData<RestroomEntry> getRestToiletData(int restID);

    @Query("SELECT restroomID, blockID, hasHanger, hangerHeight, hangerObs, hasObjHold, objHoldCorrect, objHoldHeight, objHoldObs, hasSoapHold, soapHoldHeight, soapHoldObs," +
            "hasTowelHold, towelHoldHeight, towelHoldObs, hasWallMirror, wallMirrorLow, wallMirrorHigh, wallMirrorObs FROM RestroomEntry WHERE restroomID = :restID")
    LiveData<RestroomEntry> getRestAccessData(int restID);

    @Query("SELECT restroomID, blockID, sinkType, approxMeasureA, approxMeasureB, approxMeasureC, approxMeasureD, approxMeasureE, hasSinkBar, hasLeftBar, leftHorMeasureA," +
            "leftHorMeasureB, leftHorMeasureC, leftHorMeasureD, leftVertMeasureA, leftVertMeasureB, leftVertMeasureC, leftVertMeasureD, leftVertMeasureE," +
            "leftBarDiam, leftBarDist, hasRightBar, rightHorMeasureA, rightHorMeasureB, rightHorMeasureC, rightHorMeasureD, rightVertMeasureA, rightBarDiam," +
            "rightBarDist, sinkHasMirror, siMirrorLow, siMirrorHigh, sinkObs FROM RestroomEntry WHERE restroomID = :restID")
    LiveData<RestroomEntry> getRestSinkData(int restID);

    @Query("SELECT restroomID, blockID, hasUrinal, hasAccessUrinal, urinalType, urMeasureA, urMeasureB, urMeasureC, urMeasureD, urMeasureE, urMeasureF, urMeasureG, " +
            "urMeasureH, urMeasureI, urMeasureJ, urMeasureK, urMeasureL, urMeasureM, urObs FROM RestroomEntry WHERE restroomID = :restID")
    LiveData<RestroomEntry> getRestUrinalData(int restID);

//TODO - Fazer select para Box quando colocar os campos de banheiro


    @Query("SELECT * FROM RestroomEntry WHERE restroomID == :restroomID")
    LiveData<RestroomEntry> getOneRestroomEntry(int restroomID);

    @Query("SELECT * FROM RestroomEntry WHERE restroomID == (SELECT MAX(restroomID) from RestroomEntry)")
    LiveData<RestroomEntry> getLastRestroomEntry();

    @Update
    void updateRestroomEntry(RestroomEntry RestroomEntry);

    @Update(entity = RestroomEntry.class)
    void updateRestroomData(RestEntryUpdate... restEntryUpdates);

    @Update(entity = RestroomEntry.class)
    void updateRestDoorData(RestDoorUpdate... doorUpdates);

    @Update(entity = RestroomEntry.class)
    void updateRestUpViewData(RestUpViewUpdate... upViewUpdates);

    @Update(entity = RestroomEntry.class)
    void updateRestToiletData(RestToiletUpdate... barUpdates);

    @Update(entity = RestroomEntry.class)
    void updateRestAccessData(RestAccessUpdate... accUpdates);

    @Update(entity = RestroomEntry.class)
    void updateRestSinkData(RestSinkUpdate... sinkUpdates);

    @Update(entity = RestroomEntry.class)
    void updateRestUrinalData(RestUrinalUpdate... urUpdates);

    @Query("DELETE FROM RestroomEntry WHERE restroomID == :restroomID")
    void deleteOneRestroomEntry(int restroomID);
}
