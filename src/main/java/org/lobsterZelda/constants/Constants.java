package org.lobsterZelda.constants;

public final class Constants {

    private Constants(){}

    public static final String JWT_EDITOR_COOKIE_TOKEN_NAME = "JWT_Editor_Token";

    // Field names for JWTToken (which is added to a cookie)
    public static final String JWT_PUBLIC_TRACKER_ID_FIELD_NAME = "PublicTrackerID";
    public static final String JWT_TRACKER_CREATION_DATE_FIELD_NAME = "TrackerCreationDate";
    public static final String JWT_OPTIONAL_TRACKER_CREATOR_USERNAME_FIELD_NAME = "OptionalTrackerCreatorUserName";
    public static final String JWT_VERSION_NUMBER_FIELD_NAME = "JWTVersionNumber";

    public static final Integer PUBLIC_TRACKER_ID_SIZE = 16;

    // Names of static tables
    public static final String JWT_VERSION_TABLE_NAME = "jwt_versions";
    public static final String STATIC_ENTRANCE_DATA_TABLE_NAME = "static_entrance_data";
    public static final String STATIC_ITEM_DATA_TABLE_NAME = "static_item_data";
    public static final String STATIC_ITEM_CHECK_DATA_TABLE_NAME = "static_item_check_data";

    // Names of dynamic tables that store the contents of individual trackers
    public static final String TRACKER_SETTINGS_TABLE_NAME = "tracker_seed_settings"; // This contains seed data that should never change - except for the isAdult field, which can be changed for a given Tracker.
    public static final String TRACKER_LOCATIONS_TABLE_NAME = "tracker_locations";
    public static final String TRACKER_ITEM_TABLE_NAME = "tracker_items";
    public static final String TRACKER_REMINDERS_TABLE_NAME = "tracker_reminders";
    public static final String TRACKER_MQ_DUNGEONS_TABLE_NAME = "tracker_mq_dungeons";

    // Static entrance table column names
    public static final String ENTRANCE_ID_COLUMN_NAME = "entranceId";
    public static final String MAP_ENTRANCE_ID_COLUMN_NAME = "mapEntranceId";
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




}
