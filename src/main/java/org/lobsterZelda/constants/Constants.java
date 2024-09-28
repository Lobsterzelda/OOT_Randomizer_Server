package org.lobsterZelda.constants;

public final class Constants {

    private Constants(){}

    public static final String JWT_EDITOR_COOKIE_TOKEN_NAME = "JWT_Editor_Token";

    public static final String JWT_PUBLIC_TRACKER_ID_FIELD_NAME = "PublicTrackerID";
    public static final String JWT_TRACKER_CREATION_DATE_FIELD_NAME = "TrackerCreationDate";
    public static final String JWT_OPTIONAL_TRACKER_CREATOR_USERNAME_FIELD_NAME = "OptionalTrackerCreatorUserName";
    public static final String JWT_VERSION_NUMBER_FIELD_NAME = "JWTVersionNumber";

    public static final Integer PUBLIC_TRACKER_ID_SIZE = 16;

    public static final String JWT_VERSION_TABLE_NAME = "jwt_versions";
    public static final String STATIC_ENTRANCE_DATA_TABLE_NAME = "static_entrance_data";
    public static final String STATIC_ITEM_DATA_TABLE_NAME = "static_item_data";
    public static final String STATIC_ITEM_CHECK_DATA_TABLE_NAME = "static_item_check_data";

    public static final String TRACKER_SETTINGS_TABLE_NAME = "tracker_seed_settings"; // This contains seed data that should never change - except for the isAdult field, which can be changed for a given Tracker.
    public static final String TRACKER_LOCATIONS_TABLE_NAME = "tracker_locations";
    public static final String TRACKER_ITEM_TABLE_NAME = "tracker_items";
    public static final String TRACKER_REMINDERS_TABLE_NAME = "tracker_reminders";
    public static final String TRACKER_MQ_DUNGEONS_TABLE_NAME = "tracker_mq_dungeons";



}
