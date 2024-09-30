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

    private static final String CREATE_JWT_VERSION_TABLE_QUERY = "CREATE TABLE " + Constants.JWT_VERSION_TABLE_NAME + " (id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, version_creation_date DATETIME NOT NULL, version_expiration_date DATETIME NOT NULL, comment varchar(255))";
    private static final String ADD_IN_FIRST_JWT_VERSION_QUERY = "INSERT INTO " + Constants.JWT_VERSION_TABLE_NAME + " (version_creation_date, version_expiration_date, comment) VALUES(CURRENT_TIMESTAMP, '9999-12-31 23:59:59', 'First JWT Version')";
    private static final String DELETE_JWT_VERSION_TABLE = "DROP TABLE " + Constants.JWT_VERSION_TABLE_NAME;

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
        Map<String, Object> params = new HashMap<>();
        namedParameterJdbcTemplate.update(DELETE_JWT_VERSION_TABLE, params);
    }
}
