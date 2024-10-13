package org.lobsterZelda.repositories;

import org.lobsterZelda.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

// This class is to have its methods executed only once, to initialize the database.
// For local testing with an in-memory H2 database, these methods should be called once
// each time the application is started up.
@Repository
public class CreateTablesAndReferenceDataRepositoryImpl implements CreateTablesAndReferenceDataRepository
{

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CreateTablesAndReferenceDataRepositoryImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private static final String CREATE_JWT_VERSION_TABLE_QUERY = "CREATE TABLE " + Constants.STATIC_JWT_VERSION_TABLE_NAME + " (" +
            Constants.STATIC_JWT_VERSION_ID_COLUMN_NAME + " INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
            Constants.STATIC_JWT_VERSION_CREATION_DATE_COLUMN_NAME + " DATETIME NOT NULL, " +
            Constants.STATIC_JWT_VERSION_EXPIRATION_DATE_COLUMN_NAME + " DATETIME NOT NULL, " +
            "comment varchar(255))";
    private static final String ADD_IN_FIRST_JWT_VERSION_QUERY = "INSERT INTO " + Constants.STATIC_JWT_VERSION_TABLE_NAME + " (" + Constants.STATIC_JWT_VERSION_CREATION_DATE_COLUMN_NAME + ", " + Constants.STATIC_JWT_VERSION_EXPIRATION_DATE_COLUMN_NAME + ", comment) VALUES(CURRENT_TIMESTAMP, '9999-12-31 23:59:59', 'First JWT Version')";
    private static final String DELETE_JWT_VERSION_TABLE_QUERY = "DROP TABLE " + Constants.STATIC_JWT_VERSION_TABLE_NAME;

    private static final String CREATE_ENTRANCE_TABLE_QUERY = "CREATE TABLE " + Constants.STATIC_ENTRANCES_TABLE_NAME + " ("
            + Constants.ENTRANCE_ID_COLUMN_NAME + " INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, "
            + Constants.ENTRANCE_NAME_COLUMN_NAME + " varchar(1024), "
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
            + Constants.PERCENT_FROM_LEFT_EDGE_OF_MAP_COLUMN_NAME + " DOUBLE, "
            + Constants.PERCENT_FROM_TOP_EDGE_OF_MAP_COLUMN_NAME + " DOUBLE, "
            + Constants.PATH_TO_MAP_IMAGE_COLUMN_NAME + " varchar(1024))";

    private static final String DELETE_ENTRANCE_TABLE_QUERY = "DROP TABLE " + Constants.STATIC_ENTRANCES_TABLE_NAME;


    @Override
    public void createAndInitializeAllTables()
    {
        createAndInitJwtVersionTable();
        createAndInitStaticEntranceTable();
    }

    @Override
    public void deleteAllTables()
    {
        deleteJwtVersionTable();
        deleteEntrancesTableQuery();
    }

    private void runUpdateQuery(String deleteQuery)
    {
        try
        {
            Map<String, Object> params = new HashMap<>();
            namedParameterJdbcTemplate.update(deleteQuery, params);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void createAndInitJwtVersionTable()
    {
        runUpdateQuery(CREATE_JWT_VERSION_TABLE_QUERY);
        runUpdateQuery(ADD_IN_FIRST_JWT_VERSION_QUERY);
    }

    private void deleteJwtVersionTable()
    {
        runUpdateQuery(DELETE_JWT_VERSION_TABLE_QUERY);
    }

    private void createAndInitStaticEntranceTable()
    {
        runUpdateQuery(CREATE_ENTRANCE_TABLE_QUERY);
    }

    private void deleteEntrancesTableQuery()
    {
        runUpdateQuery(DELETE_ENTRANCE_TABLE_QUERY);
    }

}
