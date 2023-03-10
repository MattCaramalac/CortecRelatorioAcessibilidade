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
    double minPrecisionCom = 0.8;
    double maxPrecisionCom = 1.0;
    double minActionHeight = 0.40;
    double maxActionHeight = 0.75;
    double minAccessHeight = 0.80;
    double maxAccessHeight = 1.20;


    //    Alert Tactile Floor Distance
    double distAlertTactSuspObj = 0.6;
    double minWidthAlertTactSuspObj = 0.25;
    double maxWidthAlertTactSuspObj = 0.60;

    //    Door
    double freeSpaceGeneral = 0.80;
    double freeSpaceSports = 1.00;
    double antiImpactCoatHeight = 0.40;
    double minHorBarLength = 0.40;
    double horBarFrameDist = 0.10;
    double minHandleGrip = 25; //in mm
    double maxHandleGrip = 35; // in mm
    double doorHorBarMinHeight = 0.80;
    double doorHorBarMaxHeight = 1.10;
    double doorWinMinWidth = 0.20;
    double doorWinMinLowerHeight = 0.40;
    double doorWinMaxLowerHeight = 0.90;
    double doorWinMinUpperHeight = 1.50;
    double doorTactSignMinHeight = 0.90;
    double doorTactSignMedHeight = 1.20;
    double doorTactSignMaxHeight = 1.60;
    double doorTactSignMinAngle = 15;
    double doorTactSignMaxAngle = 30;


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

    //    Tables
    double minTableWidth = 0.90;
    double minTableFreeWidth = 0.80;
    double minUnderTableHeight = 0.73;
    double minUpperTableHeight = 0.75;
    double maxUpperTableHeight = 0.85;
    double underTableDepth = 0.50;

    //    Blackboard
    double maxBlackboardHeight = 0.90;

    //    Free Space
    double limitWidthObstacle = 0.40;
    double lowerFreeWidth = 0.8;
    double higherFreeWidth = 0.9;

    //    Counter - Atendimento
    double minCounterWidth = 0.90;
    double minCounterFreeWidth = 0.80;
    double minUnderCounterHeight = 0.73;
    double minUpperCounterHeight = 0.75;
    double maxUpperCounterHeight = 0.85;
    double underCounterDepth = 0.30;

//    Parking
    double minLengthVacParallelPCD = 5.00;
    double minWidthVac = 2.20;
    double maxWidthVac = 2.70;
    double minWidthVacOthersPCD = 2.50;
    double minVacLimWidth = 0.10;
    double maxVacLimWidth = 0.20;
    double minVacLimWidthPCD = 0.20;
    double minVacFreeSpace = 1.20;
    double siaDimensions = 1.20;
    double minLetterHeight = 0.25;
    double maxLetterHeight = 0.40;

//    Restroom
    double minDistToiletWall = 0.8;
    double maxManUnderToilet = 0.1;
    double maxManUnderSink = 0.3;
    double maneuverArea = 1.5;
    double minToiletHeightAdult = 0.43;
    double maxToiletHeightAdult = 0.45;
    double minToiletHeightChild = 0.33;
    double maxToiletHeightChild = 0.35;
    double maxToiletHeightAdultSeat = 0.46;
    double maxToiletHeightChildSeat = 0.36;
    double maxSoculo = 0.05;
    double frBarHeightAdult = 0.75;
    double frBarMaxHeightAdult = 0.89;
    double frBarHeightChild = 0.60;
    double frBarMaxHeightChild = 0.72;
    double frBarMinLength = 0.80;
    double frBarToiletAxisChild = 0.15;
    double frBarToiletAxisAdult = 0.30;
    double fixSideBarMinLengthToilet = 0.20;
    double wallFixBarDistToiletEndBar = 0.50;
    double sideBarToiletAxisAdult = 0.40;
    double sideBarToiletAxisChild = 0.25;
    double artSideBarMinLengthToilet = 0.10;
    double wallFixBarVertDistToilet = 0.30;
    double wallFixBarVertMinLength = 0.70;
    double wallFixBarDistInterBar = 0.10;
    double toiletActionMaxHeight = 1.00;
    double embPaperHolderDistToilet = 0.20;
    double embPaperHolderHeight = 0.55;
    double supPaperHolderHeight = 1.00;
    double minLowerHeightWallMirror = 0.50;
    double minUpperHeightWallMirror = 1.80;
    double sinkFaucetMaxDist = 0.50;
    double sinkFrontApproxKnee = 0.12;
    double sinkFrontApproxFeet = 0.30;
    double sinkMinUnderSpace = 0.30;
    double sinkMinHeight = 0.78;
    double sinkMaxHeight = 0.80;
    double sinkHorBarMinLatDist = 0.04;
    double sinkBarMaxFrontDist = 0.20;
    double sinkHorBarMinUpperHeight = 0.78;
    double sinkHorBarMaxUpperHeight = 0.80;
    double sinkHorBarLowerHeight = 0.65;
    double sinkVertBarMaxDistSinkAxis = 0.50;
    double sinkVertBarMinLength = 0.40;
    double sinkVertBarHeight = 0.90;
    double sinkMirrorMaxLowerHeight = 0.90;
    double emergencyButtonHeight = 0.40;
    double restMinWidthRemodel = 1.50;
    double restMinDiamRemodel = 1.20;

    double urinalDistancePartitions = 0.80;
    double urinalDistanceBars = 0.60;
    double urinalDistAxisLeftBar = 0.30;
    double urinalDistAxisRightBar = 0.30;
    double urinalVertBarMinLength = 0.70;
    double urinalVertBarFloorHeight = 0.75;
    double urinalPartitionWidth = 0.40;
    double urinalPartitionHeight = 1.20;
    double urinalPartitionFloorHeight = 0.30;
    double urinalValveHeight = 1.00;
    double urinalMinLowerOpenHeight = 0.60;
    double urinalMaxLowerOpenHeight = 0.65;

//    RestroomBox
    double minFreeDiamComBox = 0.60;
    double minComBoxWidth = 0.90;
    double minComBoxDoorToiletDist = 0.90;
    double minComBoxBarLength = 0.70;
    double comBoxToiletBarDist = 0.30;
    double comBoxBarHeight = 0.75;

//    WaterFountain
    double lowestSpout = 0.90;
    double minHighestSpout = 1.00;
    double maxHighestSpout = 1.10;
    double lowestSpoutFreeHeight = 0.73;
    double oFountMinActionHeight = 0.80;
    double oFountMaxActionHeight = 1.20;

//    Height
    double lowerReach = 0.40;
    double upperReach = 1.20;

    double lowestHeight = 0.40;
    double lowestAcceptHeight = 0.80;
    double highestAcceptHeight = 1.20;
    double highestHeight = 1.40;
}
