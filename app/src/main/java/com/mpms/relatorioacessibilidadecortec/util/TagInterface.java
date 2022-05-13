package com.mpms.relatorioacessibilidadecortec.util;

public interface TagInterface {
//    Tags defining block info
    String BLOCK_ID = "BLOCK_ID";

    //    Tags defining ambient info
    String AMBIENT_ID = "AMBIENT_ID";
    String AMBIENT_TYPE = "AMBIENT_TYPE";
    String AREAS_REG_BUNDLE = "AREAS_REG_BUNDLE";
    String EXT_AREA_REG = "EXT_AREA_REG";
    String SUP_AREA_REG = "SUP_AREA_REG";

//    Tags defining ramps and stairs info
    String RAMP_OR_STAIRS = "RAMP_OR_STAIRS";
    String FLIGHT_ID = "FLIGHT_ID";
    String HANDRAIL_ID = "HANDRAIL_ID";
    String RAIL_ID = "RAIL_ID";
    String NEXT_FLIGHT = "NEXT_FLIGHT";

    //    Tags defining where the data is coming from
    String FROM_EXT_ACCESS = "FROM_EXT_ACCESS";
    String FROM_SIDEWALK = "FROM_SIDEWALK";
    String FROM_PARKING = "FROM_PARKING";
    String FROM_ROOMS = "FROM_ROOMS";

//    Tags used in Inspection Activity
    String LOAD_CHILD_DATA = "LOAD_CHILD_DATA";
    String GATHER_CHILD_DATA = "GATHER_CHILD_DATA";
    String ADD_ITEM_REQUEST = "ADD_ITEM_REQUEST";
    String CHILD_DATA_LISTENER = "CHILD_DATA_LISTENER";
    String CHILD_DATA_COMPLETE = "CHILD_DATA_COMPLETE";
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
    String EXT_ACCESS_ID = "EXT_ACCESS_ID";

//    External Access New Register
    String NEW_REGISTER = "NEW_REGISTER";
}
