package com.mpms.relatorioacessibilidadecortec.util;

public interface TagInterface {
    //    Tags defining school info
    String SCHOOL_ID = "SCHOOL_ID";

    //    Tags defining block info
    String BLOCK_ID = "BLOCK_ID";

    //    Tags defining ambient info
    String AMBIENT_ID = "AMBIENT_ID";
    String AMBIENT_TYPE = "AMBIENT_TYPE";
    String AREAS_REG_BUNDLE = "AREAS_REG_BUNDLE";
    String EXT_AREA_REG = "EXT_AREA_REG";
    String SUP_AREA_REG = "SUP_AREA_REG";
    String ROOM_TYPE = "ROOM_TYPE";

    //    Tags defining ramps and stairs info
    String RAMP_OR_STAIRS = "RAMP_OR_STAIRS";
    String FLIGHT_ID = "FLIGHT_ID";
    String HANDRAIL_ID = "HANDRAIL_ID";
    String RAIL_ID = "RAIL_ID";
    String NEXT_FLIGHT = "NEXT_FLIGHT";

    //    Parking Lot Data
    String PARKING_ID = "PARKING_ID"; // Used when the Parking Lot is related to the sidewalk
    String HAS_PCD = "HAS_PCD";
    String HAS_ELDERLY = "HAS_ELDERLY";
    String PCD_LIST = "PCD_LIST";
    String ELDER_LIST = "ELDER_LIST";

    //    Tags defining where the data is coming from
    String FROM_EXT_ACCESS = "FROM_EXT_ACCESS";
    String FROM_SIDEWALK = "FROM_SIDEWALK";
    String FROM_PARKING = "FROM_PARKING";
    String FROM_ROOMS = "FROM_ROOMS";

    //    Tags used in Inspection Activity
    String LOAD_CHILD_DATA = "LOAD_CHILD_DATA";
    String LOAD_CHILD_DATA_2 = "LOAD_CHILD_DATA_2";
    String GATHER_CHILD_DATA = "GATHER_CHILD_DATA";
    String GATHER_CHILD_DATA_2 = "GATHER_CHILD_DATA_2";
    String ADD_ITEM_REQUEST = "ADD_ITEM_REQUEST";
    String CHILD_DATA_LISTENER = "CHILD_DATA_LISTENER";
    String CHILD_DATA_LISTENER_2 = "CHILD_DATA_LISTENER_2";
    String CHILD_DATA_COMPLETE = "CHILD_DATA_COMPLETE";
    String CHILD_DATA_COMPLETE_2 = "CHILD_DATA_COMPLETE_2";
    String CLEAR_CHILD_DATA = "CLEAR_CHILD_DATA";
    String PARENT_SAVE_ATTEMPT = "PARENT_SAVE_ATTEMPT";

    //    Tags defining the opening of data lists
    String PARKING_LIST = "PARKING_LIST";
    String EXTERNAL_LIST = "EXTERNAL_LIST";
    String REST_LIST = "REST_LIST";
    String WATER_LIST = "WATER_LIST";
    String SIDEWALK_LIST = "SIDEWALK_LIST";
    String ALLOW_UPDATE = "ALLOW_UPDATE";
    String ROOM_LIST = "ROOM_LIST";
    String ADM_EQUIP_LIST = "ADM_EQUIP_LIST";
    String PLAYGROUND_LIST = "PLAYGROUND_LIST";

    //    Tags defining register's IDs
    String SIDEWALK_ID = "SIDEWALK_ID";
    String RAMP_STAIRS_ID = "RAMP_STAIRS_ID";
    String SIDEWALK_SLOPE_ID = "SIDEWALK_SLOPE_ID";
    String PHONE_ID = "PHONE_ID";
    String TABLE_ID = "TABLE_ID";
    String BOARD_ID = "BOARD_ID";
    String DOOR_ID = "DOOR_ID";
    String SWITCH_ID = "SWITCH_ID";
    String COUNTER_ID = "COUNTER_ID";
    String WINDOW_ID = "WINDOW_ID";
    String FREE_SPACE_ID = "FREE_SPACE_ID";
    String REST_ID = "REST_ID";

    //    External Access New Register
    String NEW_REGISTER = "NEW_REGISTER";

    //    Door Sill Data
    String HEIGHT_INCLINED_SILL = "HEIGHT_INCLINED_SILL";
    String STEP_HEIGHT = "STEP_HEIGHT";
    String SLOPE_QNT = "SLOPE_QNT";
    String SLOPE_ANGLE_1 = "SLOPE_ANGLE_1";
    String SLOPE_ANGLE_2 = "SLOPE_ANGLE_2";
    String SLOPE_ANGLE_3 = "SLOPE_ANGLE_3";
    String SLOPE_ANGLE_4 = "SLOPE_ANGLE_4";
    String SLOPE_WIDTH = "SLOPE_WIDTH";

//    Measures
    String LEFT = "LEFT";
    String RIGHT = "RIGHT";
    String SINK_TYPE = "SINK_TYPE";

    String HAS_HOR = "HAS_HOR";
    String HAS_VERT = "HAS VERT";
    String SIZE_A = "SIZE_A";
    String SIZE_B = "SIZE_B";
    String SIZE_C = "SIZE_C";
    String SIZE_D = "SIZE_D";
    String SIZE_E = "SIZE_E";
    String SIZE_F = "SIZE_F";
    String SIZE_G = "SIZE_G";
    String SIZE_H = "SIZE_H";
    String SIZE_I = "SIZE_I";
    String SIZE_J = "SIZE_J";
    String SIZE_K = "SIZE_K";
    String SIZE_L = "SIZE_L";
    String SIZE_M = "SIZE_M";

    String LEFT_A = "LEFT_A";
    String LEFT_B = "LEFT_B";
    String LEFT_C = "LEFT_C";
    String LEFT_D = "LEFT_D";
    String LEFT_E = "LEFT_E";

    String RIGHT_A = "RIGHT_A";
    String RIGHT_B = "RIGHT_B";
    String RIGHT_C = "RIGHT_C";
    String RIGHT_D = "RIGHT_D";

    String DIAM_A = "DIAM_A";
    String DIAM_B = "DIAM_B";
    String DIAM_C = "DIAM_C";

    String DIST_A = "DIST_A";
    String DIST_B = "DIST_B";
    String DIST_C = "DIST_C";
}
