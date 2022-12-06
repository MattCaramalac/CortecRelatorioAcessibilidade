package com.mpms.relatorioacessibilidadecortec.report;

public interface StandardMeasurements {

    //    Sidewalk Measurements
    double sideWidth = 1.9;
    double freeLaneWidth = 1.2;
    double transSlope = 3; //Valor em %
    double minDirTactWidth = 0.25;
    double maxDirTactWidth = 0.40;

    //    Sidewalk Slope Measurements
    double maxSlopeLongAngle = 8.33;
    double minSideSlopeWidth = 1.2;

    //    Sidewalk Ramp Measurements
    double minFlightWidth = 1.2;
    double minTactWidthRamp = 0.25;
    double maxTactWidthRamp = 0.60;
    double minDistTactRamp = 0.25;
    double maxDistTactRamp = 0.32;
    double minInterLength = 1.2;
    double lowestRampHeight = 0.8;
    double medRampHeight = 1.0;
    double highestRampHeight = 1.5;
    double minAngleRamp = 5;
    double medAngleRamp = 6.25;
    double maxAngleRamp = 8.33;
    double maxLengthUntilInterLevel = 50;

    //    Command & Switch Heights
    double minSwHeight = 0.6;
    double maxSwHeight = 1.0;
    double minDbHeight = 0.4;
    double maxDbHeight = 1.0;
    double minIntTelHeight = 0.8;
    double maxIntTelHeight = 1.2;
    double minWinHeight = 0.6;
    double maxWinHeight = 1.2;
    double minDoorHandle = 0.8;
    double maxDoorHandle = 1.1;

    //    Alert Tactile Floor Distance
    double distAlertTactSuspObj = 0.6;
    double minWidthAlertTactSuspObj = 0.25;
    double maxWidthAlertTactSuspObj = 0.60;

    //    Door FreeSpace
    double freeSpaceGeneral = 0.8;
    double freeSpaceSports = 1.0;

    //    Gate Track Height
    double maxHeightTrack = 20; //in mm
    double maxSlopePerc = 50; //in %
    double maxTrackGapSize = 15; //in mm

    //    Gate Slope Sill
    double minSillSlopeWidth = 0.9;

//    Stairs Measurements
    double minStepHeight = 0.16;
    double maxStepHeight = 0.18;
    double minStepWidth = 0.28;
    double maxStepWidth = 0.32;
    double minStairsSum = 0.63;
    double maxStairsSum = 0.65;
    double maxHeightUntilInterLevel = 3.20;
    double minWidthTactFloorStairs = 0.40;
    double minSumTactDistStairs = 0.50;
    double maxSumTactDistStairs = 0.65;
    double minLengthStairsInterLevel = 1.20;
    double minBorderSignWidth = 3; //in cm
    double minBorderSignLength = 7; // in cm

//    Stairs & Ramps Measurements
    double minRailHeight = 1.10;
    double minBeaconHeight = 0.05;
    double highHandrail = 0.92;
    double lowHandrail = 0.70;
    double extendHandrail = 0.30;
    double minHandrailGrip = 30; //in mm
    double maxHandrailGrip = 45; //in mm
    double minDistHandrail = 40; //in mm
}
