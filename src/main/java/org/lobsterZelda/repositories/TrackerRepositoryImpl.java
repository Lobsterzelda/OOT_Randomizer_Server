package org.lobsterZelda.repositories;

import org.lobsterZelda.models.SeedCreationSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TrackerRepositoryImpl implements TrackerRepository
{

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public TrackerRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public boolean publicIDExistsInDatabase(String publicTrackerID) {
        return false;
    }

    @Override
    public void createNewTracker(String publicTrackerID, SeedCreationSettings seedCreationSettings, String base64EncodedJWTString) {

    }
}
