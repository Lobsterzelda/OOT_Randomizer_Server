package org.lobsterZelda.repositories;

import org.lobsterZelda.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

// This class is to have its methods executed only once, to create the tables of the Database
// For local testing with an in-memory H2 database, these methods should be called once
// each time the application is started up.
@Repository
public class CreateTablesRepositoryImpl implements CreateTablesRepository
{

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CreateTablesRepositoryImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void createAllTables()
    {
        createStaticJwtVersionsTable();
        createStaticEntranceTable();
        createStaticItemsTable();
        createStaticItemCheckLocationsTable();
        createDynamicTrackerSeedSettingsTable();
        createDynamicTrackerLocationsTable();
    }

    @Override
    public void deleteAllTables()
    {
        deleteStaticJwtVersionsTable();
        deleteStaticEntrancesTable();
        deleteStaticItemsTable();
        deleteStaticItemLocationsTable();
        deleteDynamicTrackerSeedSettingsTable();
        deleteDynamicTrackerLocationsTable();
    }

    private void runUpdateQuery(String updateQuery)
    {
        try
        {
            Map<String, Object> params = new HashMap<>();
            namedParameterJdbcTemplate.update(updateQuery, params);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void createStaticJwtVersionsTable()
    {
        runUpdateQuery(CREATE_STATIC_JWT_VERSIONS_TABLE_QUERY);
    }

    private void deleteStaticJwtVersionsTable()
    {
        runUpdateQuery(DELETE_STATIC_JWT_VERSIONS_TABLE_QUERY);
    }

    private void createStaticEntranceTable()
    {
        runUpdateQuery(CREATE_STATIC_ENTRANCES_TABLE_QUERY);
    }

    private void deleteStaticEntrancesTable()
    {
        runUpdateQuery(DELETE_STATIC_ENTRANCES_TABLE_QUERY);
    }

    private void createStaticItemsTable()
    {
        runUpdateQuery(CREATE_STATIC_ITEMS_TABLE_QUERY);
    }

    private void deleteStaticItemsTable()
    {
        runUpdateQuery(DELETE_STATIC_ITEMS_TABLE_QUERY);
    }

    private void createStaticItemCheckLocationsTable()
    {
        runUpdateQuery(CREATE_STATIC_ITEM_CHECK_LOCATIONS_TABLE_QUERY);
    }

    private void deleteStaticItemLocationsTable()
    {
        runUpdateQuery(DELETE_STATIC_ITEM_CHECK_LOCATIONS_TABLE_QUERY);
    }

    private void createDynamicTrackerSeedSettingsTable()
    {
        runUpdateQuery(CREATE_DYNAMIC_TRACKER_SEED_SETTINGS_TABLE_QUERY);
    }

    private void deleteDynamicTrackerSeedSettingsTable()
    {
        runUpdateQuery(DELETE_DYNAMIC_TRACKER_SEED_SETTINGS_TABLE_QUERY);
    }

    private void createDynamicTrackerLocationsTable()
    {
        runUpdateQuery(CREATE_DYNAMIC_TRACKER_LOCATIONS_TABLE_QUERY);
    }

    private void deleteDynamicTrackerLocationsTable()
    {
        runUpdateQuery(DELETE_DYNAMIC_TRACKER_LOCATIONS_TABLE_QUERY);
    }

    private static final String CREATE_STATIC_JWT_VERSIONS_TABLE_QUERY = "CREATE TABLE " + Constants.STATIC_JWT_VERSIONS_TABLE_NAME + " (" +
            Constants.STATIC_JWT_VERSION_ID_COLUMN_NAME + " INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
            Constants.STATIC_JWT_VERSION_CREATION_DATE_COLUMN_NAME + " DATETIME NOT NULL, " +
            Constants.STATIC_JWT_VERSION_EXPIRATION_DATE_COLUMN_NAME + " DATETIME NOT NULL, " +
            "comment varchar(255))";

    private static final String DELETE_STATIC_JWT_VERSIONS_TABLE_QUERY = "DROP TABLE " + Constants.STATIC_JWT_VERSIONS_TABLE_NAME;

    private static final String CREATE_STATIC_ENTRANCES_TABLE_QUERY = "CREATE TABLE " + Constants.STATIC_ENTRANCES_TABLE_NAME + " ("
            + Constants.ENTRANCE_ID_COLUMN_NAME + " INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, "
            + Constants.ENTRANCE_NAME_COLUMN_NAME + " VARCHAR(1024), "
            + Constants.MAP_ENTRANCE_ID_COLUMN_NAME + " INTEGER NOT NULL, "
            + Constants.MAP_FOR_DISPLAY_ENTRANCE_ID_COLUMN_NAME + " INTEGER NOT NULL, "
            + Constants.IS_OOT_OWL_ENTRANCE_COLUMN_NAME + " BOOLEAN, "
            + Constants.IS_OOT_WARP_SONG_COLUMN_NAME + " BOOLEAN, "
            + Constants.IS_OOT_CHILD_SAVE_WARP_COLUMN_NAME  + " BOOLEAN, "
            + Constants.IS_OOT_ADULT_SAVE_WARP_COLUMN_NAME + " BOOLEAN, "
            + Constants.IS_MM_SAVE_WARP_COLUMN_NAME + " BOOLEAN, "
            + Constants.IS_MM_SONG_OF_SOARING_WARP_COLUMN_NAME + " BOOLEAN, "
            + Constants.IS_WALLMASTER_WARP_COLUMN_NAME + " BOOLEAN, "
            + Constants.IS_VOID_POINT_WARP_COLUMN_NAME + " BOOLEAN, "
            + Constants.IS_OOT_ENTRANCE_COLUMN_NAME + " BOOLEAN, "
            + Constants.IS_IN_DUNGEON_COLUMN_NAME + " BOOLEAN, "
            + Constants.IS_BOSS_ROOM_COLUMN_NAME + " BOOLEAN, "
            + Constants.IS_IN_GROTTO_COLUMN_NAME  + " BOOLEAN, "
            + Constants.IS_IN_HOUSE_COLUMN_NAME + " BOOLEAN, "
            + Constants.IS_A_MAP_COLUMN_NAME + " BOOLEAN, "
            + Constants.IS_OOT_TO_MM_ENTRANCE_COLUMN_NAME + " BOOLEAN, "
            + Constants.IS_CHILD_ONLY_ENTRANCE_COLUMN_NAME + " BOOLEAN, "
            + Constants.IS_ADULT_ONLY_ENTRANCE_COLUMN_NAME + " BOOLEAN, "
            + Constants.MAP_PERCENT_FROM_LEFT_COLUMN_NAME + " DOUBLE, "
            + Constants.MAP_PERCENT_FROM_TOP_COLUMN_NAME + " DOUBLE, "
            + Constants.MAP_PERCENT_FROM_RIGHT_COLUMN_NAME + " DOUBLE, "
            + Constants.MAP_PERCENT_FROM_BOTTOM_COLUMN_NAME + " DOUBLE, "
            + Constants.ENTRANCE_PERCENT_FROM_LEFT_EDGE_OF_MAP_COLUMN_NAME + " DOUBLE, "
            + Constants.ENTRANCE_PERCENT_FROM_TOP_EDGE_OF_MAP_COLUMN_NAME + " DOUBLE, "
            + Constants.PATH_TO_MAP_IMAGE_COLUMN_NAME + " VARCHAR(1024))";

    private static final String DELETE_STATIC_ENTRANCES_TABLE_QUERY = "DROP TABLE " + Constants.STATIC_ENTRANCES_TABLE_NAME;

    private static final String CREATE_STATIC_ITEMS_TABLE_QUERY = "CREATE TABLE " + Constants.STATIC_ITEMS_TABLE_NAME + " ("
            + Constants.ITEM_ID_COLUMN_NAME + " INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, "
            + Constants.MAP_ID_FOR_ITEM_COLUMN_NAME + " INTEGER, "
            + Constants.ITEM_NAME_COLUMN_NAME + " VARCHAR(1024), "
            + Constants.ITEM_DESCRIPTION_COLUMN_NAME + " VARCHAR(2048), "
            + Constants.IS_OOT_ITEM_COLUMN_NAME + " BOOLEAN, "
            + Constants.ITEM_PERCENT_FROM_LEFT_EDGE_OF_MAP_COLUMN_NAME + " DOUBLE, "
            + Constants.ITEM_PERCENT_FROM_TOP_EDGE_OF_MAP_COLUMN_NAME + " DOUBLE)";

    private static final String DELETE_STATIC_ITEMS_TABLE_QUERY = "DROP TABLE " + Constants.STATIC_ITEMS_TABLE_NAME;

    private static final String CREATE_STATIC_ITEM_CHECK_LOCATIONS_TABLE_QUERY = "CREATE TABLE " + Constants.STATIC_ITEM_CHECK_LOCATIONS_TABLE_NAME + " ("
            + Constants.ITEM_CHECK_LOCATION_ID_COLUMN_NAME + " INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, "
            + Constants.MAP_ID_FOR_ITEM_CHECK_LOCATION_COLUMN_NAME + " INTEGER, "
            + Constants.ITEM_CHECK_LOCATION_NAME_COLUMN_NAME + " VARCHAR(1024), "
            + Constants.ITEM_CHECK_LOCATION_DESCRIPTION_COLUMN_NAME + " VARCHAR(1024), "
            + Constants.IS_OOT_ITEM_CHECK_LOCATION_COLUMN_NAME + " BOOLEAN, "
            + Constants.ITEM_CHECK_LOCATION_PERCENT_FROM_LEFT_EDGE_OF_MAP_COLUMN_NAME + " DOUBLE, "
            + Constants.ITEM_CHECK_LOCATION_PERCENT_FROM_TOP_EDGE_OF_MAP_COLUMN_NAME + " DOUBLE)";

    private static final String DELETE_STATIC_ITEM_CHECK_LOCATIONS_TABLE_QUERY = "DROP TABLE " + Constants.STATIC_ITEM_CHECK_LOCATIONS_TABLE_NAME;

    private static final String CREATE_DYNAMIC_TRACKER_SEED_SETTINGS_TABLE_QUERY = "CREATE TABLE " + Constants.DYNAMIC_TRACKER_SEED_SETTINGS_TABLE_NAME + " ("
            + Constants.TRACKER_ID_COLUMN_NAME + " INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, "
            + Constants.INCLUDES_OOT_LOCATIONS_COLUMN_NAME + " BOOLEAN, "
            + Constants.INCLUDES_MQ_DUNGEONS_COLUMN_NAME + " BOOLEAN, "
            + Constants.INCLUDES_MM_LOCATIONS_COLUMN_NAME + " BOOLEAN, "
            + Constants.RANDOMIZED_OOT_WARP_SONGS_COLUMN_NAME + " BOOLEAN, "
            + Constants.RANDOMIZED_OOT_OWL_LOCATIONS_COLUMN_NAME + " BOOLEAN, "
            + Constants.RANDOMIZED_OOT_WALLMASTERS_COLUMN_NAME + " BOOLEAN, "
            + Constants.RANDOMIZED_OOT_THIEVES_HIDEOUT_LOCATIONS_COLUMN_NAME + " BOOLEAN, "
            + Constants.RANDOMIZED_MM_SONG_OF_SOARING_WARPS_COLUMN_NAME + " BOOLEAN, "
            + Constants.RANDOMIZED_MM_WALLMASTERS_COLUMN_NAME + " BOOLEAN, "
            + Constants.RANDOMIZED_MM_WATER_VOIDOUTS_COLUMN_NAME + " BOOLEAN, "
            + Constants.RANDOMIZED_LOCATION_OF_GAME_CHANGE_COLUMN_NAME + " BOOLEAN, "
            + Constants.RANDOMIZED_OOT_CHILD_SAVEWARP_LOCATION_COLUMN_NAME + " BOOLEAN, "
            + Constants.RANDOMIZED_OOT_ADULT_SAVEWARP_LOCATION_COLUMN_NAME + " BOOLEAN, "
            + Constants.RANDOMIZED_MM_SAVEWARP_LOCATION_COLUMN_NAME + " BOOLEAN, "
            + Constants.DECOUPLED_ENTRANCES_COLUMN_NAME + " BOOLEAN, "
            + Constants.RANDOMIZED_OOT_ITEMS_COLUMN_NAME + " BOOLEAN, "
            + Constants.RANDOMIZED_MM_ITEMS_COLUMN_NAME + " BOOLEAN, "
            + Constants.ALLOW_OOT_WARP_SONGS_IN_MM_COLUMN_NAME + " BOOLEAN, "
            + Constants.ALLOW_MM_WARP_SONGS_IN_OOT_COLUMN_NAME + " BOOLEAN, "
            + Constants.OPTIONAL_TRACKER_CREATOR_USER_NAME_COLUMN_NAME + " VARCHAR(256))";

    private static final String DELETE_DYNAMIC_TRACKER_SEED_SETTINGS_TABLE_QUERY = "DROP TABLE " + Constants.DYNAMIC_TRACKER_SEED_SETTINGS_TABLE_NAME;

    private static final String CREATE_DYNAMIC_TRACKER_LOCATIONS_TABLE_QUERY = "CREATE TABLE " + Constants.DYNAMIC_TRACKER_LOCATIONS_TABLE_NAME + " ("
            + Constants.DYNAMIC_TRACKER_ID_COLUMN_NAME + " INTEGER NOT NULL, "
            + Constants.DYNAMIC_TRACKER_SOURCE_ID_COLUMN_NAME + " INTEGER, "
            + Constants.DYNAMIC_TRACKER_DESTINATION_ID_COLUMN_NAME + " INTEGER)";

    private static final String DELETE_DYNAMIC_TRACKER_LOCATIONS_TABLE_QUERY = "DROP TABLE " + Constants.DYNAMIC_TRACKER_LOCATIONS_TABLE_NAME;


}
