package com.mpms.relatorioacessibilidadecortec.data.parcels;

import org.parceler.Parcel;

@Parcel
public class LibParcel {

    Integer rightDistShelves;
    Integer hasLongCorridors;
    Integer hasManeuverArea;
    Integer hasComputers;
    Integer hasAccessComputer;

    public LibParcel() {
//        Empty Constructor
    }

    public LibParcel(Integer rightDistShelves, Integer hasLongCorridors, Integer hasManeuverArea, Integer hasComputers, Integer hasAccessComputer) {
        this.rightDistShelves = rightDistShelves;
        this.hasLongCorridors = hasLongCorridors;
        this.hasManeuverArea = hasManeuverArea;
        this.hasComputers = hasComputers;
        this.hasAccessComputer = hasAccessComputer;
    }

    public Integer getRightDistShelves() {
        return rightDistShelves;
    }

    public void setRightDistShelves(Integer rightDistShelves) {
        this.rightDistShelves = rightDistShelves;
    }

    public Integer getHasLongCorridors() {
        return hasLongCorridors;
    }

    public void setHasLongCorridors(Integer hasLongCorridors) {
        this.hasLongCorridors = hasLongCorridors;
    }

    public Integer getHasManeuverArea() {
        return hasManeuverArea;
    }

    public void setHasManeuverArea(Integer hasManeuverArea) {
        this.hasManeuverArea = hasManeuverArea;
    }

    public Integer getHasComputers() {
        return hasComputers;
    }

    public void setHasComputers(Integer hasComputers) {
        this.hasComputers = hasComputers;
    }

    public Integer getHasAccessComputer() {
        return hasAccessComputer;
    }

    public void setHasAccessComputer(Integer hasAccessComputer) {
        this.hasAccessComputer = hasAccessComputer;
    }
}
