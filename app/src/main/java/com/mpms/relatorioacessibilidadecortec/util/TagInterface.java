package com.mpms.relatorioacessibilidadecortec.util;

public interface TagInterface {
    //    Identifying Images
    String IMAGE_ID = "IMAGE_ID";

    //    Identifying new entries
    String RECENT_ENTRY = "RECENT_ENTRY";

    //    Tags defining school info
    String SCHOOL_ID = "SCHOOL_ID";
    String SAVE_CLOSE = "SAVE_CLOSE";
    String SAVE_CONTINUE = "SAVE_CONTINUE";
    String UPDATE_CLOSE = "UPDATE_CLOSE";
    String UPDATE_CONTINUE = "UPDATE_CONTINUE";
    String CLOSE_FRAGMENT = "CLOSE_FRAGMENT";
    String OPEN_FRAG_TWO = "OPEN_FRAG_TWO";
    String OPEN_FRAG_THREE = "OPEN_FRAG_THREE";
    String NEXT_ACTIVITY = "NEXT_ACTIVITY";
    String SCHOOL_BUNDLE = "SCHOOL_BUNDLE";
    String DATA_COMPLETE = "DATA_COMPLETE";

    //    Tags defining block info
    String BLOCK_ID = "BLOCK_ID";
    String BLOCK_NUMBER = "BLOCK_NUMBER";
    String BLOCK_OR_SPACE = "BLOCK_OR_SPACE";
    String NEXT_ENTRY = "NEXT_ENTRY";

    //    Tags defining ambient info
    String AMBIENT_ID = "AMBIENT_ID";
    String AMBIENT_TYPE = "AMBIENT_TYPE";
    String AREAS_REG_BUNDLE = "AREAS_REG_BUNDLE";
    String EXT_AREA_REG = "EXT_AREA_REG";
    String SUP_AREA_REG = "SUP_AREA_REG";
    String ROOM_TYPE = "ROOM_TYPE";
    String CHOICE = "CHOICE";
    String HEADER_TEXT = "HEADER_TEXT";
    String BOX_ID = "BOX_ID";

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
    String PCD_ID = "PCD_ID";
    String ELDER_LIST = "ELDER_LIST";
    String ELDER_ID = "ELDER_ID";

    //    Tags defining where the data is coming from
    String FROM_EXT_ACCESS = "FROM_EXT_ACCESS";
    String FROM_SIDEWALK = "FROM_SIDEWALK";
    String FROM_PARKING = "FROM_PARKING";
    String FROM_ROOMS = "FROM_ROOMS";
    String FROM_REST = "FROM_REST";
    String FROM_BOX = "FROM_BOX";
    String FROM_COLLECTIVE = "FROM_COLLECTIVE";
    String REST_TYPE = "REST_TYPE";
    String CIRCULATION = "CIRCULATION";

    String NEW_SIDEWALK_ENTRY = "NEW_SIDEWALK_ENTRY";


    //    Tags used in Inspection Activity
    String LOAD_CHILD_DATA = "LOAD_CHILD_DATA";
    String LOAD_CHILD_DATA_2 = "LOAD_CHILD_DATA_2";
    String LOAD_CHILD_DATA_3 = "LOAD_CHILD_DATA_3";
    String GATHER_CHILD_DATA = "GATHER_CHILD_DATA";
    String GATHER_CHILD_DATA_2 = "GATHER_CHILD_DATA_2";
    String GATHER_CHILD_DATA_3 = "GATHER_CHILD_DATA_3";
    String GATHER_CHILD_DATA_4 = "GATHER_CHILD_DATA_4";
    String ADD_ITEM_REQUEST = "ADD_ITEM_REQUEST";
    String CHILD_DATA_LISTENER = "CHILD_DATA_LISTENER";
    String CHILD_DATA_LISTENER_2 = "CHILD_DATA_LISTENER_2";
    String CHILD_DATA_LISTENER_3 = "CHILD_DATA_LISTENER_3";
    String CHILD_DATA_LISTENER_4 = "CHILD_DATA_LISTENER_4";
    String CHILD_DATA_COMPLETE = "CHILD_DATA_COMPLETE";
    String CHILD_DATA_COMPLETE_2 = "CHILD_DATA_COMPLETE_2";
    String CHILD_DATA_COMPLETE_3 = "CHILD_DATA_COMPLETE_3";
    String CHILD_DATA_COMPLETE_4 = "CHILD_DATA_COMPLETE_3";
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
    String ROOM_OBJ_LIST = "ROOM_OBJ_LIST";
    String OTHER_OBJ_LIST = "OTHER_OBJ_LIST";
    String FREE_LIST = "FREE_LIST";
    String PLAYGROUND_LIST = "PLAYGROUND_LIST";
    String BOX_LIST = "BOX_LIST";
    String CIRC_LIST = "CIRC_LIST";
    String POOL_LIST = "POOL_LIST";

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
    String STEP_ID = "STEP_ID";
    String WINDOW_ID = "WINDOW_ID";
    String FREE_SPACE_ID = "FREE_SPACE_ID";
    String REST_ID = "REST_ID";
    String FOUNTAIN_ID = "FOUNTAIN_ID";
    String PLAY_ID = "PLAY_ID";
    String EQUIP_ID = "EQUIP_ID";
    String CIRC_ID = "CIRC_ID";
    String PROTECT_ID = "PROTECT_ID";
    String SLOPE_ID = "SLOPE_ID";
    String POOL_ID = "POOL_ID";
    String PRAMP_ID = "PRAMP_ID";
    String PBENCH_ID = "PBENCH_ID";
    String PTRANSF_ID = "PTRANSF_ID";
    String PSTAIRS_ID = "PSTAIRS_ID";

    String SLOPE_QNT = "SLOPE_QNT";
    String SLOPE_ANGLE_1 = "SLOPE_ANGLE_1";
    String SLOPE_ANGLE_2 = "SLOPE_ANGLE_2";
    String SLOPE_ANGLE_3 = "SLOPE_ANGLE_3";
    String SLOPE_ANGLE_4 = "SLOPE_ANGLE_4";
    String SLOPE_WIDTH = "SLOPE_WIDTH";
    String SLOPE_HEIGHT = "SLOPE_HEIGHT";

//    Restroom Button Pressed

    String BOX_ENTRY = "BOX_ENTRY";

    //    WaterFountain Measurements

    String HAS_DIFFERENT_HEIGHTS = "HAS_DIFFERENT_HEIGHTS";
    String HIGHEST_SPOUT = "HIGHEST_SPOUT";
    String LOWEST_SPOUT = "LOWEST_SPOUT";
    String ALLOW_FRONTAL = "ALLOW_FRONTAL";
    String FRONTAL_APPROX_HEIGHT = "FRONTAL_APPROX_HEIGHT";
    String FRONTAL_APPROX_DEPTH = "FRONTAL_APPROX_DEPTH";

    String ALLOW_LATERAL = "ALLOW_LATERAL";
    String LAT_APPROX_OBS = "LAT_APPROX_OBS";
    String FAUCET_HEIGHT = "FAUCET_HEIGHT";
    String HAS_CUP_HOLDER = "HAS_CUP_HOLDER";
    String CUP_HOLDER_HEIGHT = "CUP_HOLDER_HEIGHT";

    //    Gate Obstacles
    String GATE_OBS_ID = "GATE_OBS_ID";
    String LOCK_ID = "LOCK_ID";

    //    External Access
    String EXT_ACCESS_ID = "EXT_ACCESS_ID";

    //    Títulos Locais Análise
    String ACCESS_TITLE = "Acessos, Circulações e Área Externa";
    String HELP_TITLE = "Áreas de Apoio";
    String BLOCK_TITLE = "Bloco Nº";

    //    Parcel Indicator
    String CHILD_PARCEL = "CHILD_PARCEL";
    String CHILD_PARCEL_2 = "CHILD_PARCEL_2";
    String CHILD_PARCEL_3 = "CHILD_PARCEL_3";
    String CHILD_PARCEL_4 = "CHILD_PARCEL_4";

    //    Activity Listeners
    String CLOSE_ACTIVITY = "CLOSE_ACTIVITY";

    //    Inspection Memorial View Config
    String MEMORIAL = "MEMORIAL";
    String VISIBLE_MEMORIAL = "VISIBLE_MEMORIAL";

    //    Ambient Numeration
//    1 - Blocos
    int NUM_WC = 0;
    int NUM_FOUNT = 1;
    int NUM_LIB = 2;
    int NUM_COORD = 3;
    int NUM_DIR = 4;
    int NUM_CAFE = 5;
    int NUM_CLASS = 6;
    int NUM_TECH = 7;
    int NUM_RESOURCE = 8;
    int NUM_TEACHER = 9;
    int NUM_REST = 10;
    int NUM_SEC = 11;
    int NUM_OTHER = 12;


}
