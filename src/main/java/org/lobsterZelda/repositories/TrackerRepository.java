package org.lobsterZelda.repositories;

import java.util.Map;

public interface TrackerRepository
{
    boolean publicIDExistsInDatabase(String publicTrackerID);
    void createNewTracker(String publicTrackerID, String privateJWTToken, Map<String, String> seedSettings);
}
