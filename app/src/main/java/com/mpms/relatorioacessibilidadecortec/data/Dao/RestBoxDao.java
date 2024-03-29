package com.mpms.relatorioacessibilidadecortec.data.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.common.util.concurrent.ListenableFuture;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxAccOneUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxAccTwoUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxFirstUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxSinkUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxToilUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxUpViewUpdate;

import java.util.List;

@Dao
public interface RestBoxDao {

    @Insert
    void insertRestBox(RestBoxEntry restBox);

    @Query("SELECT * FROM RestBoxEntry WHERE restID = :restID ORDER BY boxID DESC")
    LiveData<List<RestBoxEntry>> getBoxesInRestroom(int restID);

    @Query("SELECT * FROM RestBoxEntry WHERE restID IN (:restID)")
    LiveData<List<RestBoxEntry>> getAllBoxesInRestroom(List<Integer> restID);

    @Query("SELECT boxID, restID, typeBox, comBoxDoorWidth, comBoxFreeDiam, comBoxHasBars, comBoxToiletDoorDist, comBoxWidth, comBoxHasLeftBar, comBoxLeftShapeBarA, " +
            "comBoxLeftShapeBarB, comBoxLeftBarObs, comBoxLeftShapeBarC, comBoxLeftShapeBarD, comBoxLeftShapeBarDiam, comBoxLeftShapeBarDist, comBoxHasRightBar, " +
            "comBoxRightShapeBarA, comBoxRightShapeBarB, comBoxRightShapeBarC, comBoxRightShapeBarD, comBoxRightShapeBarDiam, comBoxRightShapeBarDist, comBoxRightBarObs," +
            "comBoxObs, boxUpperPhoto FROM RestBoxEntry WHERE boxID = :boxID")
    LiveData<RestBoxEntry> getCommonBoxData(int boxID);

    @Query("SELECT boxID, restID, typeBox, upViewLength, upViewWidth, upViewMeasureA, upViewMeasureB, upViewMeasureC, upViewMeasureD, upViewMeasureD, upViewObs, " +
            " restDrain, restDrainObs, boxUpperPhoto FROM RestBoxEntry WHERE boxID = :boxID")
    LiveData<RestBoxEntry> getBoxUpViewData(int boxID);

    @Query("SELECT boxID, restID, typeBox, toType, toHeightNoSeat, toHasSeat, toHeightSeat, toHasSoculo, frSoculo, latSoculo, socCorners, toHasFrontBar, frBarA, frBarB, " +
            "frBarC, frBarSect, frBarDist, toHasWall, hasHorBar, horBarD, horBarE, horBarF, horBarDistG, horBarSect, horBarDist, hasVertBar, vertBarH, vertBarI, vertBarJ, " +
            "vertBarSect, vertBarDist, hasSideBar, sideBarD, sideBarE, sideBarDistG, sideBarSect, hasArtBar, artBarH, artBarI, artBarJ, artBarSect, toActDesc, toActHeight, " +
            "toActObs, hasPapHolder, papHolderType, papEmbDist, papEmbHeight, papSupAlign, papSupHeight, papHoldObs, hasDouche, douchePressHeight, doucheActHeight, doucheObs, " +
            "toiletObs, boxToiletPhoto FROM RestBoxEntry WHERE boxID = :boxID")
    LiveData<RestBoxEntry> getBoxToiletData(int boxID);

    @Query("SELECT boxID, restID, typeBox, hasHanger, hangerHeight, hangerObs, hasObjHold, objHoldCorrect, objHoldHeight, objHoldObs, hasSoapHold, soapHoldHeight, " +
            "soapHoldObs, hasTowelHold, towelHoldHeight, towelHoldObs, boxAccessPhoto FROM RestBoxEntry WHERE boxID = :boxID")
    LiveData<RestBoxEntry> getBoxAccessData(int boxID);

    @Query("SELECT boxID, restID, typeBox, hasEmergencyButton, emergencyHeight, emergencyObs, hasWaterValve, waterValveType, waterValveHeight, waterValveObs, hasWallMirror, " +
            "wallMirrorLow, wallMirrorHigh, wallMirrorObs, boxAccessPhoto2 FROM RestBoxEntry WHERE boxID = :boxID")
    LiveData<RestBoxEntry> getBoxAccessDataTwo(int boxID);

    @Query("SELECT boxID, restID, typeBox, hasSink, sinkType, approxMeasureA, approxMeasureB, approxMeasureC, approxMeasureD, approxMeasureE, hasSinkBar, hasLeftFrontHorBar, " +
            "leftFrontHorMeasureA, leftFrontHorMeasureB, leftFrontHorMeasureC, leftFrontHorMeasureD, leftFrontHorDiam, leftFrontHorDist, leftFrontHorObs, hasRightSideVertBar, " +
            "rightSideVertMeasureA, rightSideVertMeasureB, rightSideVertMeasureC, rightSideVertMeasureD, rightSideVertMeasureE, rightSideVertDiam, rightSideVertDist, rightSideVertObs, " +
            "sinkHasMirror, sinkMirrorLow, sinkMirrorHigh, sinkObs, boxSinkPhoto FROM RestBoxEntry WHERE boxID = :boxID")
    LiveData<RestBoxEntry> getBoxSinkData(int boxID);

    @Query("SELECT * FROM RestBoxEntry WHERE boxID == :boxID")
    LiveData<RestBoxEntry> getOneBoxEntry(int boxID);

    @Query("SELECT * FROM RestBoxEntry WHERE boxID == (SELECT MAX(boxID) from RestBoxEntry)")
    LiveData<RestBoxEntry> getLastBoxEntry();

    @Query("DELETE FROM RestBoxEntry WHERE boxID == :boxID")
    void deleteOneBox(int boxID);

    @Update(entity = RestBoxEntry.class)
    void updateBoxFirstData(RestBoxFirstUpdate... comBoxUpdate);

    @Update(entity = RestBoxEntry.class)
    void updateBoxUpView(RestBoxUpViewUpdate... upViewUpdate);

    @Update(entity = RestBoxEntry.class)
    void updateBoxToilet(RestBoxToilUpdate... toilUpdate);

    @Update(entity = RestBoxEntry.class)
    void updateBoxAccOne(RestBoxAccOneUpdate... accOneUpdate);

    @Update(entity = RestBoxEntry.class)
    void updateBoxAccTwo(RestBoxAccTwoUpdate... accTwoUpdate);

    @Update(entity = RestBoxEntry.class)
    void updateBoxSink(RestBoxSinkUpdate... sinkUpdate);

    //    Listenable
    @Query("SELECT * FROM RestBoxEntry WHERE restID IN (:restID)")
    ListenableFuture<List<RestBoxEntry>> getListAllBoxes(List<Integer> restID);

    @Query("SELECT boxID FROM RestBoxEntry WHERE restID IN (:restID)")
    ListenableFuture<List<Integer>> getListAllBoxesID(List<Integer> restID);

}
