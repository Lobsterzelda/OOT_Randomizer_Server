package org.lobsterZelda.repositories;

import org.lobsterZelda.models.SeedCreationSettings;

public interface TrackerRepository
{
    boolean publicIDExistsInDatabase(String publicTrackerID);
    void createNewTracker(String publicTrackerID, SeedCreationSettings seedCreationSettings);
}
