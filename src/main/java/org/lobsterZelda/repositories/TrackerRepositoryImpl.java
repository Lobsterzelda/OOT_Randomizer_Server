package org.lobsterZelda.repositories;

import java.util.Map;

public class TrackerRepositoryImpl implements TrackerRepository
{

    @Override
    public boolean publicIDExistsInDatabase(String publicTrackerID) {
        return false;
    }

    @Override
    public void createNewTracker(String publicTrackerID, Map<String, String> seedSettings) {

    }
}
