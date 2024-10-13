package org.lobsterZelda.constants;

// NOTE: The format for table names is that they start with s_ for static tables and d_ for dynamic tables.
public final class Constants {

    private Constants(){}

    public static final String JWT_EDITOR_COOKIE_TOKEN_NAME = "JWT_Editor_Token";

    // Field names for JWTToken (which is added to a cookie)
    public static final String JWT_PUBLIC_TRACKER_ID_FIELD_NAME = "PublicTrackerID";
    public static final String JWT_TRACKER_CREATION_DATE_FIELD_NAME = "TrackerCreationDate";
    public static final String JWT_OPTIONAL_TRACKER_CREATOR_USERNAME_FIELD_NAME = "OptionalTrackerCreatorUserName";
    public static final String JWT_VERSION_NUMBER_FIELD_NAME = "JWTVersionNumber";

    public static final Integer PUBLIC_TRACKER_ID_SIZE = 16;
    public static final Integer JWT_SECRET_KEY_LENGTH_IN_BYTES = 64;

    // Names of static tables
    public static final String JWT_VERSION_TABLE_NAME = "s_jwt_versions"; // Stores the JWT version numbers, and the date they were created and/or stopped being used.
    public static final String STATIC_ENTRANCE_DATA_TABLE_NAME = "s_entrances"; // Stores the list of entrances in the games, and their properties.
    public static final String STATIC_ITEM_DATA_TABLE_NAME = "s_item_data"; // Stores the list of items in the games, and their properties.
    public static final String STATIC_ITEM_CHECK_LOCATION_DATA_TABLE_NAME = "s_item_check_locations"; // Stores the list of itemCheckLocations in the games, and their properties.

    // Names of dynamic tables that store the contents of individual trackers
    public static final String TRACKER_SETTINGS_TABLE_NAME = "d_tracker_seed_settings"; // This contains seed data that should never change after a seed is created.
    public static final String TRACKER_LOCATIONS_TABLE_NAME = "d_tracker_locations"; // This contains the mappings for where each entrance in a seed leads
    public static final String TRACKER_ITEM_TABLE_NAME = "d_tracker_items"; // This contains the mappings for itemID to itemCheckLocationID, and includes a Boolean that tracks if the user has collected the associated item yet. If no entry is present for an item in a seed, that is interpretted as the user hasn't collected the item, and doesn't know where it is.
    // Additionally, if the user has an item but hasn't marked where they got it from, then the associated itemCheckLocation would be -1
    public static final String TRACKER_REMINDERS_TABLE_NAME = "d_tracker_reminders"; // Stores the reminders associated with a seed.
    public static final String TRACKER_MQ_DUNGEONS_TABLE_NAME = "d_tracker_mq_dungeons"; // Stores the Booleans for whether or not a dungeon is MQ. Only applicable for OOT dungeons. Also, only used on seeds where MQ dungeons are included. If an OOT dungeon main entrance isn't present in this table for a seed that could include MQ dungeons, that means it's unknown what type of dungeon it is.

    // STATIC_JWT_VERSION_TABLE column names
    public static final String JWT_VERSION_ID_COLUMN_NAME = "jwt_id";
    public static final String JWT_VERSION_CREATION_DATE_COLUMN_NAME = "jwt_version_creation_date";
    public static final String JWT_VERSION_EXPIRATION_DATE_COLUMN_NAME = "jwt_version_expiration_date";

    // STATIC_ENTRANCE_DATA_TABLE column names:
    public static final String ENTRANCE_ID_COLUMN_NAME = "entranceId";
    public static final String MAP_ENTRANCE_ID_COLUMN_NAME = "mapEntranceId";
    public static final String MAP_FOR_DISPLAY_ENTRANCE_ID_COLUMN_NAME = "mapForDisplayEntranceId";
    public static final String ENTRANCE_NAME_COLUMN_NAME = "entranceName";
    public static final String IS_OOT_OWL_ENTRANCE_COLUMN_NAME = "isOOTOwlEntrance";
    public static final String IS_OOT_WARP_SONG_COLUMN_NAME = "isOOTWarpSong";
    public static final String IS_OOT_CHILD_SAVE_WARP_COLUMN_NAME = "isOOTChildSaveWarp";
    public static final String IS_OOT_ADULT_SAVE_WARP_COLUMN_NAME = "isOOTAdultSaveWarp";
    public static final String IS_MM_SAVE_WARP_COLUMN_NAME = "isMMSaveWarp";
    public static final String IS_MM_SONG_OF_SOARING_WARP_COLUMN_NAME = "isMMSongOfSoaringWarp";
    public static final String IS_WALLMASTER_WARP_COLUMN_NAME = "isWallmasterWarp";
    public static final String IS_VOID_POINT_WARP_COLUMN_NAME = "isVoidPointWarp";
    public static final String IS_OOT_ENTRANCE_COLUMN_NAME = "isOOTEntrance";
    public static final String IS_IN_DUNGEON_COLUMNN_NAME = "isInDungeon";
    public static final String IS_BOSS_ROOM_COLUMN_NAME = "isBossRoom";
    public static final String IS_IN_GROTTO_COLUMN_NAME = "isInGrotto";
    public static final String IS_IN_HOUSE_COLUMN_NAME = "isInHouse";
    public static final String IS_A_MAP_COLUMN_NAME = "isAMap";
    public static final String IS_OOT_TO_MM_ENTRANCE_COLUMN_NAME = "isOOTToMMEntrance";
    public static final String IS_CHILD_ONLY_ENTRANCE_COLUMN_NAME = "isChildOnlyEntrance";
    public static final String IS_ADULT_ONLY_ENTRANCE_COLUMN_NAME = "isAdultOnlyEntrance";
    public static final String MAP_PERCENT_FROM_LEFT_COLUMN_NAME = "mapPercentFromLeftEdge";
    public static final String MAP_PERCENT_FROM_TOP_COLUMN_NAME = "mapPercentFromTopEdge";
    public static final String MAP_PERCENT_FROM_RIGHT_COLUMN_NAME = "mapPercentFromRightEdge";
    public static final String MAP_PERCENT_FROM_BOTTOM_COLUMN_NAME = "mapPercentFromBottomEdge";
    public static final String PERCENT_FROM_LEFT_EDGE_OF_MAP_COLUMN_NAME = "percentFromLeftEdgeOfMap";
    public static final String PERCENT_FROM_TOP_EDGE_OF_MAP_COLUMN_NAME = "percentFromTopEdgeOfMap";
    public static final String PATH_TO_MAP_IMAGE_COLUMN_NAME = "pathToMapImage";

    // STATIC_ITEM_DATA_TABLE column names:
    public static final String ITEM_ID_COLUMN_NAME = "id";
    public static final String MAP_ID_FOR_ITEM_COLUMN_NAME = "mapId";
    public static final String ITEM_NAME_COLUMN_NAME = "name";
    public static final String ITEM_DESCRIPTION_COLUMN_NAME = "description";
    public static final String IS_OOT_ITEM_COLUMN_NAME = "isOOTItem";

    // STATIC_ITEM_CHECK_LOCATION_DATA_TABLE column names:
    public static final String ITEM_CHECK_LOCATION_ID_COLUMN_NAME = "id";
    public static final String MAP_ID_FOR_ITEM_CHECK_LOCATION_COLUMN_NAME = "mapId";
    public static final String ITEM_CHECK_LOCATION_NAME_COLUMN_NAME = "name";
    public static final String ITEM_CHECK_LOCATION_DESCRIPTION_COLUMN_NAME = "description";
    public static final String IS_OOT_ITEM_CHECK_LOCATION_COLUMN_NAME = "isOOtItem";

    public static final String JWT_VERSION_PROPERTY_PREFIX = "jwtKeyVersion_";

}
