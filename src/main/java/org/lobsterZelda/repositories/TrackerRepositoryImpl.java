package org.lobsterZelda.repositories;

import org.lobsterZelda.models.SeedCreationSettings;
import org.springframework.stereotype.Repository;

@Repository
public class TrackerRepositoryImpl implements TrackerRepository
{

    @Override
    public boolean publicIDExistsInDatabase(String publicTrackerID) {
        return false;
    }

    @Override
    public void createNewTracker(String publicTrackerID, SeedCreationSettings seedCreationSettings, String base64EncodedJWTString) {

    }
}
