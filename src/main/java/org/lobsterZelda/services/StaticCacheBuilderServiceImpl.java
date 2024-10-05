package org.lobsterZelda.services;

import jakarta.annotation.PostConstruct;
import org.lobsterZelda.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.security.InvalidParameterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class StaticCacheBuilderServiceImpl implements StaticCacheBuilderService {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public StaticCacheBuilderServiceImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    @PostConstruct
    public void buildAllCaches()
    {
        populateJWTSecretKeyCache();
        populateEntrancesCache();
    }

    private static final String JWT_VERSION_QUERY = "SELECT * FROM " + Constants.JWT_VERSION_TABLE_NAME;

    // temp class only used within the method which populates the JWTSecretKeyCache
    private static class TempJwtVersionData
    {
        public Integer versionID;
        public Long creationDate;
        public Long expirationDate;
    }
    private void populateJWTSecretKeyCache()
    {
        Map<Integer, TempJwtVersionData> jwtVersionData = new HashMap<>();
        final Integer[] highestVersion = {-1};

        namedParameterJdbcTemplate.query(JWT_VERSION_QUERY, (ResultSetExtractor<Object>) resultSet -> {
            while (resultSet.next())
            {
                TempJwtVersionData newVersionObj = new TempJwtVersionData();
                newVersionObj.versionID = resultSet.getInt(Constants.JWT_VERSION_ID_COLUMN_NAME);
                if (newVersionObj.versionID > highestVersion[0])
                    highestVersion[0] = newVersionObj.versionID;
                newVersionObj.creationDate = resultSet.getDate(Constants.JWT_VERSION_CREATION_DATE_COLUMN_NAME).getTime() / 1000L;
                newVersionObj.expirationDate = resultSet.getDate(Constants.JWT_VERSION_EXPIRATION_DATE_COLUMN_NAME).getTime() / 1000L;
                jwtVersionData.put(newVersionObj.versionID, newVersionObj);
            }
            return null;
        });

        if (jwtVersionData.isEmpty())
        {
            throw new InvalidParameterException("Error: JWT Version table was empty. Need at least one entry to start the application!");
        }

        Long currentTime = Instant.now().getEpochSecond();

        TempJwtVersionData highestVersionJWT = jwtVersionData.get(highestVersion[0]);
        if (currentTime < highestVersionJWT.creationDate || currentTime >= highestVersionJWT.expirationDate)
        {
            throw new InvalidParameterException("Error: Highest JWT version was not inside the time range between creation date and expiration date. This is the JWT version that is used to create new seeds, so this may not be expired! Please create a new JWT and associated version for the app");
        }
    }

    private void populateEntrancesCache()
    {

    }
}
