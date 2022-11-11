package com.mpms.relatorioacessibilidadecortec.report;

public interface StandardMeasurements {

//    Irregularities List "Style" - obtained through an exemplar .docx file

//    Sidewalk Measurements
    double sideWidth = 1.9;
    double freeLaneWidth = 1.2;
    double transSlope = 3; //Valor em %
    double minDirTactWidth = 0.25;
    double maxDirTactWidth = 0.4;

//    Sidewalk Slope Measurements
    double maxSlopeLongAngle = 8.33;
    double minSideSlopeWidth = 1.2;

//    Sidewalk Ramp Measurements
    double minFlightWidth = 1.2;
    double lowestRampHeight = 0.8;
    double medRampHeight = 1.0;
    double highestRampHeight = 1.5;
    double minAngleRamp = 5;
    double medAngleRamp = 6.25;
    double maxAngleRamp = 8.33;
    double minTactWidthRamp = 0.25;
    double maxTactWidthRamp = 0.6;
    double minDistTactRamp = 0.25;
    double maxDistTactRamp = 0.32;
    double minInterLength = 1.2;

}
