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

    private static final String CREATE_JWT_VERSION_TABLE_QUERY = "CREATE TABLE " + Constants.JWT_VERSION_TABLE_NAME + " (" + Constants.JWT_VERSION_ID_COLUMN_NAME + " INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, " + Constants.JWT_VERSION_CREATION_DATE_COLUMN_NAME + " DATETIME NOT NULL, " + Constants.JWT_VERSION_EXPIRATION_DATE_COLUMN_NAME + " DATETIME NOT NULL, comment varchar(255))";
    private static final String ADD_IN_FIRST_JWT_VERSION_QUERY = "INSERT INTO " + Constants.JWT_VERSION_TABLE_NAME + " (" + Constants.JWT_VERSION_CREATION_DATE_COLUMN_NAME + ", " + Constants.JWT_VERSION_EXPIRATION_DATE_COLUMN_NAME + ", comment) VALUES(CURRENT_TIMESTAMP, '9999-12-31 23:59:59', 'First JWT Version')";
    private static final String DELETE_JWT_VERSION_TABLE = "DROP TABLE " + Constants.JWT_VERSION_TABLE_NAME;

    private static final String CREATE_ENTRANCE_TABLE_QUERY;

    @Override
    public void createAndInitializeAllTables()
    {
        createAndInitJwtVersionTable();
    }

    @Override
    public void deleteAllTables()
    {
        deleteJwtVersionTable();
    }

    private void createAndInitJwtVersionTable()
    {
        Map<String, Object> params = new HashMap<>();
        namedParameterJdbcTemplate.update(CREATE_JWT_VERSION_TABLE_QUERY, params);
        namedParameterJdbcTemplate.update(ADD_IN_FIRST_JWT_VERSION_QUERY, params);
    }

    private void deleteJwtVersionTable()
    {
        try
        {
            Map<String, Object> params = new HashMap<>();
            namedParameterJdbcTemplate.update(DELETE_JWT_VERSION_TABLE, params);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
