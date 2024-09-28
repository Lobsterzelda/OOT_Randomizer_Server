package org.lobsterZelda.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

// This class is to have its methods executed only once, to initialize the database.
// For local testing with an in-memory H2 database, these methods should be called once
// each time the application is started up.
public class CreateTablesAndReferenceDataRepository
{

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CreateTablesAndReferenceDataRepository(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public static void createJwtVersionTable()
    {

    }
}
