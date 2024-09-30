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

    private static final String CREATE_JWT_VERSION_TABLE_QUERY = "CREATE TABLE " + Constants.JWT_VERSION_TABLE_NAME + " (id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, version_creation_date TIMESTAMP NOT NULL, version_expiration_date TIMESTAMP NOT NULL, comment varchar(255))";

    public void createJwtVersionTable()
    {
        Map<String, Object> params = new HashMap<>();
        namedParameterJdbcTemplate.update(CREATE_JWT_VERSION_TABLE_QUERY, params);
    }
}
