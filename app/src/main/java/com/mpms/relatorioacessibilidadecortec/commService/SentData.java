package com.mpms.relatorioacessibilidadecortec.commService;

import com.google.gson.annotations.SerializedName;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlackboardEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.CounterEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorLockEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.data.entities.FreeSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.GateObsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PlaygroundEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsFlightEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsHandrailEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsRailingEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkSlopeEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SwitchEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.TableEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WindowEntry;

import java.util.List;

public class SentData {

    @SerializedName("School")
    private SchoolEntry school;
    private List<RoomEntry> roomList;
    private List<ExternalAccess> extList;
    private List<ParkingLotEntry> parkList;
    private List<PlaygroundEntry> playList;
    private List<RestroomEntry> restList;
    private List<SidewalkEntry> sideList;
    private List<WaterFountainEntry> fountList;
    private List<RampStairsEntry> roomStRaList;
    private List<RampStairsEntry> sideStRaList;
    private List<RampStairsEntry> extStRaList;
    private List<RampStairsEntry> parkStRaList;
    private List<BlackboardEntry> boardList;
    private List<CounterEntry> counterList;
    private List<DoorEntry> doorList;
    private List<FreeSpaceEntry> freeList;
    private List<SwitchEntry> switchList;
    private List<TableEntry> tableList;
    private List<WindowEntry> windowList;
    private List<DoorLockEntry> doorLockList;
    private List<DoorLockEntry> gateLockList;
    private List<GateObsEntry> gateList;
    private List<PayPhoneEntry> extPhoneList;
    private List<PayPhoneEntry> sidePhoneList;
    private List<SidewalkSlopeEntry> slopeList;
    private List<RampStairsFlightEntry> roomFlightList;
    private List<RampStairsFlightEntry> sideFlightList;
    private List<RampStairsFlightEntry> extFlightList;
    private List<RampStairsFlightEntry> parkFlightList;
    private List<RampStairsRailingEntry> roomRailList;
    private List<RampStairsRailingEntry> sideRailList;
    private List<RampStairsRailingEntry> extRailList;
    private List<RampStairsRailingEntry> parkRailList;
    private List<RampStairsHandrailEntry> roomHandList;
    private List<RampStairsHandrailEntry> sideHandList;
    private List<RampStairsHandrailEntry> extHandList;
    private List<RampStairsHandrailEntry> parkHandList;

    public SentData(SchoolEntry school, List<RoomEntry> roomList, List<ExternalAccess> extList, List<ParkingLotEntry> parkList, List<PlaygroundEntry> playList,
                    List<RestroomEntry> restList, List<SidewalkEntry> sideList, List<WaterFountainEntry> fountList, List<RampStairsEntry> roomStRaList,
                    List<RampStairsEntry> sideStRaList, List<RampStairsEntry> extStRaList, List<RampStairsEntry> parkStRaList, List<BlackboardEntry> boardList,
                    List<CounterEntry> counterList, List<DoorEntry> doorList, List<FreeSpaceEntry> freeList, List<SwitchEntry> switchList, List<TableEntry> tableList,
                    List<WindowEntry> windowList, List<DoorLockEntry> doorLockList, List<DoorLockEntry> gateLockList, List<GateObsEntry> gateList,
                    List<PayPhoneEntry> extPhoneList, List<PayPhoneEntry> sidePhoneList, List<SidewalkSlopeEntry> slopeList, List<RampStairsFlightEntry> roomFlightList,
                    List<RampStairsFlightEntry> sideFlightList, List<RampStairsFlightEntry> extFlightList, List<RampStairsFlightEntry> parkFlightList,
                    List<RampStairsRailingEntry> roomRailList, List<RampStairsRailingEntry> sideRailList, List<RampStairsRailingEntry> extRailList,
                    List<RampStairsRailingEntry> parkRailList, List<RampStairsHandrailEntry> roomHandList, List<RampStairsHandrailEntry> sideHandList,
                    List<RampStairsHandrailEntry> extHandList, List<RampStairsHandrailEntry> parkHandList) {
        this.school = school;
        this.roomList = roomList;
        this.extList = extList;
        this.parkList = parkList;
        this.playList = playList;
        this.restList = restList;
        this.sideList = sideList;
        this.fountList = fountList;
        this.roomStRaList = roomStRaList;
        this.sideStRaList = sideStRaList;
        this.extStRaList = extStRaList;
        this.parkStRaList = parkStRaList;
        this.boardList = boardList;
        this.counterList = counterList;
        this.doorList = doorList;
        this.freeList = freeList;
        this.switchList = switchList;
        this.tableList = tableList;
        this.windowList = windowList;
        this.doorLockList = doorLockList;
        this.gateLockList = gateLockList;
        this.gateList = gateList;
        this.extPhoneList = extPhoneList;
        this.sidePhoneList = sidePhoneList;
        this.slopeList = slopeList;
        this.roomFlightList = roomFlightList;
        this.sideFlightList = sideFlightList;
        this.extFlightList = extFlightList;
        this.parkFlightList = parkFlightList;
        this.roomRailList = roomRailList;
        this.sideRailList = sideRailList;
        this.extRailList = extRailList;
        this.parkRailList = parkRailList;
        this.roomHandList = roomHandList;
        this.sideHandList = sideHandList;
        this.extHandList = extHandList;
        this.parkHandList = parkHandList;
    }

    public SchoolEntry getSchool() {
        return school;
    }

    public void setSchool(SchoolEntry school) {
        this.school = school;
    }

    public List<RoomEntry> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<RoomEntry> roomList) {
        this.roomList = roomList;
    }

    public List<ExternalAccess> getExtList() {
        return extList;
    }

    public void setExtList(List<ExternalAccess> extList) {
        this.extList = extList;
    }

    public List<ParkingLotEntry> getParkList() {
        return parkList;
    }

    public void setParkList(List<ParkingLotEntry> parkList) {
        this.parkList = parkList;
    }

    public List<PlaygroundEntry> getPlayList() {
        return playList;
    }

    public void setPlayList(List<PlaygroundEntry> playList) {
        this.playList = playList;
    }

    public List<RestroomEntry> getRestList() {
        return restList;
    }

    public void setRestList(List<RestroomEntry> restList) {
        this.restList = restList;
    }

    public List<SidewalkEntry> getSideList() {
        return sideList;
    }

    public void setSideList(List<SidewalkEntry> sideList) {
        this.sideList = sideList;
    }

    public List<WaterFountainEntry> getFountList() {
        return fountList;
    }

    public void setFountList(List<WaterFountainEntry> fountList) {
        this.fountList = fountList;
    }

    public List<RampStairsEntry> getRoomStRaList() {
        return roomStRaList;
    }

    public void setRoomStRaList(List<RampStairsEntry> roomStRaList) {
        this.roomStRaList = roomStRaList;
    }

    public List<RampStairsEntry> getSideStRaList() {
        return sideStRaList;
    }

    public void setSideStRaList(List<RampStairsEntry> sideStRaList) {
        this.sideStRaList = sideStRaList;
    }

    public List<RampStairsEntry> getExtStRaList() {
        return extStRaList;
    }

    public void setExtStRaList(List<RampStairsEntry> extStRaList) {
        this.extStRaList = extStRaList;
    }

    public List<RampStairsEntry> getParkStRaList() {
        return parkStRaList;
    }

    public void setParkStRaList(List<RampStairsEntry> parkStRaList) {
        this.parkStRaList = parkStRaList;
    }

    public List<BlackboardEntry> getBoardList() {
        return boardList;
    }

    public void setBoardList(List<BlackboardEntry> boardList) {
        this.boardList = boardList;
    }

    public List<CounterEntry> getCounterList() {
        return counterList;
    }

    public void setCounterList(List<CounterEntry> counterList) {
        this.counterList = counterList;
    }

    public List<DoorEntry> getDoorList() {
        return doorList;
    }

    public void setDoorList(List<DoorEntry> doorList) {
        this.doorList = doorList;
    }

    public List<FreeSpaceEntry> getFreeList() {
        return freeList;
    }

    public void setFreeList(List<FreeSpaceEntry> freeList) {
        this.freeList = freeList;
    }

    public List<SwitchEntry> getSwitchList() {
        return switchList;
    }

    public void setSwitchList(List<SwitchEntry> switchList) {
        this.switchList = switchList;
    }

    public List<TableEntry> getTableList() {
        return tableList;
    }

    public void setTableList(List<TableEntry> tableList) {
        this.tableList = tableList;
    }

    public List<WindowEntry> getWindowList() {
        return windowList;
    }

    public void setWindowList(List<WindowEntry> windowList) {
        this.windowList = windowList;
    }

    public List<DoorLockEntry> getDoorLockList() {
        return doorLockList;
    }

    public void setDoorLockList(List<DoorLockEntry> doorLockList) {
        this.doorLockList = doorLockList;
    }

    public List<DoorLockEntry> getGateLockList() {
        return gateLockList;
    }

    public void setGateLockList(List<DoorLockEntry> gateLockList) {
        this.gateLockList = gateLockList;
    }

    public List<GateObsEntry> getGateList() {
        return gateList;
    }

    public void setGateList(List<GateObsEntry> gateList) {
        this.gateList = gateList;
    }

    public List<PayPhoneEntry> getExtPhoneList() {
        return extPhoneList;
    }

    public void setExtPhoneList(List<PayPhoneEntry> extPhoneList) {
        this.extPhoneList = extPhoneList;
    }

    public List<PayPhoneEntry> getSidePhoneList() {
        return sidePhoneList;
    }

    public void setSidePhoneList(List<PayPhoneEntry> sidePhoneList) {
        this.sidePhoneList = sidePhoneList;
    }

    public List<SidewalkSlopeEntry> getSlopeList() {
        return slopeList;
    }

    public void setSlopeList(List<SidewalkSlopeEntry> slopeList) {
        this.slopeList = slopeList;
    }

    public List<RampStairsFlightEntry> getRoomFlightList() {
        return roomFlightList;
    }

    public void setRoomFlightList(List<RampStairsFlightEntry> roomFlightList) {
        this.roomFlightList = roomFlightList;
    }

    public List<RampStairsFlightEntry> getSideFlightList() {
        return sideFlightList;
    }

    public void setSideFlightList(List<RampStairsFlightEntry> sideFlightList) {
        this.sideFlightList = sideFlightList;
    }

    public List<RampStairsFlightEntry> getExtFlightList() {
        return extFlightList;
    }

    public void setExtFlightList(List<RampStairsFlightEntry> extFlightList) {
        this.extFlightList = extFlightList;
    }

    public List<RampStairsFlightEntry> getParkFlightList() {
        return parkFlightList;
    }

    public void setParkFlightList(List<RampStairsFlightEntry> parkFlightList) {
        this.parkFlightList = parkFlightList;
    }

    public List<RampStairsRailingEntry> getRoomRailList() {
        return roomRailList;
    }

    public void setRoomRailList(List<RampStairsRailingEntry> roomRailList) {
        this.roomRailList = roomRailList;
    }

    public List<RampStairsRailingEntry> getSideRailList() {
        return sideRailList;
    }

    public void setSideRailList(List<RampStairsRailingEntry> sideRailList) {
        this.sideRailList = sideRailList;
    }

    public List<RampStairsRailingEntry> getExtRailList() {
        return extRailList;
    }

    public void setExtRailList(List<RampStairsRailingEntry> extRailList) {
        this.extRailList = extRailList;
    }

    public List<RampStairsRailingEntry> getParkRailList() {
        return parkRailList;
    }

    public void setParkRailList(List<RampStairsRailingEntry> parkRailList) {
        this.parkRailList = parkRailList;
    }

    public List<RampStairsHandrailEntry> getRoomHandList() {
        return roomHandList;
    }

    public void setRoomHandList(List<RampStairsHandrailEntry> roomHandList) {
        this.roomHandList = roomHandList;
    }

    public List<RampStairsHandrailEntry> getSideHandList() {
        return sideHandList;
    }

    public void setSideHandList(List<RampStairsHandrailEntry> sideHandList) {
        this.sideHandList = sideHandList;
    }

    public List<RampStairsHandrailEntry> getExtHandList() {
        return extHandList;
    }

    public void setExtHandList(List<RampStairsHandrailEntry> extHandList) {
        this.extHandList = extHandList;
    }

    public List<RampStairsHandrailEntry> getParkHandList() {
        return parkHandList;
    }

    public void setParkHandList(List<RampStairsHandrailEntry> parkHandList) {
        this.parkHandList = parkHandList;
    }
}
