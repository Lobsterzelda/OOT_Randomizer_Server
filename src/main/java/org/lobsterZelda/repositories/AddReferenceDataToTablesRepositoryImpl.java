package org.lobsterZelda.repositories;

import org.lobsterZelda.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class AddReferenceDataToTablesRepositoryImpl implements AddReferenceDataToTablesRepository
{
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public AddReferenceDataToTablesRepositoryImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void addReferenceDataToAllStaticTables()
    {
        addReferenceDataToJwtVersionsTable();
    }

    private void runInsertionQuery(String insertQuery)
    {
        try
        {
            Map<String, Object> params = new HashMap<>();
            namedParameterJdbcTemplate.update(insertQuery, params);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void addReferenceDataToJwtVersionsTable()
    {
        runInsertionQuery(ADD_IN_FIRST_JWT_VERSION_QUERY);
    }

    private static final String ADD_IN_FIRST_JWT_VERSION_QUERY = "INSERT INTO " + Constants.STATIC_JWT_VERSIONS_TABLE_NAME + " (" + Constants.STATIC_JWT_VERSION_CREATION_DATE_COLUMN_NAME + ", " + Constants.STATIC_JWT_VERSION_EXPIRATION_DATE_COLUMN_NAME + ", comment) VALUES(CURRENT_TIMESTAMP, '9999-12-31 23:59:59', 'First JWT Version')";

}
